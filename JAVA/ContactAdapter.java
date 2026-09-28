package com.example.myapp;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity activity;
    ArrayList<Contact> list;
    public ContactAdapter(Activity activity,ArrayList<Contact> list){
        super(activity,R.layout.contact_item,list);
        this.activity = activity;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.contact_item,null);
        ImageView img = view.findViewById(R.id.imgphoto);
        TextView name = view.findViewById(R.id.txtName);
        TextView mobile = view.findViewById(R.id.txtMobile);
        Contact c = list.get(position);
        String photoUri = c.getPhotoUri();
        if (photoUri != null) {
            img.setImageURI(Uri.parse(photoUri));
        }
        else {
            img.setImageResource(c.getImage());
        }
        img.setImageResource(c.getImage());
        name.setText(c.getName());
        mobile.setText(c.getMobile());
        return view;
    }
}