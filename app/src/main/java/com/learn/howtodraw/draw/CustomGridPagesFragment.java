package com.learn.howtodraw.draw;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridPagesFragment extends BaseAdapter {
    private Context mContext;
    private final String[] web;
    private final String[] bookPageIds;
    private final String[] bookNames;
    private final String[] bookLevels;
    private final int[] Imageid;
    private final int[] tickIcon;
    Resources res;

    public CustomGridPagesFragment(Context c, String[] web, int[] Imageid, String[] bookPageIds, int[] tickIcon, String[] bookNames, String[] bookLevels) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
        this.bookPageIds = bookPageIds;
        this.bookNames = bookNames;
        this.bookLevels = bookLevels;
        this.tickIcon = tickIcon;

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

            TextView tv= (TextView) grid.findViewById(R.id.ticklock2);
            tv.setText(tickIcon[position]);

            TextView tv2= (TextView) grid.findViewById(R.id.bookName);
            tv2.setText(bookNames[position]);

            TextView tv3= (TextView) grid.findViewById(R.id.bookLevel);
            tv3.setText(bookLevels[position]);

            if (bookLevels[position].equals("Level 1")){
                tv3.setBackgroundResource(R.color.levelOne);
            }

            if (bookLevels[position].equals("Level 3")){
                tv3.setBackgroundResource(R.color.levelThree);
            }


            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(web[position]);


          //  textView3.setId(web3[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = convertView;
        }

        return grid;
    }
}