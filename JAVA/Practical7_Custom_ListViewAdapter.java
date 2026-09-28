package com.example.myapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Practical7_Custom_ListViewAdapter extends AppCompatActivity {

    ListView listView;
    ArrayList<Contact> list;
    ContactAdapter adapter;
    private static  final int CONTACT_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical7_custom_list_view_adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.mainlistview);
        list = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},CONTACT_PERMISSION_CODE);
        }
        else {
            loadContacts();

        }
    }

    private  void loadContacts(){
        Cursor cursor = getContentResolver().
                query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new  String[]{
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER,
                                ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
                        },
                        null,null,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC ");


        if (cursor !=null){
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int photoIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI);
            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                String number = cursor.getString(numberIndex);
                String photo = cursor.getString(photoIndex);

                list.add(new Contact(R.drawable.contact, name, number));
            }
            cursor.close();
        }

        adapter = new ContactAdapter(this,list);
        listView.setAdapter(adapter);
    }
}



