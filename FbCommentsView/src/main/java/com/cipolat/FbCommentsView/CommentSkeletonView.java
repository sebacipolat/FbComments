package com.cipolat.FbCommentsView;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;


/**
 * Created by scipolat on 08/02/18.
 */
public class CommentSkeletonView extends ConstraintLayout {
    private Context mCtx;

    public CommentSkeletonView(Context context) {
        super(context);
        this.mCtx = context;
        init();
    }

    public CommentSkeletonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCtx = context;
        init();
    }

    public CommentSkeletonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCtx = context;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.comment_skeleton, this);

    }


}
