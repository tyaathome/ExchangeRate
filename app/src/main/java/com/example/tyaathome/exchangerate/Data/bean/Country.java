package com.example.tyaathome.exchangerate.Data.bean;

import android.support.annotation.DrawableRes;

public class Country {
    public @DrawableRes int iconId;
    public String cur = "";
    public String currency = "";
    public float exchangeRate = 1f;
    public float result = 0f;

    public Country(@DrawableRes int iconId, String cur, String currency, float exchangeRate) {
        this.iconId = iconId;
        this.cur = cur;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }
}
