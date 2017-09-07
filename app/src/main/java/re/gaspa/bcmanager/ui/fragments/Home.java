package re.gaspa.bcmanager.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.FragmentHomeBinding;
import re.gaspa.bcmanager.ui.adapters.BusinessCardAdapter;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Database;

/**
 * Created by gaspare on 28/08/17.
 */

public class Home extends Fragment {

    private FragmentHomeBinding mBinding;
    private BusinessCardAdapter mBcAdapter;

    public Home() {

    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_home, container, false);

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mBcAdapter = new BusinessCardAdapter(getContext());
        mBcAdapter.setBusinessCardItems(Database.getBusinessCards());

        mBinding.bcList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.bcList.setAdapter(mBcAdapter);

        return mBinding.getRoot();
    }
}
