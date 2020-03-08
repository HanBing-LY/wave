package com.langchao.waveservicefilesystem.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.langchao.wavecommon.exception.ExceptionCast;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.wavepo.filesystem.FileSystem;
import com.langchao.wavepo.filesystem.response.FileSystemCode;
import com.langchao.waveservicefilesystem.mapper.FileSystemMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileSystemService {

	@Resource
	private FastFileStorageClient fastFileStorageClient;

	@Resource
	private FileSystemMapper fileSystemMapper;
	public StorePath upload(MultipartFile multipartFile) {
		//文件验证
		if(StringUtils.isNull(multipartFile)){
			ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
		}
		InputStream is = null;
		try {
			is = multipartFile.getInputStream();
			String name = multipartFile.getOriginalFilename();
			String suffix = name.substring(name.lastIndexOf(".")+1);
			//上传
			StorePath storePath = fastFileStorageClient.uploadFile(is, is.available(), suffix, null);
			//保存记录
			FileSystem fileSystem = new FileSystem();
			fileSystem.setFilePath(storePath.getFullPath());
			fileSystem.setFileSize(is.available());
			fileSystem.setFileName(name);
			fileSystem.setFileType(multipartFile.getContentType());
			fileSystemMapper.insert(fileSystem);
			return storePath;
		} catch (IOException e) {
			ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
			return null;
		}
	}
}
