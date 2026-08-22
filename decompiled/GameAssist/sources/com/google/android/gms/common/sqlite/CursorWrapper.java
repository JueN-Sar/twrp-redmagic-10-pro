package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {

    /* renamed from: c, reason: collision with root package name */
    private AbstractWindowedCursor f11236c;

    @Override // android.database.CrossProcessCursor
    public void fillWindow(int i2, CursorWindow cursorWindow) {
        this.f11236c.fillWindow(i2, cursorWindow);
    }

    @Override // android.database.CrossProcessCursor
    public CursorWindow getWindow() {
        return this.f11236c.getWindow();
    }

    @Override // android.database.CursorWrapper
    public final /* synthetic */ Cursor getWrappedCursor() {
        return this.f11236c;
    }

    @Override // android.database.CrossProcessCursor
    public final boolean onMove(int i2, int i3) {
        return this.f11236c.onMove(i2, i3);
    }
}
