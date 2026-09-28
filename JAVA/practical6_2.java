package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class practical6_2 extends AppCompatActivity {
    Gallery gl;
    TextView tInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical62);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gl=findViewById(R.id.gallary);
        tInfo=findViewById(R.id.txtInfo);

        Intent i = getIntent();
        String name = i.getStringExtra("carname");
        tInfo.setText(name);
        int[] images = null;
        if(name.equals("Audi")){
            images = new int[]{
                    R.drawable.audi1,
                    R.drawable.audi2,
                    R.drawable.audi3
            };
            tInfo.setText("Collection of Audi`s Cars");
        }
        else if(name.equals("Bugatti")){
            images = new int[]{
                    R.drawable.bugatti1,
                    R.drawable.bugatti2,
                    R.drawable.bugatti3
            };
            tInfo.setText("Collection of Bugatti`s Cars");
        }
        else if(name.equals("Ford")){
            images = new int[]{
                    R.drawable.ford1,
                    R.drawable.ford2,
                    R.drawable.ford3
            };
            tInfo.setText("Collection of Ford`s Cars");
        }
        else if(name.equals("Range Rover")){
            images = new int[]{
                    R.drawable.rover1,
                    R.drawable.rover2,
                    R.drawable.rover3
            };
            tInfo.setText("Collection of Range Rover`s Cars");
        }
        gl.setAdapter(new ImageAdapter(this, images));


    }
}