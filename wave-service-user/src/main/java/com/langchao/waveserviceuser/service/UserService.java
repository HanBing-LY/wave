package com.langchao.waveserviceuser.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.langchao.wavecommon.constant.Informations;
import com.langchao.wavecommon.exception.ExceptionCast;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.wavecommon.vo.response.PageInfo;
import com.langchao.wavepo.user.User;
import com.langchao.wavepo.user.UserVo;
import com.langchao.wavepo.user.response.UserCode;
import com.langchao.waveserviceuser.mapper.UserMapper;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

	/**
	 * 组件注入
	 */
	@Resource
	private UserMapper userMapper;
	@Resource
	private FastFileStorageClient fastFileStorageClient;
	@Resource
	private RabbitTemplate rabbitTemplate;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private RestTemplate restTemplate;

	/**
	 * 从配置文件读取信息: 获取消息队列信息
	 */
	@Value("${langchao.mq.exchange}")
	private String exchangeName;
	@Value("${langchao.mq.routingKey}")
	private String routingKey;

	public PageInfo findUserList(UserVo userVo) {
		userVo= Optional.ofNullable(userVo).orElse(new UserVo());
		if (userVo.getPageNumber()==null||userVo.getPageNumber() < 1) {
			userVo.setPageNumber(Informations.DEFULTPAGENUM);
		}
		if (userVo.getPageSize()==null||userVo.getPageSize() < 1||userVo.getPageSize()>Informations.MaxPAGESIZE) {
			userVo.setPageSize(Informations.DEFULTPAGESIZE);
		}
		IPage<User> page = new Page<>(userVo.getPageNumber(), userVo.getPageSize());
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.like("user_name",userVo.getUserName());
		wrapper.eq("sex",userVo.getSex());
		// todo 查布隆过滤器
		// todo  查缓存
//		String s = (String) stringRedisTemplate.opsForValue().get(wrapper.getSqlSelect());
		page=userMapper.selectPage(page, wrapper);
		// todo 写入缓存,过期时间随机
//		redisTemplate.opsForList().set(wrapper.getSqlSelect(),60*60 + (int)(Math.random() * 1000),page);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setList(page.getRecords());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}

	public String publish(String userId) {
		//todo pageId根据userId获得
		String pageId="";
		try {
			saveHtml(pageId);
			sendPostPage(pageId);
		}catch (Exception e){
			ExceptionCast.cast(UserCode.USER_PUBLISH_ERROR);
		}
		return "返回一个页面的url";
	}

	/**
	 * 页面静态化后保存到fastdfs
	 * @param pageId
	 */
	private void saveHtml(String pageId){
		//获取页面静态化后的地址url
		String htmlContext= getHtml(pageId);
		if(StringUtils.isEmpty(htmlContext)||StringUtils.isEmpty(pageId)){
			ExceptionCast.cast(UserCode.USER_GENERATEHTML_SAVEHTMLERROR);
		}
		InputStream is=new ByteArrayInputStream(htmlContext.getBytes());
		StorePath storePath = null;
		try {
			storePath = fastFileStorageClient.uploadFile(is,is.available(),"html",null);
		} catch (IOException e) {
			log.error(e.getMessage());
			ExceptionCast.cast(UserCode.USER_GENERATEHTML_SAVEHTMLERROR);
		}
		String fullPath = storePath.getFullPath();//这是在fastdfs中文件地址坐标
		//todo 将对应的数据库更新
	}

	/**
	 * 向stream服务通知消息队列执行
	 * @param pageId
	 */
	private void sendPostPage(String pageId) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey,pageId);
	}

	/**
	 * 根据页面id获取当前页面的静态html
	 * @param pageId
	 * @return
	 */
	public String getHtml(String pageId) {
		//todo 从fastdfs获取模版
		String template=getTemplateById(pageId);
		//todo 从数据库获取数据
		Map model=getModelByPageId(pageId);
		//todo 以上生成html
		String html=generateHtml(model,template);
		return html;
	}

	private String getTemplateById(String pageId) {
		//todo 从数据库获取模版的地址
		String group="这是storage地址";
		String url="这是模版在storage对应的地址";
		byte[] bytes = fastFileStorageClient.downloadFile(group, url, new DownloadByteArray());
		return new String(bytes);
	}

	private Map getModelByPageId(String pageId) {
		List<User> users = userMapper.selectList(new QueryWrapper<>());
		//todo 存入当前数据
//		String url = users.get(0).getUrl();
//		Map map=restTemplate.getForObject(url,Map.class);
		Map map = new ConcurrentHashMap();
		return map;
	}

	private String generateHtml(Map model, String templateContent) {
		Configuration configuration=new Configuration(Configuration.getVersion());//创建配置对象
		StringTemplateLoader stringTemplateLoader=new StringTemplateLoader();//创建模版加载器
		stringTemplateLoader.putTemplate("template",templateContent);
		configuration.setTemplateLoader(stringTemplateLoader);
		Template template= null;
		//todo 向configuration配置模版加载器
		try {
			template = configuration.getTemplate("template");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
			return html;
		} catch (Exception e) {
			ExceptionCast.cast(UserCode.USER_GENERATEHTML_SAVEHTMLERROR);
			return "";
		}
	}

	/**
	 * 该方法由mq调用从文件管理器下载保存到本地内存,到此页面静态真正完成
	 * @param pageId
	 */
	public void savePageToServerPath(String pageId){
		//todo 根据页面id从数据库获取对应的在fastdfs的坐标
		String group="这是storage地址";
		String url="这是模版在storage对应的地址";
		byte[] bytes = fastFileStorageClient.downloadFile(group, url, new DownloadByteArray());
		//读取流
		ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
		//todo 根据数据库的相关设置拼接文件的绝对路径
		String saveUrl = "这是文件的绝对路径";
		try {
			FileOutputStream fos=new FileOutputStream(saveUrl);
			IOUtils.copy(bis,fos);
		} catch (IOException e) {
			log.error(e.getMessage());
			ExceptionCast.cast(UserCode.USER_GENERATEHTML_SAVEHTMLERROR);
		}
	}

}
