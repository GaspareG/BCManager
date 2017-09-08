package re.gaspa.bcmanager.ui.fragments;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

public class Home extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private FragmentHomeBinding mBinding;
    private BusinessCardAdapter mBcAdapter;

    public Home() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

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

        mBinding.fab.setOnClickListener(this);

        return mBinding.getRoot();
    }

    @Override
    public void onClick(View view) {

        if( view.getId() != R.id.fab ) return;

        final CharSequence[] items = {
                "Rajesh", "Mahesh", "Vijayakumar"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Condividi il tuo profilo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                //mDoneButton.setText(items[item]);
            }
        });
        builder.create().show();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        mBcAdapter.setBusinessCardItems( Database.getBusinessCards(false, query) );
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mBcAdapter.setBusinessCardItems( Database.getBusinessCards(false, newText) );
        return true;
    }

    @Override
    public boolean onClose() {
        mBcAdapter.setBusinessCardItems( Database.getBusinessCards() );
        return true;
    }
}
