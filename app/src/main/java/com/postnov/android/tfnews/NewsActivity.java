package com.postnov.android.tfnews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.postnov.android.tfnews.news.NewsFragment;
import com.postnov.android.tfnews.newscontent.NewsContentFragment;
import com.postnov.android.tfnews.util.NavigationManager;

public class NewsActivity extends AppCompatActivity implements NavigationManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (savedInstanceState == null) {
            replaceFragment(NewsFragment.newInstance());
        }
    }

    @Override
    public void openNewsList() {
        replaceFragment(NewsFragment.newInstance());
    }

    @Override
    public void openNewsContent(int id) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, NewsContentFragment.newInstance(id))
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
