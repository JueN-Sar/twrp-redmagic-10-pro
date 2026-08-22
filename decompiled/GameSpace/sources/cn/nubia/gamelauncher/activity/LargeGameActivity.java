package cn.nubia.gamelauncher.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.anim.AnimBean;
import cn.nubia.gamelauncher.anim.AnimHelper;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.view.LargeGameTabView;
import cn.nubia.gamelauncher.view.SelectedButton;
import cn.nubia.gamelauncher.xgravitation.util.LogUtils;
import cn.nubia.studio.TouPingGravitationActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class LargeGameActivity extends BaseActivity implements LargeGameTabView.OnTabChangeListener, View.OnClickListener, View.OnTouchListener {
    private static final String HANDLE_PLAY_ACTION = "cn.nubia.gamepad.SCHEME_LIST";
    private static final String HANDLE_PLAY_PACKAGE_NAME = "cn.nubia.gamepad";
    private static final String KEYBOARD_MOUSE_PLAY_ACTION = "cn.nubia.keymapcenter.intent.action.LKM_SCHEME_LIST";
    private float density;
    private int densityDpi;
    AnimatorSet mAnimatorSet;
    View mCLLayout1;
    int mJumpColor;
    TextView mLargeGameTips1;
    TextView mLargeGameTips2;
    ArrayList<Integer> mLeftIds;
    int mLinkColor;
    ImageView mPcCard;
    TextView mPcCardTitle;
    TextView mPcSecTitle;
    private PopupWindow mPopupWindow;
    ArrayList<Integer> mRightIds;
    ImageView mTabContent;
    ImageView mTabContentGame1;
    ImageView mTabContentGame2;
    ImageView mTabContentGame3;
    LargeGameTabView mTabView;
    HashMap<Integer, Integer> mTabContentMap = new HashMap<>();
    ArrayList<SelectedButton> mBtnList = new ArrayList<>();
    int[] mTopIds = {R.id.actionbar_bg, R.id.return_icon, R.id.title};
    int mTransY = 123;
    int mOffset = 82;
    int mMode = 0;
    String TAG = "selected";

    private void clickSelectedButton(View view, int i, boolean z) {
        TextView textView;
        int i2;
        int i3;
        Iterator<SelectedButton> it = this.mBtnList.iterator();
        while (true) {
            boolean z2 = true;
            if (!it.hasNext()) {
                break;
            }
            SelectedButton next = it.next();
            if (view.getId() != next.getId()) {
                z2 = false;
            }
            next.setChecked(z2);
        }
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow == null) {
            this.mPopupWindow = new PopupWindow(this);
            FrameLayout frameLayout = new FrameLayout(this);
            textView = new TextView(this);
            textView.setTag("pop_content");
            textView.setVisibility(0);
            textView.setBackground(null);
            textView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.textView2_text_size));
            textView.setTextColor(getColor(R.color.game_total_time_color));
            textView.setLinkTextColor(z ? this.mLinkColor : this.mJumpColor);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setAutoLinkMask(0);
            textView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.LargeGameActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    LargeGameActivity.this.doJump();
                }
            });
            frameLayout.addView(textView, new FrameLayout.LayoutParams(-2, -2));
            this.mPopupWindow.setContentView(frameLayout);
            this.mPopupWindow.setBackgroundDrawable(getDrawable(R.drawable.large_pop));
            this.mPopupWindow.setOutsideTouchable(true);
            this.mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: cn.nubia.gamelauncher.activity.LargeGameActivity.2
                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    LargeGameActivity.this.resetPop();
                }
            });
        } else {
            textView = (TextView) popupWindow.getContentView().findViewWithTag("pop_content");
        }
        if (textView == null) {
            this.mPopupWindow = null;
            clickSelectedButton(view, i, z);
            return;
        }
        textView.setText(i);
        int width = view.getRootView().getWidth();
        int textSize = (int) ((textView.getTextSize() * textView.getText().length()) / 2.217d);
        if (textView.getPaint() != null) {
            TextPaint paint = textView.getPaint();
            int measureText = (int) paint.measureText(getText(i).toString());
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            int ceil = (int) Math.ceil(fontMetrics.bottom - fontMetrics.top);
            textSize = ((int) (measureText * 1.12f)) + 4;
            i2 = ceil;
        } else {
            i2 = 0;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i4 = iArr[0];
        if (i4 + textSize > width) {
            int i5 = (width - i4) - 20;
            int i6 = (textSize / i5) + 1;
            textSize = i5;
            i3 = i6;
        } else {
            i3 = 1;
        }
        this.mPopupWindow.setWidth(textSize);
        textView.setLines(i3);
        this.mPopupWindow.showAtLocation(view, 8388659, i4, (iArr[1] - (this.mTransY + (i2 * (i3 / 2)))) - 20);
    }

    private void createAnimatorSet(boolean z) {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ArrayList arrayList = new ArrayList();
        AnimBean animBean = new AnimBean(View.TRANSLATION_X, z ? 0.0f : CommonUtil.getLeftLayoutTranslationX(getApplicationContext()), z ? CommonUtil.getLeftLayoutTranslationX(getApplicationContext()) : 0.0f);
        AnimBean animBean2 = new AnimBean(View.TRANSLATION_X, z ? 0.0f : CommonUtil.getRightLayoutTranslationX(getApplicationContext()), z ? CommonUtil.getRightLayoutTranslationX(getApplicationContext()) : 0.0f);
        AnimBean animBean3 = new AnimBean(View.ALPHA, z ? 1.0f : 0.0f, z ? 0.0f : 1.0f);
        Iterator<Integer> it = this.mLeftIds.iterator();
        while (it.hasNext()) {
            arrayList.add(getAnim(it.next().intValue(), animBean, animBean3));
        }
        Iterator<Integer> it2 = this.mRightIds.iterator();
        while (it2.hasNext()) {
            arrayList.add(getAnim(it2.next().intValue(), animBean2, animBean3));
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mAnimatorSet = animatorSet2;
        animatorSet2.playTogether(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doJump() {
        Log.d("selected", "doJump() mMode : " + this.mMode);
        int i = this.mMode;
        if (i == 1) {
            startHandlePlay();
        } else if (i == 2) {
            startMousePlay();
        } else {
            if (i != 3) {
                return;
            }
            startProjectionGravitation();
        }
    }

    private void enterAnim() {
        createAnimatorSet(false);
        this.mAnimatorSet.start();
    }

    private void exitAnim() {
        createAnimatorSet(true);
        this.mAnimatorSet.start();
        this.mAnimatorSet.addListener(new Animator.AnimatorListener() { // from class: cn.nubia.gamelauncher.activity.LargeGameActivity.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Log.e(LargeGameActivity.this.TAG, " onAnimationEnd ");
                LargeGameActivity.this.finish();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }

    private ObjectAnimator getAnim(int i, AnimBean animBean, AnimBean animBean2) {
        return AnimHelper.createPropertyAnim(findViewById(i), ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, animBean, animBean2);
    }

    private void initResMap() {
        this.mTabContentMap.put(Integer.valueOf(R.id.xbox), Integer.valueOf(R.mipmap.large_xbox));
        this.mTabContentMap.put(Integer.valueOf(R.id.steam), Integer.valueOf(R.mipmap.large_steam));
        this.mTabContentMap.put(Integer.valueOf(R.id.station), Integer.valueOf(R.mipmap.large_play_station));
        this.mTabContentMap.put(Integer.valueOf(R.id.epic), Integer.valueOf(R.mipmap.large_epic));
    }

    private void initView() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.large_game_layout);
        this.mTabView = (LargeGameTabView) findViewById(R.id.tab_view);
        this.mCLLayout1 = findViewById(R.id.game_explain_bg);
        this.mTabContent = (ImageView) findViewById(R.id.tab_content);
        this.mTabContentGame1 = (ImageView) findViewById(R.id.iv_tab_content_game_1);
        this.mTabContentGame2 = (ImageView) findViewById(R.id.iv_tab_content_game_2);
        this.mTabContentGame3 = (ImageView) findViewById(R.id.iv_tab_content_game_3);
        this.mLargeGameTips1 = (TextView) findViewById(R.id.tv_large_game_tips_1);
        this.mLargeGameTips2 = (TextView) findViewById(R.id.tv_large_game_tips_2);
        this.mPcCard = (ImageView) findViewById(R.id.iv_3a_game_pc_card);
        this.mPcCardTitle = (TextView) findViewById(R.id.tv_3a_game_pc_card_title);
        this.mPcSecTitle = (TextView) findViewById(R.id.tv_3a_game_pc_card_sec_title);
        this.mTabView.setOnTabChangeListener(this);
        findViewById(R.id.content).setOnClickListener(this);
        findViewById(R.id.tab_content).setOnClickListener(this);
        findViewById(R.id.scroll_explain).setOnTouchListener(this);
        findViewById(R.id.explain_details).setOnClickListener(this);
        findViewById(R.id.explain_content).setOnClickListener(this);
        findViewById(R.id.large_game_explain).setOnClickListener(this);
        this.mBtnList.add((SelectedButton) findViewById(R.id.selected_monitor));
        this.mBtnList.add((SelectedButton) findViewById(R.id.selected_keyboard));
        this.mBtnList.add((SelectedButton) findViewById(R.id.selected_mouse));
        this.mBtnList.add((SelectedButton) findViewById(R.id.selected_play_handle));
        this.mBtnList.add((SelectedButton) findViewById(R.id.selected_play_mouse));
        this.mBtnList.add((SelectedButton) findViewById(R.id.selected_play_mirror));
        this.mLinkColor = getResources().getColor(R.color.color_link_gear, null);
        this.mJumpColor = getResources().getColor(R.color.color_link_services, null);
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.mLeftIds = arrayList;
        arrayList.add(Integer.valueOf(R.id.tab_content));
        this.mLeftIds.add(Integer.valueOf(R.id.title_services));
        this.mLeftIds.add(Integer.valueOf(R.id.title_gear));
        this.mLeftIds.add(Integer.valueOf(R.id.selected_monitor));
        this.mLeftIds.add(Integer.valueOf(R.id.selected_keyboard));
        this.mLeftIds.add(Integer.valueOf(R.id.selected_mouse));
        this.mLeftIds.add(Integer.valueOf(R.id.tab_view));
        this.mLeftIds.add(Integer.valueOf(R.id.selected_play_handle));
        this.mLeftIds.add(Integer.valueOf(R.id.selected_play_mirror));
        this.mLeftIds.add(Integer.valueOf(R.id.selected_play_mouse));
        this.mLeftIds.add(Integer.valueOf(R.id.iv_tab_content_game_1));
        this.mLeftIds.add(Integer.valueOf(R.id.iv_tab_content_game_2));
        this.mLeftIds.add(Integer.valueOf(R.id.iv_tab_content_game_3));
        this.mLeftIds.add(Integer.valueOf(R.id.tv_large_game_tips_1));
        this.mLeftIds.add(Integer.valueOf(R.id.tv_large_game_tips_2));
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        this.mRightIds = arrayList2;
        arrayList2.add(Integer.valueOf(R.id.game_explain_bg));
        this.mRightIds.add(Integer.valueOf(R.id.game_explain_title));
        this.mRightIds.add(Integer.valueOf(R.id.scroll_explain));
        if ("true".equals(FeatureUtil.get(FeatureUtil.ZTE_FEATURE_STREAM_GAME, "false"))) {
            findViewById(R.id.top_remote_game_layout).setVisibility(0);
            findViewById(R.id.base_line_game_explain).setVisibility(0);
            findViewById(R.id.iv_3a_game_stream_card).setOnClickListener(this);
            findViewById(R.id.iv_3a_game_pc_card).setOnClickListener(this);
            this.mLeftIds.add(Integer.valueOf(R.id.iv_3a_game_stream_card));
            this.mLeftIds.add(Integer.valueOf(R.id.tv_3a_game_stream_card_title));
            this.mRightIds.add(Integer.valueOf(R.id.iv_3a_game_pc_card));
            this.mRightIds.add(Integer.valueOf(R.id.tv_3a_game_pc_card_title));
            if ("true".equals(FeatureUtil.get("ZTE_FEATURE_REDMAGIC_PC_GAME", "false"))) {
                this.mPcCard.setImageResource(R.mipmap.x_grav_3a_game_simulate_pc);
                this.mPcCardTitle.setText(R.string.large_game_by_pc_game_title);
                this.mPcSecTitle.setVisibility(8);
            } else {
                this.mPcCard.setImageResource(R.mipmap.x_grav_3a_game_pc);
                this.mPcSecTitle.setVisibility(0);
                this.mRightIds.add(Integer.valueOf(R.id.tv_3a_game_pc_card_sec_title));
            }
        }
    }

    private boolean isSameMode(int i) {
        if (this.mMode == i) {
            resetPop();
            return true;
        }
        this.mMode = i;
        return false;
    }

    private void resetDensity() {
        if (this.density == 0.0f || this.densityDpi == 0) {
            return;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LogUtils.d(this.TAG, " displayMetrics  density = " + this.density + " ;; densityDpi = " + this.densityDpi);
        displayMetrics.density = this.density;
        displayMetrics.densityDpi = this.densityDpi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPop() {
        ArrayList<SelectedButton> arrayList;
        this.mMode = 0;
        if (this.mPopupWindow == null || (arrayList = this.mBtnList) == null) {
            return;
        }
        Iterator<SelectedButton> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().setChecked(false);
        }
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.mPopupWindow.dismiss();
    }

    private void startHandlePlay() {
        Intent intent = new Intent();
        intent.setPackage(HANDLE_PLAY_PACKAGE_NAME);
        intent.setAction(HANDLE_PLAY_ACTION);
        intent.addFlags(268435456);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(this.TAG, " startHandlePlay exception ----- ", e);
        }
    }

    private void startMousePlay() {
        Intent intent = new Intent();
        intent.setAction(KEYBOARD_MOUSE_PLAY_ACTION);
        intent.addFlags(268435456);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(this.TAG, " startMousePlay err  ", e);
        }
    }

    private void startPcPlay() {
        Intent intent = new Intent();
        intent.setClassName("com.zte.pcgame", "com.zte.pcgame.activity.PCGameLauncher");
        intent.addFlags(268435456);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(this.TAG, " startPcPlay exception ----- ", e);
        }
    }

    private void startProjectionGravitation() {
        try {
            startActivity(new Intent(this, (Class<?>) TouPingGravitationActivity.class));
        } catch (ActivityNotFoundException e) {
            Log.e(this.TAG, " startProjectionGravitation err  ", e);
        }
    }

    private void startStreamPlay() {
        Intent intent = new Intent();
        intent.setClassName("com.zte.streamgame", "com.zte.streamgame.StreamGameActivity");
        intent.addFlags(268435456);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(this.TAG, " startHandlePlay exception ----- ", e);
        }
    }

    public void clickBack(View view) {
        onBackPressed();
    }

    public void clickHandle(View view) {
        if (isSameMode(1)) {
            return;
        }
        clickSelectedButton(view, R.string.large_handle_details, false);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_superbase_3a", "xgravity_3a", "xgravity_3a_rmfw");
    }

    public void clickKeyboard(View view) {
        if (isSameMode(-2)) {
            return;
        }
        clickSelectedButton(view, R.string.large_keyboard_details, true);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_superbase_3a", "xgravity_3a", "xgravity_3a_rmzb");
    }

    public void clickMirror(View view) {
        if (isSameMode(3)) {
            return;
        }
        clickSelectedButton(view, R.string.large_mirror_details, false);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_superbase_3a", "xgravity_3a", "xgravity_3a_rmfw");
    }

    public void clickMonitor(View view) {
        if (isSameMode(-3)) {
            return;
        }
        clickSelectedButton(view, R.string.large_monitor_details, true);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_superbase_3a", "xgravity_3a", "xgravity_3a_rmzb");
    }

    public void clickMouse(View view) {
        if (isSameMode(-1)) {
            return;
        }
        clickSelectedButton(view, R.string.large_mouse_details, true);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_superbase_3a", "xgravity_3a", "xgravity_3a_rmzb");
    }

    public void clickPlayMouse(View view) {
        if (isSameMode(2)) {
            return;
        }
        clickSelectedButton(view, R.string.large_p_mouse_details, false);
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_superbase_3a", "xgravity_3a", "xgravity_3a_rmfw");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        exitAnim();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Log.d("selected", "onClick()");
        switch (view.getId()) {
            case R.id.content /* 2131362048 */:
            case R.id.explain_content /* 2131362224 */:
            case R.id.explain_details /* 2131362225 */:
            case R.id.large_game_explain /* 2131362671 */:
            case R.id.scroll_explain /* 2131363183 */:
            case R.id.tab_content /* 2131363392 */:
                resetPop();
                break;
            case R.id.iv_3a_game_pc_card /* 2131362628 */:
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "pcgame_mode_gravityx_homepage", "status", "emulator");
                startPcPlay();
                break;
            case R.id.iv_3a_game_stream_card /* 2131362629 */:
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "pcgame_mode_gravityx_homepage", "status", "steaming");
                startStreamPlay();
                break;
        }
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindowManager().getDefaultDisplay().getRealMetrics(new DisplayMetrics());
        if (Math.max(r4.widthPixels, r4.heightPixels) / Math.min(r4.widthPixels, r4.heightPixels) <= 1.6f) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            this.density = displayMetrics.density;
            this.densityDpi = displayMetrics.densityDpi;
            displayMetrics.density = Math.min(r4.widthPixels, r4.heightPixels) / 800.0f;
            displayMetrics.densityDpi = (int) (displayMetrics.density * 320.0f);
        }
        initView();
        initResMap();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        resetDensity();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        resetPop();
        enterAnim();
    }

    @Override // cn.nubia.gamelauncher.view.LargeGameTabView.OnTabChangeListener
    public void onTabChanged(int i) {
        resetPop();
        this.mTabContentGame1.setVisibility(0);
        this.mTabContentGame2.setVisibility(0);
        this.mTabContentGame3.setVisibility(0);
        this.mLargeGameTips1.setVisibility(0);
        this.mLargeGameTips2.setVisibility(0);
        this.mLargeGameTips2.setText(R.string.large_game_tips_2_stream);
        if (i == R.id.xbox) {
            this.mTabContentGame1.setImageResource(R.mipmap.large_xbox_1);
            this.mTabContentGame2.setImageResource(R.mipmap.large_xbox_2);
            this.mTabContentGame3.setImageResource(R.mipmap.large_xbox_3);
            this.mLargeGameTips2.setText(R.string.large_game_tips_2_xbox);
        } else if (i == R.id.steam) {
            this.mTabContentGame1.setImageResource(R.mipmap.large_steam_1);
            this.mTabContentGame2.setImageResource(R.mipmap.large_steam_2);
            this.mTabContentGame3.setImageResource(R.mipmap.large_steam_3);
        } else if (i == R.id.station) {
            this.mTabContentGame1.setImageResource(R.mipmap.large_play_station_1);
            this.mTabContentGame2.setImageResource(R.mipmap.large_play_station_2);
            this.mTabContentGame3.setImageResource(R.mipmap.large_play_station_3);
            this.mLargeGameTips1.setVisibility(8);
        } else if (i == R.id.epic) {
            this.mTabContentGame1.setImageResource(R.mipmap.large_epic_1);
            this.mTabContentGame2.setImageResource(R.mipmap.large_epic_2);
            this.mTabContentGame3.setVisibility(4);
            this.mLargeGameTips1.setVisibility(8);
        }
        NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "xgravity_superbase_3a", "xgravity_3a", "xgravity_3a_play");
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        resetPop();
        return false;
    }
}
