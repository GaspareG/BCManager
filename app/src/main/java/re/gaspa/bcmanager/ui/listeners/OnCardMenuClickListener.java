package re.gaspa.bcmanager.ui.listeners;

import android.content.Context;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.asynctask.AsyncLoadDatabase;
import re.gaspa.bcmanager.ui.adapters.BusinessCardAdapter;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Database;

/**
 * Created by gaspare on 08/09/17.
 */

public class OnCardMenuClickListener implements Toolbar.OnMenuItemClickListener
{
    private Context context;
    private BusinessCard businessCard;
    private BusinessCardAdapter businessCardAdapter;

    public OnCardMenuClickListener(BusinessCardAdapter businessCardAdapter, Context ctx, BusinessCard item) {
        this.businessCardAdapter = businessCardAdapter;
        this.businessCard = item;
        this.context = ctx;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();

        menuItem.getMenuInfo();

        if( id == R.id.delete )
        {
            Database.deleteBusinessCard(businessCard);
            Toast.makeText(context, "Cancello " + businessCard.getNome(), Toast.LENGTH_LONG).show();
            this.businessCardAdapter.setBusinessCardItems(Database.getBusinessCards());
         //   new AsyncLoadDatabase(null, this, false, search, preferite);
        }
        else if( id == R.id.preferite )
        {
            menuItem.setChecked( !menuItem.isChecked() );
            Database.setPreferite(businessCard, menuItem.isChecked() );
            Toast.makeText(context, "Preferito " + businessCard.getNome(), Toast.LENGTH_LONG).show();
        }

        return true;
    }
}
