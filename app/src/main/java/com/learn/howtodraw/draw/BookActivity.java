package com.learn.howtodraw.draw;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class BookActivity extends MainActivity {

    public Boolean bookPurchasedLock;
    public String bookName;
    public Boolean isUnlocked(){

        // Calling Application class (see application tag in AndroidManifest.xml)
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        //check what page we are on and if it is purchased
        if (bookName.equals("book1"))
            bookPurchasedLock = globalVariable.getbook1bought();
          //  bookPurchasedLock = mPurchasedBook1;
        if (bookName.equals("book2"))
            bookPurchasedLock = globalVariable.getbook2bought();
        if (bookName.equals("book3"))
            bookPurchasedLock = globalVariable.getbook3bought();
        if (bookName.equals("book4"))
            bookPurchasedLock = globalVariable.getbook4bought();
        if (bookName.equals("book5"))
            bookPurchasedLock = globalVariable.getbook5bought();
        if (bookPurchasedLock)
            return true;
        else
            return false;
    }



    public void buildTheGrid(){


        //send content in the grid
        final String[] cardText1 = getResources().getStringArray(getIntent().getIntExtra("cardText1Id", 0));
        final String[] bookPageIds = getResources().getStringArray(getIntent().getIntExtra("bookPageIds", 0));//get IDs
        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getIntent().getIntExtra("cardImageId", 0));
        final int[] cardImage = new int[cardImageDrawables.length()];
        final int[] tickIcon = new int[cardImageDrawables.length()];
        for (int i = 0; i < cardImageDrawables.length(); i++) {
            cardImage[i] = cardImageDrawables.getResourceId(i, 0);
            if(isUnlocked()) // this does not work
                tickIcon[i] = R.string.fa_check;
            else
                tickIcon[i] = R.string.fa_lock;
        }

        final CustomGrid adapter = new CustomGrid(BookActivity.this, cardText1, cardImage, bookPageIds, tickIcon);

        //set the grid
        ExpandableGridView gridView = (ExpandableGridView) findViewById(R.id.grid);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
        gridView.setFocusable(false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // see if the user should have access to the content
                if (isUnlocked())  { // this  works
                    String pageId = (String) adapter.getItem(position);
                    int slidesID = getResources().getIdentifier(pageId + "Slides", "array", getClass().getPackage().getName());
                    // showToast(20000, cardText1[+position] );
                    // Toast.makeText(BookActivity.this, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(BookActivity.this, PageActivity.class);
                    Log.d("hello" + slidesID, "");
                    intent.putExtra("bookSlides", slidesID);
                    startActivity(intent);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("bookThumb", getIntent().getIntExtra("bookCoverImageInsideId", 0));
                    bundle.putInt("bookName", getIntent().getIntExtra("bookName", 0));

                    FragmentManager fm = getSupportFragmentManager();
                    MyDialogFragment dialogFragment = new MyDialogFragment();
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(fm, getString(R.string.menu_subscribe));
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        TextView textView = (TextView) findViewById(R.id.primary);
        TextView textView2 = (TextView) findViewById(R.id.secondary);
        ImageView imageView = (ImageView) findViewById(R.id.background);
        textView.setText(getString(getIntent().getIntExtra("bookCoverH1Id", 0)));
        textView2.setText(getString(getIntent().getIntExtra("bookCoverH2Id", 0)));
        imageView.setImageResource(getIntent().getIntExtra("bookCoverImageInsideId", 0));
        bookName = getString(getIntent().getIntExtra("bookName", 0));

        if (isUnlocked()){
            Log.d("PING", "I am afraid this page is locked");
        }
        else
            Log.d("PING", "I am afraid this page is  not locked");
        buildTheGrid();
    }
}
