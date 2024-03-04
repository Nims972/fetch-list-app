package com.example.fetchapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    SparseArray<ExpandableList> groups = new SparseArray<ExpandableList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        fetchData(listView);
    }

    public void fetchData(ExpandableListView expView) {
        new FetchData(MainActivity.this,expView).execute(getString(R.string.fetch_url));
    }

}