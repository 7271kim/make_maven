package com.seokjin.makemaven;

public class Main {

    public static void main(String[] args) {
        String dartUrl = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key="+GetProperties.getSecureValue("API_KEY_DART");
        // API서버에서 고유 번호 Zip파일 받아오기. 좀 오래걸램. 
        HttpClientCustom.getHttpGetStremApiData(dartUrl,"C:\\Users\\Jin\\Desktop\\zzz\\corpCode.zip");
    }

}
