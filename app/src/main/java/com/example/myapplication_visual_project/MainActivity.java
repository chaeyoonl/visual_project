package com.example.myapplication_visual_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("달력");

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button1 = (Button) findViewById(R.id.button1);
        registerForContextMenu(button1);


        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        long selectedDate = calendarView.getDate();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();

        if ( v == button1) {
            //menu.setHeaderTitle("s");

            mInflater.inflate(R.menu.menu1, menu);
        }
    }



 /*


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        //mInflater.inflate(R.menu.menu1, menu);

        if ( v == button1) {
            menu.setHeaderTitle("s");

            mInflater.inflate(R.menu.menu1, menu);
        }


        //MenuInflater inflater = getMenuInflater();

        //inflater.inflate(R.menu.menu1, menu);
        return true;
    }



  */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.month:
                return true;
            case R.id.week:
                return true;
        }

        return false;
    }
}


