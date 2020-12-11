package com.example.diyview.bean;

public class News {
    private String name;
    private String icon;
    private int age;
    private int tagCode;
    private String tagMsg;


    public News(String name, String icon, int age, int tagCode, String tagMsg) {
        this.name = name;
        this.icon = icon;
        this.age = age;
        this.tagCode = tagCode;
        this.tagMsg = tagMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTagCode() {
        return tagCode;
    }

    public void setTagCode(int tagCode) {
        this.tagCode = tagCode;
    }

    public String getTagMsg() {
        return tagMsg;
    }

    public void setTagMsg(String tagMsg) {
        this.tagMsg = tagMsg;
    }
}
