package id.web.androidhyper.mymoviecollection.detailpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.web.androidhyper.mymoviecollection.R;
import id.web.androidhyper.mymoviecollection.Util;
import id.web.androidhyper.mymoviecollection.api.ApiBuilder;
import id.web.androidhyper.mymoviecollection.api.ApiUrl;
import id.web.androidhyper.mymoviecollection.api.MainApi;
import id.web.androidhyper.mymoviecollection.detailpage.pojo.DetailModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.relase_date)
    TextView tv_relasedate;
    @BindView(R.id.genre)
    TextView tv_genre;
    @BindView(R.id.txt_vote)
    TextView tv_vote;
    @BindView(R.id.desc)
    TextView tv_description;
    @BindView(R.id.status)
    TextView tv_status;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.production_companies)
    TextView tv_productionCompanies;
    @BindView(R.id.runtimetxt)
    TextView tv_runtimeMovie;
    @BindView(R.id.ln_loadinglayout)
    LinearLayout mLoadingLayout;
    @BindView(R.id.ln_error_layout)
    LinearLayout mErrorLayout;
    @BindView(R.id.ln_container_content)
    LinearLayout mContainerContent;
    @BindView(R.id.imageView)
    ImageView imageCover;


    @OnClick(R.id.bt_refresh)
    void onRefresh(){
        getDataDetail(idMovie);
    }

    private int idMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Util.setTitleActionBar(this,getString(R.string.detail_title));

        Util.setAsUpButton(this);

        if(getIntent()!=null)
            idMovie=getIntent().getIntExtra(Util.KEY_IDMOVIE,0);

        getDataDetail(idMovie);
    }

    void getDataDetail(int id){
        Util.isError(mContainerContent,mErrorLayout,false);
        Util.isloading(mContainerContent,mLoadingLayout,true);
        MainApi api = ApiBuilder.getClient().create(MainApi.class);
        Call<DetailModel> call = api.getDetail(id, ApiUrl.API_KEY);

        call.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                Util.isloading(mContainerContent,mLoadingLayout,false);
                if(response.body()!=null){
                    DetailModel data = response.body();
                    tv_title.setText(data.getTitle());
                    tv_description.setText(data.getOverview());

                    StringBuilder strGenre = new StringBuilder();
                    for (int i = 0; i <data.getGenres().size() ; i++) {
                        strGenre.append(data.getGenres().get(i).getName()).append(",");
                        if(data.getGenres().size()-1 ==i){
                            strGenre.append(data.getGenres().get(i).getName()).append(".");
                        }
                    }

                    StringBuilder strProd = new StringBuilder();
                    for (int i = 0; i <data.getProductionCompanies().size() ; i++) {
                        strProd.append(data.getProductionCompanies().get(i).getName()).append(",");
                        if(data.getProductionCompanies().size()-1 ==i){
                            strProd.append(data.getProductionCompanies().get(i).getName()).append(".");
                        }
                    }

                    String genres = strGenre.toString();
                    String prodCompanies = strProd.toString();

                    tv_genre.setText(genres);
                    tv_productionCompanies.setText(prodCompanies);
                    tv_relasedate.setText(data.getReleaseDate());
                    String runtime = String.valueOf(data.getRuntime())+" Minutes";
                    tv_runtimeMovie.setText(runtime);
                    tv_status.setText(data.getStatus());
                    tv_vote.setText(String.valueOf(data.getVoteAverage()));
                    String imgUrl = ApiUrl.BASE_IMG_URL;
                    imgUrl+=data.getPosterPath();
                    Picasso.with(getBaseContext()).load(imgUrl).placeholder(R.drawable.ic_photo_black_24dp)
                            .into(imageCover);
                    Util.isError(mContainerContent,mErrorLayout,false);
                }else {
                    Util.isError(mContainerContent,mErrorLayout,true);
                }

            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                Util.isloading(mContainerContent,mLoadingLayout,false);
                Util.isError(mContainerContent,mErrorLayout,true);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return false;
        }
    }
}
