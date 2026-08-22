package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuView;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public class MenuAdapter extends BaseAdapter {

    /* renamed from: c, reason: collision with root package name */
    MenuBuilder f547c;

    /* renamed from: h, reason: collision with root package name */
    private int f548h = -1;

    /* renamed from: i, reason: collision with root package name */
    private boolean f549i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f550j;

    /* renamed from: k, reason: collision with root package name */
    private final LayoutInflater f551k;

    /* renamed from: l, reason: collision with root package name */
    private final int f552l;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i2) {
        this.f550j = z;
        this.f551k = layoutInflater;
        this.f547c = menuBuilder;
        this.f552l = i2;
        a();
    }

    void a() {
        MenuItemImpl x = this.f547c.x();
        if (x != null) {
            ArrayList B = this.f547c.B();
            int size = B.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((MenuItemImpl) B.get(i2)) == x) {
                    this.f548h = i2;
                    return;
                }
            }
        }
        this.f548h = -1;
    }

    public MenuBuilder b() {
        return this.f547c;
    }

    @Override // android.widget.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public MenuItemImpl getItem(int i2) {
        ArrayList B = this.f550j ? this.f547c.B() : this.f547c.G();
        int i3 = this.f548h;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return (MenuItemImpl) B.get(i2);
    }

    public void d(boolean z) {
        this.f549i = z;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f548h < 0 ? (this.f550j ? this.f547c.B() : this.f547c.G()).size() : r0.size() - 1;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f551k.inflate(this.f552l, viewGroup, false);
        }
        int groupId = getItem(i2).getGroupId();
        int i3 = i2 - 1;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        listMenuItemView.setGroupDividerEnabled(this.f547c.I() && groupId != (i3 >= 0 ? getItem(i3).getGroupId() : groupId));
        MenuView.ItemView itemView = (MenuView.ItemView) view;
        if (this.f549i) {
            listMenuItemView.setForceShowIcon(true);
        }
        itemView.c(getItem(i2), 0);
        return view;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
