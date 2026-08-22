package com.zte.mifavor.androidx.widget.swipe.touch;

import androidx.recyclerview.widget.ItemTouchHelper;

/* loaded from: classes2.dex */
public class DefaultItemTouchHelper extends ItemTouchHelper {
    private ItemTouchHelperCallback E;

    public DefaultItemTouchHelper() {
        this(new ItemTouchHelperCallback());
    }

    public OnItemMoveListener A() {
        return this.E.C();
    }

    public void B(boolean z) {
        this.E.D(z);
    }

    public void C(OnItemMoveListener onItemMoveListener) {
        this.E.E(onItemMoveListener);
    }

    public void D(OnItemMovementListener onItemMovementListener) {
        this.E.F(onItemMovementListener);
    }

    public void E(OnItemStateChangedListener onItemStateChangedListener) {
        this.E.G(onItemStateChangedListener);
    }

    private DefaultItemTouchHelper(ItemTouchHelperCallback itemTouchHelperCallback) {
        super(itemTouchHelperCallback);
        this.E = itemTouchHelperCallback;
    }
}
