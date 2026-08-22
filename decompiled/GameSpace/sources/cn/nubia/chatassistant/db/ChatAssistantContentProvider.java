package cn.nubia.chatassistant.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import cn.nubia.chatassistant.ChatAssistantService;
import cn.nubia.chatassistant.util.AssetsUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ChatAssistantContentProvider extends ContentProvider {
    private static final String KING = "王者风";
    private static final String PEACE = "和平风";
    private static final String RED_MAGIC = "红魔姬";
    public static final int TABLE_QUERY_ALL_VOICE = 2;
    public static final int TABLE_QUERY_ALL_VOICE_PACK = 0;
    public static final int TABLE_QUERY_VOICE = 1;
    private static UriMatcher uriMatcher;
    public Context mContext = null;

    static {
        UriMatcher uriMatcher2 = new UriMatcher(-1);
        uriMatcher = uriMatcher2;
        uriMatcher2.addURI("cn.nubia.chatassistant.db.chatassist", "voicePack", 0);
        uriMatcher.addURI("cn.nubia.chatassistant.db.chatassist", "voice", 1);
        uriMatcher.addURI("cn.nubia.chatassistant.db.chatassist", "allVoice", 2);
    }

    private void setChatAssistantVoiceData(List<String> list, List<List> list2) {
        if (list == null || list2 == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
            chatAssistantBean.voicePackPosition = i;
            String[] split = list.get(i).split("_", 2);
            if ("redmagic".equals(split[1])) {
                split[1] = RED_MAGIC;
            } else if ("king".equals(split[1])) {
                split[1] = KING;
            } else if ("peace".equals(split[1])) {
                split[1] = PEACE;
            }
            arrayList.add(split[1]);
            List<Map<String, Object>> itemTitle = AssetsUtils.getItemTitle(i, list.get(i), i < ChatAssistantService.ASSETS_FILE_COUNT, this.mContext);
            for (int i2 = 0; i2 < itemTitle.size(); i2++) {
                chatAssistantBean.voicePackName = (String) arrayList.get(i);
                chatAssistantBean.voiceFileName = (String) itemTitle.get(i2).get("title");
                chatAssistantBean.voiceFilePath = (String) itemTitle.get(i2).get("path");
                DBManager.getInstance(this.mContext).insertEventToDb(chatAssistantBean);
            }
            i++;
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mContext = getContext();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0014, code lost:
    
        if (r7.moveToNext() == false) goto L9;
     */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.database.Cursor query(android.net.Uri r4, java.lang.String[] r5, java.lang.String r6, java.lang.String[] r7, java.lang.String r8) {
        /*
            r3 = this;
            r5 = 0
            android.content.Context r7 = r3.mContext     // Catch: java.lang.Throwable -> L8c
            cn.nubia.chatassistant.db.DBManager r7 = cn.nubia.chatassistant.db.DBManager.getInstance(r7)     // Catch: java.lang.Throwable -> L8c
            android.database.Cursor r7 = r7.queryAllVoicePackData()     // Catch: java.lang.Throwable -> L8c
            java.lang.Class<cn.nubia.chatassistant.db.ChatAssistantContentProvider> r8 = cn.nubia.chatassistant.db.ChatAssistantContentProvider.class
            monitor-enter(r8)     // Catch: java.lang.Throwable -> L89
            if (r7 == 0) goto L16
            boolean r0 = r7.moveToNext()     // Catch: java.lang.Throwable -> L86
            if (r0 != 0) goto L3e
        L16:
            android.content.Context r0 = r3.mContext     // Catch: java.lang.Throwable -> L86
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L86
            java.lang.String r1 = "played_voice_pack"
            r2 = 0
            android.provider.Settings.Global.putInt(r0, r1, r2)     // Catch: java.lang.Throwable -> L86
            android.content.Context r0 = r3.mContext     // Catch: java.lang.Throwable -> L86
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L86
            java.lang.String r1 = "played_voice"
            android.provider.Settings.Global.putInt(r0, r1, r2)     // Catch: java.lang.Throwable -> L86
            android.content.Context r0 = r3.mContext     // Catch: java.lang.Throwable -> L86
            java.util.List r0 = cn.nubia.chatassistant.util.AssetsUtils.getFistTitle(r0)     // Catch: java.lang.Throwable -> L86
            android.content.Context r1 = r3.mContext     // Catch: java.lang.Throwable -> L86
            java.util.List r1 = cn.nubia.chatassistant.util.AssetsUtils.getContentItemTitle(r1)     // Catch: java.lang.Throwable -> L86
            r3.setChatAssistantVoiceData(r0, r1)     // Catch: java.lang.Throwable -> L86
        L3e:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L86
            if (r7 == 0) goto L49
            r7.close()     // Catch: java.lang.Exception -> L45
            goto L49
        L45:
            r7 = move-exception
            r7.printStackTrace()
        L49:
            java.lang.String r4 = r4.toString()
            int r7 = r4.length()
            r8 = 1
            int r7 = r7 - r8
            java.lang.String r4 = r4.substring(r7)
            int r4 = java.lang.Integer.parseInt(r4)
            if (r4 == 0) goto L7b
            if (r4 == r8) goto L6e
            r6 = 2
            if (r4 == r6) goto L63
            goto L85
        L63:
            android.content.Context r3 = r3.mContext
            cn.nubia.chatassistant.db.DBManager r3 = cn.nubia.chatassistant.db.DBManager.getInstance(r3)
            android.database.Cursor r5 = r3.queryAllData()
            goto L85
        L6e:
            if (r6 == 0) goto L85
            android.content.Context r3 = r3.mContext
            cn.nubia.chatassistant.db.DBManager r3 = cn.nubia.chatassistant.db.DBManager.getInstance(r3)
            android.database.Cursor r5 = r3.queryDataByVoicePackName(r6)
            goto L85
        L7b:
            android.content.Context r3 = r3.mContext
            cn.nubia.chatassistant.db.DBManager r3 = cn.nubia.chatassistant.db.DBManager.getInstance(r3)
            android.database.Cursor r5 = r3.queryAllVoicePackDataNoHide()
        L85:
            return r5
        L86:
            r3 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L86
            throw r3     // Catch: java.lang.Throwable -> L89
        L89:
            r3 = move-exception
            r5 = r7
            goto L8d
        L8c:
            r3 = move-exception
        L8d:
            if (r5 == 0) goto L97
            r5.close()     // Catch: java.lang.Exception -> L93
            goto L97
        L93:
            r4 = move-exception
            r4.printStackTrace()
        L97:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.chatassistant.db.ChatAssistantContentProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
