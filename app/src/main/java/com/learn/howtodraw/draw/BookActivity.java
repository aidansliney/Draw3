package com.learn.howtodraw.draw;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends AppCompatActivity {


    private void showToast(int duration, String message) {
        final Toast toast = Toast.makeText(getBaseContext(),
                message,
                Toast.LENGTH_SHORT);
        toast.show();
        new CountDownTimer(duration, 500) {
            public void onTick(long millisUntilFinished) {
                toast.show();
            }
            public void onFinish() {
                toast.cancel();
            }

        }.start();
    }


    GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



       // Log.d("subscribed2?" + mSubscribed, "" + mSubscribed);

        // Change the title on the main screen
        setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(com.learn.howtodraw.draw.R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(com.learn.howtodraw.draw.R.id.toolbar);
        setSupportActionBar(toolbar);

       //Create floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(com.learn.howtodraw.draw.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is where we will share the app</p>"));
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        //set the top of the page (not the grid)
        TextView textView = (TextView)findViewById(com.learn.howtodraw.draw.R.id.primary);
        TextView textView2 = (TextView)findViewById(com.learn.howtodraw.draw.R.id.secondary);
        ImageView imageView = (ImageView)findViewById(com.learn.howtodraw.draw.R.id.background);
        textView.setText(getString(getIntent().getIntExtra("bookCoverH1Id", 0)));
        textView2.setText(getString(getIntent().getIntExtra("bookCoverH2Id", 0)));
        imageView.setImageResource(getIntent().getIntExtra("bookCoverImageInsideId", 0));

        //send content in the grid
        final String[] cardText1 = getResources().getStringArray(getIntent().getIntExtra("cardText1Id", 0));
        final String[] bookPageIds = getResources().getStringArray(getIntent().getIntExtra("bookPageIds", 0));//get IDs
        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getIntent().getIntExtra("cardImageId", 0));
        final int[] cardImage = new int[cardImageDrawables.length()];
        for (int i = 0; i < cardImageDrawables.length(); i++)
            cardImage[i] = cardImageDrawables.getResourceId(i, 0);
        final CustomGrid adapter = new CustomGrid(BookActivity.this, cardText1, cardImage, bookPageIds);
        grid = (GridView) findViewById(com.learn.howtodraw.draw.R.id.grid);

        //set the grid
        ExpandableGridView gridView = (ExpandableGridView) findViewById(com.learn.howtodraw.draw.R.id.grid);
        grid.setAdapter(adapter);
        gridView.setExpanded(true);
        gridView.setFocusable(false);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pageId = (String) adapter.getItem(position);

                int slidesID = getResources().getIdentifier(pageId + "Slides", "array", getClass().getPackage().getName());
               // showToast(20000, cardText1[+position] );
               // Toast.makeText(BookActivity.this, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BookActivity.this,PageActivity.class);
                Log.d("hello" + slidesID, "" );
                intent.putExtra("bookSlides", slidesID);
                startActivity(intent);
            }
        });


    }
}
