package com.example.ca2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ResourceBundle;

public class Ball{
    private float x;
    private float y;
    private float speedX;
    private float speedY;
    private float radius;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public Ball(){
        x = 300;
        y = 300;
        radius=50;

        speedX = 15;
        speedY = 10;
    }
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, radius, paint);
    }
    public void update(){

        if (x - radius < 0 && y - radius < 0) {
            x = screenWidth / 2;
            y = screenHeight / 2;
        } else {
            x += speedX;
            y += speedY;
            if ((x > screenWidth - radius) || (x - radius < 0)) {
                speedX = speedX * -1;
            }
            if ((y > screenHeight - radius) || (y - radius < 0)) {
                speedY = speedY * -1;
            }
        }
    }
    public boolean collisionDetection(Rocket rocket){
        // Check if the ball collides with the rocket
        float rocketLeft = rocket.getX() - 50;
        float rocketRight = rocket.getX() + 50;
        float ballTop = y - radius;
        float ballBottom = y + radius;

        return x >= rocketLeft && x <= rocketRight && y >= ballTop && y <= ballBottom;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }
}