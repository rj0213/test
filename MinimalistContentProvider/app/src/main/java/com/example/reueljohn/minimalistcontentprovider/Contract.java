package com.example.reueljohn.minimalistcontentprovider;

import android.net.Uri;

/**
 * Created by reueljohn on 08/12/2017.
 */

public final class Contract {

    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.provider.words";

    static final String MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.example.provider.words";

    private Contract(){

    }

    public static final String AUTHORITY = "com.android.example.minimalistcontentprovider.provider";

    public static final String CONTENT_PATH = "words";

    static final int ALL_ITEMS = -2;
    static final String WORD_ID = "id";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);


}
