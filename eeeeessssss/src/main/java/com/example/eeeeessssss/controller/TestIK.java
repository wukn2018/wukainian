package com.example.eeeeessssss.controller;

import com.example.eeeeessssss.contans.EsContan;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class TestIK {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;




    /**
     * 搜索内容分词
     */
    protected List<String> handlingSearchContent(String searchContent) {

        List<String> searchTermResultList = new ArrayList<>();
        // 按逗号分割，获取搜索词列表
        List<String> searchTermList = Arrays.asList(searchContent.split( EsContan.STRING_TOKEN_SPLIT));

        // 如果搜索词大于 1 个字，则经过 IK 分词器获取分词结果列表
        searchTermList.forEach(searchTerm -> {
            // 搜索词 TAG 本身加入搜索词列表，并解决 will 这种问题
            searchTermResultList.add(searchTerm);
            // 获取搜索词 IK 分词列表
            searchTermResultList.addAll(getIkAnalyzeSearchTerms(searchTerm));
        });

        return searchTermResultList;
    }





    /**
     * 调用 ES 获取 IK 分词后结果
     */
    protected List<String> getIkAnalyzeSearchTerms(String searchContent) {
        AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(
                elasticsearchTemplate.getClient(),
                AnalyzeAction.INSTANCE,
                EsContan.INDEX_NAME01,
                searchContent);

        ikRequest.setTokenizer(EsContan.TOKENIZER_IK_MAX);
        List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();

        // 循环赋值
        List<String> searchTermList = new ArrayList<>();
        ikTokenList.forEach(ikToken -> {
            searchTermList.add(ikToken.getTerm());
        });

        return handlingIkResultTerms(searchTermList);
    }

    /**
     * 如果分词结果：洗发水（洗发、发水、洗、发、水）
     * - 均为词，保留
     * - 词 + 字，只保留词
     * - 均为字，保留字
     */
    private List<String> handlingIkResultTerms(List<String> searchTermList) {
        Boolean isPhrase = false;
        Boolean isWord = false;
        for (String term : searchTermList) {
            if (term.length() > 1)      {
                isPhrase = true;
            } else {
                isWord = true;
            }
        }

        if (isWord & isPhrase) {
            List<String> phraseList = new ArrayList<>();
            searchTermList.forEach(term -> {
                if (term.length() > 1)      {
                    phraseList.add(term);
                }
            });
            return phraseList;
        }
        return searchTermList;
    }












}
