package com.sana;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class SanaServerApplication {


    public static void main(String[] args) {
        try {
            SpringApplication.run(SanaServerApplication.class, args);
            // 获取本地主机对象
            InetAddress localHost = InetAddress.getLocalHost();
            log.info("\n----------------------------------------------------------------------------\n\t" +
                    "Application Sana-Server is running!  :\n\t" +
                    "本地接口文档：http://{}:8081/sana/doc.html\n\t" +
                    "sa-token配置如下:{}\n\t" +
                    "----------------------------------------------------------------------------", localHost.getHostAddress(), SaManager.getConfig());
        } catch (Exception e) {
            // 处理无法获取主机信息的情况
            e.printStackTrace();
        }
    }
}

