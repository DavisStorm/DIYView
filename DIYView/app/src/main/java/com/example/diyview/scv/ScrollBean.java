package com.example.diyview.scv;

public class ScrollBean {
    private int color;
    private String name;
    private String position;

    public ScrollBean(int color, String name, String position) {
        this.color = color;
        this.name = name;
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}
