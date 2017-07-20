package com.postnov.android.tfnews.util;

import rx.Observer;
import rx.subjects.PublishSubject;

/**
 * @author Valentin Postnov
 */
public class IntentObserver<I> implements Observer<I> {

    private final PublishSubject<I> subject;

    public IntentObserver(PublishSubject<I> subject) {
        this.subject = subject;
    }

    @Override
    public void onCompleted() {
        subject.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        throw new IllegalStateException("View intents must not throw errors", e);
    }

    @Override
    public void onNext(I value) {
        subject.onNext(value);
    }
}
