package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class KeyFrames {

    /* renamed from: b, reason: collision with root package name */
    static HashMap f2156b;

    /* renamed from: a, reason: collision with root package name */
    private HashMap f2157a = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        f2156b = hashMap;
        try {
            hashMap.put("KeyAttribute", KeyAttributes.class.getConstructor(null));
            f2156b.put("KeyPosition", KeyPosition.class.getConstructor(null));
            f2156b.put("KeyCycle", KeyCycle.class.getConstructor(null));
            f2156b.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(null));
            f2156b.put("KeyTrigger", KeyTrigger.class.getConstructor(null));
        } catch (NoSuchMethodException e2) {
            Log.e("KeyFrames", "unable to load", e2);
        }
    }

    public KeyFrames() {
    }

    public void a(MotionController motionController) {
        ArrayList arrayList = (ArrayList) this.f2157a.get(-1);
        if (arrayList != null) {
            motionController.b(arrayList);
        }
    }

    public void b(MotionController motionController) {
        ArrayList arrayList = (ArrayList) this.f2157a.get(Integer.valueOf(motionController.f2215c));
        if (arrayList != null) {
            motionController.b(arrayList);
        }
        ArrayList arrayList2 = (ArrayList) this.f2157a.get(-1);
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                Key key = (Key) it.next();
                if (key.f(((ConstraintLayout.LayoutParams) motionController.f2214b.getLayoutParams()).c0)) {
                    motionController.a(key);
                }
            }
        }
    }

    public void c(Key key) {
        if (!this.f2157a.containsKey(Integer.valueOf(key.f2124b))) {
            this.f2157a.put(Integer.valueOf(key.f2124b), new ArrayList());
        }
        ArrayList arrayList = (ArrayList) this.f2157a.get(Integer.valueOf(key.f2124b));
        if (arrayList != null) {
            arrayList.add(key);
        }
    }

    public ArrayList d(int i2) {
        return (ArrayList) this.f2157a.get(Integer.valueOf(i2));
    }

    public KeyFrames(Context context, XmlPullParser xmlPullParser) {
        HashMap hashMap;
        HashMap hashMap2;
        char c2;
        Key keyAttributes;
        try {
            int eventType = xmlPullParser.getEventType();
            Key key = null;
            while (eventType != 1) {
                if (eventType != 2) {
                    if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                        return;
                    }
                } else {
                    String name = xmlPullParser.getName();
                    if (f2156b.containsKey(name)) {
                        switch (name.hashCode()) {
                            case -300573030:
                                if (name.equals("KeyTimeCycle")) {
                                    c2 = 3;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case -298435811:
                                if (name.equals("KeyAttribute")) {
                                    c2 = 0;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 540053991:
                                if (name.equals("KeyCycle")) {
                                    c2 = 2;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 1153397896:
                                if (name.equals("KeyPosition")) {
                                    c2 = 1;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            case 1308496505:
                                if (name.equals("KeyTrigger")) {
                                    c2 = 4;
                                    break;
                                }
                                c2 = 65535;
                                break;
                            default:
                                c2 = 65535;
                                break;
                        }
                        if (c2 == 0) {
                            keyAttributes = new KeyAttributes();
                        } else if (c2 == 1) {
                            keyAttributes = new KeyPosition();
                        } else if (c2 == 2) {
                            keyAttributes = new KeyCycle();
                        } else if (c2 == 3) {
                            keyAttributes = new KeyTimeCycle();
                        } else if (c2 == 4) {
                            keyAttributes = new KeyTrigger();
                        } else {
                            throw new NullPointerException("Key " + name + " not found");
                        }
                        keyAttributes.e(context, Xml.asAttributeSet(xmlPullParser));
                        c(keyAttributes);
                        key = keyAttributes;
                    } else if (name.equalsIgnoreCase("CustomAttribute")) {
                        if (key != null && (hashMap2 = key.f2127e) != null) {
                            ConstraintAttribute.i(context, xmlPullParser, hashMap2);
                        }
                    } else if (name.equalsIgnoreCase("CustomMethod") && key != null && (hashMap = key.f2127e) != null) {
                        ConstraintAttribute.i(context, xmlPullParser, hashMap);
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            Log.e("KeyFrames", "Error parsing XML resource", e2);
        } catch (XmlPullParserException e3) {
            Log.e("KeyFrames", "Error parsing XML resource", e3);
        }
    }
}
