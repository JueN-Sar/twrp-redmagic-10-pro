package com.zte.gameassist.common;

import android.os.Bundle;
import com.zte.gameassist.AbsGameAssistToken;

/* loaded from: classes2.dex */
public interface IGameAssistCommander extends AbsGameAssistToken.ICommander {
    @Override // com.zte.gameassist.AbsGameAssistToken.ICommander
    default void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
    }
}
