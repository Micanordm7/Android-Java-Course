package com.example.appbasic;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
   @PrimaryKey(autoGenerate = true)
   private int uid;
   @ColumnInfo(name = "first_name")
   private String nom;
   @ColumnInfo(name = "last_name")
   private String Prenom;
   @ColumnInfo(name = "email")
   private String email;
   @ColumnInfo(name = "picture")
   private int image;

    public User() {
    }

    public User(String nom, String prenom, String email, int image) {
        this.nom = nom;
        Prenom = prenom;
        this.email = email;
        this.image = image;
    }

    public User(int uid, String nom, String prenom, String email, int image) {
        this.uid = uid;
        this.nom = nom;
        Prenom = prenom;
        this.email = email;
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getImage() {
        return image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
