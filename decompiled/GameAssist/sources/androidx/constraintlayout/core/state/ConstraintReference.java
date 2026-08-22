package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.State;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ConstraintReference implements Reference {

    /* renamed from: androidx.constraintlayout.core.state.ConstraintReference$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1902a;

        static {
            int[] iArr = new int[State.Constraint.values().length];
            f1902a = iArr;
            try {
                iArr[State.Constraint.LEFT_TO_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1902a[State.Constraint.LEFT_TO_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1902a[State.Constraint.RIGHT_TO_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1902a[State.Constraint.RIGHT_TO_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1902a[State.Constraint.START_TO_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1902a[State.Constraint.START_TO_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1902a[State.Constraint.END_TO_START.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1902a[State.Constraint.END_TO_END.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1902a[State.Constraint.TOP_TO_TOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1902a[State.Constraint.TOP_TO_BOTTOM.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1902a[State.Constraint.TOP_TO_BASELINE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1902a[State.Constraint.BOTTOM_TO_TOP.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f1902a[State.Constraint.BOTTOM_TO_BOTTOM.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f1902a[State.Constraint.BOTTOM_TO_BASELINE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f1902a[State.Constraint.BASELINE_TO_BOTTOM.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f1902a[State.Constraint.BASELINE_TO_TOP.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f1902a[State.Constraint.BASELINE_TO_BASELINE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f1902a[State.Constraint.CIRCULAR_CONSTRAINT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f1902a[State.Constraint.CENTER_HORIZONTALLY.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f1902a[State.Constraint.CENTER_VERTICALLY.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    public interface ConstraintReferenceFactory {
    }

    static class IncorrectConstraintException extends Exception {
        private final ArrayList<String> mErrors;

        IncorrectConstraintException(ArrayList<String> arrayList) {
            this.mErrors = arrayList;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return toString();
        }

        @Override // java.lang.Throwable
        public String toString() {
            return "IncorrectConstraintException: " + this.mErrors.toString();
        }
    }
}
