package co.myahia.markettask.features.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.myahia.markettask.R;
import co.myahia.markettask.data.local.LocProduct;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private List<LocProduct> mProductList;
    private ProductClickListener mListener;

    public ProductsAdapter(ProductClickListener mListener) {
        this.mListener = mListener;
        mProductList = new ArrayList<>();
    }

    public void setProductList(List<LocProduct> list) {
        this.mProductList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int i) {
        holder.productTitleTv.setText(mProductList.get(holder.getAdapterPosition()).getTitle());
        holder.productPrice.setText(String.valueOf(mProductList.get(holder.getAdapterPosition()).getPrice()+" $"));

        holder.productImg.requestLayout();
        holder.productImg.getLayoutParams().height = Integer.parseInt(mProductList.get(holder.getAdapterPosition()).getImgHeight());
        holder.productImg.getLayoutParams().width = Integer.parseInt(mProductList.get(holder.getAdapterPosition()).getImgWidth());

        Glide.with(holder.itemView.getContext())
                .load(mProductList.get(holder.getAdapterPosition()).getImgLink())
                .into(holder.productImg);

        holder.itemView.setOnClickListener(v -> mListener.onProductClicked(mProductList.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_title)
        TextView productTitleTv;
        @BindView(R.id.product_price)
        TextView productPrice;
        @BindView(R.id.product_img)
        ImageView productImg;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

    public interface ProductClickListener {
        public void onProductClicked(LocProduct product);
    }
}
