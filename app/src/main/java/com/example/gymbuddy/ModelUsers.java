package com.example.gymbuddy;

public class ModelUsers {

    //use same name in firebase
    String name, useremail, search, imageLink, nickname, bio, uuid;

    public ModelUsers(){
    }

    public ModelUsers(String name, String useremail, String search, String imageLink, String nickname, String bio, String uuid) {
        this.name = name;
        this.useremail = useremail;
        this.search = search;
        this.imageLink = imageLink;
        this.nickname = nickname;
        this.bio = bio;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
