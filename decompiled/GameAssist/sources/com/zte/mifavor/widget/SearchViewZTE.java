package com.zte.mifavor.widget;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import androidx.annotation.Nullable;
import androidx.core.text.TextUtilsCompat;
import com.zte.extres.R;
import java.util.Locale;

/* loaded from: classes2.dex */
public class SearchViewZTE extends SearchView {
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
    private static final String TAG = "Z#SearchViewZTE";
    private Context mContext;
    private boolean mEditState;
    private boolean mIsSupportVoice;

    /* renamed from: com.zte.mifavor.widget.SearchViewZTE$1, reason: invalid class name */
    class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        private IntEvaluator f17747c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f17748h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f17749i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ int f17750j;

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ int f17751k;

        /* renamed from: l, reason: collision with root package name */
        final /* synthetic */ SearchViewZTE f17752l;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue() / 100.0f;
            int intValue2 = this.f17747c.evaluate(intValue, Integer.valueOf(this.f17748h), Integer.valueOf(this.f17749i)).intValue();
            int intValue3 = this.f17747c.evaluate(intValue, Integer.valueOf(this.f17750j), Integer.valueOf(this.f17751k)).intValue();
            this.f17752l.setLeft(intValue2);
            this.f17752l.setRight(intValue2 + intValue3);
            this.f17752l.getLayoutParams().width = intValue3;
            this.f17752l.requestLayout();
        }
    }

    public interface OnCloseListener extends SearchView.OnCloseListener {
    }

    public interface OnQueryTextListener extends SearchView.OnQueryTextListener {
    }

    public SearchViewZTE(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEditState = true;
        this.mIsSupportVoice = false;
        this.mContext = context;
        a(context);
    }

    private void a(Context context) {
        this.mIsSupportVoice = Utils.u();
        Log.d(TAG, "init View. mIsSupportVoice=" + this.mIsSupportVoice);
        LinearLayout linearLayout = (LinearLayout) findViewById(context.getResources().getIdentifier(SEARCH_EDIT_FRAME, "id", ANDROID));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1, 1.0f);
        layoutParams.gravity = 8388611;
        linearLayout.setLayoutParams(layoutParams);
        if (TextUtilsCompat.a(Locale.getDefault()) == 1) {
            ((LinearLayout) findViewById(context.getResources().getIdentifier(SEARCH_PLATE, "id", ANDROID))).setPaddingRelative((int) context.getResources().getDimension(R.dimen.mfvc_ic_txt_padding), linearLayout.getPaddingTop(), Utils.c(getContext(), 1), linearLayout.getPaddingBottom());
        }
        SearchView.SearchAutoComplete findViewById = findViewById(context.getResources().getIdentifier(SEARCH_SRC_TEXT, "id", ANDROID));
        findViewById.setPaddingRelative(Utils.b(getContext(), 0.25d), 0, Utils.b(getContext(), 0.25d), 0);
        findViewById.setGravity(8388627);
        findViewById.setTextAppearance(R.style.mfvc_appbar_search_normal_font);
        findViewById.setTextColor(context.getColor(R.color.mfv_common_acb_search_txt));
        boolean isIconfiedByDefault = isIconfiedByDefault();
        Log.d(TAG, "init View. isIconfied=" + isIconfiedByDefault);
        if (isIconfiedByDefault) {
            Drawable drawable = context.getDrawable(R.drawable.search_hint_x);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(drawable, 2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.setSpan(imageSpan, 1, 2, 33);
            spannableStringBuilder.append(getQueryHint());
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.mfv_common_acb_search_txt_watermark)), 3, spannableStringBuilder.length(), 33);
            findViewById.setHint(spannableStringBuilder);
        }
        ImageView imageView = (ImageView) findViewById(context.getResources().getIdentifier(SEARCH_CLOSE_BTN, "id", ANDROID));
        imageView.setPaddingRelative(Utils.c(getContext(), 3), Utils.c(getContext(), 3), Utils.c(getContext(), 3), Utils.c(getContext(), 3));
        imageView.setColorFilter(context.getColor(R.color.mfv_common_acb_search_clear));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(Utils.c(getContext(), CLOSE_BTN_WIDTH), Utils.c(getContext(), CLOSE_BTN_WIDTH));
        layoutParams2.gravity = 16;
        layoutParams2.setMarginEnd((int) context.getResources().getDimension(R.dimen.mfvc_small_padding));
        imageView.setLayoutParams(layoutParams2);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }

    public boolean getEditState() {
        return this.mEditState;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow out.");
    }

    @Override // android.widget.SearchView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow out.");
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        ImageView imageView;
        super.onFinishInflate();
        if (this.mIsSupportVoice && (imageView = (ImageView) findViewById(getResources().getIdentifier(SEARCH_CLOSE_BTN, "id", ANDROID))) != null) {
            imageView.setVisibility(8);
            Log.d(TAG, "onFinishInflate. set Visibility close_btn to GONE.");
        }
        Log.d(TAG, "onFinishInflate out. mIsSupportVoice=" + this.mIsSupportVoice);
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

    @Override // android.widget.SearchView
    public void setQueryHint(@Nullable CharSequence charSequence) {
        super.setQueryHint(charSequence);
        boolean isIconfiedByDefault = isIconfiedByDefault();
        Log.d(TAG, "set Query Hint in. isIconfied=" + isIconfiedByDefault);
        if (isIconfiedByDefault) {
            SearchView.SearchAutoComplete findViewById = findViewById(getContext().getResources().getIdentifier(SEARCH_SRC_TEXT, "id", ANDROID));
            Drawable drawable = getContext().getDrawable(R.drawable.search_hint_x);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(drawable, 2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            if (findViewById != null) {
                spannableStringBuilder.setSpan(imageSpan, 1, 2, 33);
                spannableStringBuilder.append(getQueryHint());
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.mfv_common_acb_search_txt_watermark)), 3, spannableStringBuilder.length(), 33);
                findViewById.setHint(spannableStringBuilder);
            }
        }
    }

    public void setSearchHintIconColor(int i2) {
    }

    public void setSearchVoiceText(String str) {
    }

    public SearchViewZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mEditState = true;
        this.mIsSupportVoice = false;
        this.mContext = context;
        a(context);
    }

    public SearchViewZTE(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mEditState = true;
        this.mIsSupportVoice = false;
        this.mContext = context;
        a(context);
    }
}
