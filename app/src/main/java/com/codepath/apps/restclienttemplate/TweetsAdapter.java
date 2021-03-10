package com.codepath.apps.restclienttemplate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.parceler.Parcels;

import java.util.List;
import java.util.Locale;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{


    Context context;
    List<Tweet> tweets;

    // Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //For each row, inflate a layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }
    //Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);
        // Bind tweet with view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() { return tweets.size(); }

    // Clean all elements of the recycler
    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

    //Define view-holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        TextView  tvBody;
        TextView  tvScreenName;
        TextView tvTimestamp;
        TextView tvUserName;
        TextView tvFav;
        TextView tvRetweet;
        RelativeLayout RLtweet;
        FloatingActionButton actionButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvFav = itemView.findViewById(R.id.tvFav);
            tvRetweet = itemView.findViewById(R.id.tvRetweets);
            RLtweet = itemView.findViewById(R.id.RLtweet);
            actionButton = itemView.findViewById(R.id.actionButton);
        }
        public void bind(final Tweet tweet) {
            tvRetweet.setText(String.format(Locale.getDefault(), "%d", tweet.reTweets));
            tvFav.setText(String.format(Locale.getDefault(), "%d", tweet.Liked));
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.name);
            tvUserName.setText(String.format(Locale.getDefault(), "@%s", tweet.user.screenName));
            tvTimestamp.setText(TimeFormatter.getTimeDifference(tweet.createAt));
            Glide.with(context).load(tweet.user.profileImageUrl).transform(new CircleCrop()).into(ivProfileImage);

            RLtweet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(context, DetailActivity.class);
                    //Pass data
                    i.putExtra("tweet", Parcels.wrap(tweet));
                    Pair<View, String> p1 = Pair.create((View) RLtweet, "tweet");
                    //Pair<View, String> p2 = Pair.create((View) tvScreenName, "name");
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,p1);
                    context.startActivity(i, options.toBundle());


                }

            });


            }
        }
    }


