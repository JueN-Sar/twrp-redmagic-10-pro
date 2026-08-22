package cn.nubia.chatassistant.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class RecorderSoundLayout extends LinearLayout {
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    public RecorderSoundLayout(Context context) {
        super(context);
        init(context);
    }

    public RecorderSoundLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public RecorderSoundLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.recorder_sound_layout, this);
        this.img1 = (ImageView) findViewById(R.id.img_1);
        this.img2 = (ImageView) findViewById(R.id.img_2);
        this.img3 = (ImageView) findViewById(R.id.img_3);
        this.img4 = (ImageView) findViewById(R.id.img_4);
    }

    public void setSoundValue(float f) {
        if (f < 10.0f) {
            this.img4.setBackgroundResource(R.drawable.recorder_line);
            this.img3.setBackgroundResource(R.drawable.recorder_line);
            this.img2.setBackgroundResource(R.drawable.recorder_line);
            this.img1.setBackgroundResource(R.drawable.recorder_line);
            return;
        }
        if (f < 20.0f) {
            this.img4.setBackgroundResource(R.drawable.recorder_line_red);
            this.img3.setBackgroundResource(R.drawable.recorder_line);
            this.img2.setBackgroundResource(R.drawable.recorder_line);
            this.img1.setBackgroundResource(R.drawable.recorder_line);
            return;
        }
        if (f < 30.0f) {
            this.img4.setBackgroundResource(R.drawable.recorder_line_red);
            this.img3.setBackgroundResource(R.drawable.recorder_line_h);
            this.img2.setBackgroundResource(R.drawable.recorder_line);
            this.img1.setBackgroundResource(R.drawable.recorder_line);
            return;
        }
        if (f < 40.0f) {
            this.img4.setBackgroundResource(R.drawable.recorder_line_red);
            this.img3.setBackgroundResource(R.drawable.recorder_line_h);
            this.img2.setBackgroundResource(R.drawable.recorder_line_h);
            this.img1.setBackgroundResource(R.drawable.recorder_line);
            return;
        }
        this.img4.setBackgroundResource(R.drawable.recorder_line_red);
        this.img3.setBackgroundResource(R.drawable.recorder_line_h);
        this.img2.setBackgroundResource(R.drawable.recorder_line_h);
        this.img1.setBackgroundResource(R.drawable.recorder_line_h);
    }
}
