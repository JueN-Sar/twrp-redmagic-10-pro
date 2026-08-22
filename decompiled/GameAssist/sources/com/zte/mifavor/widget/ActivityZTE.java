package com.zte.mifavor.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.zte.mifavor.utils.UIUtils;

/* loaded from: classes2.dex */
public class ActivityZTE extends Activity implements MfvActivity {

    /* renamed from: c, reason: collision with root package name */
    private ActivityCommon f17526c;

    /* renamed from: h, reason: collision with root package name */
    private boolean f17527h = false;

    /* renamed from: i, reason: collision with root package name */
    private boolean f17528i = true;

    /* renamed from: com.zte.mifavor.widget.ActivityZTE$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ActivityZTE f17529c;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f17529c.onBackPressed();
        }
    }

    protected void a(String str) {
        ActivityCommon activityCommon = this.f17526c;
        if (activityCommon == null || !this.f17527h) {
            super.setTitle(str);
            return;
        }
        TextView i2 = activityCommon.i();
        if (i2 != null) {
            i2.setText(str);
            return;
        }
        this.f17526c.g();
        TextView i3 = this.f17526c.i();
        if (i3 != null) {
            i3.setText(str);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
        UIUtils.a(this);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (this.f17527h) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f17527h = UIUtils.j(this);
        ActivityCommon activityCommon = new ActivityCommon(this);
        this.f17526c = activityCommon;
        activityCommon.n();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        UIUtils.l(this, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.f17528i) {
            this.f17526c.p();
        }
    }
}
