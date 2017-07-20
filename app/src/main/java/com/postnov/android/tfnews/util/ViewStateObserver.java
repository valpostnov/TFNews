package com.postnov.android.tfnews.util;

import rx.Observer;
import rx.subjects.BehaviorSubject;

/**
 * @author Valentin Postnov
 */
public class ViewStateObserver<VS> implements Observer<VS> {

    private final BehaviorSubject<VS> subject;

    public ViewStateObserver(BehaviorSubject<VS> subject) {
        this.subject = subject;
    }

    @Override
    public void onCompleted() {
        // ViewState observable never completes so ignore any complete event
    }

    @Override
    public void onError(Throwable e) {
        throw new IllegalStateException(
                "ViewState observable must not reach error state - onError()", e);
    }

    @Override
    public void onNext(VS value) {
        subject.onNext(value);
    }
}
