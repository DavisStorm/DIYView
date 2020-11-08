package com.example.diyview.bean;

public class Dot {
    public float X;
    public float Y;
    public float speed;
    public float radius;//点的半径
    public float maxOffset;//最大移动距离
    public float offset;//当前移动距离
    public double angle;//粒子角度
    public int alpha;

    public Dot(float x, float y,float offset, float speed, float radius,float maxOffset,double angle) {
        X = x;
        Y = y;
        this.speed = speed;
        this.radius = radius;
        this.maxOffset = maxOffset;
        this.offset = offset;
        this.angle = angle;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getMaxOffset() {
        return maxOffset;
    }

    public void setMaxOffset(float maxOffset) {
        this.maxOffset = maxOffset;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
