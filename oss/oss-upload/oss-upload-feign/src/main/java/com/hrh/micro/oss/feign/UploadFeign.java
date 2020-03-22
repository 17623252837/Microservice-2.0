package com.hrh.micro.oss.feign;

import com.hrh.micro.Configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: Microservice-2.0
 * @Package: com.hrh.micro.oss.feign
 * @ClassName: UploadFeign
 * @Author: HuRonghua
 * @Description:
 * @Date: 2020/3/22 14:55
 * @Version: 1.0
 */
@FeignClient(value = "cloud-upload", path = "upload", configuration = FeignRequestConfiguration.class)
public interface UploadFeign {
    /**
     * 文件上传
     *
     * @param multipartFile {@code MultipartFile}
     * @return {@code String} 文件上传路径
     */
    @PostMapping(value = "")
    String upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile);
}
