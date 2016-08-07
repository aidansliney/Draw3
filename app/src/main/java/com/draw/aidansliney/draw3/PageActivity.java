package com.draw.aidansliney.draw3;


        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ViewFlipper;

public class PageActivity extends AppCompatActivity {

    // Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper1);
      //fade_in = AnimationUtils.loadAnimation(this,
 /*     android.R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this,
        android.R.anim.fade_out);
        viewFlipper.setInAnimation(fade_in);
        viewFlipper.setOutAnimation(fade_out);
        //sets auto flipping
        viewFlipper.setAutoStart(false);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();*/

        View nextImage = findViewById(R.id.nextImage);
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
            }
        });
        View previousImage = findViewById(R.id.previousImage);
        previousImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });

    /*trying to collect the array being sent by bookActivity*/
        String[] passedArg = getIntent().getExtras().getStringArray("pageImageArray");
        //enteredValue.setText(passedArg);
        Log.d("Hello2",passedArg[2]);
    }



}