package com.tencent.wxcloudrun.common;

/**
 * @Description:
 * @Created: 2022/4/24
 */
public enum TaskCardStatus {
    ACTIVE("1"), // 激活
    CLOSE("0"); // 关闭

    private final String status;

    TaskCardStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
