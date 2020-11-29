package com.dp.rosseti.data.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user")
    private String mUser;

    private String mPosition;

    private String mAvatar;

    public User(@NonNull String user, String position, String avatar) {
        this.mUser = user;
        this.mPosition = position;
        this.mAvatar = avatar;
    }

    public String getUser() {
        return this.mUser;
    }

    public String getPosition() {
        return this.mPosition;
    }

    public String getAvatar() {
        return this.mAvatar;
    }

}