package com.sana.abutment.initializer.proties;

import lombok.Data;

/**
 * @author LON
 * @create 2025/9/25 16:20
 */
@Data
public class SslPerties {
    private Boolean enabled;
    private String keystorePath;
    private String keystorePass;
    private String truststorePath;
    private String truststorePass;
    private String clientAuth;
}
