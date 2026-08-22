package cn.nubia.multisubscreen.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import cn.nubia.gameassist.R;
import cn.nubia.multisubscreen.secondary.SlideViewCtrl;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class SlideView extends RelativeLayout {
    private static final String TAG = "MultiSubScreen_SecData";
    private int[] mModeIds;
    private TextView mModeView;
    private SlideBar mSeekBar;
    private TextView mTitleView;

    public SlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.title);
        this.mTitleView = textView;
        textView.setSelected(true);
        TextView textView2 = (TextView) findViewById(R.id.mode);
        this.mModeView = textView2;
        textView2.setSelected(true);
        this.mSeekBar = (SlideBar) findViewById(R.id.seekbar);
    }

    public void setMax(int i2) {
        this.mSeekBar.setMax(i2);
    }

    public void setMin(int i2) {
        this.mSeekBar.setMin(i2);
    }

    public void setModeIds(int[] iArr) {
        if (iArr == null) {
            this.mModeView.setVisibility(8);
            this.mSeekBar.e(false);
        } else {
            this.mModeView.setVisibility(0);
            this.mSeekBar.e(true);
        }
        this.mModeIds = iArr;
    }

    public void setModeText(int i2) {
        int[] iArr = this.mModeIds;
        if (iArr == null) {
            return;
        }
        if (iArr.length > i2) {
            this.mModeView.setText(iArr[i2]);
            return;
        }
        this.mModeView.setText("");
        GaLog.e(TAG, "slide view index=" + i2 + ", length=" + this.mModeIds.length + ",text" + ((Object) this.mTitleView.getText()));
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    public void setProgress(int i2) {
        this.mSeekBar.setProgress(i2);
        setModeText(i2);
    }

    public void setSlide(SlideViewCtrl.Slider slider) {
        setTitle(slider.i());
        setModeIds(slider.e());
        setMax(slider.b());
        setMin(slider.c());
        setProgress(slider.g());
        if (slider.l()) {
            this.mTitleView.setAlpha(1.0f);
            this.mModeView.setAlpha(1.0f);
            this.mSeekBar.setAlpha(1.0f);
            this.mSeekBar.setEnabled(true);
            return;
        }
        this.mTitleView.setAlpha(0.4f);
        this.mModeView.setAlpha(0.4f);
        this.mSeekBar.setAlpha(0.4f);
        this.mSeekBar.setEnabled(false);
    }

    public void setTitle(int i2) {
        this.mTitleView.setText(i2);
    }

    public SlideView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public SlideView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
