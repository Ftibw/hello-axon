package com.example.helloaxon.common;

import com.example.helloaxon.common.util.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 响应结果对象
 *
 * @author : Ftibw
 * @date : 2019/7/10 17:23
 */
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    public static final int SUCCESS = 1;//请求逻辑处理成功
    public static final int FAILURE = 0;//请求逻辑处理失败
    public static final int UNAUTHENTICATED = 2;//认证失败(未登录,或token无效)
    public static final String UNAUTHENTICATED_MSG = "登录状态已变更，请重新登录！";
    public static final int UNAUTHORIZED = 3;//权限不足
    public static final String UNAUTHORIZED_MSG = "权限不足，请刷新页面或联系管理员！";

    private T data;//响应数据
    private String msg;//响应消息
    private Integer code;//响应码：0.逻辑处理失败，1.逻辑处理成功，2.身份认证失败，3.权限不足
    private Long timestamp;//响应时间戳

    private Result(Integer code) {
        this.code = code;
    }

    private Result(T data, Integer code) {
        this.data = data;
        this.code = code;
    }

    private Result(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    private Result(T data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    /**
     * success
     */
    public static <T> Result<T> success() {
        return new Result<>(Result.SUCCESS);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(data, msg, Result.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, Result.SUCCESS);
    }

    /**
     * failure
     */
    public static <T> Result<T> failure() {
        return new Result<>(Result.FAILURE);
    }

    public static <T> Result<T> failure(String msg, T data) {
        return new Result<>(data, msg, Result.FAILURE);
    }

    public static <T> Result<T> failure(String msg) {
        return new Result<>(msg, Result.FAILURE);
    }

    /**
     * unauthenticated
     */
    public static <T> Result<T> unauthenticated() {
        return new Result<>(UNAUTHENTICATED_MSG, Result.UNAUTHENTICATED);
    }

    public static <T> Result<T> unauthenticated(String msg) {
        return new Result<>(msg, Result.UNAUTHENTICATED);
    }

    /**
     * unauthorized
     */
    public static <T> Result<T> unauthorized() {
        return new Result<>(UNAUTHORIZED_MSG, Result.UNAUTHORIZED);
    }

    public static <T> Result<T> unauthorized(String msg) {
        return new Result<>(msg, Result.UNAUTHORIZED);
    }

    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }

    @JsonIgnore
    public boolean isFailure() {
        return code == Result.FAILURE;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return code == Result.SUCCESS;
    }

}
