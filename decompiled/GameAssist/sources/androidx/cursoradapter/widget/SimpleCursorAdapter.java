package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SimpleCursorAdapter extends ResourceCursorAdapter {

    /* renamed from: s, reason: collision with root package name */
    protected int[] f3581s;
    protected int[] t;
    private int u;
    private CursorToStringConverter v;
    private ViewBinder w;
    String[] x;

    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor cursor);
    }

    public interface ViewBinder {
        boolean setViewValue(View view, Cursor cursor, int i2);
    }

    private void g(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            this.f3581s = null;
            return;
        }
        int length = strArr.length;
        int[] iArr = this.f3581s;
        if (iArr == null || iArr.length != length) {
            this.f3581s = new int[length];
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.f3581s[i2] = cursor.getColumnIndexOrThrow(strArr[i2]);
        }
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public void a(View view, Context context, Cursor cursor) {
        ViewBinder viewBinder = this.w;
        int[] iArr = this.t;
        int length = iArr.length;
        int[] iArr2 = this.f3581s;
        for (int i2 = 0; i2 < length; i2++) {
            View findViewById = view.findViewById(iArr[i2]);
            if (findViewById != null && (viewBinder == null || !viewBinder.setViewValue(findViewById, cursor, iArr2[i2]))) {
                String string = cursor.getString(iArr2[i2]);
                if (string == null) {
                    string = "";
                }
                if (findViewById instanceof TextView) {
                    i((TextView) findViewById, string);
                } else {
                    if (!(findViewById instanceof ImageView)) {
                        throw new IllegalStateException(findViewById.getClass().getName() + " is not a  view that can be bounds by this SimpleCursorAdapter");
                    }
                    h((ImageView) findViewById, string);
                }
            }
        }
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, androidx.cursoradapter.widget.CursorFilter.CursorFilterClient
    public CharSequence convertToString(Cursor cursor) {
        CursorToStringConverter cursorToStringConverter = this.v;
        if (cursorToStringConverter != null) {
            return cursorToStringConverter.convertToString(cursor);
        }
        int i2 = this.u;
        return i2 > -1 ? cursor.getString(i2) : super.convertToString(cursor);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public Cursor f(Cursor cursor) {
        g(cursor, this.x);
        return super.f(cursor);
    }

    public void h(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    public void i(TextView textView, String str) {
        textView.setText(str);
    }
}
