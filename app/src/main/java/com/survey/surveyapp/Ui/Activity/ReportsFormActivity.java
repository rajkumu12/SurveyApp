package com.survey.surveyapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fevziomurtekin.customprogress.Dialog;
import com.fevziomurtekin.customprogress.Type;
import com.survey.surveyapp.R;

public class ReportsFormActivity extends AppCompatActivity {

    Dialog dialog;
    WebView webView;
    public ValueCallback<Uri[]> uploadMessage;
    private ValueCallback<Uri> mUploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;

    String form_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reports_form);

        dialog=findViewById(R.id.progress);
        webView=findViewById(R.id.webView);

        Intent intent=getIntent();
        form_url=intent.getStringExtra("url");


        dialog.settype(Type.CUBE);
        dialog.setdurationTime(100);

        checkInternetConnection();
    }
    public void checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager)this.getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        } else {
            dialog.show();
            setWeb();
        }
    }
    public void setWeb() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                dialog.show();
                if (newProgress == 100) {
                   dialog.gone();
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new ReportsFormActivity.MyWebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // ignore ssl error
                if (handler != null) {
                    handler.proceed();
                } else {
                    super.onReceivedSslError(view, null, error);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("ahkhfkdhfkjf ","url"+url);
                if (url.contains("whatsapp")) {
                    try {
                        Log.d("afhhfdfdf",""+webView.getUrl());
                        webView.loadUrl(webView.getUrl());
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    } catch (Exception use) {
                        Log.e("Error msg", use.getMessage());
                    }
                }else if (url.startsWith("mailto:")) {
                    startActivity(
                            new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                    return true;
                }
                else if (url.contains("wa.me")) {
                    try {
                        webView.loadUrl(webView.getUrl());
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    } catch (Exception use) {
                        Log.e("Error msg", use.getMessage());
                    }
                }  else if (url.startsWith("tel")){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                    Log.d("bkzvhxvb",""+url);
                    webView.loadUrl(url);
                    return false;
                }
                return false;
            }
        });
        webView.loadUrl(form_url);
    }

    class MyWebChromeClient extends WebChromeClient {
        private View mCustomView;
        private CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        public MyWebChromeClient() {
        }

        public Bitmap getDefaultVideoPoster() {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }



        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);


            if (newProgress == 100) {
               dialog.gone();
            }
            /*super.onProgressChanged(view, newProgress);*/
        }

        protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
        {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
        }


        // For Lollipop 5.0+ Devices

        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams)
        {
            if (uploadMessage != null) {
                uploadMessage.onReceiveValue(null);
                uploadMessage = null;
            }

            uploadMessage = filePathCallback;


            try
            {
               /* Intent intent = fileChooserParams.createIntent();
                startActivityForResult(intent, REQUEST_SELECT_FILE);*/

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            } catch (ActivityNotFoundException e)
            {
                Log.e("hhyuj",e.getMessage());
                uploadMessage = null;
                Toast.makeText(getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        //For Android 4.1 only
        protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
        {
            mUploadMessage = uploadMsg;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg)
        {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }
    }





}