package com.cipolat.fbcomments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.cipolat.fbcomments.UI.CustomView.FbCommentsView;
import com.cipolat.fbcomments.UI.CustomView.FbConfig;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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
        String postUrl="https://pedidosonline-357d1.firebaseapp.com/comments.html";
        String fbAppID="220403711853240";
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comments_menu, menu);
        return true;
    }

}
