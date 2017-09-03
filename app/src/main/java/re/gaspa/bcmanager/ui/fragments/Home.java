package re.gaspa.bcmanager.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.FragmentHomeBinding;
import re.gaspa.bcmanager.ui.adapters.BusinessCardAdapter;
import re.gaspa.bcmanager.ui.models.BusinessCard;

/**
 * Created by gaspare on 28/08/17.
 */

public class Home extends Fragment {

    private FragmentHomeBinding mBinding;
    private BusinessCardAdapter mBcAdapter;

    public Home() {

    }

    public static Home newInstance() {
        Home fragmentFirst = new Home();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        FloatingActionButton fab = (FloatingActionButton) mBinding.fab ;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mBcAdapter = new BusinessCardAdapter(getContext());

        ArrayList<BusinessCard> lista = new ArrayList<>();

        BusinessCard item1 = new BusinessCard();
        BusinessCard item2 = new BusinessCard();
        BusinessCard item3 = new BusinessCard();
        BusinessCard item4 = new BusinessCard();

        item1.setNome("Gaspare Ferraro");
        item2.setNome("Paolo Paperino");
        item3.setNome("Vincenzo Gervasi");
        item4.setNome("Arrigo Pierroti");

        item1.setRuolo("Studente");
        item2.setRuolo("Buffone");
        item3.setRuolo("Professore");
        item4.setRuolo("Studente");

        item1.setColore("#F44336");
        item2.setColore("#2196F3");
        item3.setColore("#009688");
        item4.setColore("#FF9800");

        item1.setTelefono("+393926477802");
        item2.setTelefono("+393331234567");
        item3.setTelefono("+393338912345");
        item4.setTelefono("+393336789123");

        for(int i = 0 ; i < 100 ; i++) {
            lista.add(item1);
            lista.add(item2);
            lista.add(item3);
            lista.add(item4);
        }

        mBcAdapter.setBusinessCardItems(lista);

        mBinding.bcList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.bcList.setAdapter(mBcAdapter);

        return mBinding.getRoot();
    }
}
