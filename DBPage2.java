package com.trolly;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Date;
import java.util.List;

public class DBPage2 extends AppCompatActivity {
     
    TextView sr,title, price,quantity,ptotala,ptotalp;
    private List insertlist1 = new ArrayList();
    Button insert,view,scanbut,demo;
    DBHelper DB ;SimpleDateFormat sdf2;
    TextView txttitle,txtprice,txtquantity;
    private List insertlist = new ArrayList();

    private static final String[] varbarcode = new String[]{"A8901030869013","A8904035416039"};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbpage2);
        getSupportActionBar().setTitle("TROlly Activate");
        getSupportActionBar().setSubtitle("🔎By Barcode Searching🔍");

        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        scanbut = findViewById(R.id.btnscan);
        insert = findViewById(R.id.btnInsert);
        demo = findViewById(R.id.demo);
        view = findViewById(R.id.btnView);
        sr = findViewById(R.id.sr);
        ptotala = findViewById(R.id.PTotalA);
        ptotalp = findViewById(R.id.PTotalP);

        sdf2 = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String currentDateandTime1 = sdf2.format(new Date());

        sr.setText(getIntent().getStringExtra("custid"));
        DB = new DBHelper(this);

        AutoCompleteTextView edittext = findViewById(R.id.compbar);
        ArrayAdapter<String> compadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,varbarcode);
        edittext.setAdapter(compadapter);
        double cdata1 = DB.gettotal(sr.getText().toString());
        ptotala.setText("Total Price : "+cdata1+" /-");
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(DBPage2.this,Userlist.class));

                InputStream inputStream = getResources().openRawResource(R.raw.product_details);
                CSVFile csvFile = new CSVFile(inputStream);
                String varscan = edittext.getText().toString();
                if(varscan.isEmpty()){
                    Toast.makeText(DBPage2.this, "Pls insert Product Barcode Number", Toast.LENGTH_SHORT).show();
                }

                else {
                    if (varscan.startsWith("A")) {
                        varscan = varscan.replace("A", "");
                    }
                    insertlist = csvFile.readinput(varscan);
// "title" is textView_component   OR    "title_i" is a variable of CSVFile which holds product information.
                    title.setText(csvFile.title_i);
                    price.setText(csvFile.price_i);
                    quantity.setText(csvFile.quantity_i);
                    if(insertlist.isEmpty()){
                        Toast.makeText(DBPage2.this, "No data found", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    Toast.makeText(DBPage2.this, "Item Found", Toast.LENGTH_SHORT).show();
                }}
            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cusidTXT = sr.getText().toString();
                String barcodeTXT = edittext.getText().toString();
                String titleTXT = title.getText().toString();
                String priceTXT = price.getText().toString();
                String quantityTXT = quantity.getText().toString();
                if(titleTXT.isEmpty()){


                    Toast.makeText(DBPage2.this, "Pls first click On Search Icon", Toast.LENGTH_SHORT).show();
                }
                else {

                    Boolean checkinsertdata = DB.insertuserdata(cusidTXT, barcodeTXT,sdf2.format(new Date()), titleTXT, priceTXT, quantityTXT);
                    double cdata1 = DB.gettotal(cusidTXT);
                    ptotala.setText("Total Price : "+cdata1+" /-");
                    //totala.setText(cdata1+" /-");
                    if (checkinsertdata == true) {
                        Toast.makeText(DBPage2.this, "New Data Inserted" + titleTXT + " :: " + priceTXT, Toast.LENGTH_SHORT).show();
                        edittext.setText("");
                        title.setText("");
                        price.setText("");
                        quantity.setText("");
                    } else {
                        Toast.makeText(DBPage2.this, "New Data Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        scanbut.setOnClickListener(v->{
            scanCode();
        });
    }

    private void scanCode() {
        ScanOptions scanoptions = new ScanOptions();
        scanoptions.setPrompt("Volume up to flash ON");
        scanoptions.setBeepEnabled(true);
        scanoptions.setOrientationLocked(true);
        scanoptions.setCaptureActivity(CaptureAct.class);
        barlaunch.launch(scanoptions);
    }

    ActivityResultLauncher<ScanOptions> barlaunch = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            InputStream inputStream = getResources().openRawResource(R.raw.product_details);
            CSVFile csvFile = new CSVFile(inputStream);
            String varscan = result.getContents().toString();
            if (varscan.startsWith("A")) {
                varscan = varscan.replace("A", "");
            }
            insertlist1 = csvFile.readinput(varscan);
            String cusidTXT = sr.getText().toString();
            String barcodeTXT = varscan.toString();
            String titleTXT = csvFile.title_i.toString();
            String priceTXT = csvFile.price_i.toString();
            String quantityTXT = csvFile.quantity_i.toString();

            // "title" is textView_component   OR    "title_i" is a variable of CSVFile which holds product information.

            // Toast.makeText(DBPage2.this, varscan + "HiBro : ", Toast.LENGTH_SHORT).show();

            Dialog dai = new Dialog(this);
            dai.setContentView(R.layout.customdialog);
            dai.setCancelable(false);

            Button btnok = (Button) dai.findViewById(R.id.btnok);
            Button btncan = (Button) dai.findViewById(R.id.btncan);
            TextView text1 = (TextView) dai.findViewById(R.id.txtcusid);
            TextView text2 = (TextView) dai.findViewById(R.id.txtbar);
            String text3 = sdf2.format(new Date());
            TextView text4 = (TextView) dai.findViewById(R.id.txtptitl);
            TextView text5 = (TextView) dai.findViewById(R.id.txtpri);
            TextView text6 = (TextView) dai.findViewById(R.id.txtwei);
            text1.setText(cusidTXT);
            text4.setText(titleTXT);
            text2.setText("A"+barcodeTXT);
            text5.setText(priceTXT);
            text6.setText(quantityTXT);

            btnok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(titleTXT.isEmpty()){
                        Toast.makeText(DBPage2.this, "Pls first click On Search Icon", Toast.LENGTH_SHORT).show();
                    }
                    else { Log.d("VariableDBTag2", "go");
                        Boolean vsr = DB.insertuserdata(text1.getText().toString(), text2.getText().toString(), text3.toString(),text4.getText().toString(), text5.getText().toString(), text6.getText().toString());
                        DB.insertuserlog(sr.getText().toString(),"mnumber","Product Inserted","currentDateandTime1","-");
                        Log.d("VariableDBTag5", "go");
                        Toast.makeText(DBPage2.this, "dial" + vsr.booleanValue(), Toast.LENGTH_SHORT).show();
                        dai.dismiss();
                        double cdata1 = DB.gettotal(sr.getText().toString());
                        ptotala.setText("Total Price : "+cdata1+" /-");
                        scanCode();
                    }
                }
            });
            btncan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dai.dismiss();
                    scanCode();
                }
            });
            dai.show();
        }
    });

}
