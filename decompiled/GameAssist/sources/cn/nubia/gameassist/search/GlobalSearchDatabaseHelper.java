package cn.nubia.gameassist.search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.onemorething.OneMoreThingManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class GlobalSearchDatabaseHelper extends SQLiteOpenHelper {

    /* renamed from: h, reason: collision with root package name */
    private static volatile GlobalSearchDatabaseHelper f7387h;

    /* renamed from: c, reason: collision with root package name */
    private Context f7388c;

    private GlobalSearchDatabaseHelper(Context context) {
        super(context, "global_search.db", (SQLiteDatabase.CursorFactory) null, 2);
        this.f7388c = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a() {
        /*
            r5 = this;
            java.lang.String r0 = "GameassistGlobalSearch"
            r1 = 0
            java.lang.String r2 = "delete start"
            com.zte.gameassist.utils.GaLog.e(r0, r2)     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
            android.database.sqlite.SQLiteDatabase r5 = r5.getWritableDatabase()     // Catch: java.lang.Throwable -> L1f java.lang.Exception -> L21
            java.lang.String r2 = "table_global_search"
            r5.delete(r2, r1, r1)     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1d
            java.lang.String r1 = "delete end"
            com.zte.gameassist.utils.GaLog.e(r0, r1)     // Catch: java.lang.Throwable -> L1a java.lang.Exception -> L1d
        L16:
            r5.close()
            goto L40
        L1a:
            r0 = move-exception
            r1 = r5
            goto L41
        L1d:
            r1 = move-exception
            goto L25
        L1f:
            r0 = move-exception
            goto L41
        L21:
            r5 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
        L25:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1a
            r2.<init>()     // Catch: java.lang.Throwable -> L1a
            java.lang.String r3 = "Exception: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L1a
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L1a
            r2.append(r1)     // Catch: java.lang.Throwable -> L1a
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L1a
            com.zte.gameassist.utils.GaLog.b(r0, r1)     // Catch: java.lang.Throwable -> L1a
            if (r5 == 0) goto L40
            goto L16
        L40:
            return
        L41:
            if (r1 == 0) goto L46
            r1.close()
        L46:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.search.GlobalSearchDatabaseHelper.a():void");
    }

    public static GlobalSearchDatabaseHelper d(Context context) {
        if (f7387h == null) {
            synchronized (GlobalSearchDatabaseHelper.class) {
                try {
                    if (f7387h == null) {
                        f7387h = new GlobalSearchDatabaseHelper(context.getApplicationContext());
                    }
                } finally {
                }
            }
        }
        return f7387h;
    }

    public void c(boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                GaLog.e("GameassistGlobalSearch", "delete start");
                sQLiteDatabase = getWritableDatabase();
                sQLiteDatabase.delete("table_global_search", z ? "app_label =?" : "app_label !=?", new String[]{this.f7388c.getString(R.string.app_label)});
                GaLog.e("GameassistGlobalSearch", "delete end");
            } catch (Exception e2) {
                GaLog.b("GameassistGlobalSearch", "Exception: " + e2.getMessage());
                if (sQLiteDatabase == null) {
                    return;
                }
            }
            sQLiteDatabase.close();
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    public void e() {
        a();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE table_global_search (name TEXT NOT NULL, help TEXT, start_type TEXT NOT NULL, app_label TEXT NOT NULL, view_id TEXT, package_name TEXT, class_name TEXT, action TEXT, category TEXT, intent_flag TEXT, feature TEXT, authorities TEXT, package_list TEXT, param_string TEXT, param_string1 TEXT, param_string2 TEXT, param_boolean TEXT, param_boolean1 TEXT, param_int TEXT, param_int1 TEXT);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        GaLog.e("GameassistGlobalSearch", "onUpgrade from " + i2 + " to " + i3);
        if (i2 < i3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS table_global_search");
            onCreate(sQLiteDatabase);
            GaLog.e("GameassistGlobalSearch", "onCreate db finish");
            GlobalSearchUtil.s(this.f7388c, "");
            GlobalSearchUtil.t(this.f7388c, "");
            OneMoreThingManager.g().m(false);
        }
    }
}
