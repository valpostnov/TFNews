package com.postnov.android.tfnews.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.postnov.android.tfnews.App;
import com.postnov.android.tfnews.R;
import com.postnov.android.tfnews.base.BaseFragment;
import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.news.interfaces.INewsPresenter;
import com.postnov.android.tfnews.news.interfaces.NewsView;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsFragment extends BaseFragment implements NewsView,
        SwipeRefreshLayout.OnRefreshListener, NewsAdapter.OnItemClickListener {

    private INewsPresenter presenter;
    private NewsAdapter newsAdapter;

    @BindView(R.id.swipe_view)      SwipeRefreshLayout refreshLayout;
    @BindView(R.id.news_list)       RecyclerView rv;
    @BindView(R.id.news_empty_view) View emptyView;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new NewsPresenter(App.get(this).repository());
        newsAdapter = new NewsAdapter();
        newsAdapter.setOnItemClickListener(this);

        rv.setAdapter(newsAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bind(this);
        presenter.fetchNews();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbind();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void showNews(News news) {
        if (news != null) newsAdapter.swap(news.getPayload());
        emptyView.setVisibility(newsAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String error) {
        Timber.tag("NewsFragment").e(error);
    }

    @Override
    public void showProgressView(boolean show) {
        refreshLayout.setRefreshing(show);
    }

    @Override
    public void onRefresh() {
        presenter.fetchNews();
    }

    @Override
    public void onItemClick(int id) {
        navigationManager.openNewsContent(id);
    }
}
