package androidx.core.view.contentcapture;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.util.List;

/* loaded from: classes.dex */
public class ContentCaptureSessionCompat {

    /* renamed from: a, reason: collision with root package name */
    private final Object f3512a;

    @RequiresApi
    private static class Api23Impl {
        @DoNotInline
        static Bundle a(ViewStructure viewStructure) {
            return viewStructure.getExtras();
        }
    }

    @RequiresApi
    private static class Api29Impl {
        @DoNotInline
        static AutofillId a(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j2) {
            return contentCaptureSession.newAutofillId(autofillId, j2);
        }

        @DoNotInline
        static ViewStructure b(ContentCaptureSession contentCaptureSession, View view) {
            return contentCaptureSession.newViewStructure(view);
        }

        @DoNotInline
        static ViewStructure c(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long j2) {
            return contentCaptureSession.newVirtualViewStructure(autofillId, j2);
        }

        @DoNotInline
        static void d(ContentCaptureSession contentCaptureSession, ViewStructure viewStructure) {
            contentCaptureSession.notifyViewAppeared(viewStructure);
        }

        @DoNotInline
        public static void e(ContentCaptureSession contentCaptureSession, AutofillId autofillId, CharSequence charSequence) {
            contentCaptureSession.notifyViewTextChanged(autofillId, charSequence);
        }

        @DoNotInline
        static void f(ContentCaptureSession contentCaptureSession, AutofillId autofillId, long[] jArr) {
            contentCaptureSession.notifyViewsDisappeared(autofillId, jArr);
        }
    }

    @RequiresApi
    private static class Api34Impl {
        @DoNotInline
        static void a(ContentCaptureSession contentCaptureSession, List<ViewStructure> list) {
            contentCaptureSession.notifyViewsAppeared(list);
        }
    }

    public ContentCaptureSession a() {
        return (ContentCaptureSession) this.f3512a;
    }
}
