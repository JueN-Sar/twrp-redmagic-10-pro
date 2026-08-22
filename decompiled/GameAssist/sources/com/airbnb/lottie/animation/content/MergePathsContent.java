package com.airbnb.lottie.animation.content;

import android.annotation.TargetApi;
import android.graphics.Path;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@TargetApi(19)
/* loaded from: classes.dex */
public class MergePathsContent implements PathContent, GreedyContent {

    /* renamed from: d, reason: collision with root package name */
    private final String f9419d;

    /* renamed from: f, reason: collision with root package name */
    private final MergePaths f9421f;

    /* renamed from: a, reason: collision with root package name */
    private final Path f9416a = new Path();

    /* renamed from: b, reason: collision with root package name */
    private final Path f9417b = new Path();

    /* renamed from: c, reason: collision with root package name */
    private final Path f9418c = new Path();

    /* renamed from: e, reason: collision with root package name */
    private final List f9420e = new ArrayList();

    /* renamed from: com.airbnb.lottie.animation.content.MergePathsContent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9422a;

        static {
            int[] iArr = new int[MergePaths.MergePathsMode.values().length];
            f9422a = iArr;
            try {
                iArr[MergePaths.MergePathsMode.MERGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9422a[MergePaths.MergePathsMode.ADD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9422a[MergePaths.MergePathsMode.SUBTRACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9422a[MergePaths.MergePathsMode.INTERSECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9422a[MergePaths.MergePathsMode.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public MergePathsContent(MergePaths mergePaths) {
        this.f9419d = mergePaths.c();
        this.f9421f = mergePaths;
    }

    private void a() {
        for (int i2 = 0; i2 < this.f9420e.size(); i2++) {
            this.f9418c.addPath(((PathContent) this.f9420e.get(i2)).d());
        }
    }

    private void e(Path.Op op) {
        this.f9417b.reset();
        this.f9416a.reset();
        for (int size = this.f9420e.size() - 1; size >= 1; size--) {
            PathContent pathContent = (PathContent) this.f9420e.get(size);
            if (pathContent instanceof ContentGroup) {
                ContentGroup contentGroup = (ContentGroup) pathContent;
                List l2 = contentGroup.l();
                for (int size2 = l2.size() - 1; size2 >= 0; size2--) {
                    Path d2 = ((PathContent) l2.get(size2)).d();
                    d2.transform(contentGroup.m());
                    this.f9417b.addPath(d2);
                }
            } else {
                this.f9417b.addPath(pathContent.d());
            }
        }
        PathContent pathContent2 = (PathContent) this.f9420e.get(0);
        if (pathContent2 instanceof ContentGroup) {
            ContentGroup contentGroup2 = (ContentGroup) pathContent2;
            List l3 = contentGroup2.l();
            for (int i2 = 0; i2 < l3.size(); i2++) {
                Path d3 = ((PathContent) l3.get(i2)).d();
                d3.transform(contentGroup2.m());
                this.f9416a.addPath(d3);
            }
        } else {
            this.f9416a.set(pathContent2.d());
        }
        this.f9418c.op(this.f9416a, this.f9417b, op);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        for (int i2 = 0; i2 < this.f9420e.size(); i2++) {
            ((PathContent) this.f9420e.get(i2)).b(list, list2);
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path d() {
        this.f9418c.reset();
        if (this.f9421f.d()) {
            return this.f9418c;
        }
        int i2 = AnonymousClass1.f9422a[this.f9421f.b().ordinal()];
        if (i2 == 1) {
            a();
        } else if (i2 == 2) {
            e(Path.Op.UNION);
        } else if (i2 == 3) {
            e(Path.Op.REVERSE_DIFFERENCE);
        } else if (i2 == 4) {
            e(Path.Op.INTERSECT);
        } else if (i2 == 5) {
            e(Path.Op.XOR);
        }
        return this.f9418c;
    }

    @Override // com.airbnb.lottie.animation.content.GreedyContent
    public void h(ListIterator listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            Content content = (Content) listIterator.previous();
            if (content instanceof PathContent) {
                this.f9420e.add((PathContent) content);
                listIterator.remove();
            }
        }
    }
}
