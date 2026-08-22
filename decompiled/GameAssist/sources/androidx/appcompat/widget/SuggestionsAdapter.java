package androidx.appcompat.widget;

import android.R;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.ResourceCursorAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
class SuggestionsAdapter extends ResourceCursorAdapter implements View.OnClickListener {
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;

    /* renamed from: s, reason: collision with root package name */
    private final SearchView f980s;
    private final SearchableInfo t;
    private final Context u;
    private final WeakHashMap v;
    private final int w;
    private boolean x;
    private int y;
    private ColorStateList z;

    private static final class ChildViewCache {

        /* renamed from: a, reason: collision with root package name */
        public final TextView f981a;

        /* renamed from: b, reason: collision with root package name */
        public final TextView f982b;

        /* renamed from: c, reason: collision with root package name */
        public final ImageView f983c;

        /* renamed from: d, reason: collision with root package name */
        public final ImageView f984d;

        /* renamed from: e, reason: collision with root package name */
        public final ImageView f985e;

        public ChildViewCache(View view) {
            this.f981a = (TextView) view.findViewById(R.id.text1);
            this.f982b = (TextView) view.findViewById(R.id.text2);
            this.f983c = (ImageView) view.findViewById(R.id.icon1);
            this.f984d = (ImageView) view.findViewById(R.id.icon2);
            this.f985e = (ImageView) view.findViewById(androidx.appcompat.R.id.edit_query);
        }
    }

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.x = false;
        this.y = 1;
        this.A = -1;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = -1;
        this.f980s = searchView;
        this.t = searchableInfo;
        this.w = searchView.getSuggestionCommitIconResId();
        this.u = context;
        this.v = weakHashMap;
    }

    private Drawable g(String str) {
        Drawable.ConstantState constantState = (Drawable.ConstantState) this.v.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private CharSequence h(CharSequence charSequence) {
        if (this.z == null) {
            TypedValue typedValue = new TypedValue();
            this.u.getTheme().resolveAttribute(androidx.appcompat.R.attr.textColorSearchUrl, typedValue, true);
            this.z = this.u.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.z, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private Drawable i(ComponentName componentName) {
        PackageManager packageManager = this.u.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.w("SuggestionsAdapter", e2.toString());
            return null;
        }
    }

    private Drawable j(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        if (!this.v.containsKey(flattenToShortString)) {
            Drawable i2 = i(componentName);
            this.v.put(flattenToShortString, i2 != null ? i2.getConstantState() : null);
            return i2;
        }
        Drawable.ConstantState constantState = (Drawable.ConstantState) this.v.get(flattenToShortString);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable(this.u.getResources());
    }

    public static String k(Cursor cursor, String str) {
        return s(cursor, cursor.getColumnIndex(str));
    }

    private Drawable l() {
        Drawable j2 = j(this.t.getSearchActivity());
        return j2 != null ? j2 : this.u.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable m(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return n(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream openInputStream = this.u.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            try {
                return Drawable.createFromStream(openInputStream, null);
            } finally {
                try {
                    openInputStream.close();
                } catch (IOException e2) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e2);
                }
            }
        } catch (FileNotFoundException e3) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
        return null;
    }

    private Drawable o(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.u.getPackageName() + "/" + parseInt;
            Drawable g2 = g(str2);
            if (g2 != null) {
                return g2;
            }
            Drawable e2 = ContextCompat.e(this.u, parseInt);
            w(str2, e2);
            return e2;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable g3 = g(str);
            if (g3 != null) {
                return g3;
            }
            Drawable m2 = m(Uri.parse(str));
            w(str, m2);
            return m2;
        }
    }

    private Drawable p(Cursor cursor) {
        int i2 = this.D;
        if (i2 == -1) {
            return null;
        }
        Drawable o2 = o(cursor.getString(i2));
        return o2 != null ? o2 : l();
    }

    private Drawable q(Cursor cursor) {
        int i2 = this.E;
        if (i2 == -1) {
            return null;
        }
        return o(cursor.getString(i2));
    }

    private static String s(Cursor cursor, int i2) {
        if (i2 == -1) {
            return null;
        }
        try {
            return cursor.getString(i2);
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            return null;
        }
    }

    private void u(ImageView imageView, Drawable drawable, int i2) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i2);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void v(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private void w(String str, Drawable drawable) {
        if (drawable != null) {
            this.v.put(str, drawable.getConstantState());
        }
    }

    private void x(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public void a(View view, Context context, Cursor cursor) {
        ChildViewCache childViewCache = (ChildViewCache) view.getTag();
        int i2 = this.F;
        int i3 = i2 != -1 ? cursor.getInt(i2) : 0;
        if (childViewCache.f981a != null) {
            v(childViewCache.f981a, s(cursor, this.A));
        }
        if (childViewCache.f982b != null) {
            String s2 = s(cursor, this.C);
            CharSequence h2 = s2 != null ? h(s2) : s(cursor, this.B);
            if (TextUtils.isEmpty(h2)) {
                TextView textView = childViewCache.f981a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    childViewCache.f981a.setMaxLines(2);
                }
            } else {
                TextView textView2 = childViewCache.f981a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    childViewCache.f981a.setMaxLines(1);
                }
            }
            v(childViewCache.f982b, h2);
        }
        ImageView imageView = childViewCache.f983c;
        if (imageView != null) {
            u(imageView, p(cursor), 4);
        }
        ImageView imageView2 = childViewCache.f984d;
        if (imageView2 != null) {
            u(imageView2, q(cursor), 8);
        }
        int i4 = this.y;
        if (i4 != 2 && (i4 != 1 || (i3 & 1) == 0)) {
            childViewCache.f985e.setVisibility(8);
            return;
        }
        childViewCache.f985e.setVisibility(0);
        childViewCache.f985e.setTag(childViewCache.f981a.getText());
        childViewCache.f985e.setOnClickListener(this);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, androidx.cursoradapter.widget.CursorFilter.CursorFilterClient
    public void changeCursor(Cursor cursor) {
        if (this.x) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(cursor);
            if (cursor != null) {
                this.A = cursor.getColumnIndex("suggest_text_1");
                this.B = cursor.getColumnIndex("suggest_text_2");
                this.C = cursor.getColumnIndex("suggest_text_2_url");
                this.D = cursor.getColumnIndex("suggest_icon_1");
                this.E = cursor.getColumnIndex("suggest_icon_2");
                this.F = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, androidx.cursoradapter.widget.CursorFilter.CursorFilterClient
    public CharSequence convertToString(Cursor cursor) {
        String k2;
        String k3;
        if (cursor == null) {
            return null;
        }
        String k4 = k(cursor, "suggest_intent_query");
        if (k4 != null) {
            return k4;
        }
        if (this.t.shouldRewriteQueryFromData() && (k3 = k(cursor, "suggest_intent_data")) != null) {
            return k3;
        }
        if (!this.t.shouldRewriteQueryFromText() || (k2 = k(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return k2;
    }

    @Override // androidx.cursoradapter.widget.ResourceCursorAdapter, androidx.cursoradapter.widget.CursorAdapter
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        View d2 = super.d(context, cursor, viewGroup);
        d2.setTag(new ChildViewCache(d2));
        ((ImageView) d2.findViewById(androidx.appcompat.R.id.edit_query)).setImageResource(this.w);
        return d2;
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View c2 = this.c(this.u, this.getCursor(), viewGroup);
            if (c2 != null) {
                ((ChildViewCache) c2.getTag()).f981a.setText(e2.toString());
            }
            return c2;
        }
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i2, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View d2 = this.d(this.u, this.getCursor(), viewGroup);
            if (d2 != null) {
                ((ChildViewCache) d2.getTag()).f981a.setText(e2.toString());
            }
            return d2;
        }
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    Drawable n(Uri uri) {
        int parseInt;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.u.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    parseInt = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                parseInt = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (parseInt != 0) {
                return resourcesForApplication.getDrawable(parseInt);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        x(getCursor());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        x(getCursor());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f980s.U((CharSequence) tag);
        }
    }

    Cursor r(SearchableInfo searchableInfo, String str, int i2) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i2 > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i2));
        }
        return this.u.getContentResolver().query(fragment.build(), null, suggestSelection, strArr2, null);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, androidx.cursoradapter.widget.CursorFilter.CursorFilterClient
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.f980s.getVisibility() == 0 && this.f980s.getWindowVisibility() == 0) {
            try {
                Cursor r2 = r(this.t, charSequence2, 50);
                if (r2 != null) {
                    r2.getCount();
                    return r2;
                }
            } catch (RuntimeException e2) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e2);
            }
        }
        return null;
    }

    public void t(int i2) {
        this.y = i2;
    }
}
