package com.example.javaprojectfophone;

import android.content.Context;

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

    void setContext(Context context);
}
