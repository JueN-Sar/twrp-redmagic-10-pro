package com.zte.mifavor.preference;

import android.R;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.zte.mifavor.utils.UIUtils;
import com.zte.mifavor.widget.ActivityCommon;
import com.zte.mifavor.widget.MfvActivity;

/* loaded from: classes2.dex */
public abstract class PreferenceActivity extends android.preference.PreferenceActivity implements MfvActivity {

    /* renamed from: c, reason: collision with root package name */
    private ActivityCommon f17329c;

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCommon activityCommon = new ActivityCommon(this);
        this.f17329c = activityCommon;
        activityCommon.n();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        UIUtils.l(this, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public void setContentView(int i2) {
        ListView listView;
        super.setContentView(i2);
        int identifier = getResources().getIdentifier("preference_list_content_material", "layout", "android");
        int identifier2 = getResources().getIdentifier("preference_list_content_single", "layout", "android");
        if ((identifier != i2 && identifier2 != i2) || (listView = (ListView) findViewById(R.id.list)) == null || (listView instanceof com.zte.mifavor.widget.ListView)) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) listView.getParent();
        if (viewGroup != null) {
            int indexOfChild = viewGroup.indexOfChild(listView);
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService("layout_inflater");
            View view = null;
            if (identifier == i2) {
                view = layoutInflater.inflate(com.zte.extres.R.layout.preference_list_content_material_listview, (ViewGroup) null);
            } else if (identifier2 == i2) {
                view = layoutInflater.inflate(com.zte.extres.R.layout.preference_list_content_single_listview, (ViewGroup) null);
            }
            if (view == null || indexOfChild == -1 || layoutParams == null) {
                return;
            }
            viewGroup.removeView(listView);
            viewGroup.addView(view, indexOfChild, layoutParams);
            onContentChanged();
        }
    }

    @Override // android.preference.PreferenceActivity
    public void setPreferenceScreen(PreferenceScreen preferenceScreen) {
        super.setPreferenceScreen(preferenceScreen);
    }
}
