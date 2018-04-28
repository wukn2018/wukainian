package com.example.eeeeessssss.repository;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.common.geo.builders.ShapeBuilders;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.common.geo.ShapeRelation;
import org.springframework.stereotype.Repository;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 *
 * 搜索基础业务类
 */
@Repository
public class QueryRepository {

    public static final Logger logger = LoggerFactory.getLogger( QueryRepository.class );



/*********************************全文查询***************************************/

    /**
     * ok
     * 查询指定索引下的所有内容
     * @return  搜索模板
     */
    public QueryBuilder queryall(){
        QueryBuilder qb = QueryBuilders.matchAllQuery();
        logger.info( "QueryBuilder.isFragment{}---->"+qb.isFragment() );
        return qb;
    }

    /**
     * ok
     * 匹配查询
     * 执行全文查询的标准查询，包括模糊匹配和短语或近似查询。
     * @return
     */
    public QueryBuilder matchQueryByTitle(Map<String,Object> map) {
        logger.info( "map{}--->"+map );
        String name = null;
        Object text = null;
        for(Map.Entry entry:map.entrySet()) {
            name = (String) entry.getKey();
            text = entry.getValue();
        }
        QueryBuilder qb = matchQuery(name,text);
        return qb;
    }

    /**
     * ok
     * 多匹配查询
     * 执行多个匹配。
     * @return
     *
     * 等价于SQL语句，就是，where name=wkn or address=wkn
    　　比如是文章数据。标题，描述，正文。把所有这些文章数据都查出来。

     */
    public QueryBuilder multiMatchQueryByTitle(String str1 , String str2 , String str3) {

        /**
         * 搜索str2=str1 and str3=str1
         */
        QueryBuilder qb = multiMatchQuery( str1,str2,str3 );
        logger.info( "{}--->"+str1+str2+str3 );
        return qb;
    }


    /**
     *
     * ok
     * 常用术语查询
     * 一个更专门的查询，让更多的偏好非常用的单词。
     * @return
     */
    public QueryBuilder commonTermsQueryByTitle(Map<String ,Object> map) {
        logger.info( "map{}---->"+map );
        String name = null;
        Object text = null;
        for(Map.Entry entry:map.entrySet()) {
            name = (String) entry.getKey();
            text = entry.getValue();
        }
        //   "sellingPrice","2000"
        QueryBuilder qb = commonTermsQuery(name,text);
        return qb;
    }

    /**
     * ok
     * 查询字符串查询
     *
     * @return
     */
    public QueryBuilder queryStringQueryByTitle(String sr) {
        QueryBuilder qb = queryStringQuery(sr);
        return qb;
    }

    /**
     * ok
     * 简单查询字符串查询
     *
     * @return
     */
    public QueryBuilder simpleQueryStringByTitle(String str) {
        QueryBuilder qb = simpleQueryStringQuery(str);
        return qb;
    }



/************************************术语级别查询*********************************************/

    /**
     * ok
     * 精确查询 主要针对人名  还有地名
     *
     *name字段中包含“测试数据”或者“es”的文本
     *
     *
     * @return
     */
    public QueryBuilder termQueryByTitle() {
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("shopName", "吴飞鹏","店铺");
        return termsQueryBuilder;
    }

    /**
     * ok
     * 范围查询
     * @return
     */
    public QueryBuilder rangeQueryByTitle() {
        QueryBuilder qb = rangeQuery("carriageId")
                .from(5)
                .to(100)
                //包括较低值意味着from被gt时false或gte当true
                .includeLower(true)
                //包括上限值是指to被lt时false或lte当true
                .includeUpper(false);
        return qb;
    }

    /**
     * ok
     * 存在查询
     *判断文本中是否存在这个字段
     * @return
     */
    public QueryBuilder existsQueryByTitle() {
        QueryBuilder qb = existsQuery("name");
        boolean b = qb.isFragment();
        System.out.println(b);
        return qb;
    }

    /**
     * ok
     * 前缀查询
     *
     * @return
     */
    public QueryBuilder prefixQueryByTitle() {
        QueryBuilder qb = prefixQuery("shopName","吴");
        return qb;
    }

    /**
     * ok
     * 通配符查询
     * ，支持* 任意字符串；？任意一个字符
     *指定字段是未分析的
     * @return
     */
    public QueryBuilder wildcardQueryByTitle() {
        QueryBuilder qb = wildcardQuery("shopName","吴*");
        return qb;
    }

    /**
     * ok
     * 正则表达式查询
     *
     * @return
     */
    public QueryBuilder regexpQueryByTitle(String str1,String str2) {
        QueryBuilder qb = regexpQuery(str1,str2);
        return qb;
    }

    /**
     * ok
     *
     * 模糊查询
     *分词模糊查询，通过增加fuzziness 模糊属性，来查询term
     * 如下 能够匹配 name 为 “淘”前或后加一个字母的term的 文档
     * fuzziness 的含义是检索的term 前后增加或减少n个单词的匹配查询，
     *
     * QueryBuilders.fuzzyQuery("hotelName", "tel").fuzziness(Fuzziness.ONE)
     * @return
     */
    public QueryBuilder fuzzyQueryByTitle() {
        QueryBuilder qb = fuzzyQuery("sellingPrice","淘");
        return qb;
    }

    /**
     * ok
     * type类型查询
     *根据所有类型查询
     * @return
     */
    public QueryBuilder typeQueryByTitle(String str) {
        QueryBuilder qb = typeQuery(str);
        return qb;
    }

    /**
     * ok
     * 根据ID查询
     * @return
     */
    public QueryBuilder idsQueryTitle() {
        QueryBuilder qb2 = idsQuery()
                .addIds("AWL2rTJbFT_odnL6Z19M", "AWL2rTKYFT_odnL6Z19O");
        return qb2;
    }










/*********************************复合查询***************************************/

    /**
     *
     *指定值查询
     * @return
     */
    public QueryBuilder constantScoreQueryByTitle(String str1,String str2,Float f) {
        QueryBuilder qb = constantScoreQuery(
                termQuery(str1,str2)
        )
                .boost(f);
        return qb;
    }


    /**
     * ok*
     * 多条件查询
     * 可以数字查询
     * @return
     */
    public QueryBuilder dfhj() {
        //多条件设置
        MatchPhraseQueryBuilder mpq1 = QueryBuilders
                .matchPhraseQuery("categoryName","没有");

        MatchPhraseQueryBuilder mpq2 = QueryBuilders
                .matchPhraseQuery("shopName","店铺");

        QueryBuilder qb2 = QueryBuilders.boolQuery()
                //必须  如果是must说明匹配的数据必须要在同一个对象
                .must(mpq1)
                .must(mpq2);
                //应该  如果是should可以匹配多个条件结果
                //.should(  )

        return qb2;
    }


    /**
     *
     13 //.setQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", "tom"))
     14 //   .mustNot(QueryBuilders.matchQuery("age", 16)))//组合查询，支持多个查询条件,并且可以给查询条件设置权重
     15 // .setQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", "zs").boost(8.0f))
     *布尔查询
     * @return
     */
    public QueryBuilder boolQueryByTitle() {
        QueryBuilder qb = boolQuery()
                //必须查询
                .must(termQuery("name", "淘宝"))
                //
                .must(termQuery("address", "苹果栗子花生"));
//                //一定不要查询
//                .mustNot(termQuery("content", "test2"))
//                //应该查询
//                .should(termQuery("content", "test3"))
//                //必须出现在匹配文档中但不参与评分的查询。
//                .filter(termQuery("content", "test5"));
        return qb;
    }

    /**
     *
     *指定值查询
     * @return
     */
    public QueryBuilder disMaxQueryByTitle(String str1,String str2,Float f) {
        QueryBuilder qb = disMaxQuery()
                .add(termQuery("name", "kimchy"))
                .add(termQuery("name", "elasticsearch"))
                .boost(1.2f)
                .tieBreaker(0.7f);
        return qb;
    }

    /**
     *
     *指定值查询
     * @return
     */
    public QueryBuilder functionScoreQueryByTitle(String str1,String str2,Float f) {
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] functions = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        //根据查询添加第一个函数
                        matchQuery("name", "kimchy"),
                        //并根据给定的种子随机分数
                        randomFunction("ABCDEF")),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                        //根据年龄字段添加另一个功能
                        exponentialDecayFunction("age", 0L, 1L))
        };
        QueryBuilder qb = QueryBuilders.functionScoreQuery(functions);
        return qb;
    }












/***************************加入查询**********************************************/

    /**
     *ok
     * 过滤  大于 小于
     *嵌套查询
     * 数字查询
     * @return
     */
    public QueryBuilder nestedQueryByTitle() {
//        QueryBuilder qb = nestedQuery(
//                //嵌套文档的路径
//                "obj1",
//                //您的查询。查询中引用的任何字段都必须使用完整路径（完全限定
//                boolQuery()
//                        .must(matchQuery("name", "es测试数据"))
//                        .must(rangeQuery("id").gt(1)),
//                //评分模式可能是ScoreMode.Max，
//                // ScoreMode.Min，ScoreMode.Total，ScoreMode.Avg或ScoreMode.None
//                ScoreMode.Avg
//        );


        /**
         * 方法： (1) gte() :范围查询将匹配字段值大于或等于此参数值的文档。

         (2) gt() :范围查询将匹配字段值大于此参数值的文档。

         (3) lte() :范围查询将匹配字段值小于或等于此参数值的文档。

         (4) lt() :范围查询将匹配字段值小于此参数值的文档。

         (5) from() 开始值 to() 结束值 这两个函数与includeLower()和includeUpper()函数配套使用。

         (6) includeLower(true) 表示 from() 查询将匹配字段值大于或等于此参数值的文档。

         (7) includeLower(false) 表示 from() 查询将匹配字段值大于此参数值的文档。

         (8) includeUpper(true) 表示 to() 查询将匹配字段值小于或等于此参数值的文档。

         (9) includeUpper(false) 表示 to() 查询将匹配字段值小于此参数值的文档。
         */
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("costPrice");
        // from 从109开始
        rangeQueryBuilder.from(50);

        //to 到110结束
        rangeQueryBuilder.to(110);

        //大于或等于
        //rangeQueryBuilder.gte( 109 );

        //大于
       // rangeQueryBuilder.gt( 109 );

        //小于
        //rangeQueryBuilder.lt( 110 );

        //false包含不包含from开始的值，true表示包含
        rangeQueryBuilder.includeLower(false);
        rangeQueryBuilder.includeUpper(true);
        return rangeQueryBuilder;




    }

    /**
     *
     *有子查询
     * @return
     */
    public QueryBuilder hasChildQueryByTitle() {
        QueryBuilder qb = hasChildQuery(
                //要查询的类型
                "tstype",
                //询问
                termQuery("name","wkn"),
                //得分模式可以是ScoreMode.Avg，ScoreMode.Max，ScoreMode.Min，ScoreMode.None或ScoreMode.Total
                ScoreMode.None
        );
        return qb;
    }


    /**
     *
     *有父查询
     * @return
     */
    public QueryBuilder hasParentQueryByTitle() {
        QueryBuilder qb = hasParentQuery(
                //要查询的父类型
                "blog",
                //询问
                termQuery("tag","something"),
                //是否从父母命中的分数传播给孩子命中
                false
        );
        return qb;
    }





/**********************************专业查询************************************/

    /**
     *
     *实现基于内容推荐, 支持实现一句话相似文章查询
     *
     * @return
     */
    public QueryBuilder moreLikeThisQueryByTitle() {
        //匹配的字段
        String[] fields = {"中国","吴开念"};
        //匹配的文本
        String[] texts = {"cat"};
        MultiGetRequest.Item[] items = null;

        QueryBuilder qb = moreLikeThisQuery(fields, texts, null )
                //忽略阈值
                .minTermFreq(1)
                //生成的查询中的最大条款数
                .maxQueryTerms(12);
        return qb;
    }




/**********************************跨度查询**********************************/

    /**
     *
     *跨度术语查询
     *等同于term查询，但用于其他跨度查询。
     * @return
     */
    public QueryBuilder spanTermQueryByTitle() {
        QueryBuilder qb = spanTermQuery("name","wkn");
        return qb;
    }

    /**
     *
     *跨度多项查询
     * GET /_search
     {
     "query": {
     "span_multi":{
     "match":{
     "prefix" : { "user" :  { "value" : "ki" } }
     }
     }
     }
     }
     *。
     * @return
     */
    public QueryBuilder spanMultiTermQueryBuilderByTitle() {
        QueryBuilder qb = spanMultiTermQueryBuilder(prefixQuery( "name","吴开念" ));
        return qb;
    }

    /**
     *
     *跨度第一查询
     *
     * @return
     */
    public QueryBuilder spanFirstQueryByTitle() {
        QueryBuilder qb = spanFirstQuery(
                spanTermQuery("name", "wkn"),
                //最大结束位置
                3
        );
        return qb;
    }

    /**
     *
     *跨度近查询
     *
     * @return
     */
    public QueryBuilder spanNearQueryByTitle() {
        QueryBuilder qb = spanNearQuery(
                spanTermQuery("field","value1"),
                12)
                .addClause(spanTermQuery("field","value2"))
                .addClause(spanTermQuery("field","value3"))
                .inOrder(false);
        return qb;
    }

    /**
     * 对结果进行高亮设置
     */
    public void testHighLighted() {


        /**
         * 5.0 版本后的高亮设置
         * client.#().#().highlighter(hBuilder).execute().actionGet();
         HighlightBuilder hBuilder = new HighlightBuilder();
         hBuilder.preTags("<h2>");
         hBuilder.postTags("</h2>");
         hBuilder.field("user");    // 设置高亮显示的字段
         */

    }


}