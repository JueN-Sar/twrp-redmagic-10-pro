package com.google.android.material.color;

import android.content.Context;
import android.content.res.loader.ResourcesLoader;
import android.content.res.loader.ResourcesProvider;
import android.os.ParcelFileDescriptor;
import android.system.Os;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.Map;

@RequiresApi
/* loaded from: classes.dex */
final class ColorResourcesLoaderCreator {
    static ResourcesLoader a(Context context, Map map) {
        FileDescriptor fileDescriptor;
        try {
            byte[] i2 = ColorResourcesTableCreator.i(context, map);
            Log.i("ColorResLoaderCreator", "Table created, length: " + i2.length);
            if (i2.length == 0) {
                return null;
            }
            try {
                fileDescriptor = Os.memfd_create("temp.arsc", 0);
            } catch (Throwable th) {
                th = th;
                fileDescriptor = null;
            }
            try {
                if (fileDescriptor == null) {
                    Log.w("ColorResLoaderCreator", "Cannot create memory file descriptor.");
                    if (fileDescriptor != null) {
                        Os.close(fileDescriptor);
                    }
                    return null;
                }
                FileOutputStream fileOutputStream = new FileOutputStream(fileDescriptor);
                try {
                    fileOutputStream.write(i2);
                    ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
                    try {
                        ResourcesLoader resourcesLoader = new ResourcesLoader();
                        resourcesLoader.addProvider(ResourcesProvider.loadFromTable(dup, null));
                        if (dup != null) {
                            dup.close();
                        }
                        fileOutputStream.close();
                        Os.close(fileDescriptor);
                        return resourcesLoader;
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileDescriptor != null) {
                    Os.close(fileDescriptor);
                }
                throw th;
            }
        } catch (Exception e2) {
            Log.e("ColorResLoaderCreator", "Failed to create the ColorResourcesTableCreator.", e2);
            return null;
        }
    }
}
