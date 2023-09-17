package com.example.ca2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import java.util.ResourceBundle;

public class Rocket{
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private float x, y, angle, xAccel, yAccel;
    private final float height = 150;
    private float rocketWidth = 200;
    private final float speed = 0.5f;
    public Rocket(){
        this.y = screenHeight - 200;
        this.x = screenWidth/2;
        this.xAccel = 0;
        this.yAccel = 0;
        this.angle = 0;
    }
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.WHITE);
        canvas.save();
        canvas.drawLine(x - rocketWidth * (float)Math.cos(Math.toRadians(this.angle )), y - rocketWidth * (float)Math.sin(Math.toRadians(this.angle)), x + rocketWidth * (float)Math.cos(Math.toRadians(this.angle )), y + rocketWidth * (float)Math.sin(Math.toRadians(this.angle)), paint);
    }
    public void update(float xAccel, float yAccel) {

        float newXAccel = (-xAccel * 10) - this.xAccel;
        float tempx = this.x + (newXAccel);
        if (tempx <= (screenWidth - 100) && tempx > 0 + 100 / 2) {
            this.x = tempx;
            this.xAccel = newXAccel;
            this.yAccel = -yAccel * 1;
        }
    }

    public void updateAngle(float xAccel, float yAccel) {
        this.xAccel = (float) (-xAccel * 0.5);

        this.angle += this.xAccel;
    }

    public boolean collisionDetection(Ball ball){
        float lineX1=x - rocketWidth * (float)Math.cos(Math.toRadians(this.angle ));
        float lineY1=y - rocketWidth * (float)Math.sin(Math.toRadians(this.angle));
        float lineX2=x + rocketWidth * (float)Math.cos(Math.toRadians(this.angle ));
        float lineY2=y + rocketWidth * (float)Math.sin(Math.toRadians(this.angle));
        float distance = Math.abs((lineY2 - lineY1) * ball.getX() - (lineX2 - lineX1) * ball.getY()
                + lineX2 * lineY1 - lineY2 * lineX1) / (float) Math.sqrt((lineY2 - lineY1)
                * (lineY2 - lineY1) + (lineX2 - lineX1) * (lineX2 - lineX1));
        float distX = Math.abs(ball.getX() - x);
        float distY = Math.abs(ball.getY() - y);
        float distanceFromCenter = (float) Math.sqrt(distY * distY + distX * distX);
        if (distance <= ball.getRadius() && distanceFromCenter <= (float)(Math.sqrt(ball.getRadius() * ball.getRadius() + rocketWidth * rocketWidth))) {
            this.updateBallSpeed(ball);
            return true; // collision detected
        } else {
            return false; // no collision
        }
    }

    private void updateBallSpeed(Ball ball){
        float totalSpeed = (float)Math.sqrt(ball.getSpeedX() * ball.getSpeedX() + ball.getSpeedY() *ball.getSpeedY());
        float ballAngle = (float) Math.toDegrees(Math.atan(ball.getSpeedY() / ball.getSpeedX()));
        float newDegree = 180 - 2 * angle - ballAngle;
        ball.setSpeedX((float) (totalSpeed*Math.cos(Math.toRadians(newDegree))));
        ball.setSpeedY((float) (totalSpeed*Math.sin(Math.toRadians(newDegree))));
    }
    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
    public float getHeight() {
        return height;
    }

    public float getAngle() {
        return angle;
    }
}