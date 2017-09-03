package re.gaspa.bcmanager.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import re.gaspa.bcmanager.databinding.BcCardBinding;

/**
 * Created by gaspare on 02/09/17.
 */

public class BusinessCardVH extends RecyclerView.ViewHolder {

    private BcCardBinding binding;

    public BusinessCardVH(BcCardBinding binding) {
        super(binding.getRoot());
        this.setBinding(binding);
    }

    public BcCardBinding getBinding() {
        return binding;
    }

    public void setBinding(BcCardBinding bcCardBinding) {
        this.binding = bcCardBinding;
    }
}
