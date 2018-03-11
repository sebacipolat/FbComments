package com.cipolat.fbcomments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.cipolat.FbCommentsView.FbCommentsView;
import com.cipolat.FbCommentsView.FbConfig;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FbCommentsView.LoadingListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.commentsView)
    FbCommentsView commentsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();


        String postUrl="";//Set an url target

        String fbAppID="";//SET your FB AppID

        commentsView.setListener(this);

        FbConfig config=new FbConfig(fbAppID,postUrl);
        commentsView.loadData(config);
    }

    private void setToolbar() {
        mToolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mToolbar.setTitle(getString(R.string.home_title));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comments_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                commentsView.reload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onError() {
        Log.e("onError"," Error Loading");
    }

    @Override
    public void onLoadingReady() {
        Log.i("onLoadingReady"," All OK");
    }
}
