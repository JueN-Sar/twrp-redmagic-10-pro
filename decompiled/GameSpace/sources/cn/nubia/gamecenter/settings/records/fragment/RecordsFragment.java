package cn.nubia.gamecenter.settings.records.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LauncherApps;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.widget.CheckedTextView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.common.C;
import androidx.media3.common.PlaybackException;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.BaseFragment;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.records.RecordsTester;
import cn.nubia.gamecenter.settings.records.StartInfo;
import cn.nubia.gamecenter.settings.records.adapter.RecordsRecyclerAdapter;
import cn.nubia.gamecenter.settings.records.bean.HighlightsFile;
import cn.nubia.gamecenter.settings.records.bean.Position;
import cn.nubia.gamecenter.settings.records.fragment.RecordsFragment;
import cn.nubia.gamecenter.settings.records.utils.HighLightsAIUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsFileUtils;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.records.utils.ListUtils;
import cn.nubia.gamecenter.settings.records.view.RoundImageDrawable;
import cn.nubia.gamecenter.settings.records.view.WrapContentLinearLayoutManager;
import cn.nubia.gamecenter.settings.recordsdb.HighLightsDb;
import cn.nubia.gamecenter.settings.recordsdb.RTimeDataBaseHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamelauncher.model.AppAddModel;
import com.bumptech.glide.Glide;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class RecordsFragment extends BaseFragment implements FragmentInterface, StartInfo {
    private static final int COLOR_MENU_NORMAL = R.color.game_name_text_normal_color;
    private static final int COLOR_MENU_SELECTED = R.color.game_name_text_selected_color;
    private static final String HIGHLIGHTS = "highlights";
    private static final int MSG_GAME_DATA = 2;
    private static final int MSG_UPDATE_DATA = 1;
    private static final String TAG = "RecordsActivity";
    private static final String ZTE_FEATURE_GAME_CENTER_OTHER_OPTIONS = "ZTE_FEATURE_GAME_CENTER_OTHER_OPTIONS";
    private RecordsRecyclerAdapter adapter;
    private LinearLayout emptyView;
    private int firstPos;
    private HorizontalScrollView hs;
    private ImageView icon;
    private LinearLayout linear;
    private WrapContentLinearLayoutManager linearLayoutManager;
    private CheckedTextView mAll;
    private Drawable mChoiceDrawable;
    private Context mContext;
    private AlertDialog mDialog;
    private TextView mEmptyTitle;
    private AnimatorSet mEntryAnimationSet;
    private AnimatorSet mExitAnimationSet;
    private LinearLayout mHeaderPanel;
    private CheckedTextView mImages;
    private CheckedTextView mLights;
    private PackageManager mPackageManager;
    private HandlerThread mPreviewThread;
    private Handler mThreadHandler;
    private Drawable mUnChoiceDrawable;
    private CheckedTextView mVideos;
    private int mWidth;
    private ColorStateList m_normalColor;
    private ColorStateList m_selColor;
    private RecordsTester m_tester;
    private int offset;
    private RecyclerView recyclerView;
    private ArrayList<ShortcutInfo> shortcutInfos;
    private LinearLayout startGameView;
    private View tileDivider;
    private Map<String, String> mInstallMap = new HashMap();
    private Map<String, String> mGameMap = new HashMap();
    private Map<String, String> mDupGameMap = new HashMap();
    private List<String> mGameApps = new ArrayList();
    private List<String> mGamePackagesName = new ArrayList();
    private HashMap<String, Integer> mPreViewHashMap = new HashMap<>();
    private LinkedHashMap<String, LinkedHashMap<String, ArrayList<HighlightsFile>>> allFilesMap = new LinkedHashMap<>();
    private ArrayList<String> dateList = new ArrayList<>();
    private ArrayList<HighlightsFile> fileLists = new ArrayList<>();
    private LinkedHashMap<String, ArrayList<HighlightsFile>> mGameData = new LinkedHashMap<>();
    private ArrayList<TextView> titlesView = new ArrayList<>();
    private int mGameTabIndex = 0;
    private int mTypeIndex = 0;
    private String mStartPackage = "";
    private String mAppName = "";
    private String mCurrentPackage = "";
    private String currentLoadedPackage = "";
    private HashMap<String, Position> positionHashMap = new HashMap<>();
    private boolean mIsFirst = true;
    private boolean mIsOpenHighLights = false;
    private LauncherApps mLauncherApps = null;
    Runnable mRefreshHeaderPanelRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.2
        @Override // java.lang.Runnable
        public void run() {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) RecordsFragment.this.mHeaderPanel.getLayoutParams();
            if (layoutParams.topMargin < -90) {
                layoutParams.topMargin = 60;
                RecordsFragment.this.mHeaderPanel.setLayoutParams(layoutParams);
                RecordsFragment.this.mHeaderPanel.requestLayout();
            }
        }
    };
    Runnable mGetGameHighLightsDataRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.3
        @Override // java.lang.Runnable
        public void run() {
            try {
                LogUtil.d(RecordsFragment.TAG, "****** mGetGameHighLightsDataRunnable");
                RecordsFragment.this.initInstallPackageMap();
                RecordsFragment.this.initAppAddList();
                RecordsFragment.this.initShortCut();
                RecordsFragment.this.refreshTimeDB();
                RecordsFragment.this.initGameList();
                RecordsFragment.this.initAllFileMap();
                Message obtainMessage = RecordsFragment.this.mHandler.obtainMessage();
                obtainMessage.what = 2;
                RecordsFragment.this.mHandler.sendMessage(obtainMessage);
            } catch (Exception e) {
                LogUtil.d(RecordsFragment.TAG, "Exception" + e);
            }
        }
    };
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.4

        /* renamed from: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment$4$1, reason: invalid class name */
        class AnonymousClass1 implements RecordsRecyclerAdapter.OnDataChangeListener {
            AnonymousClass1() {
            }

            /* renamed from: lambda$onDataChanged$0$cn-nubia-gamecenter-settings-records-fragment-RecordsFragment$4$1, reason: not valid java name */
            /* synthetic */ void m213x14bfe7b3(String str, HighlightsFile highlightsFile) {
                RecordsFragment.this.applyHighlightFileRemovedFromStorage(str, highlightsFile);
            }

            @Override // cn.nubia.gamecenter.settings.records.adapter.RecordsRecyclerAdapter.OnDataChangeListener
            public void onDataChanged(final String str, final HighlightsFile highlightsFile) {
                LogUtil.e(RecordsFragment.TAG, "onDataChanged mGameData " + RecordsFragment.this.mGameData.size() + ", mList = " + RecordsFragment.this.dateList.size());
                RecordsFragment.this.mHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment$4$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        RecordsFragment.AnonymousClass4.AnonymousClass1.this.m213x14bfe7b3(str, highlightsFile);
                    }
                });
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                RecordsFragment.this.refreshGameHighLightsData();
                return;
            }
            if (RecordsFragment.this.adapter != null && !Objects.equals(RecordsFragment.this.mCurrentPackage, RecordsFragment.this.currentLoadedPackage)) {
                Position position = (Position) RecordsFragment.this.positionHashMap.get(RecordsFragment.this.mCurrentPackage);
                if (position != null) {
                    RecordsFragment.this.linearLayoutManager.scrollToPositionWithOffset(position.getPos(), position.getOffset());
                }
                RecordsFragment.this.adapter.notifyDataSetChanged();
            }
            if (RecordsFragment.this.dateList.size() == 0) {
                RecordsFragment recordsFragment = RecordsFragment.this;
                recordsFragment.setEmptyTitle(recordsFragment.mTypeIndex);
                if (RecordsFragment.this.emptyView != null) {
                    RecordsFragment.this.emptyView.setVisibility(0);
                }
                if (RecordsFragment.this.recyclerView != null) {
                    RecordsFragment.this.recyclerView.setVisibility(8);
                }
            } else {
                if (RecordsFragment.this.emptyView != null) {
                    RecordsFragment.this.emptyView.setVisibility(8);
                }
                if (RecordsFragment.this.recyclerView != null) {
                    RecordsFragment.this.recyclerView.setVisibility(0);
                }
                if (RecordsFragment.this.adapter == null) {
                    RecordsFragment.this.adapter = new RecordsRecyclerAdapter(RecordsFragment.this.mContext, RecordsFragment.this.mGameData, RecordsFragment.this.dateList, RecordsFragment.this.mPreViewHashMap, RecordsFragment.this.mWidth);
                    RecordsFragment.this.recyclerView.setLayoutManager(RecordsFragment.this.linearLayoutManager);
                    RecordsFragment.this.recyclerView.setAdapter(RecordsFragment.this.adapter);
                    RecordsFragment.this.adapter.setOnDataChangeListener(new AnonymousClass1());
                } else {
                    RecordsFragment.this.adapter.setDataAndList(RecordsFragment.this.mGameData, RecordsFragment.this.dateList, RecordsFragment.this.mPreViewHashMap);
                    if (RecordsFragment.this.currentLoadedPackage != null && Objects.equals(RecordsFragment.this.currentLoadedPackage, RecordsFragment.this.mCurrentPackage) && RecordsFragment.this.recyclerView != null && RecordsFragment.this.linearLayoutManager != null) {
                        RecordsFragment.this.recyclerView.setLayoutManager(RecordsFragment.this.linearLayoutManager);
                        RecordsFragment.this.recyclerView.setAdapter(RecordsFragment.this.adapter);
                    }
                }
            }
            RecordsFragment recordsFragment2 = RecordsFragment.this;
            recordsFragment2.currentLoadedPackage = recordsFragment2.mCurrentPackage;
        }
    };
    private int m_textPadding = -1;
    private int m_curSelId = -1;
    private View.OnClickListener mRecordClickListener = new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (R.id.gcs_game_high_light_header_panel_all == id || R.id.gcs_game_high_light_header_panel_auto_all == id) {
                if (RecordsFragment.this.mTypeIndex == 0) {
                    return;
                }
                RecordsFragment.this.mTypeIndex = 0;
                RecordsFragment recordsFragment = RecordsFragment.this;
                recordsFragment.setChecked(recordsFragment.mTypeIndex);
                RecordsFragment recordsFragment2 = RecordsFragment.this;
                recordsFragment2.requireFileData(recordsFragment2.mGameTabIndex, false);
            } else if (R.id.gcs_game_high_light_header_panel_video == id || R.id.gcs_game_high_light_header_panel_auto_video == id) {
                if (RecordsFragment.this.mTypeIndex == 1) {
                    return;
                }
                RecordsFragment.this.mTypeIndex = 1;
                RecordsFragment recordsFragment3 = RecordsFragment.this;
                recordsFragment3.setChecked(recordsFragment3.mTypeIndex);
                RecordsFragment recordsFragment4 = RecordsFragment.this;
                recordsFragment4.requireFileData(recordsFragment4.mGameTabIndex, false);
            } else if (R.id.gcs_game_high_light_header_panel_image == id || R.id.gcs_game_high_light_header_panel_auto_image == id) {
                if (RecordsFragment.this.mTypeIndex == 2) {
                    return;
                }
                RecordsFragment.this.mTypeIndex = 2;
                RecordsFragment recordsFragment5 = RecordsFragment.this;
                recordsFragment5.setChecked(recordsFragment5.mTypeIndex);
                RecordsFragment recordsFragment6 = RecordsFragment.this;
                recordsFragment6.requireFileData(recordsFragment6.mGameTabIndex, false);
            } else if (R.id.gcs_game_high_light_header_panel_auto_lights == id) {
                if (RecordsFragment.this.mTypeIndex == 3) {
                    return;
                }
                RecordsFragment.this.mTypeIndex = 3;
                RecordsFragment recordsFragment7 = RecordsFragment.this;
                recordsFragment7.setChecked(recordsFragment7.mTypeIndex);
                RecordsFragment recordsFragment8 = RecordsFragment.this;
                recordsFragment8.requireFileData(recordsFragment8.mGameTabIndex, false);
            } else if (id == R.id.gcs_ll_start_game) {
                int i = RecordsFragment.this.m_curSelId == -1 ? 0 : RecordsFragment.this.m_curSelId;
                if (i < RecordsFragment.this.mGameApps.size() && i < RecordsFragment.this.mGamePackagesName.size()) {
                    String str = (String) RecordsFragment.this.mGamePackagesName.get(i);
                    String str2 = (String) RecordsFragment.this.mGameApps.get(i);
                    if (RecordsFragment.this.isHasDoubleApp(str)) {
                        RecordsFragment.this.showDialog(str, str2);
                    } else if (str.contains("com.tencent.mm@")) {
                        RecordsFragment recordsFragment9 = RecordsFragment.this;
                        recordsFragment9.startShortcut(recordsFragment9.shortcutInfos, 0, i);
                    } else {
                        RecordsFragment.this.startGame(str, false);
                    }
                }
            } else {
                if (RecordsFragment.this.m_curSelId == id) {
                    return;
                }
                RecordsFragment.this.updatePositionHashMap();
                RecordsFragment.this.m_curSelId = id;
                RecordsFragment.this.mStartPackage = "";
                int i2 = 0;
                while (i2 < RecordsFragment.this.mGameApps.size()) {
                    RecordsFragment recordsFragment10 = RecordsFragment.this;
                    recordsFragment10.updateTitleItemState((TextView) recordsFragment10.titlesView.get(i2), i2 == id);
                    if (i2 == id) {
                        RecordsFragment.this.mGameTabIndex = i2;
                        RecordsFragment.this.isDisplayView(i2);
                        if (RecordsFragment.this.mInstallMap.containsValue(RecordsFragment.this.mGamePackagesName.get(RecordsFragment.this.mGameTabIndex))) {
                            RecordsFragment.this.startGameView.setAlpha(1.0f);
                            RecordsFragment.this.startGameView.setVisibility(0);
                            RecordsFragment recordsFragment11 = RecordsFragment.this;
                            RecordsFragment.this.icon.setImageDrawable(new RoundImageDrawable(recordsFragment11.getBitmap(recordsFragment11.mGameTabIndex)));
                        } else {
                            RecordsFragment.this.startGameView.setVisibility(8);
                        }
                        RecordsFragment.this.recyclerView.stopScroll();
                        RecordsFragment.this.mHandler.removeCallbacks(RecordsFragment.this.mSetStartViewAlphaRunnable);
                        RecordsFragment.this.mHandler.postDelayed(RecordsFragment.this.mSetStartViewAlphaRunnable, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                    }
                    i2++;
                }
            }
            LogUtil.d(RecordsFragment.TAG, "******onClick mGameTabIndex=" + RecordsFragment.this.mGameTabIndex);
        }
    };
    Runnable mSetStartViewAlphaRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.6
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
    Runnable mGetFileRunnable = new Runnable() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.7
        @Override // java.lang.Runnable
        public void run() {
            try {
                LogUtil.d(RecordsFragment.TAG, "****** mGetVideoPhotoAndTimeRunnable mCurrentPackage = " + RecordsFragment.this.mCurrentPackage + ", mAppName = " + RecordsFragment.this.mAppName);
                RecordsFragment.this.updateDatas();
                HighLightsFileUtils.queryPreViewHashMap(RecordsFragment.this.mContext, RecordsFragment.this.mPreViewHashMap);
                Message obtainMessage = RecordsFragment.this.mHandler.obtainMessage();
                obtainMessage.what = 1;
                RecordsFragment.this.mHandler.sendMessage(obtainMessage);
            } catch (Exception e) {
                LogUtil.d(RecordsFragment.TAG, "Exception" + e);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void applyHighlightFileRemovedFromStorage(String str, HighlightsFile highlightsFile) {
        LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap;
        if (TextUtils.isEmpty(this.mCurrentPackage) || (linkedHashMap = this.allFilesMap.get(this.mCurrentPackage)) == null) {
            return;
        }
        ArrayList<HighlightsFile> arrayList = linkedHashMap.get(str);
        if (arrayList != null && highlightsFile != null) {
            arrayList.remove(highlightsFile);
            if (arrayList.isEmpty()) {
                linkedHashMap.remove(str);
            }
        }
        if (linkedHashMap.isEmpty()) {
            if (this.allFilesMap.containsKey(this.mCurrentPackage)) {
                this.allFilesMap.remove(this.mCurrentPackage);
                int indexOf = this.mGamePackagesName.indexOf(this.mCurrentPackage);
                if (indexOf >= 0) {
                    this.mGamePackagesName.remove(indexOf);
                    if (indexOf < this.mGameApps.size()) {
                        this.mGameApps.remove(indexOf);
                    }
                }
                this.mGameMap.remove(this.mCurrentPackage);
                this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
                return;
            }
            return;
        }
        if (this.mGameData.isEmpty()) {
            setEmptyTitle(this.mTypeIndex);
            LinearLayout linearLayout = this.emptyView;
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView != null) {
                recyclerView.setVisibility(8);
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

    private Bitmap getAppIcon(String str) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return drawableToBitmap(packageManager.getApplicationIcon(packageManager.getApplicationInfo(str, 0)));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getBitmap(int i) {
        Bitmap bitmap = null;
        try {
            if (this.mGamePackagesName.get(i).contains("com.tencent.mm@")) {
                Iterator<ShortcutInfo> it = this.shortcutInfos.iterator();
                while (it.hasNext()) {
                    ShortcutInfo next = it.next();
                    if (this.mGameApps.get(i).contentEquals(next.getShortLabel())) {
                        bitmap = getShortcutBitmapIcon(next);
                        break;
                    }
                }
            } else {
                bitmap = getAppIcon(this.mGamePackagesName.get(i));
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "getBitmap: ", e);
        }
        return bitmap;
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

    private void init() {
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        this.mPackageManager = activity.getPackageManager();
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.gcs_game_record_drawable_marginTop);
        this.mChoiceDrawable = getResources().getDrawable(R.drawable.gcs_record_choice);
        this.mUnChoiceDrawable = getResources().getDrawable(R.drawable.gcs_record_unchoice);
        Drawable drawable = this.mChoiceDrawable;
        drawable.setBounds(0, dimensionPixelSize, drawable.getMinimumWidth(), this.mChoiceDrawable.getMinimumHeight());
        Drawable drawable2 = this.mUnChoiceDrawable;
        drawable2.setBounds(0, dimensionPixelSize, drawable2.getMinimumWidth(), this.mUnChoiceDrawable.getMinimumHeight());
        this.linearLayoutManager = new WrapContentLinearLayoutManager(this.mContext, 1, false);
        HandlerThread handlerThread = new HandlerThread("PreviewThreadIO");
        this.mPreviewThread = handlerThread;
        handlerThread.start();
        this.mThreadHandler = new Handler(this.mPreviewThread.getLooper());
        this.mIsFirst = true;
        String str = FeatureUtil.get(ZTE_FEATURE_GAME_CENTER_OTHER_OPTIONS, null);
        if (HighLightsUtils.isInternal()) {
            this.mIsOpenHighLights = false;
        } else if ((!TextUtils.isEmpty(str) && str.contains("highlights")) || TextUtils.isEmpty(str)) {
            this.mIsOpenHighLights = true;
        }
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        this.mWidth = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.shortcutInfos = listShortcutsFromLauncher();
        initLauncherApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAllFileMap() {
        this.allFilesMap.clear();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        new ArrayList();
        if (HighLightsUtils.isInternal()) {
            LinkedHashMap<String, ArrayList<HighlightsFile>> queryVideoFiles = HighLightsFileUtils.queryVideoFiles(this.mContext, HighLightsUtils.getInternalVideoPath(), this.mGamePackagesName, this.mGameMap, this.mDupGameMap);
            LinkedHashMap<String, ArrayList<HighlightsFile>> queryVideoFiles2 = HighLightsFileUtils.queryVideoFiles(this.mContext, HighLightsUtils.getVideoDataPath(), this.mGamePackagesName, this.mGameMap, this.mDupGameMap);
            for (Map.Entry<String, ArrayList<HighlightsFile>> entry : queryVideoFiles.entrySet()) {
                ArrayList<HighlightsFile> arrayList = queryVideoFiles2.get(entry.getKey());
                if (arrayList == null) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                } else {
                    linkedHashMap.put(entry.getKey(), HighLightsFileUtils.mergeLists(entry.getValue(), arrayList));
                    queryVideoFiles2.remove(entry.getKey());
                }
            }
            if (queryVideoFiles2.size() != 0) {
                linkedHashMap.putAll(queryVideoFiles2);
            }
        } else {
            linkedHashMap = HighLightsFileUtils.queryVideoFiles(this.mContext, HighLightsUtils.getVideoDataPath(), this.mGamePackagesName, this.mGameMap, this.mDupGameMap);
        }
        LinkedHashMap<String, ArrayList<HighlightsFile>> queryImageFiles = HighLightsFileUtils.queryImageFiles(this.mContext, HighLightsUtils.getImageDataPath(), this.mGamePackagesName, this.mGameMap, this.mDupGameMap);
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            ArrayList<HighlightsFile> arrayList2 = queryImageFiles.get(entry2.getKey());
            if (arrayList2 == null) {
                linkedHashMap2.put((String) entry2.getKey(), (ArrayList) entry2.getValue());
            } else {
                linkedHashMap2.put((String) entry2.getKey(), HighLightsFileUtils.mergeLists((List) entry2.getValue(), arrayList2));
                queryImageFiles.remove(entry2.getKey());
            }
        }
        if (queryImageFiles.size() != 0) {
            linkedHashMap2.putAll(queryImageFiles);
        }
        for (Map.Entry entry3 : linkedHashMap2.entrySet()) {
            LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap3 = this.allFilesMap.get(entry3.getKey());
            if (linkedHashMap3 == null) {
                linkedHashMap3 = new LinkedHashMap<>();
            }
            HighLightsFileUtils.sortFile((List) entry3.getValue(), linkedHashMap3);
            this.allFilesMap.put((String) entry3.getKey(), linkedHashMap3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAppAddList() {
        this.mGameMap.clear();
        this.mGameApps.clear();
        this.mGamePackagesName.clear();
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse(HighLightsUtils.APPADD_URI_NO_NOTIFY), null, null, null, null);
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
                    boolean isVideoExist = HighLightsFileUtils.isVideoExist(this.mContext, str, string);
                    boolean isImageExist = HighLightsFileUtils.isImageExist(this.mContext, string);
                    LogUtil.d(TAG, "******initAppAddList hasVideo =" + isVideoExist + ",hasImage =" + isImageExist + ",gameName =" + string + ", name =" + str);
                    if (isVideoExist || isImageExist) {
                        if (string != null && !"".equals(string) && !"".equals(string2) && !"null".equals(string) && !"null".equals(string2)) {
                            this.mGameMap.put(str, string);
                            this.mGamePackagesName.add(str);
                        }
                    }
                }
                LogUtil.i(TAG, "******getGameList mGamePackagesName =" + this.mGamePackagesName);
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
        this.mDupGameMap.clear();
        try {
            query = this.mContext.getContentResolver().query(RTimeDataBaseHelper.REDMAGICTIME_QUERY_URI, new String[]{"packageName", "appName"}, null, null, "_id DESC");
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed load game app data.", e);
        }
        if (query == null) {
            if (query != null) {
                query.close();
                return;
            }
            return;
        }
        try {
            int columnIndex = query.getColumnIndex("appName");
            int columnIndex2 = query.getColumnIndex("packageName");
            query.moveToPosition(-1);
            while (query.moveToNext()) {
                String string = query.getString(columnIndex);
                String string2 = query.getString(columnIndex2);
                LogUtil.d(TAG, "******initGameList packagesName =" + string2 + ",mGameMap.get(packagesName) =" + this.mGameMap.get(string2));
                if (this.mGameMap.get(string2) != null) {
                    this.mDupGameMap.put(string, this.mGameMap.get(string2));
                } else if (!this.mGamePackagesName.contains(string2)) {
                    boolean isVideoExist = HighLightsFileUtils.isVideoExist(this.mContext, string2, string);
                    boolean isImageExist = HighLightsFileUtils.isImageExist(this.mContext, string);
                    LogUtil.d(TAG, "******initGameList hasVideo =" + isVideoExist + ",hasImage =" + isImageExist + ",gamename =" + string + ", packagesName =" + string2);
                    if (isVideoExist || isImageExist) {
                        if (string != null && string2 != null && !"".equals(string) && !"".equals(string2) && !"null".equals(string) && !"null".equals(string2)) {
                            this.mGameMap.put(string2, string);
                            this.mGamePackagesName.add(string2);
                        }
                    }
                }
            }
            Collections.sort(this.mGamePackagesName);
            for (int i = 0; i < this.mGamePackagesName.size(); i++) {
                this.mGameApps.add(this.mGameMap.get(this.mGamePackagesName.get(i)));
                if (this.mStartPackage.equals(this.mGamePackagesName.get(i))) {
                    this.m_curSelId = i;
                }
            }
            if (query != null) {
                query.close();
            }
            LogUtil.i(TAG, "******getGameList mGameApps =" + this.mGameApps + ",mGamePackagesName =" + this.mGamePackagesName);
        } finally {
        }
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

    private void initLauncherApps() {
        LogUtil.i(TAG, "initLauncherApps() - run())");
        try {
            LauncherApps launcherApps = (LauncherApps) this.mContext.getSystemService("launcherapps");
            this.mLauncherApps = launcherApps;
            LogUtil.i(TAG, "initLauncherApps() - hasPermission : " + launcherApps.hasShortcutHostPermission());
        } catch (Exception e) {
            LogUtil.e(TAG, "initLauncherApps() - e : " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initShortCut() {
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse(HighLightsUtils.URI_GAME_SPACE_SHORTCUT), null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("label");
                        int columnIndex2 = query.getColumnIndex("hashcode");
                        do {
                            String string = query.getString(columnIndex);
                            String string2 = query.getString(columnIndex2);
                            LogUtil.d(TAG, "queryShortCut label : " + string + ", labelHashCode : " + string2);
                            String str = "com.tencent.mm@" + string2;
                            this.mInstallMap.put(string, str);
                            boolean isVideoExist = HighLightsFileUtils.isVideoExist(this.mContext, str, string);
                            boolean isImageExist = HighLightsFileUtils.isImageExist(this.mContext, string);
                            if ((isVideoExist || isImageExist) && !TextUtils.isEmpty(string) && !"null".equals(string)) {
                                this.mGameMap.put(str, string);
                                this.mGamePackagesName.add(str);
                            }
                        } while (query.moveToNext());
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed load shortcut data.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isDisplayView(int i) {
        requireFileData(i, true);
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

    private void neverDisplayPermissionDialog() {
        SharedPreferences.Editor edit = this.mContext.getSharedPreferences(HighLightsUtils.STORAGE_PERMISSION_DIALOG_OPER, 0).edit();
        edit.putInt(HighLightsUtils.NEVER_DISPLAY_STORAGE_PERMISSION_DIALOG, 1);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshGameHighLightsData() {
        refreshTitles();
        refreshView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTimeDB() {
        List<HighLightsDb> queryVideoFileFromMedia = HighLightsFileUtils.queryVideoFileFromMedia(this.mContext, HighLightsUtils.getVideoDataPath());
        if (HighLightsUtils.isInternal()) {
            queryVideoFileFromMedia.addAll(HighLightsFileUtils.queryVideoFileFromMedia(this.mContext, HighLightsUtils.getInternalVideoPath()));
        }
        queryVideoFileFromMedia.addAll(HighLightsFileUtils.queryImageFileFromImage(this.mContext, HighLightsUtils.getImageDataPath()));
        LogUtil.d(TAG, "******refreshTimeDB mNotInsertList =" + queryVideoFileFromMedia.size());
        List<HighLightsDb> queryRedMagicTime = HighLightsFileUtils.queryRedMagicTime(this.mContext);
        LogUtil.d(TAG, "******refreshTimeDB mInsertList =" + queryRedMagicTime.size());
        List<HighLightsDb> queryNullPackageName = HighLightsFileUtils.queryNullPackageName(this.mContext);
        LogUtil.d(TAG, "******refreshTimeDB mNullList =" + queryNullPackageName.size());
        if (queryNullPackageName.size() > 0) {
            ListUtils.getInstance().deleteVideoToDB(this.mContext, queryNullPackageName);
        }
        queryRedMagicTime.removeAll(queryNullPackageName);
        ListUtils.getInstance().removeHadAll(queryVideoFileFromMedia, queryRedMagicTime);
        if (queryVideoFileFromMedia.size() > 0) {
            queryVideoFileFromMedia = ListUtils.getInstance().parsingPath(queryVideoFileFromMedia, this.mInstallMap);
            ListUtils.getInstance().insertVideoToDB(this.mContext, queryVideoFileFromMedia);
        }
        if (queryRedMagicTime.size() > 0) {
            ListUtils.getInstance().deleteVideoToDB(this.mContext, queryRedMagicTime);
        }
        queryVideoFileFromMedia.clear();
        queryRedMagicTime.clear();
    }

    private void refreshTitles() {
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
                } else {
                    textView.setText(this.mGameApps.get(i));
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

    private void refreshView() {
        LogUtil.d(TAG, "******initView mGameTabIndex=" + this.mGameTabIndex);
        if (this.mGamePackagesName.size() > 0) {
            if (this.mGameTabIndex >= this.mGamePackagesName.size()) {
                this.mGameTabIndex = 0;
            }
            initGameTabIndex();
            if (this.mGameTabIndex < this.titlesView.size()) {
                updateTitleItemState(this.titlesView.get(this.mGameTabIndex), true);
            }
            this.emptyView.setVisibility(8);
            this.tileDivider.setVisibility(0);
            this.recyclerView.setVisibility(0);
            this.hs.setVisibility(0);
            Bitmap bitmap = this.mGamePackagesName.size() > 0 ? ("".equals(this.mStartPackage) || "cn.nubia.gamelauncher".equals(this.mStartPackage) || !this.mGamePackagesName.contains(this.mStartPackage)) ? getBitmap(this.mGameTabIndex) : this.mStartPackage.contains("com.tencent.mm@") ? getBitmap(this.mGamePackagesName.indexOf(this.mStartPackage)) : getAppIcon(this.mStartPackage) : null;
            if (bitmap != null) {
                this.icon.setImageDrawable(new RoundImageDrawable(bitmap));
            }
            this.startGameView.setAlpha(1.0f);
            this.mHandler.removeCallbacks(this.mSetStartViewAlphaRunnable);
            this.mHandler.postDelayed(this.mSetStartViewAlphaRunnable, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        } else {
            setEmptyTitle(this.mTypeIndex);
            this.emptyView.setVisibility(0);
            entryAnimation(this.emptyView);
            this.hs.setVisibility(8);
            this.tileDivider.setVisibility(8);
            this.startGameView.setVisibility(8);
            this.recyclerView.setVisibility(8);
            this.mHeaderPanel.setVisibility(8);
        }
        if (this.mGamePackagesName.isEmpty() || this.mGameApps.isEmpty()) {
            return;
        }
        if (this.mInstallMap.containsValue(this.mGamePackagesName.get(this.mGameTabIndex))) {
            this.startGameView.setVisibility(0);
        }
        try {
            boolean isVideoExist = HighLightsFileUtils.isVideoExist(this.mContext, this.mGamePackagesName.get(this.mGameTabIndex), this.mGameApps.get(this.mGameTabIndex));
            boolean isImageExist = HighLightsFileUtils.isImageExist(this.mContext, this.mGameApps.get(this.mGameTabIndex));
            LogUtil.d(TAG, "****** initView hasVideo =" + isVideoExist + ",videopath =" + this.mGamePackagesName.get(this.mGameTabIndex) + ", hasImage =" + isImageExist + "，imagepath =" + this.mGameApps.get(this.mGameTabIndex));
            if (isImageExist || isVideoExist) {
                this.mHeaderPanel.setVisibility(0);
                requireFileData(this.mGameTabIndex, false);
            }
        } catch (Exception e) {
            Log.e(TAG, "refreshView: ", e);
        }
        this.mIsFirst = false;
    }

    private void refreshView(View view) {
        this.hs = (HorizontalScrollView) view.findViewById(R.id.hs);
        this.linear = (LinearLayout) view.findViewById(R.id.liner);
        this.emptyView = (LinearLayout) view.findViewById(R.id.emptyView);
        this.mEmptyTitle = (TextView) view.findViewById(R.id.empty_title);
        this.tileDivider = view.findViewById(R.id.tile_divider);
        if (this.mIsOpenHighLights) {
            this.mHeaderPanel = (LinearLayout) view.findViewById(R.id.gcs_game_high_light_header_auto_panel);
            this.mAll = (CheckedTextView) view.findViewById(R.id.gcs_game_high_light_header_panel_auto_all);
            this.mVideos = (CheckedTextView) view.findViewById(R.id.gcs_game_high_light_header_panel_auto_video);
            this.mImages = (CheckedTextView) view.findViewById(R.id.gcs_game_high_light_header_panel_auto_image);
            CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.gcs_game_high_light_header_panel_auto_lights);
            this.mLights = checkedTextView;
            checkedTextView.setOnClickListener(this.mRecordClickListener);
        } else {
            this.mHeaderPanel = (LinearLayout) view.findViewById(R.id.gcs_game_high_light_header_panel);
            this.mAll = (CheckedTextView) view.findViewById(R.id.gcs_game_high_light_header_panel_all);
            this.mVideos = (CheckedTextView) view.findViewById(R.id.gcs_game_high_light_header_panel_video);
            this.mImages = (CheckedTextView) view.findViewById(R.id.gcs_game_high_light_header_panel_image);
        }
        this.recyclerView = (RecyclerView) view.findViewById(R.id.gcs_game_high_light_recycler);
        this.icon = (ImageView) view.findViewById(R.id.gcs_iv_start_game);
        this.startGameView = (LinearLayout) view.findViewById(R.id.gcs_ll_start_game);
        this.mAll.setOnClickListener(this.mRecordClickListener);
        this.mVideos.setOnClickListener(this.mRecordClickListener);
        this.mImages.setOnClickListener(this.mRecordClickListener);
        this.startGameView.setOnClickListener(this.mRecordClickListener);
        this.tileDivider.setVisibility(8);
        this.emptyView.setVisibility(8);
        this.startGameView.setVisibility(8);
        this.mHeaderPanel.setVisibility(8);
        this.mAll.setChecked(true);
        this.mTypeIndex = 0;
        setEmptyTitle(0);
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                try {
                    if (RecordsFragment.this.mContext == null) {
                        return;
                    }
                    if (i == 0) {
                        Glide.with(RecordsFragment.this.mContext).resumeRequests();
                    } else if (i == 1) {
                        Glide.with(RecordsFragment.this.mContext).pauseRequests();
                    }
                } catch (Exception unused) {
                    LogUtil.e(RecordsFragment.TAG, "****** onScrollStateChanged");
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                RecordsFragment recordsFragment = RecordsFragment.this;
                recordsFragment.firstPos = recordsFragment.linearLayoutManager.findFirstVisibleItemPosition();
                View findViewByPosition = RecordsFragment.this.linearLayoutManager.findViewByPosition(RecordsFragment.this.firstPos);
                if (findViewByPosition != null) {
                    RecordsFragment.this.offset = findViewByPosition.getTop() + PlaybackException.ERROR_CODE_PARENTAL_CONTROL_RESTRICTED;
                }
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) RecordsFragment.this.mHeaderPanel.getLayoutParams();
                if (i2 > 0) {
                    if (RecordsFragment.this.mHandler.hasCallbacks(RecordsFragment.this.mRefreshHeaderPanelRunnable)) {
                        RecordsFragment.this.mHandler.removeCallbacks(RecordsFragment.this.mRefreshHeaderPanelRunnable);
                    }
                    layoutParams.topMargin -= i2;
                    RecordsFragment.this.mHeaderPanel.setLayoutParams(layoutParams);
                    RecordsFragment.this.mHeaderPanel.requestLayout();
                    return;
                }
                if (layoutParams.topMargin < -90) {
                    if (RecordsFragment.this.mHandler.hasCallbacks(RecordsFragment.this.mRefreshHeaderPanelRunnable)) {
                        return;
                    }
                    RecordsFragment.this.mHandler.postDelayed(RecordsFragment.this.mRefreshHeaderPanelRunnable, 500L);
                } else {
                    if (layoutParams.topMargin <= -90 || layoutParams.topMargin >= 60) {
                        return;
                    }
                    layoutParams.topMargin -= i2;
                    RecordsFragment.this.mHeaderPanel.setLayoutParams(layoutParams);
                    RecordsFragment.this.mHeaderPanel.requestLayout();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requireFileData(int i, boolean z) {
        List<String> list;
        String str;
        List<String> list2 = this.mGameApps;
        if (list2 == null || list2.size() <= 0 || (list = this.mGamePackagesName) == null || i < 0 || i >= list.size() || i >= this.mGameApps.size() || (str = this.mGameApps.get(i)) == null) {
            return;
        }
        this.mAppName = str;
        String str2 = this.mGamePackagesName.get(i);
        this.mCurrentPackage = str2;
        if (z) {
            Position position = this.positionHashMap.get(str2);
            if (position != null) {
                this.mTypeIndex = position.getType();
            } else {
                this.mTypeIndex = 0;
            }
            setChecked(this.mTypeIndex);
        }
        this.mThreadHandler.post(this.mGetFileRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChecked(int i) {
        CheckedTextView checkedTextView;
        if (this.mIsOpenHighLights && (checkedTextView = this.mLights) != null) {
            if (i == 3) {
                this.mAll.setChecked(false);
                this.mImages.setChecked(false);
                this.mVideos.setChecked(false);
                this.mLights.setChecked(true);
            } else {
                checkedTextView.setChecked(false);
            }
        }
        if (i == 0) {
            this.mAll.setChecked(true);
            this.mImages.setChecked(false);
            this.mVideos.setChecked(false);
        } else if (i == 2) {
            this.mAll.setChecked(false);
            this.mImages.setChecked(true);
            this.mVideos.setChecked(false);
        } else if (i == 1) {
            this.mAll.setChecked(false);
            this.mImages.setChecked(false);
            this.mVideos.setChecked(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEmptyTitle(int i) {
        TextView textView = this.mEmptyTitle;
        if (textView == null) {
            return;
        }
        if (i != 0) {
            textView.setText(R.string.gcs_game_record_type_empty);
        } else {
            textView.setText(HighLightsAIUtils.getEmptyText());
        }
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
            textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecordsFragment.this.startGame(str, false);
                    RecordsFragment.this.mDialog.dismiss();
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecordsFragment.this.startGame(str, true);
                    RecordsFragment.this.mDialog.dismiss();
                }
            });
            AlertDialog create = new AlertDialog.Builder(this.mContext, R.style.Theme_Nubia_Dialog_Alert).setCustomTitle(inflate).setNegativeButton(R.string.gamemode_account_login_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.fragment.RecordsFragment.10
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
    public void updateDatas() {
        this.mGameData.clear();
        LinkedHashMap<String, ArrayList<HighlightsFile>> linkedHashMap = this.allFilesMap.get(this.mCurrentPackage);
        if (linkedHashMap == null) {
            return;
        }
        int i = this.mTypeIndex;
        if (i == 0) {
            this.mGameData.putAll(linkedHashMap);
        } else if (i == 1) {
            for (Map.Entry<String, ArrayList<HighlightsFile>> entry : linkedHashMap.entrySet()) {
                this.fileLists.clear();
                Iterator<HighlightsFile> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    HighlightsFile next = it.next();
                    if (next.getType() == 2) {
                        this.fileLists.add(next);
                    }
                }
                if (this.fileLists.size() != 0) {
                    this.mGameData.put(entry.getKey(), new ArrayList<>(this.fileLists));
                }
            }
        } else if (i == 2) {
            for (Map.Entry<String, ArrayList<HighlightsFile>> entry2 : linkedHashMap.entrySet()) {
                this.fileLists.clear();
                Iterator<HighlightsFile> it2 = entry2.getValue().iterator();
                while (it2.hasNext()) {
                    HighlightsFile next2 = it2.next();
                    if (next2.getType() == 1) {
                        this.fileLists.add(next2);
                    }
                }
                if (this.fileLists.size() != 0) {
                    this.mGameData.put(entry2.getKey(), new ArrayList<>(this.fileLists));
                }
            }
        } else if (i == 3) {
            for (Map.Entry<String, ArrayList<HighlightsFile>> entry3 : linkedHashMap.entrySet()) {
                this.fileLists.clear();
                Iterator<HighlightsFile> it3 = entry3.getValue().iterator();
                while (it3.hasNext()) {
                    HighlightsFile next3 = it3.next();
                    if (next3.getType() == 3) {
                        this.fileLists.add(next3);
                    }
                }
                if (this.fileLists.size() != 0) {
                    this.mGameData.put(entry3.getKey(), new ArrayList<>(this.fileLists));
                }
            }
        }
        this.dateList.clear();
        String str = null;
        for (Map.Entry<String, ArrayList<HighlightsFile>> entry4 : this.mGameData.entrySet()) {
            if (str == null || !str.equals(entry4.getKey())) {
                this.dateList.add(entry4.getKey());
                str = entry4.getKey();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePositionHashMap() {
        Position position = this.positionHashMap.get(this.mCurrentPackage);
        if (position == null) {
            this.positionHashMap.put(this.mCurrentPackage, new Position(this.firstPos, this.offset, this.mTypeIndex));
            return;
        }
        position.setPos(this.firstPos);
        position.setOffset(this.offset);
        position.setType(this.mTypeIndex);
        this.positionHashMap.put(this.mCurrentPackage, position);
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
        return View.inflate(this.m_activity, R.layout.gcs_gamecenter_fragment_record, null);
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

    public ActivityOptions getActivityOptions(int i) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchDisplayId(i);
        return makeBasic;
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

    public Bitmap getShortcutBitmapIcon(ShortcutInfo shortcutInfo) {
        Drawable shortcutIconDrawable = this.mLauncherApps.getShortcutIconDrawable(shortcutInfo, 0);
        return BitmapUtils.overlayBitmaps(BitmapUtils.convertDrawableToBitmap(shortcutIconDrawable), getAppIcon("com.tencent.mm"), 0.33f);
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

    public ArrayList<ShortcutInfo> listShortcutsFromLauncher() {
        Bundle bundle;
        try {
            bundle = this.mContext.getContentResolver().call(CommonUtil.getSecureUri(Uri.parse(AppAddModel.DYNAMIC_SHOW_HIDDEN_APPS_URI)), "getWechatShortcut", (String) null, (Bundle) null);
        } catch (Exception e) {
            LogUtil.e(TAG, " listShortcutsFromLauncher() e : " + e.getMessage());
            bundle = null;
        }
        LogUtil.d(TAG, "listShortcutsFromLauncher() result : " + bundle);
        if (bundle == null) {
            return null;
        }
        return bundle.getParcelableArrayList("shortcutInfoList");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d(TAG, "******onCreate");
        init();
        showPermission();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.positionHashMap.clear();
        if (this.emptyView.getVisibility() == 0) {
            exitAnimation(this.emptyView);
        }
        this.recyclerView.clearOnScrollListeners();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        LogUtil.d(TAG, "******onPause");
        updatePositionHashMap();
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
                this.mThreadHandler.post(this.mGetGameHighLightsDataRunnable);
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
        this.mThreadHandler.post(this.mGetGameHighLightsDataRunnable);
        if (this.mIsFirst) {
            return;
        }
        this.currentLoadedPackage = "";
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        LogUtil.d(TAG, "******onViewCreated");
        refreshView(view);
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
        initShortCut();
        initGameList();
        initAllFileMap();
        refreshTitles();
        refreshView();
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

    public void startShortcut(ArrayList<ShortcutInfo> arrayList, int i, int i2) {
        try {
            Iterator<ShortcutInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                ShortcutInfo next = it.next();
                if (this.mGameApps.get(i2).contentEquals(next.getShortLabel())) {
                    LogUtil.d(TAG, "startShortcut() info : " + next.toString());
                    LogUtil.d(TAG, "startShortcut(" + next.getId() + ") activity : " + next.getActivity() + ", label : " + ((CharSequence) Objects.requireNonNull(next.getShortLabel())).toString() + ", componentName : " + (next.getPackage() + "," + next.getActivity().getClassName()) + ", displayId=" + i);
                    this.mLauncherApps.startShortcut(next.getPackage(), next.getId(), null, getActivityOptions(i).toBundle(), next.getUserHandle());
                    return;
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "startShortcut: ", e);
        }
    }
}
