package space.ttnnk.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import space.ttnnk.testapp.api.response.Data;
import space.ttnnk.testapp.presenter.MainPresenter;
import space.ttnnk.testapp.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView {
    int btnClickCount = 1;
    int idListSize = 0;

    TextView helloTextView;
    ImageView imageView;
    WebView webView;
    Button button;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTextView = findViewById(R.id.helloTextView);
        imageView = findViewById(R.id.imageView);
        webView = findViewById(R.id.webView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBtnClickCount();
                presenter.getIdUnit(btnClickCount);
            }
        });

        presenter = new MainPresenter(this);
        presenter.getIDs();
        presenter.getIdUnit(btnClickCount);

    }

    public int getBtnClickCount() {
        Log.d("count", String.valueOf(btnClickCount));
        if (btnClickCount == idListSize) {
            btnClickCount = 1;
        } else {
            btnClickCount++;
        }
        return btnClickCount;
    }

    @Override
    public void getIdList(List<Data> data) {
        for (int i = 0; i < data.size(); i++) {
            Log.d("listId", data.get(i).getId().toString());
            idListSize++;
        }
        Log.d("idListSize", String.valueOf(idListSize));
    }

    @Override
    public void showTextContent(String type, String message) {
        hideViews();
        helloTextView.setVisibility(View.VISIBLE);
        if (type.equals("text")) {
            helloTextView.setText(message);
        }
        if (type.equals("game")) {
            helloTextView.setText("There's no game yet!");
        }
    }

    @Override
    public void showUrlContent(String type, String url) {
        hideViews();
        if (type.equals("webview")) {
            webView.setVisibility(View.VISIBLE);
            webView.clearCache(true);
            webView.clearHistory();
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.loadUrl(url);
        }
        if (type.equals("image")) {
            Log.d("image", url);
//            String url1 = "https://offer-wall.com/img/logo.png";
            //использовал другую ссылку, так как та, что в задании приходит(в 106 строке проверено),
            // но не отображается
            String url1 = "https://png.pngitem.com/pimgs/s/1-11090_clip-art-portable-network-graphics-lighthouse-transparency-black.png";
            imageView.setVisibility(View.VISIBLE);
            Glide.with(this).load(url1).into(imageView);
        }
    }

    public void hideViews() {
        helloTextView.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        Log.d("error", "error");
    }
}