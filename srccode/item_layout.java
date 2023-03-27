package com.trolly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class item_layout extends AppCompatActivity {
    Button btnaddp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_layout);
        getSupportActionBar().setTitle("TROlly Activate");
        getSupportActionBar().setSubtitle("All Product Details ");

    }

    private void openAlertBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(item_layout.this);
        builder.setTitle(" ADD Product !");
        builder.setIcon(R.drawable.order);
        builder.setMessage("You want to ADD This Product !");
        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}