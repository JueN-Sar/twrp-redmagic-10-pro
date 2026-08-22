package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.ContentInfo;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: classes.dex */
public final class ContentInfoCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Compat f3313a;

    @RequiresApi
    private static final class Api31Impl {
        @NonNull
        @DoNotInline
        public static Pair<ContentInfo, ContentInfo> a(@NonNull ContentInfo contentInfo, @NonNull final Predicate<ClipData.Item> predicate) {
            ClipData clip = contentInfo.getClip();
            if (clip.getItemCount() != 1) {
                Objects.requireNonNull(predicate);
                Pair f2 = ContentInfoCompat.f(clip, new androidx.core.util.Predicate() { // from class: androidx.core.view.a
                    @Override // androidx.core.util.Predicate
                    public final boolean test(Object obj) {
                        return predicate.test((ClipData.Item) obj);
                    }
                });
                return f2.first == null ? Pair.create(null, contentInfo) : f2.second == null ? Pair.create(contentInfo, null) : Pair.create(new ContentInfo.Builder(contentInfo).setClip((ClipData) f2.first).build(), new ContentInfo.Builder(contentInfo).setClip((ClipData) f2.second).build());
            }
            boolean test = predicate.test(clip.getItemAt(0));
            ContentInfo contentInfo2 = test ? contentInfo : null;
            if (test) {
                contentInfo = null;
            }
            return Pair.create(contentInfo2, contentInfo);
        }
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final BuilderCompat f3314a;

        public Builder(ClipData clipData, int i2) {
            this.f3314a = new BuilderCompat31Impl(clipData, i2);
        }

        public ContentInfoCompat a() {
            return this.f3314a.build();
        }
    }

    private interface BuilderCompat {
        ContentInfoCompat build();
    }

    @RequiresApi
    private static final class BuilderCompat31Impl implements BuilderCompat {

        /* renamed from: a, reason: collision with root package name */
        private final ContentInfo.Builder f3315a;

        BuilderCompat31Impl(ClipData clipData, int i2) {
            this.f3315a = new ContentInfo.Builder(clipData, i2);
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new Compat31Impl(this.f3315a.build()));
        }
    }

    private static final class BuilderCompatImpl implements BuilderCompat {

        /* renamed from: a, reason: collision with root package name */
        ClipData f3316a;

        /* renamed from: b, reason: collision with root package name */
        int f3317b;

        /* renamed from: c, reason: collision with root package name */
        int f3318c;

        /* renamed from: d, reason: collision with root package name */
        Uri f3319d;

        /* renamed from: e, reason: collision with root package name */
        Bundle f3320e;

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new CompatImpl(this));
        }
    }

    private interface Compat {
        ContentInfo a();

        int b();

        ClipData c();

        int getFlags();
    }

    @RequiresApi
    private static final class Compat31Impl implements Compat {

        /* renamed from: a, reason: collision with root package name */
        private final ContentInfo f3321a;

        Compat31Impl(ContentInfo contentInfo) {
            this.f3321a = (ContentInfo) Preconditions.h(contentInfo);
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ContentInfo a() {
            return this.f3321a;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int b() {
            return this.f3321a.getSource();
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ClipData c() {
            return this.f3321a.getClip();
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int getFlags() {
            return this.f3321a.getFlags();
        }

        public String toString() {
            return "ContentInfoCompat{" + this.f3321a + "}";
        }
    }

    private static final class CompatImpl implements Compat {

        /* renamed from: a, reason: collision with root package name */
        private final ClipData f3322a;

        /* renamed from: b, reason: collision with root package name */
        private final int f3323b;

        /* renamed from: c, reason: collision with root package name */
        private final int f3324c;

        /* renamed from: d, reason: collision with root package name */
        private final Uri f3325d;

        /* renamed from: e, reason: collision with root package name */
        private final Bundle f3326e;

        CompatImpl(BuilderCompatImpl builderCompatImpl) {
            this.f3322a = (ClipData) Preconditions.h(builderCompatImpl.f3316a);
            this.f3323b = Preconditions.d(builderCompatImpl.f3317b, 0, 5, "source");
            this.f3324c = Preconditions.g(builderCompatImpl.f3318c, 1);
            this.f3325d = builderCompatImpl.f3319d;
            this.f3326e = builderCompatImpl.f3320e;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ContentInfo a() {
            return null;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int b() {
            return this.f3323b;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ClipData c() {
            return this.f3322a;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int getFlags() {
            return this.f3324c;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("ContentInfoCompat{clip=");
            sb.append(this.f3322a.getDescription());
            sb.append(", source=");
            sb.append(ContentInfoCompat.g(this.f3323b));
            sb.append(", flags=");
            sb.append(ContentInfoCompat.b(this.f3324c));
            if (this.f3325d == null) {
                str = "";
            } else {
                str = ", hasLinkUri(" + this.f3325d.toString().length() + ")";
            }
            sb.append(str);
            sb.append(this.f3326e != null ? ", hasExtras" : "");
            sb.append("}");
            return sb.toString();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Source {
    }

    ContentInfoCompat(Compat compat) {
        this.f3313a = compat;
    }

    static ClipData a(ClipDescription clipDescription, List list) {
        ClipData clipData = new ClipData(new ClipDescription(clipDescription), (ClipData.Item) list.get(0));
        for (int i2 = 1; i2 < list.size(); i2++) {
            clipData.addItem((ClipData.Item) list.get(i2));
        }
        return clipData;
    }

    static String b(int i2) {
        return (i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2);
    }

    static Pair f(ClipData clipData, androidx.core.util.Predicate predicate) {
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
            ClipData.Item itemAt = clipData.getItemAt(i2);
            if (predicate.test(itemAt)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(itemAt);
            } else {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                arrayList2.add(itemAt);
            }
        }
        return arrayList == null ? Pair.create(null, clipData) : arrayList2 == null ? Pair.create(clipData, null) : Pair.create(a(clipData.getDescription(), arrayList), a(clipData.getDescription(), arrayList2));
    }

    static String g(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? String.valueOf(i2) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP";
    }

    public static ContentInfoCompat i(ContentInfo contentInfo) {
        return new ContentInfoCompat(new Compat31Impl(contentInfo));
    }

    public ClipData c() {
        return this.f3313a.c();
    }

    public int d() {
        return this.f3313a.getFlags();
    }

    public int e() {
        return this.f3313a.b();
    }

    public ContentInfo h() {
        ContentInfo a2 = this.f3313a.a();
        Objects.requireNonNull(a2);
        return a2;
    }

    public String toString() {
        return this.f3313a.toString();
    }
}
