package androidx.lifecycle;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;

@Metadata
/* loaded from: classes.dex */
public final class ViewModelLazy<VM extends ViewModel> implements Lazy<VM> {

    /* renamed from: c, reason: collision with root package name */
    private final KClass f4395c;

    /* renamed from: h, reason: collision with root package name */
    private final Function0 f4396h;

    /* renamed from: i, reason: collision with root package name */
    private final Function0 f4397i;

    /* renamed from: j, reason: collision with root package name */
    private final Function0 f4398j;

    /* renamed from: k, reason: collision with root package name */
    private ViewModel f4399k;

    @Metadata
    /* renamed from: androidx.lifecycle.ViewModelLazy$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function0<CreationExtras.Empty> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public final CreationExtras.Empty a() {
            return CreationExtras.Empty.f4421b;
        }
    }

    @Override // kotlin.Lazy
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public ViewModel getValue() {
        ViewModel viewModel = this.f4399k;
        if (viewModel != null) {
            return viewModel;
        }
        ViewModel a2 = new ViewModelProvider((ViewModelStore) this.f4396h.a(), (ViewModelProvider.Factory) this.f4397i.a(), (CreationExtras) this.f4398j.a()).a(JvmClassMappingKt.a(this.f4395c));
        this.f4399k = a2;
        return a2;
    }
}
