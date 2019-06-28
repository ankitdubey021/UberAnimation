package com.ebiztechnocrats.uberanimation;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView bgImg;
    ConstraintLayout layout;
    TextInputEditText mobEt;
    private float height;
    private boolean isAnimating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels-metrics.heightPixels/50.0f;


        mobEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    isAnimating=true;
                    animate(-1,0f);
                }
            }
        });



    }

    private void bindViews() {
        bgImg=findViewById(R.id.bg_img);
        layout=findViewById(R.id.layout);
        mobEt=findViewById(R.id.mob_et);
    }

    private void animate(int direction, float alpha) {
        FlingAnimation flingX =
                new FlingAnimation(layout, DynamicAnimation.Y)
                        .setStartVelocity(direction*height)
                        .setFriction(0.5f);
        flingX.start();
        bgImg.animate().alpha(alpha).setDuration(1000).start();
    }

    @Override
    public void onBackPressed() {

        if(!isAnimating){
            super.onBackPressed();
        }
        else{
            animate(1,1f);
            isAnimating=false;
            mobEt.clearFocus();
        }
    }
}
