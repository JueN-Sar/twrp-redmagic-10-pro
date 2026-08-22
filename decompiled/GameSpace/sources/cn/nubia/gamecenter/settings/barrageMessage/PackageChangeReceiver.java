package cn.nubia.gamecenter.settings.barrageMessage;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import cn.nubia.gamecenter.settings.utils.LogUtil;

/* loaded from: classes.dex */
public class PackageChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "PackageChangeReceiver";

    public void deleteSource(Context context, String str) {
        try {
            LogUtil.i(TAG, " delete " + str + " " + context.getContentResolver().delete(Uri.parse(BarrageMessageSourceActivity.BARRAGE_MESSAGE_SOURCE_URI_NOTIFY), "component = ?", new String[]{str}));
        } catch (Exception e) {
            LogUtil.wtf(TAG, e);
        }
    }

    public String getPkgNameByIntent(Intent intent) {
        return intent.getData().getEncodedSchemeSpecificPart();
    }

    public void insertSource(Context context, String str) {
        if (querySource(context, str)) {
            LogUtil.i(TAG, "already have " + str);
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("component", str);
        contentValues.put("isAdd", (Boolean) true);
        try {
            contentResolver.insert(Uri.parse(BarrageMessageSourceActivity.BARRAGE_MESSAGE_SOURCE_URI_NOTIFY), contentValues);
            LogUtil.i(TAG, "insert " + str);
        } catch (Exception e) {
            LogUtil.wtf(TAG, e);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.i(TAG, intent.getAction());
        String action = intent.getAction();
        action.hashCode();
        if (action.equals("android.intent.action.PACKAGE_ADDED")) {
            insertSource(context, getPkgNameByIntent(intent));
        } else if (action.equals("android.intent.action.PACKAGE_FULLY_REMOVED")) {
            deleteSource(context, getPkgNameByIntent(intent));
        }
    }

    public boolean querySource(Context context, String str) {
        LogUtil.i(TAG, "querySource component = ?," + str);
        Cursor query = context.getContentResolver().query(Uri.parse(BarrageMessageSourceActivity.BARRAGE_MESSAGE_SOURCE_URI_NOTIFY), null, "component = ?", new String[]{str}, null);
        if (query == null) {
            return false;
        }
        boolean moveToNext = query.moveToNext();
        query.close();
        return moveToNext;
    }
}
