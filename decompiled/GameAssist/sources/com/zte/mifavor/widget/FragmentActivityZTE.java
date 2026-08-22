package com.zte.mifavor.widget;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.zte.mifavor.utils.UIUtils;

/* loaded from: classes2.dex */
public class FragmentActivityZTE extends FragmentActivity implements MfvActivity {
    private ActivityCommon F;
    private boolean G = true;

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
        UIUtils.a(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCommon activityCommon = new ActivityCommon(this);
        this.F = activityCommon;
        activityCommon.n();
        boolean o2 = Utils.o(this);
        try {
            if (o2) {
                Utils.z(getWindow());
                getWindow().setNavigationBarContrastEnforced(false);
            } else {
                getWindow().setNavigationBarContrastEnforced(true);
            }
        } catch (Exception e2) {
            Log.e("FragmentActivityZTE", "set Navigation Bar Contrast Enforced.e=", e2);
        }
        Log.d("FragmentActivityZTE", "onCreate out. indicatorMode=" + o2);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.F.o();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        UIUtils.l(this, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.G) {
            this.F.p();
        }
    }
}
