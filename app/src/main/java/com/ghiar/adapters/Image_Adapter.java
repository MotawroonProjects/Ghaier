package com.ghiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.ghiar.R;
import com.ghiar.activities_fragments.activtity_auction_detials.AuctionDetialsActivity;
import com.ghiar.databinding.AuctionImageRowBinding;
import com.ghiar.databinding.AuctionRowBinding;
import com.ghiar.models.SingleAuctionModel;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Image_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SingleAuctionModel.Images> orderlist;
    private Context context;
    private LayoutInflater inflater;
    private String lang;
    public Image_Adapter(List<SingleAuctionModel.Images> orderlist, Context context) {
        this.orderlist = orderlist;
        this.context = context;
        inflater = LayoutInflater.from(context);
        Paper.init(context);
 lang = Paper.book().read("lang", Locale.getDefault().getLanguage());

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        AuctionImageRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.auction_image_row, parent, false);
        return new EventHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EventHolder eventHolder = (EventHolder) holder;
if(position%2!=0){
    eventHolder.binding.image.setImageDrawable(context.getResources().getDrawable(R.drawable.small_gray_rounded));
}
eventHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(context instanceof AuctionDetialsActivity){
            AuctionDetialsActivity auctionDetialsActivity=(AuctionDetialsActivity)context;
        auctionDetialsActivity.showimage(eventHolder.getLayoutPosition());
    }}
});
/*
if(i==position){
    if(i!=0) {
        if (((EventHolder) holder).binding.expandLayout.isExpanded()) {
            ((EventHolder) holder).binding.tvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ((EventHolder) holder).binding.recView.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
            ((EventHolder) holder).binding.expandLayout.collapse(true);
            ((EventHolder) holder).binding.expandLayout.setVisibility(View.GONE);



        }
        else {

          //  ((EventHolder) holder).binding.tvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ((EventHolder) holder).binding.recView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            ((EventHolder) holder).binding.expandLayout.setVisibility(View.VISIBLE);

           ((EventHolder) holder).binding.expandLayout.expand(true);
        }
    }
    else {
        eventHolder.binding.tvTitle.setBackground(activity.getResources().getDrawable(R.drawable.linear_bg_green));

        ((EventHolder) holder).binding.tvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ((EventHolder) holder).binding.recView.setLayoutParams(new FrameLayout.LayoutParams(0, 0));

    }
}
if(i!=position) {
    eventHolder.binding.tvTitle.setBackground(activity.getResources().getDrawable(R.drawable.linear_bg_white));
    ((EventHolder) holder).binding.tvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

    ((EventHolder) holder).binding.recView.setLayoutParams(new FrameLayout.LayoutParams(0, 0));
    ((EventHolder) holder).binding.expandLayout.collapse(true);


}*/

    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        public AuctionImageRowBinding binding;

        public EventHolder(@NonNull AuctionImageRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
