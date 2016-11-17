package com.learn.howtodraw.draw;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.learn.howtodraw.draw.Constants.*;

public class BookActivity extends MainActivity  {

    public Boolean bookPurchasedLock;
    public String bookName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Change the title on the main screen
        setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(com.learn.howtodraw.draw.R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().clear();

        setSupportActionBar(toolbar);

        final String bookLevel = getResources().getString(getIntent().getIntExtra("booksLevel", 0)); // get book Levela
        //Set the color of the top bar
        if (bookLevel.equals("Level 1")) {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.collapse_commonview_header);
            collapsingToolbarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.levelOne));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.levelOne));
        }

        if (bookLevel.equals("Level 2")) {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.collapse_commonview_header);
            collapsingToolbarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.levelTwo));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.levelTwo));
        }
        if (bookLevel.equals("Level 3")) {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.collapse_commonview_header);
            collapsingToolbarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.levelThree));
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.levelThree));
        }

        //Create floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(com.learn.howtodraw.draw.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share));
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });

        //set the top of the page (not the grid)
        ImageView imageView = (ImageView) findViewById(R.id.background);
        getSupportActionBar().setTitle(getString(getIntent().getIntExtra("bookCoverH1Id", 0)));
        imageView.setImageResource(getIntent().getIntExtra("bookCoverImageInsideId", 0));
        bookName = getString(getIntent().getIntExtra("bookName", 0));

        //now build the grid
        buildTheGrid();

        if (isUnlocked())
            toast("You own this book");
    }

    @Override
    public void onResume(){
        super.onResume();
        buildTheGrid();
    }

    public void buildTheGrid(){
        //send content in the grid
        final String[] cardText1 = getResources().getStringArray(getIntent().getIntExtra("cardText1Id", 0));
        final String[] bookPageIds = getResources().getStringArray(getIntent().getIntExtra("bookPageIds", 0));//get IDs

        final String bookName = getResources().getString(getIntent().getIntExtra("bookName", 0));
        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getIntent().getIntExtra("cardImageId", 0));
        final int[] cardImage = new int[cardImageDrawables.length()];
        final int[] tickIcon = new int[cardImageDrawables.length()];


        //Set the locks
        for (int i = 0; i < cardImageDrawables.length(); i++) {
            cardImage[i] = cardImageDrawables.getResourceId(i, 0);
            if(isUnlocked()) // Set the tick icon
                tickIcon[i] = R.string.fa_check;
            else
                tickIcon[i] = R.string.fa_lock;
        }

        final CustomGridBookFragment adapter = new CustomGridBookFragment(BookActivity.this, cardText1, cardImage, bookPageIds, tickIcon);

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

                    String pageId =  bookPageIds[position];
                    int helpID = getResources().getIdentifier(pageId + "Help", "array", getClass().getPackage().getName());

                    Intent intent = new Intent(BookActivity.this, PageActivity.class);
                    intent.putExtra("bookHelp", helpID);
                    intent.putExtra("tutorialId", pageId);
                    intent.putExtra("bookName", bookName);
                    startActivity(intent);
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("bookThumb", getIntent().getIntExtra("bookCoverImageInsideId", 0));
                    bundle.putInt("bookName", getIntent().getIntExtra("bookName", 0));
                    bundle.putInt("early", 0);

                    FragmentManager fm = getSupportFragmentManager();
                    MyDialogFragment dialogFragment = new MyDialogFragment();
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(fm, getString(R.string.menu_subscribe));
                }
            }
        });
    }

    public Boolean isUnlocked(){

        if (mSubscribed)
            return true;
        //check what page we are on and if it is purchased
        for( int i =0; i < mPurchasedBooksArray.length; i++) {
            if (bookName.equals(SKU_BOOK_NAME_ARRAY[i]))
                bookPurchasedLock = mPurchasedBooksArray[i];
        }
        if (bookPurchasedLock == null){
            return false;

        }
        if (bookPurchasedLock  || mSubscribed) {
            return true;


        }

        else
            return false;

    }

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
    } // end toast



}