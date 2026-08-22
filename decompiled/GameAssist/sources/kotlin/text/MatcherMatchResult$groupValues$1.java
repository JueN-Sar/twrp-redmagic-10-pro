package kotlin.text;

import kotlin.Metadata;
import kotlin.collections.AbstractList;

@Metadata
/* loaded from: classes2.dex */
public final class MatcherMatchResult$groupValues$1 extends AbstractList<String> {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ MatcherMatchResult f18782c;

    @Override // kotlin.collections.AbstractCollection
    public int b() {
        java.util.regex.MatchResult b2;
        b2 = this.f18782c.b();
        return b2.groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof String) {
            return d((String) obj);
        }
        return false;
    }

    public /* bridge */ boolean d(String str) {
        return super.contains(str);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public String get(int i2) {
        java.util.regex.MatchResult b2;
        b2 = this.f18782c.b();
        String group = b2.group(i2);
        return group == null ? "" : group;
    }

    public /* bridge */ int g(String str) {
        return super.indexOf(str);
    }

    public /* bridge */ int h(String str) {
        return super.lastIndexOf(str);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof String) {
            return g((String) obj);
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof String) {
            return h((String) obj);
        }
        return -1;
    }
}
