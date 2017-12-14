package com.game.polamrapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private ImageView bottle;
    private int lastAngle = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View main = findViewById(R.id.root);
        bottle = findViewById(R.id.bottle);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinTheBottle();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_spin:
                spinTheBottle();
                break;
            case R.id.action_zero:
                resetBottle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void spinTheBottle() {
        int angle = RANDOM.nextInt(3600 - 360) + 360;
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;
        final Animation animation = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, angle, pivotX, pivotY);
        lastAngle = angle;
        animation.setDuration(2500);
        animation.setFillAfter(true);
        bottle.startAnimation(animation);
    }

    private void resetBottle() {
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;
        final Animation animation = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, 0, pivotX, pivotY);
        lastAngle = -1;
        animation.setDuration(2000);
        animation.setFillAfter(true);
        bottle.setAnimation(animation);
    }
}
