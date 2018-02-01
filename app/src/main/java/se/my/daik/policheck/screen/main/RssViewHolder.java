package se.my.daik.policheck.screen.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import se.my.daik.policheck.R;

/**
 * Created by daik on 2018-02-01.
 */

public class RssViewHolder extends RecyclerView.ViewHolder {

    private RssEntry rssEntry;

    private ImageView rssImage;
    private TextView rssHeadline;
    private TextView rssText;

    public RssViewHolder(View itemView) {
        super(itemView);

        rssImage = itemView.findViewById(R.id.rss_image);
        rssHeadline = itemView.findViewById(R.id.rss_headline);
        rssText = itemView.findViewById(R.id.rss_text);

    }

    public void bind(RssEntry entry){

        rssEntry = entry;

        //rssImage.setBackgroundResource(entry.getImage());
        rssHeadline.setText(entry.getHeadline());
        rssText.setText(entry.getMainText());

    }

    public static RssViewHolder newInstance(ViewGroup parent){
        return new RssViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_item, parent, false)
        );
    }


}
