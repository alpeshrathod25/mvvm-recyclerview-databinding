package com.universetelecom.mvvm_users.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alpesh on 18/2/18.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);

}
