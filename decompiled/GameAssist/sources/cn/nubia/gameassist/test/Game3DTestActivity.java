package cn.nubia.gameassist.test;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class Game3DTestActivity extends Activity {

    /* renamed from: c, reason: collision with root package name */
    private Context f7430c;

    /* renamed from: h, reason: collision with root package name */
    private Handler f7431h = new Handler(Looper.getMainLooper());

    /* renamed from: i, reason: collision with root package name */
    private FrameLayout f7432i;

    /* renamed from: cn.nubia.gameassist.test.Game3DTestActivity$1, reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.game_3d_test_activity);
        this.f7432i = (FrameLayout) findViewById(R.id.root_view);
        this.f7430c = this;
    }
}
