package com.trolly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class Page3 extends AppCompatActivity {
    Timer time; EditText mno;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        getSupportActionBar().setTitle("Welcome TROlly");
        getSupportActionBar().setSubtitle("Journey Starts :) ");
        mno = findViewById(R.id.MNumber);
        next = findViewById(R.id.buttonnext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mnos = mno.getText().toString();
//                if(mnos.isEmpty() || mnos.length()<10){
//                    new SweetAlertDialog(Page3.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Something went wrong!").show();
//                }else{
                Intent indent = new Intent(Page3.this,MenuPage.class);
                indent.putExtra("mobilenumber",mnos);
                startActivity(indent);
                mno.setText("");
//                }
            }
        }); //move to next page END
    }
}