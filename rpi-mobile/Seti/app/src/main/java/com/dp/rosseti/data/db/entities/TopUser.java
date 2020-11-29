package com.dp.rosseti.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "topusers_table")
public class TopUser {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "chartline")
    private int mChartLine;

    private String mTopUser;

    private String mAvatar;

    private int mRating;

    public TopUser(@NonNull int mChartLine, String mTopUser, String mAvatar, int mRating) {
        this.mChartLine = mChartLine;
        this.mTopUser = mTopUser;
        this.mAvatar = mAvatar;
        this.mRating = mRating;
    }

    public int getChartLine() {
        return this.mChartLine;
    }

    public String getTopUser() {
        return this.mTopUser;
    }

    public int getRating() {
        return this.mRating;
    }

    public String getAvatar() {
        return this.mAvatar;
    }

}