package com.zte.mifavor.widget;

/* loaded from: classes2.dex */
public interface ISpringView {
    default boolean getIsBeingDragged() {
        return false;
    }

    default boolean getUseSpring() {
        return false;
    }

    default boolean isCollapsed() {
        return false;
    }

    default boolean isDisableSink() {
        return false;
    }

    default boolean isSupportSink() {
        return false;
    }
}
