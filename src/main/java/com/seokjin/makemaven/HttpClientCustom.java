package com.seokjin.makemaven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class HttpClientCustom {
    private static String userAgent;
    private static final HttpClient HTTP_CLIENT;
    private static final Gson GSON_OBJ = new Gson();
    static {
        // HttpClient 세팅
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(300);
        connectionManager.setDefaultMaxPerRoute(50);
        
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoTimeout(2000)
                .setSoKeepAlive(true)
                .setTcpNoDelay(true)
                .setSoReuseAddress(true)
                .build();
        
        HTTP_CLIENT = HttpClients.custom().setConnectionManager(connectionManager).setDefaultSocketConfig(socketConfig).build();
        userAgent = "Mozila/5.0";
    };
    
    private HttpClientCustom() {
    }
    
    public static HttpClient getInstance() {
        return HTTP_CLIENT;
    }
    
    
    public static String getHttpGetStringApiData( String url ) {
        return getHttpGetStringApiDataWithParam(url, Collections.EMPTY_MAP);
    }
    
    public static String getHttpGetStringApiDataWithParam( String url, Map<String, String> params ) {
        String result = "";
        try{
            url = getParamToString(url, params);
            HttpEntity httpEntity = getEntity(url);
            if( httpEntity != null ) {
                result = EntityUtils.toString(httpEntity);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    
    public static String getHttpGetStringDartApiData( String url, String startDate, String endDate, String company ) {
        String result = "";
        Map<String, String> params = new HashMap<>();
        String crtfc_key    = GetProperties.getSecureValue("API_KEY_DART");
        String corp_code    = GetProperties.getSecureValue(company);
        params.put("bgn_de", startDate);
        params.put("end_de", endDate);
        params.put("page_no", "1");
        params.put("page_count", "100");
        params.put("last_reprt_at", "Y");
        params.put("crtfc_key", crtfc_key);
        params.put("corp_code", corp_code);
         
        result = getHttpGetStringApiDataWithParam(url, params);
        
        return result;
    }
    
    public static void getHttpGetStremApiData( String url, String destination ) {
        
        try{
            HttpEntity httpEntity = getEntity(url);
            if( httpEntity != null ) {
                File copyFile = new File(destination);
                try(
                    InputStream  inputStream = httpEntity.getContent();
                    OutputStream outputStream = new FileOutputStream(copyFile);
                  ) {
                    long length = inputStream.transferTo(outputStream);
                    System.out.println("고유번호 zip 파일 저장 완료 " + length);
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static HttpEntity getEntity( String url ) throws Exception {
        
        HttpGet httpGet = new HttpGet(url);
        HttpEntity result = null;
        HttpResponse httpResponse = HTTP_CLIENT.execute(httpGet);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if( statusCode == 200 ) {
            result = httpResponse.getEntity();
        }
        return result;
        
    }
    
    private static String getParamToString( String url, Map<String, String> params ) {
        String resultUrl = "";
        try {
            URIBuilder uri = new URIBuilder(new URI(url));
            for (String key : params.keySet()) {
                uri.addParameter(key, params.get(key));
            }
            resultUrl = uri.build().toString();
            
        } catch ( Exception e) {
            System.out.println(e.getMessage());
        }
        
        return resultUrl;
    }
    
    public static <T> List<T> getHttpGetListObjectDartApiData( String url, String startDate, String endDate, String company, Class<T> model ) {
        List<T> result = Collections.EMPTY_LIST;
        String apiData = getHttpGetStringDartApiData(url, startDate, endDate, company);
        JsonElement jsonElement = JsonParser.parseString(apiData);
        JsonObject jobject = jsonElement.getAsJsonObject();
        JsonArray list  = jobject.getAsJsonArray("list");
        if( list != null ) {
            Type typeOfModel = TypeToken.getParameterized(List.class, model).getType();
            result = GSON_OBJ.fromJson(list, typeOfModel);
        }
        
        return result;
    }
}
