package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.view.ActionProvider;

/* loaded from: classes.dex */
public class ShareActionProvider extends ActionProvider {

    /* renamed from: d, reason: collision with root package name */
    private int f973d;

    /* renamed from: e, reason: collision with root package name */
    private final ShareMenuItemOnMenuItemClickListener f974e;

    /* renamed from: f, reason: collision with root package name */
    final Context f975f;

    /* renamed from: g, reason: collision with root package name */
    String f976g;

    /* renamed from: h, reason: collision with root package name */
    OnShareTargetSelectedListener f977h;

    public interface OnShareTargetSelectedListener {
        boolean a(ShareActionProvider shareActionProvider, Intent intent);
    }

    private class ShareActivityChooserModelPolicy implements ActivityChooserModel.OnChooseActivityListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ShareActionProvider f978a;

        @Override // androidx.appcompat.widget.ActivityChooserModel.OnChooseActivityListener
        public boolean a(ActivityChooserModel activityChooserModel, Intent intent) {
            ShareActionProvider shareActionProvider = this.f978a;
            OnShareTargetSelectedListener onShareTargetSelectedListener = shareActionProvider.f977h;
            if (onShareTargetSelectedListener == null) {
                return false;
            }
            onShareTargetSelectedListener.a(shareActionProvider, intent);
            return false;
        }
    }

    private class ShareMenuItemOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ShareActionProvider f979a;

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            ShareActionProvider shareActionProvider = this.f979a;
            Intent b2 = ActivityChooserModel.d(shareActionProvider.f975f, shareActionProvider.f976g).b(menuItem.getItemId());
            if (b2 == null) {
                return true;
            }
            String action = b2.getAction();
            if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                this.f979a.l(b2);
            }
            this.f979a.f975f.startActivity(b2);
            return true;
        }
    }

    @Override // androidx.core.view.ActionProvider
    public boolean a() {
        return true;
    }

    @Override // androidx.core.view.ActionProvider
    public View c() {
        ActivityChooserView activityChooserView = new ActivityChooserView(this.f975f);
        if (!activityChooserView.isInEditMode()) {
            activityChooserView.setActivityChooserModel(ActivityChooserModel.d(this.f975f, this.f976g));
        }
        TypedValue typedValue = new TypedValue();
        this.f975f.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, typedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(AppCompatResources.b(this.f975f, typedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    @Override // androidx.core.view.ActionProvider
    public void f(SubMenu subMenu) {
        subMenu.clear();
        ActivityChooserModel d2 = ActivityChooserModel.d(this.f975f, this.f976g);
        PackageManager packageManager = this.f975f.getPackageManager();
        int f2 = d2.f();
        int min = Math.min(f2, this.f973d);
        for (int i2 = 0; i2 < min; i2++) {
            ResolveInfo e2 = d2.e(i2);
            subMenu.add(0, i2, i2, e2.loadLabel(packageManager)).setIcon(e2.loadIcon(packageManager)).setOnMenuItemClickListener(this.f974e);
        }
        if (min < f2) {
            SubMenu addSubMenu = subMenu.addSubMenu(0, min, min, this.f975f.getString(R.string.abc_activity_chooser_view_see_all));
            for (int i3 = 0; i3 < f2; i3++) {
                ResolveInfo e3 = d2.e(i3);
                addSubMenu.add(0, i3, i3, e3.loadLabel(packageManager)).setIcon(e3.loadIcon(packageManager)).setOnMenuItemClickListener(this.f974e);
            }
        }
    }

    void l(Intent intent) {
        intent.addFlags(134742016);
    }
}
