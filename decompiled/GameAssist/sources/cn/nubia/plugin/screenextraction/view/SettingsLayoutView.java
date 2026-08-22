package cn.nubia.plugin.screenextraction.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.plugin.screenextraction.bean.ScreenExtractionData;
import cn.nubia.plugin.screenextraction.view.SettingsLayoutView;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class SettingsLayoutView extends FrameLayout implements View.OnClickListener {
    private static final int CONTENT_WIDTH_SCREEN_HORIZONTAL_DP = 428;
    private static final int CONTENT_WIDTH_SCREEN_VERTICAL_DP = 328;
    private Callback mCallback;
    private View mCancelView;
    private View mContentView;
    private ExpandPanel mExpandPanel;
    private View mOkView;
    private ScreenExtractionData mScreenExtractionData;
    private Spinner mSpinner;

    interface Callback {
        void a();

        void d(boolean z);

        void e(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ExpandPanel implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

        /* renamed from: c, reason: collision with root package name */
        private SeekBar f8621c;

        /* renamed from: h, reason: collision with root package name */
        private Button f8622h;

        /* renamed from: i, reason: collision with root package name */
        private Button f8623i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f8624j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f8625k;

        /* renamed from: l, reason: collision with root package name */
        private boolean f8626l;

        /* renamed from: m, reason: collision with root package name */
        private ValueAnimator f8627m;

        public ExpandPanel() {
            Button button = (Button) SettingsLayoutView.this.findViewById(R.id.expand_panel_button_contract_expand);
            this.f8622h = button;
            button.setOnClickListener(this);
            Button button2 = (Button) SettingsLayoutView.this.findViewById(R.id.expand_panel_button_contract);
            this.f8623i = button2;
            button2.setOnClickListener(this);
            SeekBar seekBar = (SeekBar) SettingsLayoutView.this.findViewById(R.id.alpha_set_bar);
            this.f8621c = seekBar;
            seekBar.setOnSeekBarChangeListener(this);
            this.f8621c.setMax(1000);
            this.f8621c.setMin(0);
            SettingsLayoutView.this.mContentView.setVisibility(8);
            this.f8622h.setVisibility(0);
            e();
            f(this.f8626l);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(boolean z, ValueAnimator valueAnimator) {
            Float f2 = (Float) valueAnimator.getAnimatedValue();
            if (z) {
                SettingsLayoutView.this.mContentView.setAlpha(f2.floatValue());
                this.f8622h.setAlpha(1.0f - f2.floatValue());
            } else {
                SettingsLayoutView.this.mContentView.setAlpha(1.0f - f2.floatValue());
                this.f8622h.setAlpha(f2.floatValue());
            }
            if (f2.floatValue() < 1.0f || this.f8626l == z) {
                return;
            }
            this.f8624j = false;
            this.f8627m = null;
            this.f8626l = z;
            if (SettingsLayoutView.this.mCallback != null) {
                SettingsLayoutView.this.mCallback.d(this.f8626l);
            }
            f(this.f8626l);
            boolean z2 = this.f8625k;
            if (z2 != this.f8626l) {
                d(z2);
            }
        }

        private void f(boolean z) {
            if (z) {
                SettingsLayoutView.this.mContentView.setVisibility(0);
                this.f8622h.setVisibility(8);
            } else {
                SettingsLayoutView.this.mContentView.setVisibility(8);
                this.f8622h.setVisibility(0);
            }
        }

        public float b() {
            return this.f8621c.getProgress() / 1000.0f;
        }

        public void d(final boolean z) {
            this.f8625k = z;
            if (this.f8626l == z || this.f8624j) {
                return;
            }
            this.f8624j = true;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.f8627m = ofFloat;
            ofFloat.setDuration(200L);
            SettingsLayoutView.this.mContentView.setVisibility(0);
            this.f8622h.setVisibility(0);
            this.f8627m.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.plugin.screenextraction.view.b
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SettingsLayoutView.ExpandPanel.this.c(z, valueAnimator);
                }
            });
            this.f8627m.start();
        }

        public void e() {
            this.f8621c.setProgress(SettingsLayoutView.this.mScreenExtractionData != null ? (int) (SettingsLayoutView.this.mScreenExtractionData.b() * 1000.0f) : 500);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.expand_panel_button_contract) {
                d(false);
            } else if (id == R.id.expand_panel_button_contract_expand) {
                d(true);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (SettingsLayoutView.this.mCallback != null) {
                SettingsLayoutView.this.mCallback.a();
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class Spinner implements View.OnClickListener {

        /* renamed from: c, reason: collision with root package name */
        private View f8629c;

        /* renamed from: h, reason: collision with root package name */
        private TextView f8630h;

        /* renamed from: i, reason: collision with root package name */
        private ImageView f8631i;

        /* renamed from: j, reason: collision with root package name */
        private View f8632j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f8633k;

        /* renamed from: l, reason: collision with root package name */
        private ColorStateList f8634l = ColorStateList.valueOf(-1697991);

        /* renamed from: m, reason: collision with root package name */
        private ColorStateList f8635m = ColorStateList.valueOf(-654311425);

        /* renamed from: n, reason: collision with root package name */
        private TextView[] f8636n = new TextView[3];

        /* renamed from: o, reason: collision with root package name */
        private int[] f8637o = {R.string.plugin_screen_extraction_mode_map, R.string.plugin_screen_extraction_mode_pass, R.string.plugin_screen_extraction_mode_free};

        /* renamed from: p, reason: collision with root package name */
        private int f8638p;

        /* renamed from: q, reason: collision with root package name */
        private boolean f8639q;

        /* renamed from: r, reason: collision with root package name */
        private ValueAnimator f8640r;

        /* renamed from: s, reason: collision with root package name */
        private boolean f8641s;

        public Spinner() {
            View findViewById = SettingsLayoutView.this.findViewById(R.id.spinner_title);
            this.f8629c = findViewById;
            this.f8630h = (TextView) findViewById.findViewById(R.id.spinner_mode);
            ImageView imageView = (ImageView) this.f8629c.findViewById(R.id.spinner_img);
            this.f8631i = imageView;
            imageView.setImageResource(R.drawable.plugin_screen_extraction_spinner_down);
            View findViewById2 = SettingsLayoutView.this.findViewById(R.id.spinner_content);
            this.f8632j = findViewById2;
            this.f8636n[0] = (TextView) findViewById2.findViewById(R.id.spinner_item_map);
            this.f8636n[0].setOnClickListener(this);
            this.f8636n[1] = (TextView) this.f8632j.findViewById(R.id.spinner_item_pass);
            this.f8636n[1].setOnClickListener(this);
            this.f8636n[2] = (TextView) this.f8632j.findViewById(R.id.spinner_item_free);
            this.f8636n[2].setOnClickListener(this);
            this.f8632j.setVisibility(8);
            this.f8629c.setOnClickListener(this);
            f();
            this.f8639q = false;
            e();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(boolean z, ValueAnimator valueAnimator) {
            Float f2 = (Float) valueAnimator.getAnimatedValue();
            if (z) {
                this.f8631i.setImageResource(R.drawable.plugin_screen_extraction_spinne_up);
                this.f8632j.setAlpha(f2.floatValue());
            } else {
                this.f8631i.setImageResource(R.drawable.plugin_screen_extraction_spinner_down);
                this.f8632j.setAlpha(1.0f - f2.floatValue());
            }
            if (f2.floatValue() >= 1.0f) {
                this.f8641s = false;
                this.f8640r = null;
                this.f8639q = z;
                f();
                this.f8632j.setVisibility(this.f8639q ? 0 : 8);
                boolean z2 = this.f8639q;
                boolean z3 = this.f8633k;
                if (z2 != z3) {
                    d(z3);
                }
            }
        }

        private void d(final boolean z) {
            this.f8633k = z;
            if (z == this.f8639q || this.f8641s) {
                return;
            }
            this.f8641s = true;
            this.f8632j.setVisibility(0);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.f8640r = ofFloat;
            ofFloat.setDuration(200L);
            this.f8640r.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.plugin.screenextraction.view.c
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SettingsLayoutView.Spinner.this.c(z, valueAnimator);
                }
            });
            this.f8640r.start();
        }

        private void f() {
            this.f8630h.setText(this.f8637o[this.f8638p]);
            int i2 = 0;
            while (true) {
                TextView[] textViewArr = this.f8636n;
                if (i2 >= textViewArr.length) {
                    return;
                }
                if (i2 == this.f8638p) {
                    textViewArr[i2].setTextColor(this.f8634l);
                } else {
                    textViewArr[i2].setTextColor(this.f8635m);
                }
                i2++;
            }
        }

        public int b() {
            return this.f8638p;
        }

        public void e() {
            this.f8638p = SettingsLayoutView.this.mScreenExtractionData != null ? SettingsLayoutView.this.mScreenExtractionData.d() : 0;
            f();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.spinner_title) {
                d(!this.f8639q);
                return;
            }
            if (id == R.id.spinner_item_map) {
                this.f8638p = 0;
                d(false);
                return;
            }
            if (id == R.id.spinner_item_pass) {
                this.f8638p = 1;
                d(false);
            } else if (id == R.id.spinner_item_free) {
                this.f8638p = 2;
                d(false);
            } else if (id == R.id.settings_layout_view) {
                d(this.f8639q);
            }
        }
    }

    public SettingsLayoutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private int e(int i2) {
        return (int) ((i2 * getResources().getDisplayMetrics().density * (ZteFeature.isTabletProduct() ? 1.5f : 1.0f)) + 0.5d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
        layoutParams.width = e(i2 > i3 ? CONTENT_WIDTH_SCREEN_HORIZONTAL_DP : CONTENT_WIDTH_SCREEN_VERTICAL_DP);
        this.mContentView.setLayoutParams(layoutParams);
    }

    public int getMode() {
        return this.mSpinner.b();
    }

    public float getScreenExtractionAlpha() {
        return this.mExpandPanel.b();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Callback callback;
        int id = view.getId();
        if (id == R.id.cancel) {
            Callback callback2 = this.mCallback;
            if (callback2 != null) {
                callback2.e(false);
                return;
            }
            return;
        }
        if (id != R.id.ok || (callback = this.mCallback) == null) {
            return;
        }
        callback.e(true);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        View findViewById = findViewById(R.id.cancel);
        this.mCancelView = findViewById;
        findViewById.setOnClickListener(this);
        View findViewById2 = findViewById(R.id.ok);
        this.mOkView = findViewById2;
        findViewById2.setOnClickListener(this);
        this.mContentView = findViewById(R.id.settings_content);
        this.mSpinner = new Spinner();
        this.mExpandPanel = new ExpandPanel();
        ScreenExtractionData screenExtractionData = this.mScreenExtractionData;
        if (screenExtractionData != null) {
            setScreenExtractionData(screenExtractionData);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(final int i2, final int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        getHandler().post(new Runnable() { // from class: cn.nubia.plugin.screenextraction.view.a
            @Override // java.lang.Runnable
            public final void run() {
                SettingsLayoutView.this.f(i2, i3);
            }
        });
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setScreenExtractionData(ScreenExtractionData screenExtractionData) {
        this.mScreenExtractionData = screenExtractionData;
        Spinner spinner = this.mSpinner;
        if (spinner != null) {
            spinner.e();
        }
        ExpandPanel expandPanel = this.mExpandPanel;
        if (expandPanel != null) {
            expandPanel.e();
        }
    }

    public SettingsLayoutView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
