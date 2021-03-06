package com.ghiar.adapters;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ghiar.R;
import com.ghiar.activities_fragments.activity_home.fragments.Fragment_Auction;
import com.ghiar.databinding.AuctionRowBinding;
import com.ghiar.databinding.ItemDrawerMarkBinding;
import com.ghiar.models.MarkModel;
import com.ghiar.models.SingleAuctionModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.MarkViewholder> {

    private List<SingleAuctionModel> list;
    private Context context;
    private Fragment fragment;

    public AuctionAdapter(Context context, List<SingleAuctionModel> list, Fragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MarkViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AuctionRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.auction_row, parent, false);
        return new MarkViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkViewholder holder, int position) {

        holder.binding.setAuctionmodel(list.get(position));
        holder.binding.setLang("ar");
        long enddate = Long.parseLong(list.get(position).getEnd_date()) * 1000;
        long date = System.currentTimeMillis();
        long diffrent = (enddate - date);
        CountDownTimer cdt = new CountDownTimer(diffrent, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                holder.binding.tvseconed.setText(seconds + "");
                holder.binding.tvminute.setText(minutes + "");
                holder.binding.tvhour.setText(hours + "");
                holder.binding.tvday.setText(days + "");


            }

            @Override
            public void onFinish() {
            }
        };
        cdt.start();
        holder.binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof Fragment_Auction) {
                    Fragment_Auction fragment_auction = (Fragment_Auction) fragment;
                    fragment_auction.CreateDialogAlert(context, holder.getLayoutPosition());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof Fragment_Auction) {
                    Fragment_Auction fragment_auction = (Fragment_Auction) fragment;
                    fragment_auction.show( holder.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MarkViewholder extends RecyclerView.ViewHolder {
        AuctionRowBinding binding;

        public MarkViewholder(@NonNull AuctionRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
