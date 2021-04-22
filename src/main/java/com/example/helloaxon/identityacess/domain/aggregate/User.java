package com.example.helloaxon.identityacess.domain.aggregate;

import java.util.Date;

/**
 * @author : Ftibw
 * @date : 2021/4/7 14:49
 */
public class User {
    private Integer type;//"账户类型：0.平台 1.驿站 2.地产公司 3.推销员 4.快递网点"
    private String account;//登录账户
    private String password;//登录密码
    private String name;//真实姓名
    private String phone;//手机号
    private Integer enabled;//是否可用：1.可用 0.禁用
    private String roleId;//角色ID
    private String remark;//备注
    private Date lastLogin;//最后登录时间

    public void createUser(){

    }

    public void updateUser(){

    }

    public void changePwd(){

    }

    public void resetPwd(){

    }

    public void activate(){

    }

    public void deactivate(){

    }

}
