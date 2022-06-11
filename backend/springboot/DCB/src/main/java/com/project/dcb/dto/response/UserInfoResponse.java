package com.project.dcb.dto.response;

import com.project.dcb.Entity.Gathering;
import com.project.dcb.Entity.user.User;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserInfoResponse {
    String username;
    String name;
    String schoolClass;
    String authority;
    public UserInfoResponse(User user){
        this.username = user.getUsername();
        this.name = user.getName();
        if(user.getGathering().equals(Gathering.GENERAL)) this.schoolClass = "";
        else this.schoolClass = user.getGathering().toString().charAt(6) + "학년"
                + user.getGathering().toString().charAt(8) + "반";
        this.authority = user.getAuthority().toString();
    }
}