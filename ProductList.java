package com.trolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {
    InputStream inputStream; String[] data;String[] row;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvreader);
        getSupportActionBar().setTitle("TROlly Activate");
        getSupportActionBar().setSubtitle("All Product Details ");
        String srtodb = getIntent().getStringExtra("custidtouser");

        listView = (ListView) findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.activity_item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.product_details);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.readall();

        for(Object scoreData:scoreList ) {
            itemArrayAdapter.add(scoreData);
        }
    }
}


