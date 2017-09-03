package re.gaspa.bcmanager.ui.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.BcCardBinding;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.ui.viewholders.BusinessCardVH;

/**
 * Created by gaspare on 02/09/17.
 */

public class BusinessCardAdapter extends RecyclerView.Adapter<BusinessCardVH> implements android.widget.Toolbar.OnMenuItemClickListener {

    private Context mContext;
    private ArrayList<BusinessCard> mBusinessCardItems;

    public BusinessCardAdapter(Context mContext) {
        this.mContext = mContext;
        mBusinessCardItems = new ArrayList<>();
    }

    public ArrayList<BusinessCard> getBusinessCardItems() {
        return mBusinessCardItems;
    }

    public void setBusinessCardItems(ArrayList<BusinessCard> mBusinessCardItems) {
        this.mBusinessCardItems = mBusinessCardItems;
        notifyDataSetChanged();
    }

    @Override
    public BusinessCardVH onCreateViewHolder(ViewGroup parent, int viewType) {
        BcCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext),
                R.layout.bc_card, parent, false);
        return new BusinessCardVH(binding);
    }

    @Override
    public void onBindViewHolder(BusinessCardVH holder, int position) {
        BcCardBinding binding = holder.getBinding();
        BusinessCard item = mBusinessCardItems.get(position);
        binding.setBusinessCard(item);

        binding.cardToolbar.inflateMenu(R.menu.home_menu);
        binding.cardToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mBusinessCardItems != null ? mBusinessCardItems.size() : 0;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // TODO menuItem
        return false;
    }
}
