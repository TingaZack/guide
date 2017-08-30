package com.zack.tinga.satvguide.classes;

import java.util.ArrayList;

/**
 * Created by admin on 2017/08/14.
 */

public class TVClass {

    String name, time, img;

    public TVClass() {
    }

    public TVClass(String name, String time, String img) {
        this.name = name;
        this.time = time;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
