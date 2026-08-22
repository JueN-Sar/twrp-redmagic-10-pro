package cn.nubia.gamelauncher.gamecontrolpanel.virtual;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.DBConstant;
import cn.nubia.gamelauncher.gamecontrolpanel.virtual.db.Utils;
import cn.nubia.gamelauncher.util.LogUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String AUTHORITY = "cn.nubia.virtualgamehandle";
    private static final String CREATE_APP_GAME_HANDLE_TABLE = "CREATE TABLE IF NOT EXISTS app_game_handle (_id INTEGER PRIMARY KEY,title TEXT,package_name TEXT,type TEXT,cut_size INTEGER,left_joystick TEXT,right_joystick TEXT,left_entity_key TEXT,right_entity_key TEXT,left_arrow_key TEXT,right_arrow_key TEXT,up_arrow_key TEXT,down_arrow_key TEXT,letter_A_key TEXT,letter_A1_key TEXT,letter_A2_key TEXT,letter_B_key TEXT,letter_X_key TEXT,letter_Y_key TEXT,letter_Z_key TEXT,right_game_handle_style TEXT,image_url TEXT,default_config INTEGER,current_config INTEGER);";
    private static final String DATABASE_NAME = "gamehandle.db";
    private static final int DATABASE_VERSION = 8;
    private static final String DELETE_CONFIG = "delete from app_game_handle where type = 0";
    private static final String DROP_APP_GAME_HANDLE_TABLE = "DROP TABLE IF EXISTS app_game_handle";
    private static final String TABLE_APP_GAME_HANDLE = "app_game_handle";
    private static final String TAG = "DatabaseHelper";
    private static final Uri URI_APP_GAME_HANDLE = DBConstant.URI_APP_GAME_HANDLE;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 8);
        this.mContext = context;
    }

    private void getContentValues(String str, JSONObject jSONObject, ContentValues contentValues) {
        String optString = jSONObject.optString(str);
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        contentValues.put(str, optString);
    }

    public static String isCommonVersion() {
        try {
            return (String) Class.forName("android.util.NubiaConfig").getDeclaredMethod("getSubValue", new Class[0]).invoke("nb_virtual_game_handle", "json_name");
        } catch (Exception unused) {
            return null;
        }
    }

    private void loadConfig(SQLiteDatabase sQLiteDatabase) {
        String jsonData = getJsonData();
        if (TextUtils.isEmpty(jsonData)) {
            LogUtil.i(TAG, "no initial data");
            return;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(jsonData).getJSONArray("data");
            LogUtil.i(TAG, "loadConfig size=" + jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                ContentValues contentValues = new ContentValues();
                String optString = jSONObject.optString("title");
                if (!TextUtils.isEmpty(optString)) {
                    contentValues.put("title", optString);
                    String optString2 = jSONObject.optString("package_name");
                    if (!TextUtils.isEmpty(optString2)) {
                        if (Utils.isCommonVersion()) {
                            if (!arrayList.contains(optString2)) {
                                arrayList.add(optString2);
                            }
                            contentValues.put("package_name", optString2);
                        }
                    }
                    getContentValues(DBConstant.CUT_SIZE, jSONObject, contentValues);
                    getContentValues(DBConstant.LEFT_JOYSTICK, jSONObject, contentValues);
                    getContentValues(DBConstant.RIGHT_JOYSTICK, jSONObject, contentValues);
                    getContentValues(DBConstant.LEFT_ENTITY_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.RIGHT_ENTITY_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LEFT_ARROW_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.RIGHT_ARROW_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.UP_ARROW_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.DOWN_ARROW_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LETTER_A_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LETTER_A1_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LETTER_A2_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LETTER_B_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LETTER_X_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LETTER_Y_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.LETTER_Z_KEY, jSONObject, contentValues);
                    getContentValues(DBConstant.RIGHT_GAME_HANDLE_STYLE, jSONObject, contentValues);
                    getContentValues(DBConstant.IMAGE_URL, jSONObject, contentValues);
                    getContentValues(DBConstant.DEFAULT_CONFIG, jSONObject, contentValues);
                    if (contentValues.size() > 0) {
                        contentValues.put("type", "0");
                        sQLiteDatabase.insert("app_game_handle", null, contentValues);
                    }
                }
            }
            if (arrayList.size() > 0) {
                Utils.setOfficialRecPkg(this.mContext, TextUtils.join(",", arrayList));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateConfig(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DELETE_CONFIG);
        loadConfig(sQLiteDatabase);
    }

    public String getJsonData() {
        InputStreamReader inputStreamReader;
        String isCommonVersion = isCommonVersion();
        if (TextUtils.isEmpty(isCommonVersion)) {
            isCommonVersion = "default_config.json";
        }
        InputStreamReader inputStreamReader2 = null;
        try {
            try {
                inputStreamReader = new InputStreamReader(this.mContext.getResources().getAssets().open(isCommonVersion));
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    str = str + readLine;
                } else {
                    try {
                        break;
                    } catch (IOException e2) {
                        LogUtil.e(TAG, " IOException -- " + e2.toString());
                    }
                }
            }
            inputStreamReader.close();
            return str;
        } catch (Exception e3) {
            e = e3;
            inputStreamReader2 = inputStreamReader;
            e.printStackTrace();
            if (inputStreamReader2 != null) {
                try {
                    inputStreamReader2.close();
                } catch (IOException e4) {
                    LogUtil.e(TAG, " IOException -- " + e4.toString());
                }
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
            inputStreamReader2 = inputStreamReader;
            if (inputStreamReader2 != null) {
                try {
                    inputStreamReader2.close();
                } catch (IOException e5) {
                    LogUtil.e(TAG, " IOException -- " + e5.toString());
                }
            }
            throw th;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_APP_GAME_HANDLE_TABLE);
        loadConfig(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(DROP_APP_GAME_HANDLE_TABLE);
        onCreate(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 3) {
            updateConfig(sQLiteDatabase);
            return;
        }
        if (i == 4) {
            updateConfig(sQLiteDatabase);
            return;
        }
        if (i == 5) {
            updateConfig(sQLiteDatabase);
            return;
        }
        if (i == 6) {
            updateConfig(sQLiteDatabase);
        } else if (i == 7) {
            updateConfig(sQLiteDatabase);
        } else {
            sQLiteDatabase.execSQL(DROP_APP_GAME_HANDLE_TABLE);
            onCreate(sQLiteDatabase);
        }
    }
}
