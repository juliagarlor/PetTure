package com.ironhack.edgeservice.utils.dtos;

public class ProfileDTO {

    private String userName;
    private Long profilePic;

//    Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Long profilePic) {
        this.profilePic = profilePic;
    }
}
