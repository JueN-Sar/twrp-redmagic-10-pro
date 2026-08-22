package cn.nubia.gamelauncher.model;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.GameItemBean;
import cn.nubia.gamelauncher.bean.NeoIconDownloadInfo;
import cn.nubia.gamelauncher.bean.ResponseBean;
import cn.nubia.gamelauncher.commoninterface.ConstantVariable;
import cn.nubia.gamelauncher.commoninterface.IRequestListener;
import cn.nubia.gamelauncher.commoninterface.NeoGameDBColumns;
import cn.nubia.gamelauncher.gamecenter.BusinessRequestorImp;
import cn.nubia.gamelauncher.helper.SDKHelper;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;
import cn.nubia.globalsearch.GlobalSearchConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class NeoDownloadHelper {
    public static final int CHANGE_ALL = -200;
    public static final String EXTPROVIDEROPERATION = "action";
    public static final String EXTPROVIDEROPERATION_DELETE;
    public static final String EXTPROVIDEROPERATION_INSERT;
    public static final String EXTPROVIDEROPERATION_UPDATE;
    public static final String PACKAGE_NAME = "package_name";
    public static final String TAG = "NeoDownload";
    public String mLastDownloadAction = null;
    public String mLastDownloadProgress = null;
    public String mLastDownloadPkg = null;
    private NeoIconDownloadObserver mNeoIconObserver = null;
    BusinessRequestorImp mBusinessRequestorImp = null;
    private HashMap<Integer, NeoIconDownloadInfo> mNeoIconDownloadInfos = new HashMap<>();
    private ArrayList<NeoIconDownloadInfo> mPendingVerifyList = new ArrayList<>();
    private HashMap<String, NeoIconDownloadInfo> mVerifyMap = new HashMap<>();

    private class NeoIconDownloadObserver extends ContentObserver {
        public NeoIconDownloadObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            int parseId;
            String queryParameter = uri.getQueryParameter(Util.isMyOs() ? "ACTION" : "action");
            String queryParameter2 = uri.getQueryParameter("status");
            String queryParameter3 = uri.getQueryParameter("package_name");
            if (Util.isMyOs()) {
                String queryParameter4 = uri.getQueryParameter(NeoGameDBColumns.APP_ID);
                parseId = queryParameter4 == null ? 0 : Integer.valueOf(queryParameter4).intValue();
            } else {
                parseId = (int) ContentUris.parseId(uri);
            }
            NeoDownloadHelper.this.showNeoDownloadChangedLog(parseId, queryParameter, queryParameter2, uri);
            if (NeoDownloadHelper.EXTPROVIDEROPERATION_INSERT.equals(queryParameter)) {
                Log.i(NeoDownloadHelper.TAG, "showNeoDownloadChangedLog(" + parseId + ") action : " + queryParameter + ", status : " + queryParameter2 + ", pkg : " + queryParameter3);
                NeoIconDownloadInfo queryByAppId = NeoDownloadHelper.this.queryByAppId(uri, parseId);
                if (queryByAppId != null) {
                    NeoDownloadHelper.this.verifyDownloadInfo(queryByAppId);
                    return;
                }
                return;
            }
            if (!NeoDownloadHelper.EXTPROVIDEROPERATION_DELETE.equals(queryParameter) && !NeoGameDBColumns.STATUS_SUCCESS.equals(queryParameter2)) {
                if (NeoDownloadHelper.EXTPROVIDEROPERATION_UPDATE.equals(queryParameter)) {
                    NeoDownloadHelper.this.queryByAppId(uri, parseId);
                    NeoDownloadHelper.this.notifyChangeListener(parseId);
                    return;
                }
                return;
            }
            Log.i(NeoDownloadHelper.TAG, "showNeoDownloadChangedLog(" + parseId + ") action : " + queryParameter + ", status : " + queryParameter2 + ", pkg : " + queryParameter3);
            if (NeoDownloadHelper.this.mNeoIconDownloadInfos.containsKey(Integer.valueOf(parseId))) {
                NeoDownloadHelper.this.mNeoIconDownloadInfos.remove(Integer.valueOf(parseId));
                NeoDownloadHelper.this.notifyChangeListener(NeoDownloadHelper.CHANGE_ALL);
            }
        }
    }

    static {
        EXTPROVIDEROPERATION_DELETE = Util.isMyOs() ? "DELETE" : GlobalSearchConstants.DELETE;
        EXTPROVIDEROPERATION_INSERT = Util.isMyOs() ? "INSERT" : "insert";
        EXTPROVIDEROPERATION_UPDATE = Util.isMyOs() ? "UPDATE" : GlobalSearchConstants.UPDATE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addNeoIconDownloadInfo(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (neoIconDownloadInfo == null) {
            return;
        }
        if (AppAddModel.getInstance().isAppExitsInSystem(neoIconDownloadInfo.packageName)) {
            Log.i(TAG, "addNeoIconDownloadInfo isAppExitsInSystem packageName ==  " + neoIconDownloadInfo.packageName);
        } else {
            this.mNeoIconDownloadInfos.put(Integer.valueOf(neoIconDownloadInfo.appId), neoIconDownloadInfo);
            neoIconDownloadInfo.appListItemBean = constructAppListItemBean(neoIconDownloadInfo);
        }
    }

    private AppListItemBean constructAppListItemBean(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (neoIconDownloadInfo == null) {
            return null;
        }
        AppListItemBean appListItemBean = new AppListItemBean(neoIconDownloadInfo.mCropIcon == null ? neoIconDownloadInfo.mIcon : neoIconDownloadInfo.mCropIcon, neoIconDownloadInfo.title, neoIconDownloadInfo.packageName + ",Neo", false, null, null);
        appListItemBean.setDownloadInfo(neoIconDownloadInfo);
        return appListItemBean;
    }

    private void doNubiaRequestor(ArrayList<String> arrayList) {
        this.mBusinessRequestorImp.getApplicationsByPackageNames(GameLauncherApplication.CONTEXT, arrayList, new IRequestListener() { // from class: cn.nubia.gamelauncher.model.NeoDownloadHelper.2
            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseError(String str) {
                NeoDownloadHelper.this.onRequestFailed();
            }

            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseInfo(ResponseBean responseBean) {
                if (responseBean == null || responseBean.getGameItemBean() == null || responseBean.getGameItemBean().size() <= 0) {
                    return;
                }
                NeoDownloadHelper.this.onRequestSuccess(responseBean.getGameItemBean());
            }
        });
    }

    private void doNubiaVerfyDownloadInfo(NeoIconDownloadInfo neoIconDownloadInfo) {
        Log.d(TAG, "doNubiaVerfyDownloadInfo() packageName : " + neoIconDownloadInfo.packageName);
        this.mBusinessRequestorImp.getApplicationByPackageName(GameLauncherApplication.CONTEXT, neoIconDownloadInfo.packageName, new IRequestListener() { // from class: cn.nubia.gamelauncher.model.NeoDownloadHelper.3
            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseError(String str) {
            }

            @Override // cn.nubia.gamelauncher.commoninterface.IRequestListener
            public void responseInfo(ResponseBean responseBean) {
                if (responseBean == null || responseBean.getStateCode() != ConstantVariable.STATE_CODE_SUCESS || responseBean.getGameItemBean() == null || responseBean.getGameItemBean().size() <= 0) {
                    return;
                }
                NeoDownloadHelper.this.onVerfyItemSuccess(responseBean.getGameItemBean().get(0));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPendingVerifyListRequestor() {
        if (this.mPendingVerifyList.size() <= 0) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<NeoIconDownloadInfo> it = this.mPendingVerifyList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().packageName);
        }
        Util.isTencentAppStore();
        doNubiaRequestor(arrayList);
    }

    private void doTencentVerfyDownloadInfo(NeoIconDownloadInfo neoIconDownloadInfo) {
        Log.d(TAG, "doTencentVerfyDownloadInfo() packageName : " + neoIconDownloadInfo.packageName);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(neoIconDownloadInfo.packageName);
        SDKHelper.getInstance().doGetGameListRequest(arrayList, 4);
    }

    private void doVerify(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (this.mVerifyMap.containsKey(neoIconDownloadInfo.packageName)) {
            return;
        }
        this.mVerifyMap.put(neoIconDownloadInfo.packageName, neoIconDownloadInfo);
        Util.isTencentAppStore();
        doNubiaVerfyDownloadInfo(neoIconDownloadInfo);
    }

    public static Uri getUri() {
        return Util.isMyOs() ? ConstantVariable.NEO_DOWNLOAD_URI : ConstantVariable.NEOExtendDB_URI;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isGameDownload(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (neoIconDownloadInfo == null) {
            return false;
        }
        if (isInLocalGameList(neoIconDownloadInfo.packageName) || neoIconDownloadInfo.type == NeoIconDownloadInfo.TYPE_GAME_CENTER) {
            return true;
        }
        return this.mVerifyMap.containsKey(neoIconDownloadInfo.packageName) && this.mVerifyMap.get(neoIconDownloadInfo.packageName).type == ConstantVariable.APP_TYPE_GAME;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyChangeListener(int i) {
        AppAddModel.getInstance().onNeoDownloadGameChange(i);
    }

    private void notifyChangedChangeAllIfNeed() {
        this.mPendingVerifyList.clear();
        if (this.mNeoIconDownloadInfos.size() > 0) {
            notifyChangeListener(CHANGE_ALL);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0053, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0050, code lost:
    
        if (r1 == null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public cn.nubia.gamelauncher.bean.NeoIconDownloadInfo queryByAppId(android.net.Uri r9, int r10) {
        /*
            r8 = this;
            java.lang.String r0 = ""
            java.util.HashMap<java.lang.Integer, cn.nubia.gamelauncher.bean.NeoIconDownloadInfo> r8 = r8.mNeoIconDownloadInfos
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)
            java.lang.Object r8 = r8.get(r1)
            cn.nubia.gamelauncher.bean.NeoIconDownloadInfo r8 = (cn.nubia.gamelauncher.bean.NeoIconDownloadInfo) r8
            android.content.Context r1 = cn.nubia.gamelauncher.GameLauncherApplication.CONTEXT
            android.content.ContentResolver r2 = r1.getContentResolver()
            r1 = 0
            java.lang.String r5 = "app_id=?"
            r3 = 1
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.StringBuilder r10 = r3.append(r10)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r0 = 0
            r6[r0] = r10     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r7 = 0
            r4 = 0
            r3 = r9
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            boolean r9 = r1.moveToNext()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            if (r9 == 0) goto L44
            if (r8 != 0) goto L3f
            cn.nubia.gamelauncher.bean.NeoIconDownloadInfo r9 = new cn.nubia.gamelauncher.bean.NeoIconDownloadInfo     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r9.<init>(r1)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r8 = r9
        L3f:
            android.content.Context r9 = cn.nubia.gamelauncher.GameLauncherApplication.CONTEXT     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r8.updateInfo(r1, r9)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
        L44:
            if (r1 == 0) goto L53
        L46:
            r1.close()
            goto L53
        L4a:
            r8 = move-exception
            goto L54
        L4c:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L4a
            if (r1 == 0) goto L53
            goto L46
        L53:
            return r8
        L54:
            if (r1 == 0) goto L59
            r1.close()
        L59:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.model.NeoDownloadHelper.queryByAppId(android.net.Uri, int):cn.nubia.gamelauncher.bean.NeoIconDownloadInfo");
    }

    private void registerObserver() {
        try {
            ContentResolver contentResolver = GameLauncherApplication.CONTEXT.getContentResolver();
            Uri uri = getUri();
            NeoIconDownloadObserver neoIconDownloadObserver = new NeoIconDownloadObserver(WorkThread.getHandler());
            this.mNeoIconObserver = neoIconDownloadObserver;
            contentResolver.registerContentObserver(uri, true, neoIconDownloadObserver);
        } catch (Exception e) {
            Log.w(TAG, "registerObserver failed e: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNeoDownloadChangedLog(int i, String str, String str2, Uri uri) {
        String queryParameter = uri.getQueryParameter("package_name");
        String queryParameter2 = uri.getQueryParameter("progress");
        if (uri == null || str == null || queryParameter == null || queryParameter2 == null) {
            return;
        }
        if (str.equals(this.mLastDownloadAction) && queryParameter.equals(this.mLastDownloadPkg) && queryParameter2.equals(this.mLastDownloadProgress)) {
            return;
        }
        this.mLastDownloadAction = str;
        this.mLastDownloadPkg = queryParameter;
        this.mLastDownloadProgress = queryParameter2;
        Log.d(TAG, "showNeoDownloadChangedLog(" + i + ") action : " + str + ", status : " + str2 + ", pkg : " + queryParameter + ", progress : " + queryParameter2);
    }

    public void end() {
        if (this.mNeoIconObserver != null) {
            unRegisterObserver();
        }
    }

    NeoIconDownloadInfo findDownloadInfoInList(String str, ArrayList<NeoIconDownloadInfo> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        Iterator<NeoIconDownloadInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            NeoIconDownloadInfo next = it.next();
            if (next.packageName.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public AppListItemBean getAppListItemBeanByAppId(int i) {
        if (this.mNeoIconDownloadInfos.get(Integer.valueOf(i)) != null) {
            return this.mNeoIconDownloadInfos.get(Integer.valueOf(i)).appListItemBean;
        }
        return null;
    }

    public ArrayList<AppListItemBean> getNeoDownloadAppList() {
        if (this.mNeoIconDownloadInfos.size() <= 0) {
            return null;
        }
        ArrayList<AppListItemBean> arrayList = new ArrayList<>();
        Iterator<Integer> it = this.mNeoIconDownloadInfos.keySet().iterator();
        while (it.hasNext()) {
            NeoIconDownloadInfo neoIconDownloadInfo = this.mNeoIconDownloadInfos.get(Integer.valueOf(it.next().intValue()));
            Log.i(TAG, "NDH getNeoDownloadAppList() process == " + neoIconDownloadInfo.progress);
            if (neoIconDownloadInfo != null) {
                if (neoIconDownloadInfo.appListItemBean == null) {
                    neoIconDownloadInfo.appListItemBean = constructAppListItemBean(neoIconDownloadInfo);
                }
                arrayList.add(neoIconDownloadInfo.appListItemBean);
            }
        }
        return arrayList;
    }

    public NeoIconDownloadInfo getNeoDownloadInfoByAppId(int i) {
        return this.mNeoIconDownloadInfos.get(Integer.valueOf(i));
    }

    public void init() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.model.NeoDownloadHelper.1
            /* JADX WARN: Code restructure failed: missing block: B:10:0x00c1, code lost:
            
                r8.this$0.notifyChangeListener(cn.nubia.gamelauncher.model.NeoDownloadHelper.CHANGE_ALL);
             */
            /* JADX WARN: Code restructure failed: missing block: B:11:0x00c8, code lost:
            
                android.util.Log.i(cn.nubia.gamelauncher.model.NeoDownloadHelper.TAG, "NeoDownloadHelper init NeoIconDownloadInfo mNeoIconDownloadInfos == " + r8.this$0.mNeoIconDownloadInfos);
             */
            /* JADX WARN: Code restructure failed: missing block: B:12:0x00e0, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:31:0x0096, code lost:
            
                if (r1 == null) goto L27;
             */
            /* JADX WARN: Code restructure failed: missing block: B:6:0x00a4, code lost:
            
                r8.this$0.doPendingVerifyListRequestor();
             */
            /* JADX WARN: Code restructure failed: missing block: B:7:0x00b3, code lost:
            
                if (r8.this$0.mNeoIconDownloadInfos.size() <= 0) goto L32;
             */
            /* JADX WARN: Code restructure failed: missing block: B:9:0x00bf, code lost:
            
                if (r8.this$0.mPendingVerifyList.size() != 0) goto L32;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r8 = this;
                    java.lang.String r0 = "init() count == "
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r1 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this
                    java.util.HashMap r1 = cn.nubia.gamelauncher.model.NeoDownloadHelper.access$000(r1)
                    r1.clear()
                    android.content.Context r1 = cn.nubia.gamelauncher.GameLauncherApplication.CONTEXT
                    android.content.ContentResolver r2 = r1.getContentResolver()
                    android.net.Uri r3 = cn.nubia.gamelauncher.model.NeoDownloadHelper.getUri()
                    r6 = 0
                    r7 = 0
                    r4 = 0
                    r5 = 0
                    android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r2 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this
                    java.util.ArrayList r2 = cn.nubia.gamelauncher.model.NeoDownloadHelper.access$100(r2)
                    r2.clear()
                    java.lang.String r2 = "NeoDownload"
                    if (r1 == 0) goto L9f
                    boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    if (r3 == 0) goto L9f
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    r3.<init>(r0)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    int r0 = r1.getCount()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    android.util.Log.i(r2, r0)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                L44:
                    cn.nubia.gamelauncher.bean.NeoIconDownloadInfo r0 = new cn.nubia.gamelauncher.bean.NeoIconDownloadInfo     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    r0.<init>(r1)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    android.content.Context r3 = cn.nubia.gamelauncher.GameLauncherApplication.CONTEXT     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    r0.updateInfo(r1, r3)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r3 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    boolean r3 = cn.nubia.gamelauncher.model.NeoDownloadHelper.access$200(r3, r0)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    if (r3 == 0) goto L66
                    java.lang.String r3 = "STATUS_SUCCESS"
                    java.lang.String r4 = r0.status     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    if (r3 != 0) goto L6f
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r3 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    cn.nubia.gamelauncher.model.NeoDownloadHelper.access$300(r3, r0)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    goto L6f
                L66:
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r3 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    java.util.ArrayList r3 = cn.nubia.gamelauncher.model.NeoDownloadHelper.access$100(r3)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    r3.add(r0)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                L6f:
                    boolean r0 = r1.moveToNext()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
                    if (r0 != 0) goto L44
                    goto L9f
                L76:
                    r8 = move-exception
                    goto L99
                L78:
                    r0 = move-exception
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> L76
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L76
                    r3.<init>()     // Catch: java.lang.Throwable -> L76
                    java.lang.String r4 = "Exception e : "
                    java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> L76
                    java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L76
                    java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Throwable -> L76
                    java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L76
                    android.util.Log.i(r2, r0)     // Catch: java.lang.Throwable -> L76
                    if (r1 == 0) goto La4
                    goto La1
                L99:
                    if (r1 == 0) goto L9e
                    r1.close()
                L9e:
                    throw r8
                L9f:
                    if (r1 == 0) goto La4
                La1:
                    r1.close()
                La4:
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r0 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this
                    cn.nubia.gamelauncher.model.NeoDownloadHelper.access$400(r0)
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r0 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this
                    java.util.HashMap r0 = cn.nubia.gamelauncher.model.NeoDownloadHelper.access$000(r0)
                    int r0 = r0.size()
                    if (r0 <= 0) goto Lc8
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r0 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this
                    java.util.ArrayList r0 = cn.nubia.gamelauncher.model.NeoDownloadHelper.access$100(r0)
                    int r0 = r0.size()
                    if (r0 != 0) goto Lc8
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r0 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this
                    r1 = -200(0xffffffffffffff38, float:NaN)
                    cn.nubia.gamelauncher.model.NeoDownloadHelper.access$500(r0, r1)
                Lc8:
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    java.lang.String r1 = "NeoDownloadHelper init NeoIconDownloadInfo mNeoIconDownloadInfos == "
                    r0.<init>(r1)
                    cn.nubia.gamelauncher.model.NeoDownloadHelper r8 = cn.nubia.gamelauncher.model.NeoDownloadHelper.this
                    java.util.HashMap r8 = cn.nubia.gamelauncher.model.NeoDownloadHelper.access$000(r8)
                    java.lang.StringBuilder r8 = r0.append(r8)
                    java.lang.String r8 = r8.toString()
                    android.util.Log.i(r2, r8)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.model.NeoDownloadHelper.AnonymousClass1.run():void");
            }
        });
        registerObserver();
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

    public void onRequestFailed() {
        Log.i(TAG, "onRequestFailed()");
        notifyChangedChangeAllIfNeed();
    }

    public void onRequestSuccess(ArrayList<GameItemBean> arrayList) {
        Log.i(TAG, "onRequestSuccess() list.size() : " + arrayList.size());
        Iterator<GameItemBean> it = arrayList.iterator();
        while (it.hasNext()) {
            GameItemBean next = it.next();
            if (next != null && next.getAppType() == ConstantVariable.APP_TYPE_GAME) {
                addNeoIconDownloadInfo(findDownloadInfoInList(next.getPackageName(), this.mPendingVerifyList));
            }
        }
        notifyChangedChangeAllIfNeed();
    }

    public void onVerfyItemSuccess(GameItemBean gameItemBean) {
        NeoIconDownloadInfo neoIconDownloadInfo;
        Log.i(TAG, "onVerfyItemSuccess() gameItemBean : " + gameItemBean);
        if (gameItemBean == null || gameItemBean.getAppType() != ConstantVariable.APP_TYPE_GAME || (neoIconDownloadInfo = this.mVerifyMap.get(gameItemBean.getPackageName())) == null) {
            return;
        }
        neoIconDownloadInfo.type = ConstantVariable.APP_TYPE_GAME;
        if (Util.isTencentAppStore()) {
            neoIconDownloadInfo.type = 1;
        }
        this.mVerifyMap.put(neoIconDownloadInfo.packageName, neoIconDownloadInfo);
        addNeoIconDownloadInfo(neoIconDownloadInfo);
        notifyChangeListener(CHANGE_ALL);
    }

    public void setBusinessRequestorImp(BusinessRequestorImp businessRequestorImp) {
        this.mBusinessRequestorImp = businessRequestorImp;
    }

    public void unRegisterObserver() {
        try {
            GameLauncherApplication.CONTEXT.getContentResolver().unregisterContentObserver(this.mNeoIconObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void verifyDownloadInfo(NeoIconDownloadInfo neoIconDownloadInfo) {
        if (!isGameDownload(neoIconDownloadInfo)) {
            doVerify(neoIconDownloadInfo);
        } else {
            addNeoIconDownloadInfo(neoIconDownloadInfo);
            notifyChangeListener(CHANGE_ALL);
        }
    }
}
