package com.goalkeeper.goalkeeperbe.global.error.exception;

import com.goalkeeper.goalkeeperbe.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private final ErrorCode errorCode;
}