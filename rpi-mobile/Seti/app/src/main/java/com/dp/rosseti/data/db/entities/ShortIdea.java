package com.dp.rosseti.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "short_ideas_table")
public class ShortIdea {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idea_id")
    private int mIdeaId;

    private String mTitle;

    @ColumnInfo(name = "idea_owner")
    private String mOwnerName;

    private String mDescription;

    private String mAttachment;

    private String mStatus;

    public ShortIdea(@NonNull int mIdeaId, String mTitle, String mOwnerName, String mDescription, String mAttachment, String mStatus) {
        this.mIdeaId = mIdeaId;
        this.mTitle = mTitle;
        this.mOwnerName = mOwnerName;
        this.mDescription = mDescription;
        this.mAttachment = mAttachment;
        this.mStatus = mStatus;
    }

    public int getIdeaId() {
        return mIdeaId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAttachment() {
        return mAttachment;
    }

    public String getStatus() {
        return mStatus;
    }

}