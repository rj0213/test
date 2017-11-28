package com.example.reueljohn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TOTAL_COUNT = "total_count";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastMe(View view){
        Toast myToast = Toast.makeText(this, "HELLO", Toast.LENGTH_SHORT);
        myToast.show();

    }

    public void countMe(View view){
        TextView showCountText = (TextView) findViewById(R.id.textView);

        String countString = showCountText.getText().toString();

        Integer count = Integer.parseInt(countString);
        count++;

        showCountText.setText(count.toString());
    }

    public void randomMe(View view){
        Intent randomIntent = new Intent(this, SecondActivity.class);

        TextView showCountText = (TextView) findViewById(R.id.textView);

        String countString = showCountText.getText().toString();

        int count = Integer.parseInt(countString);

        randomIntent.putExtra(TOTAL_COUNT, count);

        startActivity(randomIntent);
    }
}
