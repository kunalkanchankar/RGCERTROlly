package com.trolly;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    InputStream inputStream;String title_i,price_i,sr_i,quantity_i;
    DataGS dataGS = new DataGS();

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List read(String scanbar){
        ArrayList resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if(row[0].equalsIgnoreCase("A"+scanbar)){
                    Log.d("VariableTag1", row[0].toString());
                    resultList.add(row);

                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }

    public List readall() {
        ArrayList resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                    resultList.add(row);
            }
        }
        catch (IOException ex) {throw new RuntimeException("Error in reading CSV file: "+ex);}
        finally { try {inputStream.close();}
            catch (IOException e) {throw new RuntimeException("Error while closing input stream: "+e);}
        }
        return resultList;
    }

    public List readinput(String varscan) {
        ArrayList resultList = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                if(row[0].equalsIgnoreCase("A"+varscan)){
                    Log.d("VariableTag1", row[0].toString());
                    resultList.add(row);
                    Log.d("VariableTag2", row[3].toString());
                    title_i = row[1].toString();
                    price_i = row[3].toString();
                    quantity_i =  row[4].toString();
                    sr_i =  row[0].toString();
                }else{
                    Log.d("VariableTag", "k");
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }

    public List readallinput(String varscan) {
        ArrayList resultList1 = new ArrayList();
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader1.readLine()) != null) {
                String[] row = csvLine.split(",");
                if(row[0].equalsIgnoreCase(varscan)){
                    Log.d("VariableTag5", row[0].toString());
                }else{
                    Log.d("VariableTag", "k");
                }
            }
        }
        catch (IOException ex) {throw new RuntimeException("Error in reading CSV file: "+ex);}
        finally { try {inputStream.close();}
            catch (IOException e) {throw new RuntimeException("Error while closing input stream: "+e);}
        }
        return resultList1;
    }
}
