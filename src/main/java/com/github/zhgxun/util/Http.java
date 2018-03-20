package com.github.zhgxun.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HTTP请求处理
 * <p>
 * Apache的HttpClient可以被用于从客户端发送HTTP请求到服务器端
 */
public class Http {

    /**
     * 发送get请求
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @return 响应结果
     */
    public static String get(String url, Map<String, String> requestParams) {
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2. 拼装请求地址和参数
        String uri = Url.query(url, requestParams);

        // 3. 获取HttpGet实例
        HttpGet httpGet = new HttpGet(uri);

        BufferedReader reader = null;

        String inputLine;
        StringBuilder response = new StringBuilder();

        try {
            // 4. 执行http请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            // 5. 从响应中读取数据
            reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            // 6. 逐行读取
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 7. 关闭客户端对象
            try {
                if (reader != null) {
                    reader.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response.toString();
    }

    /**
     * 发送post请求数据
     *
     * @param url           请求地址
     * @param requestParams 请求参数
     * @return 响应结果
     */
    public static String post(String url, Map<String, String> requestParams) {
        String result = null;

        // 1. 使用帮助类HttpClients创建CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2. 基于要发送的HTTP请求类型创建HttpGet或者HttpPost实例
        HttpPost httpPost = new HttpPost(url);

        // 3. 使用addHeader方法添加请求头部, 诸如User-Agent, Accept-Encoding等参数

        List<NameValuePair> params = new ArrayList<>();

        // 4. 对于POST请求, 创建NameValuePair列表, 并添加所有的表单参数, 然后把它填充进HttpPost实体
        for (Entry<String, String> entry : requestParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                params.add(new BasicNameValuePair(key, value));
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            // 5. 通过执行此HttpGet或者HttpPost请求获取CloseableHttpResponse实例
            httpResponse = httpClient.execute(httpPost);

            // 6. 从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity, "UTF-8");
            EntityUtils.consume(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    // 7. 关闭HttpClient资源
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
