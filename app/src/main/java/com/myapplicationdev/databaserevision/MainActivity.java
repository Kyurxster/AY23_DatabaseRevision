package com.myapplicationdev.databaserevision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button btnInsertRecord, btnRetrieveRecords;
    ArrayList<Note> noteArrayList;
    ArrayAdapter<Note> noteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertRecord = findViewById(R.id.btnInsertRecord);
        btnRetrieveRecords = findViewById(R.id.btnGetRecord);

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

        btnRetrieveRecords.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        RetrieveActivityListView.class);
                startActivity(i);

            }
        });

    }
}
