package com.sana.camera.proties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author LON
 * @create 2025/11/4 16:32
 */
@Data
public class Nodes {

    private String serverId;

    private String host;

    @JsonIgnore
    private String secret;

    private Boolean enabled;

    private Boolean hookEnabled;

}
