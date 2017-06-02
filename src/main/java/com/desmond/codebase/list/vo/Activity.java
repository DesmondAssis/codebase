package com.desmond.codebase.list.vo;

/**
 * Created by Li.Xiaochuan on 15/11/23.
 */
public class Activity {
    private int id;
    private Shop shop;

    public Activity(int id, Shop shop) {
        this.id = id;
        this.shop = shop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
