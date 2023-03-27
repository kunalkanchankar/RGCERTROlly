package com.trolly;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Parcelable;
import android.widget.ListView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.InputStream;
import java.util.List;

public class Page4 extends AppCompatActivity {
    private ListView listView;

    Button scanbut, addbut, removebut;
    //    public static TextView id;
    //    Button add,view;
    TextView textview3, textview5, textview6 ,textView;
    EditText name, price, code, Mnumber;
    String txtname, txtprice, txtcode, txtmno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

    }

}
