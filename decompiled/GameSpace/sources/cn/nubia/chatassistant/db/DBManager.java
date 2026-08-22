package cn.nubia.chatassistant.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.nubia.chatassistant.util.LogUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class DBManager {
    private static DBManager mDBManager;
    private volatile SQLiteDatabase mDb;
    private DBOpenHelper mHelper;
    private final String TAG = "DBManager";
    private AtomicInteger mOpenCounter = new AtomicInteger();

    private DBManager(Context context) {
        this.mHelper = new DBOpenHelper(context);
        prepareWritableDB();
    }

    public static synchronized DBManager getInstance(Context context) {
        DBManager dBManager;
        synchronized (DBManager.class) {
            if (mDBManager == null) {
                mDBManager = new DBManager(context);
            }
            dBManager = mDBManager;
        }
        return dBManager;
    }

    private synchronized void prepareWritableDB() {
        if (this.mDb == null || this.mOpenCounter.incrementAndGet() == 1 || !this.mDb.isOpen()) {
            this.mDb = this.mHelper.getWritableDatabase();
        }
    }

    public int deleteByVoiceFileName(String str) {
        LogUtils.i("DBManager", "DBManager deleteByVoiceFileName voiceFileName: " + str);
        prepareWritableDB();
        return this.mDb.delete(DBOpenHelper.CHAT_ASSISTANT_VOICE, "voice_file_name = ?", new String[]{str});
    }

    public int deleteByVoicePackName(String str) {
        LogUtils.i("DBManager", "DBManager deleteByVoicePackName voicePackName: " + str);
        prepareWritableDB();
        return this.mDb.delete(DBOpenHelper.CHAT_ASSISTANT_VOICE, "voice_pack_name = ?", new String[]{str});
    }

    public void insertEventToDb(ChatAssistantBean chatAssistantBean) {
        LogUtils.i("DBManager", "DBManager insertEventToDb");
        try {
            try {
                prepareWritableDB();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBOpenHelper.VOICE_PACK_NAME, chatAssistantBean.voicePackName);
                contentValues.put(DBOpenHelper.VOICE_FILE_NAME, chatAssistantBean.voiceFileName);
                contentValues.put(DBOpenHelper.VOICE_FILE_PATH, chatAssistantBean.voiceFilePath);
                contentValues.put(DBOpenHelper.VOICE_FILE_TIME, Integer.valueOf(chatAssistantBean.voiceFileTime));
                contentValues.put(DBOpenHelper.VOICE_PACK_SHOW, Integer.valueOf(chatAssistantBean.voicePackShow));
                contentValues.put(DBOpenHelper.VOICE_PACK_POSITION, Integer.valueOf(chatAssistantBean.voicePackPosition));
                contentValues.put(DBOpenHelper.VOICE_PACK_SYSTEM, Integer.valueOf(chatAssistantBean.voicePackSystem));
                contentValues.put(DBOpenHelper.VOICE_SYSTEM, Integer.valueOf(chatAssistantBean.voiceSystem));
                LogUtils.i("DBManager", "DBManager add result : " + this.mDb.insert(DBOpenHelper.CHAT_ASSISTANT_VOICE, null, contentValues));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.mDb.close();
            this.mDb = null;
        }
    }

    public Cursor queryAllData() {
        LogUtils.i("DBManager", "DBManager queryAllData");
        prepareWritableDB();
        this.mDb.beginTransaction();
        Cursor cursor = null;
        try {
            try {
                cursor = this.mDb.rawQuery("select * from chat_assistant_voice", null);
                this.mDb.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        } finally {
            this.mDb.endTransaction();
        }
    }

    public Cursor queryAllVoicePackData() {
        LogUtils.i("DBManager", "DBManager queryAllVoicePackData");
        prepareWritableDB();
        this.mDb.beginTransaction();
        Cursor cursor = null;
        try {
            try {
                cursor = this.mDb.rawQuery("select * from chat_assistant_voice group by voice_pack_name", null);
                this.mDb.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        } finally {
            this.mDb.endTransaction();
        }
    }

    public Cursor queryAllVoicePackDataNoHide() {
        LogUtils.i("DBManager", "DBManager queryAllVoicePackDataNoHide");
        prepareWritableDB();
        this.mDb.beginTransaction();
        Cursor cursor = null;
        try {
            try {
                cursor = this.mDb.rawQuery("select * from chat_assistant_voice where voice_pack_show = \"0\" group by voice_pack_name", null);
                this.mDb.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        } finally {
            this.mDb.endTransaction();
        }
    }

    public Cursor queryDataByPosition(int i) {
        LogUtils.i("DBManager", "DBManager queryDataByPosition position: " + i);
        prepareWritableDB();
        this.mDb.beginTransaction();
        Cursor cursor = null;
        try {
            try {
                cursor = this.mDb.rawQuery("select * from chat_assistant_voice where voice_pack_position = \"" + i + "\"", null);
                this.mDb.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        } finally {
            this.mDb.endTransaction();
        }
    }

    public Cursor queryDataByVoicePackName(String str) {
        LogUtils.i("DBManager", "DBManager queryDataByVoicePackName voicePackName: " + str);
        prepareWritableDB();
        this.mDb.beginTransaction();
        Cursor cursor = null;
        try {
            try {
                cursor = this.mDb.rawQuery("select * from chat_assistant_voice where voice_pack_name = \"" + str + "\"", null);
                this.mDb.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        } finally {
            this.mDb.endTransaction();
        }
    }

    public Cursor queryVoiceForID(String str) {
        LogUtils.i("DBManager", "DBManager queryVoiceForID id: " + str);
        prepareWritableDB();
        this.mDb.beginTransaction();
        Cursor cursor = null;
        try {
            try {
                cursor = this.mDb.rawQuery("select * from chat_assistant_voice where _id = \"" + str + "\"", null);
                this.mDb.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cursor;
        } finally {
            this.mDb.endTransaction();
        }
    }

    public void updateVoicePack(ChatAssistantBean chatAssistantBean) {
        LogUtils.i("DBManager", "DBManager updateVoicePack voiceFileName: " + chatAssistantBean.voiceFileName);
        prepareWritableDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.VOICE_PACK_NAME, chatAssistantBean.voicePackName);
        this.mDb.update(DBOpenHelper.CHAT_ASSISTANT_VOICE, contentValues, "voice_file_name =?", new String[]{chatAssistantBean.voiceFileName + ""});
        contentValues.put(DBOpenHelper.VOICE_FILE_PATH, chatAssistantBean.voiceFilePath);
        this.mDb.update(DBOpenHelper.CHAT_ASSISTANT_VOICE, contentValues, "voice_file_name =?", new String[]{chatAssistantBean.voiceFileName + ""});
        contentValues.put(DBOpenHelper.VOICE_FILE_NAME, chatAssistantBean.voiceFileName);
        this.mDb.update(DBOpenHelper.CHAT_ASSISTANT_VOICE, contentValues, "voice_file_name =?", new String[]{chatAssistantBean.voiceFileName + ""});
    }

    public void updateVoicePackPosition(ChatAssistantBean chatAssistantBean) {
        LogUtils.i("DBManager", "DBManager updateVoicePackPosition voiceFileName: ");
        prepareWritableDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.VOICE_PACK_POSITION, Integer.valueOf(chatAssistantBean.voicePackPosition));
        this.mDb.update(DBOpenHelper.CHAT_ASSISTANT_VOICE, contentValues, "voice_pack_name =?", new String[]{chatAssistantBean.voicePackName + ""});
    }

    public void updateVoicePackState(ChatAssistantBean chatAssistantBean) {
        LogUtils.i("DBManager", "DBManager updateVoicePackState");
        prepareWritableDB();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.VOICE_PACK_SHOW, Integer.valueOf(chatAssistantBean.voicePackShow));
        this.mDb.update(DBOpenHelper.CHAT_ASSISTANT_VOICE, contentValues, "voice_pack_name =?", new String[]{chatAssistantBean.voicePackName + ""});
    }
}
