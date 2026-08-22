package com.zte.mifavor.alphavideo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.zte.extres.R;
import com.zte.mifavor.widget.ActivityZTE;
import com.zte.mifavor.widget.Utils;

/* loaded from: classes2.dex */
public class OutScreenAlphaVideoActivity extends ActivityZTE {

    /* renamed from: j, reason: collision with root package name */
    protected SurfaceView f17083j = null;

    /* renamed from: k, reason: collision with root package name */
    protected GLSurfaceView f17084k = null;

    /* renamed from: l, reason: collision with root package name */
    protected FrameLayout f17085l = null;

    /* renamed from: m, reason: collision with root package name */
    protected boolean f17086m = false;

    /* renamed from: n, reason: collision with root package name */
    protected TextView f17087n = null;

    /* renamed from: o, reason: collision with root package name */
    protected TextView f17088o = null;

    /* renamed from: p, reason: collision with root package name */
    protected Button f17089p = null;

    private void b() {
        GLSurfaceView gLSurfaceView = new GLSurfaceView(this);
        this.f17084k = gLSurfaceView;
        gLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.f17084k.getHolder().setFormat(-3);
        this.f17084k.setZOrderOnTop(true);
        this.f17084k.setBackgroundColor(10066329);
        new AlphaVideoPlayer(this.f17084k, new IPlayerListener(this) { // from class: com.zte.mifavor.alphavideo.OutScreenAlphaVideoActivity.2
            @Override // com.zte.mifavor.alphavideo.IPlayerListener
            public void a() {
            }

            @Override // com.zte.mifavor.alphavideo.IPlayerListener
            public void b() {
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.mfvc_open_animation_view_width), getResources().getDimensionPixelSize(R.dimen.mfvc_open_animation_view_height));
        layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.mfvc_open_animation_view_topMargin);
        layoutParams.leftMargin = getResources().getDimensionPixelSize(R.dimen.mfvc_open_animation_view_leftMargin);
        this.f17085l.addView(this.f17084k, layoutParams);
    }

    @Override // com.zte.mifavor.widget.ActivityZTE, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alphavideo);
        this.f17085l = (FrameLayout) findViewById(R.id.alpha_video_layout);
        View findViewById = findViewById(R.id.effectView);
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("bitmap_data");
        if (byteArrayExtra != null && byteArrayExtra.length > 0) {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length);
            findViewById.setBackground(new BitmapDrawable(getResources(), decodeByteArray));
            this.f17086m = Utils.s(decodeByteArray);
        }
        Button button = (Button) findViewById(R.id.btnCancel);
        this.f17089p = button;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.alphavideo.OutScreenAlphaVideoActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OutScreenAlphaVideoActivity.this.finish();
                }
            });
        }
        findViewById.setRenderEffect(RenderEffect.createBlurEffect(100.0f, 100.0f, Shader.TileMode.CLAMP));
        b();
        this.f17087n = (TextView) findViewById(R.id.primaryTip);
        this.f17088o = (TextView) findViewById(R.id.secondaryTip);
        Log.d("AlphaVideoActivity", "onCreate out. mIsSimilarDark=" + this.f17086m);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        GLSurfaceView gLSurfaceView;
        SurfaceView surfaceView;
        super.onDestroy();
        FrameLayout frameLayout = this.f17085l;
        if (frameLayout != null && (surfaceView = this.f17083j) != null) {
            frameLayout.removeView(surfaceView);
        }
        FrameLayout frameLayout2 = this.f17085l;
        if (frameLayout2 == null || (gLSurfaceView = this.f17084k) == null) {
            return;
        }
        frameLayout2.removeView(gLSurfaceView);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // com.zte.mifavor.widget.ActivityZTE, android.app.Activity
    protected void onResume() {
        super.onResume();
        int color = getResources().getColor(R.color.mfv_common_primary_txt_color_alpha);
        int color2 = getResources().getColor(R.color.mfv_common_primary_txt_color_alpha_dark);
        if (this.f17089p != null) {
            int i2 = this.f17086m ? color2 : color;
            Log.d("AlphaVideoActivity", "onResume set button text color. mIsSimilarDark=" + this.f17086m + ", color=" + i2 + ", colorprimary=" + color + ", colorprimaryDark=" + color2);
            this.f17089p.setTextColor(i2);
            this.f17089p.setBackgroundResource(this.f17086m ? R.drawable.btn_default_bg_alpha_dark : R.drawable.btn_default_bg_alpha);
        }
        TextView textView = this.f17087n;
        if (textView != null) {
            if (this.f17086m) {
                color = color2;
            }
            textView.setTextColor(color);
        }
        TextView textView2 = this.f17088o;
        if (textView2 != null) {
            textView2.setTextColor(getResources().getColor(this.f17086m ? R.color.mfv_common_secondary_txt_color_alpha_dark : R.color.mfv_common_secondary_txt_color_alpha));
        }
    }
}
