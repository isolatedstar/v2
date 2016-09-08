package com.zllh.factoring.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: FacMessageStatus
 * @Description: 报文处理状态表（被调报文）
 * @author JW
 * @date 2016年4月8日 下午2:40:55
 */
public class FacMessageStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;

    private Date createtime;

    private Integer status;

    private String message;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
    
}