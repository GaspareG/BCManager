package re.gaspa.bcmanager.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.ActivityHelpBinding;
import re.gaspa.bcmanager.ui.fragments.EditProfile;
import re.gaspa.bcmanager.ui.fragments.HelpPage0;
import re.gaspa.bcmanager.ui.fragments.HelpPage1;

public class Help extends AppCompatActivity implements View.OnClickListener {

    private ActivityHelpBinding binding;
    private int page = 0;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_help);

        binding.buttonBack.setOnClickListener(this);
        binding.buttonForward.setOnClickListener(this);

        binding.buttonBack.setVisibility(View.INVISIBLE);

        loadFragment(HelpPage0.class);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if( id == R.id.button_back )
        {
            if( page == 1 )
            {
                page = 0;
                binding.buttonBack.setVisibility(View.INVISIBLE);
                loadFragment(HelpPage0.class);
            }
            else if( page == 2 )
            {
                page = 1;
                loadFragment(HelpPage1.class);
                binding.buttonForward.setText("Avanti");
            }


        }
        else if( id == R.id.button_forward )
        {
            if( page == 0 )
            {
                loadFragment(HelpPage1.class);
                binding.buttonBack.setVisibility(View.VISIBLE);
                page = 1 ;
            }
            else if( page == 1 )
            {
                loadFragment(EditProfile.class);
                page = 2 ;
            }
            else if( page == 2 )
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(Help.this);
                alert.setTitle("Sei sicuro?")
                .setMessage("Prima di proseguire ricorda di salvare il tuo profilo!")
                .setPositiveButton("Continua", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), Main.class);
                        getApplicationContext().startActivity(intent);
                    }
                }).setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }

        }
    }

    private void loadFragment(Class fragmentClass)
    {
        Fragment fragment;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragmentManager.beginTransaction().replace(R.id.fragment_help, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
