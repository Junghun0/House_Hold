package com.example.parkjunghun.house_hold.Model;

import java.util.ArrayList;

public class StoreInfoEvent {
    private ArrayList<StoreParseModel> parseModelArrayList;
    private boolean result;
    private int roofsize;

    public StoreInfoEvent(ArrayList<StoreParseModel> parseModelArrayList, boolean result, int roofsize) {
        this.parseModelArrayList = parseModelArrayList;
        this.result = result;
        this.roofsize = roofsize;
    }

    public ArrayList<StoreParseModel> getParseModelArrayList() {
        return parseModelArrayList;
    }

    public void setParseModelArrayList(ArrayList<StoreParseModel> parseModelArrayList) {
        this.parseModelArrayList = parseModelArrayList;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getRoofsize() {
        return roofsize;
    }

    public void setRoofsize(int roofsize) {
        this.roofsize = roofsize;
    }
}
