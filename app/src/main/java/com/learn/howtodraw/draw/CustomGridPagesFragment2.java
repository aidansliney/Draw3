package com.learn.howtodraw.draw;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.learn.howtodraw.draw.Constants.*;

public class CustomGridPagesFragment2 extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;
    private final String[] text;
    private final String[] bookPageIds;
    private final String[] bookNames;
    private final int[] Imageid;


    public CustomGridPagesFragment2(Activity c, String[] text, int[] Imageid, String[] bookPageIds,  String[] bookNames) {
         mContext = c;
        mActivity = c;
        this.Imageid = Imageid;
        this.text = text;
        this.bookPageIds = bookPageIds;
        this.bookNames = bookNames;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return text.length;  // - 1 removes the last book
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
            grid = inflater.inflate(R.layout.grid_of_books, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            TextView tv2= (TextView) grid.findViewById(R.id.bookName);
            tv2.setText(bookNames[position]);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(text[position]);
            imageView.setImageResource(Imageid[position]);
            // plus One to skip the tutorial pages
            LinkBooks.linkBook(R.id.grid_image,SKU_BOOK_ARRAY_ARRAY[position+1], grid, false, mActivity);

        } else {
            grid = convertView;
        }

        return grid;
    }
}