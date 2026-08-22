package androidx.preference;

import android.util.SparseArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class PreferenceViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: s, reason: collision with root package name */
    private final SparseArray f4759s;
    private boolean t;
    private boolean u;

    PreferenceViewHolder(View view) {
        super(view);
        SparseArray sparseArray = new SparseArray(4);
        this.f4759s = sparseArray;
        sparseArray.put(android.R.id.title, view.findViewById(android.R.id.title));
        sparseArray.put(android.R.id.summary, view.findViewById(android.R.id.summary));
        sparseArray.put(android.R.id.icon, view.findViewById(android.R.id.icon));
        int i2 = R.id.icon_frame;
        sparseArray.put(i2, view.findViewById(i2));
        sparseArray.put(android.R.id.icon_frame, view.findViewById(android.R.id.icon_frame));
    }

    public View N(int i2) {
        View view = (View) this.f4759s.get(i2);
        if (view != null) {
            return view;
        }
        View findViewById = this.f5252a.findViewById(i2);
        if (findViewById != null) {
            this.f4759s.put(i2, findViewById);
        }
        return findViewById;
    }

    public boolean O() {
        return this.t;
    }

    public boolean P() {
        return this.u;
    }

    public void Q(boolean z) {
        this.t = z;
    }

    public void R(boolean z) {
        this.u = z;
    }
}
