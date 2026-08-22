package com.zte.mifavor.custom.widget;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.zte.mifavor.custom.Config;
import com.zte.mifavor.widget.SearchViewZTE;
import com.zte.mifavor.widget.VoiceSearchViewZTE;

/* loaded from: classes2.dex */
public class SearchViewMiFavor {
    private static final String TAG = "Z#SearchViewMFV";

    public static boolean onUpdateSearchView_getCloseButtonIconState(Object[] objArr) {
        boolean z = false;
        if (Config.isMifavorTheme((Context) objArr[0])) {
            View view = (View) objArr[1];
            Log.d(TAG, "onUpdateSearchView_getCloseButtonIconState in. view=" + view);
            if (view instanceof VoiceSearchViewZTE) {
                z = true;
            }
        }
        Log.d(TAG, "onUpdateSearchView_getCloseButtonIconState out. ret=" + z);
        return z;
    }

    public static void onUpdateSearchView_updateColseButtonIcon(Object[] objArr) {
        if (Config.isMifavorTheme((Context) objArr[0])) {
            View view = (View) objArr[1];
            Log.d(TAG, "onUpdateSearchView_updateColseButtonIcon in. view=" + view);
            if (view instanceof SearchViewZTE) {
                return;
            }
            boolean z = view instanceof VoiceSearchViewZTE;
        }
    }
}
