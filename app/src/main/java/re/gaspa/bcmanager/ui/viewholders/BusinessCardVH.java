package re.gaspa.bcmanager.ui.viewholders;

import android.support.v7.widget.RecyclerView;

import re.gaspa.bcmanager.databinding.CardviewBcBinding;

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
