package com.postnov.android.tfnews.news;

import com.postnov.android.tfnews.data.entity.News;

/**
 * @author Valentin Postnov
 */
public interface NewsViewSate {

    final class DataState implements NewsViewSate {
        private final News news;

        public DataState(News news) {
            this.news = news;
        }

        public News getNews() {
            return news;
        }
    }

    final class LoadingState implements NewsViewSate {
    }

    final class ErrorState implements NewsViewSate {

        private final Throwable error;

        public ErrorState(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }
    }
}
