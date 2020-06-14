package com.langchao.waveservicesearch.service;


import com.liyuan.wave.po.user.User;
import com.liyuan.wave.po.user.UserVo;
import com.liyuan.wavecommon.constant.Informations;
import com.liyuan.wavecommon.util.StringUtils;
import com.liyuan.wavecommon.vo.response.PageInfo;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EsUserService {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;


    public PageInfo list(UserVo userVo) {
        userVo = Optional.ofNullable(userVo).orElse(new UserVo());
        if (userVo.getPageNumber() == null || userVo.getPageNumber() < 1) {
            userVo.setPageNumber(Informations.DEFULTPAGENUM);
        }
        if (userVo.getPageSize() == null || userVo.getPageSize() < 1 || userVo.getPageSize() > Informations.MaxPAGESIZE) {
            userVo.setPageSize(Informations.DEFULTPAGESIZE);
        }
        String preTags = "<font color=\"red\">";
        String postTags = "</font>";
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //关键字查询
        if (StringUtils.isNotEmpty(userVo.getUserName())) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(userVo.getUserName(), "name")
                    .minimumShouldMatch("70%").field("name", 10));
        }
        //分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(userVo.getPageNumber() - 1, userVo.getPageSize()));
        //高亮
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("name").preTags(preTags).postTags(postTags),
                new HighlightBuilder.Field("description").preTags(preTags).postTags(postTags));
        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        PageInfo<User> pageInfo = new PageInfo<>();
        AggregatedPage<User> page = elasticsearchTemplate.queryForPage(searchQuery, User.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
                //定义查询出来内容存储的集合
                List<User> listUser = new ArrayList<>();
                //获取高亮的结果
                SearchHits searchHits = response.getHits();
                pageInfo.setTotal(searchHits.getTotalHits());
				//获取高亮中所有的内容
				SearchHit[] hits = searchHits.getHits();
				if (hits.length > 0) {
					for (SearchHit hit : hits) {
						User user1 = new User();
						Map<String, Object> sourceAsMap = hit.getSourceAsMap();
						//取出id
						user1.setUserId(Integer.valueOf(sourceAsMap.get("id").toString()));
						;
						//获取第一个字段的高亮内容
						HighlightField highlightField1 = hit.getHighlightFields().get("name");
						if (highlightField1 != null) {
							//获取第一个字段的值并封装给实体类
							String hightValue1 = highlightField1.getFragments()[0].toString();
							user1.setUserName(hightValue1);
						} else {
							//获取原始的值
							String value = (String) hit.getSourceAsMap().get("name");
							user1.setUserName(value);
						}
						//将coursePub对象放入list
						listUser.add(user1);
					}
				}
				return new AggregatedPageImpl(listUser);
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        pageInfo.setList(page.getContent());
        return pageInfo;
    }
}
