package com.codepath.apps.restclienttemplate.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TweetDao {
    @Query("SELECT Tweet.body AS tweet_body, Tweet.createAt AS tweet_createAt, Tweet.id AS tweet_id, Tweet.Liked AS tweet_Liked, Tweet.reTweets AS tweet_reTweets, User.* " +
            "FROM Tweet INNER JOIN User ON Tweet.userId = User.id ORDER BY Tweet.createAt DESC LIMIT 20")
    List<TweetWithUser> recentItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(Tweet...tweets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertModel(User...users);
}
