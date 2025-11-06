<template>
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1400" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
			<!--	展示内容	-->
		<el-alert v-if="mode =='show'" title="以下信息均来自于ZLMediaKit节点的配置信息，项目启动的时候会根据application-*.yml中的ZLM节点的配置信息自动调用/setServerConfig修改替换！" type="warning" show-icon :closable="false"/>
		<el-card shadow="never"  v-show="mode == 'show'">

			<el-descriptions title="api信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="apiDebug" :width="200">{{serverConfig['api.apiDebug']}}</el-descriptions-item>
				<el-descriptions-item label="defaultSnap" :width="200">{{serverConfig['api.defaultSnap']}}</el-descriptions-item>
				<el-descriptions-item label="downloadRoot" :width="200">{{serverConfig['api.downloadRoot']}}</el-descriptions-item>
				<el-descriptions-item label="snapRoot" :width="200">{{serverConfig['api.snapRoot']}}</el-descriptions-item>
				<el-descriptions-item label="secret" :width="200">{{serverConfig['api.secret']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="cluster信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="apiDebug" :width="200">{{serverConfig['cluster.origin_url']}}</el-descriptions-item>
				<el-descriptions-item label="defaultSnap" :width="200">{{serverConfig['cluster.retry_count']}}</el-descriptions-item>
				<el-descriptions-item label="downloadRoot" :width="200">{{serverConfig['cluster.timeout_sec']}}</el-descriptions-item>
			</el-descriptions>


			<el-descriptions title="multicast信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="addrMax" :width="200">{{serverConfig['multicast.addrMax']}}</el-descriptions-item>
				<el-descriptions-item label="addrMin" :width="200">{{serverConfig['multicast.addrMin']}}</el-descriptions-item>
				<el-descriptions-item label="udpTTL" :width="200">{{serverConfig['multicast.udpTTL']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="record信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="appName" :width="200">{{serverConfig['record.appName']}}</el-descriptions-item>
				<el-descriptions-item label="enableFmp4" :width="200">{{serverConfig['record.enableFmp4']}}</el-descriptions-item>
				<el-descriptions-item label="fastStart" :width="200">{{serverConfig['record.']}}</el-descriptions-item>
				<el-descriptions-item label="fileBufSize" :width="200">{{serverConfig['record.fileBufSize']}}</el-descriptions-item>
				<el-descriptions-item label="fileRepeat" :width="200">{{serverConfig['record.fileRepeat']}}</el-descriptions-item>
				<el-descriptions-item label="sampleMS" :width="200">{{serverConfig['record.sampleMS']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="ffmpeg信息：" :column="2" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="bin" :width="200">{{serverConfig['ffmpeg.bin']}}</el-descriptions-item>
				<el-descriptions-item label="log" :width="200">{{serverConfig['ffmpeg.log']}}</el-descriptions-item>
				<el-descriptions-item label="restart_sec" :width="200">{{serverConfig['ffmpeg.restart_sec']}}</el-descriptions-item>
				<el-descriptions-item label="snap" :width="200">{{serverConfig['ffmpeg.snap']}}</el-descriptions-item>
				<el-descriptions-item label="cmd" :width="200">{{serverConfig['ffmpeg.cmd']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="general信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="broadcast_player_count_changed" :width="200">{{serverConfig['general.broadcast_player_count_changed']}}</el-descriptions-item>
				<el-descriptions-item label="check_nvidia_dev" :width="200">{{serverConfig['general.check_nvidia_dev']}}</el-descriptions-item>
				<el-descriptions-item label="enable_ffmpeg_log" :width="200">{{serverConfig['general.enable_ffmpeg_log']}}</el-descriptions-item>
				<el-descriptions-item label="enableVhost" :width="200">{{serverConfig['general.enableVhost']}}</el-descriptions-item>
				<el-descriptions-item label="flowThreshold" :width="200">{{serverConfig['general.flowThreshold']}}</el-descriptions-item>
				<el-descriptions-item label="listen_ip" :width="200">{{serverConfig['general.listen_ip']}}</el-descriptions-item>
				<el-descriptions-item label="maxStreamWaitMS" :width="200">{{serverConfig['general.maxStreamWaitMS']}}</el-descriptions-item>
				<el-descriptions-item label="mediaServerId" :width="200">{{serverConfig['general.mediaServerId']}}</el-descriptions-item>
				<el-descriptions-item label="mergeWriteMS" :width="200">{{serverConfig['general.mergeWriteMS']}}</el-descriptions-item>
				<el-descriptions-item label="resetWhenRePlay" :width="200">{{serverConfig['general.resetWhenRePlay']}}</el-descriptions-item>
				<el-descriptions-item label="streamNoneReaderDelayMS" :width="200">{{serverConfig['general.streamNoneReaderDelayMS']}}</el-descriptions-item>
				<el-descriptions-item label="unready_frame_cache" :width="200">{{serverConfig['general.unready_frame_cache']}}</el-descriptions-item>
				<el-descriptions-item label="wait_add_track_ms" :width="200">{{serverConfig['general.wait_add_track_ms']}}</el-descriptions-item>
				<el-descriptions-item label="wait_audio_track_data_ms" :width="200">{{serverConfig['general.wait_audio_track_data_ms']}}</el-descriptions-item>
				<el-descriptions-item label="wait_track_ready_ms" :width="200">{{serverConfig['general.wait_track_ready_ms']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="hls信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="broadcastRecordTs" :width="200">{{serverConfig['hls.broadcastRecordTs']}}</el-descriptions-item>
				<el-descriptions-item label="deleteDelaySec" :width="200">{{serverConfig['hls.deleteDelaySec']}}</el-descriptions-item>
				<el-descriptions-item label="fastRegister" :width="200">{{serverConfig['hls.fastRegister']}}</el-descriptions-item>
				<el-descriptions-item label="fileBufSize" :width="200">{{serverConfig['hls.fileBufSize']}}</el-descriptions-item>
				<el-descriptions-item label="segDelay" :width="200">{{serverConfig['hls.segDelay']}}</el-descriptions-item>
				<el-descriptions-item label="segDur" :width="200">{{serverConfig['hls.segDur']}}</el-descriptions-item>
				<el-descriptions-item label="segKeep" :width="200">{{serverConfig['hls.segKeep']}}</el-descriptions-item>
				<el-descriptions-item label="segNum" :width="200">{{serverConfig['hls.segNum']}}</el-descriptions-item>
				<el-descriptions-item label="segRetain" :width="200">{{serverConfig['hls.segRetain']}}</el-descriptions-item>
			</el-descriptions>


			<el-descriptions title="rtmp信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="directProxy" :width="200">{{serverConfig['rtmp.directProxy']}}</el-descriptions-item>
				<el-descriptions-item label="enhanced" :width="200">{{serverConfig['rtmp.enhanced']}}</el-descriptions-item>
				<el-descriptions-item label="handshakeSecond" :width="200">{{serverConfig['rtmp.handshakeSecond']}}</el-descriptions-item>
				<el-descriptions-item label="keepAliveSecond" :width="200">{{serverConfig['rtmp.keepAliveSecond']}}</el-descriptions-item>
				<el-descriptions-item label="port" :width="200">{{serverConfig['rtmp.port']}}</el-descriptions-item>
				<el-descriptions-item label="sslport" :width="200">{{serverConfig['rtmp.sslport']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="rtp信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="audioMtuSize" :width="200">{{serverConfig['rtp.audioMtuSize']}}</el-descriptions-item>
				<el-descriptions-item label="h264_stap_a" :width="200">{{serverConfig['rtp.h264_stap_a']}}</el-descriptions-item>
				<el-descriptions-item label="lowLatency" :width="200">{{serverConfig['rtp.lowLatency']}}</el-descriptions-item>
				<el-descriptions-item label="rtpMaxSize" :width="200">{{serverConfig['rtp.rtpMaxSize']}}</el-descriptions-item>
				<el-descriptions-item label="videoMtuSize" :width="200">{{serverConfig['rtp.videoMtuSize']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="rtp_proxy信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="dumpDir" :width="200">{{serverConfig['rtp_proxy.dumpDir']}}</el-descriptions-item>
				<el-descriptions-item label="gop_cache" :width="200">{{serverConfig['rtp_proxy.gop_cache']}}</el-descriptions-item>
				<el-descriptions-item label="h264_pt" :width="200">{{serverConfig['rtp_proxy.h264_pt']}}</el-descriptions-item>
				<el-descriptions-item label="h265_pt" :width="200">{{serverConfig['rtp_proxy.h265_pt']}}</el-descriptions-item>
				<el-descriptions-item label="merge_frame" :width="200">{{serverConfig['rtp_proxy.merge_frame']}}</el-descriptions-item>
				<el-descriptions-item label="opus_pt" :width="200">{{serverConfig['rtp_proxy.opus_pt']}}</el-descriptions-item>
				<el-descriptions-item label="port" :width="200">{{serverConfig['rtp_proxy.port']}}</el-descriptions-item>
				<el-descriptions-item label="port_range" :width="200">{{serverConfig['rtp_proxy.port_range']}}</el-descriptions-item>
				<el-descriptions-item label="ps_pt" :width="200">{{serverConfig['rtp_proxy.ps_pt']}}</el-descriptions-item>
				<el-descriptions-item label="rtp_g711_dur_ms" :width="200">{{serverConfig['rtp_proxy.rtp_g711_dur_ms']}}</el-descriptions-item>
				<el-descriptions-item label="timeoutSec" :width="200">{{serverConfig['rtp_proxy.timeoutSec']}}</el-descriptions-item>
				<el-descriptions-item label="udp_recv_socket_buffer" :width="200">{{serverConfig['rtp_proxy.udp_recv_socket_buffer']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="rtsp信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="authBasic" :width="200">{{serverConfig['rtsp.authBasic']}}</el-descriptions-item>
				<el-descriptions-item label="directProxy" :width="200">{{serverConfig['rtsp.directProxy']}}</el-descriptions-item>
				<el-descriptions-item label="handshakeSecond" :width="200">{{serverConfig['rtsp.handshakeSecond']}}</el-descriptions-item>
				<el-descriptions-item label="keepAliveSecond" :width="200">{{serverConfig['rtsp.keepAliveSecond']}}</el-descriptions-item>
				<el-descriptions-item label="lowLatency" :width="200">{{serverConfig['rtsp.lowLatency']}}</el-descriptions-item>
				<el-descriptions-item label="port" :width="200">{{serverConfig['rtsp.port']}}</el-descriptions-item>
				<el-descriptions-item label="rtpTransportType" :width="200">{{serverConfig['rtsp.rtpTransportType']}}</el-descriptions-item>
				<el-descriptions-item label="sslport" :width="200">{{serverConfig['rtsp.sslport']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="rtc信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="bfilter" :width="200">{{serverConfig['rtc.bfilter']}}</el-descriptions-item>
				<el-descriptions-item label="datachannel_echo" :width="200">{{serverConfig['rtc.datachannel_echo']}}</el-descriptions-item>
				<el-descriptions-item label="enableTurn" :width="200">{{serverConfig['rtc.enableTurn']}}</el-descriptions-item>
				<el-descriptions-item label="externIP" :width="200">{{serverConfig['rtc.externIP']}}</el-descriptions-item>
				<el-descriptions-item label="icePort" :width="200">{{serverConfig['rtc.icePort']}}</el-descriptions-item>
				<el-descriptions-item label="icePwd" :width="200">{{serverConfig['rtc.icePwd']}}</el-descriptions-item>
				<el-descriptions-item label="iceTcpPort" :width="200">{{serverConfig['rtc.iceTcpPort']}}</el-descriptions-item>
				<el-descriptions-item label="iceTransportPolicy" :width="200">{{serverConfig['rtc.iceTransportPolicy']}}</el-descriptions-item>
				<el-descriptions-item label="iceUfrag" :width="200">{{serverConfig['rtc.iceUfrag']}}</el-descriptions-item>
				<el-descriptions-item label="interfaces" :width="200">{{serverConfig['rtc.interfaces']}}</el-descriptions-item>
				<el-descriptions-item label="max_bitrate" :width="200">{{serverConfig['rtc.max_bitrate']}}</el-descriptions-item>
				<el-descriptions-item label="max_stun_retry" :width="200">{{serverConfig['rtc.max_stun_retry']}}</el-descriptions-item>
				<el-descriptions-item label="maxRtpCacheMS" :width="200">{{serverConfig['rtc.maxRtpCacheMS']}}</el-descriptions-item>
				<el-descriptions-item label="maxRtpCacheSize" :width="200">{{serverConfig['rtc.maxRtpCacheSize']}}</el-descriptions-item>
				<el-descriptions-item label="min_bitrate" :width="200">{{serverConfig['rtc.min_bitrate']}}</el-descriptions-item>
				<el-descriptions-item label="nackIntervalRatio" :width="200">{{serverConfig['rtc.nackIntervalRatio']}}</el-descriptions-item>
				<el-descriptions-item label="nackMaxCount" :width="200">{{serverConfig['rtc.nackMaxCount']}}</el-descriptions-item>
				<el-descriptions-item label="nackMaxMS" :width="200">{{serverConfig['rtc.nackMaxMS']}}</el-descriptions-item>
				<el-descriptions-item label="nackMaxSize" :width="200">{{serverConfig['rtc.nackMaxSize']}}</el-descriptions-item>
				<el-descriptions-item label="nackRtpSize" :width="200">{{serverConfig['rtc.nackRtpSize']}}</el-descriptions-item>
				<el-descriptions-item label="port" :width="200">{{serverConfig['rtc.port']}}</el-descriptions-item>
				<el-descriptions-item label="portRange" :width="200">{{serverConfig['rtc.portRange']}}</el-descriptions-item>
				<el-descriptions-item label="preferredCodecA" :width="200">{{serverConfig['rtc.preferredCodecA']}}</el-descriptions-item>
				<el-descriptions-item label="preferredCodecV" :width="200">{{serverConfig['rtc.preferredCodecV']}}</el-descriptions-item>
				<el-descriptions-item label="rembBitRate" :width="200">{{serverConfig['rtc.rembBitRate']}}</el-descriptions-item>
				<el-descriptions-item label="signalingPort" :width="200">{{serverConfig['rtc.signalingPort']}}</el-descriptions-item>
				<el-descriptions-item label="signalingSslPort" :width="200">{{serverConfig['rtc.signalingSslPort']}}</el-descriptions-item>
				<el-descriptions-item label="start_bitrate" :width="200">{{serverConfig['rtc.start_bitrate']}}</el-descriptions-item>
				<el-descriptions-item label="tcpPort" :width="200">{{serverConfig['rtc.tcpPort']}}</el-descriptions-item>
				<el-descriptions-item label="timeoutSec" :width="200">{{serverConfig['rtc.timeoutSec']}}</el-descriptions-item>
			</el-descriptions>


			<el-descriptions title="http信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="allow_cross_domains" :width="200">{{serverConfig['http.allow_cross_domains']}}</el-descriptions-item>
				<el-descriptions-item label="charSet" :width="200">{{serverConfig['http.charSet']}}</el-descriptions-item>
				<el-descriptions-item label="dirMenu" :width="200">{{serverConfig['http.dirMenu']}}</el-descriptions-item>
				<el-descriptions-item label="forwarded_ip_header" :width="200">{{serverConfig['http.forwarded_ip_header']}}</el-descriptions-item>
				<el-descriptions-item label="forbidCacheSuffix" :width="200">{{serverConfig['http.forbidCacheSuffix']}}</el-descriptions-item>
				<el-descriptions-item label="keepAliveSecond" :width="200">{{serverConfig['http.keepAliveSecond']}}</el-descriptions-item>
				<el-descriptions-item label="maxReqSize" :width="200">{{serverConfig['http.maxReqSize']}}</el-descriptions-item>
				<el-descriptions-item label="port" :width="200">{{serverConfig['http.port']}}</el-descriptions-item>
				<el-descriptions-item label="rootPath" :width="200">{{serverConfig['http.rootPath']}}</el-descriptions-item>
				<el-descriptions-item label="sendBufSize" :width="200">{{serverConfig['http.sendBufSize']}}</el-descriptions-item>
				<el-descriptions-item label="sslport" :width="200">{{serverConfig['http.sslport']}}</el-descriptions-item>
				<el-descriptions-item label="virtualPath" :width="200">{{serverConfig['http.virtualPath']}}</el-descriptions-item>
				<el-descriptions-item label="allow_ip_range" :width="200">{{serverConfig['http.allow_ip_range']}}</el-descriptions-item>
				<el-descriptions-item label="notFound" :width="200">{{serverConfig['http.notFound']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="shell信息：" :column="2" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="maxReqSize" :width="200">{{serverConfig['shell.maxReqSize']}}</el-descriptions-item>
				<el-descriptions-item label="port" :width="200">{{serverConfig['shell.port']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="srt信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="latencyMul" :width="200">{{serverConfig['srt.latencyMul']}}</el-descriptions-item>
				<el-descriptions-item label="passPhrase" :width="200">{{serverConfig['srt.passPhrase']}}</el-descriptions-item>
				<el-descriptions-item label="pktBufSize" :width="200">{{serverConfig['srt.pktBufSize']}}</el-descriptions-item>
				<el-descriptions-item label="port" :width="200">{{serverConfig['srt.port']}}</el-descriptions-item>
				<el-descriptions-item label="timeoutSec" :width="200">{{serverConfig['srt.timeoutSec']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="hook信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="alive_interval" :width="200">{{serverConfig['hook.alive_interval']}}</el-descriptions-item>
				<el-descriptions-item label="enable" :width="200">{{serverConfig['hook.enable']}}</el-descriptions-item>
				<el-descriptions-item label="on_flow_report" :width="200">{{serverConfig['hook.on_flow_report']}}</el-descriptions-item>
				<el-descriptions-item label="on_http_access" :width="200">{{serverConfig['hook.on_http_access']}}</el-descriptions-item>
				<el-descriptions-item label="on_play" :width="200">{{serverConfig['hook.on_play']}}</el-descriptions-item>
				<el-descriptions-item label="on_publish" :width="200">{{serverConfig['hook.on_publish']}}</el-descriptions-item>
				<el-descriptions-item label="on_record_mp4" :width="200">{{serverConfig['hook.on_record_mp4']}}</el-descriptions-item>
				<el-descriptions-item label="on_record_ts" :width="200">{{serverConfig['hook.on_record_ts']}}</el-descriptions-item>
				<el-descriptions-item label="on_rtp_server_timeout" :width="200">{{serverConfig['hook.on_rtp_server_timeout']}}</el-descriptions-item>
				<el-descriptions-item label="on_rtsp_auth" :width="200">{{serverConfig['hook.on_rtsp_auth']}}</el-descriptions-item>
				<el-descriptions-item label="on_rtsp_realm" :width="200">{{serverConfig['hook.on_rtsp_realm']}}</el-descriptions-item>
				<el-descriptions-item label="on_send_rtp_stopped" :width="200">{{serverConfig['hook.on_send_rtp_stopped']}}</el-descriptions-item>
				<el-descriptions-item label="on_server_exited" :width="200">{{serverConfig['hook.on_server_exited']}}</el-descriptions-item>
				<el-descriptions-item label="on_server_keepalive" :width="200">{{serverConfig['hook.on_server_keepalive']}}</el-descriptions-item>
				<el-descriptions-item label="on_server_started" :width="200">{{serverConfig['hook.on_server_started']}}</el-descriptions-item>
				<el-descriptions-item label="on_shell_login" :width="200">{{serverConfig['hook.on_shell_login']}}</el-descriptions-item>
				<el-descriptions-item label="on_stream_changed" :width="200">{{serverConfig['hook.on_stream_changed']}}</el-descriptions-item>
				<el-descriptions-item label="on_stream_none_reader" :width="200">{{serverConfig['hook.on_stream_none_reader']}}</el-descriptions-item>
				<el-descriptions-item label="on_stream_not_found" :width="200">{{serverConfig['hook.on_stream_not_found']}}</el-descriptions-item>
				<el-descriptions-item label="retry" :width="200">{{serverConfig['hook.retry']}}</el-descriptions-item>
				<el-descriptions-item label="retry_delay" :width="200">{{serverConfig['hook.retry_delay']}}</el-descriptions-item>
				<el-descriptions-item label="timeoutSec" :width="200">{{serverConfig['hook.timeoutSec']}}</el-descriptions-item>
				<el-descriptions-item label="stream_changed_schemas" :width="200">{{serverConfig['hook.stream_changed_schemas']}}</el-descriptions-item>
			</el-descriptions>

			<el-descriptions title="protocol信息：" :column="3" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="add_mute_audio" :width="200">{{serverConfig['protocol.add_mute_audio']}}</el-descriptions-item>
				<el-descriptions-item label="auto_close" :width="200">{{serverConfig['protocol.auto_close']}}</el-descriptions-item>
				<el-descriptions-item label="continue_push_ms" :width="200">{{serverConfig['protocol.continue_push_ms']}}</el-descriptions-item>
				<el-descriptions-item label="enable_audio" :width="200">{{serverConfig['protocol.enable_audio']}}</el-descriptions-item>
				<el-descriptions-item label="enable_fmp4" :width="200">{{serverConfig['protocol.enable_fmp4']}}</el-descriptions-item>
				<el-descriptions-item label="enable_hls" :width="200">{{serverConfig['protocol.enable_hls']}}</el-descriptions-item>
				<el-descriptions-item label="enable_hls_fmp4" :width="200">{{serverConfig['protocol.enable_hls_fmp4']}}</el-descriptions-item>
				<el-descriptions-item label="enable_mp4" :width="200">{{serverConfig['protocol.enable_mp4']}}</el-descriptions-item>
				<el-descriptions-item label="enable_rtsp" :width="200">{{serverConfig['protocol.enable_rtsp']}}</el-descriptions-item>
				<el-descriptions-item label="enable_rtmp" :width="200">{{serverConfig['protocol.enable_rtmp']}}</el-descriptions-item>
				<el-descriptions-item label="enable_ts" :width="200">{{serverConfig['protocol.enable_ts']}}</el-descriptions-item>
				<el-descriptions-item label="fmp4_demand" :width="200">{{serverConfig['protocol.fmp4_demand']}}</el-descriptions-item>
				<el-descriptions-item label="hls_demand" :width="200">{{serverConfig['protocol.hls_demand']}}</el-descriptions-item>
				<el-descriptions-item label="hls_save_path" :width="200">{{serverConfig['protocol.hls_save_path']}}</el-descriptions-item>
				<el-descriptions-item label="modify_stamp" :width="200">{{serverConfig['protocol.modify_stamp']}}</el-descriptions-item>
				<el-descriptions-item label="mp4_as_player" :width="200">{{serverConfig['protocol.mp4_as_player']}}</el-descriptions-item>
				<el-descriptions-item label="mp4_max_second" :width="200">{{serverConfig['protocol.mp4_max_second']}}</el-descriptions-item>
				<el-descriptions-item label="mp4_save_path" :width="200">{{serverConfig['protocol.mp4_save_path']}}</el-descriptions-item>
				<el-descriptions-item label="paced_sender_ms" :width="200">{{serverConfig['protocol.paced_sender_ms']}}</el-descriptions-item>
				<el-descriptions-item label="rtsp_demand" :width="200">{{serverConfig['protocol.rtsp_demand']}}</el-descriptions-item>
				<el-descriptions-item label="rtmp_demand" :width="200">{{serverConfig['protocol.rtmp_demand']}}</el-descriptions-item>
				<el-descriptions-item label="ts_demand" :width="200">{{serverConfig['protocol.ts_demand']}}</el-descriptions-item>
			</el-descriptions>

		</el-card>
				<template #footer>
<!--					<el-button v-if="mode!='show'" @click="visible=false" >取 消</el-button>
					<el-button v-if="mode!='show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>-->
					<el-button v-if="mode =='show'" @click="visible=false" >关 闭</el-button>
				</template>
	</sc-dialog>
</template>

<script>
import scFileImport from "@/components/scFileImport/index.vue";

export default {
	components: {scFileImport},
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "show",
			titleMap: {
				show: 'ZLMediaKit节点配置信息'
			},
			visible: false,
			isSaveing: false,
			serverConfig:{
			},
			cameraZLMNode:{
			},
		}
	},

	mounted() {
	},
	methods: {

		open(mode='add'){
			this.mode = mode;
			this.visible = true;
			return this
		},

		setData(data){
			Object.assign(this.cameraZLMNode, data)
			this.getZLMServerConfig(data);
		},

		async getZLMServerConfig(data){
			var res = await this.$API.auth.settings.getZLMServerConfig.post(data);
			if(res.code == 200){
				this.serverConfig = res.data.data[0];
				console.log(this.serverConfig)
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})

			}

		},
	}
}
</script>


<style>
.custom-margin-top{
	margin-top: 20px;
	justify-content: flex-end;
}
.align-right {
	display: flex;
	justify-content: flex-end;
}
</style>
