package jpabook.jpashop.entity.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //서브타입을 물리적으로 구현하는 전략 설정
@DiscriminatorColumn(name = "dtype") //하위 클래스를 구분하는 용도의 컬럼
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    //데이터를 가지고있는 쪽에 비즈니스 메서드가 있는게 객체지향적으로 더 나음 (응집력이 생김)
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock <= 0){throw new IllegalStateException("재고가 부족합니다");}
        this.stockQuantity = restStock;
    }
}