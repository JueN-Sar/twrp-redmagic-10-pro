package cn.nubia.gameassist.search;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import com.zte.gameassist.utils.GaLog;
import java.util.List;

/* loaded from: classes.dex */
public class GlobalSearchProvider extends ContentProvider {

    /* renamed from: i, reason: collision with root package name */
    private static final UriMatcher f7389i;

    /* renamed from: c, reason: collision with root package name */
    private SQLiteOpenHelper f7390c;

    /* renamed from: h, reason: collision with root package name */
    private Context f7391h;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f7389i = uriMatcher;
        uriMatcher.addURI("cn.nubia.gameassist.globalsearch", null, 0);
    }

    private String a() {
        return this.f7391h.getString(R.string.app_label);
    }

    private void b(String str, Bundle bundle) {
        Cursor query;
        Cursor cursor = null;
        String str2 = "";
        if (bundle != null) {
            try {
                try {
                    if (bundle.containsKey("help")) {
                        str2 = bundle.getString("help").substring(1);
                        GaLog.e("GameassistGlobalSearch", "help:" + str2);
                    }
                } catch (Exception e2) {
                    GaLog.k("GameassistGlobalSearch", "Exception " + e2.getMessage());
                    if (cursor == null) {
                        return;
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            query = query(GlobalSearchConstants.f7383a, null, "name =? ", new String[]{str}, null);
        } else {
            String a2 = a();
            query = str2.startsWith(a2) ? query(GlobalSearchConstants.f7383a, null, "name =? AND app_label =? ", new String[]{str, a2}, null) : query(GlobalSearchConstants.f7383a, null, "name =? AND app_label !=?", new String[]{str, a2}, null);
        }
        cursor = query;
        if (cursor != null && cursor.moveToFirst()) {
            ContentValues contentValues = new ContentValues();
            for (String str3 : GlobalSearchUtil.h()) {
                GlobalSearchUtil.w(str3, cursor.getString(cursor.getColumnIndexOrThrow(str3)), contentValues);
            }
            if (!contentValues.isEmpty()) {
                GlobalSearchUtil.l(this.f7391h, contentValues);
            }
        }
        if (cursor == null) {
            return;
        }
        cursor.close();
    }

    private void c(String str, Bundle bundle, Bundle bundle2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String lowerCase = str.replace("。", "").toLowerCase();
        GaLog.e("GameassistGlobalSearch", "OPERATION_QUERY " + lowerCase);
        if (bundle != null && bundle.containsKey("package_name")) {
            GaLog.e("GameassistGlobalSearch", "PACKAGE_NAME: " + bundle.getString("package_name"));
        }
        List<GlobalSearchUtil.SearchInfo> f2 = GlobalSearchUtil.f(this.f7391h);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (GlobalSearchUtil.SearchInfo searchInfo : f2) {
            if (i2 >= 3) {
                break;
            }
            if (e(searchInfo, lowerCase)) {
                if (!d(searchInfo.f7392a) || a().equals(searchInfo.f7394c)) {
                    sb.append(searchInfo.f7392a);
                    sb.append(":");
                    sb.append(searchInfo.f7394c);
                    sb.append("|");
                } else if (g()) {
                    sb.append(searchInfo.f7392a);
                    sb.append(":");
                    sb.append(searchInfo.f7394c);
                    sb.append("|");
                }
                i2++;
            }
        }
        if (sb.length() > 0) {
            bundle2.putString("search_result", sb.toString().substring(0, sb.length() - 1));
        }
        GaLog.e("GameassistGlobalSearch", "Search result: " + bundle2.getString("search_result") + " count " + i2);
    }

    private boolean d(String str) {
        return this.f7391h.getString(R.string.ic_qs_virtual_handle).equals(str);
    }

    private boolean e(GlobalSearchUtil.SearchInfo searchInfo, String str) {
        return searchInfo.f7392a.toLowerCase().contains(str) || str.contains(searchInfo.f7392a.toLowerCase()) || (!TextUtils.isEmpty(searchInfo.f7393b) && searchInfo.f7393b.contains(str)) || (!TextUtils.isEmpty(searchInfo.f7393b) && str.contains(searchInfo.f7393b));
    }

    private void f(Uri uri) {
        this.f7391h.getContentResolver().notifyChange(uri, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0059 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean g() {
        /*
            r8 = this;
            java.lang.String r0 = "GameassistGlobalSearch"
            r1 = 0
            android.content.Context r8 = r8.f7391h     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L27
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L27
            android.net.Uri r3 = cn.nubia.gameassist.search.GlobalSearchConstants.f7385c     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L27
            java.lang.String r5 = "package_name =? "
            java.lang.String r8 = com.zte.gameassist.common.SystemMgr.v()     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L27
            java.lang.String[] r6 = new java.lang.String[]{r8}     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L27
            r7 = 0
            r4 = 0
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L27
            if (r8 == 0) goto L42
            int r2 = r8.getCount()
            r8.close()
            goto L43
        L25:
            r8 = move-exception
            goto L29
        L27:
            r8 = move-exception
            goto L2a
        L29:
            throw r8
        L2a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Exception "
            r2.append(r3)
            java.lang.String r8 = r8.getMessage()
            r2.append(r8)
            java.lang.String r8 = r2.toString()
            com.zte.gameassist.utils.GaLog.b(r0, r8)
        L42:
            r2 = r1
        L43:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r3 = "count = "
            r8.append(r3)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            com.zte.gameassist.utils.GaLog.e(r0, r8)
            if (r2 <= 0) goto L5a
            r1 = 1
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.search.GlobalSearchProvider.g():boolean");
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        str.hashCode();
        if (str.equals("jump")) {
            b(str2, bundle);
        } else if (str.equals("query")) {
            c(str2, bundle, bundle2);
        } else {
            GaLog.b("GameassistGlobalSearch", "Unknown method: " + str);
        }
        return bundle2;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int delete = this.f7390c.getWritableDatabase().delete("table_global_search", str, strArr);
        if (delete > 0) {
            f(uri);
        }
        return delete;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/global_search";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        long insert = this.f7390c.getWritableDatabase().insert("table_global_search", null, contentValues);
        if (insert <= 0) {
            return null;
        }
        f(uri);
        return ContentUris.withAppendedId(uri, insert);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        this.f7391h = context;
        this.f7390c = GlobalSearchDatabaseHelper.d(context);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.f7390c.getReadableDatabase();
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("table_global_search");
        Cursor query = sQLiteQueryBuilder.query(readableDatabase, strArr, str, strArr2, null, null, str2);
        if (query != null) {
            query.setNotificationUri(this.f7391h.getContentResolver(), uri);
        }
        return query;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int update = this.f7390c.getWritableDatabase().update("table_global_search", contentValues, str, strArr);
        if (update > 0) {
            f(uri);
        }
        return update;
    }
}
