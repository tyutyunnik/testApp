package space.ttnnk.testapp.presenter;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter {
    CompositeDisposable compositeDisposable;

    public void onDestroy () {
        compositeDisposable.dispose();
    }
}
