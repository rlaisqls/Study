package jpabook.jpashop.payload.request;

import jpabook.jpashop.entity.address.Address;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateMemberRequest {

    private final String name;

    private final String password;

    private final Address address;

    public UpdateMemberRequest(String name, String password, String city, String street){
        this.name = name;
        this.password = password;
        this.address = new Address(city,street);
    }
}