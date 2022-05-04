package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Created: 2022/4/24
 */
@Data
public class TaskCard implements Serializable {
    /**
     * 卡片id
     */
    private Integer id;
    /**
     * 卡片内容
     */
    private String content;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 类型: 1-教育学,2-心理学
     */
    private String subject;
    /**
     * 卡片状态: 0-关闭,1-开启
     */
    private String status;
    /**
     * 用户id
     */
    private String uid;
    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}