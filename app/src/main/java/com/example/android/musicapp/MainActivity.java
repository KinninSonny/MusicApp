package com.example.android.musicapp;


import android.app.Activity;
import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    ListView lv;
    String items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lvPlayList);

        ArrayList<File> MySongs = findsongs(Environment.getExternalStorageDirectory());

        for (int i = 0; i < MySongs.size(); i++) {

            toast(MySongs.get(i).getName().toString());
        }

    }

    public ArrayList<File> findsongs(File root){

        ArrayList<File> al = new ArrayList<>();
        File[] files = root.listFiles();
        for(File singleFile : files){
            if (singleFile.isDirectory() && !singleFile.isHidden()){

                al.addAll(findsongs(singleFile));
            }else {
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")){

                    al.add(singleFile);
                }
            }
        }
        return al;
    }

    public void toast (String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}