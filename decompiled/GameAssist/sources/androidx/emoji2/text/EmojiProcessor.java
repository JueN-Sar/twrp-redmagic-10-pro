package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.annotation.AnyThread;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

@AnyThread
@RequiresApi
@RestrictTo
/* loaded from: classes.dex */
final class EmojiProcessor {

    /* renamed from: a, reason: collision with root package name */
    private final EmojiCompat.SpanFactory f3735a;

    /* renamed from: b, reason: collision with root package name */
    private final MetadataRepo f3736b;

    /* renamed from: c, reason: collision with root package name */
    private EmojiCompat.GlyphChecker f3737c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f3738d;

    /* renamed from: e, reason: collision with root package name */
    private final int[] f3739e;

    @RequiresApi
    private static final class CodepointIndexFinder {
        static int a(CharSequence charSequence, int i2, int i3) {
            int length = charSequence.length();
            if (i2 < 0 || length < i2 || i3 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i3 != 0) {
                    i2--;
                    if (i2 < 0) {
                        return z ? -1 : 0;
                    }
                    char charAt = charSequence.charAt(i2);
                    if (z) {
                        if (!Character.isHighSurrogate(charAt)) {
                            return -1;
                        }
                        i3--;
                    } else if (!Character.isSurrogate(charAt)) {
                        i3--;
                    } else {
                        if (Character.isHighSurrogate(charAt)) {
                            return -1;
                        }
                        z = true;
                    }
                }
                return i2;
            }
        }

        static int b(CharSequence charSequence, int i2, int i3) {
            int length = charSequence.length();
            if (i2 < 0 || length < i2 || i3 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i3 != 0) {
                    if (i2 >= length) {
                        if (z) {
                            return -1;
                        }
                        return length;
                    }
                    char charAt = charSequence.charAt(i2);
                    if (z) {
                        if (!Character.isLowSurrogate(charAt)) {
                            return -1;
                        }
                        i3--;
                        i2++;
                    } else if (!Character.isSurrogate(charAt)) {
                        i3--;
                        i2++;
                    } else {
                        if (Character.isLowSurrogate(charAt)) {
                            return -1;
                        }
                        i2++;
                        z = true;
                    }
                }
                return i2;
            }
        }
    }

    private static class EmojiProcessAddSpanCallback implements EmojiProcessCallback<UnprecomputeTextOnModificationSpannable> {

        /* renamed from: a, reason: collision with root package name */
        public UnprecomputeTextOnModificationSpannable f3740a;

        /* renamed from: b, reason: collision with root package name */
        private final EmojiCompat.SpanFactory f3741b;

        EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable, EmojiCompat.SpanFactory spanFactory) {
            this.f3740a = unprecomputeTextOnModificationSpannable;
            this.f3741b = spanFactory;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean a(CharSequence charSequence, int i2, int i3, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (typefaceEmojiRasterizer.k()) {
                return true;
            }
            if (this.f3740a == null) {
                this.f3740a = new UnprecomputeTextOnModificationSpannable(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
            }
            this.f3740a.setSpan(this.f3741b.a(typefaceEmojiRasterizer), i2, i3, 33);
            return true;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public UnprecomputeTextOnModificationSpannable getResult() {
            return this.f3740a;
        }
    }

    private interface EmojiProcessCallback<T> {
        boolean a(CharSequence charSequence, int i2, int i3, TypefaceEmojiRasterizer typefaceEmojiRasterizer);

        Object getResult();
    }

    private static class EmojiProcessLookupCallback implements EmojiProcessCallback<EmojiProcessLookupCallback> {

        /* renamed from: a, reason: collision with root package name */
        private final int f3742a;

        /* renamed from: b, reason: collision with root package name */
        public int f3743b;

        /* renamed from: c, reason: collision with root package name */
        public int f3744c;

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean a(CharSequence charSequence, int i2, int i3, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            int i4 = this.f3742a;
            if (i2 > i4 || i4 >= i3) {
                return i3 <= i4;
            }
            this.f3743b = i2;
            this.f3744c = i3;
            return false;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public EmojiProcessLookupCallback getResult() {
            return this;
        }
    }

    private static class MarkExclusionCallback implements EmojiProcessCallback<MarkExclusionCallback> {

        /* renamed from: a, reason: collision with root package name */
        private final String f3745a;

        MarkExclusionCallback(String str) {
            this.f3745a = str;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean a(CharSequence charSequence, int i2, int i3, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (!TextUtils.equals(charSequence.subSequence(i2, i3), this.f3745a)) {
                return true;
            }
            typefaceEmojiRasterizer.l(true);
            return false;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public MarkExclusionCallback getResult() {
            return this;
        }
    }

    static final class ProcessorSm {

        /* renamed from: a, reason: collision with root package name */
        private int f3746a = 1;

        /* renamed from: b, reason: collision with root package name */
        private final MetadataRepo.Node f3747b;

        /* renamed from: c, reason: collision with root package name */
        private MetadataRepo.Node f3748c;

        /* renamed from: d, reason: collision with root package name */
        private MetadataRepo.Node f3749d;

        /* renamed from: e, reason: collision with root package name */
        private int f3750e;

        /* renamed from: f, reason: collision with root package name */
        private int f3751f;

        /* renamed from: g, reason: collision with root package name */
        private final boolean f3752g;

        /* renamed from: h, reason: collision with root package name */
        private final int[] f3753h;

        ProcessorSm(MetadataRepo.Node node, boolean z, int[] iArr) {
            this.f3747b = node;
            this.f3748c = node;
            this.f3752g = z;
            this.f3753h = iArr;
        }

        private static boolean d(int i2) {
            return i2 == 65039;
        }

        private static boolean f(int i2) {
            return i2 == 65038;
        }

        private int g() {
            this.f3746a = 1;
            this.f3748c = this.f3747b;
            this.f3751f = 0;
            return 1;
        }

        private boolean h() {
            if (this.f3748c.b().j() || d(this.f3750e)) {
                return true;
            }
            if (this.f3752g) {
                if (this.f3753h == null) {
                    return true;
                }
                if (Arrays.binarySearch(this.f3753h, this.f3748c.b().b(0)) < 0) {
                    return true;
                }
            }
            return false;
        }

        int a(int i2) {
            MetadataRepo.Node a2 = this.f3748c.a(i2);
            int i3 = 2;
            if (this.f3746a != 2) {
                if (a2 == null) {
                    i3 = g();
                } else {
                    this.f3746a = 2;
                    this.f3748c = a2;
                    this.f3751f = 1;
                }
            } else if (a2 != null) {
                this.f3748c = a2;
                this.f3751f++;
            } else if (f(i2)) {
                i3 = g();
            } else if (!d(i2)) {
                if (this.f3748c.b() != null) {
                    i3 = 3;
                    if (this.f3751f != 1) {
                        this.f3749d = this.f3748c;
                        g();
                    } else if (h()) {
                        this.f3749d = this.f3748c;
                        g();
                    } else {
                        i3 = g();
                    }
                } else {
                    i3 = g();
                }
            }
            this.f3750e = i2;
            return i3;
        }

        TypefaceEmojiRasterizer b() {
            return this.f3748c.b();
        }

        TypefaceEmojiRasterizer c() {
            return this.f3749d.b();
        }

        boolean e() {
            return this.f3746a == 2 && this.f3748c.b() != null && (this.f3751f > 1 || h());
        }
    }

    EmojiProcessor(MetadataRepo metadataRepo, EmojiCompat.SpanFactory spanFactory, EmojiCompat.GlyphChecker glyphChecker, boolean z, int[] iArr, Set set) {
        this.f3735a = spanFactory;
        this.f3736b = metadataRepo;
        this.f3737c = glyphChecker;
        this.f3738d = z;
        this.f3739e = iArr;
        g(set);
    }

    private static boolean a(Editable editable, KeyEvent keyEvent, boolean z) {
        EmojiSpan[] emojiSpanArr;
        if (f(keyEvent)) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (!e(selectionStart, selectionEnd) && (emojiSpanArr = (EmojiSpan[]) editable.getSpans(selectionStart, selectionEnd, EmojiSpan.class)) != null && emojiSpanArr.length > 0) {
            for (EmojiSpan emojiSpan : emojiSpanArr) {
                int spanStart = editable.getSpanStart(emojiSpan);
                int spanEnd = editable.getSpanEnd(emojiSpan);
                if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    static boolean b(InputConnection inputConnection, Editable editable, int i2, int i3, boolean z) {
        int max;
        int min;
        if (editable != null && inputConnection != null && i2 >= 0 && i3 >= 0) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (e(selectionStart, selectionEnd)) {
                return false;
            }
            if (z) {
                max = CodepointIndexFinder.a(editable, selectionStart, Math.max(i2, 0));
                min = CodepointIndexFinder.b(editable, selectionEnd, Math.max(i3, 0));
                if (max == -1 || min == -1) {
                    return false;
                }
            } else {
                max = Math.max(selectionStart - i2, 0);
                min = Math.min(selectionEnd + i3, editable.length());
            }
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) editable.getSpans(max, min, EmojiSpan.class);
            if (emojiSpanArr != null && emojiSpanArr.length > 0) {
                for (EmojiSpan emojiSpan : emojiSpanArr) {
                    int spanStart = editable.getSpanStart(emojiSpan);
                    int spanEnd = editable.getSpanEnd(emojiSpan);
                    max = Math.min(spanStart, max);
                    min = Math.max(spanEnd, min);
                }
                int max2 = Math.max(max, 0);
                int min2 = Math.min(min, editable.length());
                inputConnection.beginBatchEdit();
                editable.delete(max2, min2);
                inputConnection.endBatchEdit();
                return true;
            }
        }
        return false;
    }

    static boolean c(Editable editable, int i2, KeyEvent keyEvent) {
        boolean a2;
        if (i2 != 67) {
            if (i2 == 112) {
                a2 = a(editable, keyEvent, true);
            }
            return false;
        }
        a2 = a(editable, keyEvent, false);
        if (a2) {
            MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
            return true;
        }
        return false;
    }

    private boolean d(CharSequence charSequence, int i2, int i3, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if (typefaceEmojiRasterizer.d() == 0) {
            typefaceEmojiRasterizer.m(this.f3737c.a(charSequence, i2, i3, typefaceEmojiRasterizer.h()));
        }
        return typefaceEmojiRasterizer.d() == 2;
    }

    private static boolean e(int i2, int i3) {
        return i2 == -1 || i3 == -1 || i2 != i3;
    }

    private static boolean f(KeyEvent keyEvent) {
        return !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState());
    }

    private void g(Set set) {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            int[] iArr = (int[]) it.next();
            String str = new String(iArr, 0, iArr.length);
            i(str, 0, str.length(), 1, true, new MarkExclusionCallback(str));
        }
    }

    private Object i(CharSequence charSequence, int i2, int i3, int i4, boolean z, EmojiProcessCallback emojiProcessCallback) {
        int i5;
        ProcessorSm processorSm = new ProcessorSm(this.f3736b.f(), this.f3738d, this.f3739e);
        int i6 = 0;
        boolean z2 = true;
        int codePointAt = Character.codePointAt(charSequence, i2);
        loop0: while (true) {
            i5 = i2;
            while (i2 < i3 && i6 < i4 && z2) {
                int a2 = processorSm.a(codePointAt);
                if (a2 == 1) {
                    i5 += Character.charCount(Character.codePointAt(charSequence, i5));
                    if (i5 < i3) {
                        codePointAt = Character.codePointAt(charSequence, i5);
                    }
                    i2 = i5;
                } else if (a2 == 2) {
                    i2 += Character.charCount(codePointAt);
                    if (i2 < i3) {
                        codePointAt = Character.codePointAt(charSequence, i2);
                    }
                } else if (a2 == 3) {
                    if (z || !d(charSequence, i5, i2, processorSm.c())) {
                        z2 = emojiProcessCallback.a(charSequence, i5, i2, processorSm.c());
                        i6++;
                    }
                }
            }
        }
        if (processorSm.e() && i6 < i4 && z2 && (z || !d(charSequence, i5, i2, processorSm.b()))) {
            emojiProcessCallback.a(charSequence, i5, i2, processorSm.b());
        }
        return emojiProcessCallback.getResult();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0049 A[Catch: all -> 0x002a, TryCatch #0 {all -> 0x002a, blocks: (B:51:0x000e, B:54:0x0013, B:56:0x0017, B:58:0x0024, B:9:0x003a, B:11:0x0042, B:13:0x0045, B:15:0x0049, B:17:0x0055, B:19:0x0058, B:24:0x0066, B:30:0x0074, B:31:0x0080, B:33:0x0094, B:6:0x002f), top: B:50:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0094 A[Catch: all -> 0x002a, TRY_LEAVE, TryCatch #0 {all -> 0x002a, blocks: (B:51:0x000e, B:54:0x0013, B:56:0x0017, B:58:0x0024, B:9:0x003a, B:11:0x0042, B:13:0x0045, B:15:0x0049, B:17:0x0055, B:19:0x0058, B:24:0x0066, B:30:0x0074, B:31:0x0080, B:33:0x0094, B:6:0x002f), top: B:50:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.lang.CharSequence h(java.lang.CharSequence r11, int r12, int r13, int r14, boolean r15) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.emoji2.text.SpannableBuilder
            if (r0 == 0) goto La
            r1 = r11
            androidx.emoji2.text.SpannableBuilder r1 = (androidx.emoji2.text.SpannableBuilder) r1
            r1.a()
        La:
            java.lang.Class<androidx.emoji2.text.EmojiSpan> r1 = androidx.emoji2.text.EmojiSpan.class
            if (r0 != 0) goto L2f
            boolean r2 = r11 instanceof android.text.Spannable     // Catch: java.lang.Throwable -> L2a
            if (r2 == 0) goto L13
            goto L2f
        L13:
            boolean r2 = r11 instanceof android.text.Spanned     // Catch: java.lang.Throwable -> L2a
            if (r2 == 0) goto L2d
            r2 = r11
            android.text.Spanned r2 = (android.text.Spanned) r2     // Catch: java.lang.Throwable -> L2a
            int r3 = r12 + (-1)
            int r4 = r13 + 1
            int r2 = r2.nextSpanTransition(r3, r4, r1)     // Catch: java.lang.Throwable -> L2a
            if (r2 > r13) goto L2d
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r2 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch: java.lang.Throwable -> L2a
            r2.<init>(r11)     // Catch: java.lang.Throwable -> L2a
            goto L37
        L2a:
            r10 = move-exception
            goto Lb2
        L2d:
            r2 = 0
            goto L37
        L2f:
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r2 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch: java.lang.Throwable -> L2a
            r3 = r11
            android.text.Spannable r3 = (android.text.Spannable) r3     // Catch: java.lang.Throwable -> L2a
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2a
        L37:
            r3 = 0
            if (r2 == 0) goto L63
            java.lang.Object[] r4 = r2.getSpans(r12, r13, r1)     // Catch: java.lang.Throwable -> L2a
            androidx.emoji2.text.EmojiSpan[] r4 = (androidx.emoji2.text.EmojiSpan[]) r4     // Catch: java.lang.Throwable -> L2a
            if (r4 == 0) goto L63
            int r5 = r4.length     // Catch: java.lang.Throwable -> L2a
            if (r5 <= 0) goto L63
            int r5 = r4.length     // Catch: java.lang.Throwable -> L2a
            r6 = r3
        L47:
            if (r6 >= r5) goto L63
            r7 = r4[r6]     // Catch: java.lang.Throwable -> L2a
            int r8 = r2.getSpanStart(r7)     // Catch: java.lang.Throwable -> L2a
            int r9 = r2.getSpanEnd(r7)     // Catch: java.lang.Throwable -> L2a
            if (r8 == r13) goto L58
            r2.removeSpan(r7)     // Catch: java.lang.Throwable -> L2a
        L58:
            int r12 = java.lang.Math.min(r8, r12)     // Catch: java.lang.Throwable -> L2a
            int r13 = java.lang.Math.max(r9, r13)     // Catch: java.lang.Throwable -> L2a
            int r6 = r6 + 1
            goto L47
        L63:
            r4 = r13
            if (r12 == r4) goto La9
            int r13 = r11.length()     // Catch: java.lang.Throwable -> L2a
            if (r12 < r13) goto L6d
            goto La9
        L6d:
            r13 = 2147483647(0x7fffffff, float:NaN)
            if (r14 == r13) goto L80
            if (r2 == 0) goto L80
            int r13 = r2.length()     // Catch: java.lang.Throwable -> L2a
            java.lang.Object[] r13 = r2.getSpans(r3, r13, r1)     // Catch: java.lang.Throwable -> L2a
            androidx.emoji2.text.EmojiSpan[] r13 = (androidx.emoji2.text.EmojiSpan[]) r13     // Catch: java.lang.Throwable -> L2a
            int r13 = r13.length     // Catch: java.lang.Throwable -> L2a
            int r14 = r14 - r13
        L80:
            r5 = r14
            androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback r7 = new androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback     // Catch: java.lang.Throwable -> L2a
            androidx.emoji2.text.EmojiCompat$SpanFactory r13 = r10.f3735a     // Catch: java.lang.Throwable -> L2a
            r7.<init>(r2, r13)     // Catch: java.lang.Throwable -> L2a
            r1 = r10
            r2 = r11
            r3 = r12
            r6 = r15
            java.lang.Object r10 = r1.i(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L2a
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r10 = (androidx.emoji2.text.UnprecomputeTextOnModificationSpannable) r10     // Catch: java.lang.Throwable -> L2a
            if (r10 == 0) goto La0
            android.text.Spannable r10 = r10.b()     // Catch: java.lang.Throwable -> L2a
            if (r0 == 0) goto L9f
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            r11.d()
        L9f:
            return r10
        La0:
            if (r0 == 0) goto La8
            r10 = r11
            androidx.emoji2.text.SpannableBuilder r10 = (androidx.emoji2.text.SpannableBuilder) r10
            r10.d()
        La8:
            return r11
        La9:
            if (r0 == 0) goto Lb1
            r10 = r11
            androidx.emoji2.text.SpannableBuilder r10 = (androidx.emoji2.text.SpannableBuilder) r10
            r10.d()
        Lb1:
            return r11
        Lb2:
            if (r0 == 0) goto Lb9
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            r11.d()
        Lb9:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.EmojiProcessor.h(java.lang.CharSequence, int, int, int, boolean):java.lang.CharSequence");
    }
}
