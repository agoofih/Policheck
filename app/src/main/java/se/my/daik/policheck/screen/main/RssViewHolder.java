package se.my.daik.policheck.screen.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import se.my.daik.policheck.R;
import se.my.daik.policheck.fragments.FavoriteFragment;
import se.my.daik.policheck.fragments.MainFragment;

/**
 * Created by daik on 2018-02-01.
 */

public class RssViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "RssViewHolder";

    private RssEntry rssEntry;

    private ImageView rssImage;
    private TextView rssHeadline;
    private TextView rssText;
    private Button rssFavButton;


    private AdapterView.OnItemClickListener mItemClickListener;


    public RssViewHolder(final View itemView, final RssAdapter.OnFavBtnClickedListener listener) {
        super(itemView);

        rssImage = itemView.findViewById(R.id.rss_image);
        rssHeadline = itemView.findViewById(R.id.rss_headline);
        rssText = itemView.findViewById(R.id.rss_text);
        rssFavButton = itemView.findViewById(R.id.rss_fav_button);

        Log.d(TAG, "RssViewHolder: asd...........");


        rssFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "FAVBUTTON CLICKED 1" + v);
                Log.d(TAG, "onClick: pooooop");

                rssEntry.setFavorite(!rssEntry.isFavorite());
                listener.onFavBtnClicked(rssEntry);
            }
        });
    }

    public void setOnItemClickListener(final AdapterView.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public void bind(RssEntry entry){

        rssEntry = entry;

        Picasso.with(itemView.getContext())
                .load(rssEntry.getImage())
                .into(rssImage);

       // rssImage.setImageURI(entry.getImage());
        rssHeadline.setText(entry.getHeadline());
        rssText.setText(entry.getMainText());

    }

    public static RssViewHolder newInstance(ViewGroup parent, RssAdapter.OnFavBtnClickedListener listener){
        Log.d(TAG, "newInstance: ---" + listener.getClass());
        if(listener.getClass() == MainFragment.class){
            //Log.d(TAG, "newInstance: MAAAAIIIINN");
            return new RssViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_item, parent, false),
                    listener
            );
        }else{
            //Log.d(TAG, "newInstance: ANNOOOOTTHEER");
            return new RssViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_item_fav, parent, false),
                    listener
            );
        }


    }


}
