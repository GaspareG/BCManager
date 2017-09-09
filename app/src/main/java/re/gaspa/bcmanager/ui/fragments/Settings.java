package re.gaspa.bcmanager.ui.fragments;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.FragmentSettingBinding;
import re.gaspa.bcmanager.utils.Utils;


public class Settings extends Fragment implements View.OnClickListener {

    private FragmentSettingBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_setting, container, false);

        mBinding.layoutDeleteFavourite.setOnClickListener(this);
        mBinding.layoutDeleteProfile.setOnClickListener(this);
        mBinding.layoutDeleteContacts.setOnClickListener(this);
        mBinding.layoutDeleteAll.setOnClickListener(this);

        return mBinding.getRoot();
    }

    @Override
    public void onClick(final View view) {
        final int id = view.getId();

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Sei sicuro?");
        builder.setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (id == R.id.layout_delete_all) {
                    Utils.deleteAll();
                    Toast.makeText(mBinding.getRoot().getContext(), "Cancellati tutti i dati!", Toast.LENGTH_LONG).show();
                } else if (id == R.id.layout_delete_favourite) {
                    Utils.deletePreferite();
                    Toast.makeText(mBinding.getRoot().getContext(), "Cancellati tutti i preferiti!", Toast.LENGTH_LONG).show();
                } else if (id == R.id.layout_delete_profile) {
                    Utils.deleteProfile();
                    Toast.makeText(mBinding.getRoot().getContext(), "Cancellato il tuo profilo!", Toast.LENGTH_LONG).show();
                } else if (id == R.id.layout_delete_contacts) {
                    Utils.deleteContacts();
                    Toast.makeText(mBinding.getRoot().getContext(), "Cancellati tutti i contatti!", Toast.LENGTH_LONG).show();
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
