package com.example.smartaquarium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Set;

public class dashboard extends AppCompatActivity {

    ViewFlipper imageflip;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        int images[] = {R.drawable.ikanhias, R.drawable.ikansatu, R.drawable.ikanbadut};

        imageflip = findViewById(R.id.imageflip);

        //pengulangan
        /*for (int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }*/
// hore
        for (int image: images){
            flipperImages(image);
        }


        //ButtonMenuPakanIkan
        button = (Button) findViewById(R.id.buttonpakan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPakanIkan();
            }
        });
        }

        public void openPakanIkan() {
            Intent intent = new Intent(this, PakanIkan.class);
            startActivity(intent);
        }

        //GambarSlideDashboard
        public void flipperImages(int image){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);

            imageflip.addView(imageView);
            imageflip.setFlipInterval(4000);
            imageflip.setAutoStart(true);

            //animasi
            imageflip.setInAnimation(this, android.R.anim.slide_in_left);
            imageflip.setOutAnimation(this, android.R.anim.slide_out_right);
        }
    }