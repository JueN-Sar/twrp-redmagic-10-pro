package com.zte.gameassist.lowsugar.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.lowsugar.LowSugarGameplayController;
import com.zte.gameassist.lowsugar.R;
import com.zte.gameassist.lowsugar.ai.LowSugarAiMgr;
import com.zte.gameassist.lowsugar.common.Constants;
import com.zte.gameassist.lowsugar.provider.LowSugarColumn;
import com.zte.gameassist.lowsugar.ui.LowSugarListAdapter;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import com.zte.mifavor.widget.AlertDialog;

/* loaded from: classes2.dex */
public class LowSugarView extends RelativeLayout implements RotationMgr.Callback {
    private static final int QUERY_LOW_SUGAR_TOKEN = 0;
    private static final String TAG = "LowSugarView";
    private static final long THUMB_ANIMATION_DURATION_MS = 250;
    private static final long THUMB_ANIMATION_START_DURATION_MS = 0;
    private static final float THUMB_X_OFFSET_DIP = 30.0f;
    private int automaticRecognitionTaskFirstInit;
    private ImageView automaticRecognitionTaskSwitchButtonThumbBlack;
    private ImageView automaticRecognitionTaskSwitchButtonThumbRed;
    private ImageView automaticRecognitionTaskSwitchButtonTrackBlack;
    private ImageView automaticRecognitionTaskSwitchButtonTrackGray;
    private ImageView enableSwitchButtonThumbBlack;
    private ImageView enableSwitchButtonThumbRed;
    private ImageView enableSwitchButtonTrackBlack;
    private ImageView enableSwitchButtonTrackGray;
    private ContentObserver mAiDataObserver;
    private ToggleButton mAutomaticRecognitionTaskSwitch;
    private View mAutomaticRecognitionTaskSwitchLayout;
    private ImageView mCloseIconView;
    private ContentObserver mContentObserver;
    private RelativeLayout mContentView;
    private RelativeLayout.LayoutParams mContentViewLayoutParams;
    private AlertDialog mDialog;
    private ToggleButton mEnableSwitch;
    private View mEnableSwitchLayout;
    private Handler mHandler;
    private ImageView mHelpIconView;
    private boolean mInflateSwitch;
    private TextView mLowSugarAutomaticRecognitionTaskSummary;
    private View mLowSugarAutomaticRecognitionTaskSwtichPanel;
    private TextView mLowSugarAutomaticRecognitionTaskTitle;
    private ImageView mLowSugarEmptyIconView;
    private View mLowSugarEmptyView;
    private ContentObserver mLowSugarEnableContentObserver;
    private LowSugarEventQueryHanlder mLowSugarEventQueryHanlder;
    private LowSugarListAdapter mLowSugarListAdapter;
    private ListView mLowSugarListView;
    private TextView mLowSugarpropose;
    private ImageView mTestIconView;

    private final class LowSugarEventQueryHanlder extends AsyncQueryHandler {
        public LowSugarEventQueryHanlder(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override // android.content.AsyncQueryHandler
        protected void onQueryComplete(int i2, Object obj, Cursor cursor) {
            GaLog.b(LowSugarView.TAG, "onQueryComplete cursor = " + cursor);
            super.onQueryComplete(i2, obj, cursor);
            if (i2 != 0) {
                return;
            }
            if (cursor == null || cursor.getCount() == 0) {
                LowSugarView.this.mLowSugarEmptyIconView.setVisibility(0);
                LowSugarView.this.mLowSugarEmptyView.setVisibility(0);
                LowSugarView.this.mLowSugarListView.setVisibility(8);
                LowSugarView.this.mLowSugarListAdapter.changeCursor(null);
                return;
            }
            LowSugarView.this.mLowSugarEmptyIconView.setVisibility(8);
            LowSugarView.this.mLowSugarEmptyView.setVisibility(8);
            LowSugarView.this.mLowSugarListView.setVisibility(0);
            LowSugarView.this.mLowSugarListAdapter.changeCursor(cursor);
        }
    }

    public interface RemoveViewListener {
    }

    public LowSugarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.automaticRecognitionTaskFirstInit = 0;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mInflateSwitch = false;
        Handler handler = null;
        this.mContentObserver = new ContentObserver(handler) { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                LowSugarView.this.F();
            }
        };
        this.mLowSugarEnableContentObserver = new ContentObserver(handler) { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                super.onChange(z);
                GaLog.b(LowSugarView.TAG, "mLowSugarEnableContentObserver onChange");
                LowSugarView.this.w(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LowSugarView.this.N();
                    }
                });
            }
        };
        this.mAiDataObserver = new ContentObserver(handler) { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.3
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                GaLog.b(LowSugarView.TAG, "mAiDataObserver onChange uri = " + uri.toString());
                if ("com.zte.aispeaker.contentProvider".equals(uri.getAuthority())) {
                    final String queryParameter = uri.getQueryParameter("aigcUserLogged");
                    GaLog.b(LowSugarView.TAG, "mAiDataObserver is AI_SPEAKER_PROVIDER aigcUserLogged = " + queryParameter);
                    LowSugarView.this.w(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            GaLog.a(LowSugarView.TAG, "mAiDataObserver aigcUserLogged = " + queryParameter);
                            if ("true".equals(queryParameter)) {
                                Settings.Global.putInt(LowSugarView.this.getContext().getContentResolver(), "nubia_account_login_status", 1);
                                if (Settings.Global.getInt(LowSugarView.this.getContext().getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) == 1) {
                                    return;
                                }
                                LowSugarView.this.K(true);
                                return;
                            }
                            if (!"false".equals(queryParameter)) {
                                GaLog.b(LowSugarView.TAG, "mAiDataObserver is not user login info!");
                                return;
                            }
                            Settings.Global.putInt(LowSugarView.this.getContext().getContentResolver(), "nubia_account_login_status", -1);
                            LowSugarAiMgr.F().U(1);
                            LowSugarView.this.K(false);
                            LowSugarWindowManager.d().i();
                        }
                    });
                }
            }
        };
    }

    private void A(boolean z, View view, View view2, View view3, View view4, boolean z2) {
        GaLog.b(TAG, "setEnableThumbAnim anim = " + z + ", checked = " + z2);
        B(view, view2, view3, view4, z2, z ? THUMB_ANIMATION_DURATION_MS : 0L);
    }

    private void B(View view, View view2, View view3, View view4, boolean z, long j2) {
        AnimatorSet animatorSet = new AnimatorSet();
        float applyDimension = TypedValue.applyDimension(1, THUMB_X_OFFSET_DIP, getResources().getDisplayMetrics());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", 0.0f, applyDimension);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "translationX", 0.0f, applyDimension);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "translationX", applyDimension, 0.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view2, "translationX", applyDimension, 0.0f);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(view2, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(view3, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(view4, "alpha", 0.0f, 1.0f);
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(view2, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(view3, "alpha", 1.0f, 0.0f);
        ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(view4, "alpha", 1.0f, 0.0f);
        animatorSet.setDuration(j2);
        if (!z) {
            ofFloat = ofFloat3;
        }
        if (!z) {
            ofFloat2 = ofFloat4;
        }
        if (z) {
            ofFloat5 = ofFloat9;
        }
        if (!z) {
            ofFloat6 = ofFloat10;
        }
        if (z) {
            ofFloat7 = ofFloat11;
        }
        if (!z) {
            ofFloat8 = ofFloat12;
        }
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat5, ofFloat6, ofFloat7, ofFloat8);
        animatorSet.start();
    }

    private void C() {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.low_sugar_automatic_recognition_task_switch);
        this.mAutomaticRecognitionTaskSwitch = toggleButton;
        toggleButton.setClickable(true);
        this.mAutomaticRecognitionTaskSwitchLayout = findViewById(R.id.low_sugar_automatic_recognition_task_switch_button_layout);
        this.automaticRecognitionTaskSwitchButtonTrackBlack = (ImageView) findViewById(R.id.low_sugar_automatic_recognition_task_switch_button_track_black);
        this.automaticRecognitionTaskSwitchButtonTrackGray = (ImageView) findViewById(R.id.low_sugar_automatic_recognition_task_switch_button_track_gray);
        this.automaticRecognitionTaskSwitchButtonThumbRed = (ImageView) findViewById(R.id.low_sugar_automatic_recognition_task_switch_button_thumb_red);
        this.automaticRecognitionTaskSwitchButtonThumbBlack = (ImageView) findViewById(R.id.low_sugar_automatic_recognition_task_switch_button_thumb_black);
        this.mAutomaticRecognitionTaskSwitchLayout.setAlpha(1.0f);
        this.mAutomaticRecognitionTaskSwitch.setOnCheckedChangeListener(q());
    }

    private void D() {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.low_sugar_enable_switch);
        this.mEnableSwitch = toggleButton;
        toggleButton.setClickable(true);
        this.mEnableSwitchLayout = findViewById(R.id.low_sugar_enable_switch_button_layout);
        this.enableSwitchButtonTrackBlack = (ImageView) findViewById(R.id.low_sugar_enable_switch_button_track_black);
        this.enableSwitchButtonTrackGray = (ImageView) findViewById(R.id.low_sugar_enable_switch_button_track_gray);
        this.enableSwitchButtonThumbRed = (ImageView) findViewById(R.id.low_sugar_enable_switch_button_thumb_red);
        this.enableSwitchButtonThumbBlack = (ImageView) findViewById(R.id.low_sugar_enable_switch_button_thumb_black);
        this.mEnableSwitchLayout.setAlpha(1.0f);
        this.mEnableSwitch.setOnCheckedChangeListener(r());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(final Runnable runnable) {
        t();
        AlertDialog a2 = new AlertDialog.Builder(getContext(), com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).l(R.string.ic_qs_low_sugar_describe_title).c(true).d(ZteFeature.isSupportGameVoiceAssist() ? R.string.ic_qs_low_sugar_mora_describe_content : R.string.ic_qs_low_sugar_demi_describe_content).i(com.zte.gameassist.common.R.string.single_ok, new DialogInterface.OnClickListener() { // from class: com.zte.gameassist.lowsugar.ui.f
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                runnable.run();
            }
        }).a();
        this.mDialog = a2;
        a2.setCanceledOnTouchOutside(true);
        this.mDialog.getWindow().setType(2008);
        this.mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.mDialog.getWindow().getDecorView().setSystemUiVisibility(6);
        this.mDialog.show();
        GameAssistDialog.f(this.mDialog.getWindow());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        GaLog.a(TAG, "startQuery");
        LowSugarEventQueryHanlder lowSugarEventQueryHanlder = this.mLowSugarEventQueryHanlder;
        if (lowSugarEventQueryHanlder != null) {
            lowSugarEventQueryHanlder.cancelOperation(0);
            this.mLowSugarEventQueryHanlder.startQuery(0, null, LowSugarColumn.f16922a, null, "time>?", new String[]{Long.toString(System.currentTimeMillis())}, "time asc, _id asc");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G(boolean z, boolean z2) {
        this.mAutomaticRecognitionTaskSwitch.setChecked(z);
        if (z2) {
            z(this.automaticRecognitionTaskSwitchButtonThumbBlack, this.automaticRecognitionTaskSwitchButtonThumbRed, this.automaticRecognitionTaskSwitchButtonTrackBlack, this.automaticRecognitionTaskSwitchButtonTrackGray, z);
        }
    }

    private void H(boolean z, boolean z2) {
        GaLog.b(TAG, "syncEnableSwitchView anim = " + z2 + ", enable = " + z);
        this.mEnableSwitch.setChecked(z);
        A(z2, this.enableSwitchButtonThumbBlack, this.enableSwitchButtonThumbRed, this.enableSwitchButtonTrackBlack, this.enableSwitchButtonTrackGray, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(boolean z) {
        Settings.Global.putInt(getContext().getContentResolver(), "nubia_low_sugar_automatic_recognition_task_pkg_open", z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K(boolean z) {
        GaLog.b(TAG, "updateEnable isChecked = " + z + ", mInflateSwitch = " + this.mInflateSwitch);
        H(z, this.mInflateSwitch ^ true);
        this.mInflateSwitch = false;
        L(z);
        O(z);
    }

    private void L(boolean z) {
        Settings.Global.putInt(getContext().getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", z ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        final boolean z = Settings.Global.getInt(getContext().getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) == 1;
        H(z, false);
        GaLog.b(TAG, "updateSwitch isLowSugarEnable = " + z);
        G(Settings.Global.getInt(getContext().getContentResolver(), "nubia_low_sugar_automatic_recognition_task_pkg_open", 0) == 1, false);
        w(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.7
            @Override // java.lang.Runnable
            public void run() {
                LowSugarView.this.O(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(boolean z) {
        if (z) {
            this.mAutomaticRecognitionTaskSwitch.setEnabled(true);
            this.mLowSugarAutomaticRecognitionTaskTitle.setAlpha(1.0f);
            this.mLowSugarAutomaticRecognitionTaskSummary.setAlpha(1.0f);
            this.mLowSugarAutomaticRecognitionTaskSwtichPanel.setAlpha(1.0f);
            this.mLowSugarpropose.setAlpha(1.0f);
            this.mLowSugarListView.setAlpha(1.0f);
            this.mLowSugarListView.setEnabled(true);
            this.mLowSugarEmptyView.setAlpha(1.0f);
            this.mLowSugarEmptyIconView.setAlpha(1.0f);
            return;
        }
        this.mAutomaticRecognitionTaskSwitch.setEnabled(false);
        this.mLowSugarAutomaticRecognitionTaskTitle.setAlpha(0.3f);
        this.mLowSugarAutomaticRecognitionTaskSummary.setAlpha(0.3f);
        this.mLowSugarAutomaticRecognitionTaskSwtichPanel.setAlpha(0.3f);
        this.mLowSugarpropose.setAlpha(0.3f);
        this.mLowSugarListView.setAlpha(0.3f);
        this.mLowSugarListView.setEnabled(false);
        this.mLowSugarEmptyView.setAlpha(0.3f);
        this.mLowSugarEmptyIconView.setAlpha(0.3f);
    }

    private CompoundButton.OnCheckedChangeListener q() {
        return new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LowSugarView.this.G(z, true);
                LowSugarView.this.J(z);
            }
        };
    }

    private CompoundButton.OnCheckedChangeListener r() {
        return new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                GaLog.b(LowSugarView.TAG, "onCheckedChanged isChecked = " + z);
                if (ZteFeature.IS_INTER_VERSION || !z || LowSugarUtils.p(LowSugarView.this.getContext())) {
                    LowSugarView.this.K(z);
                } else {
                    LowSugarUtils.b(LowSugarView.this.getContext());
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public void w(final Runnable runnable) {
        if (this.mHandler.getLooper().isCurrentThread()) {
            runnable.run();
        } else {
            this.mHandler.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.e
                @Override // java.lang.Runnable
                public final void run() {
                    LowSugarView.this.w(runnable);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    private void v() {
        LowSugarListAdapter lowSugarListAdapter = new LowSugarListAdapter(getContext(), null);
        this.mLowSugarListAdapter = lowSugarListAdapter;
        lowSugarListAdapter.a(new LowSugarListAdapter.OnDataChangedListener() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.8
            @Override // com.zte.gameassist.lowsugar.ui.LowSugarListAdapter.OnDataChangedListener
            public void a() {
                LowSugarView.this.F();
            }
        });
        this.mLowSugarListView.setAdapter((ListAdapter) this.mLowSugarListAdapter);
        this.mLowSugarEventQueryHanlder = new LowSugarEventQueryHanlder(getContext().getContentResolver());
        F();
    }

    private void z(View view, View view2, View view3, View view4, boolean z) {
        GaLog.b(TAG, "setAutomaticRecognitionTaskThumbAnim checked = " + z);
        int i2 = this.automaticRecognitionTaskFirstInit;
        long j2 = i2 == 0 ? 0L : THUMB_ANIMATION_DURATION_MS;
        if (i2 == 0) {
            this.automaticRecognitionTaskFirstInit = i2 + 1;
        }
        B(view, view2, view3, view4, z, j2);
    }

    public void I() {
        GaLog.b(TAG, "update");
    }

    public void M(boolean z) {
        if (z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mContentView.getLayoutParams();
            this.mContentViewLayoutParams = layoutParams;
            layoutParams.removeRule(12);
            this.mContentViewLayoutParams.addRule(11);
            this.mContentViewLayoutParams.removeRule(14);
            this.mContentViewLayoutParams.addRule(15);
            RelativeLayout.LayoutParams layoutParams2 = this.mContentViewLayoutParams;
            layoutParams2.bottomMargin = 0;
            layoutParams2.rightMargin = 48;
            return;
        }
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.mContentView.getLayoutParams();
        this.mContentViewLayoutParams = layoutParams3;
        layoutParams3.removeRule(11);
        this.mContentViewLayoutParams.addRule(12);
        this.mContentViewLayoutParams.removeRule(15);
        this.mContentViewLayoutParams.addRule(14);
        RelativeLayout.LayoutParams layoutParams4 = this.mContentViewLayoutParams;
        layoutParams4.rightMargin = 0;
        layoutParams4.bottomMargin = 48;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        GaLog.a(TAG, "onAttachedToWindow");
        getContext().getContentResolver().registerContentObserver(LowSugarColumn.f16922a, false, this.mContentObserver);
        getContext().getContentResolver().registerContentObserver(Constants.f16792a, false, this.mLowSugarEnableContentObserver);
        RotationMgr.e(getContext()).c(this);
        if (!LowSugarUtils.p(getContext())) {
            getContext().getContentResolver().registerContentObserver(LowSugarUtils.f17021p, true, this.mAiDataObserver);
        }
        N();
        F();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GaLog.a(TAG, "onDetachedFromWindow");
        if (this.mContentObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
        }
        if (this.mLowSugarEnableContentObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mLowSugarEnableContentObserver);
        }
        if (this.mAiDataObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(this.mAiDataObserver);
        }
        RotationMgr.e(getContext()).p(this);
        this.automaticRecognitionTaskFirstInit = 0;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mInflateSwitch = true;
        this.mHandler = new Handler();
        this.mContentView = (RelativeLayout) findViewById(R.id.low_sugar_content);
        this.mHelpIconView = (ImageView) findViewById(R.id.low_sugar_help);
        this.mCloseIconView = (ImageView) findViewById(R.id.low_sugar_close);
        ImageView imageView = (ImageView) findViewById(R.id.low_sugar_test);
        this.mTestIconView = imageView;
        imageView.setVisibility(LowSugarUtils.f17012g ? 0 : 8);
        this.mLowSugarEmptyView = findViewById(R.id.low_sugar_empty);
        this.mLowSugarEmptyIconView = (ImageView) findViewById(R.id.low_sugar_empty_icon);
        this.mLowSugarListView = (ListView) findViewById(R.id.low_sugar_event_list);
        this.mLowSugarAutomaticRecognitionTaskTitle = (TextView) findViewById(R.id.low_sugar_automatic_recognition_task_title);
        this.mLowSugarAutomaticRecognitionTaskSummary = (TextView) findViewById(R.id.low_sugar_automatic_recognition_task_summary);
        this.mLowSugarAutomaticRecognitionTaskSwtichPanel = findViewById(R.id.low_sugar_automatic_recognition_task_switch_panel);
        this.mLowSugarpropose = (TextView) findViewById(R.id.low_sugar_propose);
        this.mHelpIconView.setOnClickListener(new View.OnClickListener() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!LowSugarUtils.f17012g) {
                    LowSugarView.this.E(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LowSugarView.this.t();
                        }
                    });
                    return;
                }
                GaLog.a(LowSugarView.TAG, "onFinishInflate now is test and startManualPurpose!");
                LowSugarGameplayController.l().z();
            }
        });
        this.mCloseIconView.setOnClickListener(new View.OnClickListener(this) { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LowSugarWindowManager.d().i();
            }
        });
        D();
        C();
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.mDialog.getWindow());
    }

    public void setRemoveViewListener(RemoveViewListener removeViewListener) {
    }

    public void setTestIconView(final Bitmap bitmap) {
        w(new Runnable() { // from class: com.zte.gameassist.lowsugar.ui.LowSugarView.6
            @Override // java.lang.Runnable
            public void run() {
                if (LowSugarView.this.mTestIconView != null) {
                    LowSugarView.this.mTestIconView.setImageBitmap(bitmap);
                }
            }
        });
    }

    public void u() {
        GaLog.b(TAG, "init");
        v();
    }

    public void y() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        for (int i2 = 0; i2 < this.mLowSugarListView.getChildCount(); i2++) {
            View childAt = this.mLowSugarListView.getChildAt(i2);
            if (childAt instanceof LowSugarItem) {
                ((LowSugarItem) childAt).j();
            }
        }
    }
}
