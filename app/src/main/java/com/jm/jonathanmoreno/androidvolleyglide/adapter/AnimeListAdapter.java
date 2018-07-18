package com.jm.jonathanmoreno.androidvolleyglide.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jm.jonathanmoreno.androidvolleyglide.R;
import com.jm.jonathanmoreno.androidvolleyglide.activities.AnimeActivity;
import com.jm.jonathanmoreno.androidvolleyglide.model.AnimeList;

import java.util.List;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>  {

   private RequestOptions requestOptions;
   private Context context;
   private List<AnimeList> animeData;


   public AnimeListAdapter(Context context, List<AnimeList> list){
       this.context = context;
       this.animeData = list;


       requestOptions = new RequestOptions()
               .centerCrop()
               .placeholder(R.drawable.loading_shape)
               .error(R.drawable.loading_shape);

   }

   @Override
   public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
       View view;
       LayoutInflater layoutInflater = LayoutInflater.from(context);
       view = layoutInflater.inflate(R.layout.anime_row_item ,parent, false);
       final AnimeViewHolder viewHolder = new AnimeViewHolder(view);
       viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent i = new Intent(context, AnimeActivity.class);

               i.putExtra("anime_name",animeData.get(viewHolder.getAdapterPosition()).getName());
               i.putExtra("anime_description",animeData.get(viewHolder.getAdapterPosition()).getDescription());
               i.putExtra("anime_studio",animeData.get(viewHolder.getAdapterPosition()).getStudio());
               i.putExtra("anime_category",animeData.get(viewHolder.getAdapterPosition()).getCategorie());
               i.putExtra("anime_nb_episode",animeData.get(viewHolder.getAdapterPosition()).getNb_episode());
               i.putExtra("anime_rating",animeData.get(viewHolder.getAdapterPosition()).getRating());
               i.putExtra("anime_img",animeData.get(viewHolder.getAdapterPosition()).getImage_url());

               context.startActivity(i);

           }
       });

       return viewHolder;
   }

   @Override
    public  void onBindViewHolder(AnimeViewHolder holder, final int position){
       holder.titleName.setText(animeData.get(position).getName());
       holder.categoryInfo.setText(animeData.get(position).getCategorie());
       holder.ratingInfo.setText(animeData.get(position).getRating());
       holder.studioInfo.setText(animeData.get(position).getStudio());


       Glide.with(context).load(animeData.get(position).getImage_url()).apply(requestOptions).into(holder.animeThumnail);



   }

   @Override
    public int getItemCount(){
       return animeData.size();
   }

   public static class AnimeViewHolder extends RecyclerView.ViewHolder {

       @BindView(R.id.rowname)
       TextView titleName;
       @BindView(R.id.category)
       TextView categoryInfo;
       @BindView(R.id.rating)
       TextView ratingInfo;
       @BindView(R.id.studio)
       TextView studioInfo;


       ImageView animeThumnail;
       LinearLayout view_container;



       public AnimeViewHolder(View itemView){
           super(itemView);
           ButterKnife.bind(this, itemView);
           view_container = itemView.findViewById(R.id.container);
           animeThumnail = itemView.findViewById(R.id.thumbnail);


       }


   }

}
