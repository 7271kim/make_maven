package com.seokjin.makemaven;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.kim.library.JsoupCustom;


public class TaskGetKospoTop100 implements Runnable {
    private int number;
    private List<String> shared;
    

    public TaskGetKospoTop100(int number, List<String> shared) {
        this.number = number;
        this.shared = shared;
    }

    @Override
    public void run() {
        
        Document doc = JsoupCustom.getGetDocumentFromURL("https://finance.naver.com/sise/entryJongmok.nhn?&page="+number);
        Element table           = doc.getElementsByTag("table").get(0);
        Elements companyLow      = table.select("tbody tr");
        for( Element item : companyLow ) {
            String companyName= "",companyCode= "";
            if(!item.select(".ctg a").isEmpty()) {
                Element companyElement = item.select(".ctg a").first();
                companyName  = companyElement.text();
                System.out.println(companyName);
                companyCode  = companyElement.attr("href").split("code=")[1];
            }
            if(companyCode!="") {
                shared.add(companyName);
            }
        }
    }
    
}
