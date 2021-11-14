package com.academiasmoviles.appacademia.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academiasmoviles.appacademia.R;

public class AyudaFragment extends Fragment {

    public static AyudaFragment newInstance()
    {
        AyudaFragment fragment = new AyudaFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ayuda, container, false);
    }
}
