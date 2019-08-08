package com.jiangkai.framework.admin.common;

import com.github.pagehelper.PageInfo;
import com.jiangkai.framework.admin.common.enums.MsgType;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/6/28
 */
@SuppressWarnings("unchecked")
@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private Integer page;
    private Long total;
    private List<T> rows;
    private T data;

    protected Result() {
    }

    private Result(MsgType type, T data) {
        this.code = type.getCode();
        this.msg = type.getContent();
        if (data instanceof PageInfo) {
            PageInfo info = (PageInfo) data;
            this.page = info.getPages();
            this.total = info.getTotal();
            this.rows = info.getList();
        } else {
            this.data = data;
        }
    }

    public static Result result(MsgType type) {
        return new Result(type, type.getContent());
    }

    public static Result success(Object data) {
        return new Result<>(MsgType.SUCCESS, data);
    }

    public static Result success() {
        return new Result<>(MsgType.SUCCESS, MsgType.SUCCESS.getContent());
    }

    public static Result businessError(Object data) {
        return new Result<>(MsgType.BUSINESS_ERROR, data);
    }

    public static Result notLogin() {
        return new Result<>(MsgType.NOT_LOGIN, "请先登录");
    }

    public static Result noPermission() {
        return new Result<>(MsgType.NO_PERMISSION, "您没有权限");
    }

    public static Result noData() {
        return new Result<>(MsgType.NO_DATA, "系统无相关数据");
    }

    public static Result paramError(Object data) {
        return new Result<>(MsgType.PARAM_ERROR, data);
    }

    public static Result error() {
        return new Result<>(MsgType.ERROR, "系统异常");
    }

    public static Result page(PageInfo info) {
        return pageInfo(MsgType.SUCCESS, info);
    }

    public static Result page(List list){
        return page(new PageInfo(list));
    }

    public static Result emptyPage() {
        PageInfo info = new PageInfo();
        return pageInfo(MsgType.NO_DATA, info);
    }

    private static Result pageInfo(MsgType msgType, PageInfo info) {
        return new Result(msgType, info);
    }
}
