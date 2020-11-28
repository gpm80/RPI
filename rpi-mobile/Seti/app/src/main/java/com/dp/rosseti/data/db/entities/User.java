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

    public User(@NonNull String user) {
        this.mUser = user;}

    public String getUser(){return this.mUser;}
}