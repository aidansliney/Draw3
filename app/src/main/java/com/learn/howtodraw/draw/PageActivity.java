package com.learn.howtodraw.draw;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        View camera= findViewById(com.learn.howtodraw.draw.R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                dispatchTakePictureIntent();
            }
        });


        View info= findViewById(com.learn.howtodraw.draw.R.id.info);
        info.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                ArrayList<Uri> imageUris = new ArrayList<Uri>();
               // Uri imageUri1=Uri.parse("android.resource://"+getPackageName()+"/drawable/"+camera);
               // imageUris.add(imageUri1);// Add your image URIs here

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "Share images to.."));

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

    static final int REQUEST_TAKE_PHOTO = 1;
    static Uri photoURI;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            ArrayList<Uri> imageUris = new ArrayList<Uri>();
            imageUris.add(photoURI); // Add your image URIs here
            Log.d("This is Sparta",photoURI.toString() );
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out my drawing. I learned how to draw it from http://www.google.com");
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "Share images to.."));
        }
    }

    private void dispatchTakePictureIntent() {

        Log.d("This is","dispatch" );
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
                Log.d("This is",photoFile.toString() );

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





}
