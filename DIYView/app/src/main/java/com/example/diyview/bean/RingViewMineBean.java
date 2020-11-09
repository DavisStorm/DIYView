package com.example.diyview.bean;

public class RingViewMineBean {
    public String name;//属性名
    public int percent;//占比，总100
    public int typeColor;//颜色，类型不同颜色不同
    public float length;//所占长度
    public float[] centerLocation;//中点坐标

    public RingViewMineBean(String name, int percent, int typeColor) {
        this.name = name;
        this.percent = percent;
        this.typeColor = typeColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getTypeColor() {
        return typeColor;
    }
    public void setTypeColor(int typeColor) {
        this.typeColor = typeColor;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setCenterLocation(float[] centerLocation) {
        this.centerLocation = centerLocation;
    }

    public float getLength() {
        return length;
    }

    public float[] getCenterLocation() {
        return centerLocation;
    }
}
