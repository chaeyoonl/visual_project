package com.example.myapplication_visual_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class weekactivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekactivity);
        setTitle("달력");

        baseLayout = (LinearLayout) findViewById(R.id.baseLayout);
        button1 = (Button) findViewById(R.id.button1);
        registerForContextMenu(button1);



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





    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //super.onOptionsItemSelected(item);

        Toast toast = Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG);

        switch (item.getItemId()) {
            case R.id.month:

                toast.setText("month");
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);

                break;



            case R.id.week:
                toast.setText("week");
                Intent homeIntents = new Intent(this, weekactivity.class);
                startActivity(homeIntents);

                break;
        }

        toast.show();

        return super.onOptionsItemSelected(item);
    }


}
