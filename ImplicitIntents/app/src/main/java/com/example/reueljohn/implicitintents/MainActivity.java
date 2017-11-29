package com.example.reueljohn.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = (EditText) findViewById(R.id.website_editText);
        mLocationEditText = (EditText) findViewById(R.id.location_editText);
        mShareEditText = (EditText) findViewById(R.id.share_editText);

    }

    public void openWebsite(View view){
        String url = mWebsiteEditText.getText().toString();

        Uri webpage = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents", "Can't handle this intent");
        }
    }

    public void openLocation(View view){

        String loc = mLocationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents", "Can't handle this intent");
        }
    }

    public void shareText(View view){

        String txt = mShareEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }
}
