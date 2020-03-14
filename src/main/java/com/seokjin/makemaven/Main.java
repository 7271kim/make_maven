package com.seokjin.makemaven;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.seokjin.kim.library.GetProperties;
import com.seokjin.kim.library.HttpClientCustom;
import com.seokjin.model.Disclousure;

public class Main {

    public static void main(String[] args) {
        
        GetProperties properties = new GetProperties("config/secure.properties");
        Map<String, String> params = new HashMap<>();
        String crtfc_key    = properties.getValue("API_KEY_DART");
        String corp_code    = properties.getValue("삼성전자");
        params.put("bgn_de", "20180117");
        params.put("end_de", "20190117");
        params.put("page_no", "1");
        params.put("page_count", "100");
        params.put("last_reprt_at", "Y");
        params.put("crtfc_key", crtfc_key);
        params.put("corp_code", corp_code);
        try {
            String getDisclosureSearch  =  HttpClientCustom.getHttpGetStringApiData("https://opendart.fss.or.kr/api/list.json", params);
            List<Disclousure> disLit = HttpClientCustom.getHttpGetListObjectApiData("https://opendart.fss.or.kr/api/list.json", params, Disclousure.class);
            System.out.println(getDisclosureSearch);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }

}
