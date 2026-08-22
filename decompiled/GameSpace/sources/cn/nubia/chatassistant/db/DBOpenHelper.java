package cn.nubia.chatassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.nubia.chatassistant.util.LogUtils;

/* loaded from: classes.dex */
public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String CHAT_ASSISTANT_VOICE = "chat_assistant_voice";
    private static final String DB_NAME = "chatAssistantVoice.db";
    public static final String ID = "_id";
    private static final int VERSION = 1;
    public static final String VOICE_FILE_NAME = "voice_file_name";
    public static final String VOICE_FILE_PATH = "voice_file_path";
    public static final String VOICE_FILE_TIME = "voice_file_time";
    public static final String VOICE_PACK_NAME = "voice_pack_name";
    public static final String VOICE_PACK_POSITION = "voice_pack_position";
    public static final String VOICE_PACK_SHOW = "voice_pack_show";
    public static final String VOICE_PACK_SYSTEM = "voice_pack_system";
    public static final String VOICE_SYSTEM = "voice_system";
    private final String TAG;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.TAG = "DBOpenHelper";
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists chat_assistant_voice (_id integer primary key autoincrement, voice_pack_name text, voice_file_name text,voice_pack_system integer not null,voice_system integer not null,voice_file_path text,voice_file_time integer not null,voice_pack_show integer not null,voice_pack_position integer not null)");
        LogUtils.i("DBOpenHelper", "onCreate");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtils.i("DBOpenHelper", "onUpgrade");
    }
}
