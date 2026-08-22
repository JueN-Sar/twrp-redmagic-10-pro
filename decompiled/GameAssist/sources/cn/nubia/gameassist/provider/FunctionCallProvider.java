package cn.nubia.gameassist.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class FunctionCallProvider extends ContentProvider {

    /* renamed from: c, reason: collision with root package name */
    private static final UriMatcher f7381c;

    /* renamed from: h, reason: collision with root package name */
    public static final Uri f7382h;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f7381c = uriMatcher;
        f7382h = Uri.parse("content://cn.nubia.gameassist.provider.FunctionCallProvider");
        uriMatcher.addURI("cn.nubia.gameassist.provider.FunctionCallProvider", "FunctionCallProvider", 0);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if ("function_call".equals(str) && bundle != null) {
            String string = bundle.getString("function_name", "");
            String string2 = bundle.getString("function_switch", "");
            String string3 = bundle.getString("function_value", "");
            GaLog.e("FunctionCallProvider", "call: functionName = " + string + " , functionSwitch = " + string2 + " , functionValue = " + string3 + " , package = " + str2);
            FunctionCallController.c(getContext()).d(string, string2, string3);
        }
        return super.call(str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
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
