package cn.nubia.gamelauncher.gamecontrolpanel.virtual.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.DatabaseHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.AppGameHandleItem;
import cn.nubia.gamelauncher.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GameHandleDbUtil {
    public static void deleteDbItem(ContentResolver contentResolver, String str) {
        contentResolver.delete(DBConstant.URI_APP_GAME_HANDLE, "_id=?", new String[]{str});
    }

    public static String getDataTitle(DatabaseHelper databaseHelper, String str, ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            return str + "_1";
        }
        for (int i = 1; i < arrayList.size() + 2; i++) {
            String str2 = str + "_" + i;
            if (!arrayList.contains(str2)) {
                return str2;
            }
        }
        return str + 0;
    }

    public static ArrayList<String> getDbItem(DatabaseHelper databaseHelper, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = databaseHelper.getWritableDatabase().query(DBConstant.TABLE_APP_GAME_HANDLE, new String[]{str}, null, null, null, null, null);
        if (query.moveToFirst()) {
            do {
                arrayList.add(query.getString(query.getColumnIndex(str)));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    private static String getTargetInfoString(AppGameHandleItem appGameHandleItem, int i) {
        AppGameHandleItem.TargetInfo targetInfo = appGameHandleItem.getTargetInfo(i);
        return targetInfo == null ? new AppGameHandleItem.TargetInfo().toString() : targetInfo.toString();
    }

    public static void insertDataToDb(DatabaseHelper databaseHelper, AppGameHandleItem appGameHandleItem) {
        SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        putContentValues(contentValues, appGameHandleItem);
        writableDatabase.insert(DBConstant.TABLE_APP_GAME_HANDLE, null, contentValues);
        writableDatabase.close();
    }

    public static List<AppGameHandleItem> parseDataFromDb(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null && cursor.getCount() != 0) {
            LogUtil.e("qn", "cursor.getCount() : " + cursor.getCount());
            cursor.moveToFirst();
            do {
                AppGameHandleItem appGameHandleItem = new AppGameHandleItem();
                appGameHandleItem.setId(cursor.getString(cursor.getColumnIndex("_id")));
                appGameHandleItem.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                appGameHandleItem.setPackageName(cursor.getString(cursor.getColumnIndex("package_name")));
                appGameHandleItem.setCutSize(cursor.getInt(cursor.getColumnIndex(DBConstant.CUT_SIZE)));
                appGameHandleItem.setRightGameHandleStyle(cursor.getString(cursor.getColumnIndex(DBConstant.RIGHT_GAME_HANDLE_STYLE)));
                appGameHandleItem.setType(cursor.getString(cursor.getColumnIndex("type")));
                appGameHandleItem.setImageUrl(cursor.getString(cursor.getColumnIndex(DBConstant.IMAGE_URL)));
                appGameHandleItem.setDefaultConfig(cursor.getInt(cursor.getColumnIndex(DBConstant.DEFAULT_CONFIG)));
                appGameHandleItem.setCurrentConfig(cursor.getInt(cursor.getColumnIndex(DBConstant.CURRENT_CONFIG)));
                appGameHandleItem.setTargetInfo(0, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LEFT_JOYSTICK))));
                appGameHandleItem.setTargetInfo(1, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.RIGHT_JOYSTICK))));
                appGameHandleItem.setTargetInfo(2, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LEFT_ENTITY_KEY))));
                appGameHandleItem.setTargetInfo(3, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.RIGHT_ENTITY_KEY))));
                appGameHandleItem.setTargetInfo(4, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LEFT_ARROW_KEY))));
                appGameHandleItem.setTargetInfo(5, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.RIGHT_ARROW_KEY))));
                appGameHandleItem.setTargetInfo(6, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.UP_ARROW_KEY))));
                appGameHandleItem.setTargetInfo(7, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.DOWN_ARROW_KEY))));
                appGameHandleItem.setTargetInfo(8, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LETTER_A_KEY))));
                appGameHandleItem.setTargetInfo(9, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LETTER_A1_KEY))));
                appGameHandleItem.setTargetInfo(10, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LETTER_A2_KEY))));
                appGameHandleItem.setTargetInfo(11, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LETTER_B_KEY))));
                appGameHandleItem.setTargetInfo(12, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LETTER_X_KEY))));
                appGameHandleItem.setTargetInfo(13, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LETTER_Y_KEY))));
                appGameHandleItem.setTargetInfo(14, Utils.parseTargetRect(cursor.getString(cursor.getColumnIndex(DBConstant.LETTER_Z_KEY))));
                arrayList.add(appGameHandleItem);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    private static void putContentValues(ContentValues contentValues, AppGameHandleItem appGameHandleItem) {
        contentValues.put("title", appGameHandleItem.getTitle());
        contentValues.put("package_name", appGameHandleItem.getPackageName());
        contentValues.put("type", appGameHandleItem.getType());
        contentValues.put(DBConstant.CUT_SIZE, Integer.valueOf(appGameHandleItem.getCutSize()));
        contentValues.put(DBConstant.LEFT_JOYSTICK, getTargetInfoString(appGameHandleItem, 0));
        contentValues.put(DBConstant.RIGHT_JOYSTICK, getTargetInfoString(appGameHandleItem, 1));
        contentValues.put(DBConstant.LEFT_ENTITY_KEY, getTargetInfoString(appGameHandleItem, 2));
        contentValues.put(DBConstant.RIGHT_ENTITY_KEY, getTargetInfoString(appGameHandleItem, 3));
        contentValues.put(DBConstant.LEFT_ARROW_KEY, getTargetInfoString(appGameHandleItem, 4));
        contentValues.put(DBConstant.RIGHT_ARROW_KEY, getTargetInfoString(appGameHandleItem, 5));
        contentValues.put(DBConstant.UP_ARROW_KEY, getTargetInfoString(appGameHandleItem, 6));
        contentValues.put(DBConstant.DOWN_ARROW_KEY, getTargetInfoString(appGameHandleItem, 7));
        contentValues.put(DBConstant.LETTER_A_KEY, getTargetInfoString(appGameHandleItem, 8));
        contentValues.put(DBConstant.LETTER_A1_KEY, getTargetInfoString(appGameHandleItem, 9));
        contentValues.put(DBConstant.LETTER_A2_KEY, getTargetInfoString(appGameHandleItem, 10));
        contentValues.put(DBConstant.LETTER_B_KEY, getTargetInfoString(appGameHandleItem, 11));
        contentValues.put(DBConstant.LETTER_X_KEY, getTargetInfoString(appGameHandleItem, 12));
        contentValues.put(DBConstant.LETTER_Y_KEY, getTargetInfoString(appGameHandleItem, 13));
        contentValues.put(DBConstant.LETTER_Z_KEY, getTargetInfoString(appGameHandleItem, 14));
        contentValues.put(DBConstant.RIGHT_GAME_HANDLE_STYLE, appGameHandleItem.getRightGameHandleStyle());
        contentValues.put(DBConstant.CURRENT_CONFIG, Integer.valueOf(appGameHandleItem.getCurrentConfig()));
        contentValues.put(DBConstant.IMAGE_URL, appGameHandleItem.getImageUrl());
    }

    public static void resetCurrentConfig(ContentResolver contentResolver, String str) {
        if (str == null || str == "") {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.CURRENT_CONFIG, "0");
        contentResolver.update(DBConstant.URI_APP_GAME_HANDLE, contentValues, "package_name=? and current_config= ?", new String[]{str, "1"});
    }

    public static void updateDataToDb(ContentResolver contentResolver, AppGameHandleItem appGameHandleItem, String str) {
        ContentValues contentValues = new ContentValues();
        putContentValues(contentValues, appGameHandleItem);
        if ("-1" != appGameHandleItem.getId()) {
            contentResolver.update(DBConstant.URI_APP_GAME_HANDLE, contentValues, "_id=?", new String[]{appGameHandleItem.getId()});
        } else {
            contentResolver.update(DBConstant.URI_APP_GAME_HANDLE, contentValues, "title=?", new String[]{str});
        }
    }
}
