package com.ironhack.userservice.utils.dtos;

import com.ironhack.userservice.model.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotEmpty;

public class ProfileDTO {

    @NotEmpty(message = "Be creative, it's your username")
    @Length(max = 20, message = "SHORT NAMES!")
    private String userName;
    @NotEmpty(message = "Pick your favourite pic")
    private String profilePic;

//    Constructors
    public ProfileDTO(@NotEmpty(message = "Be creative, it's your username") @Length(max = 10, message = "SHORT NAMES!")
                              String userName, @NotEmpty(message = "Pick your favourite pic") String profilePic) {
        this.userName = userName;
        this.profilePic = profilePic;
    }

    public ProfileDTO(User user){
        this(user.getUserName(), user.getProfilePicture());
    }

//    Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
