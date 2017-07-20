package com.postnov.android.tfnews.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.postnov.android.tfnews.App;
import com.postnov.android.tfnews.R;
import com.postnov.android.tfnews.base.BaseFragment;
import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.news.NewsViewSate.DataState;
import com.postnov.android.tfnews.news.NewsViewSate.ErrorState;
import com.postnov.android.tfnews.news.NewsViewSate.LoadingState;
import com.postnov.android.tfnews.news.interfaces.INewsPresenter;
import com.postnov.android.tfnews.news.interfaces.NewsView;

import butterknife.BindView;
import rx.Observable;

import static com.postnov.android.tfnews.news.DividerItemDecoration.VERTICAL_LIST;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsFragment extends BaseFragment implements NewsView, NewsAdapter.OnItemClickListener {

    private INewsPresenter presenter;
    private NewsAdapter newsAdapter;

    @BindView(R.id.swipe_view)      SwipeRefreshLayout refreshLayout;
    @BindView(R.id.news_list)       RecyclerView rv;
    @BindView(R.id.news_empty_view) View emptyView;
    @BindView(R.id.list_toolbar)    Toolbar toolbar;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setTitle(R.string.app_name);

        presenter = new NewsPresenter(new GetNewsInteractor(App.get(this).repository()));
        newsAdapter = new NewsAdapter();
        newsAdapter.setOnItemClickListener(this);

        rv.setAdapter(newsAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL_LIST));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.detachView(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void onItemClick(int id) {
        navigationManager.openNewsContent(id);
    }

    @Override
    public Observable<Void> loadNewsIntent() {
        return Observable.just(null);
    }

    @Override
    public Observable<Void> refreshIntent() {
        return RxSwipeRefreshLayout.refreshes(refreshLayout);
    }

    @Override
    public void render(NewsViewSate newsViewSate) {
        if (newsViewSate instanceof LoadingState) {
            renderLoading();
        } else if (newsViewSate instanceof ErrorState) {
            renderError((ErrorState) newsViewSate);
        } else {
            renderData((DataState) newsViewSate);
        }
    }

    private void renderLoading() {
        refreshLayout.setRefreshing(true);
    }

    private void renderError(ErrorState errorState) {
        String error = errorState.getError().getMessage();
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    private void renderData(DataState dataState) {
        refreshLayout.setRefreshing(false);
        News news = dataState.getNews();
        if (news != null) {
            newsAdapter.swap(news.getPayload());
        }
        emptyView.setVisibility(newsAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
