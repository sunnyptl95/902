package com.example.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;

public class Practical8 extends AppCompatActivity {
    Button bSelect,bStart,bStop,bPause;
    ToggleButton btn;
    Uri uri;
    MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical8);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bSelect=findViewById(R.id.btnSelect);
        bStart=findViewById(R.id.btnStart);
        bPause=findViewById(R.id.btnPause);
        bStop=findViewById(R.id.btnStop);


        bSelect.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            i.setType("audio/*");
            startActivityForResult(i,100);
        });
        if(checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO)
        != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.READ_MEDIA_AUDIO},
                    100);
        }
//       media = new MediaPlayer();
//        String path = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DOWNLOADS) + "/song.mp3";
//        File file = new File(path);
//        Toast.makeText(this, "File Exists = " + file.exists(),Toast.LENGTH_SHORT).show();
//        try{
//            media.setDataSource(path);
//            media.prepare();
//              media=MediaPlayer.create(Practical8.this,R.raw.song);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                // Toggle is ON (Displays "Play")
//                Toast.makeText(Practical8.this, "Play Clicked", Toast.LENGTH_SHORT).show();
//                media.start();
//            } else {
//                // Toggle is OFF (Displays "Pause")
//                Toast.makeText(Practical8.this, "Pause Clicked", Toast.LENGTH_SHORT).show();
//                if (media.isPlaying()) {
//                    media.pause();
//                }
//            }
//        });
        bStart.setOnClickListener(v -> {
            Toast.makeText(Practical8.this,"Play Clicked",Toast.LENGTH_SHORT).show();
            media.start();
        });
        bPause.setOnClickListener(v -> {
            Toast.makeText(Practical8.this,"Pause Clicked",Toast.LENGTH_SHORT).show();
            if(media.isPlaying())
                media.pause();
        });
        bStop.setOnClickListener(v -> {
            Toast.makeText(Practical8.this,"Stop Clicked",Toast.LENGTH_SHORT).show();
            if(media.isPlaying())
                media.stop();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 100 && resultCode == RESULT_OK && data != null){
           uri = data.getData();
           media = MediaPlayer.create(this,uri);
           Toast.makeText(this,"Audio Selected.", Toast.LENGTH_SHORT).show();
        }
    }
}