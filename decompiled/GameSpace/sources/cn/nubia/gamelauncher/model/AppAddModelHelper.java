package cn.nubia.gamelauncher.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.GameItemBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.db.AppAddProvider;
import cn.nubia.gamelauncher.helper.ShowNotificationManager;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class AppAddModelHelper {
    public static final long MIN_INTERVAL = 43200000;
    private int mMaxIdInAppAddTable = 1;
    private int mMaxIdInUserRemoveTable = 1;
    private int mMaxIdInVerifiedAppsTable = 1;
    private ShowNotificationManager mShowNotificationManager = null;
    private ArrayList<String> mVerifiedAppsList = null;

    private int generateNewAppAddId() {
        int i = this.mMaxIdInAppAddTable + 1;
        this.mMaxIdInAppAddTable = i;
        return i;
    }

    private int generateNewUserRemoveId() {
        int i = this.mMaxIdInUserRemoveTable + 1;
        this.mMaxIdInUserRemoveTable = i;
        return i;
    }

    private int generateNewVerifiedAppsId() {
        int i = this.mMaxIdInVerifiedAppsTable + 1;
        this.mMaxIdInVerifiedAppsTable = i;
        return i;
    }

    private void initVerifiedAppsList() {
        this.mVerifiedAppsList = new ArrayList<>();
        try {
            Cursor query = GameLauncherApplication.CONTEXT.getContentResolver().query(ConstantVariable.VERIFIED_URI, null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            }
            try {
                int columnIndex = query.getColumnIndex("component");
                new ArrayList();
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    this.mVerifiedAppsList.add(CommonUtil.convertPackageName(query.getString(columnIndex)));
                }
                query.close();
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception e) {
            Log.d("AppAddModelHelper", "initVerifiedAppsList() e :" + e);
        }
    }

    private boolean isIntervalLessThanOneDay(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return true;
        }
        long lastUpdateUrlTime = appListItemBean.getLastUpdateUrlTime();
        long currentTimeMillis = System.currentTimeMillis();
        Log.d(AppAddModel.TAG, "------>isIntervalLessThanOneDay(e) pkg : " + appListItemBean.getComponentName() + ", last : " + lastUpdateUrlTime + ", curr : " + currentTimeMillis + ", MIN_INTERVAL : 43200000");
        return currentTimeMillis - lastUpdateUrlTime > MIN_INTERVAL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyLauncherNotification(int i, String str) {
        LogUtil.d("notify", "notifyLauncherNotification() appName : " + str);
        if (!cn.nubia.common.util.CommonUtil.isSetupComplete()) {
            LogUtil.d("notify", "notifyLauncherNotification(" + str + ") setup not complete!");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putString("appName", str);
        try {
            if (this.mShowNotificationManager == null) {
                this.mShowNotificationManager = new ShowNotificationManager(GameLauncherApplication.CONTEXT);
            }
            this.mShowNotificationManager.showAppMoveToGameSpace(i, str, GameLauncherApplication.CONTEXT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateGameInDb, reason: merged with bridge method [inline-methods] */
    public boolean m322x601bdf3d(AppListItemBean appListItemBean) {
        if (appListItemBean == null) {
            return false;
        }
        ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
        return componentExistInAppAddDB(appListItemBean.getComponentName(), contentResolver) && contentResolver.update(ConstantVariable.APPADD_URI, covertToAppAddContentValues(appListItemBean), "component=?", new String[]{appListItemBean.getComponentName()}) > 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
    
        if (r0 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        if (r0 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean componentExistInAppAddDB(java.lang.String r9, android.content.ContentResolver r10) {
        /*
            r8 = this;
            r8 = 0
            r0 = 0
            android.net.Uri r2 = cn.nubia.gamelauncher.commoninterface.ConstantVariable.APPADD_URI     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r1 = 2
            java.lang.String[] r3 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r1 = "_id"
            r3[r8] = r1     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r1 = "component"
            r7 = 1
            r3[r7] = r1     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r4 = "component=?"
            java.lang.String[] r5 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r5[r8] = r9     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r6 = 0
            r1 = r10
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r0 == 0) goto L2a
            boolean r9 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r9 == 0) goto L2a
            if (r0 == 0) goto L29
            r0.close()
        L29:
            return r7
        L2a:
            if (r0 == 0) goto L38
            goto L35
        L2d:
            r8 = move-exception
            goto L39
        L2f:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L2d
            if (r0 == 0) goto L38
        L35:
            r0.close()
        L38:
            return r8
        L39:
            if (r0 == 0) goto L3e
            r0.close()
        L3e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.model.AppAddModelHelper.componentExistInAppAddDB(java.lang.String, android.content.ContentResolver):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
    
        if (r0 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        if (r0 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean componentExistInUserRemoveDB(java.lang.String r9, android.content.ContentResolver r10) {
        /*
            r8 = this;
            r8 = 0
            r0 = 0
            android.net.Uri r2 = cn.nubia.gamelauncher.commoninterface.ConstantVariable.USER_ROMOVE_URI     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r1 = 2
            java.lang.String[] r3 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r1 = "_id"
            r3[r8] = r1     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r1 = "component"
            r7 = 1
            r3[r7] = r1     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r4 = "component=?"
            java.lang.String[] r5 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r5[r8] = r9     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r6 = 0
            r1 = r10
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r0 == 0) goto L2a
            boolean r9 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r9 == 0) goto L2a
            if (r0 == 0) goto L29
            r0.close()
        L29:
            return r7
        L2a:
            if (r0 == 0) goto L38
            goto L35
        L2d:
            r8 = move-exception
            goto L39
        L2f:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L2d
            if (r0 == 0) goto L38
        L35:
            r0.close()
        L38:
            return r8
        L39:
            if (r0 == 0) goto L3e
            r0.close()
        L3e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.model.AppAddModelHelper.componentExistInUserRemoveDB(java.lang.String, android.content.ContentResolver):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
    
        if (r0 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        if (r0 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean componentExistInVerifiedAppsDB(java.lang.String r9, android.content.ContentResolver r10) {
        /*
            r8 = this;
            r8 = 0
            r0 = 0
            android.net.Uri r2 = cn.nubia.gamelauncher.commoninterface.ConstantVariable.VERIFIED_URI     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r1 = 2
            java.lang.String[] r3 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r1 = "_id"
            r3[r8] = r1     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r1 = "component"
            r7 = 1
            r3[r7] = r1     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            java.lang.String r4 = "component=?"
            java.lang.String[] r5 = new java.lang.String[r7]     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r5[r8] = r9     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            r6 = 0
            r1 = r10
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r0 == 0) goto L2a
            boolean r9 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L2f
            if (r9 == 0) goto L2a
            if (r0 == 0) goto L29
            r0.close()
        L29:
            return r7
        L2a:
            if (r0 == 0) goto L38
            goto L35
        L2d:
            r8 = move-exception
            goto L39
        L2f:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L2d
            if (r0 == 0) goto L38
        L35:
            r0.close()
        L38:
            return r8
        L39:
            if (r0 == 0) goto L3e
            r0.close()
        L3e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.model.AppAddModelHelper.componentExistInVerifiedAppsDB(java.lang.String, android.content.ContentResolver):boolean");
    }

    public ArrayList<AppListItemBean> convertToAppAddList(ArrayList<AppListItemBean> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<AppListItemBean> it = arrayList.iterator();
            while (it.hasNext()) {
                AppListItemBean next = it.next();
                if (next != null) {
                    String convertPackageName = CommonUtil.convertPackageName(next.getComponentName());
                    if (isInLocalGameList(convertPackageName) && TextUtils.isEmpty(next.getAtmosphereUrl())) {
                        next.setImageUrl(ConstantVariable.LOCAL_GAME_IMAGE_MAP.get(convertPackageName), null);
                    }
                    next.setSelect(true);
                }
            }
        }
        return arrayList;
    }

    public ContentValues covertToAppAddContentValues(AppListItemBean appListItemBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(generateNewAppAddId()));
        if (appListItemBean.select) {
            contentValues.put("isAdd", (Integer) 1);
        } else {
            contentValues.put("isAdd", (Integer) 0);
        }
        if (appListItemBean.isGame()) {
            contentValues.put(AppAddProvider.APPADD_ISGAME, (Integer) 1);
        } else {
            contentValues.put(AppAddProvider.APPADD_ISGAME, (Integer) 0);
        }
        contentValues.put("gamename", appListItemBean.getName());
        contentValues.put("component", appListItemBean.getComponentName());
        contentValues.put("urlType", appListItemBean.getAtmosphere().getType());
        contentValues.put("imageUrl", appListItemBean.getAtmosphere().getCurrentUrl());
        contentValues.put("netUrl", appListItemBean.getAtmosphere().getNetUrl());
        contentValues.put("lastStartTime", Long.valueOf(appListItemBean.getLastStartTime()));
        contentValues.put("middleImageUrl", appListItemBean.getMediumUrl());
        contentValues.put("widgetUrl", appListItemBean.getWidgetUrl());
        contentValues.put("lastUpdateUrlTime", Long.valueOf(appListItemBean.getLastUpdateUrlTime()));
        Log.d("Atmosphere", "-------------->updateDb(" + appListItemBean.getName() + ") Atmosphere : " + appListItemBean.getAtmosphere());
        return contentValues;
    }

    public ContentValues covertToUserRemoveContentValues(AppListItemBean appListItemBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(generateNewUserRemoveId()));
        contentValues.put("component", appListItemBean.getComponentName());
        return contentValues;
    }

    public ContentValues covertToUserVerifiedContentValues(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Integer.valueOf(generateNewVerifiedAppsId()));
        contentValues.put("component", str);
        return contentValues;
    }

    public void deleteAppItemBeanInAppAddDB(final ArrayList<AppListItemBean> arrayList) {
        final ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper.3
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < arrayList.size(); i++) {
                    AppListItemBean appListItemBean = (AppListItemBean) arrayList.get(i);
                    if (appListItemBean != null && AppAddModelHelper.this.componentExistInAppAddDB(appListItemBean.getComponentName(), contentResolver)) {
                        if (i == arrayList.size() - 1) {
                            contentResolver.delete(ConstantVariable.APPADD_URI, "component=?", new String[]{appListItemBean.getComponentName()});
                        } else {
                            contentResolver.delete(ConstantVariable.APPADD_URI_NO_NOTIFY, "component=?", new String[]{appListItemBean.getComponentName()});
                        }
                    }
                }
            }
        });
    }

    public void deleteAppItemBeanInUserRemoveDB(final ArrayList<AppListItemBean> arrayList) {
        final ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    AppListItemBean appListItemBean = (AppListItemBean) it.next();
                    if (appListItemBean != null && AppAddModelHelper.this.componentExistInUserRemoveDB(appListItemBean.getComponentName(), contentResolver)) {
                        contentResolver.delete(ConstantVariable.USER_ROMOVE_URI_NO_NOTIFY, "component=?", new String[]{appListItemBean.getComponentName()});
                    }
                }
            }
        });
    }

    public void deleteAppItemBeanInVerifiedAppsDB(final String str) {
        final ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper.7
            @Override // java.lang.Runnable
            public void run() {
                String str2 = str;
                if (str2 != null && AppAddModelHelper.this.componentExistInVerifiedAppsDB(str2, contentResolver)) {
                    Log.d(AppAddModel.TAG, "deleteAppItemBeanInVerifiedAppsDB() pkgName : " + str);
                    contentResolver.delete(ConstantVariable.VERIFIED_URI, "component=?", new String[]{str});
                }
            }
        });
    }

    public AppListItemBean findItemFromList(String str, ArrayList<AppListItemBean> arrayList) {
        if (str == null) {
            return null;
        }
        Iterator<AppListItemBean> it = arrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (str.equals(next.getPackageName())) {
                return next;
            }
        }
        return null;
    }

    public AppListItemBean findItemFromList(String str, CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        if (str == null) {
            return null;
        }
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (str.equals(next.getPackageName())) {
                return next;
            }
        }
        return null;
    }

    public ArrayList<AppListItemBean> getListBeanByGameItemBean(GameItemBean gameItemBean, ArrayList<AppListItemBean> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        ArrayList<AppListItemBean> arrayList2 = new ArrayList<>();
        Iterator<AppListItemBean> it = arrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && CommonUtil.convertPackageName(next.getComponentName()).equals(gameItemBean.getPackageName())) {
                next.setImageUrl(gameItemBean.getUrl(), null);
                next.setMediumUrl(gameItemBean.getMiddleUrl());
                next.setGame(gameItemBean.getAppType() == ConstantVariable.APP_TYPE_GAME);
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public ArrayList<AppListItemBean> getListBeanByPackName(String str, ArrayList<AppListItemBean> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        ArrayList<AppListItemBean> arrayList2 = new ArrayList<>();
        Iterator<AppListItemBean> it = arrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && CommonUtil.convertPackageName(next.getComponentName()).equals(str)) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public int getMaxIdInAppAddTable() {
        return this.mMaxIdInAppAddTable;
    }

    public int getMaxIdInUserRemoveTable() {
        return this.mMaxIdInUserRemoveTable;
    }

    public int getMaxIdInVerifiedAppsTable() {
        return this.mMaxIdInVerifiedAppsTable;
    }

    public ArrayList<String> getPackageList(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return null;
        }
        Log.d("Atmosphere", "getPackageList(s) list.size() : " + copyOnWriteArrayList.size());
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && next.isIntervalOverStepDay() && !next.isLocalImage()) {
                arrayList.add(CommonUtil.convertPackageName(next.getComponentName()));
            }
        }
        Log.d("Atmosphere", "------>getPackageList(e) packList : " + arrayList);
        return arrayList;
    }

    public ArrayList<String> getVerifiedAppsList() {
        if (this.mVerifiedAppsList == null) {
            initVerifiedAppsList();
        }
        return this.mVerifiedAppsList;
    }

    public ArrayList<String> getWillUpdateUrlPackageList(CopyOnWriteArrayList<AppListItemBean> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.size() <= 0) {
            return null;
        }
        Log.d(AppAddModel.TAG, "getWillUpdateUrlPackageList(s) list.size() : " + copyOnWriteArrayList.size());
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<AppListItemBean> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && isIntervalLessThanOneDay(next)) {
                arrayList.add(CommonUtil.convertPackageName(next.getComponentName()));
            }
        }
        Log.d(AppAddModel.TAG, "------>getWillUpdateUrlPackageList(e) packList : " + arrayList);
        return arrayList;
    }

    public ArrayList<String> getWillVerifyPackageList(ArrayList<AppListItemBean> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        Log.d(AppAddModel.TAG, "getWillVerifyPackageList(s) list.size() : " + arrayList.size());
        ArrayList<String> arrayList2 = new ArrayList<>();
        Iterator<AppListItemBean> it = arrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && !isVerifiedApp(CommonUtil.convertPackageName(next.getComponentName()))) {
                arrayList2.add(CommonUtil.convertPackageName(next.getComponentName()));
            }
        }
        Log.d(AppAddModel.TAG, "------->getWillVerifyPackageList(e) packList : " + arrayList2);
        return arrayList2;
    }

    public void initMaxIdInTable() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper.1
            /* JADX WARN: Removed duplicated region for block: B:20:0x008e  */
            /* JADX WARN: Removed duplicated region for block: B:22:0x0093  */
            /* JADX WARN: Removed duplicated region for block: B:24:0x0098  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x00c9  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00ce  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x00d3  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 215
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.model.AppAddModelHelper.AnonymousClass1.run():void");
            }
        });
    }

    public void insertAppToAppAddDB(final ArrayList<AppListItemBean> arrayList) {
        final ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper.2
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < arrayList.size(); i++) {
                    AppListItemBean appListItemBean = (AppListItemBean) arrayList.get(i);
                    if (appListItemBean != null && !AppAddModelHelper.this.componentExistInAppAddDB(appListItemBean.getComponentName(), contentResolver)) {
                        if (i == arrayList.size() - 1) {
                            contentResolver.insert(ConstantVariable.APPADD_URI, AppAddModelHelper.this.covertToAppAddContentValues(appListItemBean));
                        } else {
                            contentResolver.insert(ConstantVariable.APPADD_URI_NO_NOTIFY, AppAddModelHelper.this.covertToAppAddContentValues(appListItemBean));
                        }
                        if (appListItemBean.isGame()) {
                            AppAddModelHelper appAddModelHelper = AppAddModelHelper.this;
                            appAddModelHelper.notifyLauncherNotification(appAddModelHelper.getMaxIdInAppAddTable(), appListItemBean.getName());
                        }
                    }
                }
            }
        });
    }

    public void insertAppToUserRemoveDB(final ArrayList<AppListItemBean> arrayList) {
        final ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    AppListItemBean appListItemBean = (AppListItemBean) it.next();
                    if (appListItemBean != null && !AppAddModelHelper.this.componentExistInUserRemoveDB(appListItemBean.getComponentName(), contentResolver)) {
                        contentResolver.insert(ConstantVariable.USER_ROMOVE_URI_NO_NOTIFY, AppAddModelHelper.this.covertToUserRemoveContentValues(appListItemBean));
                    }
                }
            }
        });
    }

    public void insertAppToVerifiedAppsDB(final String str) {
        final ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper.6
            @Override // java.lang.Runnable
            public void run() {
                String str2 = str;
                if (str2 == null || AppAddModelHelper.this.componentExistInVerifiedAppsDB(str2, contentResolver)) {
                    return;
                }
                Log.d(AppAddModel.TAG, "insertAppToVerifiedAppsDB() pkgName : " + str);
                contentResolver.insert(ConstantVariable.VERIFIED_URI, AppAddModelHelper.this.covertToUserVerifiedContentValues(str));
            }
        });
    }

    public boolean isGameByAppSelfFlag(String str) {
        return false;
    }

    public boolean isInLocalGameList(String str) {
        Iterator<String> it = ConstantVariable.LOCAL_GAME_IMAGE_MAP.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInSystemAppList(String str) {
        if (str == null) {
            return true;
        }
        Iterator<String> it = ConstantVariable.SYSTEM_APP_LIST.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return str.startsWith("com.zte.") || str.startsWith("zte.com.") || str.startsWith("cn.zte.") || Util.isSystemApp(str) || Util.isHomeApp(str);
    }

    public boolean isVerifiedApp(String str) {
        ArrayList<String> verifiedAppsList = getVerifiedAppsList();
        Log.d(AppAddModel.TAG, "isVerifiedApp() list.size() : " + verifiedAppsList.size());
        if (verifiedAppsList == null) {
            return false;
        }
        Iterator<String> it = verifiedAppsList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next())) {
                return true;
            }
        }
        Log.d(AppAddModel.TAG, "isVerifiedApp() false packageName : " + str);
        return false;
    }

    public void removeAppListItemBeanInRemoveDB(ArrayList<AppListItemBean> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<AppListItemBean> it = arrayList.iterator();
        while (it.hasNext()) {
            AppListItemBean next = it.next();
            if (next != null && componentExistInUserRemoveDB(next.getComponentName(), GameLauncherApplication.CONTEXT.getContentResolver())) {
                it.remove();
            }
        }
    }

    public void setMaxIdInAppAddTable(int i) {
        this.mMaxIdInAppAddTable = i;
    }

    public void setMaxIdInUserRemoveTable(int i) {
        this.mMaxIdInUserRemoveTable = i;
    }

    public void setMaxIdInVerifiedAppsTable(int i) {
        this.mMaxIdInVerifiedAppsTable = i;
    }

    public void updateAppItemBeanInAppAddDB(final AppListItemBean appListItemBean) {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.AppAddModelHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AppAddModelHelper.this.m322x601bdf3d(appListItemBean);
            }
        });
    }
}
