package com.coursework.main;

class FieldElement { //cell class
    private int channelRed, channelGreen, channelBlue; //cell color channels
    private boolean alive; //cell alive value

    boolean isAlive() { //get cell alive value
        return alive;
    }

    int getChannelBlue() { //get cell blue channel value
        return channelBlue;
    }

    int getChannelGreen() { //get cell green channel value
        return channelGreen;
    }

    int getChannelRed() { //get cell red channel value
        return channelRed;
    }
//
//    void setAlive() { //default call to set cell alive value
//        this.alive = false;
//    }
//
//    void setChannelBlue() { //default call to set cell blue channel value
//        this.channelBlue = 0;
//    }
//
//    void setChannelGreen() { //default call to set cell green channel value
//        this.channelGreen = 0;
//    }
//
//    void setChannelRed() { //default call to set cell red channel value
//        this.channelRed = 0;
//    }

    void setAlive(boolean alive) { //set cell alive value
        this.alive = alive;
    }

    void setChannelBlue(int channelBlue) { //set cell blue channel value
        this.channelBlue = channelBlue;
    }

    void setChannelGreen(int channelGreen) { //set cell green channel value
        this.channelGreen = channelGreen;
    }

    void setChannelRed(int channelRed) { //set cell red channel value
        this.channelRed = channelRed;
    }

//    void changeAlive() { //change cell alive value
//        this.alive^=alive;
//    }
}
