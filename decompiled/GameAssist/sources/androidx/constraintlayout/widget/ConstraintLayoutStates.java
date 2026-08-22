package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class ConstraintLayoutStates {

    /* renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f2456a;

    /* renamed from: b, reason: collision with root package name */
    ConstraintSet f2457b;

    /* renamed from: c, reason: collision with root package name */
    int f2458c = -1;

    /* renamed from: d, reason: collision with root package name */
    int f2459d = -1;

    /* renamed from: e, reason: collision with root package name */
    private SparseArray f2460e = new SparseArray();

    /* renamed from: f, reason: collision with root package name */
    private SparseArray f2461f = new SparseArray();

    /* renamed from: g, reason: collision with root package name */
    private ConstraintsChangedListener f2462g = null;

    static class State {

        /* renamed from: a, reason: collision with root package name */
        int f2463a;

        /* renamed from: b, reason: collision with root package name */
        ArrayList f2464b = new ArrayList();

        /* renamed from: c, reason: collision with root package name */
        int f2465c;

        /* renamed from: d, reason: collision with root package name */
        ConstraintSet f2466d;

        State(Context context, XmlPullParser xmlPullParser) {
            this.f2465c = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.State_android_id) {
                    this.f2463a = obtainStyledAttributes.getResourceId(index, this.f2463a);
                } else if (index == R.styleable.State_constraints) {
                    this.f2465c = obtainStyledAttributes.getResourceId(index, this.f2465c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2465c);
                    context.getResources().getResourceName(this.f2465c);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.f2466d = constraintSet;
                        constraintSet.n(context, this.f2465c);
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }

        void a(Variant variant) {
            this.f2464b.add(variant);
        }

        public int b(float f2, float f3) {
            for (int i2 = 0; i2 < this.f2464b.size(); i2++) {
                if (((Variant) this.f2464b.get(i2)).a(f2, f3)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    static class Variant {

        /* renamed from: a, reason: collision with root package name */
        float f2467a;

        /* renamed from: b, reason: collision with root package name */
        float f2468b;

        /* renamed from: c, reason: collision with root package name */
        float f2469c;

        /* renamed from: d, reason: collision with root package name */
        float f2470d;

        /* renamed from: e, reason: collision with root package name */
        int f2471e;

        /* renamed from: f, reason: collision with root package name */
        ConstraintSet f2472f;

        Variant(Context context, XmlPullParser xmlPullParser) {
            this.f2467a = Float.NaN;
            this.f2468b = Float.NaN;
            this.f2469c = Float.NaN;
            this.f2470d = Float.NaN;
            this.f2471e = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Variant_constraints) {
                    this.f2471e = obtainStyledAttributes.getResourceId(index, this.f2471e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2471e);
                    context.getResources().getResourceName(this.f2471e);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.f2472f = constraintSet;
                        constraintSet.n(context, this.f2471e);
                    }
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.f2470d = obtainStyledAttributes.getDimension(index, this.f2470d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.f2468b = obtainStyledAttributes.getDimension(index, this.f2468b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.f2469c = obtainStyledAttributes.getDimension(index, this.f2469c);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.f2467a = obtainStyledAttributes.getDimension(index, this.f2467a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }

        boolean a(float f2, float f3) {
            if (!Float.isNaN(this.f2467a) && f2 < this.f2467a) {
                return false;
            }
            if (!Float.isNaN(this.f2468b) && f3 < this.f2468b) {
                return false;
            }
            if (Float.isNaN(this.f2469c) || f2 <= this.f2469c) {
                return Float.isNaN(this.f2470d) || f3 <= this.f2470d;
            }
            return false;
        }
    }

    ConstraintLayoutStates(Context context, ConstraintLayout constraintLayout, int i2) {
        this.f2456a = constraintLayout;
        a(context, i2);
    }

    private void a(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            int eventType = xml.getEventType();
            State state = null;
            while (true) {
                char c2 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 2) {
                    String name = xml.getName();
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c2 = 4;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                c2 = 2;
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
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                c2 = 0;
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
                        State state2 = new State(context, xml);
                        this.f2460e.put(state2.f2463a, state2);
                        state = state2;
                    } else if (c2 == 3) {
                        Variant variant = new Variant(context, xml);
                        if (state != null) {
                            state.a(variant);
                        }
                    } else if (c2 == 4) {
                        b(context, xml);
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e2) {
            Log.e("ConstraintLayoutStates", "Error parsing resource: " + i2, e2);
        } catch (XmlPullParserException e3) {
            Log.e("ConstraintLayoutStates", "Error parsing resource: " + i2, e3);
        }
    }

    private void b(Context context, XmlPullParser xmlPullParser) {
        ConstraintSet constraintSet = new ConstraintSet();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (attributeName != null && attributeValue != null && VirtualHandleWrapper.KEY_ID.equals(attributeName)) {
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), VirtualHandleWrapper.KEY_ID, context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                constraintSet.D(context, xmlPullParser);
                this.f2461f.put(identifier, constraintSet);
                return;
            }
        }
    }

    public void c(ConstraintsChangedListener constraintsChangedListener) {
        this.f2462g = constraintsChangedListener;
    }

    public void d(int i2, float f2, float f3) {
        int b2;
        int i3 = this.f2458c;
        if (i3 == i2) {
            State state = i2 == -1 ? (State) this.f2460e.valueAt(0) : (State) this.f2460e.get(i3);
            int i4 = this.f2459d;
            if ((i4 == -1 || !((Variant) state.f2464b.get(i4)).a(f2, f3)) && this.f2459d != (b2 = state.b(f2, f3))) {
                ConstraintSet constraintSet = b2 == -1 ? this.f2457b : ((Variant) state.f2464b.get(b2)).f2472f;
                int i5 = b2 == -1 ? state.f2465c : ((Variant) state.f2464b.get(b2)).f2471e;
                if (constraintSet == null) {
                    return;
                }
                this.f2459d = b2;
                ConstraintsChangedListener constraintsChangedListener = this.f2462g;
                if (constraintsChangedListener != null) {
                    constraintsChangedListener.b(-1, i5);
                }
                constraintSet.i(this.f2456a);
                ConstraintsChangedListener constraintsChangedListener2 = this.f2462g;
                if (constraintsChangedListener2 != null) {
                    constraintsChangedListener2.a(-1, i5);
                    return;
                }
                return;
            }
            return;
        }
        this.f2458c = i2;
        State state2 = (State) this.f2460e.get(i2);
        int b3 = state2.b(f2, f3);
        ConstraintSet constraintSet2 = b3 == -1 ? state2.f2466d : ((Variant) state2.f2464b.get(b3)).f2472f;
        int i6 = b3 == -1 ? state2.f2465c : ((Variant) state2.f2464b.get(b3)).f2471e;
        if (constraintSet2 == null) {
            Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i2 + ", dim =" + f2 + ", " + f3);
            return;
        }
        this.f2459d = b3;
        ConstraintsChangedListener constraintsChangedListener3 = this.f2462g;
        if (constraintsChangedListener3 != null) {
            constraintsChangedListener3.b(i2, i6);
        }
        constraintSet2.i(this.f2456a);
        ConstraintsChangedListener constraintsChangedListener4 = this.f2462g;
        if (constraintsChangedListener4 != null) {
            constraintsChangedListener4.a(i2, i6);
        }
    }
}
