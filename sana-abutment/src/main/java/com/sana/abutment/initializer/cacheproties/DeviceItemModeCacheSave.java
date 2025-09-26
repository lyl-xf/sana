package com.sana.abutment.initializer.cacheproties;

import java.util.Arrays;
import java.util.List;

/**
 * @author LON
 * @create 2025/9/21
 */
public class DeviceItemModeCacheSave {

    /**
     * 要缓存的设备物模型名称
     */
    private List<String> modeSigns;
    /**
     * 设备实例id
     */
    private Long deviceItemId;

    // Getter 和 Setter 方法
    public List<String> getModeSigns() {
        return modeSigns;
    }

    public void setModeSigns(String modeSigns) {
        if (modeSigns != null) {
            this.modeSigns = Arrays.asList(modeSigns.split(","));
        } else {
            this.modeSigns = null;
        }
    }

    public Long getDeviceItemId() {
        return deviceItemId;
    }

    public void setDeviceItemId(Long deviceItemId) {
        this.deviceItemId = deviceItemId;
    }


}
