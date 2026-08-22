package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class ResourceCursorAdapter extends CursorAdapter {

    /* renamed from: p, reason: collision with root package name */
    private int f3578p;

    /* renamed from: q, reason: collision with root package name */
    private int f3579q;

    /* renamed from: r, reason: collision with root package name */
    private LayoutInflater f3580r;

    public ResourceCursorAdapter(Context context, int i2, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.f3579q = i2;
        this.f3578p = i2;
        this.f3580r = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public View c(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f3580r.inflate(this.f3579q, viewGroup, false);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f3580r.inflate(this.f3578p, viewGroup, false);
    }
}
