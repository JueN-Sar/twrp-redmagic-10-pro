package com.zte.mifavor.widget;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.Menu;
import androidx.annotation.Nullable;
import com.zte.mifavor.utils.UIUtils;

@Deprecated
/* loaded from: classes2.dex */
public class TabActivityZTE extends TabActivity implements MfvActivity {

    /* renamed from: c, reason: collision with root package name */
    private ActivityCommon f17778c;

    @Override // android.app.ActivityGroup, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCommon activityCommon = new ActivityCommon(this);
        this.f17778c = activityCommon;
        activityCommon.n();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        UIUtils.l(this, menu);
        return super.onPrepareOptionsMenu(menu);
    }
}
