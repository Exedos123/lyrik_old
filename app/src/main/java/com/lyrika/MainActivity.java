package com.lyrika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView mySearchView;
    ListView myListView;

    String [] myListofSongs = new String[]{
            "Song 1", "Song 2", "Song 3", "Song 4", "jSong 5", "Song 6", "Song 1", "Song 2", "Song 3", "Song 4", "Song 5", "Song 6"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mySearchView = (SearchView) findViewById(R.id.search_view);
        myListView = (ListView) findViewById(R.id.my_List_view);
        ArrayList<String> list = new ArrayList<>();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2,android.R.id.text1,myListofSongs);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Templistview1=myListofSongs[position].toString();
                Intent intent= new Intent(MainActivity.this,Song_View_page.class);
                intent.putExtra("ListClick",Templistview1);
                startActivities(new Intent[]{intent});
            }
        });

        myListView.setAdapter(adapter);

       mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s1) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s.toLowerCase());
                return false;
            }
        });



        setTitle("lyrika");
    }
}