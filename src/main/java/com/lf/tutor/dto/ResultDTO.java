package com.lf.tutor.dto;

import com.lf.tutor.config.ReturnCfg;

/*
 * @Description: 结果统一返回类
 * @Athor:
 * @version: v1.0
 */
public class ResultDTO {
    private String code;
    private String msg;
    private Object data;
    public ResultDTO(){}
    public ResultDTO(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public ResultDTO(Object o){
        this.code = ReturnCfg.SUCCESS_CODE;
        this.msg = ReturnCfg.SUCCESS_MSG;
        this.data = o;
    }
    public ResultDTO(Exception e){
        this.code = ReturnCfg.ERROR_CODE;
        this.msg = ReturnCfg.ERROR_MSG;
        this.data = e.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
