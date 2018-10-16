package com.example.akabhi.autocompletetextview_with_costomadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private ArrayList<Pojo_Data> pojo_data;
    private CustomAdatper customAdatper;
    private Toolbar toolbar;
    String name[] = {"Shubham", "Atul", "Ayush", "Rupesh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCompleteTextView = findViewById(R.id.find_product);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("AutoCompleteTextView");

        //=========Adding Data To the ArrayList=====================================================
        pojo_data = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            Pojo_Data data = new Pojo_Data();
            data.string_data = name[i];
            pojo_data.add(data);
        }


        //Setting the adpater to the autocomplete textview==========================================
        customAdatper = new CustomAdatper(this, pojo_data);
        autoCompleteTextView.setAdapter(customAdatper);

    }
}
