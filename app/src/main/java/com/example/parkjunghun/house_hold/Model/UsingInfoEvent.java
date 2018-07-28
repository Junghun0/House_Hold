package com.example.parkjunghun.house_hold.Model;

public class UsingInfoEvent {
    private boolean result;
    private UsingInfo usingInfo;

    public UsingInfoEvent(boolean result, UsingInfo usingInfo) {
        this.result = result;
        this.usingInfo = usingInfo;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public UsingInfo getUsingInfo() {
        return usingInfo;
    }

    public void setUsingInfo(UsingInfo usingInfo) {
        this.usingInfo = usingInfo;
    }
}
