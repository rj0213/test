package com.example.reueljohn.twoactivities2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    public static final String EXTRA_REPLY = "com.example.reueljohn.twoactivities2.extra.REPLY";

    private EditText mReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.text_message);
        textView.setText(message);

        mReply = (EditText) findViewById(R.id.editText_second);

        Log.d(LOG_TAG, "onCreate");


    }

    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");

    }

    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG,"onPause");
    }

    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG,"onResume");
    }

    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG,"onRestart");
    }

    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG,"onRestart");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
    }

    public void returnReply(View view){

        String reply = mReply.getText().toString();

        Intent replyIntent = new Intent();

        replyIntent.putExtra(EXTRA_REPLY, reply);

        setResult(RESULT_OK, replyIntent);

        Log.d(LOG_TAG, "End Second Activity");
        finish();
    }
}
