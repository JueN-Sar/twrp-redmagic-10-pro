package com.facebook.rebound.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import com.facebook.rebound.OrigamiValueConverter;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringConfigRegistry;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SpringConfiguratorView extends FrameLayout {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
    private static final float MAX_FRICTION = 50.0f;
    private static final int MAX_SEEKBAR_VAL = 100000;
    private static final float MAX_TENSION = 200.0f;
    private static final float MIN_FRICTION = 0.0f;
    private static final float MIN_TENSION = 0.0f;
    private TextView mFrictionLabel;
    private SeekBar mFrictionSeekBar;
    private final float mRevealPx;
    private final Spring mRevealerSpring;
    private SpringConfig mSelectedSpringConfig;
    private final List<SpringConfig> mSpringConfigs;
    private Spinner mSpringSelectorSpinner;
    private final float mStashPx;
    private TextView mTensionLabel;
    private SeekBar mTensionSeekBar;
    private final int mTextColor;
    private final SpinnerAdapter spinnerAdapter;
    private final SpringConfigRegistry springConfigRegistry;

    private class OnNubTouchListener implements View.OnTouchListener {
        private OnNubTouchListener() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return true;
            }
            SpringConfiguratorView.this.p();
            return true;
        }
    }

    private class RevealerSpringListener implements SpringListener {
        private RevealerSpringListener() {
        }

        @Override // com.facebook.rebound.SpringListener
        public void a(Spring spring) {
            float d2 = (float) spring.d();
            float f2 = SpringConfiguratorView.this.mRevealPx;
            SpringConfiguratorView.this.setTranslationY((d2 * (SpringConfiguratorView.this.mStashPx - f2)) + f2);
        }

        @Override // com.facebook.rebound.SpringListener
        public void b(Spring spring) {
        }

        @Override // com.facebook.rebound.SpringListener
        public void c(Spring spring) {
        }

        @Override // com.facebook.rebound.SpringListener
        public void d(Spring spring) {
        }
    }

    private class SeekbarListener implements SeekBar.OnSeekBarChangeListener {
        private SeekbarListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (seekBar == SpringConfiguratorView.this.mTensionSeekBar) {
                double d2 = ((i2 * SpringConfiguratorView.MAX_TENSION) / 100000.0f) + 0.0f;
                SpringConfiguratorView.this.mSelectedSpringConfig.f10037b = OrigamiValueConverter.d(d2);
                String format = SpringConfiguratorView.DECIMAL_FORMAT.format(d2);
                SpringConfiguratorView.this.mTensionLabel.setText("T:" + format);
            }
            if (seekBar == SpringConfiguratorView.this.mFrictionSeekBar) {
                double d3 = ((i2 * SpringConfiguratorView.MAX_FRICTION) / 100000.0f) + 0.0f;
                SpringConfiguratorView.this.mSelectedSpringConfig.f10036a = OrigamiValueConverter.a(d3);
                String format2 = SpringConfiguratorView.DECIMAL_FORMAT.format(d3);
                SpringConfiguratorView.this.mFrictionLabel.setText("F:" + format2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    private class SpinnerAdapter extends BaseAdapter {

        /* renamed from: c, reason: collision with root package name */
        private final Context f10048c;

        /* renamed from: h, reason: collision with root package name */
        private final List f10049h = new ArrayList();

        public SpinnerAdapter(Context context) {
            this.f10048c = context;
        }

        public void a(String str) {
            this.f10049h.add(str);
            notifyDataSetChanged();
        }

        public void b() {
            this.f10049h.clear();
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f10049h.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return this.f10049h.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                textView = new TextView(this.f10048c);
                textView.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
                int d2 = Util.d(12.0f, SpringConfiguratorView.this.getResources());
                textView.setPadding(d2, d2, d2, d2);
                textView.setTextColor(SpringConfiguratorView.this.mTextColor);
            } else {
                textView = (TextView) view;
            }
            textView.setText((CharSequence) this.f10049h.get(i2));
            return textView;
        }
    }

    private class SpringSelectedListener implements AdapterView.OnItemSelectedListener {
        private SpringSelectedListener() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView adapterView, View view, int i2, long j2) {
            SpringConfiguratorView springConfiguratorView = SpringConfiguratorView.this;
            springConfiguratorView.mSelectedSpringConfig = (SpringConfig) springConfiguratorView.mSpringConfigs.get(i2);
            SpringConfiguratorView springConfiguratorView2 = SpringConfiguratorView.this;
            springConfiguratorView2.q(springConfiguratorView2.mSelectedSpringConfig);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    public SpringConfiguratorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private View n(Context context) {
        Resources resources = getResources();
        int d2 = Util.d(5.0f, resources);
        int d3 = Util.d(10.0f, resources);
        int d4 = Util.d(20.0f, resources);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.setMargins(0, 0, d2, 0);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(Util.a(-1, Util.d(300.0f, resources)));
        FrameLayout frameLayout2 = new FrameLayout(context);
        FrameLayout.LayoutParams b2 = Util.b();
        b2.setMargins(0, d4, 0, 0);
        frameLayout2.setLayoutParams(b2);
        frameLayout2.setBackgroundColor(Color.argb(100, 0, 0, 0));
        frameLayout.addView(frameLayout2);
        this.mSpringSelectorSpinner = new Spinner(context, 0);
        FrameLayout.LayoutParams c2 = Util.c();
        c2.gravity = 48;
        c2.setMargins(d3, d3, d3, 0);
        this.mSpringSelectorSpinner.setLayoutParams(c2);
        frameLayout2.addView(this.mSpringSelectorSpinner);
        LinearLayout linearLayout = new LinearLayout(context);
        FrameLayout.LayoutParams c3 = Util.c();
        c3.setMargins(0, 0, 0, Util.d(80.0f, resources));
        c3.gravity = 80;
        linearLayout.setLayoutParams(c3);
        linearLayout.setOrientation(1);
        frameLayout2.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context);
        FrameLayout.LayoutParams c4 = Util.c();
        c4.setMargins(d3, d3, d3, d4);
        linearLayout2.setPadding(d3, d3, d3, d3);
        linearLayout2.setLayoutParams(c4);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        SeekBar seekBar = new SeekBar(context);
        this.mTensionSeekBar = seekBar;
        seekBar.setLayoutParams(layoutParams);
        linearLayout2.addView(this.mTensionSeekBar);
        TextView textView = new TextView(getContext());
        this.mTensionLabel = textView;
        textView.setTextColor(this.mTextColor);
        FrameLayout.LayoutParams a2 = Util.a(Util.d(MAX_FRICTION, resources), -1);
        this.mTensionLabel.setGravity(19);
        this.mTensionLabel.setLayoutParams(a2);
        this.mTensionLabel.setMaxLines(1);
        linearLayout2.addView(this.mTensionLabel);
        LinearLayout linearLayout3 = new LinearLayout(context);
        FrameLayout.LayoutParams c5 = Util.c();
        c5.setMargins(d3, d3, d3, d4);
        linearLayout3.setPadding(d3, d3, d3, d3);
        linearLayout3.setLayoutParams(c5);
        linearLayout3.setOrientation(0);
        linearLayout.addView(linearLayout3);
        SeekBar seekBar2 = new SeekBar(context);
        this.mFrictionSeekBar = seekBar2;
        seekBar2.setLayoutParams(layoutParams);
        linearLayout3.addView(this.mFrictionSeekBar);
        TextView textView2 = new TextView(getContext());
        this.mFrictionLabel = textView2;
        textView2.setTextColor(this.mTextColor);
        FrameLayout.LayoutParams a3 = Util.a(Util.d(MAX_FRICTION, resources), -1);
        this.mFrictionLabel.setGravity(19);
        this.mFrictionLabel.setLayoutParams(a3);
        this.mFrictionLabel.setMaxLines(1);
        linearLayout3.addView(this.mFrictionLabel);
        View view = new View(context);
        FrameLayout.LayoutParams a4 = Util.a(Util.d(60.0f, resources), Util.d(40.0f, resources));
        a4.gravity = 49;
        view.setLayoutParams(a4);
        view.setOnTouchListener(new OnNubTouchListener());
        view.setBackgroundColor(Color.argb(255, 0, 164, 209));
        frameLayout.addView(view);
        return frameLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.mRevealerSpring.n(this.mRevealerSpring.f() == 1.0d ? 0.0d : 1.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(SpringConfig springConfig) {
        int round = Math.round(((((float) OrigamiValueConverter.c(springConfig.f10037b)) - 0.0f) * 100000.0f) / MAX_TENSION);
        int round2 = Math.round(((((float) OrigamiValueConverter.b(springConfig.f10036a)) - 0.0f) * 100000.0f) / MAX_FRICTION);
        this.mTensionSeekBar.setProgress(round);
        this.mFrictionSeekBar.setProgress(round2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void o() {
        Map b2 = this.springConfigRegistry.b();
        this.spinnerAdapter.b();
        this.mSpringConfigs.clear();
        for (Map.Entry entry : b2.entrySet()) {
            if (entry.getKey() != SpringConfig.f10035c) {
                this.mSpringConfigs.add(entry.getKey());
                this.spinnerAdapter.a((String) entry.getValue());
            }
        }
        this.mSpringConfigs.add(SpringConfig.f10035c);
        this.spinnerAdapter.a((String) b2.get(SpringConfig.f10035c));
        this.spinnerAdapter.notifyDataSetChanged();
        if (this.mSpringConfigs.size() > 0) {
            this.mSpringSelectorSpinner.setSelection(0);
        }
    }

    @TargetApi(11)
    public SpringConfiguratorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSpringConfigs = new ArrayList();
        this.mTextColor = Color.argb(255, 225, 225, 225);
        SpringSystem h2 = SpringSystem.h();
        this.springConfigRegistry = SpringConfigRegistry.c();
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(context);
        this.spinnerAdapter = spinnerAdapter;
        Resources resources = getResources();
        this.mRevealPx = Util.d(40.0f, resources);
        float d2 = Util.d(280.0f, resources);
        this.mStashPx = d2;
        Spring c2 = h2.c();
        this.mRevealerSpring = c2;
        c2.l(1.0d).n(1.0d).a(new RevealerSpringListener());
        addView(n(context));
        SeekbarListener seekbarListener = new SeekbarListener();
        this.mTensionSeekBar.setMax(MAX_SEEKBAR_VAL);
        this.mTensionSeekBar.setOnSeekBarChangeListener(seekbarListener);
        this.mFrictionSeekBar.setMax(MAX_SEEKBAR_VAL);
        this.mFrictionSeekBar.setOnSeekBarChangeListener(seekbarListener);
        this.mSpringSelectorSpinner.setAdapter((android.widget.SpinnerAdapter) spinnerAdapter);
        this.mSpringSelectorSpinner.setOnItemSelectedListener(new SpringSelectedListener());
        o();
        setTranslationY(d2);
    }
}
