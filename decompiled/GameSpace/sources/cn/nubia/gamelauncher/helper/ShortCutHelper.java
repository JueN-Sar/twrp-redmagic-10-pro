package cn.nubia.gamelauncher.helper;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.SortUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class ShortCutHelper {
    public static final boolean DEBUG = false;
    public static final String ID = "_id";
    public static final String SHORTCUT_AUTO_OPEN_FAN = "autoOpenFan";
    public static final String SHORTCUT_AUTO_OPEN_LIQUID = "autoOpenLiquid";
    public static final String SHORTCUT_COMPONENT = "component";
    public static final String SHORTCUT_FROM_APP = "fromApp";
    public static final String SHORTCUT_ID = "shortcutId";
    public static final String SHORTCUT_IMAGE_URL = "imageUrl";
    public static final String SHORTCUT_LABEL = "label";
    public static final String SHORTCUT_LABEL_HASH = "hashcode";
    public static final String SHORTCUT_LAST_START_TIME = "lastStartTime";
    public static final String SHORTCUT_LAST_UPDATE_URL_TIME = "lastUpdateUrlTime";
    public static final String SHORTCUT_MEDIUM_IMAGE_URL = "middleImageUrl";
    public static final String SHORTCUT_NET_URL = "netUrl";
    public static final Uri SHORTCUT_URI = ConstantVariable.SHORTCUT_URI;
    public static final String SHORTCUT_URL_TYPE = "urlType";
    public static final String SHORTCUT_WIDGET_URL = "widgetUrl";
    public static final String TAG = "SCH";
    public static final String WECHAT_PACKAGE = "com.tencent.mm";
    Context mContext;
    LauncherApps mLauncherApps;
    private final CopyOnWriteArrayList<AppListItemBean> mShortcutAddList;
    private final CopyOnWriteArrayList<AppListItemBean> mShortcutNotAddList;

    private static class ShortCutHelperHolder {
        public static final ShortCutHelper INSTANCE = new ShortCutHelper();

        private ShortCutHelperHolder() {
        }
    }

    private ShortCutHelper() {
        this.mLauncherApps = null;
        this.mShortcutAddList = new CopyOnWriteArrayList<>();
        this.mShortcutNotAddList = new CopyOnWriteArrayList<>();
        init();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0044, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
    
        if (r2 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getInsertId() {
        /*
            r9 = this;
            java.lang.String r0 = "getInsertId() e : "
            r1 = 1
            r2 = 0
            android.content.ContentResolver r3 = r9.getResolver()     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            android.net.Uri r4 = cn.nubia.gamelauncher.helper.ShortCutHelper.SHORTCUT_URI     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            java.lang.String r6 = "max(_id)"
            r7 = 0
            r8 = 0
            r5 = 0
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            if (r2 == 0) goto L22
            boolean r9 = r2.moveToNext()     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            if (r9 == 0) goto L22
            r9 = 0
            int r9 = r2.getInt(r9)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            int r1 = r1 + r9
        L22:
            if (r2 == 0) goto L44
        L24:
            r2.close()
            goto L44
        L28:
            r9 = move-exception
            goto L45
        L2a:
            r9 = move-exception
            java.lang.String r3 = "SCH"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L28
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L28
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L28
            java.lang.StringBuilder r9 = r4.append(r9)     // Catch: java.lang.Throwable -> L28
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L28
            android.util.Log.i(r3, r9)     // Catch: java.lang.Throwable -> L28
            if (r2 == 0) goto L44
            goto L24
        L44:
            return r1
        L45:
            if (r2 == 0) goto L4a
            r2.close()
        L4a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.helper.ShortCutHelper.getInsertId():int");
    }

    public static ShortCutHelper getInstance() {
        return ShortCutHelperHolder.INSTANCE;
    }

    private void init() {
        this.mContext = GameLauncherApplication.getAppContext();
        initLauncherApps();
    }

    private void sortListData() {
        Log.i(TAG, "sortListData())");
        SortUtil.sortByStartTime(this.mShortcutAddList);
    }

    private void verifyShortcutListFromDB(ArrayList<ShortcutInfo> arrayList) {
        ArrayList<ShortcutInfo> arrayList2 = arrayList;
        Cursor query = getResolver().query(SHORTCUT_URI, null, null, null, null);
        ArrayList<String> arrayList3 = new ArrayList<>();
        this.mShortcutAddList.clear();
        if (query == null) {
            return;
        }
        Log.i(TAG, "verifyShortcutListFromDB() list : " + (arrayList2 != null ? Integer.valueOf(arrayList.size()) : null));
        while (query.moveToNext()) {
            try {
                try {
                    String string = query.getString(query.getColumnIndex("imageUrl"));
                    String string2 = query.getString(query.getColumnIndex("urlType"));
                    String string3 = query.getString(query.getColumnIndex("netUrl"));
                    String string4 = query.getString(query.getColumnIndex("middleImageUrl"));
                    String string5 = query.getString(query.getColumnIndex("widgetUrl"));
                    String string6 = query.getString(query.getColumnIndex("component"));
                    String string7 = query.getString(query.getColumnIndex("label"));
                    ShortcutInfo findShortcutInfo = findShortcutInfo(query.getString(query.getColumnIndex(SHORTCUT_ID)), string7, arrayList2);
                    AppListItemBean appListItemBean = new AppListItemBean(string7, string6, string, string2, findShortcutInfo);
                    appListItemBean.setNetUrl(string3);
                    appListItemBean.setMediumUrl(string4);
                    appListItemBean.setWidgetUrl(string5);
                    if (findShortcutInfo == null) {
                        arrayList3.add(string7);
                    } else {
                        appListItemBean.setIcon(getShortcutBitmapIcon(findShortcutInfo));
                        appListItemBean.setShortcut(findShortcutInfo);
                        this.mShortcutAddList.add(appListItemBean);
                    }
                    arrayList2 = arrayList;
                } catch (Exception e) {
                    Log.i(TAG, "verifyShortcutListFromDB() e ==  " + e.getMessage());
                }
            } finally {
                query.close();
            }
        }
        fillNotAddList(arrayList);
        deleteShortcutFromDbOnWorkThread(arrayList3);
        Log.i(TAG, "verifyShortcutListFromDB mShortcutList ==  " + this.mShortcutAddList.size());
    }

    public AppListItemBean convertShortcutInfoToItem(ShortcutInfo shortcutInfo) {
        if (shortcutInfo == null) {
            return null;
        }
        AppListItemBean appListItemBean = new AppListItemBean(((CharSequence) Objects.requireNonNull(shortcutInfo.getShortLabel())).toString(), shortcutInfo.getPackage() + "," + shortcutInfo.getActivity().getClassName(), null, null);
        appListItemBean.setShortcut(shortcutInfo);
        appListItemBean.setIcon(getShortcutBitmapIcon(shortcutInfo));
        appListItemBean.setShortcut(shortcutInfo);
        return appListItemBean;
    }

    public ContentValues covertToAppAddContentValues(AppListItemBean appListItemBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(getInsertId()));
        contentValues.put(SHORTCUT_ID, appListItemBean.getShortcutId());
        contentValues.put("label", appListItemBean.getName());
        contentValues.put("hashcode", Integer.valueOf(appListItemBean.getName().hashCode()));
        contentValues.put("component", appListItemBean.getComponentName());
        contentValues.put("urlType", appListItemBean.getAtmosphere().getType());
        contentValues.put("imageUrl", appListItemBean.getAtmosphere().getCurrentUrl());
        contentValues.put("netUrl", appListItemBean.getAtmosphere().getNetUrl());
        contentValues.put("lastStartTime", Long.valueOf(appListItemBean.getLastStartTime()));
        contentValues.put("middleImageUrl", appListItemBean.getMediumUrl());
        contentValues.put("widgetUrl", appListItemBean.getWidgetUrl());
        contentValues.put("lastUpdateUrlTime", Long.valueOf(appListItemBean.getLastUpdateUrlTime()));
        Log.d(TAG, "-------------->covertToAppAddContentValues(" + appListItemBean.getName() + ") bean : " + appListItemBean);
        return contentValues;
    }

    public void deleteShortcutFromDb(String str) {
        if (str == null) {
            return;
        }
        Log.i(TAG, "deleteShortcutFromDb(" + str + ")");
        Log.i(TAG, "deleteShortcutFromDb(" + str + ") result : " + getResolver().delete(SHORTCUT_URI, "label=?", new String[]{str}));
    }

    public void deleteShortcutFromDbOnWorkThread(final ArrayList<String> arrayList) {
        Log.i(TAG, "deleteShortcutFromDbOnWorkThread())");
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.helper.ShortCutHelper$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ShortCutHelper.this.m314x351437ae(arrayList);
            }
        });
    }

    /* renamed from: deleteShortcutsFromDb, reason: merged with bridge method [inline-methods] */
    public void m314x351437ae(ArrayList<String> arrayList) {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            deleteShortcutFromDb(it.next());
        }
    }

    public void doShortcutCheckedAfterManagerGame(ShortcutInfo shortcutInfo, boolean z) {
        Log.i(TAG, "doShortcutCheckedAfterManagerGame(" + z + ") info : " + shortcutInfo);
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList = z ? this.mShortcutNotAddList : this.mShortcutAddList;
        CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList2 = z ? this.mShortcutAddList : this.mShortcutNotAddList;
        final AppListItemBean findItemFromList = findItemFromList(shortcutInfo, copyOnWriteArrayList);
        if (findItemFromList == null) {
            return;
        }
        copyOnWriteArrayList2.add(findItemFromList);
        copyOnWriteArrayList.remove(findItemFromList);
        findItemFromList.setSelect(z);
        WorkThread.runOnWorkThread(z ? new Runnable() { // from class: cn.nubia.gamelauncher.helper.ShortCutHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ShortCutHelper.this.m315x9fbd9e98(findItemFromList);
            }
        } : new Runnable() { // from class: cn.nubia.gamelauncher.helper.ShortCutHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ShortCutHelper.this.m316x3a5e6119(findItemFromList);
            }
        });
        sortListData();
    }

    public void fillNotAddList(ArrayList<ShortcutInfo> arrayList) {
        this.mShortcutNotAddList.clear();
        if (arrayList == null) {
            return;
        }
        Iterator<ShortcutInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            ShortcutInfo next = it.next();
            if (!isShortcutInAddList(next)) {
                String id = next.getId();
                ComponentName activity = next.getActivity();
                Intent intent = next.getIntent();
                PersistableBundle extras = next.getExtras();
                Log.d(TAG, "fillNotAddList(" + id + ") activity : " + activity + ", label : " + ((CharSequence) Objects.requireNonNull(next.getShortLabel())).toString() + ", intent : " + intent + ", extras : " + extras + ", componentName : " + (next.getPackage() + "," + next.getActivity().getClassName()));
                this.mShortcutNotAddList.add(convertShortcutInfoToItem(next));
            }
        }
        Log.d(TAG, "fillNotAddList() mShortcutNotAddList.size() : " + this.mShortcutNotAddList.size());
    }

    public AppListItemBean findItemByShortcutId(String str) {
        if (str == null) {
            return null;
        }
        Iterator<AppListItemBean> it = getShortcutAddList().iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (str.equals(next.getShortcutId())) {
                return next;
            }
        }
        Iterator<AppListItemBean> it2 = getShortcutNotAddList().iterator();
        while (it2.hasNext()) {
            AppListItemBean next2 = it2.next();
            if (str.equals(next2.getShortcutId())) {
                return next2;
            }
        }
        return null;
    }

    public AppListItemBean findItemFromList(ShortcutInfo shortcutInfo, CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        if (shortcutInfo == null) {
            return null;
        }
        Log.d(TAG, "findItemFromList(" + ((Object) shortcutInfo.getShortLabel()) + ") info Id : " + shortcutInfo.getId());
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next.isSameShortcut(shortcutInfo)) {
                return next;
            }
        }
        Log.d(TAG, "findItemFromList(" + ((Object) shortcutInfo.getShortLabel()) + ") not found ");
        return null;
    }

    public ShortcutInfo findShortcutInfo(String str, String str2, ArrayList<ShortcutInfo> arrayList) {
        if (str != null && str2 != null && !arrayList.isEmpty()) {
            Log.d(TAG, "findShortcutInfo(" + str2 + ") shortcutId : " + str);
            Iterator<ShortcutInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                ShortcutInfo next = it.next();
                String obj = ((CharSequence) Objects.requireNonNull(next.getShortLabel())).toString();
                if (next.getId().equals(str) && obj.equals(str2)) {
                    return next;
                }
            }
            Log.d(TAG, "findShortcutInfo(" + str2 + ") not found ");
        }
        return null;
    }

    public ActivityOptions getActivityOptions(int i) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchDisplayId(i);
        return makeBasic;
    }

    public ContentResolver getResolver() {
        return this.mContext.getContentResolver();
    }

    public CopyOnWriteArrayList<AppListItemBean> getShortcutAddList() {
        return this.mShortcutAddList;
    }

    public Bitmap getShortcutBitmapIcon(ShortcutInfo shortcutInfo) {
        Drawable shortcutIconDrawable = this.mLauncherApps.getShortcutIconDrawable(shortcutInfo, 0);
        return BitmapUtils.overlayBitmaps(BitmapUtils.convertDrawableToBitmap(shortcutIconDrawable), Util.getOriginalIcon("com.tencent.mm"), 0.33f);
    }

    public Drawable getShortcutIcon(ShortcutInfo shortcutInfo) {
        if (shortcutInfo != null) {
            return BitmapUtils.convertBitmapToDrawable(getShortcutBitmapIcon(shortcutInfo));
        }
        throw new IllegalArgumentException("ShortcutInfo cannot be null");
    }

    public ShortcutInfo getShortcutInfoByShortName(String str) {
        try {
            Cursor query = getResolver().query(SHORTCUT_URI, new String[]{"_id", SHORTCUT_ID, "label"}, "label=?", new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow(SHORTCUT_ID));
                        String string2 = query.getString(query.getColumnIndexOrThrow("label"));
                        Iterator<ShortcutInfo> it = listShortcutsFromLauncher().iterator();
                        while (it.hasNext()) {
                            ShortcutInfo next = it.next();
                            if (next.getId().equals(string) && Objects.equals(next.getShortLabel(), string2)) {
                                if (query != null) {
                                    query.close();
                                }
                                return next;
                            }
                        }
                    }
                } finally {
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            Log.e(TAG, "getShortcutInfoByShortName: ", e);
            return null;
        }
    }

    public CopyOnWriteArrayList<AppListItemBean> getShortcutNotAddList() {
        return this.mShortcutNotAddList;
    }

    void initLauncherApps() {
        Log.i(TAG, "initLauncherApps() - run())");
        try {
            this.mContext.getPackageName();
            LauncherApps launcherApps = (LauncherApps) this.mContext.getSystemService("launcherapps");
            this.mLauncherApps = launcherApps;
            Log.i(TAG, "initLauncherApps() - hasPermission : " + launcherApps.hasShortcutHostPermission());
        } catch (Exception e) {
            Log.e(TAG, "initLauncherApps() - e : " + e.getMessage());
        }
    }

    /* renamed from: insertShortcutToDb, reason: merged with bridge method [inline-methods] */
    public void m315x9fbd9e98(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return;
        }
        if (isShortcutExistInDB(appListItemBean.getShortcutInfo())) {
            m317x45db754e(appListItemBean);
            return;
        }
        Log.i(TAG, "insertShortcutToDb(" + appListItemBean.getName() + ") -> insert()");
        Log.i(TAG, "insertShortcutToDb(" + appListItemBean.getName() + ") -> insert() result : " + CommonUtil.getSecureUri(getResolver().insert(SHORTCUT_URI, covertToAppAddContentValues(appListItemBean))));
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005b, code lost:
    
        if (r3 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005d, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0089, code lost:
    
        android.util.Log.d(cn.nubia.gamelauncher.helper.ShortCutHelper.TAG, "isShortcutExistInDB(" + ((java.lang.Object) r12.getShortLabel()) + ") false ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a3, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0086, code lost:
    
        if (r3 == null) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean isShortcutExistInDB(android.content.pm.ShortcutInfo r12) {
        /*
            r11 = this;
            java.lang.String r0 = "isShortcutExistInDB("
            r1 = 0
            java.lang.String r2 = "SCH"
            if (r12 == 0) goto Laa
            java.lang.String r3 = r12.getId()
            if (r3 != 0) goto Lf
            goto Laa
        Lf:
            r3 = 0
            android.content.ContentResolver r4 = r11.getResolver()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            android.net.Uri r5 = cn.nubia.gamelauncher.helper.ShortCutHelper.SHORTCUT_URI     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r11 = 2
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r11 = "_id"
            r6[r1] = r11     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r11 = "shortcutId"
            r10 = 1
            r6[r10] = r11     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r7 = "shortcutId=?"
            java.lang.String[] r8 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r11 = r12.getId()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r8[r1] = r11     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r9 = 0
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            if (r3 == 0) goto L5b
            boolean r11 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            if (r11 == 0) goto L5b
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            r11.<init>(r0)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.CharSequence r4 = r12.getShortLabel()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.StringBuilder r11 = r11.append(r4)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r4 = ") true"
            java.lang.StringBuilder r11 = r11.append(r4)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            android.util.Log.d(r2, r11)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L63
            if (r3 == 0) goto L5a
            r3.close()
        L5a:
            return r10
        L5b:
            if (r3 == 0) goto L89
        L5d:
            r3.close()
            goto L89
        L61:
            r11 = move-exception
            goto La4
        L63:
            r11 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L61
            java.lang.CharSequence r5 = r12.getShortLabel()     // Catch: java.lang.Throwable -> L61
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = ") e : "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L61
            java.lang.String r11 = r11.getMessage()     // Catch: java.lang.Throwable -> L61
            java.lang.StringBuilder r11 = r4.append(r11)     // Catch: java.lang.Throwable -> L61
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> L61
            android.util.Log.d(r2, r11)     // Catch: java.lang.Throwable -> L61
            if (r3 == 0) goto L89
            goto L5d
        L89:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r0)
            java.lang.CharSequence r12 = r12.getShortLabel()
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = ") false "
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.d(r2, r11)
            return r1
        La4:
            if (r3 == 0) goto La9
            r3.close()
        La9:
            throw r11
        Laa:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "isShortcutExistInDB() info : "
            r11.<init>(r0)
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.d(r2, r11)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.helper.ShortCutHelper.isShortcutExistInDB(android.content.pm.ShortcutInfo):boolean");
    }

    public boolean isShortcutInAddList(ShortcutInfo shortcutInfo) {
        return findItemFromList(shortcutInfo, this.mShortcutAddList) != null;
    }

    /* renamed from: lambda$doShortcutCheckedAfterManagerGame$1$cn-nubia-gamelauncher-helper-ShortCutHelper, reason: not valid java name */
    /* synthetic */ void m316x3a5e6119(AppListItemBean appListItemBean) {
        deleteShortcutFromDb(appListItemBean.getName());
    }

    public ArrayList<ShortcutInfo> listShortcutsFromLauncher() {
        Bundle bundle;
        Log.d(TAG, "listShortcutsFromLauncher()");
        try {
            bundle = this.mContext.getContentResolver().call(CommonUtil.getSecureUri(Uri.parse(AppAddModel.DYNAMIC_SHOW_HIDDEN_APPS_URI)), "getWechatShortcut", (String) null, (Bundle) null);
        } catch (Exception e) {
            Log.e(TAG, "listShortcutsFromLauncher() e : " + e.getMessage());
            bundle = null;
        }
        Log.d(TAG, "listShortcutsFromLauncher() result : " + bundle);
        if (bundle == null) {
            return null;
        }
        ArrayList<ShortcutInfo> parcelableArrayList = bundle.getParcelableArrayList("shortcutInfoList");
        Log.d(TAG, "listShortcutsFromLauncher() shortcutList.size() : " + parcelableArrayList.size());
        return parcelableArrayList;
    }

    public void loadShortcutList() {
        Log.d(TAG, "loadShortcutList()");
        verifyShortcutListFromDB(listShortcutsFromLauncher());
        AppAddModel.getInstance().notifyChangedData();
        Log.d(TAG, "loadShortcutList(end) add count : " + this.mShortcutAddList.size() + ", not add count : " + this.mShortcutNotAddList.size());
    }

    public void onShortcutsChanged() {
        if (GameSpaceConfig.supportWechatShortcut()) {
            WorkThread.getHandler().postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.helper.ShortCutHelper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    ShortCutHelper.this.loadShortcutList();
                }
            }, 100L);
        }
    }

    public void startShortcut(ShortcutInfo shortcutInfo) {
        startShortcut(shortcutInfo, 0);
    }

    public void startShortcut(ShortcutInfo shortcutInfo, int i) {
        Log.d(TAG, "startShortcut() info : " + shortcutInfo.toString());
        Log.d(TAG, "startShortcut(" + shortcutInfo.getId() + ") activity : " + shortcutInfo.getActivity() + ", label : " + ((CharSequence) Objects.requireNonNull(shortcutInfo.getShortLabel())).toString() + ", componentName : " + (shortcutInfo.getPackage() + "," + shortcutInfo.getActivity().getClassName()) + ", displayId=" + i);
        this.mLauncherApps.startShortcut(shortcutInfo.getPackage(), shortcutInfo.getId(), null, getActivityOptions(i).toBundle(), shortcutInfo.getUserHandle());
    }

    public void updateShortcutInDB(ShortcutInfo shortcutInfo) {
        final AppListItemBean findItemFromList = findItemFromList(shortcutInfo, getShortcutAddList());
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.helper.ShortCutHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ShortCutHelper.this.m317x45db754e(findItemFromList);
            }
        });
    }

    /* renamed from: updateShortcutInDB, reason: merged with bridge method [inline-methods] */
    public void m317x45db754e(AppListItemBean appListItemBean) {
        Log.i(TAG, "updateShortcutInDB(" + appListItemBean.getName() + ") -> update()");
        Log.i(TAG, "updateShortcutInDB(" + appListItemBean.getName() + ") -> update() result : " + getResolver().update(SHORTCUT_URI, covertToAppAddContentValues(appListItemBean), "shortcutId=?", new String[]{appListItemBean.getShortcutId()}));
    }
}
