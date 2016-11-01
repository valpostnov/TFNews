package com.postnov.android.tfnews.news;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.postnov.android.tfnews.R;
import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.entity.Payload;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by platon on 01.11.2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Payload> newsList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public void swap(List<Payload> news) {
        newsList = news;
        Collections.sort(newsList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            tvTitle = (TextView) view.findViewById(R.id.tv_news_title);
        }

        void bind(Payload payload) {
            tvTitle.setText(Html.fromHtml(payload.getText()));
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(newsList.get(getAdapterPosition()).getId());
        }
    }
}
