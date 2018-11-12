package com.frame.ssm.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_sys_function")
public class Function implements Serializable {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     *	功能名称
     */
    private String name;
    /**
     *	父id
     */
    private Integer pId;
    /**
     *	功能地址
     */
    private String url;
    /**
     *	功能简介
     */
    private String note;
    /**
     *	功能图标
     */
    private String icon;
    /**
     *	功能排序
     */
    private Byte orderBy;
    /**
     *	创建时间
     */
    private String createTime;
    /**
     *	修改时间
     */
    private String updateTime;
    /**
     *	所属系统 0 管理系统
     */
    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Byte orderBy) {
        this.orderBy = orderBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
