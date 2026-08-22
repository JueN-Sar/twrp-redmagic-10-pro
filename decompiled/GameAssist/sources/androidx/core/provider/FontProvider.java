package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes.dex */
class FontProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final Comparator f3135a = new Comparator() { // from class: androidx.core.provider.a
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int f2;
            f2 = FontProvider.f((byte[]) obj, (byte[]) obj2);
            return f2;
        }
    };

    private interface ContentQueryWrapper {
        static ContentQueryWrapper a(Context context, Uri uri) {
            return new ContentQueryWrapperApi24Impl(context, uri);
        }

        Cursor b(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);

        void close();
    }

    private static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {

        /* renamed from: a, reason: collision with root package name */
        private final ContentProviderClient f3136a;

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor b(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.f3136a;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e2) {
                Log.w("FontsProvider", "Unable to query the content provider", e2);
                return null;
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient = this.f3136a;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }
    }

    @RequiresApi
    private static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {

        /* renamed from: a, reason: collision with root package name */
        private final ContentProviderClient f3137a;

        ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.f3137a = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public Cursor b(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.f3137a;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e2) {
                Log.w("FontsProvider", "Unable to query the content provider", e2);
                return null;
            }
        }

        @Override // androidx.core.provider.FontProvider.ContentQueryWrapper
        public void close() {
            ContentProviderClient contentProviderClient = this.f3137a;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        }
    }

    private static List b(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    private static boolean c(List list, List list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!Arrays.equals((byte[]) list.get(i2), (byte[]) list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private static List d(FontRequest fontRequest, Resources resources) {
        return fontRequest.b() != null ? fontRequest.b() : FontResourcesParserCompat.c(resources, fontRequest.c());
    }

    static FontsContractCompat.FontFamilyResult e(Context context, FontRequest fontRequest, CancellationSignal cancellationSignal) {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        return provider == null ? FontsContractCompat.FontFamilyResult.a(1, null) : FontsContractCompat.FontFamilyResult.a(0, query(context, fontRequest, provider.authority, cancellationSignal));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int f(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            byte b3 = bArr2[i2];
            if (b2 != b3) {
                return b2 - b3;
            }
        }
        return 0;
    }

    @Nullable
    @VisibleForTesting
    static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) {
        String e2 = fontRequest.e();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(e2, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + e2);
        }
        if (!resolveContentProvider.packageName.equals(fontRequest.f())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + e2 + ", but package was not " + fontRequest.f());
        }
        List b2 = b(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
        Collections.sort(b2, f3135a);
        List d2 = d(fontRequest, resources);
        for (int i2 = 0; i2 < d2.size(); i2++) {
            ArrayList arrayList = new ArrayList((Collection) d2.get(i2));
            Collections.sort(arrayList, f3135a);
            if (c(b2, arrayList)) {
                return resolveContentProvider;
            }
        }
        return null;
    }

    @NonNull
    @VisibleForTesting
    static FontsContractCompat.FontInfo[] query(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        ArrayList arrayList;
        Uri withAppendedId;
        boolean z;
        ArrayList arrayList2 = new ArrayList();
        Uri build = new Uri.Builder().scheme("content").authority(str).build();
        Uri build2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        ContentQueryWrapper a2 = ContentQueryWrapper.a(context, build);
        Cursor cursor = null;
        try {
            cursor = a2.b(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest.g()}, null, cancellationSignal);
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex("result_code");
                ArrayList arrayList3 = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex("file_id");
                int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursor.getColumnIndex("font_weight");
                int columnIndex6 = cursor.getColumnIndex("font_italic");
                while (cursor.moveToNext()) {
                    int i2 = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i3 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        arrayList = arrayList3;
                        withAppendedId = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        arrayList = arrayList3;
                        withAppendedId = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    int i4 = columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400;
                    if (columnIndex6 != -1) {
                        z = true;
                        if (cursor.getInt(columnIndex6) == 1) {
                            FontsContractCompat.FontInfo a3 = FontsContractCompat.FontInfo.a(withAppendedId, i3, i4, z, i2);
                            arrayList3 = arrayList;
                            arrayList3.add(a3);
                        }
                    }
                    z = false;
                    FontsContractCompat.FontInfo a32 = FontsContractCompat.FontInfo.a(withAppendedId, i3, i4, z, i2);
                    arrayList3 = arrayList;
                    arrayList3.add(a32);
                }
                arrayList2 = arrayList3;
            }
            if (cursor != null) {
                cursor.close();
            }
            a2.close();
            return (FontsContractCompat.FontInfo[]) arrayList2.toArray(new FontsContractCompat.FontInfo[0]);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            a2.close();
            throw th;
        }
    }
}
