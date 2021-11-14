package com.academiasmoviles.appacademia.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academiasmoviles.appacademia.R;

public class MisionFragment extends Fragment {

    public static MisionFragment newInstance()
    {
        MisionFragment fragment = new MisionFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mision, container, false);
    }
}
