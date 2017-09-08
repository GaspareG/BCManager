package re.gaspa.bcmanager.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.CardviewBcBinding;
import re.gaspa.bcmanager.ui.activities.BusinessCardActivity;
import re.gaspa.bcmanager.ui.listeners.CardMenuClickListener;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.ui.viewholders.BusinessCardVH;
import re.gaspa.bcmanager.utils.Utils;

/**
 * Created by gaspare on 02/09/17.
 */

public class BusinessCardAdapter extends RecyclerView.Adapter<BusinessCardVH> implements View.OnClickListener {

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
        CardviewBcBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext),
                R.layout.cardview_bc, parent, false);
        return new BusinessCardVH(binding);
    }

    @Override
    public void onBindViewHolder(BusinessCardVH holder, int position) {
        CardviewBcBinding binding = holder.getBinding();
        BusinessCard item = mBusinessCardItems.get(position);
        binding.setBusinessCard(item);

        binding.cardView.setTag(item);
        binding.cardView.setOnClickListener(this);

        binding.cardToolbar.getMenu().clear();
        binding.cardToolbar.inflateMenu(R.menu.menu_card);

        MenuItem preferiteItem = binding.cardToolbar.getMenu().getItem(2);
        preferiteItem.setChecked( item.getPreferito() );

        binding.cardToolbar.setOnMenuItemClickListener(new CardMenuClickListener(binding.getRoot().getContext(), item));

        final String nome = item.getNome();
        final String colore = item.getColore();
        final String ruolo = item.getLavoroRuolo();
        final String numero = item.getTelefono();
        final Bitmap sfondo = item.getSfondo();
        final Bitmap profilo = item.getProfilo();

        if (nome != null && nome.length() > 0)
            binding.textName.setText(nome);

        if (ruolo != null && ruolo.length() > 0)
            binding.textRole.setText(ruolo);

        if (colore != null && colore.length() > 0) {
            int background = Color.parseColor(colore);

            binding.fabCall.setBackgroundTintList(ColorStateList.valueOf(background));
            binding.fabMessage.setBackgroundTintList(ColorStateList.valueOf(background));

            binding.profileImage.setBorderColor(background);
            binding.textName.setTextColor(background);
        }

        if (sfondo != null)
            binding.backgroundImage.setImageBitmap(sfondo);

        if (profilo != null)
            binding.profileImage.setImageBitmap(profilo);

        if (numero != null && numero.length() > 0) {
            binding.fabCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.openNumber(numero, view.getContext());
                }
            });

            binding.fabMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.openMessage(numero, view.getContext());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mBusinessCardItems != null ? mBusinessCardItems.size() : 0;
    }

    @Override
    public void onClick(View view) {
        BusinessCard obj = (BusinessCard) view.getTag();
        Toast.makeText(view.getContext(), "TOCCATO" + obj.getNome(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(view.getContext(), BusinessCardActivity.class);
        intent.putExtra("businesscard", obj);
        view.getContext().startActivity(intent);
    }
}
