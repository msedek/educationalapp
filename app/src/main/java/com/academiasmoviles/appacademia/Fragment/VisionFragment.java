package com.academiasmoviles.appacademia.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.academiasmoviles.appacademia.R;

public class VisionFragment extends Fragment {

    public static VisionFragment newInstance()
    {
        VisionFragment fragment = new VisionFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_vision, container, false);
    }
}
