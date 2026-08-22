package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
abstract class PickerFragment<S> extends Fragment {
    protected final LinkedHashSet i0 = new LinkedHashSet();

    PickerFragment() {
    }

    boolean a2(OnSelectionChangedListener onSelectionChangedListener) {
        return this.i0.add(onSelectionChangedListener);
    }

    void b2() {
        this.i0.clear();
    }
}
