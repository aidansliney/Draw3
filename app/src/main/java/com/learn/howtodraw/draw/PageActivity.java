package com.learn.howtodraw.draw;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PageActivity extends AppCompatActivity {

    public int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.learn.howtodraw.draw.R.layout.activity_page);


        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getIntent().getIntExtra("bookSlides", 0));
        final int[] bookSlides = new int[cardImageDrawables.length()];
        for (int i = 0; i < cardImageDrawables.length(); i++)
            bookSlides[i] = cardImageDrawables.getResourceId(i, 0);
        ImageView imageView1 = (ImageView)findViewById(com.learn.howtodraw.draw.R.id.slideImage);
        int lastPic = bookSlides.length-1;
        imageView1.setImageResource(bookSlides[lastPic]);
        View nextImage2 = findViewById(com.learn.howtodraw.draw.R.id.nextImage2);
        nextImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bookSlides.length - 1 == x) {
                }
                else {
                    x++;
                    Log.d("Right", "Right");
                    Log.d(getClass().getName(), "position = " + x);
                    Log.d(getClass().getName(), "length = " + bookSlides.length);
                    ImageView imageView = (ImageView) findViewById(com.learn.howtodraw.draw.R.id.slideImage);
                    // ((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
                    imageView.setImageResource(bookSlides[x]);

                }
            }
        });

        View previousImage2 = findViewById(com.learn.howtodraw.draw.R.id.previousImage2);
        previousImage2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (x != 0) {
                    x--;
                    Log.d("left", "left");
                    Log.d(getClass().getName(), "position = " + x);
                    Log.d(getClass().getName(), "length = " + bookSlides.length);
                    ImageView imageView = (ImageView) findViewById(com.learn.howtodraw.draw.R.id.slideImage);
                    // ((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
                    imageView.setImageResource(bookSlides[x]);
                }
            }
        });

        ImageView imageView = (ImageView) findViewById(com.learn.howtodraw.draw.R.id.slideImage);
        imageView.setOnTouchListener(new OnSwipeTouchListener(PageActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(PageActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(PageActivity.this, "right", Toast.LENGTH_SHORT).show();
                if (x != 0) {
                    x--;
                    Log.d("left", "left");
                    Log.d(getClass().getName(), "position = " + x);
                    Log.d(getClass().getName(), "length = " + bookSlides.length);
                    ImageView imageView = (ImageView) findViewById(com.learn.howtodraw.draw.R.id.slideImage);
                    // ((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
                    imageView.setImageResource(bookSlides[x]);

                }
            }
            public void onSwipeLeft() {
                Toast.makeText(PageActivity.this, "left", Toast.LENGTH_SHORT).show();
                if (bookSlides.length - 1 == x) {
                }
                else {
                    x++;
                    Log.d("Right", "Right");
                    Log.d(getClass().getName(), "position = " + x);
                    Log.d(getClass().getName(), "length = " + bookSlides.length);
                    ImageView imageView = (ImageView) findViewById(com.learn.howtodraw.draw.R.id.slideImage);
                    // ((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
                    imageView.setImageResource(bookSlides[x]);

                }
            }
            public void onSwipeBottom() {
                Toast.makeText(PageActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });


    }
}
