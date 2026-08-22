package cn.nubia.gamelauncher.atmosphere;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.util.WorkThread;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class LiveAtmosphereManager {
    public static final String TAG = "LiveATM";
    private SimpleDateFormat mDateFormat;
    private boolean mHasTraversal;
    private CopyOnWriteArrayList<LiveAtmosphereBean> mList;
    private LiveAtmosphereObserver mObserver;
    private String mPath;
    private String mSupportHighLightPackages;
    public static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/红魔时刻";
    private static final String GAME_HIGHT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/红魔时刻";
    private static final String GAME_HIGHT_PATH_INTER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Red Magic Moment";
    private static final String GAME_HIGHT_PATH_ZTE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/精彩时刻";
    private static final String GAME_HIGHT_PATH_ZTE_INTER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Wonderful Time";

    private static class LiveAtmosphereHolder {
        public static final LiveAtmosphereManager INSTANCE = new LiveAtmosphereManager();

        private LiveAtmosphereHolder() {
        }
    }

    private LiveAtmosphereManager() {
        this.mDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        this.mList = new CopyOnWriteArrayList<>();
        this.mPath = null;
        this.mSupportHighLightPackages = "";
        this.mHasTraversal = false;
        initPackages();
        initObserver();
    }

    private Date convertStringToDateFormat(String str) {
        if (str == null) {
            return null;
        }
        try {
            return this.mDateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static LiveAtmosphereManager getInstance() {
        return LiveAtmosphereHolder.INSTANCE;
    }

    private void initObserver() {
        LiveAtmosphereObserver liveAtmosphereObserver = new LiveAtmosphereObserver(getPath());
        this.mObserver = liveAtmosphereObserver;
        liveAtmosphereObserver.startWatching();
    }

    private void initPath() {
        String string = Settings.Global.getString(getContentResolver(), "persist_sys_highlights_auto_file_path");
        if (!TextUtils.isEmpty(string)) {
            this.mPath = string;
        } else if (CommonUtil.isNubia()) {
            if (CommonUtil.isInter()) {
                this.mPath = GAME_HIGHT_PATH_INTER;
            } else {
                this.mPath = GAME_HIGHT_PATH;
            }
        } else if (!CommonUtil.isZte()) {
            this.mPath = PATH;
        } else if (CommonUtil.isInter()) {
            this.mPath = GAME_HIGHT_PATH_ZTE_INTER;
        } else {
            this.mPath = GAME_HIGHT_PATH_ZTE;
        }
        Log.d(TAG, "initPath() mPath : " + this.mPath);
    }

    public static boolean isDateTimeFormatValid(String str) {
        return Pattern.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}", str);
    }

    private void updateAtmosphereBean(LiveAtmosphereBean liveAtmosphereBean, LiveAtmosphereBean liveAtmosphereBean2) {
        if (liveAtmosphereBean.mDate.after(liveAtmosphereBean2.mDate)) {
            return;
        }
        Log.d(TAG, "updateAtmosphere() from : " + liveAtmosphereBean + " to : " + liveAtmosphereBean2);
        liveAtmosphereBean.setDate(liveAtmosphereBean2.getDate());
        liveAtmosphereBean.setTitle(liveAtmosphereBean2.getTitle());
        liveAtmosphereBean.setPath(liveAtmosphereBean2.getUrl());
    }

    public void addFileToList(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(TAG, "addFileToList() find path : " + str);
        if (!isHighLightPath(str)) {
            Log.d(TAG, "addFileToList() but is not a HighLightsFile");
            return;
        }
        String[] split = str2.split("_");
        if (split == null) {
            Log.d(TAG, "addFileToList() but strings is null");
            return;
        }
        if (split.length < 5) {
            Log.d(TAG, "addFileToList() but strings.length : " + split.length);
            return;
        }
        String str3 = split[0];
        if (TextUtils.isEmpty(str3) || str3.contains(".trashed-")) {
            Log.d(TAG, "addFileToList() but file is .trashed ");
            return;
        }
        String str4 = split[1];
        if (str4 == null) {
            Log.d(TAG, "addFileToList() but pkg is null ");
            return;
        }
        String str5 = split[3];
        if (!isDateTimeFormatValid(str5)) {
            Log.d(TAG, "addFileToList() but time is not Format Valid, time : " + str5);
            return;
        }
        Date convertStringToDateFormat = convertStringToDateFormat(str5);
        if (convertStringToDateFormat == null) {
            Log.d(TAG, "addFileToList() but date is null ");
        } else {
            addLiveAtmosphereBeanToList(new LiveAtmosphereBean(str, str2, str4, convertStringToDateFormat));
        }
    }

    public void addLiveAtmosphereBeanToList(LiveAtmosphereBean liveAtmosphereBean) {
        Log.d(TAG, "addLiveAtmosphereBeanToList() bean : " + liveAtmosphereBean);
        LiveAtmosphereBean findBeanInLiveAtmosphereList = findBeanInLiveAtmosphereList(liveAtmosphereBean.getPackageName());
        if (findBeanInLiveAtmosphereList == null) {
            this.mList.add(liveAtmosphereBean);
        } else {
            updateAtmosphereBean(findBeanInLiveAtmosphereList, liveAtmosphereBean);
        }
    }

    public void doTraversalDirectoryIfNeed() {
        if (this.mHasTraversal) {
            return;
        }
        this.mHasTraversal = true;
        startTraversalDirectory();
    }

    public LiveAtmosphereBean findBeanInLiveAtmosphereList(String str) {
        if (str == null) {
            return null;
        }
        Iterator<LiveAtmosphereBean> it = this.mList.iterator();
        while (it.hasNext()) {
            LiveAtmosphereBean next = it.next();
            if (str.equals(next.getPackageName())) {
                return next;
            }
            Log.d(TAG, "findBeanInLiveAtmosphereList() not equals because pkg : " + str + ", bean.pkg : " + next.getPackageName());
        }
        return null;
    }

    public String findLiveAtmosphereUrl(String str) {
        Log.d(TAG, "findLiveAtmosphereUrl() pkg : " + str);
        if (!isSupportHighLight(str)) {
            Log.d(TAG, str + " not support highlight");
            return null;
        }
        LiveAtmosphereBean findBeanInLiveAtmosphereList = findBeanInLiveAtmosphereList(str);
        String url = findBeanInLiveAtmosphereList != null ? findBeanInLiveAtmosphereList.getUrl() : null;
        Log.d(TAG, "findLiveAtmosphereUrl(" + str + ") url : " + url);
        return url;
    }

    public ContentResolver getContentResolver() {
        return getContext().getContentResolver();
    }

    public Context getContext() {
        return GameLauncherApplication.getAppContext();
    }

    public String getPath() {
        if (this.mPath == null) {
            initPath();
        }
        return this.mPath;
    }

    public String getSelection() {
        return "_data like  '" + getPath() + "/%' AND _data like '%_HighLights_%' AND duration <> 0";
    }

    public boolean hasValidUrl(String str) {
        return isAtmosphereUrlInvalid(findLiveAtmosphereUrl(str));
    }

    public void initPackages() {
        this.mSupportHighLightPackages = Settings.Global.getString(getContentResolver(), "persist_sys_highlights_auto_list");
        Log.d(TAG, "initPackages() mSupportHighLightPackages : " + this.mSupportHighLightPackages);
    }

    public boolean isAtmosphereUrlInvalid(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(getPath()) || !str.endsWith(".mp4") || !CommonUtil.isSecurePath(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isFile();
    }

    public boolean isHighLightPath(String str) {
        if (str == null) {
            return false;
        }
        Log.d(TAG, "fileName : " + str);
        return str.contains("_HighLights_") && str.endsWith(".mp4");
    }

    public boolean isSupportHighLight(String str) {
        return (ActivityManager.isUserAMonkey() || TextUtils.isEmpty(this.mSupportHighLightPackages) || !this.mSupportHighLightPackages.contains(str)) ? false : true;
    }

    /* renamed from: listHighLightsDirectory, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m233xf624d5f5() {
        Log.d(TAG, "listHighLightsDirectory() path : " + getPath());
        this.mList.clear();
        Cursor cursor = null;
        try {
            try {
                String selection = getSelection();
                cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id", "date_modified", "date_added", "title", "duration"}, selection, null, "date_added DESC");
                Log.d(TAG, "listHighLightsDirectory() cursor.getCount() = " + (cursor != null ? cursor.getCount() : 0) + ", selection : " + selection);
                while (cursor.moveToNext()) {
                    int columnIndex = cursor.getColumnIndex("_data");
                    int columnIndex2 = cursor.getColumnIndex("title");
                    if (columnIndex >= 0 && columnIndex2 >= 0) {
                        String string = cursor.getString(columnIndex);
                        String string2 = cursor.getString(columnIndex2);
                        Log.d(TAG, "listHighLightsDirectory() path = " + string);
                        addFileToList(string, string2);
                    }
                }
                if (cursor == null) {
                    return;
                }
            } catch (Exception e) {
                Log.d(TAG, "listHighLightsDirectory() e : " + e.getMessage());
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void resetTraversalFlag() {
        this.mHasTraversal = false;
    }

    public void startTraversalDirectory() {
        Log.d(TAG, "startTraversalDirectory()");
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.atmosphere.LiveAtmosphereManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LiveAtmosphereManager.this.m231xf3b83037();
            }
        });
    }

    public void startTraversalDirectory(boolean z) {
        Log.d(TAG, "startTraversalDirectory() isCreateOrDelete = " + z);
        try {
            if (z) {
                WorkThread.getHandler().postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.atmosphere.LiveAtmosphereManager$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LiveAtmosphereManager.this.m232xf4ee8316();
                    }
                }, 1500L);
            } else {
                WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.atmosphere.LiveAtmosphereManager$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        LiveAtmosphereManager.this.m233xf624d5f5();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
