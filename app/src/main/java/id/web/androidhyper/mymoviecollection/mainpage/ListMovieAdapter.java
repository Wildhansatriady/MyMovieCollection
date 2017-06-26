package id.web.androidhyper.mymoviecollection.mainpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.web.androidhyper.mymoviecollection.R;
import id.web.androidhyper.mymoviecollection.api.ApiUrl;
import id.web.androidhyper.mymoviecollection.mainpage.pojo.MovieModel;

/**
 * Created by wildhan on 6/26/2017 in MyMovieCollection.
 * Keep Spirit!!
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MovieViewholder>{
    private MovieModel mMovieList;
    private Context mContext;
    private onClickList mClickList;
    private int idMovie;
    public ListMovieAdapter(MovieModel mMovieList, Context mContext,onClickList clickList) {
        this.mMovieList = mMovieList;
        this.mContext = mContext;
        this.mClickList = clickList;
    }

    @Override
    public MovieViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new MovieViewholder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewholder holder, int position) {
        String imgUrl = ApiUrl.BASE_IMG_URL;
        imgUrl += mMovieList.getResults().get(position).getPosterPath();
        Picasso.with(mContext).load(imgUrl).placeholder(R.drawable.ic_photo_black_24dp).into(holder.mImageItem);
    }

    @Override
    public int getItemCount() {
        return mMovieList.getResults().size();
    }

    public int getIdMovie() {
        return idMovie;
    }

    interface onClickList{
        void onClickMovie(int position);
    }
    class MovieViewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item)
        ImageView mImageItem;

        @OnClick(R.id.img_item)
        void onItemClicked(){
            idMovie = mMovieList.getResults().get(getAdapterPosition()).getId();
            mClickList.onClickMovie(getAdapterPosition());
        }
        MovieViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
