package com.zte.gameassist.lowsugar.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.lowsugar.R;

/* loaded from: classes2.dex */
public class LowSugarListAdapter extends CursorAdapter {

    /* renamed from: c, reason: collision with root package name */
    private OnDataChangedListener f16959c;

    public interface OnDataChangedListener {
        void a();
    }

    public LowSugarListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    public void a(OnDataChangedListener onDataChangedListener) {
        this.f16959c = onDataChangedListener;
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        ((LowSugarItem) view).e(cursor.getLong(cursor.getColumnIndexOrThrow("_id")), cursor.getString(cursor.getColumnIndexOrThrow("package")), cursor.getString(cursor.getColumnIndexOrThrow("title")), cursor.getLong(cursor.getColumnIndexOrThrow("time")), cursor.getInt(cursor.getColumnIndexOrThrow("app_exist")) == 1);
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return InflaterHelper.g(R.layout.low_sugar_list_item, viewGroup, false);
    }

    @Override // android.widget.CursorAdapter
    protected void onContentChanged() {
        OnDataChangedListener onDataChangedListener = this.f16959c;
        if (onDataChangedListener != null) {
            onDataChangedListener.a();
        }
    }
}
