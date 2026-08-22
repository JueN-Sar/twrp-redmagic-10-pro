package cn.nubia.gamecenter.settings.records;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.UserHandle;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.common.C;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.gamecenter.settings.BaseFragment;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.utils.HighLightsAIUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.records.view.RoundImageDrawable;
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.recordsdb.RTimeDataBaseHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.settings.trackclient.NubiaTrackManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class RecordsFragment extends BaseFragment implements FragmentInterface, StartInfo {
    public static final String APPADD_NAME = "gamename";
    private static final String APPADD_URI_NO_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false";
    private static final String ATTR_APP_NAME = "component";
    private static final int MSG_GAME_DATA = 3;
    private static final int MSG_IMAGE = 2;
    private static final int MSG_VIDEO = 1;
    private static final int REQUEST_PERMISSION_EXTERNAL_STORAGE = 2777;
    private static final String TAG = "RecordsFragment";
    private LinearLayout ImageCategory;
    private LinearLayout emptyView;
    private HorizontalScrollView hs;
    private ImageView icon;
    private View imageDivider;
    private LinearLayout linear;
    private Drawable mChoiceDrawable;
    private Context mContext;
    private AlertDialog mDialog;
    private TextView mEmptyTitle;
    private AnimatorSet mEntryAnimationSet;
    private AnimatorSet mExitAnimationSet;
    private LinearLayout mGameListLl;
    private List<VideoFile> mImageList;
    private RecyclerView mImageRecyclerView;
    private ImagelistAdapter mImagelistAdapter;
    private PackageManager mPackageManager;
    private LinearLayout mRecordMoreImage;
    private LinearLayout mRecordMoreVideo;
    private Handler mThreadHandler;
    private String mTitle;
    private Drawable mUnChoiceDrawable;
    private HashMap<String, Integer> mVideoHashMap;
    private ViewGroup.LayoutParams mVideoLayoutParams;
    private List<VideoFile> mVideoList;
    private VideolistAdapter mVideolistAdapter;
    private RecyclerView mVodeoRecyclerView;
    private ColorStateList m_normalColor;
    private ColorStateList m_selColor;
    private RecordsTester m_tester;
    private LinearLayout startGameView;
    private View tileDivider;
    private LinearLayout videoCategory;
    private View videoDivider;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE"};
    private static String GOOGLE_PHOTO_PACKAGE = "com.google.android.apps.photos";
    private static String GOOGLE_PHOTO_ACTIVITY = "com.google.android.apps.photos.home.HomeActivity";
    private static final int COLOR_MENU_NORMAL = R.color.game_name_text_normal_color;
    private static final int COLOR_MENU_SELECTED = R.color.game_name_text_selected_color;
    private String mMp4OutPath = "";
    private String mVideoPackageName = "";
    private HandlerThread mPreviewThread = new HandlerThread("PreviewThreadIO");
    private Map<String, String> mInstallMap = new HashMap();
    private Map<String, String> mGameMap = new HashMap();
    private List<String> mGameApps = new ArrayList();
    private List<String> mGamePackagesName = new ArrayList();
    private ArrayList<TextView> titlesView = new ArrayList<>();
    private int mImageIndex = 0;
    private int mGameTabIndex = 0;
    private int lastVideoOffset = 0;
    private int lastVideoPosition = 0;
    private int lastImageOffset = 0;
    private int lastImagePosition = 0;
    private String mStartPackage = "";
    private boolean mIsFirst = true;
    private Handler mHandler = new Handler() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                RecordsFragment.this.mVideoList = (List) message.obj;
                if (RecordsFragment.this.mVideoList == null || RecordsFragment.this.mVideoList.size() == 0) {
                    LogUtil.d(RecordsFragment.TAG, "******mVideoList == null");
                    return;
                }
                RecordsFragment.this.mVideolistAdapter = new VideolistAdapter(RecordsFragment.this.mContext, RecordsFragment.this.mVideoHashMap, RecordsFragment.this.mVideoList, RecordsFragment.this.mMp4OutPath);
                RecordsFragment.this.mVodeoRecyclerView.setAdapter(RecordsFragment.this.mVideolistAdapter);
                RecordsFragment.this.mVodeoRecyclerView.setLayoutManager(new LinearLayoutManager(RecordsFragment.this.mContext, 0, false));
                RecordsFragment.this.mVodeoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.1.1
                    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                        super.onScrollStateChanged(recyclerView, i2);
                        if (recyclerView.getLayoutManager() != null) {
                            RecordsFragment.this.getVideoPositionAndOffset();
                        }
                    }
                });
                RecordsFragment.this.scrollToVideoPosition();
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                RecordsFragment.this.refreshGameHighLightsData();
                return;
            }
            RecordsFragment.this.mImageList = (List) message.obj;
            if (RecordsFragment.this.mImageList == null || RecordsFragment.this.mImageList.size() == 0) {
                LogUtil.d(RecordsFragment.TAG, "******mImageList == null");
                return;
            }
            RecordsFragment.this.mImagelistAdapter = new ImagelistAdapter(RecordsFragment.this.mContext, RecordsFragment.this.mImageList, RecordsFragment.this.mMp4OutPath);
            RecordsFragment.this.mImageRecyclerView.setAdapter(RecordsFragment.this.mImagelistAdapter);
            RecordsFragment.this.mImageRecyclerView.setLayoutManager(new LinearLayoutManager(RecordsFragment.this.mContext, 0, false));
            RecordsFragment.this.mImageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.1.2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                    super.onScrollStateChanged(recyclerView, i2);
                    if (recyclerView.getLayoutManager() != null) {
                        RecordsFragment.this.getImagePositionAndOffset();
                    }
                }
            });
            RecordsFragment.this.scrollToImagePosition();
        }
    };
    private int m_textPadding = -1;
    private int m_curSelId = -1;
    private View.OnClickListener mRecordClickListener = new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.gcs_game_video_record_more) {
                if (VideoListUtil.isNubiaOS()) {
                    RecordsFragment.this.loadVideo();
                } else if (VideoListUtil.isInternal()) {
                    RecordsFragment.this.loadGoogleGallery();
                } else {
                    RecordsFragment.this.loadZteGallery();
                }
                if (RecordsFragment.this.mTitle != null) {
                    NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "pers_center_redmagic_time_shots_view_all", HighLightsUtils.TRACK_GAME_NAME_KEY, RecordsFragment.this.mTitle);
                }
            } else if (id == R.id.gcs_game_screenshot_more) {
                if (VideoListUtil.isNubiaOS()) {
                    RecordsFragment.this.loadImage();
                } else if (VideoListUtil.isInternal()) {
                    RecordsFragment.this.loadGoogleGallery();
                } else {
                    RecordsFragment.this.loadZteGallery();
                }
                if (RecordsFragment.this.mTitle != null) {
                    NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "pers_center_redmagic_time_screenshots_view_all", HighLightsUtils.TRACK_GAME_NAME_KEY, RecordsFragment.this.mTitle);
                }
            } else if (id == R.id.gcs_ll_start_game) {
                int i = RecordsFragment.this.m_curSelId == -1 ? 0 : RecordsFragment.this.m_curSelId;
                if (i < RecordsFragment.this.mGameApps.size() && i < RecordsFragment.this.mGamePackagesName.size()) {
                    String str = (String) RecordsFragment.this.mGamePackagesName.get(i);
                    String str2 = (String) RecordsFragment.this.mGameApps.get(i);
                    if (RecordsFragment.this.isHasDoubleApp(str)) {
                        RecordsFragment.this.showDialog(str, str2);
                    } else {
                        RecordsFragment.this.startGame(str, false);
                    }
                }
            } else {
                if (RecordsFragment.this.m_curSelId == id) {
                    return;
                }
                RecordsFragment.this.m_curSelId = id;
                RecordsFragment.this.lastImageOffset = 0;
                RecordsFragment.this.lastImagePosition = 0;
                RecordsFragment.this.lastVideoOffset = 0;
                RecordsFragment.this.lastVideoPosition = 0;
                RecordsFragment.this.mStartPackage = "";
                int i2 = 0;
                while (i2 < RecordsFragment.this.mGameApps.size()) {
                    RecordsFragment recordsFragment = RecordsFragment.this;
                    recordsFragment.updateTitleItemState((TextView) recordsFragment.titlesView.get(i2), i2 == id);
                    if (i2 == id) {
                        RecordsFragment.this.mGameTabIndex = i2;
                        RecordsFragment.this.isDisplayView(i2);
                        if (RecordsFragment.this.mInstallMap.get(RecordsFragment.this.mGameApps.get(RecordsFragment.this.mGameTabIndex)) != null) {
                            RecordsFragment.this.startGameView.setAlpha(1.0f);
                            RecordsFragment.this.startGameView.setVisibility(0);
                            ImageView imageView = RecordsFragment.this.icon;
                            RecordsFragment recordsFragment2 = RecordsFragment.this;
                            imageView.setImageDrawable(new RoundImageDrawable(recordsFragment2.getAppIcon((String) recordsFragment2.mGamePackagesName.get(i2))));
                        } else {
                            RecordsFragment.this.startGameView.setVisibility(8);
                        }
                        RecordsFragment.this.mHandler.removeCallbacks(RecordsFragment.this.mSetStartViewAlphaRunnable);
                        RecordsFragment.this.mHandler.postDelayed(RecordsFragment.this.mSetStartViewAlphaRunnable, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                    }
                    i2++;
                }
            }
            LogUtil.d(RecordsFragment.TAG, "******onClick mGameTabIndex=" + RecordsFragment.this.mGameTabIndex);
        }
    };
    Runnable mSetStartViewAlphaRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.3
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (RecordsFragment.this.startGameView != null) {
                    RecordsFragment.this.startGameView.setAlpha(0.6f);
                }
            } catch (Exception e) {
                LogUtil.d(RecordsFragment.TAG, "Exception" + e);
            }
        }
    };
    Runnable mGetVideoPhotoAndTimeRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.4
        @Override // java.lang.Runnable
        public void run() {
            try {
                LogUtil.d(RecordsFragment.TAG, "****** mGetVideoPhotoAndTimeRunnable mMp4OutPath" + RecordsFragment.this.mMp4OutPath + ",mVideoPackageName = " + RecordsFragment.this.mVideoPackageName);
                RecordsFragment recordsFragment = RecordsFragment.this;
                recordsFragment.mVideoList = VideoListUtil.queryVideoFils(recordsFragment.mContext, RecordsFragment.this.mMp4OutPath, RecordsFragment.this.mVideoPackageName, (String) RecordsFragment.this.mGameMap.get(RecordsFragment.this.mVideoPackageName));
                RecordsFragment recordsFragment2 = RecordsFragment.this;
                recordsFragment2.mVideoHashMap = VideoListUtil.queryRedMagicTimeHashMap(recordsFragment2.mContext);
                Message obtainMessage = RecordsFragment.this.mHandler.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = VideoListUtil.sortList(RecordsFragment.this.mVideoList);
                RecordsFragment.this.mHandler.sendMessage(obtainMessage);
            } catch (Exception e) {
                LogUtil.d(RecordsFragment.TAG, "Exception" + e);
            }
        }
    };
    Runnable mGetGameHightLightsDataRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.5
        @Override // java.lang.Runnable
        public void run() {
            try {
                LogUtil.d(RecordsFragment.TAG, "****** mGetGameHightLightsDataRunnable");
                RecordsFragment.this.initInstallPackageMap();
                RecordsFragment.this.deletePubgVideoFromDB();
                RecordsFragment.this.initAppAddList();
                List<HighLightsDb> queryVideoFile = VideoListUtil.queryVideoFile(RecordsFragment.this.mContext);
                queryVideoFile.addAll(VideoListUtil.queryImageFile(RecordsFragment.this.mContext, VideoListUtil.getImageDataPath()));
                List<HighLightsDb> queryRedMagicTime = VideoListUtil.queryRedMagicTime(RecordsFragment.this.mContext);
                LogUtil.d(RecordsFragment.TAG, "****** mGetGameHightLightsDataRunnable mNotInsertList " + queryVideoFile);
                RecordsFragment.this.removeHadAll(queryVideoFile, queryRedMagicTime);
                if (queryVideoFile.size() > 0) {
                    queryVideoFile = RecordsFragment.this.parsingVideoPath(queryVideoFile);
                    RecordsFragment.this.insertVideoToDB(queryVideoFile);
                }
                if (queryRedMagicTime.size() > 0) {
                    RecordsFragment.this.deleteVideoToDB(queryRedMagicTime);
                }
                queryVideoFile.clear();
                queryRedMagicTime.clear();
                RecordsFragment.this.initGameList();
                Message obtainMessage = RecordsFragment.this.mHandler.obtainMessage();
                obtainMessage.what = 3;
                RecordsFragment.this.mHandler.sendMessage(obtainMessage);
            } catch (Exception e) {
                LogUtil.d(RecordsFragment.TAG, "Exception" + e);
            }
        }
    };
    Runnable mGetImagePhotoRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.6
        @Override // java.lang.Runnable
        public void run() {
            try {
                LogUtil.d(RecordsFragment.TAG, "****** mGetImagePhotoRunnable , mGameApps.get(" + RecordsFragment.this.mImageIndex + " ) =" + ((String) RecordsFragment.this.mGameApps.get(RecordsFragment.this.mImageIndex)));
                RecordsFragment recordsFragment = RecordsFragment.this;
                recordsFragment.mImageList = VideoListUtil.queryImageFils(recordsFragment.mContext, VideoListUtil.getImageDataPath(), (String) RecordsFragment.this.mGameApps.get(RecordsFragment.this.mImageIndex));
                Message obtainMessage = RecordsFragment.this.mHandler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = RecordsFragment.this.mImageList;
                RecordsFragment.this.mHandler.sendMessage(obtainMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private ContentValues covertToVideoContentValues(HighLightsDb highLightsDb) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", highLightsDb.getPath());
        contentValues.put("isPreview", Integer.valueOf(highLightsDb.getIsPreview()));
        contentValues.put("packageName", highLightsDb.getPackageName());
        contentValues.put("appName", highLightsDb.getAppName());
        return contentValues;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deletePubgVideoFromDB() {
        getContext().getContentResolver().delete(RTimeDataBaseHelper.REDMAGICTIME_NOT_NOTIFY_URI, "appName=?", new String[]{"PUBG"});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteVideoToDB(List<HighLightsDb> list) {
        ContentResolver contentResolver = getContext().getContentResolver();
        for (int i = 0; i < list.size(); i++) {
            HighLightsDb highLightsDb = list.get(i);
            if (highLightsDb != null) {
                if (i == list.size() - 1) {
                    contentResolver.delete(RTimeDataBaseHelper.REDMAGICTIME_NOT_NOTIFY_URI, "path=?", new String[]{highLightsDb.getPath()});
                } else {
                    contentResolver.delete(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, "path=?", new String[]{highLightsDb.getPath()});
                }
            }
        }
    }

    private void entryAnimation(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, 200.0f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mEntryAnimationSet = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2);
        this.mEntryAnimationSet.setDuration(300L);
        this.mEntryAnimationSet.setInterpolator(new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f));
        this.mEntryAnimationSet.start();
    }

    private void exitAnimation(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, 0.0f, 200.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 1.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mExitAnimationSet = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2);
        this.mExitAnimationSet.setDuration(250L);
        this.mExitAnimationSet.setInterpolator(new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f));
        this.mExitAnimationSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getAppIcon(String str) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return drawableToBitmap(packageManager.getApplicationIcon(packageManager.getApplicationInfo(str, 0)));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getImagePositionAndOffset() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mImageRecyclerView.getLayoutManager();
        View childAt = linearLayoutManager.getChildAt(0);
        if (childAt != null) {
            this.lastImageOffset = childAt.getLeft();
            this.lastImagePosition = linearLayoutManager.getPosition(childAt);
        }
    }

    private PackageInfo getPackageInfoAsUser(Object obj, String str, int i, int i2) {
        try {
            Object invoke = Class.forName("android.content.pm.PackageManager").getMethod("getPackageInfoAsUser", String.class, Integer.TYPE, Integer.TYPE).invoke(obj, str, Integer.valueOf(i), Integer.valueOf(i2));
            if (invoke == null) {
                return null;
            }
            return (PackageInfo) invoke;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ColorStateList getTitleColor(boolean z) {
        if (this.m_activity == null) {
            return null;
        }
        if (this.m_selColor == null) {
            this.m_selColor = getResources().getColorStateList(COLOR_MENU_SELECTED);
        }
        if (this.m_normalColor == null) {
            this.m_normalColor = getResources().getColorStateList(COLOR_MENU_NORMAL);
        }
        return z ? this.m_selColor : this.m_normalColor;
    }

    private String getVideoDataPath(String str) {
        RecordsTester recordsTester = this.m_tester;
        return recordsTester != null ? recordsTester.getVideoDataPath(str) : Build.DEVICE.contains("NX627") ? str.equals(HighLightsUtils.WZRY_PACKAGE_NAME) ? VideoListUtil.GAME_WZRY_WONDERFUL : str.equals(HighLightsUtils.CJZC_PACKAGE_NAME) ? VideoListUtil.GAME_CJZC_WONDERFUL : str.equals(HighLightsUtils.PUBG_PACKAGE_NAME) ? VideoListUtil.GAME_PUBG_WONDERFUL : str.equals("com.epicgames.fortnite") ? VideoListUtil.GAME_BLZY_WONDERFUL : str : str.equals(HighLightsUtils.WZRY_PACKAGE_NAME) ? "/storage/emulated/0/红魔时刻/王者荣耀" : str.equals(HighLightsUtils.CJZC_PACKAGE_NAME) ? "/storage/emulated/0/红魔时刻/和平精英" : str.equals(HighLightsUtils.PUBG_PACKAGE_NAME) ? "/storage/emulated/0/红魔时刻/PUBGMOBILE" : str.equals(HighLightsUtils.LOL_PACKAGE_NAME) ? "/storage/emulated/0/红魔时刻/英雄联盟" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getVideoPositionAndOffset() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mVodeoRecyclerView.getLayoutManager();
        View childAt = linearLayoutManager.getChildAt(0);
        if (childAt != null) {
            this.lastVideoOffset = childAt.getLeft();
            this.lastVideoPosition = linearLayoutManager.getPosition(childAt);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAppAddList() {
        this.mGameMap.clear();
        this.mGameApps.clear();
        this.mGamePackagesName.clear();
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            }
            try {
                int columnIndex = query.getColumnIndex("gamename");
                int columnIndex2 = query.getColumnIndex("component");
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    String string = query.getString(columnIndex);
                    String string2 = query.getString(columnIndex2);
                    String[] split = string2.split(",");
                    String str = split == null ? "" : split[0];
                    boolean isVideoExist = VideoListUtil.isVideoExist(this.mContext, str, string);
                    boolean isImageExist = VideoListUtil.isImageExist(this.mContext, string);
                    LogUtil.d(TAG, "******initAppAddList hasVideo =" + isVideoExist + ",hasImage =" + isImageExist + ",gamename =" + string + ", packagesName =" + string2);
                    if (isVideoExist || isImageExist) {
                        if (string != null && !"".equals(string) && !"".equals(string2) && !"null".equals(string) && !"null".equals(string2)) {
                            this.mGameMap.put(str, string);
                            this.mGamePackagesName.add(str);
                        }
                    }
                }
                query.close();
                LogUtil.d(TAG, "******getGameList mGamePackagesName =" + this.mGamePackagesName);
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed load game app data.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initGameList() {
        Cursor query;
        try {
            query = this.mContext.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_QUERY_URI, new String[]{"packageName", "appName"}, null, null, "_id DESC");
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed load game app data.", e);
        }
        if (query == null) {
            return;
        }
        int columnIndex = query.getColumnIndex("appName");
        int columnIndex2 = query.getColumnIndex("packageName");
        query.moveToPosition(-1);
        while (query.moveToNext()) {
            String string = query.getString(columnIndex);
            String string2 = query.getString(columnIndex2);
            if (!this.mGamePackagesName.contains(string2)) {
                boolean isVideoExist = VideoListUtil.isVideoExist(this.mContext, string2, string);
                boolean isImageExist = VideoListUtil.isImageExist(this.mContext, string);
                LogUtil.d(TAG, "******initGameList hasVideo =" + isVideoExist + ",hasImage =" + isImageExist + ",gamename =" + string + ", packagesName =" + string2);
                if (isVideoExist || isImageExist) {
                    if (string != null && string2 != null && !"".equals(string) && !"".equals(string2) && !"null".equals(string) && !"null".equals(string2)) {
                        this.mGameMap.put(string2, string);
                        this.mGamePackagesName.add(string2);
                    }
                }
            }
        }
        query.close();
        Collections.sort(this.mGamePackagesName);
        for (int i = 0; i < this.mGamePackagesName.size(); i++) {
            this.mGameApps.add(this.mGameMap.get(this.mGamePackagesName.get(i)));
            if (this.mStartPackage.equals(this.mGamePackagesName.get(i))) {
                this.m_curSelId = i;
            }
        }
        LogUtil.d(TAG, "******getGameList mGameApps =" + this.mGameApps + ",mGamePackagesName =" + this.mGamePackagesName);
    }

    private void initGameTabIndex() {
        List<String> list;
        if (this.mStartPackage == null || (list = this.mGamePackagesName) == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.mGamePackagesName.size(); i++) {
            if (this.mStartPackage.equals(this.mGamePackagesName.get(i))) {
                this.mGameTabIndex = i;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initInstallPackageMap() {
        if (this.mContext == null || !this.mIsFirst) {
            return;
        }
        this.mInstallMap.clear();
        PackageManager packageManager = this.mContext.getPackageManager();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(4096)) {
            String obj = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            if (obj.length() > 0) {
                this.mInstallMap.put(obj, packageInfo.packageName);
            }
        }
    }

    private void initTitles() {
        try {
            this.titlesView.clear();
            int size = this.mGameApps.size();
            LinearLayout linearLayout = this.linear;
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
            int i = 0;
            while (i < size) {
                TextView textView = new TextView(this.mContext);
                if ("Fortnite".equals(this.mGameApps.get(i))) {
                    textView.setText(R.string.gcs_game_video_blzy);
                    this.mTitle = this.mContext.getResources().getString(R.string.gcs_game_video_blzy);
                } else {
                    String str = this.mGameApps.get(i);
                    textView.setText(str);
                    this.mTitle = str;
                }
                textView.setTextSize(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_summary_percent_title));
                textView.setId(i);
                textView.setGravity(17);
                textView.setOnClickListener(this.mRecordClickListener);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                boolean z = true;
                boolean z2 = i == 0;
                if (i != size - 1) {
                    z = false;
                }
                setTextPadding(textView, z2, z);
                updateTitleItemState(textView, false);
                LinearLayout linearLayout2 = this.linear;
                if (linearLayout2 != null) {
                    linearLayout2.addView(textView, layoutParams);
                }
                ArrayList<TextView> arrayList = this.titlesView;
                if (arrayList != null) {
                    arrayList.add(textView);
                }
                i++;
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "initTitles.", e);
        }
    }

    private void initView() {
        LogUtil.d(TAG, "******initView mGameTabIndex=" + this.mGameTabIndex);
        if (this.mGamePackagesName.size() > 0) {
            if (this.mGameTabIndex >= this.mGamePackagesName.size()) {
                this.mGameTabIndex = 0;
            }
            initGameTabIndex();
            updateTitleItemState(this.titlesView.get(this.mGameTabIndex), true);
            this.emptyView.setVisibility(8);
            this.tileDivider.setVisibility(0);
            this.hs.setVisibility(0);
            Bitmap appIcon = this.mGamePackagesName.size() > 0 ? ("".equals(this.mStartPackage) || "cn.nubia.gamelauncher".equals(this.mStartPackage) || !this.mGamePackagesName.contains(this.mStartPackage)) ? getAppIcon(this.mGamePackagesName.get(this.mGameTabIndex)) : getAppIcon(this.mStartPackage) : null;
            if (appIcon != null) {
                this.icon.setImageDrawable(new RoundImageDrawable(appIcon));
            }
            this.startGameView.setAlpha(1.0f);
            this.mHandler.removeCallbacks(this.mSetStartViewAlphaRunnable);
            this.mHandler.postDelayed(this.mSetStartViewAlphaRunnable, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        } else {
            this.emptyView.setVisibility(0);
            entryAnimation(this.emptyView);
            this.hs.setVisibility(8);
            this.tileDivider.setVisibility(8);
            this.startGameView.setVisibility(8);
        }
        if (this.mGamePackagesName.size() <= 0) {
            this.mGameListLl.setVisibility(8);
            this.videoCategory.setVisibility(8);
            this.mVodeoRecyclerView.setVisibility(8);
            this.ImageCategory.setVisibility(8);
            this.mImageRecyclerView.setVisibility(8);
            this.videoDivider.setVisibility(8);
            this.imageDivider.setVisibility(8);
            return;
        }
        if (this.mInstallMap.get(this.mGameApps.get(this.mGameTabIndex)) != null) {
            this.startGameView.setVisibility(0);
        }
        boolean isVideoExist = VideoListUtil.isVideoExist(this.mContext, this.mGamePackagesName.get(this.mGameTabIndex), this.mGameApps.get(this.mGameTabIndex));
        boolean isImageExist = VideoListUtil.isImageExist(this.mContext, this.mGameApps.get(this.mGameTabIndex));
        LogUtil.d(TAG, "****** initView hasVideo =" + isVideoExist + ",videopath =" + this.mGamePackagesName.get(this.mGameTabIndex) + ", hasImage =" + isImageExist + "，imagepath =" + this.mGameApps.get(this.mGameTabIndex));
        if (isVideoExist) {
            this.videoCategory.setVisibility(0);
            this.mVodeoRecyclerView.setVisibility(0);
            this.mVideoLayoutParams.height = 255;
            this.mVodeoRecyclerView.setLayoutParams(this.mVideoLayoutParams);
            this.videoDivider.setVisibility(0);
            requireVideoData(this.mGameTabIndex);
        } else {
            this.videoCategory.setVisibility(8);
            this.mVodeoRecyclerView.setVisibility(8);
            this.videoDivider.setVisibility(8);
            this.mVideoLayoutParams.height = 0;
            this.mVodeoRecyclerView.setLayoutParams(this.mVideoLayoutParams);
        }
        if (isImageExist) {
            this.ImageCategory.setVisibility(0);
            this.mImageRecyclerView.setVisibility(0);
            this.imageDivider.setVisibility(0);
            requireImageData(this.mGameTabIndex);
        } else {
            this.ImageCategory.setVisibility(8);
            this.mImageRecyclerView.setVisibility(8);
            this.imageDivider.setVisibility(8);
        }
        if ((isVideoExist || isImageExist) && this.mIsFirst) {
            this.mGameListLl.setVisibility(0);
            entryAnimation(this.mGameListLl);
        }
        this.mIsFirst = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insertVideoToDB(List<HighLightsDb> list) {
        ContentResolver contentResolver = getContext().getContentResolver();
        for (int i = 0; i < list.size(); i++) {
            HighLightsDb highLightsDb = list.get(i);
            if (highLightsDb != null) {
                if (i == list.size() - 1) {
                    contentResolver.insert(RTimeDataBaseHelper.REDMAGICTIME_NOT_NOTIFY_URI, covertToVideoContentValues(highLightsDb));
                } else {
                    contentResolver.insert(RTimeDataBaseHelper.REDMAGICTIME_NOTIFY_URI, covertToVideoContentValues(highLightsDb));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isDisplayView(int i) {
        boolean isVideoExist = VideoListUtil.isVideoExist(this.mContext, this.mGamePackagesName.get(i), this.mGameApps.get(this.mGameTabIndex));
        boolean isImageExist = VideoListUtil.isImageExist(this.mContext, this.mGameApps.get(i));
        LogUtil.d(TAG, "****** isDisplayView hasVideo =" + isVideoExist + ",videopath =" + this.mGamePackagesName.get(i) + ", hasImage =" + isImageExist + "，imagepath =" + this.mGameApps.get(i));
        if (isVideoExist || isImageExist) {
            this.mGameListLl.setVisibility(0);
        } else {
            this.mGameListLl.setVisibility(8);
        }
        if (isVideoExist) {
            this.mVideoLayoutParams.height = 255;
            this.mVodeoRecyclerView.setLayoutParams(this.mVideoLayoutParams);
            this.videoCategory.setVisibility(0);
            this.mVodeoRecyclerView.setVisibility(0);
            this.videoDivider.setVisibility(0);
            requireVideoData(i);
        } else {
            this.mVideoLayoutParams.height = 0;
            this.mVodeoRecyclerView.setLayoutParams(this.mVideoLayoutParams);
            this.videoCategory.setVisibility(8);
            this.mVodeoRecyclerView.setVisibility(8);
            this.videoDivider.setVisibility(8);
        }
        if (!isImageExist) {
            this.ImageCategory.setVisibility(8);
            this.mImageRecyclerView.setVisibility(8);
            this.imageDivider.setVisibility(8);
        } else {
            this.ImageCategory.setVisibility(0);
            this.mImageRecyclerView.setVisibility(0);
            this.imageDivider.setVisibility(0);
            requireImageData(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHasDoubleApp(String str) {
        if (this.mPackageManager == null) {
            this.mPackageManager = this.mContext.getPackageManager();
        }
        if (str == null || this.mContext == null) {
            return false;
        }
        return getPackageInfoAsUser(this.mPackageManager, str, 0, HighLightsUtils.isNubiaOS() ? HighLightsUtils.NUBIA_TWIN_USERID : 999) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadImage() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("cn.nubia.gallery3d", "cn.nubia.gallery3d.app.Gallery"));
            intent.putExtra("is_game_highlights", "cn.nubia.gamehighlights");
            intent.setType("image/*");
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    private void neverDisplayPermissionDialog() {
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(HighLightsUtils.STORAGE_PERMISSION_DIALOG_OPER, 0).edit();
        edit.putInt(HighLightsUtils.NEVER_DISPLAY_STORAGE_PERMISSION_DIALOG, 1);
        edit.apply();
    }

    private String parsingAppName(String str) {
        if (str.contains("/storage/emulated/0/红魔时刻/王者荣耀")) {
            return "王者荣耀";
        }
        if (str.contains("/storage/emulated/0/红魔时刻/和平精英")) {
            return "和平精英";
        }
        if (str.contains("/storage/emulated/0/红魔时刻/PUBGMOBILE")) {
            return HighLightsUtils.PUBG_APP_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/英雄联盟")) {
            return "英雄联盟手游";
        }
        return null;
    }

    private String parsingPackageName(String str) {
        if (str.contains("/storage/emulated/0/红魔时刻/王者荣耀")) {
            return HighLightsUtils.WZRY_PACKAGE_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/和平精英")) {
            return HighLightsUtils.CJZC_PACKAGE_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/PUBGMOBILE")) {
            return HighLightsUtils.PUBG_PACKAGE_NAME;
        }
        if (str.contains("/storage/emulated/0/红魔时刻/英雄联盟")) {
            return HighLightsUtils.LOL_PACKAGE_NAME;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HighLightsDb> parsingVideoPath(List<HighLightsDb> list) {
        String path;
        String str;
        String str2;
        String str3;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            HighLightsDb highLightsDb = list.get(i);
            if (highLightsDb != null && (path = highLightsDb.getPath()) != null) {
                if (path.contains("/storage/emulated/0/Pictures/Game Space Screenshot") || path.contains("/storage/emulated/0/Pictures/Redmagic Time Screenshot")) {
                    String[] split = path.split("/");
                    String[] split2 = (split.length - 1 <= 0 ? "" : split[split.length - 1]).split("_");
                    str = split2.length >= 2 ? split2[1] : "";
                    str2 = this.mInstallMap.get(str);
                    LogUtil.d(TAG, "****** parsingVideoPath , appName =" + str + " packageName =" + str2);
                    str3 = str;
                } else if (parsingPackageName(path) != null && parsingAppName(path) != null) {
                    str2 = parsingPackageName(path);
                    str3 = parsingAppName(path);
                } else if (path.contains("/") && path.contains("_")) {
                    String[] split3 = path.split("/");
                    String str4 = split3.length - 1 <= 0 ? "" : split3[split3.length - 1];
                    String[] split4 = str4.split("_");
                    if (str4.contains("manual")) {
                        String str5 = split4.length < 2 ? "" : split4[0];
                        str = split4.length >= 2 ? split4[1] : "";
                        str3 = str5;
                        str2 = str;
                    } else {
                        str3 = split4.length < 2 ? "" : split4[0];
                        str2 = this.mInstallMap.get(str3);
                        if ("".equals(str2)) {
                        }
                    }
                }
                arrayList.add(new HighLightsDb(path, 1, str2, str3));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshGameHighLightsData() {
        initTitles();
        initView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeHadAll(List<HighLightsDb> list, List<HighLightsDb> list2) {
        for (int size = list.size() - 1; size >= 0; size--) {
            int size2 = list2.size();
            while (true) {
                if (size2 > 0) {
                    int i = size2 - 1;
                    String path = list2.get(i).getPath();
                    if (path != null && path.equals(list.get(size).getPath())) {
                        list2.remove(i);
                        list.remove(size);
                        break;
                    }
                    size2--;
                }
            }
        }
    }

    private void requireImageData(int i) {
        try {
            List<String> list = this.mGameApps;
            if (list == null || list.size() <= 0) {
                return;
            }
            List<VideoFile> list2 = this.mImageList;
            if (list2 != null) {
                list2.clear();
            }
            ImagelistAdapter imagelistAdapter = this.mImagelistAdapter;
            if (imagelistAdapter != null) {
                imagelistAdapter.notifyDataSetChanged();
            }
            this.mImageIndex = i;
            this.mThreadHandler.post(this.mGetImagePhotoRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requireVideoData(int i) {
        String str;
        List<String> list = this.mGamePackagesName;
        if (list == null || list.size() <= 0 || (str = this.mGamePackagesName.get(i)) == null) {
            return;
        }
        List<VideoFile> list2 = this.mVideoList;
        if (list2 != null) {
            list2.clear();
        }
        String videoDataPath = getVideoDataPath(str);
        if (videoDataPath != null) {
            this.mMp4OutPath = videoDataPath;
            this.mVideoPackageName = str;
            this.mThreadHandler.post(this.mGetVideoPhotoAndTimeRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollToImagePosition() {
        try {
            LogUtil.d(TAG, "lastImagePosition = " + this.lastImagePosition + ", lastImageOffset = " + this.lastImageOffset);
            if (this.mImageRecyclerView.getLayoutManager() == null || this.lastImagePosition <= 0) {
                return;
            }
            ((LinearLayoutManager) this.mImageRecyclerView.getLayoutManager()).scrollToPositionWithOffset(this.lastImagePosition, this.lastImageOffset);
        } catch (Exception e) {
            LogUtil.e(TAG, "scrollToImagePosition:", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollToVideoPosition() {
        if (this.mVodeoRecyclerView.getLayoutManager() == null || this.lastVideoPosition <= 0) {
            return;
        }
        ((LinearLayoutManager) this.mVodeoRecyclerView.getLayoutManager()).scrollToPositionWithOffset(this.lastVideoPosition, this.lastVideoOffset);
    }

    private void setTextPadding(TextView textView, boolean z, boolean z2) {
        try {
            if (this.m_textPadding == -1) {
                this.m_textPadding = this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_record_title_margin);
            }
            textView.setPadding(z ? 0 : this.m_textPadding, 0, z2 ? 0 : this.m_textPadding, 0);
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed setTextPadding.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(final String str, String str2) {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            Drawable icon = getIcon(str);
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.gcs_record_double_app_dialog, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.sour_title);
            textView.setFocusable(true);
            textView.setText(str2);
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, cropCenterDrawable(icon), (Drawable) null, (Drawable) null);
            textView.setPadding(5, 5, 5, 5);
            textView.requestFocus();
            TextView textView2 = (TextView) inflate.findViewById(R.id.twin_title);
            textView2.setPadding(5, 5, 5, 5);
            textView2.setFocusable(true);
            textView2.setText(str2);
            textView2.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, cropCenterDrawable(getTwinIcon(str, icon)), (Drawable) null, (Drawable) null);
            textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecordsFragment.this.startGame(str, false);
                    RecordsFragment.this.mDialog.dismiss();
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecordsFragment.this.startGame(str, true);
                    RecordsFragment.this.mDialog.dismiss();
                }
            });
            AlertDialog create = new AlertDialog.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert).setCustomTitle(inflate).setNegativeButton(R.string.gamemode_account_login_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.RecordsFragment.9
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    RecordsFragment.this.mDialog.dismiss();
                }
            }).create();
            this.mDialog = create;
            create.getWindow().setType(2047);
            this.mDialog.show();
        }
    }

    private void showPermission() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGame(String str, boolean z) {
        try {
            if (this.mPackageManager == null) {
                this.mPackageManager = this.mContext.getPackageManager();
            }
            Intent launchIntentForPackage = this.mPackageManager.getLaunchIntentForPackage(str);
            launchIntentForPackage.setFlags(268435456);
            launchIntentForPackage.putExtra("start_from_heartservice_app_lock", true);
            if (!z) {
                startActivity(launchIntentForPackage);
                return;
            }
            PackageInfo packageInfoAsUser = getPackageInfoAsUser(this.mContext.getPackageManager(), str, 0, HighLightsUtils.isNubiaOS() ? HighLightsUtils.NUBIA_TWIN_USERID : 999);
            if (packageInfoAsUser != null) {
                startActivityAsUser(this.mContext, launchIntentForPackage, null, UserHandle.getUserHandleForUid(packageInfoAsUser.applicationInfo.uid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTitleItemState(TextView textView, boolean z) {
        try {
            textView.setCompoundDrawables(null, null, null, z ? this.mChoiceDrawable : this.mUnChoiceDrawable);
            if (getTitleColor(z) != null) {
                textView.setTextColor(getTitleColor(z));
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed updateTitleItemState.", e);
        }
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment
    protected View createMainView() {
        LogUtil.d(TAG, "******createMainView");
        if (this.m_activity == null) {
            return null;
        }
        return View.inflate(this.m_activity, R.layout.gcs_gamecenter_fragment_record_old, null);
    }

    public Drawable cropCenterDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return BitmapUtils.convertBitmapToDrawable(BitmapUtils.bitmapRound(BitmapUtils.getZoomImage(BitmapUtils.convertDrawableToBitmap(drawable), 144.0d, 144.0d, true), 34.0f));
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public Drawable getIcon(String str) {
        if (this.mPackageManager == null) {
            this.mPackageManager = this.mContext.getPackageManager();
        }
        try {
            return this.mPackageManager.getApplicationInfo(str, 0).loadIcon(this.mPackageManager);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Drawable getTwinIcon(String str, Drawable drawable) {
        if (this.mPackageManager == null) {
            this.mPackageManager = this.mContext.getPackageManager();
        }
        try {
            PackageInfo packageInfoAsUser = getPackageInfoAsUser(this.mContext.getPackageManager(), str, 0, HighLightsUtils.isNubiaOS() ? HighLightsUtils.NUBIA_TWIN_USERID : 999);
            if (packageInfoAsUser != null) {
                return this.mPackageManager.getUserBadgedIcon(drawable, UserHandle.getUserHandleForUid(packageInfoAsUser.applicationInfo.uid));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadGoogleGallery() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(GOOGLE_PHOTO_PACKAGE, GOOGLE_PHOTO_ACTIVITY));
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    public void loadVideo() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("cn.nubia.gallery3d", "cn.nubia.gallery3d.app.Gallery"));
            intent.putExtra("is_game_highlights", "cn.nubia.gamehighlights");
            intent.setType("video/*");
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    public void loadZteGallery() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.gallery3d", "com.zte.gallery3d.activity.launcher.MainGallery"));
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            LogUtil.d(TAG, "Exception" + e);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d(TAG, "******onCreate");
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.mPackageManager = activity.getPackageManager();
        this.mChoiceDrawable = getResources().getDrawable(R.drawable.gcs_record_choice);
        this.mUnChoiceDrawable = getResources().getDrawable(R.drawable.gcs_record_unchoice);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_game_record_drawable_marginTop);
        Drawable drawable = this.mChoiceDrawable;
        drawable.setBounds(0, dimensionPixelSize, drawable.getMinimumWidth(), this.mChoiceDrawable.getMinimumHeight());
        Drawable drawable2 = this.mUnChoiceDrawable;
        drawable2.setBounds(0, dimensionPixelSize, drawable2.getMinimumWidth(), this.mUnChoiceDrawable.getMinimumHeight());
        this.mPreviewThread.start();
        this.mThreadHandler = new Handler(this.mPreviewThread.getLooper());
        this.mIsFirst = true;
        showPermission();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mVodeoRecyclerView.getVisibility() == 0 || this.mImageRecyclerView.getVisibility() == 0) {
            exitAnimation(this.mGameListLl);
        }
        if (this.emptyView.getVisibility() == 0) {
            exitAnimation(this.emptyView);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 2777) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                LogUtil.e(TAG, "External Storage permissions need to be granted !");
                if (!shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE")) {
                    neverDisplayPermissionDialog();
                    LogUtil.d(TAG, "External Storage permissions never display forever.");
                }
            } else {
                LogUtil.e(TAG, "permission granted!");
                this.mThreadHandler.post(this.mGetGameHightLightsDataRunnable);
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        LogUtil.d(TAG, "******onStart");
        this.mThreadHandler.post(this.mGetGameHightLightsDataRunnable);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LogUtil.d(TAG, "******onViewCreated");
        this.hs = (HorizontalScrollView) view.findViewById(R.id.hs);
        this.linear = (LinearLayout) view.findViewById(R.id.liner);
        this.emptyView = (LinearLayout) view.findViewById(R.id.emptyView);
        this.mEmptyTitle = (TextView) view.findViewById(R.id.empty_title);
        this.videoCategory = (LinearLayout) view.findViewById(R.id.gcs_game_video_record_category);
        this.ImageCategory = (LinearLayout) view.findViewById(R.id.gcs_game_screenshot_record_category);
        this.tileDivider = view.findViewById(R.id.tile_divider);
        this.videoDivider = view.findViewById(R.id.tile_video_divider);
        this.imageDivider = view.findViewById(R.id.tile_image_divider);
        this.icon = (ImageView) view.findViewById(R.id.gcs_iv_start_game);
        this.mGameListLl = (LinearLayout) view.findViewById(R.id.gcs_game_list);
        this.mVodeoRecyclerView = (RecyclerView) view.findViewById(R.id.gcs_game_video_list_recycler);
        this.mImageRecyclerView = (RecyclerView) view.findViewById(R.id.gcs_game_screenshot_list_recycler);
        this.mRecordMoreVideo = (LinearLayout) view.findViewById(R.id.gcs_game_video_record_more);
        this.mRecordMoreImage = (LinearLayout) view.findViewById(R.id.gcs_game_screenshot_more);
        this.startGameView = (LinearLayout) view.findViewById(R.id.gcs_ll_start_game);
        this.mVideoLayoutParams = this.mVodeoRecyclerView.getLayoutParams();
        this.mRecordMoreVideo.setOnClickListener(this.mRecordClickListener);
        this.mRecordMoreImage.setOnClickListener(this.mRecordClickListener);
        this.startGameView.setOnClickListener(this.mRecordClickListener);
        this.videoCategory.setVisibility(8);
        this.ImageCategory.setVisibility(8);
        this.tileDivider.setVisibility(8);
        this.videoDivider.setVisibility(8);
        this.imageDivider.setVisibility(8);
        this.emptyView.setVisibility(8);
        this.startGameView.setVisibility(8);
        TextView textView = this.mEmptyTitle;
        if (textView != null) {
            textView.setText(HighLightsAIUtils.getEmptyText());
        }
    }

    @Override // cn.nubia.gamecenter.settings.records.StartInfo
    public void setRMTPackageName(String str) {
        if (str == null) {
            this.mStartPackage = "";
        } else {
            this.mStartPackage = str;
        }
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, cn.nubia.gamecenter.settings.FragmentInterface
    public void setTestMode() {
        this.m_tester = new RecordsTester();
        this.mGameApps = new ArrayList();
        ArrayList<String> arrayList = new ArrayList<>();
        this.mGamePackagesName = arrayList;
        this.m_tester.addBaseInfo((ArrayList) this.mGameApps, arrayList);
        LinearLayout linearLayout = this.linear;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        initInstallPackageMap();
        initAppAddList();
        initGameList();
        initTitles();
        initView();
    }

    public void startActivityAsUser(Object obj, Intent intent, Bundle bundle, UserHandle userHandle) {
        try {
            Method method = Activity.class.getMethod("startActivityAsUser", Intent.class, Bundle.class, UserHandle.class);
            method.setAccessible(true);
            method.invoke(obj, intent, bundle, userHandle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
