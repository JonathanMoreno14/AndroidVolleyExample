package com.jm.jonathanmoreno.androidvolleyglide.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jm.jonathanmoreno.androidvolleyglide.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeActivity extends AppCompatActivity {

    @BindView(R.id.anime_name)
    TextView detailName;
    @BindView(R.id.anime_studio)
    TextView detailStudio;
    @BindView(R.id.anime_category)
    TextView detailCategory;
    @BindView(R.id.anime_description)
    TextView detailDescription;
    @BindView(R.id.anime_rating)
    TextView detailRating;

    @BindView(R.id.anime_thumbnail)
    ImageView detailImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        ButterKnife.bind(this);
        getSupportActionBar().hide();


        String name  = getIntent().getExtras().getString("anime_name");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio") ;
        String category = getIntent().getExtras().getString("anime_category");
        int nb_episode = getIntent().getExtras().getInt("anime_nb_episode") ;
        String rating = getIntent().getExtras().getString("anime_rating") ;
        String image_url = getIntent().getExtras().getString("anime_img") ;

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        detailName.setText(name);
        detailCategory.setText(category);
        detailDescription.setText(description);
        detailStudio.setText(studio);
        detailRating.setText(rating);

        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        Glide.with(this).load(image_url).apply(requestOptions).into(detailImg);

    }
}
