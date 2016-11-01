package com.postnov.android.tfnews;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.postnov.android.tfnews.news.NewsFragment;
import com.postnov.android.tfnews.newscontent.NewsContentFragment;
import com.postnov.android.tfnews.util.NavigationManager;

public class NewsActivity extends AppCompatActivity implements NavigationManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewsFragment.newInstance())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }

    @Override
    public void openNewsList() {
        onBackPressed();
    }

    @Override
    public void openNewsContent(int id) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, NewsContentFragment.newInstance(id))
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
