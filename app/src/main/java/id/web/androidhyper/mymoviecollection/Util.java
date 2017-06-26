package id.web.androidhyper.mymoviecollection;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wildhan on 6/26/2017 in MyMovieCollection.
 * Keep Spirit!!
 */

public class Util {
    public static final String PARAMS_APIKEY = "api_key";
    public static final String PARAMS_LANGUAGE = "language";

    public static final String DEFAULT_LANGUAGE = "en-US";

    public static final String KEY_IDMOVIE = "idmovie";

    public static void setTitleActionBar(AppCompatActivity mActivity,String title){
        ActionBar actionBar = mActivity.getSupportActionBar();
        if(actionBar!=null)
            actionBar.setTitle(title);
    }

    public static void setAsUpButton(AppCompatActivity mActivity){
        ActionBar actionBar = mActivity.getSupportActionBar();
        if(actionBar!=null)
           actionBar.setDisplayHomeAsUpEnabled(true);
    }
   public static void isloading(RecyclerView mGridFilm,LinearLayout mLoadingLayout, boolean isHiden){
        if(isHiden){
            mLoadingLayout.setVisibility(View.VISIBLE);
            mGridFilm.setVisibility(View.GONE);
        }else {
            mLoadingLayout.setVisibility(View.GONE);
            mGridFilm.setVisibility(View.VISIBLE);
        }
    }


    public static void isError(RecyclerView mGridFilm,LinearLayout mErrorLayout, boolean isHiden){
        if(isHiden){
            mErrorLayout.setVisibility(View.VISIBLE);
            mGridFilm.setVisibility(View.GONE);
        }else {
            mErrorLayout.setVisibility(View.GONE);
            mGridFilm.setVisibility(View.VISIBLE);
        }
    }

    public static void isloading(LinearLayout lnContent,LinearLayout mLoadingLayout, boolean isHiden){
        if(isHiden){
            mLoadingLayout.setVisibility(View.VISIBLE);
            lnContent.setVisibility(View.GONE);
        }else {
            mLoadingLayout.setVisibility(View.GONE);
            lnContent.setVisibility(View.VISIBLE);
        }
    }


    public static void isError(LinearLayout lnContent,LinearLayout mErrorLayout, boolean isHiden){
        if(isHiden){
            mErrorLayout.setVisibility(View.VISIBLE);
            lnContent.setVisibility(View.GONE);
        }else {
            mErrorLayout.setVisibility(View.GONE);
            lnContent.setVisibility(View.VISIBLE);
        }
    }
}
