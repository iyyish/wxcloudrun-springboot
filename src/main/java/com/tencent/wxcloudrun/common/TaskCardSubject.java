package com.tencent.wxcloudrun.common;

/**
 * @Description:
 * @Created: 2022/5/4
 */
public enum TaskCardSubject {
    JIAOYU("1"), // 教育学
    XINLI("2"); // 心理学

    private final String subject;

    TaskCardSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
