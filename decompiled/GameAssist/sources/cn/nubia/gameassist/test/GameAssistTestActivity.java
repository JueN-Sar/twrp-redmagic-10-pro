package cn.nubia.gameassist.test;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import cn.nubia.gameassist.R;

/* loaded from: classes.dex */
public class GameAssistTestActivity extends Activity {

    /* renamed from: c, reason: collision with root package name */
    public static boolean f7433c = false;

    @VisibleForTesting
    public static boolean mResume = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.game_assist_activity);
        getWindow().addFlags(128);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        mResume = false;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        mResume = false;
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        mResume = true;
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        mResume = false;
    }
}
