package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.NumberFormat;

/* loaded from: classes2.dex */
public class ProgressDialog extends AlertDialog {
    public static final int STYLE_HORIZONTAL = 1;
    public static final int STYLE_SPINNER = 0;
    private static final String TAG = "Z#DialogProgress";
    private boolean isSetProgressNumberFormat;
    private ColorProgressBarZTE mColorProgress;
    private boolean mHasStarted;
    private int mIncrementBy;
    private int mIncrementSecondaryBy;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private boolean mIsColorProgress;
    private int mMax;
    private CharSequence mMessage;
    private TextView mMessageView;
    private ProgressBar mProgress;
    private Drawable mProgressDrawable;
    private TextView mProgressNumber;
    private String mProgressNumberFormat;
    private TextView mProgressPercent;
    private NumberFormat mProgressPercentFormat;
    private int mProgressStyle;
    private int mProgressVal;
    private int mSecondaryProgressVal;
    private Handler mViewUpdateHandler;

    public ProgressDialog(Context context) {
        super(context);
        this.mIsColorProgress = false;
        this.mProgressStyle = 0;
        initFormats();
    }

    private void initFormats() {
        Log.d(TAG, "init Formats in.");
        this.mProgressNumberFormat = "%1d/%2d";
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        this.mProgressPercentFormat = percentInstance;
        percentInstance.setMaximumFractionDigits(0);
        this.isSetProgressNumberFormat = false;
        Log.d(TAG, "init Formats out.");
    }

    private void onProgressChanged() {
        Handler handler;
        if (this.mProgressStyle != 1 || (handler = this.mViewUpdateHandler) == null || handler.hasMessages(0)) {
            return;
        }
        this.mViewUpdateHandler.sendEmptyMessage(0);
    }

    private void setMeassageGravity() {
        TextView textView = (TextView) getWindow().findViewById(R.id.message);
        if (textView == null || !(textView instanceof DialogMessage)) {
            return;
        }
        ((DialogMessage) textView).setIsMultiplelines(true);
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2) {
        return show(context, charSequence, charSequence2, false);
    }

    public int getMax() {
        ColorProgressBarZTE colorProgressBarZTE;
        if (!this.mIsColorProgress || (colorProgressBarZTE = this.mColorProgress) == null) {
            ProgressBar progressBar = this.mProgress;
            if (progressBar != null) {
                return progressBar.getMax();
            }
        } else if (colorProgressBarZTE != null) {
            return Math.round(colorProgressBarZTE.getMax());
        }
        return this.mMax;
    }

    public int getProgress() {
        ColorProgressBarZTE colorProgressBarZTE;
        if (this.mIsColorProgress && (colorProgressBarZTE = this.mColorProgress) != null) {
            return colorProgressBarZTE.getProgress();
        }
        ProgressBar progressBar = this.mProgress;
        return progressBar != null ? progressBar.getProgress() : this.mProgressVal;
    }

    public int getSecondaryProgress() {
        ProgressBar progressBar = this.mProgress;
        return progressBar != null ? progressBar.getSecondaryProgress() : this.mSecondaryProgressVal;
    }

    public void incrementProgressBy(int i2) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mIncrementBy += i2;
        } else {
            progressBar.incrementProgressBy(i2);
            onProgressChanged();
        }
    }

    public void incrementSecondaryProgressBy(int i2) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mIncrementSecondaryBy += i2;
        } else {
            progressBar.incrementSecondaryProgressBy(i2);
            onProgressChanged();
        }
    }

    public boolean isIndeterminate() {
        ProgressBar progressBar = this.mProgress;
        return progressBar != null ? progressBar.isIndeterminate() : this.mIndeterminate;
    }

    @Override // com.zte.mifavor.widget.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        Log.d(TAG, "onCreate in.");
        LayoutInflater from = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, com.zte.extres.R.styleable.AlertDialogMfv, com.zte.extres.R.attr.alertDialogStyleMfv, 0);
        if (this.mProgressStyle == 1) {
            this.mViewUpdateHandler = new Handler() { // from class: com.zte.mifavor.widget.ProgressDialog.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    int i2;
                    int i3;
                    super.handleMessage(message);
                    if (ProgressDialog.this.mIsColorProgress && ProgressDialog.this.mColorProgress != null) {
                        i2 = ProgressDialog.this.mColorProgress.getProgress();
                        i3 = Math.round(ProgressDialog.this.mColorProgress.getMax());
                    } else if (ProgressDialog.this.mProgress != null) {
                        i2 = ProgressDialog.this.mProgress.getProgress();
                        i3 = ProgressDialog.this.mProgress.getMax();
                    } else {
                        i2 = 0;
                        i3 = 0;
                    }
                    if (ProgressDialog.this.mProgressNumberFormat == null || !ProgressDialog.this.isSetProgressNumberFormat) {
                        ProgressDialog.this.mProgressNumber.setText("");
                    } else {
                        String str = ProgressDialog.this.mProgressNumberFormat;
                        String format = String.format(str, Integer.valueOf(i2), Integer.valueOf(i3));
                        Log.d(ProgressDialog.TAG, "update progress. numerator=" + format + ",progress=" + i2 + ",max=" + i3 + ",format=" + str);
                        ProgressDialog.this.mProgressNumber.setText(format);
                    }
                    if (ProgressDialog.this.mProgressPercentFormat == null) {
                        ProgressDialog.this.mProgressPercent.setText("");
                        return;
                    }
                    SpannableString spannableString = new SpannableString(ProgressDialog.this.mProgressPercentFormat.format(i2 / i3));
                    spannableString.setSpan(new StyleSpan(0), 0, spannableString.length(), 33);
                    ProgressDialog.this.mProgressPercent.setText(spannableString);
                }
            };
            View inflate = from.inflate(obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_horizontalProgressLayout, com.zte.extres.R.layout.alert_dialog_progress), (ViewGroup) null);
            View findViewById = inflate.findViewById(R.id.progress);
            if (findViewById == null || !(findViewById instanceof ColorProgressBarZTE)) {
                this.mProgress = (ProgressBar) findViewById;
                this.mIsColorProgress = false;
            } else {
                this.mColorProgress = (ColorProgressBarZTE) findViewById;
                this.mIsColorProgress = true;
            }
            this.mProgressNumber = (TextView) inflate.findViewById(com.zte.extres.R.id.progress_number);
            this.mProgressPercent = (TextView) inflate.findViewById(com.zte.extres.R.id.progress_percent);
            setView(inflate);
        } else {
            View inflate2 = from.inflate(obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.AlertDialogMfv_progressLayout, com.zte.extres.R.layout.progress_dialog), (ViewGroup) null);
            this.mProgress = (ProgressBar) inflate2.findViewById(com.zte.extres.R.id.progress);
            this.mMessageView = (TextView) inflate2.findViewById(com.zte.extres.R.id.message);
            setView(inflate2);
        }
        obtainStyledAttributes.recycle();
        int i2 = this.mMax;
        if (i2 > 0) {
            setMax(i2);
        }
        int i3 = this.mProgressVal;
        if (i3 > 0) {
            setProgress(i3);
        }
        int i4 = this.mSecondaryProgressVal;
        if (i4 > 0) {
            setSecondaryProgress(i4);
        }
        int i5 = this.mIncrementBy;
        if (i5 > 0) {
            incrementProgressBy(i5);
        }
        int i6 = this.mIncrementSecondaryBy;
        if (i6 > 0) {
            incrementSecondaryProgressBy(i6);
        }
        Drawable drawable = this.mProgressDrawable;
        if (drawable != null) {
            setProgressDrawable(drawable);
        }
        Drawable drawable2 = this.mIndeterminateDrawable;
        if (drawable2 != null) {
            setIndeterminateDrawable(drawable2);
        }
        CharSequence charSequence = this.mMessage;
        if (charSequence != null) {
            setMessage(charSequence);
        }
        setIndeterminate(this.mIndeterminate);
        onProgressChanged();
        super.onCreate(bundle);
        android.widget.ScrollView scrollView = (android.widget.ScrollView) getWindow().findViewById(com.zte.extres.R.id.scrollView);
        scrollView.setPaddingRelative(scrollView.getPaddingStart(), 0, scrollView.getPaddingEnd(), 0);
        Log.d(TAG, "onCreate out.");
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        this.mHasStarted = true;
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        this.mHasStarted = false;
    }

    public void setIndeterminate(boolean z) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setIndeterminate(z);
        } else {
            this.mIndeterminate = z;
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setIndeterminateDrawable(drawable);
        } else {
            this.mIndeterminateDrawable = drawable;
        }
    }

    public void setMax(int i2) {
        if (this.mIsColorProgress) {
            ColorProgressBarZTE colorProgressBarZTE = this.mColorProgress;
            if (colorProgressBarZTE == null) {
                this.mMax = i2;
                return;
            } else {
                colorProgressBarZTE.setMax(i2);
                onProgressChanged();
                return;
            }
        }
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mMax = i2;
        } else {
            progressBar.setMax(i2);
            onProgressChanged();
        }
    }

    @Override // com.zte.mifavor.widget.AlertDialog
    public void setMessage(CharSequence charSequence) {
        Log.d(TAG, "set Message in. message=" + ((Object) charSequence) + ", mMessageView=" + this.mMessageView);
        if (this.mProgress == null && (!this.mIsColorProgress || this.mColorProgress == null)) {
            this.mMessage = charSequence;
        } else if (this.mProgressStyle == 1) {
            super.setMessage(charSequence);
        } else {
            this.mMessageView.setText(charSequence);
        }
    }

    public void setProgress(int i2) {
        ColorProgressBarZTE colorProgressBarZTE;
        Log.d(TAG, "set Progress in. value=" + i2);
        if (!this.mHasStarted) {
            this.mProgressVal = i2;
            return;
        }
        if (!this.mIsColorProgress || (colorProgressBarZTE = this.mColorProgress) == null) {
            ProgressBar progressBar = this.mProgress;
            if (progressBar != null) {
                progressBar.setProgress(i2);
            }
        } else {
            colorProgressBarZTE.setProgress(i2);
        }
        onProgressChanged();
    }

    public void setProgressDrawable(Drawable drawable) {
        ProgressBar progressBar = this.mProgress;
        if (progressBar != null) {
            progressBar.setProgressDrawable(drawable);
        } else {
            this.mProgressDrawable = drawable;
        }
    }

    public void setProgressNumberFormat(String str) {
        this.mProgressNumberFormat = str;
        this.isSetProgressNumberFormat = true;
        onProgressChanged();
    }

    public void setProgressPercentFormat(NumberFormat numberFormat) {
        this.mProgressPercentFormat = numberFormat;
        onProgressChanged();
    }

    public void setProgressStyle(int i2) {
        this.mProgressStyle = i2;
    }

    public void setSecondaryProgress(int i2) {
        if (1 == i2) {
            i2 = 2;
        }
        ProgressBar progressBar = this.mProgress;
        if (progressBar == null) {
            this.mSecondaryProgressVal = i2;
        } else {
            progressBar.setSecondaryProgress(i2);
            onProgressChanged();
        }
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return show(context, charSequence, charSequence2, z, false, null);
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        return show(context, charSequence, charSequence2, z, z2, null);
    }

    public static ProgressDialog show(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2, DialogInterface.OnCancelListener onCancelListener) {
        Log.d(TAG, "Progress Dialog show. ");
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(charSequence);
        progressDialog.setMessage(charSequence2);
        progressDialog.setIndeterminate(z);
        progressDialog.setCancelable(z2);
        progressDialog.setOnCancelListener(onCancelListener);
        progressDialog.closeSyncGravity();
        progressDialog.show();
        return progressDialog;
    }

    public ProgressDialog(Context context, int i2) {
        super(context, i2);
        this.mIsColorProgress = false;
        this.mProgressStyle = 0;
        initFormats();
    }

    @Override // com.zte.mifavor.widget.AlertDialog, android.app.Dialog
    public void show() {
        closeSyncGravity();
        super.show();
        setMeassageGravity();
        Log.d(TAG, "show out.");
    }
}
