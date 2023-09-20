package com.alibaba.datax.example.elasticsearchreader;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.statistics.PerfRecord;
import com.alibaba.datax.plugin.reader.elasticsearchreader.ESClient;
import com.alibaba.datax.plugin.reader.elasticsearchreader.ESReaderErrorCode;
import com.alibaba.fastjson2.JSON;
import com.google.gson.JsonElement;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchScroll;
import io.searchbox.params.SearchType;

import java.util.HashMap;
import java.util.List;

/**
 * @author KJG-999
 * @date 2023/9/20 14:02
 * @description
 */
public class Test {

    public static void main(String[] args) throws Exception {
        SearchResult searchResult;
        ESClient esClient = new ESClient();
        esClient.createClient("http://192.168.110.243:9200","root","root",true,600000,false,false);
        try {
            searchResult = esClient.search("{\"query\": {\"match_all\": {}}}", SearchType.DFS_QUERY_THEN_FETCH, "cloud_web_log", "_doc", "3m", new HashMap<>());
        } catch (Exception e) {
            throw DataXException.asDataXException(ESReaderErrorCode.ES_SEARCH_ERROR, e);
        }
        if (!searchResult.isSucceeded()) {
            throw DataXException.asDataXException(ESReaderErrorCode.ES_SEARCH_ERROR, searchResult.getResponseCode() + ":" + searchResult.getErrorMessage());
        }

        List<Object> sourceAsObjectList = searchResult.getSourceAsObjectList(Object.class, true);
        System.out.println(sourceAsObjectList.size());
        sourceAsObjectList.forEach(obj -> {
            System.out.println(JSON.toJSONString(obj));
        });
        String scrollId = searchResult.getJsonObject().get("_scroll_id").getAsString();
        while (sourceAsObjectList.size() > 0) {
            SearchScroll scroll = new SearchScroll.Builder(scrollId, "3m").build();
            JestResult jestResult = esClient.execute(scroll);
            scrollId = jestResult.getJsonObject().get("_scroll_id").getAsString();
            sourceAsObjectList = jestResult.getSourceAsObjectList(Object.class, true);
            sourceAsObjectList.forEach(obj -> {
                System.out.println(JSON.toJSONString(obj));
            });
        }
    }

}
