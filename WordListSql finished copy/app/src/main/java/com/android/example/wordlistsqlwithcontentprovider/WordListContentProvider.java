package com.android.example.wordlistsqlwithcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.android.example.wordlistsqlwithcontentprovider.Contract.ALL_ITEMS;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.CONTENT_URI;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.MULTIPLE_RECORDS_MIME_TYPE;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.SINGLE_RECORD_MIME_TYPE;
import static java.lang.Integer.parseInt;

/**
 * Created by reueljohn on 11/12/2017.
 */

public class WordListContentProvider extends ContentProvider {

    private static final String TAG = WordListContentProvider.class.getSimpleName();

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private WordListOpenHelper mDB;

    private static final int URI_ALL_ITEMS_CODE = 10;
    private static final int URI_ONE_ITEM_CODE = 20;
    private static final int URI_COUNT_CODE = 30;

    @Override
    public boolean onCreate() {
        mDB = new WordListOpenHelper(getContext());
        initializeUriMatching();
        return true;
    }

    private void initializeUriMatching(){
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, URI_ALL_ITEMS_CODE);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", URI_ONE_ITEM_CODE);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/" + Contract.COUNT, URI_COUNT_CODE );
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        Cursor cursor = null;

        // Determine integer code from the URI matcher and switch on it.
        switch (sUriMatcher.match(uri)) {
            case URI_ALL_ITEMS_CODE:
                cursor =  mDB.query(ALL_ITEMS);
                Log.d(TAG, "case all items " + cursor);
                break;
            case URI_ONE_ITEM_CODE:
                cursor =  mDB.query(parseInt(uri.getLastPathSegment()));
                Log.d(TAG, "case one item " + cursor);
                break;
            case URI_COUNT_CODE:
                cursor = mDB.count();
                Log.d(TAG, "case count " + cursor);
                break;
            case UriMatcher.NO_MATCH:
                // You should do some error handling here.
                Log.d(TAG, "NO MATCH FOR THIS URI IN SCHEME: " + uri);
                break;
            default:
                // You should do some error handling here.
                Log.d(TAG, "INVALID URI - URI NOT RECOGNIZED: "  + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case URI_ALL_ITEMS_CODE:
                return MULTIPLE_RECORDS_MIME_TYPE;
            case URI_ONE_ITEM_CODE:
                return SINGLE_RECORD_MIME_TYPE;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = mDB.insert(String.valueOf(contentValues));
        return Uri.parse(CONTENT_URI + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return mDB.delete(parseInt(strings[0]));
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return mDB.update(parseInt(strings[0]), contentValues.getAsString("word"));
    }
}
