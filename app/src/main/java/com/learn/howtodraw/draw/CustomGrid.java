package com.learn.howtodraw.draw;

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
    private final int[] Imageid;

    public CustomGrid(Context c, String[] web, int[] Imageid, String[] bookPageIds) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
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
            grid = inflater.inflate(com.learn.howtodraw.draw.R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(com.learn.howtodraw.draw.R.id.grid_text);

            ImageView imageView = (ImageView) grid.findViewById(com.learn.howtodraw.draw.R.id.grid_image);
            textView.setText(web[position]);

          //  textView3.setId(web3[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = convertView;
        }

        return grid;
    }
}