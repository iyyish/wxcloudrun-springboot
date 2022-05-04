package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.TaskCardMapper;
import com.tencent.wxcloudrun.model.TaskCard;
import com.tencent.wxcloudrun.service.TaskCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Created: 2022/4/24
 */
@Service
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
public class TaskCardServiceImpl implements TaskCardService {

    @Resource
    TaskCardMapper taskCardMapper;

    @Override
    public List<TaskCard> selectByStatusAndSubjectOrNot(String status, String subject, String uid) {
        return taskCardMapper.selectByStatusAndSubjectOrNot(status, subject, uid);
    }

    @Override
    public void updateStatusBySubjectOrNot(String status, String subject, String uid) {
        taskCardMapper.updateStatusBySubjectOrNot(status, subject, uid);
    }

    @Override
    public void updateStatusById(String status, Integer id) {
        taskCardMapper.updateStatusById(status, id);
    }

    @Override
    public void save(TaskCard taskCard) {
        // 1. 数据校验
        Assert.hasText(taskCard.getContent(), "卡片内容不能为空");
        Assert.notNull(taskCard.getWeight(), "卡片权重不能为空");
        Assert.notNull(taskCard.getSubject(), "卡片类型不能为空");
        Assert.notNull(taskCard.getUid(), "用户信息不能为空");
        // 2. 内容重复校验
        int count = taskCardMapper.countBySubjectAndContentAndUid(taskCard.getSubject(), taskCard.getContent(), taskCard.getUid());
        Assert.isTrue(count == 0, "卡片内容已存在,不能重复新增");
        taskCardMapper.save(taskCard);
    }

    @Override
    public void deleteById(Integer id) {
        taskCardMapper.deleteById(id);
    }
}
