package com.learn.howtodraw.draw;

import android.content.Intent;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.learn.howtodraw.draw.R.id.bookName;

public class PageActivity extends AppCompatActivity {

    int pageCounter = 0;
    int i;

    String tabletOrPhone; // direct
    String bookName;
    String[] directDownloadLinks, bookHelpStrings; // array of links + help text

    TextView leftArrow,rightArrow, helpText, pageNumber;
    View shareText, shareLayout;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.learn.howtodraw.draw.R.layout.activity_page);

        boolean istabletOrPhone = getResources().getBoolean(R.bool.isTablet); //checks sw600
        if (istabletOrPhone)
            tabletOrPhone = "tablet";
        else
            tabletOrPhone = "phone";

        helpText = (TextView) findViewById(R.id.help_text);
        shareLayout = findViewById(R.id.shareLayout);
        shareText = findViewById(R.id.share_text);
        pageNumber = (TextView) findViewById(R.id.pageNumber);
        imageView = (ImageView) findViewById(R.id.slideImage);
        rightArrow = (TextView) findViewById(R.id.nextImage);
        leftArrow = (TextView) findViewById(R.id.previousImage);
        //bookSlides come from intents (BookActivity or Third Fragment as these are the only entry points)
        bookHelpStrings = getResources().getStringArray(getIntent().getIntExtra("bookHelp", 0));
        directDownloadLinks = new String[bookHelpStrings.length];

        final String tutorialId = getIntent().getStringExtra("tutorialId");
        bookName = getIntent().getStringExtra("bookName");

        for (i= 0; i < bookHelpStrings.length; i++) {
            String page =  getPaddedNumber(i+1);
            directDownloadLinks[i] ="https://firebasestorage.googleapis.com/v0/b/draw-891c7.appspot.com/o/"+tabletOrPhone+"%2F"+bookName+"%2F"+tutorialId+"p"+page+".png?alt=media";

            if (i == 0)
            {
                Picasso.with(PageActivity.this).load(directDownloadLinks[0]).fetch( new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(PageActivity.this).load(directDownloadLinks[0]).networkPolicy(NetworkPolicy.OFFLINE).into(imageView);
                    }
                    @Override
                    public void onError() {
                        Picasso.with(PageActivity.this).load(directDownloadLinks[0]).fetch();
                    }
                });

            }
            else {
                Picasso.with(PageActivity.this).load(directDownloadLinks[i]).fetch(new com.squareup.picasso.Callback() {
                    String link = directDownloadLinks[i];
                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onError() {
                        Picasso.with(PageActivity.this).load(link).fetch();
                    }
                });
            }
        }

        //prepare first page
        helpText.setText(bookHelpStrings[0]);
        leftArrow.setVisibility(View.INVISIBLE);
        shareLayout.setVisibility(View.INVISIBLE);
        shareText.setVisibility(View.INVISIBLE);
        View camera = findViewById(R.id.camera);

        TextView endPageNumber = (TextView) findViewById(R.id.endPage);
        endPageNumber.setText(String.valueOf(bookHelpStrings.length));
        assert rightArrow != null;
        // if tutorial book
        if (bookName.equals("book00")) {
            TextView slash = (TextView) findViewById(R.id.slash);
            endPageNumber.setVisibility(View.INVISIBLE);
            endPageNumber.setVisibility(View.INVISIBLE);
            pageNumber.setVisibility(View.INVISIBLE);
            slash.setVisibility(View.INVISIBLE);
        }

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnPageNext();
            }
        });
        leftArrow.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                turnPagePrevious();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        imageView.setOnTouchListener(new OnSwipeTouchListener(PageActivity.this) {
            public void onSwipeRight() {
                turnPagePrevious();
            }
            public void onSwipeLeft() {
                turnPageNext();
            }
            public void onSwipeBottom() {
                //Toast.makeText(PageActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeTop() {}
        });
    }

    public void turnPageNext(){
        leftArrow.setVisibility(View.VISIBLE);
        if (pageCounter == bookHelpStrings.length - 1) {
            shareLayout.setVisibility(View.VISIBLE);
            shareText.setVisibility(View.VISIBLE);
            helpText.setVisibility(View.INVISIBLE);
            rightArrow.setVisibility(View.INVISIBLE);
        } else {
            pageCounter++;
            shareLayout.setVisibility(View.INVISIBLE);
            shareText.setVisibility(View.INVISIBLE);

            if (bookHelpStrings[pageCounter].equals(""))
                helpText.setVisibility(View.INVISIBLE);
            else
                helpText.setVisibility(View.VISIBLE);
            Picasso.with(PageActivity.this).load(directDownloadLinks[pageCounter]).networkPolicy(NetworkPolicy.OFFLINE).noFade().into(imageView, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                }
                @Override
                public void onError() {
                    Picasso.with(PageActivity.this).load(directDownloadLinks[pageCounter]).into(imageView);
                }
            });
            helpText.setText(bookHelpStrings[pageCounter]);
            pageNumber.setText(String.valueOf(pageCounter + 1));
        }

        if (bookName.equals("book00")){
            shareLayout.setVisibility(View.INVISIBLE);
            shareText.setVisibility(View.INVISIBLE);
        }
    }

    public void turnPagePrevious(){
        rightArrow.setVisibility(View.VISIBLE);
        if (pageCounter != 0) {
            pageCounter--;
            shareLayout.setVisibility(View.INVISIBLE);
            shareText.setVisibility(View.INVISIBLE);
            if (bookHelpStrings[pageCounter].equals(""))
                helpText.setVisibility(View.INVISIBLE);
            else
                helpText.setVisibility(View.VISIBLE);
            Picasso.with(PageActivity.this).load(directDownloadLinks[pageCounter]).networkPolicy(NetworkPolicy.OFFLINE).noFade().into(imageView, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                }
                @Override
                public void onError() {
                    Picasso.with(PageActivity.this).load(directDownloadLinks[pageCounter]).noFade().into(imageView);
                }
            });
            helpText.setText(bookHelpStrings[pageCounter]);
            pageNumber.setText(String.valueOf(pageCounter + 1));
        }
        //no left arrow on page 1
        if(pageCounter == 0){
            leftArrow.setVisibility(View.INVISIBLE);
        }

    }

    static final int REQUEST_TAKE_PHOTO = 1;
    static Uri photoURI;
    static String shareTo;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            ArrayList<Uri> imageUris = new ArrayList<Uri>();
            imageUris.add(photoURI); // Add your image URIs here
            Log.d("This is Sparta", photoURI.toString());
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share));
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, getString(R.string.shareimage)));
        }
    }

    private void dispatchTakePictureIntent() {

        Log.d("This is", "dispatch");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                Log.d("This is", photoFile.toString());

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private String getPaddedNumber(int number) {
        return String.format("%02d", number);
    }


}
