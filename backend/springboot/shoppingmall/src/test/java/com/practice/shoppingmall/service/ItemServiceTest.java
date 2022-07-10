package com.practice.shoppingmall.service;

import com.practice.shoppingmall.constant.ItemConstant;
import com.practice.shoppingmall.domain.item.domain.Item;
import com.practice.shoppingmall.domain.item.domain.repository.ItemRepository;
import com.practice.shoppingmall.domain.item.exception.ItemNotFoundException;
import com.practice.shoppingmall.domain.item.presentation.dto.request.AddItemStockRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.CreateItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.request.ModifyItemRequest;
import com.practice.shoppingmall.domain.item.presentation.dto.response.CreateItemResponse;
import com.practice.shoppingmall.domain.item.service.ItemServiceImpl;
import com.practice.shoppingmall.global.error.exception.BusinessException;
import com.practice.shoppingmall.util.ItemBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    static private Item item;
    @InjectMocks
    private ItemServiceImpl itemService;
    @Mock
    private ItemRepository itemRepository;

    @BeforeAll
    static void setUp() {
        item = ItemBuilder.build();
    }

    @Test
    void 아이템_생성_성공() {
        //given
        String name = item.getName();
        int price = item.getPrice();
        int stock = item.getStock();

        given(itemRepository.save(any())).willReturn(ItemBuilder.build());

        //when
        CreateItemRequest request = CreateItemRequest
                .builder()
                .itemName(name)
                .price(price)
                .stock(stock)
                .build();
        CreateItemResponse response = itemService.createItem(request);

        //then
        assertThat(response.getId()).isNotNull();
        verify(itemRepository, times(1)).save(any());
    }

    @Test
    void 아이템_수정_성공() {
        //given
        String newName = "주머니빵";
        int newPrice = 10000;

        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        //when
        ModifyItemRequest request = ModifyItemRequest
                .builder()
                .itemId(1L)
                .itemName(newName)
                .price(newPrice)
                .build();
        itemService.modifyItem(request);

        //then
        assertThat(item.getName()).isEqualTo(newName);
        assertThat(item.getPrice()).isEqualTo(newPrice);
        verify(itemRepository, times(1)).save(any());

    }

    @Test
    void 아이템_수정_실패() {
        //given
        String newName = "주머니빵";
        int newPrice = 10000;

        given(itemRepository.findById(any())).willReturn(Optional.empty());

        //when
        try {
            ModifyItemRequest request = ModifyItemRequest
                    .builder()
                    .itemId(item.getId())
                    .itemName(newName)
                    .price(newPrice)
                    .build();
            itemService.modifyItem(request);
            fail("No error");
        } catch (BusinessException e) {
            //then
            Assertions.assertThat(e).isInstanceOf(ItemNotFoundException.class);
            verify(itemRepository, times(0)).save(any());
        }

    }

    @Test
    void 아이템_재고추가_성공() {
        //given
        int addStock = 10;

        given(itemRepository.findById(any())).willReturn(Optional.of(item));

        //when
        AddItemStockRequest request = AddItemStockRequest
                .builder()
                .addStock(addStock)
                .build();
        itemService.addItemStock(item.getId(), request);

        //then
        assertThat(item.getStock()).isEqualTo(ItemConstant.STOCK + addStock);
        verify(itemRepository, times(1)).save(any());
    }

    @Test
    void 아이템_삭제_성공() {
        //given
        given(itemRepository.findById(any())).willReturn(Optional.of(item));
        //when
        itemService.deleteItem(item.getId());
        //then
        verify(itemRepository, times(1)).delete(item);
    }
}