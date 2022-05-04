package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.common.TaskCardStatus;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.TaskCardRequest;
import com.tencent.wxcloudrun.model.TaskCard;
import com.tencent.wxcloudrun.service.TaskCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @Description:
 * @Created: 2022/4/24
 */
@RestController
public class TaskCardController {
    final Logger logger;
    final TaskCardService taskCardService;

    public TaskCardController(TaskCardService taskCardService) {
        this.taskCardService = taskCardService;
        this.logger = LoggerFactory.getLogger(TaskCardController.class);
    }

    @GetMapping(value = "/api/cards/random")
    public ApiResponse random(@RequestParam(value = "subject", required = false) String subject) {
        logger.info("GET /api/cards/random, subject: {}", subject);
        List<TaskCard> list = taskCardService.selectByStatusAndSubjectOrNot(TaskCardStatus.ACTIVE.getStatus(), subject);
        if (list.size() == 0) {
            taskCardService.updateStatusBySubjectOrNot(TaskCardStatus.ACTIVE.getStatus(), subject);
            list = taskCardService.selectByStatusAndSubjectOrNot(TaskCardStatus.ACTIVE.getStatus(), subject);
        }
        if (list.size() == 0) {
            return ApiResponse.ok();
        }
        // 重新成计算权重后的数组
        List<TaskCard> result = new ArrayList<>();
        for (TaskCard taskCard : list) {
            int weight = taskCard.getWeight();
            for (int i = 0; i < weight; i++) {
                result.add(taskCard);
            }
        }
        // 打乱顺序
        Collections.shuffle(result);
        Random random = new Random();
        int index = random.nextInt(result.size());
        return ApiResponse.ok(result.get(index));
    }

    @PutMapping(value = "/api/cards/{id}")
    public ApiResponse checkStatus(@PathVariable("id") Integer id) {
        logger.info("PUT /api/cards, id: {}", id);
        taskCardService.updateStatusById(TaskCardStatus.CLOSE.getStatus(), id);
        return ApiResponse.ok();
    }

    @PostMapping(value = "/api/cards")
    public ApiResponse save(@RequestBody TaskCardRequest request) {
        logger.info("POST /api/cards, request: {}", JSON.toJSONString(request));
        TaskCard taskCard = request.dto2TaskCard();
        taskCardService.save(taskCard);
        return ApiResponse.ok(taskCard);
    }

    @DeleteMapping(value = "/api/cards/{id}")
    public ApiResponse delete(@PathVariable("id") Integer id) {
        logger.info("DELETE /api/cards, id: {}", id);
        taskCardService.deleteById(id);
        return ApiResponse.ok();
    }

    @GetMapping(value = "/api/cards")
    public ApiResponse selectByStatusAndSubject(@RequestParam(value = "status", required = false) String status,
                                                @RequestParam(value = "subject", required = false) String subject) {
        logger.info("GET /api/cards, status: {}, subject: {}", status, subject);
        List<TaskCard> list = taskCardService.selectByStatusAndSubjectOrNot(status, subject);
        return ApiResponse.ok(list);
    }
}
