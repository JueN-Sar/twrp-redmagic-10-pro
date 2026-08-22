package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.SubMenuBuilder;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.internal.ParcelableSparseArray;

@RestrictTo
/* loaded from: classes.dex */
public class NavigationBarPresenter implements MenuPresenter {

    /* renamed from: c, reason: collision with root package name */
    private MenuBuilder f14847c;

    /* renamed from: h, reason: collision with root package name */
    private NavigationBarMenuView f14848h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f14849i = false;

    /* renamed from: j, reason: collision with root package name */
    private int f14850j;

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.material.navigation.NavigationBarPresenter.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        int f14851c;

        /* renamed from: h, reason: collision with root package name */
        ParcelableSparseArray f14852h;

        SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.f14851c);
            parcel.writeParcelable(this.f14852h, 0);
        }

        SavedState(Parcel parcel) {
            this.f14851c = parcel.readInt();
            this.f14852h = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void a(MenuBuilder menuBuilder, boolean z) {
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean d(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean e(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void f(Context context, MenuBuilder menuBuilder) {
        this.f14847c = menuBuilder;
        this.f14848h.a(menuBuilder);
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public void g(int i2) {
        this.f14850j = i2;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.f14850j;
    }

    public void h(NavigationBarMenuView navigationBarMenuView) {
        this.f14848h = navigationBarMenuView;
    }

    public void i(boolean z) {
        this.f14849i = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.f14848h.l(savedState.f14851c);
            this.f14848h.k(BadgeUtils.d(this.f14848h.getContext(), savedState.f14852h));
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.f14851c = this.f14848h.getSelectedItemId();
        savedState.f14852h = BadgeUtils.e(this.f14848h.getBadgeDrawables());
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        if (this.f14849i) {
            return;
        }
        if (z) {
            this.f14848h.d();
        } else {
            this.f14848h.m();
        }
    }
}
