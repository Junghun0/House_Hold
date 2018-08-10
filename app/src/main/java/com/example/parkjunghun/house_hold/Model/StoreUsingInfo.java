package com.example.parkjunghun.house_hold.Model;

import java.util.HashMap;

public class StoreUsingInfo {
    public HashMap<String, Object> usingmap;

    public StoreUsingInfo(){}

    public StoreUsingInfo(HashMap<String, Object> usingmap) {
        this.usingmap = usingmap;
    }

    public HashMap<String, Object> getUsingmap() {
        return usingmap;
    }

    public void setUsingmap(HashMap<String, Object> usingmap) {
        this.usingmap = usingmap;
    }
}
