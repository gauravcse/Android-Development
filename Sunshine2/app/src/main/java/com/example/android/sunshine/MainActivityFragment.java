package com.example.android.sunshine;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * Created by Gaurav Mitra on 27-11-2015.
 */
public interface MainActivityFragment {
    void OnCreate(Bundle savedInstanceState);

    void OnCreateOptionsMenu(Menu menu, MenuInflater inflater);
}
