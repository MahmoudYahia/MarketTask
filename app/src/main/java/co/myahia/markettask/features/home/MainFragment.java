package co.myahia.markettask.features.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.myahia.markettask.R;
import co.myahia.markettask.data.local.LocProduct;
import co.myahia.markettask.viewModel.ViewModelFactory;
import dagger.android.support.DaggerFragment;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
public class MainFragment extends DaggerFragment implements ProductsAdapter.ProductClickListener {

    private int CURRENT_VIEW;
    private final int VIEW_LIST = 10;
    private final int VIEW_DETAILS = 20;


    @BindView(R.id.screen_title_tv)
    TextView screenTitleTv;

    @BindView(R.id.products_rv)
    RecyclerView productsRv;
    @BindView(R.id.detail_layout)
    ViewGroup detailsLayout;
    @BindView(R.id.detail_img)
    ImageView detailsImg;
    @BindView(R.id.detail_price)
    TextView detailsPriceTv;
    @BindView(R.id.detail_description)
    TextView detailsDescTv;
    @BindView(R.id.detail_title)
    TextView detailsTitleTv;

    @Inject
    ViewModelFactory viewModelFactory;


    private MainViewModel mMainViewModel;
    private ProductsAdapter mProductsAdapter;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        observeProductsModel();

        checkInternetState();
    }

    private void checkInternetState() {
        mMainViewModel.getInternetState().observe(this,
                this::presentInternetState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        CURRENT_VIEW = VIEW_LIST;
        screenTitleTv.setText(R.string.product_list_title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        productsRv.setLayoutManager(layoutManager);
        mProductsAdapter = new ProductsAdapter(this);
        productsRv.setAdapter(mProductsAdapter);
        return view;
    }

    @OnClick(R.id.back_img_btn)
    public void onBackClicked() {

        switch (CURRENT_VIEW) {
            case VIEW_LIST: {
                if (getActivity() != null) getActivity().finish();
                break;
            }
            case VIEW_DETAILS: {
                setViewDetailsVisibility(false);
                setViewListVisibility(true);
                CURRENT_VIEW = VIEW_LIST;
                break;
            }
        }
    }

    private void setViewListVisibility(boolean isVisible) {
        if (isVisible) {
            screenTitleTv.setText(R.string.product_list_title);
            productsRv.setVisibility(View.VISIBLE);
        } else {
            productsRv.setVisibility(View.GONE);
        }
    }

    private void setViewDetailsVisibility(boolean isVisible) {
        if (isVisible) {

            detailsLayout.setVisibility(View.VISIBLE);
        } else {
            detailsLayout.setVisibility(View.GONE);
        }
    }


    private void observeProductsModel() {
        mMainViewModel.getAllProducts().observe(this,
                apiProducts -> {
                    mProductsAdapter.setProductList(apiProducts);
                });
    }


    @Override
    public void onProductClicked(LocProduct product) {
        CURRENT_VIEW = VIEW_DETAILS;
        setViewListVisibility(false);
        setViewDetailsVisibility(true);
        bindProductDetails(product);
    }

    private void presentInternetState(boolean state) {
        int backColor = (state ? R.color.colorSuccess : android.R.color.holo_red_light);
        int text = (state ? R.string.Internet_connected : R.string.Internet_disconnected);
        View parentLayout = getActivity().findViewById(android.R.id.content);

        Snackbar snackbar = Snackbar.make(parentLayout, text, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", view -> {
                });

        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getActivity(), backColor));
        snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
        snackbar.show();
    }

    private void bindProductDetails(LocProduct product) {
        screenTitleTv.setText(product.getTitle());
        detailsTitleTv.setText(product.getTitle());
        detailsPriceTv.setText(String.valueOf(product.getPrice() + " $"));
        detailsDescTv.setText(product.getDesc());

        Glide.with(getContext())
                .load(product.getImgLink())
                .into(detailsImg);

    }
}
