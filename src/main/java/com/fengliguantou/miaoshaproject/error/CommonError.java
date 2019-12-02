package com.fengliguantou.miaoshaproject.error;

/**
 * Created by fengliguantou on 2018/11/13.
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);


}
