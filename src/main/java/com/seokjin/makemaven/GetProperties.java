package com.seokjin.makemaven;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;


public class GetProperties {
    private static final Properties PROPERTIES_COMMON = new Properties();
    private static final Properties PROPERTIES_SECURE = new Properties();
    private static final String COMMON_CONFIG="config/config.properties";
    private static final String SECURES_CONFIG="config/secure.properties";
    private static final ClassLoader DEFULT_CLASSLOADER;
    static {
        // Resorce 하위 propertiese들 읽기
        DEFULT_CLASSLOADER = ClassLoader.getSystemClassLoader();
       try( InputStreamReader inputStreamCommon = new InputStreamReader( DEFULT_CLASSLOADER.getSystemResourceAsStream(COMMON_CONFIG) , Charset.forName("UTF-8"));
               InputStreamReader inputStreamSecure = new InputStreamReader( DEFULT_CLASSLOADER.getSystemResourceAsStream(SECURES_CONFIG) , Charset.forName("UTF-8"));){
           
           PROPERTIES_COMMON.load ( inputStreamCommon );
           PROPERTIES_SECURE.load( inputStreamSecure );
           
       } catch (Exception e) {
        System.out.println(e.getMessage());
       }
    }
    
    public static Properties getCommonProperties () {
        return PROPERTIES_COMMON;
    }
    
    public static Properties getSecureProperties () {
        return PROPERTIES_SECURE;
    }
    
    public static String getSecureValue ( String key ) {
        return PROPERTIES_SECURE.getProperty( key );
    }
}
