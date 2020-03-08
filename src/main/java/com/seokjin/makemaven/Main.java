package com.seokjin.makemaven;

import java.util.List;

import com.seokjin.model.Disclousure;

public class Main {

    public static void main(String[] args) {
        
        /*
         1. API서버에서 고유 번호 Zip파일 받아서 원하는 곳에 zip 만들기. => 서버 다운로드 조금 오래걸림
         String dartUrl = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key="+GetProperties.getSecureValue("API_KEY_DART");
         HttpClientCustom.getHttpGetStremApiData(dartUrl,"C:\\Users\\Jin\\Desktop\\zzz\\corpCode.zip");
         
         */
        
        /*
         2. String 으로 API 서버에서 데이터 받아오기
         String getDisclosureSearch  =  HttpClientCustom.getHttpGetStringDartApiData("https://opendart.fss.or.kr/api/list.json", "20180117", "20200117");
         
         */
        
        /*
        3. 원하는 객체로  API 서버에서 데이터 받아오기
        List<Disclousure> disLit = HttpClientCustom.getHttpGetListObjectDartApiData("https://opendart.fss.or.kr/api/list.json", "20180117", "20200117", Disclousure.class);
         for (int index = 0; index < disLit.size(); index++) {
            
            Disclousure temp = disLit.get(index);
            System.out.println(temp.getCorp_name());
        }
        */
        
        
        Thread thread1 = new Thread( () -> {
            List<Disclousure> disLit = HttpClientCustom.getHttpGetListObjectDartApiData("https://opendart.fss.or.kr/api/list.json", "20180117", "20200117","삼성전자", Disclousure.class);
            for (int index = 0; index < disLit.size(); index++) {
               Disclousure temp = disLit.get(index);
               System.out.println(temp.getCorp_name());
               System.out.println(temp.getReport_nm());
           }
        });
        
        Thread thread2 = new Thread( () -> {
            List<Disclousure> disLit = HttpClientCustom.getHttpGetListObjectDartApiData("https://opendart.fss.or.kr/api/list.json", "20180117", "20200117","웹바이오텍", Disclousure.class);
            for (int index = 0; index < disLit.size(); index++) {
               Disclousure temp = disLit.get(index);
               System.out.println(temp.getCorp_name());
               System.out.println(temp.getReport_nm());
           }
        });
       
        thread1.start();
        thread2.start();
    }

}
