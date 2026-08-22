package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@ShowFirstParty
@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zaa();

    /* renamed from: c, reason: collision with root package name */
    final int f10887c;

    /* renamed from: h, reason: collision with root package name */
    ParcelFileDescriptor f10888h;

    /* renamed from: i, reason: collision with root package name */
    final int f10889i;

    /* renamed from: j, reason: collision with root package name */
    private Bitmap f10890j = null;

    /* renamed from: k, reason: collision with root package name */
    private boolean f10891k = false;

    /* renamed from: l, reason: collision with root package name */
    private File f10892l;

    BitmapTeleporter(int i2, ParcelFileDescriptor parcelFileDescriptor, int i3) {
        this.f10887c = i2;
        this.f10888h = parcelFileDescriptor;
        this.f10889i = i3;
    }

    private static final void G(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e2) {
            Log.w("BitmapTeleporter", "Could not close stream", e2);
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        if (this.f10888h == null) {
            Bitmap bitmap = (Bitmap) Preconditions.i(this.f10890j);
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            File file = this.f10892l;
            if (file == null) {
                throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
            }
            try {
                File createTempFile = File.createTempFile("teleporter", ".tmp", file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                    this.f10888h = ParcelFileDescriptor.open(createTempFile, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
                    createTempFile.delete();
                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                    try {
                        try {
                            dataOutputStream.writeInt(array.length);
                            dataOutputStream.writeInt(bitmap.getWidth());
                            dataOutputStream.writeInt(bitmap.getHeight());
                            dataOutputStream.writeUTF(bitmap.getConfig().toString());
                            dataOutputStream.write(array);
                        } catch (IOException e2) {
                            throw new IllegalStateException("Could not write into unlinked file", e2);
                        }
                    } finally {
                        G(dataOutputStream);
                    }
                } catch (FileNotFoundException unused) {
                    throw new IllegalStateException("Temporary file is somehow already deleted");
                }
            } catch (IOException e3) {
                throw new IllegalStateException("Could not create temporary file", e3);
            }
        }
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f10887c);
        SafeParcelWriter.l(parcel, 2, this.f10888h, i2 | 1, false);
        SafeParcelWriter.g(parcel, 3, this.f10889i);
        SafeParcelWriter.b(parcel, a2);
        this.f10888h = null;
    }
}
