
package jpabook.jpashop.payload.request;

import jpabook.jpashop.entity.address.Address;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateMemberRequest {
    @NotNull
    private final String name;

    @NotNull
    private final String password;

    @NotNull
    private final Address address;

    public CreateMemberRequest(String name, String password, String city, String street){
        this.name = name;
        this.password = password;
        this.address = new Address(city,street);
    }
}