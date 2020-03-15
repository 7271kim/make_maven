package com.seokjin.makemaven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.seokjin.kim.library.GetProperties;
import com.seokjin.kim.library.HttpClientCustom;
import com.seokjin.model.Disclousure;

public class Main {

    public static void main(String[] args) {
      
        /*
        1. API서버에서 고유 번호 Zip파일 받아서 원하는 곳에 zip 만들기. => 서버 다운로드 조금 오래걸림
        Properties secuore = GetProperties.getProperties("config/secure.properties");
        String url       = "https://opendart.fss.or.kr/api/corpCode.xml";
        String crtfc_key = secuore.getProperty("API_KEY_DART");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("crtfc_key", crtfc_key);
        
        HttpClientCustom.getHttpGetStremApiData(url, params, "C:\\Users\\King\\Desktop\\sdd");  
        HttpClientCustom.getHttpGetStremApiData(dartUrl,"C:\\Users\\Jin\\Desktop\\zzz\\corpCode.zip");
        
        */
       
       /*
        2. String 으로 API 서버에서 데이터 받아오기
         Properties secuore = GetProperties.getProperties("config/secure.properties");
        String url = "https://opendart.fss.or.kr/api/list.json";
        Map<String, String> params = new HashMap<String, String>();
        params.put("crtfc_key", secuore.getProperty("API_KEY_DART"));
        params.put("bgn_de", "20190315");
        params.put("end_de", "20200315");
        params.put("page_no", "1");
        params.put("page_count", "100");
        params.put("last_reprt_at", "Y");
        params.put("corp_code", secuore.getProperty("삼성전자"));
        
        try {
            String getDisclosureSearch  =  HttpClientCustom.getStringApiDataWithParamDoGet(url, params);
            System.out.println(getDisclosureSearch);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        */
       
       /*
       3. 원하는 객체로  API 서버에서 데이터 받아오기
        Properties secuore = GetProperties.getProperties("config/secure.properties");
        String url = "https://opendart.fss.or.kr/api/list.json";
        Map<String, String> params = new HashMap<String, String>();
        params.put("crtfc_key", secuore.getProperty("API_KEY_DART"));
        params.put("bgn_de", "20190315");
        params.put("end_de", "20200315");
        params.put("page_no", "1");
        params.put("page_count", "100");
        params.put("last_reprt_at", "Y");
        params.put("corp_code", secuore.getProperty("삼성전자"));
        
        try {
            List<Disclousure> getDisclosureSearch  =  HttpClientCustom.getHttpGetListObjectApiData(url, params, Disclousure.class);
            for (int index = 0; index < getDisclosureSearch.size(); index++) {
                System.out.println(getDisclosureSearch.get(index).getCorp_name());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       */
        /* 
        4. Json통신 쓰레드 처리.
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<String> temp = new ArrayList<>();
        for (int index = 1; index <= 20; index++) {
            TaskGetKospoTop100 task = new TaskGetKospoTop100(index, temp);
            executorService.submit(task);
        }
        
        executorService.shutdown();
        */
        
    }

}
