package com.seokjin.makemaven;

import java.util.List;

import com.seokjin.model.Disclousure;

public class Main {

    public static void main(String[] args) {
        
        // API서버에서 고유 번호 Zip파일 받아오기. 좀 오래걸램.
        //String dartUrl = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key="+GetProperties.getSecureValue("API_KEY_DART");
        //HttpClientCustom.getHttpGetStremApiData(dartUrl,"C:\\Users\\Jin\\Desktop\\zzz\\corpCode.zip");
        
        //String getDisclosureSearch  =  HttpClientCustom.getHttpGetStringApiData("https://opendart.fss.or.kr/api/list.json", "20180117", "20200117");
        
        List<Disclousure> disLit = HttpClientCustom.getHttpGetListObjectApiData("https://opendart.fss.or.kr/api/list.json", "20180117", "20200117", Disclousure.class);
        
        for (int index = 0; index < disLit.size(); index++) {
            
            Disclousure temp = disLit.get(index);
            System.out.println(temp.getCorp_name());
        }
    }

}
