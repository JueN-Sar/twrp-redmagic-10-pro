package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.AnimationUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.ControlPanelFeatureHelper;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class GameStrengthenIndicatorView extends FrameLayout implements View.OnClickListener {
    private static final int CAN_SCROLL_ITEM_MIN_INDEX = 6;
    private static final int CAN_SCROLL_MIN_ITEM_LIMIT_COUNT = 7;
    private static final int GAME_STRENGTH_TAB_COUNT = 9;
    private static final String TAG = "GameStrengthenIndicatorView";
    private int mAdjustTabIndex;
    private String mCurrentPkg;
    private int mFunctionTabIndex;
    private int mGpuSettingsIndex;
    private LinearLayout mLayout;
    private ArrayList<View> mList;
    private int mNetSettingsIndex;
    private int mPerfModeTabIndex;
    private int mResourceSettingsIndex;
    private ScrollView mScrollView;
    private int mShowScreenIndex;
    private int mStrengthVoiceIndex;
    private OnGameStrengthenTabClickListener onGameStrengthenTabClickListener;
    private FrameLayout vGameGyroscopeSensitivityLayout;
    private View vGameGyroscopeSensitivityTab;
    private View vGameStrengthenFunctionTab;
    private View vGameStrengthenGpuSettingsTab;
    private View vGameStrengthenNetSettingsTab;
    private View vGameStrengthenPerformanceTab;
    private View vGameStrengthenPluginTab;
    private View vGameStrengthenResourceTab;
    private View vGameStrengthenScreenTab;
    private List<View> vGameStrengthenTabs;
    private View vGameStrengthenVoiceTab;

    public interface OnGameStrengthenTabClickListener {
        void onGameStrengthenTabClick(int i);
    }

    public GameStrengthenIndicatorView(Context context) {
        this(context, null);
    }

    public GameStrengthenIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameStrengthenIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mList = new ArrayList<>();
    }

    private void checkSupportItem() {
        this.mLayout.removeAllViews();
        String gameControlpanelMenu = ControlPanelFeatureHelper.getGameControlpanelMenu();
        if (TextUtils.isEmpty(gameControlpanelMenu)) {
            if (!Utils.isRedMagicPad(getContext().getApplicationContext())) {
                this.vGameStrengthenTabs.add(this.vGameGyroscopeSensitivityTab);
                this.mLayout.addView(this.vGameGyroscopeSensitivityTab);
            }
            this.vGameStrengthenTabs.add(this.vGameStrengthenPerformanceTab);
            this.mLayout.addView(this.vGameStrengthenPerformanceTab);
            if (Utils.isSupportSnapdragonAdrenoGpu(getContext().getApplicationContext())) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenGpuSettingsTab);
                this.mLayout.addView(this.vGameStrengthenGpuSettingsTab);
            }
            this.vGameStrengthenTabs.add(this.vGameStrengthenScreenTab);
            this.mLayout.addView(this.vGameStrengthenScreenTab);
            if (!Utils.isInternalVersion()) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenVoiceTab);
                this.mLayout.addView(this.vGameStrengthenVoiceTab);
            }
            if (!Util.isZte()) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenNetSettingsTab);
                this.mLayout.addView(this.vGameStrengthenNetSettingsTab);
            }
            this.vGameStrengthenTabs.add(this.vGameStrengthenFunctionTab);
            this.mLayout.addView(this.vGameStrengthenFunctionTab);
            if (!Util.isZte()) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenResourceTab);
                this.mLayout.addView(this.vGameStrengthenResourceTab);
            }
            if (!Util.isNubiaAppStore() && !Utils.isInternalVersion()) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenPluginTab);
                this.mLayout.addView(this.vGameStrengthenPluginTab);
            }
        } else {
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.AdjustOperation.toString()) || ControlPanelFeatureHelper.isLddTpInterfaceSupported()) {
                this.vGameStrengthenTabs.add(this.vGameGyroscopeSensitivityTab);
                this.mLayout.addView(this.vGameGyroscopeSensitivityTab);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.PerformanceStrengthen.toString())) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenPerformanceTab);
                this.mLayout.addView(this.vGameStrengthenPerformanceTab);
            }
            if (Utils.isSupportSnapdragonAdrenoGpu(getContext().getApplicationContext()) || gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.GpuSettings.toString())) {
                if (!Utils.isSprdPlatform()) {
                    this.vGameStrengthenTabs.add(this.vGameStrengthenGpuSettingsTab);
                    this.mLayout.addView(this.vGameStrengthenGpuSettingsTab);
                } else if (Utils.isSupportGpu(this.mCurrentPkg)) {
                    this.vGameStrengthenTabs.add(this.vGameStrengthenGpuSettingsTab);
                    this.mLayout.addView(this.vGameStrengthenGpuSettingsTab);
                }
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.ScreenShowStrengthen.toString())) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenScreenTab);
                this.mLayout.addView(this.vGameStrengthenScreenTab);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.VoiceStrengthen.toString())) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenVoiceTab);
                this.mLayout.addView(this.vGameStrengthenVoiceTab);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.NetSettings.toString())) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenNetSettingsTab);
                this.mLayout.addView(this.vGameStrengthenNetSettingsTab);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.FunctionConfiguration.toString())) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenFunctionTab);
                this.mLayout.addView(this.vGameStrengthenFunctionTab);
            }
            if (Utils.supportResourceSettings(gameControlpanelMenu)) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenResourceTab);
                this.mLayout.addView(this.vGameStrengthenResourceTab);
            }
            if (gameControlpanelMenu.contains(ControlPanelFeatureHelper.MenuHelper.PluginSettings.toString())) {
                this.vGameStrengthenTabs.add(this.vGameStrengthenPluginTab);
                this.mLayout.addView(this.vGameStrengthenPluginTab);
            }
        }
        if (this.vGameStrengthenTabs.contains(this.vGameStrengthenFunctionTab)) {
            this.mFunctionTabIndex = this.vGameStrengthenTabs.indexOf(this.vGameStrengthenFunctionTab);
        }
        if (this.vGameStrengthenTabs.contains(this.vGameStrengthenPerformanceTab)) {
            this.mPerfModeTabIndex = this.vGameStrengthenTabs.indexOf(this.vGameStrengthenPerformanceTab);
        }
        if (this.vGameStrengthenTabs.contains(this.vGameGyroscopeSensitivityTab)) {
            this.mAdjustTabIndex = this.vGameStrengthenTabs.indexOf(this.vGameGyroscopeSensitivityTab);
        }
        if (this.vGameStrengthenTabs.contains(this.vGameStrengthenScreenTab)) {
            this.mShowScreenIndex = this.vGameStrengthenTabs.indexOf(this.vGameStrengthenScreenTab);
        }
        if (this.vGameStrengthenTabs.contains(this.vGameStrengthenVoiceTab)) {
            this.mStrengthVoiceIndex = this.vGameStrengthenTabs.indexOf(this.vGameStrengthenVoiceTab);
        }
        if (this.vGameStrengthenTabs.contains(this.vGameStrengthenNetSettingsTab)) {
            this.mNetSettingsIndex = this.vGameStrengthenTabs.indexOf(this.vGameStrengthenNetSettingsTab);
        }
        if (this.vGameStrengthenTabs.contains(this.vGameStrengthenResourceTab)) {
            this.mResourceSettingsIndex = this.vGameStrengthenTabs.indexOf(this.vGameStrengthenResourceTab);
        }
        if (this.vGameStrengthenTabs.contains(this.vGameStrengthenGpuSettingsTab)) {
            this.mGpuSettingsIndex = this.vGameStrengthenTabs.indexOf(this.vGameStrengthenGpuSettingsTab);
        }
        LogUtil.d(TAG, " checkSupportItem childCount = " + this.mLayout.getChildCount() + "mFunctionTabIndex = " + this.mFunctionTabIndex);
    }

    private int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private static ArrayList<View> getItemViewList(View view) {
        ArrayList<View> arrayList = new ArrayList<>();
        if (view instanceof ViewGroup) {
            for (int i = 0; i < 3; i++) {
                View childAt = ((ViewGroup) view).getChildAt(i);
                if (childAt instanceof ImageView) {
                    arrayList.add(childAt);
                }
            }
        }
        return arrayList;
    }

    private void initListener() {
        int size = this.vGameStrengthenTabs.size();
        for (int i = 0; i < size; i++) {
            this.vGameStrengthenTabs.get(i).setOnClickListener(this);
        }
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(GameControlOrientationManager.getInstance().isPortrait() ? R.layout.nubia_game_strengthen_view_indicator_port : R.layout.nubia_game_strengthen_view_indicator, this);
        this.mLayout = (LinearLayout) findViewById(R.id.view_layout);
        this.mScrollView = (ScrollView) findViewById(R.id.indicator_scrollView);
        this.vGameGyroscopeSensitivityTab = findViewById(R.id.options_item_game_gyroscope_text);
        this.vGameStrengthenPerformanceTab = findViewById(R.id.options_item_strengthperformance_text);
        this.vGameStrengthenScreenTab = findViewById(R.id.options_item_game_screen_text);
        this.vGameStrengthenVoiceTab = findViewById(R.id.options_item_game_voice_text);
        this.vGameStrengthenFunctionTab = findViewById(R.id.options_item_game_function_text);
        this.vGameStrengthenPluginTab = findViewById(R.id.options_item_game_plug_text);
        this.vGameStrengthenResourceTab = findViewById(R.id.options_item_resource_lib_settings);
        this.vGameStrengthenGpuSettingsTab = findViewById(R.id.options_item_gpu_settings_text);
        this.vGameStrengthenNetSettingsTab = findViewById(R.id.options_item_net_settings_text);
        this.vGameStrengthenTabs = new ArrayList(9);
        checkSupportItem();
        updateGameStrengthenIndicator(this.vGameStrengthenPerformanceTab);
    }

    private boolean isSupportGameShow() {
        return Build.DEVICE.contains("NX65") || Build.DEVICE.contains("NX67") || Build.DEVICE.contains("NX66") || Build.DEVICE.contains("NX70");
    }

    private boolean isSupportGameVoice() {
        return (Build.DEVICE.contains("NX659") || Build.DEVICE.contains("NX67") || Build.DEVICE.contains("NX66") || Build.DEVICE.contains("NX70")) && !Utils.isInternalVersion();
    }

    private void startItemAnimation(ArrayList<View> arrayList) {
        AnimationUtil.setGcsItemBgTranslationX(arrayList.get(0));
        AnimationUtil.setGcsRedItemAlpha(arrayList.get(1));
        AnimationUtil.setGcsRedItemAlpha(arrayList.get(2));
    }

    private void updateGameStrengthenIndicator(View view) {
        int size = this.vGameStrengthenTabs.size();
        for (int i = 0; i < size; i++) {
            View view2 = this.vGameStrengthenTabs.get(i);
            if (view2 == null) {
                return;
            }
            ArrayList<View> itemViewList = getItemViewList(view2);
            this.mList = itemViewList;
            if (view == view2) {
                Iterator<View> it = itemViewList.iterator();
                while (it.hasNext()) {
                    it.next().setVisibility(0);
                }
                startItemAnimation(this.mList);
                ((MarqueeTextView) view2.findViewById(R.id.title)).setTextColor(getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text_checked));
                OnGameStrengthenTabClickListener onGameStrengthenTabClickListener = this.onGameStrengthenTabClickListener;
                if (onGameStrengthenTabClickListener != null) {
                    onGameStrengthenTabClickListener.onGameStrengthenTabClick(i);
                }
            } else {
                Iterator<View> it2 = itemViewList.iterator();
                while (it2.hasNext()) {
                    it2.next().setVisibility(4);
                }
                ((MarqueeTextView) view2.findViewById(R.id.title)).setTextColor(getContext().getResources().getColorStateList(R.color.gcs_gamecenter_menu_text));
            }
        }
    }

    public int getAdjustIndex() {
        return this.mAdjustTabIndex;
    }

    public int getFunctionIndex() {
        return this.mFunctionTabIndex;
    }

    public int getGpuSettingsIndex() {
        return this.mGpuSettingsIndex;
    }

    public int getNetSettingsIndex() {
        return this.mNetSettingsIndex;
    }

    public int getPerfModeIndex() {
        return this.mPerfModeTabIndex;
    }

    public int getResourceSettingsIndex() {
        return this.mResourceSettingsIndex;
    }

    public int getShowScreenIndex() {
        return this.mShowScreenIndex;
    }

    public int getStrengthVoiceIndex() {
        return this.mStrengthVoiceIndex;
    }

    public void initStartType(String str) {
        this.mCurrentPkg = str;
        initView();
        initListener();
    }

    /* renamed from: lambda$setGameStrengthenIndicator$0$cn-nubia-gamelauncher-gamecontrolpanel-GameStrengthenIndicatorView, reason: not valid java name */
    /* synthetic */ void m272x4f123c2d(int i, View view) {
        if (this.vGameStrengthenTabs.size() < 7 || i < 6) {
            return;
        }
        view.requestRectangleOnScreen(new Rect(0, 0, view.getWidth(), view.getHeight()), true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.i(TAG, "view onClick view");
        updateGameStrengthenIndicator(view);
    }

    public void setGameStrengthenIndicator(final int i) {
        if (i < 0 || i >= this.vGameStrengthenTabs.size()) {
            updateGameStrengthenIndicator(this.vGameStrengthenTabs.get(0));
            return;
        }
        final View view = this.vGameStrengthenTabs.get(i);
        updateGameStrengthenIndicator(view);
        this.mScrollView.post(new Runnable() { // from class: cn.nubia.gamelauncher.gamecontrolpanel.GameStrengthenIndicatorView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GameStrengthenIndicatorView.this.m272x4f123c2d(i, view);
            }
        });
    }

    public void setOnGameStrengthenTabClickListener(OnGameStrengthenTabClickListener onGameStrengthenTabClickListener) {
        this.onGameStrengthenTabClickListener = onGameStrengthenTabClickListener;
    }
}
