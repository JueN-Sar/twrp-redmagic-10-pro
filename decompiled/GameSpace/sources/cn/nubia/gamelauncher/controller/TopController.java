package cn.nubia.gamelauncher.controller;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.bean.RelevantBean;
import cn.nubia.gamelauncher.helper.Controller;
import cn.nubia.gamelauncher.helper.LobbySoundPoolHelper;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.observer.OperationKeyObserver;
import cn.nubia.gamelauncher.util.CommonUtil;
import cn.nubia.gamelauncher.util.GameCenterHelper;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.tgk.TgkHelper;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TopController implements View.OnClickListener, OperationKeyObserver.Callback {
    public static final int RELEVANT_ANIM_INTERVAL = 60000;
    public static PathInterpolator ROTATION_INTERPOLATOR = new PathInterpolator(1.0f, 1.4f, 0.72f, 0.82f);
    private static final String TAG = "Controller";
    IndicatorController mDetectorCtrl;
    private Button mIndicator;
    private Button mMora;
    private Button mNews;
    View mParent;
    private Group mPop;
    ImageView mPopBg;
    View mPopChild;
    FrameLayout mPopContent;
    TextView mPopTitle;
    private Button mRelevant;
    private Button mScore;
    private final Runnable mSelectedChangedRunnable;
    AppListItemBean mSelectedItem;
    ShortcutController mShortcutController;
    ImageView top_pop_title_bg;
    ArrayList<Button> mTopEntry = new ArrayList<>();
    ArrayList<ImageView> mDirections = new ArrayList<>();
    public long mLastAnimTime = 0;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public TopController(View view, ShortcutController shortcutController) {
        Runnable runnable = new Runnable() { // from class: cn.nubia.gamelauncher.controller.TopController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TopController.this.onSelectedChanged();
            }
        };
        this.mSelectedChangedRunnable = runnable;
        this.mShortcutController = shortcutController;
        initView(view);
        Controller.getInstance().addSelectedChangedListener(runnable);
        OperationKeyObserver.getInstance(getContext()).addCallback(this);
        doEntryDetector();
    }

    private boolean canDoNextAnim(AppListItemBean appListItemBean) {
        return this.mRelevant.getVisibility() == 8 || System.currentTimeMillis() - this.mLastAnimTime > 60000 || isSelectedItemChanged(appListItemBean);
    }

    private void clickIndicator() {
        Log.d("Controller", "top - clickIndicator() ");
        this.mIndicator.setBackgroundResource(R.drawable.top_indicator_expand);
        showAndFillPop(getIndicatorCtrl(), R.id.top_pop_direction_detector, getContext().getString(R.string.pop_title_default), false);
        LobbySoundPoolHelper.getInstance().play();
    }

    private void clickMora() {
        Log.d("Controller", "top - clickMora() ");
        try {
            if (!Build.DEVICE.contains("P688S01")) {
                Intent intent = new Intent();
                intent.setAction("intent.action.redmagickyi.main");
                intent.setFlags(268435456);
                getContext().startActivity(intent);
            } else if (isDigitalHumanStatusOn()) {
                gotoDigitalHumanSettings(getContext());
            } else {
                gotoMainSettings(getContext());
            }
            doTrack(AppAddModel.getInstance().getSelectedItem(), "lobby_mora_click");
        } catch (Exception e) {
            LogUtil.w("Controller", "click red magic Exception e " + e.getMessage());
        }
        LobbySoundPoolHelper.getInstance().play();
    }

    private void clickNews() {
        Log.d("Controller", "top - clickNews() ");
        this.mNews.setBackgroundResource(R.drawable.top_news_expand);
        showAndFillPop(getIndicatorCtrl(), R.id.top_pop_direction_news, getContext().getString(R.string.pop_title_default), false);
        LobbySoundPoolHelper.getInstance().play();
    }

    private void clickRelevant() {
        RelevantBean currentRelevant;
        Log.d("Controller", "top - clickRelevant() ");
        AppListItemBean selectedItem = AppAddModel.getInstance().getSelectedItem();
        if (selectedItem == null || (currentRelevant = selectedItem.getCurrentRelevant()) == null) {
            return;
        }
        GameCenterHelper.startOperation(getContext(), "gameplacesdk://appdetail?packageName=" + currentRelevant.pkg);
    }

    private void clickScore() {
        Log.d("Controller", "top - clickScore() ");
        this.mScore.setBackgroundResource(R.drawable.top_score_expand);
        showAndFillPop(ScoreRecordsController.getInstance().getScoreLayout(), R.id.top_pop_direction_score, getContext().getString(R.string.score_record), true);
        ScoreRecordsController.getInstance().requestData();
        LobbySoundPoolHelper.getInstance().play();
    }

    private void doEntryDetector() {
        if (GameSpaceConfig.supportIndicator()) {
            getIndicatorCtrl().doDetect();
        }
    }

    private void doTrack(AppListItemBean appListItemBean, String str) {
        if (CommonUtil.isInternalVersion() || appListItemBean == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NubiaTrackManager.EVENT_NAME, str);
        bundle.putString("app_name", appListItemBean.getName());
        if (str.contains("recommend")) {
            bundle.putString("recommend_app", getRelevantPkg(appListItemBean));
        } else {
            bundle.putString("package_name", appListItemBean.getPackageName());
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
    }

    private long getAnimWaitTime() {
        return Math.max(Math.min(60000 - (System.currentTimeMillis() - this.mLastAnimTime), 60000L), 20000L);
    }

    private Context getContext() {
        return GameLauncherApplication.getAppContext();
    }

    private IndicatorController getIndicatorCtrl() {
        if (this.mDetectorCtrl == null) {
            this.mDetectorCtrl = new IndicatorController(getContext());
        }
        return this.mDetectorCtrl;
    }

    private String getRelevantPkg(AppListItemBean appListItemBean) {
        if (appListItemBean == null || appListItemBean.getRelevantList().size() <= 0 || appListItemBean.getCurrentRelevant() == null) {
            return null;
        }
        return appListItemBean.getCurrentRelevant().pkg;
    }

    private void gotoDigitalHumanSettings(Context context) {
        Log.i("Controller", "gotoDigitalHumanSettings act = com.zte.aiassistant.action.DIGITAL_HUMAN_SETTING");
        if (!isActionSupport(context, "com.zte.aiassistant.action.DIGITAL_HUMAN_SETTING")) {
            Log.i("Controller", "No Activity found to handle Intent { act=com.zte.aiassistant.action.DIGITAL_HUMAN_SETTING }");
            return;
        }
        Intent intent = new Intent("com.zte.aiassistant.action.DIGITAL_HUMAN_SETTING");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private void gotoMainSettings(Context context) {
        Log.i("Controller", "gotoMainSettings act = com.zte.aiassistant.SETTINGS_SEARCH");
        if (!isActionSupport(context, "com.zte.aiassistant.SETTINGS_SEARCH")) {
            Log.i("Controller", "No Activity found to handle Intent { act=com.zte.aiassistant.SETTINGS_SEARCH }");
            return;
        }
        Intent intent = new Intent("com.zte.aiassistant.SETTINGS_SEARCH");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    private void initView(View view) {
        this.mParent = view;
        this.mPop = (Group) view.findViewById(R.id.group_pop);
        Button button = (Button) view.findViewById(R.id.top_news);
        this.mNews = button;
        button.setOnClickListener(this);
        this.mNews.setVisibility(GameSpaceConfig.supportNews() ? 0 : 8);
        Log.d("Controller", "initView() supportNews : " + GameSpaceConfig.supportNews());
        Button button2 = (Button) view.findViewById(R.id.top_detector);
        this.mIndicator = button2;
        button2.setOnClickListener(this);
        this.mIndicator.setVisibility(GameSpaceConfig.supportIndicator() ? 0 : 8);
        this.mScore = (Button) view.findViewById(R.id.top_score);
        if (CommonUtil.isInternalVersion() || !FeatureUtil.scoreRecordEnable()) {
            this.mScore.setVisibility(8);
        } else {
            this.mScore.setVisibility(0);
            this.mScore.setOnClickListener(this);
        }
        Button button3 = (Button) view.findViewById(R.id.top_mora);
        this.mMora = button3;
        button3.setOnClickListener(this);
        this.mMora.setVisibility(GameSpaceConfig.supportMora() ? 0 : 8);
        if (Build.DEVICE.contains(TgkHelper.P820F05_DEVICE) || Build.DEVICE.contains(TgkHelper.P780F01_DEVICE) || Build.DEVICE.contains(TgkHelper.P720F10_DEVICE) || Build.DEVICE.contains("Z7606O") || Build.DEVICE.contains(TgkHelper.P820F03_DEVICE) || Build.DEVICE.contains(TgkHelper.P720F03_DEVICE)) {
            this.mMora.setBackgroundResource(R.mipmap.game_lobby_demi);
        } else {
            this.mMora.setBackgroundResource((FeatureUtil.isSprd() || FeatureUtil.isMtk()) ? R.mipmap.game_lobby_demi_digital_human : R.mipmap.game_lobby_mora);
        }
        Button button4 = (Button) view.findViewById(R.id.top_relevant);
        this.mRelevant = button4;
        button4.setOnClickListener(this);
        if (GameSpaceConfig.supportMora()) {
            this.mTopEntry.add(this.mMora);
        }
        this.mPopTitle = (TextView) view.findViewById(R.id.top_pop_title);
        this.top_pop_title_bg = (ImageView) view.findViewById(R.id.top_pop_title_bg);
        this.mPopBg = (ImageView) view.findViewById(R.id.top_pop_content);
        this.mPopContent = (FrameLayout) view.findViewById(R.id.pop_state_content);
        this.mDirections.add((ImageView) view.findViewById(R.id.top_pop_direction_news));
        this.mDirections.add((ImageView) view.findViewById(R.id.top_pop_direction_detector));
        this.mDirections.add((ImageView) view.findViewById(R.id.top_pop_direction_score));
        Iterator<ImageView> it = this.mDirections.iterator();
        while (it.hasNext()) {
            it.next().setTranslationY(3.0f);
        }
    }

    private boolean isActionSupport(Context context, String str) {
        return !context.getPackageManager().queryIntentActivities(new Intent(str), 65536).isEmpty();
    }

    private boolean isDigitalHumanStatusOn() {
        return Settings.System.getInt(getContext().getContentResolver(), "is_enable_digital_human", 1) == 1;
    }

    private boolean isSelectedItemChanged(AppListItemBean appListItemBean) {
        if (this.mSelectedItem == null || appListItemBean == null) {
            return true;
        }
        return !appListItemBean.isSameItem(r1);
    }

    private void setPopContentView(View view, boolean z) {
        this.mPopContent.removeView(this.mPopChild);
        this.mPopChild = view;
        this.mPopContent.getLayoutParams().width = z ? 1124 : AnalyticsListener.EVENT_PLAYER_RELEASED;
        this.mPopContent.requestLayout();
        this.mPopTitle.requestLayout();
        this.top_pop_title_bg.requestLayout();
        this.mPopBg.setImageDrawable(getContext().getDrawable(z ? R.drawable.top_state_content_score : R.drawable.top_state_content));
        this.mPopContent.addView(view);
        Log.d("Controller", "setPopContentView()");
    }

    private void setScoreIconVisibility() {
        if (CommonUtil.isInternalVersion() || !FeatureUtil.scoreRecordEnable()) {
            this.mScore.setVisibility(8);
        } else {
            this.mScore.setVisibility(this.mSelectedItem != null ? 0 : 8);
        }
    }

    private void showAndFillPop(View view, int i, String str, boolean z) {
        setPopTitle(str);
        setPopContentView(view, z);
        showPop();
        updateDirection(i, false);
    }

    private void showPop() {
        this.mPop.setVisibility(0);
        this.mShortcutController.hideShortcutTips();
    }

    private void updateDirection(int i, boolean z) {
        Iterator<ImageView> it = this.mDirections.iterator();
        while (it.hasNext()) {
            ImageView next = it.next();
            next.setVisibility((next.getId() != i || z) ? 4 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateResource(Bitmap bitmap, AppListItemBean appListItemBean) {
        Log.d("Controller", "top - updateResource() item : " + appListItemBean.getName());
        final Bitmap hexagonBitmap = BitmapUtils.getHexagonBitmap(bitmap, 96, 96);
        this.mRelevant.setVisibility(0);
        this.mLastAnimTime = System.currentTimeMillis();
        ObjectAnimator createPropertyValuesAnimator = AnimHelper.createPropertyValuesAnimator(this.mRelevant, View.ALPHA, 0, 1, 200, ROTATION_INTERPOLATOR);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createPropertyValuesAnimator);
        animatorSet.start();
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.controller.TopController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TopController.this.m236xf5bdc663(hexagonBitmap);
            }
        }, 10L);
    }

    public boolean dismissPop() {
        updateDirection(0, true);
        this.mPop.setVisibility(8);
        this.mShortcutController.resetShortcutTips();
        this.mIndicator.setBackgroundResource(R.drawable.top_indicator_normal);
        this.mScore.setBackgroundResource(R.drawable.top_score_normal);
        this.mNews.setBackgroundResource(R.drawable.top_news_normal);
        this.mPopContent.removeView(this.mPopChild);
        return true;
    }

    public void exit() {
        Log.d("Controller", "top - exit()");
        IndicatorController indicatorController = this.mDetectorCtrl;
        if (indicatorController != null) {
            indicatorController.unregisterObserver();
            this.mDetectorCtrl.cancelCountdown();
            this.mDetectorCtrl = null;
        }
        ScoreRecordsController.getInstance().destroy();
        Controller.getInstance().removeSelectedChangedListener(this.mSelectedChangedRunnable);
        OperationKeyObserver.getInstance(getContext()).removeCallback(this);
    }

    public boolean isConsumptionTouch(MotionEvent motionEvent) {
        Rect rect = new Rect();
        this.mPopBg.getHitRect(rect);
        if (!rect.contains((int) motionEvent.getX(), (int) motionEvent.getY()) && isPopVisible()) {
            return dismissPop();
        }
        return false;
    }

    public boolean isPopVisible() {
        return this.mPop.getVisibility() == 0;
    }

    /* renamed from: lambda$updateResource$0$cn-nubia-gamelauncher-controller-TopController, reason: not valid java name */
    /* synthetic */ void m236xf5bdc663(Bitmap bitmap) {
        this.mRelevant.setBackground(BitmapUtils.convertBitmapToDrawable(bitmap));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_detector /* 2131363549 */:
                clickIndicator();
                break;
            case R.id.top_mora /* 2131363551 */:
                clickMora();
                break;
            case R.id.top_news /* 2131363552 */:
                clickNews();
                break;
            case R.id.top_relevant /* 2131363560 */:
                clickRelevant();
                break;
            case R.id.top_score /* 2131363563 */:
                clickScore();
                break;
        }
    }

    @Override // cn.nubia.gamelauncher.observer.OperationKeyObserver.Callback
    public void onOperationKeyChanged(boolean z) {
        updateRelevantVisible();
    }

    public void onSelectedChanged() {
        Log.d("Controller", "top - onSelectedChanged()");
        if (!Controller.getInstance().supportSelected()) {
            dismissPop();
            stopRelevantAnim();
        }
        updateIndicatorVisible();
        updateScoreVisible();
        updateMoraVisible();
        updateRelevantVisible();
        recordSelected();
    }

    public void recordSelected() {
        AppListItemBean selectedItem = Controller.getInstance().getSelectedItem();
        this.mSelectedItem = selectedItem;
        if (selectedItem != null) {
            ScoreRecordsController.getInstance().updateSelectGames(selectedItem.isShortcut() ? this.mSelectedItem.getPackageName() + "@" + this.mSelectedItem.getName().hashCode() : this.mSelectedItem.getPackageName());
        }
        setScoreIconVisibility();
    }

    public void setPopTitle(String str) {
        this.mPopTitle.setText(str);
    }

    public void stopRelevantAnim() {
        this.mHandler.removeCallbacksAndMessages(null);
        Log.d("Controller", "top - stopRelevantAnim()");
    }

    public void updateIndicatorVisible() {
        Log.d("Controller", "top - updateIndicatorVisible()");
        this.mIndicator.setVisibility(Controller.getInstance().isStayInFullLobby() && GameSpaceConfig.supportIndicator() ? 0 : 8);
    }

    public void updateMoraVisible() {
        Log.d("Controller", "top - updateMoraVisible()");
        this.mMora.setVisibility(Controller.getInstance().isStayInLobby() && GameSpaceConfig.supportMora() ? 0 : 8);
    }

    public void updateRelevantIcon() {
        final AppListItemBean selectedItem = Controller.getInstance().getSelectedItem();
        if (!Controller.getInstance().supportRelevant()) {
            Log.d("Controller", "top - updateRelevantIcon() setVisibility(GONE) ");
            return;
        }
        if (!canDoNextAnim(selectedItem)) {
            long animWaitTime = getAnimWaitTime();
            Log.d("Controller", "top - updateRelevantIcon() postDelayed : " + animWaitTime);
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.controller.TopController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    TopController.this.updateRelevantIcon();
                }
            }, animWaitTime);
            return;
        }
        this.mRelevant.setVisibility(8);
        this.mHandler.removeCallbacks(new Runnable() { // from class: cn.nubia.gamelauncher.controller.TopController$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TopController.this.updateRelevantIcon();
            }
        });
        Glide.with(getContext()).load(selectedItem.getNextRelevantIconUrl()).asBitmap().into((BitmapTypeRequest<String>) new SimpleTarget<Bitmap>() { // from class: cn.nubia.gamelauncher.controller.TopController.1
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                TopController.this.updateResource(bitmap, selectedItem);
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                onResourceReady((Bitmap) obj, (GlideAnimation<? super Bitmap>) glideAnimation);
            }
        });
        if (selectedItem.isLoopUpdateRelevantIcon()) {
            this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.controller.TopController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    TopController.this.updateRelevantIcon();
                }
            }, 60000L);
        }
    }

    public void updateRelevantVisible() {
        boolean z = Controller.getInstance().isStayInFullLobby() && Controller.getInstance().supportRelevant() && !OperationKeyObserver.getInstance(getContext()).isOperationKeyClose();
        Log.d("Controller", "top - updateRelevantVisible() allowed : " + z);
        if (z) {
            updateRelevantIcon();
        } else {
            this.mRelevant.setVisibility(8);
            stopRelevantAnim();
        }
    }

    public void updateScoreVisible() {
        Log.d("Controller", "top - updateScoreVisible()");
        this.mScore.setVisibility(Controller.getInstance().isStayInFullLobby() ? 0 : 8);
    }
}
