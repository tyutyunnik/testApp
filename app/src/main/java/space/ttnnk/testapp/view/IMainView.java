package space.ttnnk.testapp.view;

import android.net.Uri;

import java.util.List;

import space.ttnnk.testapp.api.response.Data;

public interface IMainView {
    void getIdList(List<Data> data);
    void showTextContent(String type, String message);
    void showUrlContent(String type, String url);
    void showError(Throwable error);
}
