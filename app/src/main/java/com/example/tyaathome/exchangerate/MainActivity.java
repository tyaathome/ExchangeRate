package com.example.tyaathome.exchangerate;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.tyaathome.exchangerate.Data.Adapter.AdapterExchangeRate;
import com.example.tyaathome.exchangerate.Data.bean.Country;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private AdapterExchangeRate adapter;
    private List<Country> countryList = new ArrayList<>();
    private View previousOnFocusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCountry();
        listView = (ListView) findViewById(R.id.listview);
        adapter = new AdapterExchangeRate(this, countryList);
        listView.setAdapter(adapter);
    }

    private void initCountry() {
        String[] curList = getResources().getStringArray(R.array.cur);
        String[] currencyList = getResources().getStringArray(R.array.currency);
        TypedArray countryIconId = getResources().obtainTypedArray(R.array.icon_id);
        String[] exchangeRateList = getResources().getStringArray(R.array.exchange_rate);
        for(int i = 0; i < curList.length; i++) {
            float exchangeRate = Float.parseFloat(exchangeRateList[i]);
            Country bean = new Country(countryIconId.getResourceId(i, -1), curList[i], currencyList[i], exchangeRate);
            countryList.add(bean);
        }
    }
}
