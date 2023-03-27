package com.trolly;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuPage extends AppCompatActivity {
    DBHelper DB1;
    TextView custid,mtotala;SimpleDateFormat sdf1;
    private List insertlist1 = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        getSupportActionBar().setTitle("TROlly Activate");
        getSupportActionBar().setSubtitle("Menu Page");

        DB1 = new DBHelper(this);
//       ===================Set date and customer ID ========================================
        custid = findViewById(R.id.Custid);
        SimpleDateFormat sdf = new SimpleDateFormat("'CustID'yyMMddHHmmss");
        sdf1 = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTime1 = sdf1.format(new Date());
        custid.setText(currentDateandTime);

//      ===========fetch mobile number from Page3 ===============================================================

        String mnumber = getIntent().getStringExtra("mobilenumber");
        Log.d("VariableDBTag5", mnumber);
        DB1.insertuserlog(custid.getText().toString(),mnumber,"Login Successful",currentDateandTime1,"-");
//      ========total price set label ========================================================================
        mtotala = findViewById(R.id.MTotalA);
        double cdata1 = DB1.gettotal(custid.getText().toString());
        mtotala.setText("Total Price : "+cdata1+" /-");

//      ========orderlist set button 1 ========================================================================
        View orderlist = findViewById(R.id.gifImageView1);
        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(MenuPage.this,Userlist.class);
                indent.putExtra("custidtouser",custid.getText().toString());
                startActivity(indent);
            }
        });

//      ========inputdata set button 4 ========================================================================
        View productlist = findViewById(R.id.gifImageView4);
        productlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent indent = new Intent(MenuPage.this,DBPage2.class); //DBPage2.class
                indent.putExtra("custid",custid.getText().toString());
                startActivity(indent);
            }
        });
//      ========product list set button 2 ========================================================================
        View btnlist = findViewById(R.id.gifImageView2);
        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(MenuPage.this,ProductList.class);
                indent.putExtra("custidtouser",custid.getText().toString());
                startActivity(indent);
            }
        });

//      ========sanner button 3 ========================================================================

        View scanproduct = findViewById(R.id.gifImageView3);
        scanproduct.setOnClickListener(v->{
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
            String cusidTXT = custid.getText().toString();
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
            //String text3 = currentDateandTime1;
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
                        Toast.makeText(MenuPage.this, "Pls first click On Search Icon", Toast.LENGTH_SHORT).show();
                    }
                    else { Log.d("VariableDBTag2", "go");
                        Boolean vsr = DB1.insertuserdata(text1.getText().toString(), text2.getText().toString(), sdf1.format(new Date()),text4.getText().toString(), text5.getText().toString(), text6.getText().toString());
                        DB1.insertuserlog(custid.getText().toString(),"mnumber","Product Inserted","currentDateandTime1","-");
                        Log.d("VariableDBTag5", "go");
                        Toast.makeText(MenuPage.this, "dial" + vsr.booleanValue(), Toast.LENGTH_SHORT).show();
                        dai.dismiss();
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

    // ===========================================On back click===============================
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity").setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(MenuPage.this, "Activity closed",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", null).show();
    }
}