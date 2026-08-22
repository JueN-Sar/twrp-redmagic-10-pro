package androidx.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class PrintHelper {

    /* renamed from: e, reason: collision with root package name */
    static final boolean f4768e = true;

    /* renamed from: f, reason: collision with root package name */
    static final boolean f4769f = true;

    /* renamed from: a, reason: collision with root package name */
    final Context f4770a;

    /* renamed from: b, reason: collision with root package name */
    BitmapFactory.Options f4771b;

    /* renamed from: c, reason: collision with root package name */
    final Object f4772c;

    /* renamed from: d, reason: collision with root package name */
    int f4773d;

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @RequiresApi
    private class PrintBitmapAdapter extends PrintDocumentAdapter {

        /* renamed from: a, reason: collision with root package name */
        private final String f4782a;

        /* renamed from: b, reason: collision with root package name */
        private final int f4783b;

        /* renamed from: c, reason: collision with root package name */
        private final Bitmap f4784c;

        /* renamed from: d, reason: collision with root package name */
        private final OnPrintFinishCallback f4785d;

        /* renamed from: e, reason: collision with root package name */
        private PrintAttributes f4786e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ PrintHelper f4787f;

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            OnPrintFinishCallback onPrintFinishCallback = this.f4785d;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.f4786e = printAttributes2;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.f4782a).setContentType(1).setPageCount(1).build(), true ^ printAttributes2.equals(printAttributes));
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            this.f4787f.g(this.f4786e, this.f4783b, this.f4784c, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    @RequiresApi
    private class PrintUriAdapter extends PrintDocumentAdapter {

        /* renamed from: a, reason: collision with root package name */
        final String f4788a;

        /* renamed from: b, reason: collision with root package name */
        final Uri f4789b;

        /* renamed from: c, reason: collision with root package name */
        final OnPrintFinishCallback f4790c;

        /* renamed from: d, reason: collision with root package name */
        final int f4791d;

        /* renamed from: e, reason: collision with root package name */
        PrintAttributes f4792e;

        /* renamed from: f, reason: collision with root package name */
        AsyncTask f4793f;

        /* renamed from: g, reason: collision with root package name */
        Bitmap f4794g;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ PrintHelper f4795h;

        void a() {
            synchronized (this.f4795h.f4772c) {
                try {
                    PrintHelper printHelper = this.f4795h;
                    if (printHelper.f4771b != null) {
                        printHelper.f4771b = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            super.onFinish();
            a();
            AsyncTask asyncTask = this.f4793f;
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            OnPrintFinishCallback onPrintFinishCallback = this.f4790c;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
            Bitmap bitmap = this.f4794g;
            if (bitmap != null) {
                bitmap.recycle();
                this.f4794g = null;
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(final PrintAttributes printAttributes, final PrintAttributes printAttributes2, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            synchronized (this) {
                this.f4792e = printAttributes2;
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else if (this.f4794g != null) {
                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.f4788a).setContentType(1).setPageCount(1).build(), true ^ printAttributes2.equals(printAttributes));
            } else {
                this.f4793f = new AsyncTask<Uri, Boolean, Bitmap>() { // from class: androidx.print.PrintHelper.PrintUriAdapter.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public Bitmap doInBackground(Uri... uriArr) {
                        try {
                            PrintUriAdapter printUriAdapter = PrintUriAdapter.this;
                            return printUriAdapter.f4795h.f(printUriAdapter.f4789b);
                        } catch (FileNotFoundException unused) {
                            return null;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onCancelled(Bitmap bitmap) {
                        layoutResultCallback.onLayoutCancelled();
                        PrintUriAdapter.this.f4793f = null;
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onPostExecute(Bitmap bitmap) {
                        PrintAttributes.MediaSize mediaSize;
                        super.onPostExecute(bitmap);
                        if (bitmap != null && (!PrintHelper.f4768e || PrintUriAdapter.this.f4795h.f4773d == 0)) {
                            synchronized (this) {
                                mediaSize = PrintUriAdapter.this.f4792e.getMediaSize();
                            }
                            if (mediaSize != null && mediaSize.isPortrait() != PrintHelper.d(bitmap)) {
                                Matrix matrix = new Matrix();
                                matrix.postRotate(90.0f);
                                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            }
                        }
                        PrintUriAdapter.this.f4794g = bitmap;
                        if (bitmap != null) {
                            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(PrintUriAdapter.this.f4788a).setContentType(1).setPageCount(1).build(), true ^ printAttributes2.equals(printAttributes));
                        } else {
                            layoutResultCallback.onLayoutFailed(null);
                        }
                        PrintUriAdapter.this.f4793f = null;
                    }

                    @Override // android.os.AsyncTask
                    protected void onPreExecute() {
                        cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.print.PrintHelper.PrintUriAdapter.1.1
                            @Override // android.os.CancellationSignal.OnCancelListener
                            public void onCancel() {
                                PrintUriAdapter.this.a();
                                cancel(false);
                            }
                        });
                    }
                }.execute(new Uri[0]);
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            this.f4795h.g(this.f4792e, this.f4791d, this.f4794g, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static Bitmap a(Bitmap bitmap, int i2) {
        if (i2 != 1) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }

    private static PrintAttributes.Builder b(PrintAttributes printAttributes) {
        PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            minMargins.setColorMode(printAttributes.getColorMode());
        }
        if (printAttributes.getDuplexMode() != 0) {
            minMargins.setDuplexMode(printAttributes.getDuplexMode());
        }
        return minMargins;
    }

    static Matrix c(int i2, int i3, RectF rectF, int i4) {
        Matrix matrix = new Matrix();
        float f2 = i2;
        float width = rectF.width() / f2;
        float max = i4 == 2 ? Math.max(width, rectF.height() / i3) : Math.min(width, rectF.height() / i3);
        matrix.postScale(max, max);
        matrix.postTranslate((rectF.width() - (f2 * max)) / 2.0f, (rectF.height() - (i3 * max)) / 2.0f);
        return matrix;
    }

    static boolean d(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    private Bitmap e(Uri uri, BitmapFactory.Options options) {
        Context context;
        if (uri == null || (context = this.f4770a) == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream inputStream = null;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, null, options);
                if (openInputStream != null) {
                    try {
                        openInputStream.close();
                    } catch (IOException e2) {
                        Log.w("PrintHelper", "close fail ", e2);
                    }
                }
                return decodeStream;
            } catch (Throwable th) {
                th = th;
                inputStream = openInputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        Log.w("PrintHelper", "close fail ", e3);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    Bitmap f(Uri uri) {
        BitmapFactory.Options options;
        if (uri == null || this.f4770a == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        e(uri, options2);
        int i2 = options2.outWidth;
        int i3 = options2.outHeight;
        if (i2 > 0 && i3 > 0) {
            int max = Math.max(i2, i3);
            int i4 = 1;
            while (max > 3500) {
                max >>>= 1;
                i4 <<= 1;
            }
            if (i4 > 0 && Math.min(i2, i3) / i4 > 0) {
                synchronized (this.f4772c) {
                    options = new BitmapFactory.Options();
                    this.f4771b = options;
                    options.inMutable = true;
                    options.inSampleSize = i4;
                }
                try {
                    Bitmap e2 = e(uri, options);
                    synchronized (this.f4772c) {
                        this.f4771b = null;
                    }
                    return e2;
                } catch (Throwable th) {
                    synchronized (this.f4772c) {
                        this.f4771b = null;
                        throw th;
                    }
                }
            }
        }
        return null;
    }

    void g(final PrintAttributes printAttributes, final int i2, final Bitmap bitmap, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        final PrintAttributes build = f4769f ? printAttributes : b(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
        new AsyncTask<Void, Void, Throwable>() { // from class: androidx.print.PrintHelper.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Throwable doInBackground(Void... voidArr) {
                RectF rectF;
                try {
                    if (cancellationSignal.isCanceled()) {
                        return null;
                    }
                    PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument(PrintHelper.this.f4770a, build);
                    Bitmap a2 = PrintHelper.a(bitmap, build.getColorMode());
                    if (cancellationSignal.isCanceled()) {
                        return null;
                    }
                    try {
                        PdfDocument.Page startPage = printedPdfDocument.startPage(1);
                        boolean z = PrintHelper.f4769f;
                        if (z) {
                            rectF = new RectF(startPage.getInfo().getContentRect());
                        } else {
                            PrintedPdfDocument printedPdfDocument2 = new PrintedPdfDocument(PrintHelper.this.f4770a, printAttributes);
                            PdfDocument.Page startPage2 = printedPdfDocument2.startPage(1);
                            RectF rectF2 = new RectF(startPage2.getInfo().getContentRect());
                            printedPdfDocument2.finishPage(startPage2);
                            printedPdfDocument2.close();
                            rectF = rectF2;
                        }
                        Matrix c2 = PrintHelper.c(a2.getWidth(), a2.getHeight(), rectF, i2);
                        if (!z) {
                            c2.postTranslate(rectF.left, rectF.top);
                            startPage.getCanvas().clipRect(rectF);
                        }
                        startPage.getCanvas().drawBitmap(a2, c2, null);
                        printedPdfDocument.finishPage(startPage);
                        if (cancellationSignal.isCanceled()) {
                            printedPdfDocument.close();
                            ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
                            if (parcelFileDescriptor2 != null) {
                                try {
                                    parcelFileDescriptor2.close();
                                } catch (IOException unused) {
                                }
                            }
                            if (a2 != bitmap) {
                                a2.recycle();
                            }
                            return null;
                        }
                        printedPdfDocument.writeTo(new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
                        printedPdfDocument.close();
                        ParcelFileDescriptor parcelFileDescriptor3 = parcelFileDescriptor;
                        if (parcelFileDescriptor3 != null) {
                            try {
                                parcelFileDescriptor3.close();
                            } catch (IOException unused2) {
                            }
                        }
                        if (a2 != bitmap) {
                            a2.recycle();
                        }
                        return null;
                    } finally {
                    }
                } catch (Throwable th) {
                    return th;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Throwable th) {
                if (cancellationSignal.isCanceled()) {
                    writeResultCallback.onWriteCancelled();
                } else if (th == null) {
                    writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e("PrintHelper", "Error writing printed content", th);
                    writeResultCallback.onWriteFailed(null);
                }
            }
        }.execute(new Void[0]);
    }
}
