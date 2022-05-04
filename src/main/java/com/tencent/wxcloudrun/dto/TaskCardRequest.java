package com.tencent.wxcloudrun.dto;

import com.tencent.wxcloudrun.common.TaskCardStatus;
import com.tencent.wxcloudrun.model.TaskCard;
import lombok.Data;

/**
 * @Description:
 * @Created: 2022/4/24
 */
@Data
public class TaskCardRequest {
    private Integer id;
    private String uid;
    private String content;
    private Integer weight;
    private String subject;
    private String status;


    public TaskCard dto2TaskCard() {
        TaskCard taskCard = new TaskCard();
        taskCard.setId(this.id);
        taskCard.setUid(this.uid);
        taskCard.setContent(this.content);
        taskCard.setWeight(this.weight);
        taskCard.setSubject(this.subject);
        taskCard.setStatus(TaskCardStatus.ACTIVE.getStatus());
        return taskCard;
    }
}
