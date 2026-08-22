package com.zte.performanceindicator.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.zte.distbus.basetransfer.Constants;
import com.zte.performanceindicator.PerfIndicatorManager;

/* loaded from: classes2.dex */
public class PerformanceIndicatorProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        String str3;
        Bundle bundle2 = new Bundle();
        Log.d("PerformanceIndicatorProvider", " call: method=" + str + " callPkg=" + bundle.getString("package"));
        str.hashCode();
        if (str.equals("call_network_detect")) {
            PerfIndicatorManager.t().r();
            str3 = "true";
        } else {
            str3 = "false";
        }
        bundle2.putString(Constants.EXTRA_RESULT, str3);
        return bundle2;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
