package com.postnov.android.tfnews.data;

import com.postnov.android.tfnews.data.entity.News;
import com.postnov.android.tfnews.data.entity.NewsContent;
import com.postnov.android.tfnews.data.source.IDataSource;
import com.postnov.android.tfnews.data.source.remote.RemoteDataSource;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

/**
 * Created by platon on 01.11.2016.
 */

public class RemoteDataSourceTest {

    private IDataSource remote;

    @Before
    public void setup() {
        remote = new RemoteDataSource();
    }

    @Test
    public void testGetNews() throws Exception {
        TestSubscriber<News> testSubscriber = new TestSubscriber<>();
        remote.getNews().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testGetNewsContent() throws Exception {
        TestSubscriber<NewsContent> testSubscriber = new TestSubscriber<>();
        remote.getContent(6804).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }
}
