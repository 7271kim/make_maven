package com.seokjin.makemaven;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientCustom {
    private static String userAgent;
    private static final HttpClient HTTP_CLIENT;
    
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
        String result = "";
        try{
            HttpEntity httpEntity = getEntity(url);
            if( httpEntity != null ) {
                result = EntityUtils.toString(httpEntity);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        
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
                    System.out.println(length);
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
    

}
