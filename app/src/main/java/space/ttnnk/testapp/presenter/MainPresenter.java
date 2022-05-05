package space.ttnnk.testapp.presenter;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import space.ttnnk.testapp.api.RetrofitFactory;
import space.ttnnk.testapp.view.IMainView;

public class MainPresenter extends BasePresenter {
    private IMainView mainView;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        compositeDisposable = new CompositeDisposable();
    }

    public void getIDs() {
        compositeDisposable.add(RetrofitFactory.getTestApi().getAllIds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(idListResponse -> {
                    if (!idListResponse.getData().isEmpty()) {
                        mainView.getIdList(idListResponse.getData());
                    } else {
                        mainView.showError(null);
                    }
                }, throwable -> {
                    mainView.showError(throwable);
                }));
    }

    public void getIdUnit(int id) {
        compositeDisposable.add(RetrofitFactory.getTestApi().getId(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(idUnitResponse -> {
                    Log.d("!@#", "здесь мы заходим в switch: " + idUnitResponse.getId());
                    switch (idUnitResponse.getType()) {
                        case "text":
                            Log.d("!@#", "здесь text: " + idUnitResponse.getMessage());
                            mainView.showTextContent(idUnitResponse.getType(), idUnitResponse.getMessage());
                            break;
                        case "webview":
                        case "image":
                            Log.d("!@#", "здесь webview и imageview: " + idUnitResponse.getUrl());
                            mainView.showUrlContent(idUnitResponse.getType(), idUnitResponse.getUrl());
                            break;
                        case "game":
                            Log.d("!@#", "здесь game: " + idUnitResponse.getUrl());
                            mainView.showTextContent(idUnitResponse.getType(), "");
                            break;
                    }
                }, throwable -> {
                    mainView.showError(throwable);
                }));
    }

}
