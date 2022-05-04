package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.TaskCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Created: 2022/4/24
 */
public interface TaskCardMapper {
    TaskCard selectById(@Param("id") Integer id);

    List<TaskCard> selectByStatusAndSubjectOrNot(@Param("status") String status, @Param("subject") String subject, @Param("uid") String uid);

    void updateStatusBySubjectOrNot(@Param("status") String status, @Param("subject") String subject, @Param("uid") String uid);

    void updateStatusById(@Param("status") String status, @Param("id") Integer id);

    void save(TaskCard taskCard);

    void deleteById(@Param("id") Integer id);

    int countBySubjectAndContentAndUid(@Param("subject") String subject, @Param("content") String content, @Param("uid") String uid);
}