package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;

/* loaded from: classes.dex */
public final class EmojiTextViewHelper {

    /* renamed from: a, reason: collision with root package name */
    private final HelperInternal f3860a;

    static class HelperInternal {
        HelperInternal() {
        }

        InputFilter[] a(InputFilter[] inputFilterArr) {
            return inputFilterArr;
        }

        public boolean b() {
            return false;
        }

        void c(boolean z) {
        }

        void d(boolean z) {
        }

        TransformationMethod e(TransformationMethod transformationMethod) {
            return transformationMethod;
        }
    }

    @RequiresApi
    private static class HelperInternal19 extends HelperInternal {

        /* renamed from: a, reason: collision with root package name */
        private final TextView f3861a;

        /* renamed from: b, reason: collision with root package name */
        private final EmojiInputFilter f3862b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f3863c = true;

        HelperInternal19(TextView textView) {
            this.f3861a = textView;
            this.f3862b = new EmojiInputFilter(textView);
        }

        private InputFilter[] f(InputFilter[] inputFilterArr) {
            int length = inputFilterArr.length;
            for (InputFilter inputFilter : inputFilterArr) {
                if (inputFilter == this.f3862b) {
                    return inputFilterArr;
                }
            }
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length + 1];
            System.arraycopy(inputFilterArr, 0, inputFilterArr2, 0, length);
            inputFilterArr2[length] = this.f3862b;
            return inputFilterArr2;
        }

        private SparseArray g(InputFilter[] inputFilterArr) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i2 = 0; i2 < inputFilterArr.length; i2++) {
                InputFilter inputFilter = inputFilterArr[i2];
                if (inputFilter instanceof EmojiInputFilter) {
                    sparseArray.put(i2, inputFilter);
                }
            }
            return sparseArray;
        }

        private InputFilter[] h(InputFilter[] inputFilterArr) {
            SparseArray g2 = g(inputFilterArr);
            if (g2.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - g2.size()];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (g2.indexOfKey(i3) < 0) {
                    inputFilterArr2[i2] = inputFilterArr[i3];
                    i2++;
                }
            }
            return inputFilterArr2;
        }

        private TransformationMethod j(TransformationMethod transformationMethod) {
            return transformationMethod instanceof EmojiTransformationMethod ? ((EmojiTransformationMethod) transformationMethod).a() : transformationMethod;
        }

        private void k() {
            this.f3861a.setFilters(a(this.f3861a.getFilters()));
        }

        private TransformationMethod m(TransformationMethod transformationMethod) {
            return ((transformationMethod instanceof EmojiTransformationMethod) || (transformationMethod instanceof PasswordTransformationMethod)) ? transformationMethod : new EmojiTransformationMethod(transformationMethod);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        InputFilter[] a(InputFilter[] inputFilterArr) {
            return !this.f3863c ? h(inputFilterArr) : f(inputFilterArr);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public boolean b() {
            return this.f3863c;
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        void c(boolean z) {
            if (z) {
                l();
            }
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        void d(boolean z) {
            this.f3863c = z;
            l();
            k();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        TransformationMethod e(TransformationMethod transformationMethod) {
            return this.f3863c ? m(transformationMethod) : j(transformationMethod);
        }

        void i(boolean z) {
            this.f3863c = z;
        }

        void l() {
            this.f3861a.setTransformationMethod(e(this.f3861a.getTransformationMethod()));
        }
    }

    @RequiresApi
    private static class SkippingHelper19 extends HelperInternal {

        /* renamed from: a, reason: collision with root package name */
        private final HelperInternal19 f3864a;

        SkippingHelper19(TextView textView) {
            this.f3864a = new HelperInternal19(textView);
        }

        private boolean f() {
            return !EmojiCompat.i();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        InputFilter[] a(InputFilter[] inputFilterArr) {
            return f() ? inputFilterArr : this.f3864a.a(inputFilterArr);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        public boolean b() {
            return this.f3864a.b();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        void c(boolean z) {
            if (f()) {
                return;
            }
            this.f3864a.c(z);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        void d(boolean z) {
            if (f()) {
                this.f3864a.i(z);
            } else {
                this.f3864a.d(z);
            }
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.HelperInternal
        TransformationMethod e(TransformationMethod transformationMethod) {
            return f() ? transformationMethod : this.f3864a.e(transformationMethod);
        }
    }

    public EmojiTextViewHelper(TextView textView, boolean z) {
        Preconditions.i(textView, "textView cannot be null");
        if (z) {
            this.f3860a = new HelperInternal19(textView);
        } else {
            this.f3860a = new SkippingHelper19(textView);
        }
    }

    public InputFilter[] a(InputFilter[] inputFilterArr) {
        return this.f3860a.a(inputFilterArr);
    }

    public boolean b() {
        return this.f3860a.b();
    }

    public void c(boolean z) {
        this.f3860a.c(z);
    }

    public void d(boolean z) {
        this.f3860a.d(z);
    }

    public TransformationMethod e(TransformationMethod transformationMethod) {
        return this.f3860a.e(transformationMethod);
    }
}
