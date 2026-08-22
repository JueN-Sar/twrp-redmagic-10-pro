package cn.nubia.globalsearch;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import cn.nubia.gamelauncher.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class GlobalSearchProvider extends ContentProvider {
    public static final String AUTHORITY = "cn.nubia.globalsearch.globalsearchprovider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://cn.nubia.globalsearch.globalsearchprovider");
    private static final String GAMEASSIST_PACKAGE_NAME = "cn.nubia.gameassist";
    private static final String INIT_GLOBAL_SEARCH = "initGlobalSearchList";
    private static final String TAG = "GlobalSearchProvider";
    private static final String UPDATE_GLOBAL_SEARCH = "updateGlobalSearchList";

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        LogUtil.i(TAG, str + " " + str2);
        Bundle bundle2 = null;
        if (!TextUtils.isEmpty(str) && GAMEASSIST_PACKAGE_NAME.equals(str2)) {
            ArrayList<ContentValues> parserXml = GlobalSearchUtil.parserXml(getContext(), UPDATE_GLOBAL_SEARCH.equals(str));
            if (parserXml.isEmpty()) {
                return null;
            }
            bundle2 = new Bundle();
            str.hashCode();
            if (str.equals(UPDATE_GLOBAL_SEARCH)) {
                ContentResolver contentResolver = getContext().getContentResolver();
                try {
                    Iterator<ContentValues> it = parserXml.iterator();
                    while (it.hasNext()) {
                        ContentValues next = it.next();
                        String asString = next.getAsString("status");
                        if (GlobalSearchConstants.UPDATE.equals(asString)) {
                            contentResolver.update(GlobalSearchConstants.GAMEASSIST_AUTHORITY_URI, next, "name = ?", new String[]{next.getAsString(GlobalSearchConstants.NAME)});
                        } else if (GlobalSearchConstants.ADD.equals(asString)) {
                            contentResolver.insert(GlobalSearchConstants.GAMEASSIST_AUTHORITY_URI, next);
                        } else if (GlobalSearchConstants.DELETE.equals(asString)) {
                            contentResolver.delete(GlobalSearchConstants.GAMEASSIST_AUTHORITY_URI, "name = ?", new String[]{next.getAsString(GlobalSearchConstants.NAME)});
                        }
                    }
                    bundle2.putString("package_name", "cn.nubia.gamelauncher");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (str.equals(INIT_GLOBAL_SEARCH)) {
                ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
                Iterator<ContentValues> it2 = parserXml.iterator();
                while (it2.hasNext()) {
                    arrayList.add(ContentProviderOperation.newInsert(GlobalSearchConstants.GAMEASSIST_AUTHORITY_URI).withValues(it2.next()).build());
                }
                try {
                    getContext().getContentResolver().applyBatch(GlobalSearchConstants.GAMEASSIST_AUTHORITY, arrayList);
                    bundle2.putString("package_name", "cn.nubia.gamelauncher");
                } catch (OperationApplicationException | RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
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
        return true;
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
