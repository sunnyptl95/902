package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Practical_9 extends AppCompatActivity {

    EditText edtFileName ,edtContent;
    Button btnCreate , btnRead;
    TextView txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical9);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       edtFileName = findViewById(R.id.edtFileName);
       edtContent = findViewById(R.id.edtContent);
       btnCreate = findViewById(R.id.btnCreate);
       btnRead = findViewById(R.id.btnRead);
       txtContent = findViewById(R.id.txtContent);

       btnCreate.setOnClickListener(v -> {
           try{
               String fileName = edtFileName.getText().toString();
               String content = edtContent.getText().toString();
               File folder = getExternalFilesDir(null);
               File file = new File(folder,fileName + ".txt");
               FileWriter writer = new FileWriter(file);
               writer.write(content);
               writer.close();
               edtFileName.setText(null);
               edtContent.setText(null);

               Toast.makeText(Practical_9.this, "File Created SuccessFully", Toast.LENGTH_LONG).show();
           }catch (Exception e){
               Toast.makeText(Practical_9.this, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
           }
       });


        btnRead.setOnClickListener(v -> {
            String fileName1 = edtFileName.getText().toString();
           if (fileName1.isEmpty()){
                edtFileName.setHint("Please Enter File Name");
                edtFileName.requestFocus();
                Toast.makeText(this,"Please Enter file name",Toast.LENGTH_LONG).show();
            }
           else {
               try {
                   String fileName = edtFileName.getText().toString();

                   File folder = getExternalFilesDir(null);
                   File file = new File(folder, fileName1 + ".txt");
                   FileReader reader = new FileReader(file);
                   StringBuilder content = new StringBuilder();
                   int ch;

                   while ((ch = reader.read()) != -1) {
                       content.append((char) ch);
                   }
                   reader.close();
                   txtContent.setText(content.toString());
               } catch (Exception e) {
                   Toast.makeText(Practical_9.this, "File not found...", Toast.LENGTH_LONG).show();
               }
           }
        });



    }
}