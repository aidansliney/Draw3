package com.learn.howtodraw.draw;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.View;

/**
 * Created by aidansliney on 23/08/2016.
 */
public class LinkBooks {

    // a method to setup the right content for = book thumbnails
    public static void linkBook(int layout, final int bookContent, View rootView, final boolean advert, final Activity activity) {
        View Layout = rootView.findViewById(layout);
        Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    TypedArray bookBuild = activity.getResources().obtainTypedArray(bookContent);
                    final int[] bookCollected = new int[bookBuild.length()];
                    for (int i = 0; i < bookBuild.length(); i++)
                        bookCollected[i] = bookBuild.getResourceId(i, 0);
                    bookBuild.recycle();
                    Intent intent = new Intent(activity, BookActivity.class);
                    intent.putExtra("bookCoverH1Id", bookCollected[0]);
                    intent.putExtra("bookCoverH2Id", bookCollected[1]);
                    intent.putExtra("bookCoverImageId", bookCollected[2]);
                    intent.putExtra("cardText1Id", bookCollected[3]);
                    intent.putExtra("cardImageId", bookCollected[4]);
                    intent.putExtra("bookPageIds", bookCollected[5]);
                    intent.putExtra("bookCoverImageInsideId", bookCollected[6]);
                    activity.startActivity(intent);
            }
        });
    }
}
