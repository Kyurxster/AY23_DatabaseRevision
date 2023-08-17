package com.myapplicationdev.databaserevision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button btnInsertRecord, btnRetrieveRecordsTV,btnRetrieveRecordsLV;
    ArrayList<Note> noteArrayList;
    ArrayAdapter<Note> noteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertRecord = findViewById(R.id.btnInsertRecord);
        btnRetrieveRecordsTV = findViewById(R.id.btnGetRecordTextView);
        btnRetrieveRecordsLV = findViewById(R.id.btnGetRecordListView);

        DBHelper db = new DBHelper(MainActivity.this);
        noteArrayList =new ArrayList<Note>();
        ArrayAdapter noteArrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,noteArrayList);


        btnInsertRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        InsertActivity.class);
                startActivity(i);


            }
        });

        btnRetrieveRecordsTV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this,
                        RetrieveActivityListView.class);
                startActivity(i);

            }
        });

        btnRetrieveRecordsLV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        RetrieveActivityListView.class);
                startActivity(i);

            }
        });

    }
}
