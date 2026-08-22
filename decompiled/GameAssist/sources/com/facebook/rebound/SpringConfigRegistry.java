package com.facebook.rebound;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class SpringConfigRegistry {

    /* renamed from: b, reason: collision with root package name */
    private static final SpringConfigRegistry f10038b = new SpringConfigRegistry(true);

    /* renamed from: a, reason: collision with root package name */
    private final Map f10039a = new HashMap();

    SpringConfigRegistry(boolean z) {
        if (z) {
            a(SpringConfig.f10035c, "default config");
        }
    }

    public static SpringConfigRegistry c() {
        return f10038b;
    }

    public boolean a(SpringConfig springConfig, String str) {
        if (springConfig == null) {
            throw new IllegalArgumentException("springConfig is required");
        }
        if (str == null) {
            throw new IllegalArgumentException("configName is required");
        }
        if (this.f10039a.containsKey(springConfig)) {
            return false;
        }
        this.f10039a.put(springConfig, str);
        return true;
    }

    public Map b() {
        return Collections.unmodifiableMap(this.f10039a);
    }
}
