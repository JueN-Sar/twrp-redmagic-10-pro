package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuPresenter;

/* loaded from: classes.dex */
class MenuDialogHelper implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, MenuPresenter.Callback {

    /* renamed from: c, reason: collision with root package name */
    private MenuBuilder f572c;

    /* renamed from: h, reason: collision with root package name */
    private AlertDialog f573h;

    /* renamed from: i, reason: collision with root package name */
    ListMenuPresenter f574i;

    /* renamed from: j, reason: collision with root package name */
    private MenuPresenter.Callback f575j;

    public MenuDialogHelper(MenuBuilder menuBuilder) {
        this.f572c = menuBuilder;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public void a(MenuBuilder menuBuilder, boolean z) {
        if (z || menuBuilder == this.f572c) {
            c();
        }
        MenuPresenter.Callback callback = this.f575j;
        if (callback != null) {
            callback.a(menuBuilder, z);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
    public boolean b(MenuBuilder menuBuilder) {
        MenuPresenter.Callback callback = this.f575j;
        if (callback != null) {
            return callback.b(menuBuilder);
        }
        return false;
    }

    public void c() {
        AlertDialog alertDialog = this.f573h;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public void d(IBinder iBinder) {
        MenuBuilder menuBuilder = this.f572c;
        AlertDialog.Builder builder = new AlertDialog.Builder(menuBuilder.w());
        ListMenuPresenter listMenuPresenter = new ListMenuPresenter(builder.b(), R.layout.abc_list_menu_item_layout);
        this.f574i = listMenuPresenter;
        listMenuPresenter.c(this);
        this.f572c.b(this.f574i);
        builder.c(this.f574i.g(), this);
        View A = menuBuilder.A();
        if (A != null) {
            builder.d(A);
        } else {
            builder.e(menuBuilder.y()).m(menuBuilder.z());
        }
        builder.i(this);
        AlertDialog a2 = builder.a();
        this.f573h = a2;
        a2.setOnDismissListener(this);
        WindowManager.LayoutParams attributes = this.f573h.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f573h.show();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        this.f572c.O((MenuItemImpl) this.f574i.g().getItem(i2), 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f574i.a(this.f572c, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i2 == 82 || i2 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f573h.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f573h.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.f572c.e(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.f572c.performShortcut(i2, keyEvent, 0);
    }
}
