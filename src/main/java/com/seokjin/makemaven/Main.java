package com.seokjin.makemaven;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<String> temp = new ArrayList<>();
        for (int index = 1; index <= 20; index++) {
            TaskGetKospoTop100 task = new TaskGetKospoTop100(index, temp);
            executorService.submit(task);
        }
        
        executorService.shutdown();
       
    }

}
