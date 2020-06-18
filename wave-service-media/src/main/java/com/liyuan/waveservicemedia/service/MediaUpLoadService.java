package com.liyuan.waveservicemedia.service;

import com.liyuan.wave.po.media.MediaFile;
import com.liyuan.wave.common.util.StringUtils;
import com.liyuan.waveservicemedia.mapper.MediaFileMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;

@Service
public class MediaUpLoadService {

	@Resource
	private MediaFileMapper mediaFileMapper;
	@Resource
	private RabbitTemplate rabbitTemplate;
	/**
	 * 从配置文件获取消息队列信息
	 */
	@Value("${langchao.mq.exchange}")
	private String exchange_name;
	@Value("${langchao.mq.routingKey}")
	private String routing_key;

	@Value("${langchao.upload-location}")
	private String upload_location;

	//得到文件所属目录路径
	private String getFileFolderPath(String fileMd5){
		return upload_location+fileMd5.substring(0,1)+"/"+fileMd5.substring(1,2)+"/"+fileMd5+"/";
	}

	//得到文件的路径
	private String getFilePath(String fileMd5,String fileExt) {
		return getFileFolderPath(fileMd5)+fileMd5+"."+fileExt;
	}
	//得到块文件所属目录路径
	private String getChunkFileFolderPath(String fileMd5){
		return getFileFolderPath(fileMd5)+"chunk/";
	}

	/**
	 * 上传前检查上传环境
	 * - 检查文件是否上传，已上传则直接返回。
	 * - 检查文件上传路径是否存在，不存在则创建。
	 *
	 * 根据文件md5得到文件路径
	 * 规则：
	 *      * 一级目录：md5的第一个字符
	 *      * 二级目录：md5的第二个字符
	 *      * 三级目录：md5
	 *      * 文件名：md5+文件扩展名
	 * @param fileMd5 文件md5值
	 * @param fileName 文件名
	 * @param fileSize 文件大小
	 * @param mimetype
	 * @param fileExt 文件扩展名
	 */
	public void register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
		//1  检查文件在磁盘上是否存在
		//文件目录的路径
		String fileFolderPath = this.getFileFolderPath(fileMd5);
		//文件路径
		String filePath = this.getFilePath(fileMd5,fileExt);

		File file = new File(filePath);
		if(file.exists()){
			//文件存在

		}

		//2 检查文件上传路径是否存在
		File fileFolder = new File(fileFolderPath);
		if(!fileFolder.exists()){
			fileFolder.mkdirs();
		}

		//3.检查文件信息在mysql中是否存在
		MediaFile mediaFile = mediaFileMapper.selectById(fileMd5);
		if(StringUtils.isNotNull(mediaFile)){
			//文件存在

		}

	}


	/**
	 * 分块检查
	 * - 检查分块文件是否上传，已上传则返回true。
	 * - 未上传则检查上传路径是否存在，不存在则创建。
	 * @param fileMd5
	 * @param chunk
	 * @param chunkSize
	 * @return
	 */
	public boolean checkchunk(String fileMd5, Integer chunk, Integer chunkSize) {
		//块目录
		String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
		//块文件
		File chunkFile = new File(chunkFileFolderPath+chunk);
		if(chunkFile.exists()){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 分块上传
	 * - 将分块文件上传到指定的路径。
	 * @param file
	 * @param fileMd5
	 * @param chunk
	 */
	public void uploadchunk(MultipartFile file, String fileMd5, Integer chunk) {
//块目录
		String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
		// 如果块目录不存在，创建块目录
		File chunkFileFolder = new File (chunkFileFolderPath);
		if(!chunkFileFolder.exists()){
			chunkFileFolder.mkdirs();
		}
		//块文件
		File chunkFile = new File(chunkFileFolderPath+chunk);
		try {
			FileOutputStream os = new FileOutputStream(chunkFile);
			IOUtils.copy(file.getInputStream(),os);
			os.close();
		} catch (Exception e) {

		}

	}

	/**
	 * 合并分块
	 * - 将所有分块文件合并为一个文件。
	 * - 在数据库记录文件信息。
	 * @param fileMd5
	 * @param fileName
	 * @param fileSize
	 * @param mimetype
	 * @param fileExt
	 */
	public void mergechunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {
		//块目录
		String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
		File chunkFileFolder = new File(chunkFileFolderPath);
		int count = chunkFileFolder.list().length;

		//创建一个合并文件
		String filePath = this.getFilePath(fileMd5,fileExt);
		File target = new File(filePath);

		//执行合并
		target= this.merge(count,target,fileMd5);
		if(target==null || target.length()<=0){

		}

		// 校验文件的md5值是否和前端传入的md5一致
		boolean flag=this.checkMd5(target,fileMd5);
		if(!flag){

		}


		// 将文件的信息写入mysql
		this.saveMediaFile(fileMd5,fileName,fileSize,mimetype,fileExt);

		// 向MQ发送视频处理消息(m3u8)
		this.sendProcessVideoMsg(fileMd5);
	}

	private void sendProcessVideoMsg(String fileMd5) {
		MediaFile mediaFile = mediaFileMapper.selectById(fileMd5);
		if(StringUtils.isNotNull(mediaFile)) {
			rabbitTemplate.convertAndSend(exchange_name,routing_key,fileMd5);
		}else{

		}
	}


	private void saveMediaFile(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {

		String path = fileMd5.substring(0,1)+"/"+fileMd5.substring(1,2)+"/"+fileMd5+"/";

		MediaFile mediaFile = new MediaFile();
		mediaFile.setFileId(fileMd5);
		mediaFile.setFileOriginalName(fileName);
		mediaFile.setFileName(fileMd5+"."+fileExt);
		//文件目录
		mediaFile.setFilePath(path);
		//m3u8文件
		mediaFile.setFileUrl(path+"hls/"+fileMd5+".m3u8");
		mediaFile.setFileType(fileExt);
		mediaFile.setFileSize(fileSize);
		//状态为上传成功
		mediaFile.setFileStatus("");
		//设置处理状态为处理中
		mediaFile.setUploadTime(new Date());
		mediaFileMapper.insert(mediaFile);
	}

	private boolean checkMd5(File target, String fileMd5) {
		InputStream is = null;
		try {
			is = new FileInputStream(target);
			String md5Str = DigestUtils.md5DigestAsHex(is);
			return md5Str.equalsIgnoreCase(fileMd5);
		} catch (Exception e) {

			return  false;
		}finally{
			try {
				if(is!=null) {
					is.close();
				}
			} catch (IOException e) {

			}
		}

	}

	private File merge(int count, File target,String fileMd5) {
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(target));
			//块目录
			String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
			int len = 0;
			byte[] bs = new byte[1024 * 1024];
			for (int i = 0; i <count; i++) {
				try {
					bis = new BufferedInputStream(new FileInputStream(chunkFileFolderPath + i), 1024 * 1024);
					len = bis.read(bs, 0, bs.length);
					bos.write(bs, 0, len);
				} catch (Exception e) {

				} finally {
					if(bis!=null){
						bis.close();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(bos!=null){
					bos.close();
				}
			} catch (IOException e) {

			}
		}
		return target;

	}
}
