package com.zte.mifavor.widget;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.text.TextUtilsCompat;
import com.google.android.material.card.MaterialCardView;
import com.zte.aimodel.ModelManager;
import com.zte.aimodel.asr.IASRActor;
import com.zte.aimodel.asr.IASRCallback;
import com.zte.extres.R;
import com.zte.mifavor.utils.AudioRecorder;
import java.util.Locale;

/* loaded from: classes2.dex */
public class VoiceSearchViewZTE extends SearchView {
    private static final String ANDROID = "android";
    private static final int CLOSE_BTN_PADDING = 3;
    private static final int CLOSE_BTN_WIDTH = 24;
    private static final String ID = "id";
    private static final int LAYOUT_WEIGHT = 1;
    private static final float PADDING_LEFT_RIGHT = 0.25f;
    private static final int PADDING_NO = 0;
    private static final int PADDING_PLATE_RIGHT = 1;
    private static final String SEARCH_BTN = "search_mag_icon";
    private static final String SEARCH_CLOSE_BTN = "search_close_btn";
    private static final String SEARCH_EDIT_FRAME = "search_edit_frame";
    private static final String SEARCH_PLATE = "search_plate";
    private static final String SEARCH_SRC_TEXT = "search_src_text";
    private static final String SEARCH_VOICE_BTN = "search_voice_btn";
    public static final String START_VOICE_ACTION = "com.zte.mifavor.widget.START_VOICE_ACTION";
    private static final String TAG = "Z#SearchViewZTEVoice";
    private static final int TIME_OUT = 3030;
    private IASRActor asr;
    private AudioRecorder audioRecorder;
    IASRCallback mASRCallback;
    private ImageView mCloseBtn;
    private Context mContext;
    private boolean mEditState;
    private final Handler mHandler;
    private boolean mInUse;
    private boolean mIsSupportVoice;
    private OnStartRecognizeListener mOnStartRecognizeListener;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private BroadcastReceiver mStartVoiceBroadcastReceiver;
    private boolean mUsingException;
    private View mVoiceView;
    private int mX;
    private int mY;

    /* renamed from: com.zte.mifavor.widget.VoiceSearchViewZTE$1, reason: invalid class name */
    class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        private IntEvaluator f17834c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f17835h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f17836i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ int f17837j;

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ int f17838k;

        /* renamed from: l, reason: collision with root package name */
        final /* synthetic */ VoiceSearchViewZTE f17839l;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue() / 100.0f;
            int intValue2 = this.f17834c.evaluate(intValue, Integer.valueOf(this.f17835h), Integer.valueOf(this.f17836i)).intValue();
            int intValue3 = this.f17834c.evaluate(intValue, Integer.valueOf(this.f17837j), Integer.valueOf(this.f17838k)).intValue();
            this.f17839l.setLeft(intValue2);
            this.f17839l.setRight(intValue2 + intValue3);
            this.f17839l.getLayoutParams().width = intValue3;
            this.f17839l.requestLayout();
        }
    }

    public interface OnCloseListener extends SearchView.OnCloseListener {
    }

    public interface OnQueryTextListener extends SearchView.OnQueryTextListener {
    }

    public interface OnStartRecognizeListener {
    }

    public VoiceSearchViewZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEditState = true;
        this.mContext = null;
        this.mSearchAutoComplete = null;
        this.mCloseBtn = null;
        this.mIsSupportVoice = false;
        this.mVoiceView = null;
        this.mX = 0;
        this.mY = 0;
        this.mInUse = false;
        this.mUsingException = false;
        this.mOnStartRecognizeListener = null;
        this.asr = null;
        this.audioRecorder = null;
        this.mASRCallback = new IASRCallback.Stub() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.3
        };
        this.mHandler = new Handler() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != VoiceSearchViewZTE.TIME_OUT) {
                    return;
                }
                VoiceSearchViewZTE.this.mInUse = false;
                Log.w(VoiceSearchViewZTE.TAG, "It is time out and stop Recognize. mInUse=" + VoiceSearchViewZTE.this.mInUse);
                VoiceSearchViewZTE.this.r();
                Toast.makeText(VoiceSearchViewZTE.this.getContext(), VoiceSearchViewZTE.this.getContext().getResources().getString(R.string.stop_recognize), 0).show();
                VoiceSearchViewZTE.this.q(true);
            }
        };
        this.mStartVoiceBroadcastReceiver = new BroadcastReceiver() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    return;
                }
                String action = intent.getAction();
                Log.d(VoiceSearchViewZTE.TAG, "onReceive action=" + action);
                if (VoiceSearchViewZTE.START_VOICE_ACTION.equals(action)) {
                    VoiceSearchViewZTE.this.s();
                    Log.d(VoiceSearchViewZTE.TAG, "unregister Receiver...");
                    VoiceSearchViewZTE.this.getContext().unregisterReceiver(VoiceSearchViewZTE.this.mStartVoiceBroadcastReceiver);
                }
            }
        };
        this.mContext = context;
        n(context);
    }

    private WindowManager.LayoutParams getVoiceWindowParams() {
        Log.d(TAG, "get Voice Window Params in.");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.flags = 40;
        layoutParams.format = -3;
        layoutParams.x = this.mX;
        layoutParams.y = this.mY;
        layoutParams.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_START;
        layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.voice_icon_height);
        layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.voice_icon_width);
        Log.d(TAG, "get Voice Window Params out. params = " + layoutParams);
        return layoutParams;
    }

    private WindowManager getWindowManager() {
        return (WindowManager) this.mContext.getSystemService("window");
    }

    private void n(Context context) {
        this.mIsSupportVoice = Utils.u();
        Log.d(TAG, "init View. mIsSupportVoice=" + this.mIsSupportVoice);
        this.mVoiceView = LayoutInflater.from(this.mContext).inflate(R.layout.voice_search_layout, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) findViewById(context.getResources().getIdentifier(SEARCH_EDIT_FRAME, "id", ANDROID));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1, 1.0f);
        layoutParams.gravity = 8388611;
        linearLayout.setLayoutParams(layoutParams);
        if (TextUtilsCompat.a(Locale.getDefault()) == 1) {
            ((LinearLayout) findViewById(context.getResources().getIdentifier(SEARCH_PLATE, "id", ANDROID))).setPaddingRelative((int) context.getResources().getDimension(R.dimen.mfvc_ic_txt_padding), linearLayout.getPaddingTop(), Utils.c(getContext(), 1), linearLayout.getPaddingBottom());
        }
        SearchView.SearchAutoComplete findViewById = findViewById(context.getResources().getIdentifier(SEARCH_SRC_TEXT, "id", ANDROID));
        this.mSearchAutoComplete = findViewById;
        findViewById.setPaddingRelative(Utils.b(getContext(), 0.25d), 0, Utils.b(getContext(), 0.25d), 0);
        this.mSearchAutoComplete.setGravity(8388627);
        this.mSearchAutoComplete.setTextAppearance(R.style.mfvc_appbar_search_normal_font);
        this.mSearchAutoComplete.setTextColor(context.getColor(R.color.mfv_common_acb_search_txt));
        boolean isIconfiedByDefault = isIconfiedByDefault();
        Log.d(TAG, "init View. isIconfied=" + isIconfiedByDefault);
        if (!isIconfiedByDefault) {
            Drawable drawable = context.getDrawable(R.drawable.search_hint_x);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(drawable, 2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(imageSpan, 1, 2, 33);
            spannableStringBuilder.append(getQueryHint());
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.mfv_common_acb_search_txt_watermark)), 3, spannableStringBuilder.length(), 33);
            this.mSearchAutoComplete.setHint(spannableStringBuilder);
        }
        ImageView imageView = (ImageView) findViewById(context.getResources().getIdentifier(SEARCH_CLOSE_BTN, "id", ANDROID));
        this.mCloseBtn = imageView;
        imageView.setPaddingRelative(Utils.c(getContext(), 3), Utils.c(getContext(), 3), Utils.c(getContext(), 3), Utils.c(getContext(), 3));
        this.mCloseBtn.setColorFilter(context.getColor(R.color.mfv_common_acb_search_clear));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(Utils.c(getContext(), CLOSE_BTN_WIDTH), Utils.c(getContext(), CLOSE_BTN_WIDTH));
        layoutParams2.gravity = 16;
        layoutParams2.setMarginEnd((int) context.getResources().getDimension(R.dimen.mfvc_small_padding));
        this.mCloseBtn.setLayoutParams(layoutParams2);
        this.mCloseBtn.setVisibility(8);
        Log.d(TAG, "init View out. set mCloseBtn GONE. mIsSupportVoice=" + this.mIsSupportVoice);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }

    private void p() {
        l();
        o();
        new Thread(new Runnable() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    VoiceSearchViewZTE.this.asr = ModelManager.pickIASRActor("microsoft", "com.android.settings");
                    Log.d(VoiceSearchViewZTE.TAG, "start Recognize User registerCallback. asr=" + VoiceSearchViewZTE.this.asr);
                    if (VoiceSearchViewZTE.this.asr != null) {
                        VoiceSearchViewZTE.this.asr.registerCallback(VoiceSearchViewZTE.this.mASRCallback);
                        VoiceSearchViewZTE.this.asr.init("zh-CN");
                        VoiceSearchViewZTE.this.t(VoiceSearchViewZTE.this.asr.getStream());
                        VoiceSearchViewZTE.this.asr.startRecognize();
                    } else {
                        VoiceSearchViewZTE.this.mUsingException = true;
                        Log.e(VoiceSearchViewZTE.TAG, "registerCallback error. asr is null. mUsingException=" + VoiceSearchViewZTE.this.mUsingException);
                    }
                } catch (RemoteException e2) {
                    VoiceSearchViewZTE.this.mUsingException = true;
                    Log.e(VoiceSearchViewZTE.TAG, "start Recognize User. RemoteException e=" + e2);
                } catch (Exception e3) {
                    VoiceSearchViewZTE.this.mUsingException = true;
                    Log.e(VoiceSearchViewZTE.TAG, "start Recognize User. error e=" + e3);
                }
                Log.d(VoiceSearchViewZTE.TAG, "start Recognize User out.");
            }
        }, "startRecognizeUser").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(final boolean z) {
        m();
        new Thread(new Runnable() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.5
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r5v4, types: [com.zte.mifavor.widget.VoiceSearchViewZTE] */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (VoiceSearchViewZTE.this.audioRecorder != null) {
                        Log.d(VoiceSearchViewZTE.TAG, "stop Recognize stop Recording...");
                        VoiceSearchViewZTE.this.audioRecorder.c(false);
                    }
                } catch (Exception e2) {
                    Log.e(VoiceSearchViewZTE.TAG, " stop Recording error, e=", e2);
                }
                if (VoiceSearchViewZTE.this.asr != null) {
                    try {
                        try {
                            VoiceSearchViewZTE.this.asr.stopRecognize();
                            if (z) {
                                VoiceSearchViewZTE.this.asr.deinit();
                            }
                        } finally {
                            VoiceSearchViewZTE.this.asr = null;
                        }
                    } catch (Exception e3) {
                        Log.e(VoiceSearchViewZTE.TAG, "stop asr error :" + e3);
                    }
                }
                Log.d(VoiceSearchViewZTE.TAG, "stop Recognize out.");
            }
        }, "stopRecognize").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        Log.i(TAG, "voiceRecognize in. mUsingException=" + this.mUsingException);
        if (this.mUsingException) {
            return;
        }
        if (this.asr == null) {
            Log.i(TAG, "voiceRecognize start voice searching.");
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(TIME_OUT), 8000L);
            p();
            this.mInUse = true;
            r();
            return;
        }
        if (!this.mInUse) {
            Log.w(TAG, "voiceRecognize other.");
            return;
        }
        Log.i(TAG, "voiceRecognize cancel voice searching. removeMessages and sendMessage. mInUse=" + this.mInUse);
        this.mHandler.removeMessages(TIME_OUT);
        Handler handler2 = this.mHandler;
        handler2.sendMessage(handler2.obtainMessage(TIME_OUT));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(ParcelFileDescriptor parcelFileDescriptor) {
        if (this.audioRecorder != null) {
            Log.d(TAG, "write Data call start Recording...");
            this.audioRecorder.b(parcelFileDescriptor);
        }
    }

    public boolean getEditState() {
        return this.mEditState;
    }

    public int getStatusBarHeight() {
        Resources resources = this.mContext.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", ANDROID));
    }

    public void l() {
        Log.d(TAG, "attach mVoiceView = " + this.mVoiceView);
        try {
            View view = this.mVoiceView;
            if (view == null || view.isAttachedToWindow()) {
                return;
            }
            getWindowManager().addView(this.mVoiceView, getVoiceWindowParams());
            Log.w(TAG, "add view mVoiceView.");
        } catch (Exception e2) {
            Log.e(TAG, "attach error, e = ", e2);
        }
    }

    public void m() {
        Log.d(TAG, "detach mVoiceView = " + this.mVoiceView);
        View view = this.mVoiceView;
        if (view == null || !view.isAttachedToWindow()) {
            return;
        }
        getWindowManager().removeView(this.mVoiceView);
        Log.w(TAG, "detach mVoiceView.");
    }

    protected void o() {
        SearchView.SearchAutoComplete findViewById = findViewById(getContext().getResources().getIdentifier(SEARCH_SRC_TEXT, "id", ANDROID));
        if (findViewById != null) {
            findViewById.requestFocus();
            findViewById.setSelection(findViewById.getText().length());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mUsingException = false;
        if (!this.mIsSupportVoice) {
            Log.d(TAG, "onAttachedToWindow out. mIsSupportVoice=" + this.mIsSupportVoice + ", mUsingException=" + this.mUsingException);
            return;
        }
        this.audioRecorder = new AudioRecorder();
        Log.d(TAG, "onAttachedToWindow out. isbind=" + ModelManager.bindService(this.mContext) + ", mUsingException=" + this.mUsingException);
    }

    @Override // android.widget.SearchView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mUsingException = false;
        m();
        if (!this.mIsSupportVoice) {
            Log.d(TAG, "onDetachedFromWindow out. mIsSupportVoice=" + this.mIsSupportVoice);
            return;
        }
        Log.d(TAG, "onDetachedFromWindow out. isunbind=" + ModelManager.unbindService(this.mContext) + ", mUsingException=" + this.mUsingException);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "on Finish Inflate. mIsSupportVoice=" + this.mIsSupportVoice);
        if (this.mIsSupportVoice) {
            r();
        }
    }

    @Override // android.widget.SearchView, android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        this.mX = i2;
        this.mY = (i5 - getStatusBarHeight()) - 24;
        Log.d(TAG, "onLayout. left=" + i2 + ", top=" + i3 + ", right=" + i4 + ", bottom=" + i5 + ", mX=" + this.mX + ", mY=" + this.mY);
    }

    public void r() {
        SearchView.SearchAutoComplete searchAutoComplete = this.mSearchAutoComplete;
        Editable text = searchAutoComplete != null ? searchAutoComplete.getText() : null;
        Log.d(TAG, "update Colse Button Icon. content=" + ((Object) text) + ", mInUse=" + this.mInUse + ", mUsingException=" + this.mUsingException);
        if (this.mCloseBtn == null) {
            Log.e(TAG, "update Colse Button Icon error. mCloseBtn is null.");
            return;
        }
        if (!TextUtils.isEmpty(text)) {
            this.mCloseBtn.setImageResource(R.drawable.search_cancel);
            return;
        }
        if (this.mUsingException) {
            this.mCloseBtn.setImageResource(R.drawable.search_cancel);
            this.mCloseBtn.setVisibility(8);
            Log.d(TAG, "update Colse Button Icon to search_cancel and GONE");
        } else if (this.mInUse) {
            this.mCloseBtn.setImageResource(R.drawable.mic_color);
            Log.d(TAG, "update Colse Button Icon to mic_color.");
        } else {
            Log.d(TAG, "update Colse Button Icon to mic.");
            this.mCloseBtn.setImageResource(R.drawable.mic);
        }
    }

    public void setCloseBtnPadding(int i2) {
        ImageView imageView = (ImageView) findViewById(this.mContext.getResources().getIdentifier(SEARCH_CLOSE_BTN, "id", ANDROID));
        imageView.setPaddingRelative(i2, i2, i2, i2);
        imageView.setColorFilter(this.mContext.getColor(R.color.mfv_common_acb_search_clear));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Utils.c(getContext(), CLOSE_BTN_WIDTH), Utils.c(getContext(), CLOSE_BTN_WIDTH));
        layoutParams.gravity = 16;
        layoutParams.setMarginEnd((int) this.mContext.getResources().getDimension(R.dimen.mfvc_small_padding));
        imageView.setLayoutParams(layoutParams);
    }

    public void setCloseButtonColor(int i2) {
    }

    public void setEditState(boolean z) {
        this.mEditState = z;
        SearchView.SearchAutoComplete findViewById = findViewById(getContext().getResources().getIdentifier(SEARCH_SRC_TEXT, "id", ANDROID));
        if (findViewById != null) {
            findViewById.setEnabled(z);
        }
    }

    public void setOnStartRecognize(OnStartRecognizeListener onStartRecognizeListener) {
        this.mOnStartRecognizeListener = onStartRecognizeListener;
    }

    @Override // android.widget.SearchView
    public void setQueryHint(@Nullable CharSequence charSequence) {
        super.setQueryHint(charSequence);
        boolean isIconfiedByDefault = isIconfiedByDefault();
        Log.d(TAG, "set Query Hint in. isIconfied=" + isIconfiedByDefault);
        if (isIconfiedByDefault) {
            return;
        }
        SearchView.SearchAutoComplete findViewById = findViewById(getContext().getResources().getIdentifier(SEARCH_SRC_TEXT, "id", ANDROID));
        Drawable drawable = getContext().getDrawable(R.drawable.search_hint_x);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, 2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(imageSpan, 1, 2, 33);
        spannableStringBuilder.append(getQueryHint());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.mfv_common_acb_search_txt_watermark)), 3, spannableStringBuilder.length(), 33);
        findViewById.setHint(spannableStringBuilder);
    }

    public void setSearchContent(String str) {
        Log.d(TAG, "set Search Content in. content=" + str);
        setEnabled(true);
        SearchView.SearchAutoComplete findViewById = findViewById(getContext().getResources().getIdentifier(SEARCH_SRC_TEXT, "id", ANDROID));
        if (findViewById != null) {
            findViewById.setText(str);
            Log.w(TAG, "set Search Content. content=" + str);
        }
        setEnabled(false);
    }

    public void setSearchHintIconColor(int i2) {
    }

    public void setSearchVoiceText(String str) {
    }

    public void setVoiceInUse(boolean z) {
        this.mInUse = z;
    }

    public VoiceSearchViewZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mEditState = true;
        this.mContext = null;
        this.mSearchAutoComplete = null;
        this.mCloseBtn = null;
        this.mIsSupportVoice = false;
        this.mVoiceView = null;
        this.mX = 0;
        this.mY = 0;
        this.mInUse = false;
        this.mUsingException = false;
        this.mOnStartRecognizeListener = null;
        this.asr = null;
        this.audioRecorder = null;
        this.mASRCallback = new IASRCallback.Stub() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.3
        };
        this.mHandler = new Handler() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != VoiceSearchViewZTE.TIME_OUT) {
                    return;
                }
                VoiceSearchViewZTE.this.mInUse = false;
                Log.w(VoiceSearchViewZTE.TAG, "It is time out and stop Recognize. mInUse=" + VoiceSearchViewZTE.this.mInUse);
                VoiceSearchViewZTE.this.r();
                Toast.makeText(VoiceSearchViewZTE.this.getContext(), VoiceSearchViewZTE.this.getContext().getResources().getString(R.string.stop_recognize), 0).show();
                VoiceSearchViewZTE.this.q(true);
            }
        };
        this.mStartVoiceBroadcastReceiver = new BroadcastReceiver() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    return;
                }
                String action = intent.getAction();
                Log.d(VoiceSearchViewZTE.TAG, "onReceive action=" + action);
                if (VoiceSearchViewZTE.START_VOICE_ACTION.equals(action)) {
                    VoiceSearchViewZTE.this.s();
                    Log.d(VoiceSearchViewZTE.TAG, "unregister Receiver...");
                    VoiceSearchViewZTE.this.getContext().unregisterReceiver(VoiceSearchViewZTE.this.mStartVoiceBroadcastReceiver);
                }
            }
        };
        this.mContext = context;
        n(context);
    }

    public VoiceSearchViewZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mEditState = true;
        this.mContext = null;
        this.mSearchAutoComplete = null;
        this.mCloseBtn = null;
        this.mIsSupportVoice = false;
        this.mVoiceView = null;
        this.mX = 0;
        this.mY = 0;
        this.mInUse = false;
        this.mUsingException = false;
        this.mOnStartRecognizeListener = null;
        this.asr = null;
        this.audioRecorder = null;
        this.mASRCallback = new IASRCallback.Stub() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.3
        };
        this.mHandler = new Handler() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.4
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != VoiceSearchViewZTE.TIME_OUT) {
                    return;
                }
                VoiceSearchViewZTE.this.mInUse = false;
                Log.w(VoiceSearchViewZTE.TAG, "It is time out and stop Recognize. mInUse=" + VoiceSearchViewZTE.this.mInUse);
                VoiceSearchViewZTE.this.r();
                Toast.makeText(VoiceSearchViewZTE.this.getContext(), VoiceSearchViewZTE.this.getContext().getResources().getString(R.string.stop_recognize), 0).show();
                VoiceSearchViewZTE.this.q(true);
            }
        };
        this.mStartVoiceBroadcastReceiver = new BroadcastReceiver() { // from class: com.zte.mifavor.widget.VoiceSearchViewZTE.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (intent == null) {
                    return;
                }
                String action = intent.getAction();
                Log.d(VoiceSearchViewZTE.TAG, "onReceive action=" + action);
                if (VoiceSearchViewZTE.START_VOICE_ACTION.equals(action)) {
                    VoiceSearchViewZTE.this.s();
                    Log.d(VoiceSearchViewZTE.TAG, "unregister Receiver...");
                    VoiceSearchViewZTE.this.getContext().unregisterReceiver(VoiceSearchViewZTE.this.mStartVoiceBroadcastReceiver);
                }
            }
        };
        this.mContext = context;
        n(context);
    }
}
