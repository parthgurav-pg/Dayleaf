package in.dayleaf.notebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NotebookActivity extends Activity {
    private static final String NOTEBOOK = "https://dayleaf-parth-planner.stirringkookaburra.chatgpt.site/";
    private WebView notebook;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(0xff395643);
        notebook = new WebView(this);
        WebSettings settings = notebook.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        CookieManager.getInstance().setAcceptCookie(true);
        notebook.setWebViewClient(new WebViewClient() {
            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return !"https".equals(request.getUrl().getScheme());
            }
            @Override public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (!request.isForMainFrame() || isFinishing()) return;
                new AlertDialog.Builder(NotebookActivity.this)
                    .setTitle("Couldn't open Dayleaf")
                    .setMessage("Check your connection and try opening the notebook again.")
                    .setPositiveButton("Retry", (dialog, button) -> notebook.loadUrl(NOTEBOOK))
                    .setNegativeButton("Close", (dialog, button) -> finish()).show();
            }
        });
        setContentView(notebook);
        if (state == null || notebook.restoreState(state) == null) notebook.loadUrl(NOTEBOOK);
    }
    @Override public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        notebook.saveState(state);
    }
    @Override public void onPause() {
        CookieManager.getInstance().flush();
        super.onPause();
    }
    @Override public void onBackPressed() {
        if (notebook.canGoBack()) notebook.goBack(); else super.onBackPressed();
    }
}
