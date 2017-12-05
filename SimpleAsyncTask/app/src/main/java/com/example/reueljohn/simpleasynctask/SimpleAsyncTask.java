package com.example.reueljohn.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by reueljohn on 05/12/2017.
 */

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    TextView mTextView;

    public SimpleAsyncTask(TextView tv){

        mTextView = tv;

    }

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        try{
            Thread.sleep(s);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return "Awake after " + s + "milliseconds";
    }

    protected void onPostExecute(String result){
        mTextView.setText(result);
    }


}
