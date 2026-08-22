package com.google.android.gms.tasks;

import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public final class DuplicateTaskCompletionException extends IllegalStateException {
    private DuplicateTaskCompletionException(String str, @Nullable Throwable th) {
        super(str, th);
    }

    public static IllegalStateException a(Task task) {
        if (!task.k()) {
            return new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
        }
        Exception h2 = task.h();
        return new DuplicateTaskCompletionException("Complete with: ".concat(h2 != null ? "failure" : task.l() ? "result ".concat(String.valueOf(task.i())) : task.j() ? "cancellation" : "unknown issue"), h2);
    }
}
