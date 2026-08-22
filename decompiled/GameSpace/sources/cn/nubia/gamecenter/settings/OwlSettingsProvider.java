package cn.nubia.gamecenter.settings;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import cn.nubia.gamelauncher.util.GameKeysConstant;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class OwlSettingsProvider extends ContentProvider {
    private static final String ACQUIRE_EVENT_LIST = "acquire_event_list";
    private static final String PKG_NAME_GAME_SPACE = "cn.nubia.gamelauncher";
    private static final String PKG_NAME_SETTINGS = "com.android.settings";
    private static ArrayList<DataEntity> REPORT_DATAS = null;
    private static final String REPORT_DEFAULT_VALUE = "default_value";
    private static final String REPORT_EVENT_KEY = "event_key";
    private static final String REPORT_EVENT_NAME = "event_name";
    private static final String REPORT_PKG_NAME = "package_name";
    private static final String REPORT_SETTINGS_TABLE = "settings_type";
    private static final String REPORT_VALUE_TYPE = "value_type";
    private static final String SELF_PID = "self_pid";
    private static final String SELF_UPGRADE = "is_self_upgrade";
    private static final String SETTINGS_GLOBAL_TABLE = "global";
    private static final String SETTINGS_SECURE_TABLE = "secure";
    private static final String SETTINGS_SYSTEM_TABLE = "system";
    private static final String TYPE_FLOAT = "float";
    private static final String TYPE_INT = "int";
    private static final String TYPE_LONG = "long";
    private static final String TYPE_STRING = "string";

    static class DataEntity {
        public String defaultValue;
        public String eventKey;
        public String eventName;
        public String packageName;
        public String settingsType;
        public String valueType;

        public DataEntity(String str, String str2, String str3, String str4, String str5) {
            this(OwlSettingsProvider.PKG_NAME_SETTINGS, str, str2, str3, str4, str5);
        }

        public DataEntity(String str, String str2, String str3, String str4, String str5, String str6) {
            this.packageName = str;
            this.eventName = str2;
            this.eventKey = str3;
            this.valueType = str4;
            this.defaultValue = str5;
            this.settingsType = str6;
        }
    }

    static {
        ArrayList<DataEntity> arrayList = new ArrayList<>();
        REPORT_DATAS = arrayList;
        arrayList.add(new DataEntity("cn.nubia.gamelauncher", "pers_center_basic_calling_float_status", "phone_call_floating_window", TYPE_INT, "1", SETTINGS_SYSTEM_TABLE));
        REPORT_DATAS.add(new DataEntity("cn.nubia.gamelauncher", "pers_center_basic_display_in_gamebox_status", "switch_hide_games_icon", TYPE_INT, "0", SETTINGS_GLOBAL_TABLE));
        REPORT_DATAS.add(new DataEntity("cn.nubia.gamelauncher", "pers_center_basic_health_reminder_status", GameKeysConstant.DB_GAME_TIME_REMIND, TYPE_INT, "1", SETTINGS_GLOBAL_TABLE));
        REPORT_DATAS.add(new DataEntity("cn.nubia.gamelauncher", "pers_center_start_animation_status", GameKeysConstant.DB_GAME_SPACE_START_ANIM, TYPE_INT, "1", SETTINGS_GLOBAL_TABLE));
    }

    private Bundle getDataBundle(DataEntity dataEntity) {
        Bundle bundle = new Bundle();
        bundle.putString("package_name", dataEntity.packageName);
        bundle.putString("event_name", dataEntity.eventName);
        bundle.putString(REPORT_EVENT_KEY, dataEntity.eventKey);
        bundle.putString(REPORT_VALUE_TYPE, dataEntity.valueType);
        bundle.putString(REPORT_DEFAULT_VALUE, dataEntity.defaultValue);
        bundle.putString(REPORT_SETTINGS_TABLE, dataEntity.settingsType);
        return bundle;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        Iterator<DataEntity> it = REPORT_DATAS.iterator();
        while (it.hasNext()) {
            DataEntity next = it.next();
            Bundle bundle2 = new Bundle();
            bundle2.putString("package_name", next.packageName);
            bundle2.putString("event_name", next.eventName);
            bundle2.putString(REPORT_EVENT_KEY, next.eventKey);
            bundle2.putString(REPORT_VALUE_TYPE, next.valueType);
            bundle2.putString(REPORT_DEFAULT_VALUE, next.defaultValue);
            bundle2.putString(REPORT_SETTINGS_TABLE, next.settingsType);
            arrayList.add(bundle2);
        }
        Bundle bundle3 = new Bundle();
        bundle3.putInt(SELF_PID, Process.myPid());
        bundle3.putString(SELF_UPGRADE, "0");
        bundle3.putParcelableArrayList(ACQUIRE_EVENT_LIST, arrayList);
        return bundle3;
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
