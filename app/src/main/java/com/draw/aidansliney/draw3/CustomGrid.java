package com.draw.aidansliney.draw3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private final String[] web;
    private final String[] bookPageIds;
    private final String[] web2;
    private final int[] Imageid;

    public CustomGrid(Context c, String[] web, String[] web2, int[] Imageid, String[] bookPageIds) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
        this.web2 = web2;
        this.bookPageIds = bookPageIds;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        return bookPageIds[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            TextView textView2 = (TextView) grid.findViewById(R.id.grid_text2);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(web[position]);
            textView2.setText(web2[position]);
          //  textView3.setId(web3[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}