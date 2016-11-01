package com.postnov.android.tfnews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.postnov.android.tfnews.util.NavigationManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by platon on 01.11.2016.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected NavigationManager navigationManager;

    protected abstract int getLayout();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationManager) {
            navigationManager = (NavigationManager) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) unbinder.unbind();
    }
}
