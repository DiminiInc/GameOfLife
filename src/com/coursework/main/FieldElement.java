package com.coursework.main;

/**
 * Created by Yaskovich Dmitry on 16/11/2016.
 */
public class FieldElement {
    private int channelRed, channelGreen, channelBlue;
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public int getChannelBlue() {
        return channelBlue;
    }

    public int getChannelGreen() {
        return channelGreen;
    }

    public int getChannelRed() {
        return channelRed;
    }

    public void setAlive() {
        this.alive = false;
    }

    public void setChannelBlue() {
        this.channelBlue = 0;
    }

    public void setChannelGreen() {
        this.channelGreen = 0;
    }

    public void setChannelRed() {
        this.channelRed = 0;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setChannelBlue(int channelBlue) {
        this.channelBlue = channelBlue;
    }

    public void setChannelGreen(int channelGreen) {
        this.channelGreen = channelGreen;
    }

    public void setChannelRed(int channelRed) {
        this.channelRed = channelRed;
    }

    public void changeAlive()
    {
        this.alive^=alive;
    }
}
