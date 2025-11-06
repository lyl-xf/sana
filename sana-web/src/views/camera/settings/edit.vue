<template>
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1300" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-tabs tab-position="top">

			<!--	常用设置		-->
			<el-tab-pane label="常用设置" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="通用设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="180px" class="custom-form">

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="check_nvidia_dev" prop="general.check_nvidia_dev">
								<el-select  v-model="serverConfig['general.check_nvidia_dev']" placeholder="general.check_nvidia_dev"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="启用虚拟主机" prop="general.enableVhost">
								<el-select  v-model="serverConfig['general.enableVhost']" placeholder="general.enableVhost"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="服务器唯一id" prop="general.mediaServerId">
								<el-input v-model="serverConfig['general.mediaServerId']" placeholder="请输入边缘实例名称" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="启用FFMPEG日志" prop="general.enable_ffmpeg_log'">
								<el-select  v-model="serverConfig['general.enable_ffmpeg_log']" placeholder="general.enable_ffmpeg_log"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >

							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="流超时时间(ms)" prop="general.maxStreamWaitMS">
								<el-input v-model="serverConfig['general.maxStreamWaitMS']" placeholder="流超时时间(ms)" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="合并写缓存(ms)" prop="general.mergeWriteMS">
								<el-input v-model="serverConfig['general.mergeWriteMS']"  placeholder="合并写缓存(ms)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="断连删除" prop="general.resetWhenRePlay">
								<el-select  v-model="serverConfig['general.resetWhenRePlay']" placeholder="general.resetWhenRePlay"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>


						</el-col>
						<el-col :span="12">
							<el-form-item label="无人观看释放超时" prop="general.streamNoneReaderDelayMS">
								<el-input v-model="serverConfig['general.streamNoneReaderDelayMS']"  placeholder="无人观看释放超时(ms)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="未就绪缓存帧数" prop="general.unready_frame_cache">
								<el-input v-model="serverConfig['general.unready_frame_cache']" placeholder="未就绪缓存帧数" class="custom-width" clearable></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="加流超时(ms)" prop="general.wait_add_track_ms">
								<el-input v-model="serverConfig['general.wait_add_track_ms']"  placeholder="加流超时(ms)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="流就绪超时(ms)" prop="general.wait_track_ready_ms">
								<el-input v-model="serverConfig['general.wait_track_ready_ms']" placeholder="流就绪超时(ms)" class="custom-width" clearable></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="启用虚拟主机" prop="general.flowThreshold">
								<el-input v-model="serverConfig['general.flowThreshold']" placeholder="启用虚拟主机" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>


				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="API设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="调试" prop="api.apiDebug">
								<el-select  v-model="serverConfig['api.apiDebug']" placeholder="api.apiDebug"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="密钥(ms)" prop="edgeSn">
								<el-input v-model="serverConfig['api.secret']" placeholder="密钥(ms)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="截图路径" prop="edgeName">
								<el-input v-model="serverConfig['api.snapRoot']" placeholder="截图路径" class="custom-width" clearable></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="缺省截图" prop="edgeSn">
								<el-input v-model="serverConfig['api.defaultSnap']" placeholder="缺省截图" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>



				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="cluster设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="源站拉流url模板" prop="cluster.origin_url">
								<el-input v-model="serverConfig['cluster.origin_url']" placeholder="源站拉流url模板" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="溯源总超时" prop="cluster.retry_count">
								<el-input v-model="serverConfig['cluster.retry_count']" placeholder="溯源总超时" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="溯源失败重试次数" prop="cluster.timeout_sec">
								<el-input v-model="serverConfig['cluster.timeout_sec']" placeholder="溯源失败重试次数" class="custom-width" clearable></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>

			</el-tab-pane>

			<!--	功能设置		-->
			<el-tab-pane label="功能设置" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="FFMPEG设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="ffmpeg路径" prop="ffmpeg.bin">
								<el-input v-model="serverConfig['ffmpeg.bin']" placeholder="ffmpeg路径" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="拉流再推流命令模板" prop="ffmpeg.cmd">
								<el-input v-model="serverConfig['ffmpeg.cmd']" placeholder="拉流再推流命令模板" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="日志路径" prop="ffmpeg.log">
								<el-input v-model="serverConfig['ffmpeg.log']" placeholder="拉流再推流命令模板" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="自动重启时间" prop="ffmpeg.restart_sec">
								<el-input v-model="serverConfig['ffmpeg.restart_sec']" placeholder="自动重启时间" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="截图模板" prop="ffmpeg.snap">
								<el-input v-model="serverConfig['ffmpeg.snap']" placeholder="截图模板" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>


				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="Hook设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="启用hook事件" prop="hook.enable">
								<el-select  v-model="serverConfig['hook.enable']" placeholder="启用hook事件"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="alive心跳间隔(s)" v-if="serverConfig['hook.enable'] == '1'" prop="hook.alive_interval">
								<el-input v-model="serverConfig['hook.alive_interval']" placeholder="alive心跳间隔(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>


					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="Hook请求超时(s)" v-if="serverConfig['hook.enable'] == '1'" prop="hook.timeoutSec">
								<el-input v-model="serverConfig['hook.timeoutSec']" placeholder="Hook请求超时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="失败重试次数" v-if="serverConfig['hook.enable'] == '1'" prop="hook.retry">
								<el-input v-model="serverConfig['hook.retry']" placeholder="失败重试次数" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="失败重试延时(s)" v-if="serverConfig['hook.enable'] == '1'" prop="hook.retry_delay">
								<el-input v-model="serverConfig['hook.retry_delay']" placeholder="失败重试延时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="保活上报" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_server_keepalive">
								<el-input v-model="serverConfig['hook.on_server_keepalive']" placeholder="保活上报" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="流量事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_flow_report">
								<el-input v-model="serverConfig['hook.on_flow_report']" placeholder="流量事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="http文件鉴权事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_http_access">
								<el-input v-model="serverConfig['hook.on_http_access']" placeholder="http文件鉴权事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="播放鉴权事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_play">
								<el-input v-model="serverConfig['hook.on_play']" placeholder="播放鉴权事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="推流鉴权事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_publish">
								<el-input v-model="serverConfig['hook.on_publish']" placeholder="推流鉴权事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="mp4切片录事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_record_mp4">
								<el-input v-model="serverConfig['hook.on_record_mp4']" placeholder="mp4切片录事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="ts切片录事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_record_ts">
								<el-input v-model="serverConfig['hook.on_record_ts']" placeholder="ts切片录事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="rtp服务超时事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_rtp_server_timeout">
								<el-input v-model="serverConfig['hook.on_rtp_server_timeout']" placeholder="rtp服务超时事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="rtsp播放鉴权事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_rtsp_auth">
								<el-input v-model="serverConfig['hook.on_rtsp_auth']" placeholder="rtsp播放鉴权事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="rtsp专属鉴权事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_rtsp_realm">
								<el-input v-model="serverConfig['hook.on_rtsp_realm']" placeholder="rtsp专属鉴权事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="发送rtp被动关闭" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_send_rtp_stopped">
								<el-input v-model="serverConfig['hook.on_send_rtp_stopped']" placeholder="发送rtp被动关闭" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="服务启动事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_server_started">
								<el-input v-model="serverConfig['hook.on_server_started']" placeholder="服务启动事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="服务关闭事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_server_exited">
								<el-input v-model="serverConfig['hook.on_server_exited']" placeholder="服务关闭事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="telnet调试鉴权事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_shell_login">
								<el-input v-model="serverConfig['hook.on_shell_login']" placeholder="telnet调试鉴权事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="流注册或注销事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_stream_changed">
								<el-input v-model="serverConfig['hook.on_stream_changed']" placeholder="流注册或注销事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="无人观看流事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_stream_none_reader">
								<el-input v-model="serverConfig['hook.on_stream_none_reader']" placeholder="无人观看流事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="播放未找到流事件" v-if="serverConfig['hook.enable'] == '1'" prop="hook.on_stream_not_found">
								<el-input v-model="serverConfig['hook.on_stream_not_found']" placeholder="播放未找到流事件" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-tab-pane>


			<!--	HLS/HTTP设置		-->
			<el-tab-pane  label="HLS/HTTP设置" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="HLS设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="广播hls切片" prop="hls.broadcastRecordTs">
								<el-select  v-model="serverConfig['hls.broadcastRecordTs']" placeholder="广播hls切片"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="删除延时(s)"  prop="hls.deleteDelaySec">
								<el-input v-model="serverConfig['hls.deleteDelaySec']" placeholder="删除延时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>


					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="文件缓存大小"  prop="hls.fileBufSize">
								<el-input v-model="serverConfig['hls.fileBufSize']" placeholder="文件缓存大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="切片时长(s)"  prop="hls.segDur">
								<el-input v-model="serverConfig['hls.segDur']" placeholder="切片时长(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>


					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="切片个数"  prop="hls.segNum">
								<el-input v-model="serverConfig['hls.segNum']" placeholder="切片个数" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="预留切片数"  prop="hls.segRetain">
								<el-input v-model="serverConfig['hls.segRetain']" placeholder="预留切片数" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="保留hls文件" prop="hls.segKeep">
								<el-select  v-model="serverConfig['hls.segKeep']" placeholder="保留hls文件"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>

<!--				<el-col :span="12">
					<el-form-item label="广播hls切片" prop="hls.broadcastRecordTs">
						<el-select  v-model="serverConfig['hls.broadcastRecordTs']" placeholder="广播hls切片"  class="custom-width">
							<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
						</el-select >
					</el-form-item>
				</el-col>-->


				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="HTTP设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="端口"  prop="http.port">
								<el-input v-model="serverConfig['http.port']" placeholder="端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="SSL端口"  prop="http.sslport">
								<el-input v-model="serverConfig['http.sslport']" placeholder="SSL端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>


					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="编码"  prop="http.charSet">
								<el-select  v-model="serverConfig['http.charSet']" placeholder="编码"  class="custom-width">
									<el-option v-for="item in configOption.general.charType" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >

							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="超时时间"  prop="http.keepAliveSecond">
								<el-input v-model="serverConfig['http.keepAliveSecond']" placeholder="超时时间" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>


					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="最大请求大小(字节)"  prop="http.maxReqSize">
								<el-input v-model="serverConfig['http.maxReqSize']" placeholder="最大请求大小(字节)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="404网页内容"  prop="http.notFound">
								<el-input v-model="serverConfig['http.notFound']" placeholder="404网页内容" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="根目录"  prop="http.rootPath">
								<el-input v-model="serverConfig['http.rootPath']" placeholder="根目录" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="发送缓存大小"  prop="http.sendBufSize">
								<el-input v-model="serverConfig['http.sendBufSize']" placeholder="发送缓存大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="禁止使用mmap缓存的文件后缀"  prop="http.forbidCacheSuffix">
								<el-input v-model="serverConfig['http.forbidCacheSuffix']" placeholder="禁止使用mmap缓存的文件后缀" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="forwarded_ip_header"  prop="http.forwarded_ip_header">
								<el-input v-model="serverConfig['http.forwarded_ip_header']" placeholder="forwarded_ip_header" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="显示目录列表"  prop="http.dirMenu">
								<el-select  v-model="serverConfig['http.dirMenu']" placeholder="显示目录列表"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="虚拟路径"  prop="http.virtualPath">
								<el-input v-model="serverConfig['http.virtualPath']" placeholder="虚拟路径" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="允许跨域请求"  prop="http.allow_cross_domains">
								<el-select  v-model="serverConfig['http.allow_cross_domains']" placeholder="允许跨域请求"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="Ip白名单"  prop="http.allow_ip_range">
								<el-input v-model="serverConfig['http.allow_ip_range']" placeholder="Ip白名单" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-tab-pane>



			<!--	protocol设置		-->
			<el-tab-pane  label="protocol设置" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="protocol设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="230px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="开启音频"  prop="protocol.enable_audio">
								<el-select  v-model="serverConfig['protocol.enable_audio']" placeholder="开启音频"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="添加静音音轨"  prop="protocol.add_mute_audio">
								<el-select  v-model="serverConfig['protocol.add_mute_audio']" placeholder="添加静音音轨"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="继续推流时间(ms)"  prop="protocol.continue_push_ms">
								<el-input v-model="serverConfig['protocol.continue_push_ms']" placeholder="继续推流时间(ms)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="将mp4录制当做观看者"  prop="protocol.mp4_as_player">
								<el-select  v-model="serverConfig['protocol.mp4_as_player']" placeholder="将mp4录制当做观看者"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="mp4切片大小(s)"  prop="protocol.mp4_max_second">
								<el-input v-model="serverConfig['protocol.mp4_max_second']" placeholder="mp4切片大小(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="mp4录像保存路径"  prop="protocol.mp4_save_path">
								<el-input v-model="serverConfig['protocol.mp4_save_path']" placeholder="mp4录像保存路径" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="hls录像保存路径"  prop="protocol.hls_save_path">
								<el-input v-model="serverConfig['protocol.hls_save_path']" placeholder="hls录像保存路径" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="开启帧级时间戳覆盖"  prop="protocol.modify_stamp">
								<el-input v-model="serverConfig['protocol.modify_stamp']" placeholder="开启帧级时间戳覆盖" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="无人观看时直接关闭"  prop="protocol.auto_close">
								<el-select  v-model="serverConfig['protocol.auto_close']" placeholder="无人观看时直接关闭"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="启用 http-fmp4/ws-fmp4"  prop="protocol.enable_fmp4">
								<el-select  v-model="serverConfig['protocol.enable_fmp4']" placeholder="启用 http-fmp4/ws-fmp4"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="启用 hls"  prop="protocol.enable_hls">
								<el-select  v-model="serverConfig['protocol.enable_hls']" placeholder="启用 hls"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="启用MP4录制"  prop="protocol.enable_mp4">
								<el-select  v-model="serverConfig['protocol.enable_mp4']" placeholder="启用MP4录制"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="启用 rtmp/flv"  prop="protocol.enable_rtmp">
								<el-select  v-model="serverConfig['protocol.enable_rtmp']" placeholder="启用 rtmp/flv"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="启用 rtsp"  prop="protocol.enable_rtsp">
								<el-select  v-model="serverConfig['protocol.enable_rtsp']" placeholder="启用 rtsp"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="启用 http-ts/ws-ts"  prop="protocol.enable_ts">
								<el-select  v-model="serverConfig['protocol.enable_ts']" placeholder="启用 http-ts/ws-ts"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="按需生成http-fmp4、ws-fmp4"  prop="protocol.fmp4_demand">
								<el-select  v-model="serverConfig['protocol.fmp4_demand']" placeholder="按需生成http-fmp4、ws-fmp4"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="按需生成 hls"  prop="protocol.hls_demand">
								<el-select  v-model="serverConfig['protocol.hls_demand']" placeholder="按需生成 hls"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="按需生成rtmp、http-flv、ws-flv"  prop="protocol.rtmp_demand">
								<el-select  v-model="serverConfig['protocol.rtmp_demand']" placeholder="按需生成rtmp、http-flv、ws-flv"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="按需生成 rtsp[s]"  prop="protocol.rtsp_demand">
								<el-select  v-model="serverConfig['protocol.rtsp_demand']" placeholder="按需生成 rtsp[s]"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="按需生成 http[s]-ts"  prop="protocol.ts_demand">
								<el-select  v-model="serverConfig['protocol.ts_demand']" placeholder="按需生成 http[s]-ts"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>

			</el-tab-pane>



		</el-tabs>
		<template #footer>
			<el-button @click="visible=false" >关 闭</el-button>
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
			mode: "edit",
			titleMap: {
				edit: 'ZLMediaKitServe服务器配置',
			},
			visible: false,
			configOption:{
				general:{
					yesNo:[
						{
							label: '否',
							value: "0"
						},
						{
							label: '是',
							value: "1"
						},
					],
					charType:[
						{
							label: 'utf-8',
							value: 'utf-8'
						},
						{
							label: 'GB2312',
							value: 'GB2312'
						},
					]
				},
			},

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
.custom-form {
	border: 1px solid #000; /* 黑色边框 */
	padding: 20px; /* 内边距，可以根据需要调整 */
	border-radius: 8px; /* 圆角，可以根据需要调整 */
}

.custom-margin-top {
	margin-top: 20px;
	justify-content: flex-end;
}

.align-right {
	display: flex;
	justify-content: flex-end;
}

.custom-padding-left {
	padding-left: 20px;
	padding-right: 20px;
}

.custom-width {
	width: 100%;
}
</style>
