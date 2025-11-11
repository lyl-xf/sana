package com.sana.camera.entity.result;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sana.base.mybatis.entity.BaseEntity;
import com.sana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author LON
 * @create 2025/11/11 13:29
 */
@Data
@Schema(description = "代理拉流设备")
public class CameraStreamProxyResult{

    @Schema(description = "id")
    private Long id;
    /**
     * 摄像头名称
     */
    @Schema(description = "摄像头名称")
    private String name;
    /**
     * 节点信息
     */
    @Schema(description = "节点信息")
    private String nodeNameId;

    /**
     * 节点信息ip
     */
    @Schema(description = "节点信息ip")
    private String nodeIp;
    /**
     * 控制类型名称
     */
    @Schema(description = "控制类型名称")
    private String app;
    /**
     * 流id
     */
    @Schema(description = "流id")
    private String params;
    /**
     * 流id
     */
    @Schema(description = "流id")
    private String stream;
    /**
     * 虚拟主机
     */
    @Schema(description = "虚拟主机")
    private String vhost;
    /**
     * 流地址
     */
    @Schema(description = "流地址")
    private String url;
    /**
     * 重试次数
     */
    @Schema(description = "重试次数")
    private Integer retryCount;
    /**
     * 时间戳覆盖，不开启则没有该参数，1：绝对时间戳，2：系统时间戳，3：相对时间戳
     */
    @Schema(description = "时间戳覆盖")
    private Integer modifyStamp;
    /**
     * 拉流方式[0:TCP,1:UDP,2:组播]
     */
    @Schema(description = "拉流方式")
    private Integer rtpType;
    /**
     * 超时重试
     */
    @Schema(description = "超时重试")
    private Integer timeoutSec;

    /**
     * 开启hls
     */
    @Schema(description = "开启hls")
    private Boolean enableHls;
    /**
     * 开启hls-fmp4
     */
    @Schema(description = "开启hls-fmp4")
    private Boolean enableHlsFmp4;
    /**
     * 开启MP4
     */
    @Schema(description = "开启MP4")
    private Boolean enableMp4;
    /**
     * 开启rtsp
     */
    @Schema(description = "开启rtsp")
    private Boolean enableRtsp;
    /**
     * 开启rtmp/flv
     */
    @Schema(description = "开启rtmp/flv")
    private Boolean enableRtmp;
    /**
     * 开启ts
     */
    @Schema(description = "开启ts")
    private Boolean enableTs;
    /**
     * 开启fmp4
     */
    @Schema(description = "开启fmp4")
    private Boolean enableFmp4;
    /**
     * 按需生成hls
     */
    @Schema(description = "按需生成hls")
    private Boolean hlsDemand;
    /**
     * 按需生成rtsp
     */
    @Schema(description = "按需生成rtsp")
    private Boolean rtspDemand;
    /**
     * 按需生成rtmp
     */
    @Schema(description = "按需生成rtmp")
    private Boolean rtmpDemand;
    /**
     * 按需生成ts
     */
    @Schema(description = "按需生成ts")
    private Boolean tsDemand;

    /**
     * 按需生成fmp4
     */
    @Schema(description = "按需生成fmp4")
    private Boolean fmp4Demand;
    /**
     * 开启声音
     */
    @Schema(description = "开启声音")
    private Boolean enableAudio;
    /**
     * 添加静音音轨
     */
    @Schema(description = "添加静音音轨")
    private Boolean addMuteAudio;
    /**
     * MP4录制当作观看者参与播放人数计数
     */
    @Schema(description = "MP4录制当作观看者参与播放人数计数")
    private Boolean mp4AsPlayer;
    /**
     * 无人观看自动关闭
     */
    @Schema(description = "无人观看自动关闭")
    private Boolean autoClose;

    /**
     * mp4录像保存目录
     */
    @Schema(description = "mp4录像保存目录")
    private String mp4SavePath;

    /**
     * mp4切片大小(s)
     */
    @Schema(description = "mp4切片大小(s)")
    private Integer mp4MaxSecond;

    /**
     * hls文件保存目录
     */
    @Schema(description = "hls文件保存目录")
    private String hlsSavePath;

    /**
     * hls切片大小(s)
     */
    @Schema(description = "hls切片大小(s)")
    private String node;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private Long  creator;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private Long  updater;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String  creatorName;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private String  updaterName;

}
