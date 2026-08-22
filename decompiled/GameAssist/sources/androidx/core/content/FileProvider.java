package androidx.core.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.ObjectsCompat;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class FileProvider extends ContentProvider {

    /* renamed from: k, reason: collision with root package name */
    private static final String[] f2841k = {"_display_name", "_size"};

    /* renamed from: l, reason: collision with root package name */
    private static final File f2842l = new File("/");

    /* renamed from: m, reason: collision with root package name */
    private static final HashMap f2843m = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final Object f2844c;

    /* renamed from: h, reason: collision with root package name */
    private String f2845h;

    /* renamed from: i, reason: collision with root package name */
    private PathStrategy f2846i;

    /* renamed from: j, reason: collision with root package name */
    private final int f2847j;

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static File[] a(Context context) {
            return context.getExternalMediaDirs();
        }
    }

    interface PathStrategy {
        File a(Uri uri);
    }

    static class SimplePathStrategy implements PathStrategy {

        /* renamed from: a, reason: collision with root package name */
        private final String f2848a;

        /* renamed from: b, reason: collision with root package name */
        private final HashMap f2849b = new HashMap();

        SimplePathStrategy(String str) {
            this.f2848a = str;
        }

        private boolean c(String str, String str2) {
            String i2 = FileProvider.i(str);
            String i3 = FileProvider.i(str2);
            if (!i2.equals(i3)) {
                if (!i2.startsWith(i3 + '/')) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.core.content.FileProvider.PathStrategy
        public File a(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = (File) this.f2849b.get(decode);
            if (file == null) {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
            File file2 = new File(file, decode2);
            try {
                File canonicalFile = file2.getCanonicalFile();
                if (c(canonicalFile.getPath(), file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
            }
        }

        void b(String str, File file) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                this.f2849b.put(str, file.getCanonicalFile());
            } catch (IOException e2) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e2);
            }
        }
    }

    public FileProvider() {
        this(0);
    }

    private static File b(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    private static Object[] c(Object[] objArr, int i2) {
        Object[] objArr2 = new Object[i2];
        System.arraycopy(objArr, 0, objArr2, 0, i2);
        return objArr2;
    }

    private static String[] d(String[] strArr, int i2) {
        String[] strArr2 = new String[i2];
        System.arraycopy(strArr, 0, strArr2, 0, i2);
        return strArr2;
    }

    private PathStrategy e() {
        PathStrategy pathStrategy;
        synchronized (this.f2844c) {
            try {
                ObjectsCompat.d(this.f2845h, "mAuthority is null. Did you override attachInfo and did not call super.attachInfo()?");
                if (this.f2846i == null) {
                    this.f2846i = f(getContext(), this.f2845h, this.f2847j);
                }
                pathStrategy = this.f2846i;
            } catch (Throwable th) {
                throw th;
            }
        }
        return pathStrategy;
    }

    private static PathStrategy f(Context context, String str, int i2) {
        PathStrategy pathStrategy;
        HashMap hashMap = f2843m;
        synchronized (hashMap) {
            try {
                pathStrategy = (PathStrategy) hashMap.get(str);
                if (pathStrategy == null) {
                    try {
                        try {
                            pathStrategy = h(context, str, i2);
                            hashMap.put(str, pathStrategy);
                        } catch (IOException e2) {
                            throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                        }
                    } catch (XmlPullParserException e3) {
                        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e3);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return pathStrategy;
    }

    private static int g(String str) {
        if ("r".equals(str)) {
            return WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    @VisibleForTesting
    static XmlResourceParser getFileProviderPathsMetaData(Context context, String str, @Nullable ProviderInfo providerInfo, int i2) {
        if (providerInfo == null) {
            throw new IllegalArgumentException("Couldn't find meta-data for provider with authority " + str);
        }
        if (providerInfo.metaData == null && i2 != 0) {
            Bundle bundle = new Bundle(1);
            providerInfo.metaData = bundle;
            bundle.putInt("android.support.FILE_PROVIDER_PATHS", i2);
        }
        XmlResourceParser loadXmlMetaData = providerInfo.loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (loadXmlMetaData != null) {
            return loadXmlMetaData;
        }
        throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
    }

    private static PathStrategy h(Context context, String str, int i2) {
        SimplePathStrategy simplePathStrategy = new SimplePathStrategy(str);
        XmlResourceParser fileProviderPathsMetaData = getFileProviderPathsMetaData(context, str, context.getPackageManager().resolveContentProvider(str, 128), i2);
        while (true) {
            int next = fileProviderPathsMetaData.next();
            if (next == 1) {
                return simplePathStrategy;
            }
            if (next == 2) {
                String name = fileProviderPathsMetaData.getName();
                File file = null;
                String attributeValue = fileProviderPathsMetaData.getAttributeValue(null, "name");
                String attributeValue2 = fileProviderPathsMetaData.getAttributeValue(null, "path");
                if ("root-path".equals(name)) {
                    file = f2842l;
                } else if ("files-path".equals(name)) {
                    file = context.getFilesDir();
                } else if ("cache-path".equals(name)) {
                    file = context.getCacheDir();
                } else if ("external-path".equals(name)) {
                    file = Environment.getExternalStorageDirectory();
                } else if ("external-files-path".equals(name)) {
                    File[] g2 = ContextCompat.g(context, null);
                    if (g2.length > 0) {
                        file = g2[0];
                    }
                } else if ("external-cache-path".equals(name)) {
                    File[] f2 = ContextCompat.f(context);
                    if (f2.length > 0) {
                        file = f2[0];
                    }
                } else if ("external-media-path".equals(name)) {
                    File[] a2 = Api21Impl.a(context);
                    if (a2.length > 0) {
                        file = a2[0];
                    }
                }
                if (file != null) {
                    simplePathStrategy.b(attributeValue, b(file, attributeValue2));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String i(String str) {
        return (str.length() <= 0 || str.charAt(str.length() + (-1)) != '/') ? str : str.substring(0, str.length() - 1);
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        String str = providerInfo.authority.split(";")[0];
        synchronized (this.f2844c) {
            this.f2845h = str;
        }
        HashMap hashMap = f2843m;
        synchronized (hashMap) {
            hashMap.remove(str);
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return e().a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        File a2 = e().a(uri);
        int lastIndexOf = a2.getName().lastIndexOf(46);
        if (lastIndexOf < 0) {
            return "application/octet-stream";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(a2.getName().substring(lastIndexOf + 1));
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public String getTypeAnonymous(Uri uri) {
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        return ParcelFileDescriptor.open(e().a(uri), g(str));
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i2;
        File a2 = e().a(uri);
        String queryParameter = uri.getQueryParameter("displayName");
        if (strArr == null) {
            strArr = f2841k;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i3 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i3] = "_display_name";
                i2 = i3 + 1;
                objArr[i3] = queryParameter == null ? a2.getName() : queryParameter;
            } else if ("_size".equals(str3)) {
                strArr3[i3] = "_size";
                i2 = i3 + 1;
                objArr[i3] = Long.valueOf(a2.length());
            }
            i3 = i2;
        }
        String[] d2 = d(strArr3, i3);
        Object[] c2 = c(objArr, i3);
        MatrixCursor matrixCursor = new MatrixCursor(d2, 1);
        matrixCursor.addRow(c2);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    protected FileProvider(int i2) {
        this.f2844c = new Object();
        this.f2847j = i2;
    }
}
