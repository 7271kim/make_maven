package com.seokjin.makemaven;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean isTrue = StringUtils.isNotBlank("aaaaaaaa");
        String name = "config/config.properties"; 
        // Resorce 하위 propertiese들 읽기
        InputStream inputStream = ClassLoader.getSystemClassLoader().getSystemResourceAsStream(name);
        Properties properties = new Properties();
        properties.load(inputStream);
        System.out.println(properties);
    }

}
