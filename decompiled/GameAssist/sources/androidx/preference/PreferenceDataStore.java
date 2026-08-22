package androidx.preference;

import java.util.Set;

/* loaded from: classes.dex */
public abstract class PreferenceDataStore {
    public boolean a(String str, boolean z) {
        return z;
    }

    public int b(String str, int i2) {
        return i2;
    }

    public String c(String str, String str2) {
        return str2;
    }

    public Set d(String str, Set set) {
        return set;
    }

    public void e(String str, boolean z) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void f(String str, int i2) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void g(String str, String str2) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void h(String str, Set set) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }
}
