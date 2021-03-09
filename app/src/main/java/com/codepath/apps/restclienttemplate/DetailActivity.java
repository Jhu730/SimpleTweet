package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;
import java.util.Locale;

public class  DetailActivity extends AppCompatActivity {

    Context context;
    List<Tweet> tweets;

    // Pass in the context and list of tweets
    public void TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }


    TextView tvBody;
    TextView tvScreenName;
    TextView tvUserName;
    TextView tvLikes;
    TextView tvRetweets;
    TextView tvTime;
    ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvBody=findViewById(R.id.tvBody);
        tvScreenName=findViewById(R.id.tvScreenName);
        tvUserName=findViewById(R.id.tvUserName);
        tvLikes = findViewById(R.id.tvFavorite);
        tvRetweets = findViewById(R.id.tvRetweet);
        tvTime = findViewById(R.id.tvTime);
        ivProfileImage = findViewById(R.id.ivProfileImage);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvBody.setText(tweet.body);
        tvScreenName.setText(String.format(Locale.getDefault(), "@%s", tweet.user.screenName));
        tvUserName.setText(tweet.user.name);
        tvLikes.setText(String.format(Locale.getDefault(), "%d", tweet.Liked));
        tvRetweets.setText(String.format(Locale.getDefault(), "%d", tweet.reTweets));
        tvTime.setText(TimeFormatter.getTimeDifference(tweet.createAt));
        Glide.with(this).load(tweet.user.profileImageUrl)
                .into(ivProfileImage);

    }
}