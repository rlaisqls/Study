package jpabook.jpashop.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException {
    private final ErrorCode errorcode;
}