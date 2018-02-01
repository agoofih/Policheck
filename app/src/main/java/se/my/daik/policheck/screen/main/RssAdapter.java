package se.my.daik.policheck.screen.main;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daik on 2018-02-01.
 */

public class RssAdapter extends RecyclerView.Adapter<RssViewHolder> {

    private List<RssEntry> rssEntryList = new ArrayList<>();

    @Override
    public int getItemCount() {
        return rssEntryList.size();
    }

    @Override
    public RssViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RssViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(RssViewHolder holder, int position) {

        holder.bind(rssEntryList.get(position));

    }

    public void setRssEntryList(List<RssEntry> rssEntryList) {
        this.rssEntryList = rssEntryList;
        notifyDataSetChanged();
    }
}
