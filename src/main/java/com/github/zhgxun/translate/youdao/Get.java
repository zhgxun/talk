package com.github.zhgxun.translate.youdao;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Get {

    public static String post(String url, Map<String, String> requestParams) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        System.out.println(new JSONObject(requestParams).toString());

        List<String> params = new ArrayList<>();

        for (Entry<String, String> entry : requestParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                params.add(new BasicNameValuePair(key, value).toString());
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
