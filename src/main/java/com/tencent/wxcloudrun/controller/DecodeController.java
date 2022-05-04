package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.WeChatUtil;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.DecodeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Created: 2022/5/4
 */
@RestController
public class DecodeController {
    private Logger logger = LoggerFactory.getLogger(DecodeController.class);


    @PostMapping(value = "/api/wx/decode")
    public ApiResponse decode(@RequestBody DecodeRequest request) {
        try {
            Assert.hasText(request.getCode(), "登录凭证code不能为空");
            Assert.hasText(request.getEncryptedData(), "加密数据encrypted不能为空");
            Assert.hasText(request.getIv(), "加密算法iv不能为空");
            // 根据 code 获取密钥信息
            JSONObject response = WeChatUtil.getSessionKeyAndOpenid(request.getCode());
            String key = response.getString("session_key");
            logger.info("key: {}", key);
            String result = WeChatUtil.wxDecrypt(request.getEncryptedData(), key, request.getIv());
            logger.info("decode: {}", result);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.error(e.getMessage());
        }
    }
}
