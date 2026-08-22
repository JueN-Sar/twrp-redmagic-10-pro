package cn.nubia.nbgame.sdk.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import cn.nubia.nbgame.sdk.util.NeoLog;

/* loaded from: classes.dex */
public class ShowActivity extends Activity {

    /* renamed from: h, reason: collision with root package name */
    public static ShowActivity f8283h;

    /* renamed from: c, reason: collision with root package name */
    boolean f8284c = false;

    private void a() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        BasicDialog basicDialog = new BasicDialog();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", getIntent() != null ? getIntent().getStringExtra("msg") : "正在检测新版本");
        basicDialog.setArguments(bundle);
        basicDialog.show(beginTransaction, "dialog");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f8283h = this;
        this.f8284c = true;
        NeoLog.g("ShowActivity", "ShowActivity onCreate");
        a();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.f8284c) {
            finish();
            this.f8284c = false;
        }
    }
}
