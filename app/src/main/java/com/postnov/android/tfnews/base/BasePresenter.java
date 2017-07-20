package com.postnov.android.tfnews.base;

import android.support.annotation.NonNull;

import com.postnov.android.tfnews.util.IntentObserver;
import com.postnov.android.tfnews.util.ViewStateObserver;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by platon on 01.11.2016.
 */

public abstract class BasePresenter<V extends BaseView, VS> {

    private final BehaviorSubject<VS> viewStateBehaviorSubject;
    private boolean viewAttachedFirstTime = true;
    private ViewStateConsumer<V, VS> viewStateConsumer;

    private List<IntentRelayBinderPair<?>> intentRelaysBinders = new ArrayList<>(4);

    private Subscription viewRelayConsumerSubscription;
    private Subscription viewsStateSubscription;
    private Subscription intentSubscription;

    private boolean subscribeViewStateMethodCalled = false;

    protected BasePresenter(VS initialViewState) {
        viewStateBehaviorSubject = BehaviorSubject.create(initialViewState);
        reset();
    }

    public BasePresenter() {
        viewStateBehaviorSubject = BehaviorSubject.create();
        reset();
    }

    protected abstract void bindIntents();

    public void attachView(V view) {
        if (viewAttachedFirstTime) {
            bindIntents();
        }

        if (viewStateConsumer != null) {
            subscribeViewStateConsumerActually(view);
        }

        int intentsSize = intentRelaysBinders.size();
        for (int i = 0; i < intentsSize; i++) {
            IntentRelayBinderPair<?> intentRelayBinderPair = intentRelaysBinders.get(i);
            bindIntentActually(view, intentRelayBinderPair);
        }

        viewAttachedFirstTime = false;
    }

    public void detachView(boolean retainInstance) {
        if (!retainInstance) {
            if (viewsStateSubscription != null) {
                viewsStateSubscription.unsubscribe();
            }

            unbindIntents();
            reset();
        }

        if (intentSubscription != null) {
            intentSubscription.unsubscribe();
        }

        if (viewRelayConsumerSubscription != null) {
            viewRelayConsumerSubscription.unsubscribe();
        }
    }

    protected void subscribe(@NonNull Observable<VS> viewStateObservable, @NonNull ViewStateConsumer<V, VS> consumer) {
        if (subscribeViewStateMethodCalled) {
            throw new IllegalStateException("subscribeViewState() method is only allowed to be called once");
        }
        subscribeViewStateMethodCalled = true;

        viewStateConsumer = consumer;
        viewsStateSubscription = viewStateObservable.subscribe(new ViewStateObserver<>(viewStateBehaviorSubject));
    }

    protected <I> Observable<I> intent(ViewIntentBinder<V, I> binder) {
        PublishSubject<I> intentRelay = PublishSubject.create();
        intentRelaysBinders.add(new IntentRelayBinderPair<>(intentRelay, binder));
        return intentRelay;
    }

    private <I> Observable<I> bindIntentActually(V view, IntentRelayBinderPair<?> relayBinderPair) {
        PublishSubject<I> intentRelay = (PublishSubject<I>) relayBinderPair.intentRelaySubject;
        ViewIntentBinder<V, I> intentBinder = (ViewIntentBinder<V, I>) relayBinderPair.intentBinder;
        Observable<I> intent = intentBinder.bind(view);

        intentSubscription = intent.subscribe(new IntentObserver<>(intentRelay));
        return intentRelay;
    }

    //при перевороте получаем последний результат
    private void subscribeViewStateConsumerActually(V view) {
        viewRelayConsumerSubscription = viewStateBehaviorSubject.subscribe(viesState -> viewStateConsumer.accept(view, viesState));
    }

    private void unbindIntents() {
    }

    private void reset() {
        viewAttachedFirstTime = true;
        intentRelaysBinders.clear();
        subscribeViewStateMethodCalled = false;
    }

    protected interface ViewIntentBinder<V extends BaseView, I> {

        @NonNull
        Observable<I> bind(@NonNull V view);
    }

    protected interface ViewStateConsumer<V extends BaseView, VS> {

        void accept(@NonNull V view, @NonNull VS viewState);
    }

    private class IntentRelayBinderPair<I> {

        private final PublishSubject<I> intentRelaySubject;
        private final ViewIntentBinder<V, I> intentBinder;

        public IntentRelayBinderPair(PublishSubject<I> intentRelaySubject,
                                     ViewIntentBinder<V, I> intentBinder) {
            this.intentRelaySubject = intentRelaySubject;
            this.intentBinder = intentBinder;
        }
    }
}
