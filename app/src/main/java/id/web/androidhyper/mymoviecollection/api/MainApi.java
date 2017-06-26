package id.web.androidhyper.mymoviecollection.api;

import id.web.androidhyper.mymoviecollection.Util;
import id.web.androidhyper.mymoviecollection.detailpage.pojo.DetailModel;
import id.web.androidhyper.mymoviecollection.mainpage.pojo.MovieModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wildhan on 6/26/2017 in MyMovieCollection.
 * Keep Spirit!!
 */

public interface MainApi {
    @GET(ApiUrl.TOPRATED_MOVIE_URL)
    Call<MovieModel> getTopRatedMovie(@Query(Util.PARAMS_APIKEY)String apikey,
                                      @Query(Util.PARAMS_LANGUAGE)String language);

    @GET(ApiUrl.POPULAR_MOVIE_URL)
    Call<MovieModel> getPopularMovie(@Query(Util.PARAMS_APIKEY)String apikey,
                                     @Query(Util.PARAMS_LANGUAGE)String language);

    @GET(ApiUrl.DETAIL_MOVIE_URL)
    Call<DetailModel> getDetail(@Path("movie_id") int id, @Query("api_key") String apikey);
}
