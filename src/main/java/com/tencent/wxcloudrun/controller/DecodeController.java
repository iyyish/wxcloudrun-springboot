package com.tencent.wxcloudrun.controller;

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
            Assert.hasText(request.getSessionKey(), "登录密钥session不能为空");
            Assert.hasText(request.getEncryptedData(), "加密数据encrypted不能为空");
            Assert.hasText(request.getIv(), "加密算法iv不能为空");
            String result = WeChatUtil.wxDecrypt(request.getEncryptedData(), request.getSessionKey(), request.getIv());
            logger.info("decode: {}", result);
            return ApiResponse.ok(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ApiResponse.error(e.getMessage());
        }
    }
}
