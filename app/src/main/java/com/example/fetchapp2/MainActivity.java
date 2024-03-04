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
    // more efficient than HashMap for mapping integers to objects
    SparseArray<ExpandableList> groups = new SparseArray<ExpandableList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Log.d("123123","oncreate");
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
//        ExpandableListAdapter adapter = new ExpandableListAdapter(this, groups);
//        listView.setAdapter(adapter);
        fetchData(listView);
    }

    public void fetchData(ExpandableListView expView) {
        Log.d("123123","fetchdata");
        new FetchData(MainActivity.this,expView).execute("https://fetch-hiring.s3.amazonaws.com/hiring.json");
//        try{
//
//            HttpURLConnection urlConnection = null;
//
//            URL url = new URL("https://fetch-hiring.s3.amazonaws.com/hiring.json");
//
//            urlConnection = (HttpURLConnection) url.openConnection();
//
//            urlConnection.setRequestMethod("GET");
//            urlConnection.setReadTimeout(5000);
//            urlConnection.setConnectTimeout(10000);
//
//            urlConnection.setDoOutput(true);
//
//            urlConnection.connect();
//            Log.d("JSON:","jsonString");
//            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//
//            char[] buffer = new char[1024];
//
//            String jsonString = new String();
//
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            br.close();
//
//            jsonString = sb.toString();
//            Log.d("JSON:",jsonString);
//
//            urlConnection.disconnect();
//        } catch(Exception e) {
//            e.printStackTrace();
//            Log.d("ERROR:","error intry catch"+" " + e.getMessage());
//        }

//        for (int j = 0; j < 5; j++) {
//            ExpandableList group = new ExpandableList("Test " + j);
//            for (int i = 0; i < 5; i++) {
//                group.children.add("Sub Item" + i);
//            }
//            groups.append(j, group);
//        }
    }

}