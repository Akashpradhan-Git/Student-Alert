package com.example.bottom_nav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class TimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        PDFView b1=findViewById(R.id.pdfview);
        b1.fromAsset("pdf1.pdf").load();
    }
}
