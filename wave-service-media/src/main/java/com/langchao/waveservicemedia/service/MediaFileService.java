package com.langchao.waveservicemedia.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.langchao.wavecommon.constant.Informations;
import com.langchao.wavecommon.util.StringUtils;
import com.langchao.wavecommon.vo.response.PageInfo;
import com.langchao.wavepo.media.MediaFile;
import com.langchao.wavepo.media.request.QueryMediaFileRequest;
import com.langchao.waveservicemedia.mapper.MediaFileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MediaFileService extends ServiceImpl<MediaFileMapper, MediaFile> {
	@Resource
	private MediaFileMapper mediaFileMapper;

	/**
	 * 媒资管理分页查询
	 * @param pageNo
	 * @param pageSize
	 * @param queryMediaFileRequest
	 * @return
	 */
	public PageInfo queryBy(int pageNo, int pageSize, QueryMediaFileRequest queryMediaFileRequest) {
		if (pageNo <= 0) {
			pageNo = Informations.DEFULTPAGENUM;
		}
		if (pageSize <= 0||pageSize > Informations.MaxPAGESIZE) {
			pageSize = Informations.DEFULTPAGESIZE;
		}
		IPage<MediaFile> page = new Page<>(pageNo, pageSize);
		//条件
		QueryWrapper<MediaFile> wrapper = new QueryWrapper<>();
		if (!StringUtils.isNullOrEmpty(queryMediaFileRequest.getFileOriginalName())) {
			wrapper.like("file_original_name", queryMediaFileRequest.getFileOriginalName());
		}
		if (!StringUtils.isNullOrEmpty(queryMediaFileRequest.getProcessStatus())) {
			wrapper.like("process_status", queryMediaFileRequest.getProcessStatus());
		}
		if (!StringUtils.isNullOrEmpty(queryMediaFileRequest.getTag())) {
			wrapper.eq("tag", queryMediaFileRequest.getTag());
		}
		page=mediaFileMapper.selectPage(page,wrapper);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setList(page.getRecords());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}
}
