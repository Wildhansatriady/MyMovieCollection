package id.web.androidhyper.mymoviecollection.mainpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.web.androidhyper.mymoviecollection.R;
import id.web.androidhyper.mymoviecollection.Util;
import id.web.androidhyper.mymoviecollection.api.ApiBuilder;
import id.web.androidhyper.mymoviecollection.api.ApiUrl;
import id.web.androidhyper.mymoviecollection.api.MainApi;
import id.web.androidhyper.mymoviecollection.detailpage.DetailActivity;
import id.web.androidhyper.mymoviecollection.mainpage.pojo.MovieModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListMovieAdapter.onClickList {

    @BindView(R.id.rv_gridmovie)
    RecyclerView mGridFilm;
    @BindView(R.id.ln_loadinglayout)
    LinearLayout mLoadingLayout;
    @BindView(R.id.ln_error_layout)
    LinearLayout mErrorLayout;
    private int positionMenu =0;
    @OnClick(R.id.bt_refresh)
    void onRefresh(){
        if(positionMenu==0){
            getPopularMovieData();
        }else {
            getTopRatedMovie();
        }
    }

    ListMovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getPopularMovieData();
    }

    void initRecylerView(MovieModel movieModel){
        mGridFilm.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        mGridFilm.setLayoutManager(layoutManager);
        mMovieAdapter = new ListMovieAdapter(movieModel,getBaseContext(),this);
        mGridFilm.setAdapter(mMovieAdapter);
    }

    void getPopularMovieData(){
        positionMenu=0;
        Util.setTitleActionBar(this,getString(R.string.popular_movie));
        Util.isError(mGridFilm,mErrorLayout,false);
        Util.isloading(mGridFilm,mLoadingLayout,true);
        MainApi api = ApiBuilder.getClient().create(MainApi.class);
        Call<MovieModel> call = api.getPopularMovie(ApiUrl.API_KEY,Util.DEFAULT_LANGUAGE);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                Util.isloading(mGridFilm,mLoadingLayout,false);

                if(response.body()!=null){
                    initRecylerView(response.body());
                    Util.isError(mGridFilm,mErrorLayout,false);

                }else{
                    Util.isError(mGridFilm,mErrorLayout,true);
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Util.isloading(mGridFilm,mLoadingLayout,false);
                Util.isError(mGridFilm,mErrorLayout,true);

            }
        });
    }

    void getTopRatedMovie(){
        positionMenu=1;
        Util.setTitleActionBar(this,getString(R.string.top_rated_movie));
        Util.isError(mGridFilm,mErrorLayout,false);
        Util.isloading(mGridFilm,mLoadingLayout,true);
        MainApi api = ApiBuilder.getClient().create(MainApi.class);
        Call<MovieModel> call = api.getTopRatedMovie(ApiUrl.API_KEY,Util.DEFAULT_LANGUAGE);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                Util.isloading(mGridFilm,mLoadingLayout,false);

                if(response.body()!=null){
                    initRecylerView(response.body());
                    Util.isError(mGridFilm,mErrorLayout,false);

                }else{
                    Util.isError(mGridFilm,mErrorLayout,true);
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Util.isloading(mGridFilm,mLoadingLayout,false);
                Util.isError(mGridFilm,mErrorLayout,true);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choose_type,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_popularmovie:
                getPopularMovieData();
                return true;
            case R.id.menu_topratedmovie:
                getTopRatedMovie();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClickMovie(int position) {
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);
        intent.putExtra(Util.KEY_IDMOVIE,mMovieAdapter.getIdMovie());
        startActivity(intent);
    }
}
