package re.gaspa.bcmanager.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import re.gaspa.bcmanager.databinding.CardviewBcBinding;

/**
 * Created by gaspare on 02/09/17.
 */

public class BusinessCardVH extends RecyclerView.ViewHolder {

    private CardviewBcBinding binding;

    public BusinessCardVH(CardviewBcBinding binding) {
        super(binding.getRoot());
        this.setBinding(binding);
    }

    public CardviewBcBinding getBinding() {
        return binding;
    }

    public void setBinding(CardviewBcBinding bcCardBinding) {
        this.binding = bcCardBinding;
    }
}
