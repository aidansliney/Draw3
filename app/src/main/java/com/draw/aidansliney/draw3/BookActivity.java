package com.draw.aidansliney.draw3;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
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

    GridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Change the title on the main screen
        setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       //Create floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is where we will share the app</p>"));
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        //set all content but grid
        TextView textView = (TextView)findViewById(R.id.primary);
        TextView textView2 = (TextView)findViewById(R.id.secondary);
        ImageView imageView = (ImageView)findViewById(R.id.background);
        textView.setText(getString(getIntent().getIntExtra("bookCoverH1Id", 0)));
        textView2.setText(getString(getIntent().getIntExtra("bookCoverH2Id", 0)));
        imageView.setImageResource(getIntent().getIntExtra("bookCoverImageId", 0));

        //send content in the grid
        final String[] cardText1 = getResources().getStringArray(getIntent().getIntExtra("cardText1Id", 0));
        final String[] cardText2 = getResources().getStringArray(getIntent().getIntExtra("cardText2Id", 0));
        final String[] bookPageIds = getResources().getStringArray(getIntent().getIntExtra("bookPageIds", 0));//get IDs
        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getIntent().getIntExtra("cardImageId", 0));
        final int[] cardImage = new int[cardImageDrawables.length()];
        for (int i = 0; i < cardImageDrawables.length(); i++)
            cardImage[i] = cardImageDrawables.getResourceId(i, 0);
        final CustomGrid adapter = new CustomGrid(BookActivity.this, cardText1, cardText2, cardImage, bookPageIds);
        grid = (GridView) findViewById(R.id.grid);

        //set the grid
        ExpandableGridView gridView = (ExpandableGridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        gridView.setExpanded(true);
        gridView.setFocusable(false);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pageId = (String) adapter.getItem(position);

                int slidesID = getResources().getIdentifier(pageId + "Slides", "array", getClass().getPackage().getName());
                Toast.makeText(BookActivity.this, "You Clicked at " + cardText1[+position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookActivity.this,PageActivity.class);
                Log.d("hellooooooooooooooo" + slidesID, "" );
                intent.putExtra("bookSlides", slidesID);
                startActivity(intent);

            }
        });


    }
}
