package se.my.daik.policheck.screen.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.my.daik.policheck.R;

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


    public RssViewHolder(View itemView, final RssAdapter.OnFavBtnClickedListener listener) {
        super(itemView);

        rssImage = itemView.findViewById(R.id.rss_image);
        rssHeadline = itemView.findViewById(R.id.rss_headline);
        rssText = itemView.findViewById(R.id.rss_text);
        rssFavButton = itemView.findViewById(R.id.rss_fav_button);

        rssFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "FAVBUTTON CLICKED 1" + v);

                listener.onFavBtnClicked(rssEntry);

            }
        });
    }



    public void bind(RssEntry entry){

        rssEntry = entry;

        //rssImage.setBackgroundResource(entry.getImage());
        rssHeadline.setText(entry.getHeadline());
        rssText.setText(entry.getMainText());

    }

    public static RssViewHolder newInstance(ViewGroup parent, RssAdapter.OnFavBtnClickedListener listener){
        return new RssViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_item, parent, false),
                listener
        );
    }


}
