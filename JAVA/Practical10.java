package com.example.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Practical10 extends AppCompatActivity {
EditText etId,etName,etMarks;
Button btnAdd,btnView,btnUpdate,btnDelete;
TextView txResult;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical10);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    etId = findViewById(R.id.etId1);
    etName = findViewById(R.id.etName1);
    etMarks = findViewById(R.id.etMarks1);
    btnAdd = findViewById(R.id.btnAdd);
    btnView = findViewById(R.id.btnView);
    btnUpdate = findViewById(R.id.btnUpdate);
    btnDelete = findViewById(R.id.btnDelete);
    txResult = findViewById(R.id.txResult1);

    db = openOrCreateDatabase("StudentDB",MODE_PRIVATE,null);

    db.execSQL("CREATE TABLE IF NOT EXISTS student (" +
           "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "name TEXT," + "marks INTEGER)");
    btnAdd.setOnClickListener( v -> {
        String name = etName.getText().toString();
        String marks = etMarks.getText().toString();

        db.execSQL("INSERT INTO student(name,marks) VALUES(?,?)",
                new Object[]{name,marks});
        Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
        etName.setText(" ");
        etMarks.setText(" ");
    });
    btnView.setOnClickListener( v -> {
        Intent i = new Intent(Practical10.this,pr10_2.class);
        startActivity(i);

    });
    btnUpdate.setOnClickListener( v -> {
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        String marks = etMarks.getText().toString();

        db.execSQL("UPDATE student SET name=?,marks=? WHERE id=?",
                new Object[]{name,marks,id});
        Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show();
        etName.setText(" ");
        etMarks.setText(" ");
    });
    btnDelete.setOnClickListener(v -> {
        String id = etId.getText().toString();
        db.execSQL("DELETE FROM student WHERE id=?",
                new Object[]{id});
        Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show();
        etName.setText(" ");
        etMarks.setText(" ");
    });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    if(db != null && db.isOpen()){
        db.close();
        }
    }
}
