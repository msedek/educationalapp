package com.academiasmoviles.appacademia.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academiasmoviles.appacademia.R;

public class AcercadeFragment extends Fragment {

    public static AcercadeFragment newInstance()
    {
        AcercadeFragment fragment = new AcercadeFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_acercade, container, false);
    }
}
