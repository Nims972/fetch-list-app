package com.example.fetchapp2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class FetchData extends AsyncTask<String, Void, String> {
    SparseArray<ExpandableList> groups = new SparseArray<ExpandableList>();
    private Activity activity;
    private ExpandableListView expView;
    public FetchData(Activity activity, ExpandableListView expView) {
        this.activity = activity;
        this.expView = expView;
    }
    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                result = response.toString();
            } else {
                result = "Error: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        ObjectMapper mapper = new ObjectMapper();
        List<ListItem> fetchedData=null;
        try {
            fetchedData = mapper.readValue(result, new TypeReference<List<ListItem>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Log.d("fetchdebug",String.valueOf(fetchedData));
        try {
            TreeMap<Integer, ArrayList<ListItem>> groupMap = new TreeMap<>();
            for (ListItem li : fetchedData) {
                if (li.getName() == null || li.getName().length() == 0) continue;
                if (groupMap.containsKey(li.getListId())) {
                    groupMap.get(li.getListId()).add(li);
                } else {
                    ArrayList<ListItem> ls = new ArrayList<>();
                    ls.add(li);
                    groupMap.put(li.getListId(), ls);
                }
            }
            int cnt=0;
            for (Map.Entry<Integer, ArrayList<ListItem>> entry : groupMap.entrySet()) {
                ArrayList<ListItem> ls=entry.getValue();
                Collections.sort(ls, new Comparator<ListItem>() {
                    @Override
                    public int compare(ListItem li1, ListItem li2) {
                        if(li1.getListId()==li2.getListId()) {
//                            String tm1[]=li1.getName().split(" ");
//                            String tm2[]=li2.getName().split(" ");
                            return li1.getItemNo().compareTo(li2.getItemNo());
//                            return Integer.valueOf(tm1[1]).compareTo(Integer.valueOf(tm2[1]));
//                            return li1.getName().compareTo(li2.getName());
                        } else
                            return li1.getListId().compareTo(li2.getListId());
                    }
                });
            }
            for (Map.Entry<Integer, ArrayList<ListItem>> entry : groupMap.entrySet()) {
                ExpandableList group = new ExpandableList("ListID " + entry.getKey());
                for (ListItem li : entry.getValue()) {
                    group.children.add(li.getName());
                }

                if(group.children!=null && group.children.size()>0)
                    groups.append(entry.getKey()-1, group);

            }

            ExpandableListAdapter adapter = new ExpandableListAdapter(activity, groups);
            expView.setAdapter(adapter);
        }catch (Exception e){
            Log.e("exception",e.getMessage());
        }

    }
}
