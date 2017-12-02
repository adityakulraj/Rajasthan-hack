package aarnav100.developer.rajafair;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.sand));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.sand)));
        final GifImageView camel = (GifImageView)findViewById(R.id.camel);
        TranslateAnimation anim = new TranslateAnimation(-250.0f,700.0f,0.0f,0.0f);
        anim.setDuration(5000);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                camel.setVisibility(View.GONE);
                startActivity(new Intent(Splash.this,MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        camel.setAnimation(anim);
        ImageView title = (ImageView)findViewById(R.id.title);
        title.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        title.setVisibility(View.VISIBLE);
    }
}
