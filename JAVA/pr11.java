package com.example.myapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class pr11 extends AppCompatActivity {
    EditText etNumber, etMessage;
    Button btnSend;
    private static final int SMS_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pr11);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });
        etNumber = findViewById(R.id.etNumber);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(pr11.this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(pr11.this,
                            new String[]{Manifest.permission.SEND_SMS}, 100);
                }
                else{
                    sendSMS();
                }

            }
        });
    }

    private void sendSMS() {
        String number = etNumber.getText().toString().trim();
        String message = etMessage.getText().toString().trim();
        if (!number.isEmpty() && !message.isEmpty()) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, message,
                        null, null);
                Toast.makeText(this, "SMS Sent!",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Failed: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Enter number and message",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[]
                                                   permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS Permission Granted",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS Permission Denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}