package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class StateSet {

    /* renamed from: a, reason: collision with root package name */
    int f2563a = -1;

    /* renamed from: b, reason: collision with root package name */
    int f2564b = -1;

    /* renamed from: c, reason: collision with root package name */
    int f2565c = -1;

    /* renamed from: d, reason: collision with root package name */
    private SparseArray f2566d = new SparseArray();

    /* renamed from: e, reason: collision with root package name */
    private ConstraintsChangedListener f2567e = null;

    static class State {

        /* renamed from: a, reason: collision with root package name */
        int f2568a;

        /* renamed from: b, reason: collision with root package name */
        ArrayList f2569b = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        int f2570c;

        /* renamed from: d, reason: collision with root package name */
        boolean f2571d;

        State(Context context, XmlPullParser xmlPullParser) {
            this.f2570c = -1;
            this.f2571d = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.State_android_id) {
                    this.f2568a = obtainStyledAttributes.getResourceId(index, this.f2568a);
                } else if (index == R.styleable.State_constraints) {
                    this.f2570c = obtainStyledAttributes.getResourceId(index, this.f2570c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2570c);
                    context.getResources().getResourceName(this.f2570c);
                    if ("layout".equals(resourceTypeName)) {
                        this.f2571d = true;
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }

        void a(Variant variant) {
            this.f2569b.add(variant);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f2569b.size(); i2++) {
                if (((Variant) this.f2569b.get(i2)).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    static class Variant {

        /* renamed from: a, reason: collision with root package name */
        float f2572a;

        /* renamed from: b, reason: collision with root package name */
        float f2573b;

        /* renamed from: c, reason: collision with root package name */
        float f2574c;

        /* renamed from: d, reason: collision with root package name */
        float f2575d;

        /* renamed from: e, reason: collision with root package name */
        int f2576e;

        /* renamed from: f, reason: collision with root package name */
        boolean f2577f;

        Variant(Context context, XmlPullParser xmlPullParser) {
            this.f2572a = Float.NaN;
            this.f2573b = Float.NaN;
            this.f2574c = Float.NaN;
            this.f2575d = Float.NaN;
            this.f2576e = -1;
            this.f2577f = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Variant_constraints) {
                    this.f2576e = obtainStyledAttributes.getResourceId(index, this.f2576e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2576e);
                    context.getResources().getResourceName(this.f2576e);
                    if ("layout".equals(resourceTypeName)) {
                        this.f2577f = true;
                    }
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.f2575d = obtainStyledAttributes.getDimension(index, this.f2575d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.f2573b = obtainStyledAttributes.getDimension(index, this.f2573b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.f2574c = obtainStyledAttributes.getDimension(index, this.f2574c);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.f2572a = obtainStyledAttributes.getDimension(index, this.f2572a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f2572a) && f2 < this.f2572a) {
                return false;
            }
            if (!Float.isNaN(this.f2573b) && f3 < this.f2573b) {
                return false;
            }
            if (Float.isNaN(this.f2574c) || f2 <= this.f2574c) {
                return Float.isNaN(this.f2575d) || f3 <= this.f2575d;
            }
            return false;
        }
    }

    public StateSet(Context context, XmlPullParser xmlPullParser) {
        b(context, xmlPullParser);
    }

    private void b(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.StateSet);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.StateSet_defaultState) {
                this.f2563a = obtainStyledAttributes.getResourceId(index, this.f2563a);
            }
        }
        obtainStyledAttributes.recycle();
        try {
            int eventType = xmlPullParser.getEventType();
            State state = null;
            while (true) {
                char c2 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        default:
                            c2 = 65535;
                            break;
                    }
                    if (c2 == 2) {
                        state = new State(context, xmlPullParser);
                        this.f2566d.put(state.f2568a, state);
                    } else if (c2 == 3) {
                        Variant variant = new Variant(context, xmlPullParser);
                        if (state != null) {
                            state.a(variant);
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("StateSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            Log.e("ConstraintLayoutStates", "Error parsing XML resource", e2);
        } catch (XmlPullParserException e3) {
            Log.e("ConstraintLayoutStates", "Error parsing XML resource", e3);
        }
    }

    public int a(int i2, int i3, float f2, float f3) {
        State state = (State) this.f2566d.get(i3);
        if (state == null) {
            return i3;
        }
        if (f2 == -1.0f || f3 == -1.0f) {
            if (state.f2570c == i2) {
                return i2;
            }
            Iterator it = state.f2569b.iterator();
            while (it.hasNext()) {
                if (i2 == ((Variant) it.next()).f2576e) {
                    return i2;
                }
            }
            return state.f2570c;
        }
        Iterator it2 = state.f2569b.iterator();
        Variant variant = null;
        while (it2.hasNext()) {
            Variant variant2 = (Variant) it2.next();
            if (variant2.a(f2, f3)) {
                if (i2 == variant2.f2576e) {
                    return i2;
                }
                variant = variant2;
            }
        }
        return variant != null ? variant.f2576e : state.f2570c;
    }

    public int c(int i2, int i3, int i4) {
        return d(-1, i2, i3, i4);
    }

    public int d(int i2, int i3, float f2, float f3) {
        int b2;
        if (i2 == i3) {
            State state = i3 == -1 ? (State) this.f2566d.valueAt(0) : (State) this.f2566d.get(this.f2564b);
            if (state == null) {
                return -1;
            }
            return ((this.f2565c == -1 || !((Variant) state.f2569b.get(i2)).a(f2, f3)) && i2 != (b2 = state.b(f2, f3))) ? b2 == -1 ? state.f2570c : ((Variant) state.f2569b.get(b2)).f2576e : i2;
        }
        State state2 = (State) this.f2566d.get(i3);
        if (state2 == null) {
            return -1;
        }
        int b3 = state2.b(f2, f3);
        return b3 == -1 ? state2.f2570c : ((Variant) state2.f2569b.get(b3)).f2576e;
    }
}
