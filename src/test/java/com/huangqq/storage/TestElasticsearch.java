package com.huangqq.storage;

import com.google.gson.Gson;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangqq on 2017/11/13.
 */
public class TestElasticsearch extends BaseJunit4Test{

    @Autowired
    public ElasticsearchTemplate template;

    @Test
    public void createIndex(){
        String indexName = "template";
        boolean suc = false;
        if (template.indexExists(indexName)){
            suc = template.deleteIndex(indexName);
            System.out.println("删除索引" + suc);
        }
        suc = template.createIndex("template");
        System.out.println("创建索引" + suc);
    }

    @Test
    public void insertMap(){
        String indexName = "template";
        String typeName = "myType";
        boolean suc = template.typeExists(indexName, typeName);
        System.out.println("exist?" + suc);

        Map map = new HashMap();
        map.put("indexQueryKey", "indexQueryValue");

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId("1")
                .withIndexName(indexName)
                .withType(typeName)
                .withSource(new Gson().toJson(map))
                .build();

        template.index(indexQuery);
        suc = template.typeExists(indexName, typeName);
        System.out.println("exist?" + suc);
    }

    @Test
    public void testMapping(){
        try {
            String indexName = "template";
            String typeName = "myType";
            Map mapping = template.getMapping(indexName, typeName);
            System.out.println(mapping);

            Map mapping1 = new HashMap();

            XContentBuilder xContentBuilder = XContentFactory
                    .jsonBuilder()
                    .startObject()
//                    .startObject("settings").startObject("_source").field("enabled", false).endObject()
                    .startObject("properties")
                    .startObject("indexQueryKey")
                    .field("type", "string").field("store", "no")
                    .endObject()
                    .endObject()
                    .endObject();
            System.out.println(xContentBuilder.string());
            boolean suc = template.putMapping(indexName, typeName, xContentBuilder);
            System.out.println("mapping put " + suc);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        template.putMapping(indexName, typeName, )
    }

    @Test
    public void testQuery(){
        String indexName = "lol";
        String typeName = "hero_list";

        List<String> ids = new ArrayList<>();
        ids.add("29");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices(indexName)
                .withTypes(typeName)
                .withQuery(QueryBuilders
                    .boolQuery()
                        //模糊匹配 带一个字都匹配       match类型的搜索都是分词过的
//                    .must(QueryBuilders.matchQuery("title", "德莱文")))
                        //完全匹配 必须完全都有
//                    .must(QueryBuilders.matchPhraseQuery("title", "德莱文")))
                        //termquery是完全匹配 但是因为一开始入数据时已经按照一元分词分了
                        .must(QueryBuilders.termQuery("displayName", "荣耀行刑官")))
                .build();
        List<Hero> heroes = template.queryForList(searchQuery, Hero.class);
        System.out.println(heroes);
    }

    @Test
    public void testTermStats(){
        String indexName = "lol";
        String typeName = "hero_list";

        AggregationBuilder aggregationBuilder = AggregationBuilders
                .terms("agg")
                .field("id")
                .subAggregation(AggregationBuilders.count("id"));


        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices(indexName)
                .withTypes(typeName)
                .addAggregation(aggregationBuilder)
                .build();

        List<Hero> heroes = template.queryForList(searchQuery, Hero.class);
//        System.out.println(heroes);


    }

    /**
     * 测试client还是template
     */
    @Test
    public void testClient(){
        String indexName = "netbeans";
        String typeName = "songs";
        Client client = template.getClient();

        AggregationBuilder aggregationBuilder = AggregationBuilders
                .terms("agg")
                .field("id");

        MaxBuilder max = AggregationBuilders
                .max("agg")
                .field("bMusic.playTime");

        SearchResponse response = client
                .prepareSearch(indexName)
                .setTypes(typeName)
                .setQuery(QueryBuilders.matchAllQuery())
                .setSearchType(SearchType.QUERY_THEN_FETCH)
//                .addAggregation(aggregationBuilder)
                .addAggregation(max).setSize(0)
                .get();
        System.out.println(response);
        System.out.println(response.getHits().getHits().length);
        System.out.println(response.getAggregations().asMap().get("agg").getProperty("value"));
        System.out.println(response.getHits().getTotalHits());
//        System.out.println(response.getAggregations());



    }
}
