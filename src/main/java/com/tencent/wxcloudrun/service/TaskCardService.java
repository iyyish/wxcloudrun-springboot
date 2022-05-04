package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.TaskCard;

import java.util.List;

/**
 * @Description:
 * @Created: 2022/4/24
 */
public interface TaskCardService {

    List<TaskCard> selectByStatusAndSubjectOrNot(String status, String subject, String uid);

    void updateStatusBySubjectOrNot(String status, String subject, String uid);

    void updateStatusById(String status, Integer id);

    void save(TaskCard taskCard);

    void deleteById(Integer id);
}
