package com.trolly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CSVReader extends AppCompatActivity {
    InputStream inputStream; String[] data;String[] row;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csvreader);

        listView = (ListView) findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.activity_item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.product_details);
        CSVFile csvFile = new CSVFile(inputStream);
        String varscan = getIntent().getStringExtra("scanbar");
        List scoreList = csvFile.read(varscan);

        for(Object scoreData:scoreList ) {
            itemArrayAdapter.add(scoreData);
        }
    }
}


