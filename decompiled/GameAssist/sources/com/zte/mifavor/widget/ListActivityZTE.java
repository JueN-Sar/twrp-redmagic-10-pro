package com.zte.mifavor.widget;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import androidx.annotation.Nullable;
import com.zte.mifavor.utils.UIUtils;

/* loaded from: classes2.dex */
public class ListActivityZTE extends ListActivity implements MfvActivity {

    /* renamed from: c, reason: collision with root package name */
    private ActivityCommon f17663c;

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCommon activityCommon = new ActivityCommon(this);
        this.f17663c = activityCommon;
        activityCommon.n();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        UIUtils.l(this, menu);
        return super.onPrepareOptionsMenu(menu);
    }
}
