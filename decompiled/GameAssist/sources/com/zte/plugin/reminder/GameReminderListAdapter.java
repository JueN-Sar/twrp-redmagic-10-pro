package com.zte.plugin.reminder;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.reminder.R;

/* loaded from: classes2.dex */
public class GameReminderListAdapter extends CursorAdapter {

    /* renamed from: c, reason: collision with root package name */
    private Context f18025c;

    /* renamed from: h, reason: collision with root package name */
    private OnDataChangedListener f18026h;

    public interface OnDataChangedListener {
        void a();
    }

    public GameReminderListAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.f18025c = context;
    }

    public void a(OnDataChangedListener onDataChangedListener) {
        this.f18026h = onDataChangedListener;
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        ((GameReminderItem) view).d(cursor.getLong(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("package")), cursor.getString(cursor.getColumnIndex("title")), cursor.getLong(cursor.getColumnIndex("time")));
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return InflaterHelper.g(R.layout.game_reminder_list_item, viewGroup, false);
    }

    @Override // android.widget.CursorAdapter
    protected void onContentChanged() {
        OnDataChangedListener onDataChangedListener = this.f18026h;
        if (onDataChangedListener != null) {
            onDataChangedListener.a();
        }
    }
}
