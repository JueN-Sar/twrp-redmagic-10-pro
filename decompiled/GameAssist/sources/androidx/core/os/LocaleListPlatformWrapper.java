package androidx.core.os;

import android.os.LocaleList;
import androidx.annotation.RequiresApi;
import java.util.Locale;

@RequiresApi
/* loaded from: classes.dex */
final class LocaleListPlatformWrapper implements LocaleListInterface {

    /* renamed from: a, reason: collision with root package name */
    private final LocaleList f3122a;

    LocaleListPlatformWrapper(Object obj) {
        this.f3122a = (LocaleList) obj;
    }

    @Override // androidx.core.os.LocaleListInterface
    public String a() {
        return this.f3122a.toLanguageTags();
    }

    @Override // androidx.core.os.LocaleListInterface
    public Object b() {
        return this.f3122a;
    }

    public boolean equals(Object obj) {
        return this.f3122a.equals(((LocaleListInterface) obj).b());
    }

    @Override // androidx.core.os.LocaleListInterface
    public Locale get(int i2) {
        return this.f3122a.get(i2);
    }

    public int hashCode() {
        return this.f3122a.hashCode();
    }

    @Override // androidx.core.os.LocaleListInterface
    public boolean isEmpty() {
        return this.f3122a.isEmpty();
    }

    @Override // androidx.core.os.LocaleListInterface
    public int size() {
        return this.f3122a.size();
    }

    public String toString() {
        return this.f3122a.toString();
    }
}
