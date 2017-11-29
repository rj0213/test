package com.example.reueljohn.twoactivities2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.reueljohn.twoactivities2.extra.MESSAGE";

    public static final int TEXT_REQUEST = 1;

    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;

    private EditText mMessageEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = (EditText) findViewById(R.id.editText_main);

        mReplyHeadTextView = (TextView) findViewById(R.id.text_header_reply);
        mReplyTextView = (TextView) findViewById(R.id.text_message_reply);

        Log.d(LOG_TAG,"...");
        Log.d(LOG_TAG, "onCreate");

        if(savedInstanceState !=null) {
            boolean isVisible =savedInstanceState.getBoolean("reply_visible");
            if(isVisible){
                mReplyHeadTextView.setVisibility(View.VISIBLE);

                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void onSavedInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        if(mReplyHeadTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", mReplyTextView.getText().toString());
        }
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

    public void launchSecondActivity(View view){

        Intent intent = new Intent(this, SecondActivity.class);

        String message = mMessageEditText.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, message);

        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEXT_REQUEST){
            if (resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
