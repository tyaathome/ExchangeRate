package com.example.tyaathome.exchangerate.Data.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tyaathome.exchangerate.Data.bean.Country;
import com.example.tyaathome.exchangerate.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdapterExchangeRate extends BaseAdapter{

    private Context context;
    private List<Country> listdata = new ArrayList<>();

    public AdapterExchangeRate(Context context, List<Country> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int position) {
        return listdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_exchange_rate, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.tvCur = (TextView) convertView.findViewById(R.id.cur);
            holder.etValue = (EditText) convertView.findViewById(R.id.value);
            holder.tvCurrency = (TextView) convertView.findViewById(R.id.currency);
            holder.layout = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.etValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        for(int i = 0; i < listdata.size(); i++) {
                            //bean.exchangeRate = Double.parseDouble(holder.etValue.getText().toString());
                            if(!TextUtils.isEmpty(holder.etValue.getText().toString())) {
                                float currentValue = Float.parseFloat(holder.etValue.getText().toString());
                                Country bean = listdata.get(i);
                                if (i == position) {
                                    bean.result = Float.parseFloat(holder.etValue.getText().toString());
                                } else {
                                    float rate = bean.exchangeRate / listdata.get(position).exchangeRate;
                                    float result = rate * currentValue;
                                    bean.result = new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                                }
                            } else {
                                for(Country bean : listdata) {
                                    bean.result = 0;
                                }
                            }
                        }
                        notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        Country bean = listdata.get(position);
        holder.icon.setImageResource(bean.iconId);
        holder.tvCur.setText(bean.cur);
        holder.tvCurrency.setText(bean.currency);
        holder.etValue.setText(String.valueOf(bean.result));

        return convertView;
    }

    private static class ViewHolder {
        public ImageView icon;
        public TextView tvCur;
        public EditText etValue;
        public TextView tvCurrency;
        public View layout;
    }
}
