package re.gaspa.bcmanager.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.utils.Utils;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Utils.getPreferences(this.getBaseContext());

    }
}
