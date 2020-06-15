package com.example.myapplication_visual_project;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

abstract class ListViewItemLingClickKistener extends Context implements android.widget.AdapterView.OnItemLongClickListener {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        View dialogView = (View) View.inflate(ListViewItemLingClickKistener.this, R.layout.dialog_month, null);
        return true;
    }
}
