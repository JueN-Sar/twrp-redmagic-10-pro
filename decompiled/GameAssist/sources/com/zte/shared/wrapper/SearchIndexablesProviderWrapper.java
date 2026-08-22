package com.zte.shared.wrapper;

import android.annotation.Nullable;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/* loaded from: classes2.dex */
public abstract class SearchIndexablesProviderWrapper extends ContentProvider {
    private static final int MATCH_DYNAMIC_RAW_CODE = 6;
    private static final int MATCH_NON_INDEXABLE_KEYS_CODE = 3;
    private static final int MATCH_RAW_CODE = 2;
    private static final int MATCH_RES_CODE = 1;
    private static final int MATCH_SITE_MAP_PAIRS_CODE = 4;
    private static final int MATCH_SLICE_URI_PAIRS_CODE = 5;
    private static final String TAG = "SearchIndexablesProviderWrapper";
    private String mAuthority;
    private UriMatcher mMatcher;

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        this.mAuthority = providerInfo.authority;
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.mMatcher = uriMatcher;
        uriMatcher.addURI(this.mAuthority, SearchIndexablesContractWrapper.INDEXABLES_XML_RES_PATH, 1);
        this.mMatcher.addURI(this.mAuthority, SearchIndexablesContractWrapper.INDEXABLES_RAW_PATH, 2);
        this.mMatcher.addURI(this.mAuthority, SearchIndexablesContractWrapper.NON_INDEXABLES_KEYS_PATH, 3);
        this.mMatcher.addURI(this.mAuthority, SearchIndexablesContractWrapper.SITE_MAP_PAIRS_PATH, 4);
        this.mMatcher.addURI(this.mAuthority, SearchIndexablesContractWrapper.SLICE_URI_PAIRS_PATH, 5);
        this.mMatcher.addURI(this.mAuthority, SearchIndexablesContractWrapper.DYNAMIC_INDEXABLES_RAW_PATH, 6);
        if (!providerInfo.exported) {
            throw new SecurityException("Provider must be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grantUriPermissions");
        }
        if (!"android.permission.READ_SEARCH_INDEXABLES".equals(providerInfo.readPermission)) {
            throw new SecurityException("Provider must be protected by READ_SEARCH_INDEXABLES");
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Delete not supported");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int match = this.mMatcher.match(uri);
        if (match == 1) {
            return "vnd.android.cursor.dir/indexables_xml_res";
        }
        if (match == 2) {
            return "vnd.android.cursor.dir/indexables_raw";
        }
        if (match == 3) {
            return "vnd.android.cursor.dir/non_indexables_key";
        }
        if (match == 6) {
            return "vnd.android.cursor.dir/indexables_raw";
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Insert not supported");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            switch (this.mMatcher.match(uri)) {
                case 1:
                    return queryXmlResources(null);
                case 2:
                    return queryRawData(null);
                case 3:
                    return queryNonIndexableKeys(null);
                case 4:
                    return querySiteMapPairs();
                case 5:
                    return querySliceUriPairs();
                case 6:
                    return queryDynamicRawData(null);
                default:
                    throw new UnsupportedOperationException("Unknown Uri " + uri);
            }
        } catch (UnsupportedOperationException e2) {
            throw e2;
        } catch (Exception e3) {
            Log.e(TAG, "Provider querying exception:", e3);
            return null;
        }
    }

    @Nullable
    public Cursor queryDynamicRawData(@Nullable String[] strArr) {
        return null;
    }

    public abstract Cursor queryNonIndexableKeys(String[] strArr);

    public abstract Cursor queryRawData(String[] strArr);

    public Cursor querySiteMapPairs() {
        return null;
    }

    @Nullable
    public Cursor querySliceUriPairs() {
        return null;
    }

    public abstract Cursor queryXmlResources(String[] strArr);

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("Update not supported");
    }
}
