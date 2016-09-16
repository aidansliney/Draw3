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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class PageActivity extends AppCompatActivity {

    public int pageCounter = 0;
    public int i;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://draw-891c7.appspot.com");
    StorageReference imagesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.learn.howtodraw.draw.R.layout.activity_page);

        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            imagesRef = storageRef.child("tablet");
        } else {
            imagesRef = storageRef.child("phone");
        }

        final TextView helpText = (TextView) findViewById(R.id.help_text);
        final View shareLayout = findViewById(R.id.shareLayout);
        final View shareText = findViewById(R.id.share_text);
        final TextView pageNumber = (TextView) findViewById(R.id.pageNumber);
        final ImageView imageView = (ImageView) findViewById(R.id.slideImage);
        final TextView rightArrow = (TextView) findViewById(R.id.nextImage2);
        final TextView leftArrow = (TextView) findViewById(R.id.previousImage2);

        //bookSlides come from intents (BookActivity or Third Fragment as these are the only entry points)
        final String bookHelpStrings[] = getResources().getStringArray(getIntent().getIntExtra("bookHelp", 0));
        final String tutorialId = getIntent().getStringExtra("tutorialId");
        final String bookName = getIntent().getStringExtra("bookName");
        final StorageReference imagesRef2 = imagesRef.child(bookName);

        final String[] downloadLinks = new String[bookHelpStrings.length];

        if(downloadLinks[0] == null) {
            Arrays.fill(downloadLinks, "http://www.laminex.com.au/uploads/products/white.jpg");
        }

        //needs to start with 1 as the first image is 01
        for (i= 1; i < bookHelpStrings.length+1; i++) {
            String page =  getPaddedNumber(i);
            StorageReference spaceRef = imagesRef2.child(tutorialId+"p"+page+".png");
            Log.d("The page we are looking",tutorialId+"p"+page+".png");

            spaceRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    String s=uri.toString();

                    Log.d("URL",s);
                    String requiredString = s.substring(s.indexOf(tutorialId+"p") + 7, s.indexOf(".png"));
                    int result = Integer.parseInt(requiredString);
                    Log.d("String to int",""+result);

                    downloadLinks[result -1] = uri.toString();
                    Picasso.with(PageActivity.this).load(downloadLinks[result -1]).fetch();
                    if (result  == 1){
                        Picasso.with(PageActivity.this).load(downloadLinks[result -1]).noFade().into(imageView);
                    }
                }
            });
        }

        if (bookHelpStrings[pageCounter].equals(""))
            helpText.setVisibility(View.INVISIBLE);
        else
            helpText.setText(bookHelpStrings[0]);
        leftArrow.setVisibility(View.INVISIBLE);
        shareLayout.setVisibility(View.INVISIBLE);
        shareText.setVisibility(View.INVISIBLE);
        int lastPic = bookHelpStrings.length - 1;
        // Picasso.with(PageActivity.this).load(downloadLinks[downloadLinks.length-1]).noFade().into(imageView);
        View nextImage2 = findViewById(R.id.nextImage2);
        TextView endPage = (TextView) findViewById(R.id.endPage);
        String endPageString = String.valueOf(lastPic + 1);
        endPage.setText(endPageString);

        assert nextImage2 != null;
        nextImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leftArrow.setVisibility(View.VISIBLE);

                if (pageCounter == bookHelpStrings.length - 1) {
                    shareLayout.setVisibility(View.VISIBLE);
                    shareText.setVisibility(View.VISIBLE);
                    helpText.setVisibility(View.INVISIBLE);
                    rightArrow.setVisibility(View.INVISIBLE);
                } else {
                    pageCounter++;
                    Log.d("this is the counter", "" + pageCounter);
                    shareLayout.setVisibility(View.INVISIBLE);
                    shareText.setVisibility(View.INVISIBLE);

                    if (bookHelpStrings[pageCounter].equals(""))
                        helpText.setVisibility(View.INVISIBLE);
                    else
                        helpText.setVisibility(View.VISIBLE);
                    Picasso.with(PageActivity.this).load(downloadLinks[pageCounter]).noFade().into(imageView);
                    // Picasso.with(PageActivity.this).load("https://www.eff.org/files/tor-https-1.png").noFade().into(imageView);
                    // Log.d("downlaod link",downloadLinks[pageCounter+1]);

                    helpText.setText(bookHelpStrings[pageCounter]);
                    String pNumber = String.valueOf(pageCounter + 1);
                    pageNumber.setText(pNumber);
                }
            }
        });

        View previousImage2 = findViewById(com.learn.howtodraw.draw.R.id.previousImage2);
        assert previousImage2 != null;
        previousImage2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                rightArrow.setVisibility(View.VISIBLE);
                if (pageCounter != 0) {
                    pageCounter--;
                    Log.d("this is the counter", "" + pageCounter);
                    shareLayout.setVisibility(View.INVISIBLE);
                    shareText.setVisibility(View.INVISIBLE);
                    if (bookHelpStrings[pageCounter].equals(""))
                        helpText.setVisibility(View.INVISIBLE);
                    else
                        helpText.setVisibility(View.VISIBLE);
                    Picasso.with(PageActivity.this).load(downloadLinks[pageCounter]).noFade().into(imageView);
                    helpText.setText(bookHelpStrings[pageCounter]);
                    TextView pageNumber = (TextView) findViewById(R.id.pageNumber);
                    String pNumber = String.valueOf(pageCounter + 1);
                    pageNumber.setText(pNumber);
                }
            }
        });

        View camera = findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                dispatchTakePictureIntent();
            }
        });

        View twitter = findViewById(R.id.twitter);
        twitter.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                shareTo = "twitter";
                dispatchTakePictureIntent();
            }
        });

        assert imageView != null;
        imageView.setOnTouchListener(new OnSwipeTouchListener(PageActivity.this) {
            public void onSwipeTop() {
                // Toast.makeText(PageActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                rightArrow.setVisibility(View.VISIBLE);
                if (pageCounter != 0) {
                    pageCounter--;
                    Log.d("this is the counter", "" + pageCounter);
                    shareLayout.setVisibility(View.INVISIBLE);
                    shareText.setVisibility(View.INVISIBLE);
                    if (bookHelpStrings[pageCounter].equals(""))
                        helpText.setVisibility(View.INVISIBLE);
                    else
                        helpText.setVisibility(View.VISIBLE);
                    Picasso.with(PageActivity.this).load(downloadLinks[pageCounter]).noFade().into(imageView);
                    helpText.setText(bookHelpStrings[pageCounter]);
                    TextView pageNumber = (TextView) findViewById(R.id.pageNumber);
                    String pNumber = String.valueOf(pageCounter + 1);
                    pageNumber.setText(pNumber);
                }
            }


            public void onSwipeLeft() {
                leftArrow.setVisibility(View.VISIBLE);

                if (pageCounter == bookHelpStrings.length - 1) {
                    shareLayout.setVisibility(View.VISIBLE);
                    shareText.setVisibility(View.VISIBLE);
                    helpText.setVisibility(View.INVISIBLE);
                    rightArrow.setVisibility(View.INVISIBLE);
                } else {
                    pageCounter++;
                    Log.d("this is the counter", "" + pageCounter);
                    shareLayout.setVisibility(View.INVISIBLE);
                    shareText.setVisibility(View.INVISIBLE);

                    if (bookHelpStrings[pageCounter].equals(""))
                        helpText.setVisibility(View.INVISIBLE);
                    else
                        helpText.setVisibility(View.VISIBLE);
                    Picasso.with(PageActivity.this).load(downloadLinks[pageCounter]).noFade().into(imageView);
                    // Picasso.with(PageActivity.this).load("https://www.eff.org/files/tor-https-1.png").noFade().into(imageView);
                    // Log.d("downlaod link",downloadLinks[pageCounter+1]);

                    helpText.setText(bookHelpStrings[pageCounter]);
                    String pNumber = String.valueOf(pageCounter + 1);
                    pageNumber.setText(pNumber);
                }
            }



            public void onSwipeBottom() {
                //  Toast.makeText(PageActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }
        });
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

    public void run(){


    }
}
