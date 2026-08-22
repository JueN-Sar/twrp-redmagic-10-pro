package com.google.errorprone.annotations.concurrent;

import com.google.errorprone.annotations.IncompatibleModifiers;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IncompatibleModifiers
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface LazyInit {
}
