package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import androidx.cursoradapter.widget.CursorFilter;

/* loaded from: classes.dex */
public abstract class CursorAdapter extends BaseAdapter implements Filterable, CursorFilter.CursorFilterClient {

    /* renamed from: c, reason: collision with root package name */
    protected boolean f3566c;

    /* renamed from: h, reason: collision with root package name */
    protected boolean f3567h;

    /* renamed from: i, reason: collision with root package name */
    protected Cursor f3568i;

    /* renamed from: j, reason: collision with root package name */
    protected Context f3569j;

    /* renamed from: k, reason: collision with root package name */
    protected int f3570k;

    /* renamed from: l, reason: collision with root package name */
    protected ChangeObserver f3571l;

    /* renamed from: m, reason: collision with root package name */
    protected DataSetObserver f3572m;

    /* renamed from: n, reason: collision with root package name */
    protected CursorFilter f3573n;

    /* renamed from: o, reason: collision with root package name */
    protected FilterQueryProvider f3574o;

    private class ChangeObserver extends ContentObserver {
        ChangeObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            CursorAdapter.this.e();
        }
    }

    private class MyDataSetObserver extends DataSetObserver {
        MyDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.f3566c = true;
            cursorAdapter.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.f3566c = false;
            cursorAdapter.notifyDataSetInvalidated();
        }
    }

    public CursorAdapter(Context context, Cursor cursor, boolean z) {
        b(context, cursor, z ? 1 : 2);
    }

    public abstract void a(View view, Context context, Cursor cursor);

    void b(Context context, Cursor cursor, int i2) {
        if ((i2 & 1) == 1) {
            i2 |= 2;
            this.f3567h = true;
        } else {
            this.f3567h = false;
        }
        boolean z = cursor != null;
        this.f3568i = cursor;
        this.f3566c = z;
        this.f3569j = context;
        this.f3570k = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i2 & 2) == 2) {
            this.f3571l = new ChangeObserver();
            this.f3572m = new MyDataSetObserver();
        } else {
            this.f3571l = null;
            this.f3572m = null;
        }
        if (z) {
            ChangeObserver changeObserver = this.f3571l;
            if (changeObserver != null) {
                cursor.registerContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.f3572m;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    public View c(Context context, Cursor cursor, ViewGroup viewGroup) {
        return d(context, cursor, viewGroup);
    }

    public void changeCursor(Cursor cursor) {
        Cursor f2 = f(cursor);
        if (f2 != null) {
            f2.close();
        }
    }

    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    public abstract View d(Context context, Cursor cursor, ViewGroup viewGroup);

    protected void e() {
        Cursor cursor;
        if (!this.f3567h || (cursor = this.f3568i) == null || cursor.isClosed()) {
            return;
        }
        this.f3566c = this.f3568i.requery();
    }

    public Cursor f(Cursor cursor) {
        Cursor cursor2 = this.f3568i;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            ChangeObserver changeObserver = this.f3571l;
            if (changeObserver != null) {
                cursor2.unregisterContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.f3572m;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f3568i = cursor;
        if (cursor != null) {
            ChangeObserver changeObserver2 = this.f3571l;
            if (changeObserver2 != null) {
                cursor.registerContentObserver(changeObserver2);
            }
            DataSetObserver dataSetObserver2 = this.f3572m;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.f3570k = cursor.getColumnIndexOrThrow("_id");
            this.f3566c = true;
            notifyDataSetChanged();
        } else {
            this.f3570k = -1;
            this.f3566c = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.f3566c || (cursor = this.f3568i) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // androidx.cursoradapter.widget.CursorFilter.CursorFilterClient
    public Cursor getCursor() {
        return this.f3568i;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f3566c) {
            return null;
        }
        this.f3568i.moveToPosition(i2);
        if (view == null) {
            view = c(this.f3569j, this.f3568i, viewGroup);
        }
        a(view, this.f3569j, this.f3568i);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.f3573n == null) {
            this.f3573n = new CursorFilter(this);
        }
        return this.f3573n;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.f3566c || (cursor = this.f3568i) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f3568i;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        Cursor cursor;
        if (this.f3566c && (cursor = this.f3568i) != null && cursor.moveToPosition(i2)) {
            return this.f3568i.getLong(this.f3570k);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f3566c) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f3568i.moveToPosition(i2)) {
            if (view == null) {
                view = d(this.f3569j, this.f3568i, viewGroup);
            }
            a(view, this.f3569j, this.f3568i);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i2);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        FilterQueryProvider filterQueryProvider = this.f3574o;
        return filterQueryProvider != null ? filterQueryProvider.runQuery(charSequence) : this.f3568i;
    }
}
