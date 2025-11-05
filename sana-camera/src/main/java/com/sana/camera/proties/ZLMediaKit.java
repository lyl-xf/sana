package com.sana.camera.proties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LON
 * @create 2025/11/4 16:30
 */

@Data
@Component
@ConfigurationProperties(prefix = "zlm")
public class ZLMediaKit {

    private Boolean enable;

    private String balance;

    private List<Nodes> nodes;

}
