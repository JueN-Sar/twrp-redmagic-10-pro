package androidx.appcompat.widget;

import android.view.MenuItem;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.ShowableListMenu;

/* loaded from: classes.dex */
public class PopupMenu {

    /* renamed from: a, reason: collision with root package name */
    final MenuPopupHelper f918a;

    /* renamed from: b, reason: collision with root package name */
    OnMenuItemClickListener f919b;

    /* renamed from: c, reason: collision with root package name */
    OnDismissListener f920c;

    /* renamed from: androidx.appcompat.widget.PopupMenu$1, reason: invalid class name */
    class AnonymousClass1 implements MenuBuilder.Callback {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ PopupMenu f921c;

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            OnMenuItemClickListener onMenuItemClickListener = this.f921c.f919b;
            if (onMenuItemClickListener != null) {
                return onMenuItemClickListener.onMenuItemClick(menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void b(MenuBuilder menuBuilder) {
        }
    }

    /* renamed from: androidx.appcompat.widget.PopupMenu$2, reason: invalid class name */
    class AnonymousClass2 implements PopupWindow.OnDismissListener {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ PopupMenu f922c;

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            PopupMenu popupMenu = this.f922c;
            OnDismissListener onDismissListener = popupMenu.f920c;
            if (onDismissListener != null) {
                onDismissListener.a(popupMenu);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.PopupMenu$3, reason: invalid class name */
    class AnonymousClass3 extends ForwardingListener {

        /* renamed from: p, reason: collision with root package name */
        final /* synthetic */ PopupMenu f923p;

        @Override // androidx.appcompat.widget.ForwardingListener
        public ShowableListMenu b() {
            return this.f923p.f918a.c();
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        protected boolean c() {
            this.f923p.b();
            return true;
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        protected boolean d() {
            this.f923p.a();
            return true;
        }
    }

    public interface OnDismissListener {
        void a(PopupMenu popupMenu);
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public void a() {
        this.f918a.b();
    }

    public void b() {
        this.f918a.k();
    }
}
