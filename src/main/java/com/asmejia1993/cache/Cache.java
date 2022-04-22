package com.asmejia1993.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    private Map<String, String> cache = new HashMap<>();
    private List<String> lessRecent;
    private int size;

    public Cache(int size) {
        this.lessRecent = new ArrayList<>();
        this.cache = new HashMap<>();
        this.size = size;
    }
    
    public String getValue(String key) {
        if (!this.cache.containsKey(key)) {
            return "";
        }
        
        if (key.equalsIgnoreCase(this.lessRecent.get(0)) && this.lessRecent.size() > 1) {
            this.lessRecent.remove(1);
        }
        
        if (!key.equalsIgnoreCase(this.lessRecent.get(0)) && !this.lessRecent.isEmpty()) {
            this.lessRecent.remove(0);
        }


        return this.cache.get(key);
    }

    public void addNewKey(String key, String value) {
        this.lessRecent.add(key);  
        if (lessRecent.size() > this.size) {
            this.cache.remove(this.lessRecent.get(0));
            lessRecent.remove(0);
        }
        this.cache.put(key, value);
    }

    public void printCache() {
        this.cache.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));    
    }

    public List<String> printLessUsed() {
        System.out.println(this.lessRecent);
        return this.lessRecent;
    }

    public int getSizeCache() {
        return this.cache.size();
    }
}
