package com.postnov.android.tfnews.newscontent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.postnov.android.tfnews.App;
import com.postnov.android.tfnews.R;
import com.postnov.android.tfnews.base.BaseFragment;
import com.postnov.android.tfnews.data.entity.NewsContent;
import com.postnov.android.tfnews.newscontent.interfaces.INewsContentPresenter;
import com.postnov.android.tfnews.newscontent.interfaces.NewsContentView;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by platon on 01.11.2016.
 */

public class NewsContentFragment extends BaseFragment implements NewsContentView {
    private static final String CONTENT_ID = "com.postnov.tfnews.CONTENT_ID";
    private INewsContentPresenter presenter;
    private int contentId;

    @BindView(R.id.tv_news_content) TextView tvNewsContent;
    @BindView(R.id.content_toolbar) Toolbar toolbar;

    @Override
    protected int getLayout() {
        return R.layout.fragment_content;
    }

    public static NewsContentFragment newInstance(int id) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle args = new Bundle();
        args.putInt(CONTENT_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        contentId = getArguments().getInt(CONTENT_ID);
        presenter = new NewsContentPresenter(App.get(this).repository());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bind(this);
        presenter.fetchContent(contentId);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbind();
    }

    @Override
    public void showContent(NewsContent content) {
        tvNewsContent.setText(Html.fromHtml(content.getPayload().getContent()));
    }

    @Override
    public void showError(String error) {
        Timber.e(error);
    }

    @Override
    public void showProgressView(boolean show) {}

    private void initToolbar() {
        toolbar.setTitle("Подробности");
        toolbar.setNavigationIcon(R.drawable.ic_arrow);
        toolbar.setNavigationOnClickListener(v -> navigationManager.openNewsList());
    }
}
