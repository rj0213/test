package com.example.reueljohn.whowroteitloader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by reueljohn on 05/12/2017.
 */

public class BookLoader extends AsyncTaskLoader<String> {

    String mQueryString;

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    public BookLoader(Context context, String queryString) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }
}
