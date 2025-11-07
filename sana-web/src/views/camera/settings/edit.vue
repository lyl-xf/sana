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
					<el-alert title="如果要修改secret参数，请注意，后端配置文件中application-*.yml 中 zlm对应的节点的secret也要修改，不然会导致API调用失败" type="warning" style="margin-bottom: 15px;"></el-alert>

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

				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="组播设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="组播udp ttl" prop="multicast.udpTTL">
								<el-input v-model="serverConfig['multicast.udpTTL']" placeholder="组播udp ttl" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="rtp组播起始地址" prop="multicast.addrMin">
								<el-input v-model="serverConfig['multicast.addrMin']" placeholder="rtp组播起始地址" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="rtp组播截止地址" prop="multicast.addrMax">
								<el-input v-model="serverConfig['multicast.addrMax']" placeholder="rtp组播截止地址" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>

				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="RECORD设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="应用名" prop="record.appName">
								<el-input v-model="serverConfig['record.appName']" placeholder="应用名" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="启用Fmp4" prop="record.enableFmp4">
								<el-select  v-model="serverConfig['record.enableFmp4']" placeholder="启用Fmp4"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="fastStart" prop="record.fastStart">
								<el-select  v-model="serverConfig['record.fastStart']" placeholder="fastStart"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="文件缓存大小" prop="record.fileBufSize">
								<el-input v-model="serverConfig['record.fileBufSize']" placeholder="文件缓存大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="fileRepeat" prop="record.fileRepeat">
								<el-select  v-model="serverConfig['record.fileRepeat']" placeholder="fileRepeat"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="MP4点播每次流化数据量" prop="record.sampleMS">
								<el-input v-model="serverConfig['record.sampleMS']" placeholder="MP4点播每次流化数据量" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
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

			<!--	protocol设置		-->
			<el-tab-pane  label="RTSP/RTMP" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="RTSP设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="端口"  prop="rtsp.port">
								<el-input v-model="serverConfig['rtsp.port']" placeholder="端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="SSL端口"  prop="rtsp.sslport">
								<el-input v-model="serverConfig['rtsp.sslport']" placeholder="SSL端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="握手超时(s)"  prop="rtsp.handshakeSecond">
								<el-input v-model="serverConfig['rtsp.handshakeSecond']" placeholder="握手超时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="心跳超时(s)"  prop="rtsp.keepAliveSecond">
								<el-input v-model="serverConfig['rtsp.keepAliveSecond']" placeholder="心跳超时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="低延迟模式"  prop="rtsp.lowLatency">
								<el-select  v-model="serverConfig['rtsp.lowLatency']" placeholder="低延迟模式"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="直接代理模式"  prop="rtsp.directProxy">
								<el-select  v-model="serverConfig['rtsp.directProxy']" placeholder="直接代理模式"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="rtsp专有鉴权"  prop="rtsp.authBasic">
								<el-input v-model="serverConfig['rtsp.authBasic']" placeholder="rtsp专有鉴权" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>

				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="RTMP设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="端口"  prop="rtmp.port">
								<el-input v-model="serverConfig['rtmp.port']" placeholder="端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="SSL端口"  prop="rtmp.sslport">
								<el-input v-model="serverConfig['rtmp.sslport']" placeholder="SSL端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="握手超时(s)"  prop="rtmp.handshakeSecond">
								<el-input v-model="serverConfig['rtmp.handshakeSecond']" placeholder="握手超时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="心跳超时(s)"  prop="rtmp.keepAliveSecond">
								<el-input v-model="serverConfig['rtmp.keepAliveSecond']" placeholder="心跳超时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="直接代理模式"  prop="rtmp.directProxy">
								<el-select  v-model="serverConfig['rtmp.directProxy']" placeholder="直接代理模式"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-tab-pane>



			<!--	RTP		-->
			<el-tab-pane  label="RTP" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="RTP设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="音频mtu大小"  prop="rtp.audioMtuSize">
								<el-input v-model="serverConfig['rtp.audioMtuSize']" placeholder="音频mtu大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="视频mtu大小"  prop="rtp.videoMtuSize">
								<el-input v-model="serverConfig['rtp.videoMtuSize']" placeholder="视频mtu大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="低延迟开关"  prop="rtp.lowLatency">
								<el-select  v-model="serverConfig['rtp.lowLatency']" placeholder="低延迟开关"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="rtp包长度限制"  prop="rtp.rtpMaxSize">
								<el-input v-model="serverConfig['rtp.rtpMaxSize']" placeholder="rtp包长度限制" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="采用stap-a模式"  prop="rtp.h264_stap_a">
								<el-select  v-model="serverConfig['rtp.h264_stap_a']" placeholder="采用stap-a模式"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>


				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="rtp_proxy设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="180px" class="custom-form">

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="调试输出目录"  prop="rtp_proxy.dumpDir">
								<el-input v-model="serverConfig['rtp_proxy.dumpDir']" placeholder="调试输出目录(包括rtp/ps/h264)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="udp和tcp代理服务端口"  prop="rtp_proxy.port">
								<el-input v-model="serverConfig['rtp_proxy.port']" placeholder="udp和tcp代理服务端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="端口范围"  prop="rtp_proxy.port_range">
								<el-input v-model="serverConfig['rtp_proxy.port_range']" placeholder="端口范围" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="UDP接收缓冲区"  prop="rtp_proxy.udp_recv_socket_buffer">
								<el-input v-model="serverConfig['rtp_proxy.udp_recv_socket_buffer']" placeholder="UDP接收缓冲区" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="开启gop缓存优化"  prop="rtp_proxy.gop_cache">
								<el-input v-model="serverConfig['rtp_proxy.gop_cache']" placeholder="开启gop缓存优化" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="单包封装数据ms"  prop="rtp_proxy.rtp_g711_dur_ms">
								<el-input v-model="serverConfig['rtp_proxy.rtp_g711_dur_ms']" placeholder="每个RTP包封装100ms的G.711音频数据" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="合并帧"  prop="rtp_proxy.merge_frame">
								<el-input v-model="serverConfig['rtp_proxy.merge_frame']" placeholder="合并帧" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="h264载荷类型"  prop="rtp_proxy.h264_pt">
								<el-input v-model="serverConfig['rtp_proxy.h264_pt']" placeholder="h264载荷类型" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="h265载荷类型"  prop="rtp_proxy.h265_pt">
								<el-input v-model="serverConfig['rtp_proxy.h265_pt']" placeholder="h265载荷类型" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="opus载荷类型"  prop="rtp_proxy.opus_pt">
								<el-input v-model="serverConfig['rtp_proxy.opus_pt']" placeholder="opus载荷类型" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="ps载荷类型"  prop="rtp_proxy.ps_pt">
								<el-input v-model="serverConfig['rtp_proxy.ps_pt']" placeholder="ps载荷类型" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="超时时间"  prop="rtp_proxy.timeoutSec">
								<el-input v-model="serverConfig['rtp_proxy.timeoutSec']" placeholder="超时时间" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-tab-pane>


			<!--	RTC		-->
			<el-tab-pane  label="RTC" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="RTC设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="200px" class="custom-form">
					<el-row :gutter="24">

						<el-col :span="12">
							<el-form-item label="公网 IP"  prop="rtc.externIP">
								<el-input v-model="serverConfig['rtc.externIP']" placeholder="公网 IP" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>

						<el-col :span="12">
							<el-form-item label="ICE的UDP端口"  prop="rtc.icePort">
								<el-input v-model="serverConfig['rtc.icePort']" placeholder="ICE 使用的 UDP 端口（默认 3478）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>


					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="ICE 使用的 TCP 端口"  prop="rtc.iceTcpPort">
								<el-input v-model="serverConfig['rtc.iceTcpPort']" placeholder="ICE 使用的 TCP 端口（Nat 类型很差时可用）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="使用 TURN 中继"  prop="rtc.enableTurn">
								<el-select  v-model="serverConfig['rtc.enableTurn']" placeholder="是否允许使用 TURN 中继（启用适用于 NAT 穿透失败时）"  class="custom-width">
									<el-option v-for="item in configOption.general.yesNo" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="ICE 认证密码"  prop="rtc.icePwd">
								<el-input v-model="serverConfig['rtc.icePwd']" placeholder="ICE 认证密码（WebRTC 连接时使用）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="ICE 传输策略"  prop="rtc.iceTransportPolicy">
								<el-select  v-model="serverConfig['rtc.iceTransportPolicy']" placeholder="ICE 传输策略"  class="custom-width">
									<el-option v-for="item in configOption.general.iceTransportPolicy" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="帧过滤开关"  prop="rtc.bfilter">

								<el-select  v-model="serverConfig['rtc.bfilter']" placeholder="帧过滤开关"  class="custom-width">
									<el-option v-for="item in configOption.general.noOff" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="DataChannel回显测试开关"  prop="rtc.datachannel_echo">
								<el-select  v-model="serverConfig['rtc.datachannel_echo']" placeholder="DataChannel回显测试开关"  class="custom-width">
									<el-option v-for="item in configOption.general.noOff" :key="item.value" :label="item.label" :value="item.value"/>
								</el-select >
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="ICE 用户凭证"  prop="rtc.iceUfrag">
								<el-input v-model="serverConfig['rtc.iceUfrag']" placeholder="ICE 用户凭证（WebRTC 握手时使用）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="指定网络接口"  prop="rtc.interfaces">
								<el-input v-model="serverConfig['rtc.interfaces']" placeholder="指定网络接口（如 eth0,wlan0），默认空表示自动选择" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="允许的最大码率"  prop="rtc.max_bitrate">
								<el-input v-model="serverConfig['rtc.max_bitrate']" placeholder="允许的最大码率（0 表示不限）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="STUN 请求最大重试次数"  prop="rtc.max_stun_retry">
								<el-input v-model="serverConfig['rtc.max_stun_retry']" placeholder="STUN 请求最大重试次数" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="RTP 最大缓存时长"  prop="rtc.maxRtpCacheMS">
								<el-input v-model="serverConfig['rtc.maxRtpCacheMS']" placeholder="RTP 最大缓存时长（毫秒），用于乱序或丢包处理" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="RTP 缓存最大数量"  prop="rtc.maxRtpCacheSize">
								<el-input v-model="serverConfig['rtc.maxRtpCacheSize']" placeholder="RTP 缓存最大数量（包数量）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="允许的最小码率"  prop="rtc.min_bitrate">
								<el-input v-model="serverConfig['rtc.min_bitrate']" placeholder="允许的最小码率" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="NACK 重传间隔比例"  prop="rtc.nackIntervalRatio">
								<el-input v-model="serverConfig['rtc.nackIntervalRatio']" placeholder="NACK 重传间隔比例（越大重传越慢）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="一次最大 NACK 数量"  prop="rtc.nackMaxCount">
								<el-input v-model="serverConfig['rtc.nackMaxCount']" placeholder="一次最大 NACK 数量" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="NACK请求的最大时间范围"  prop="rtc.nackMaxMS">
								<el-input v-model="serverConfig['rtc.nackMaxMS']" placeholder="NACK 请求的最大时间范围（ms）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="NACK 队列最大容量"  prop="rtc.nackMaxSize">
								<el-input v-model="serverConfig['rtc.nackMaxSize']" placeholder="NACK 队列最大容量" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="NACK 可请求的最大 RTP 包大小"  prop="rtc.nackRtpSize">
								<el-input v-model="serverConfig['rtc.nackRtpSize']" placeholder="NACK 可请求的最大 RTP 包大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="WebRTC 使用的 UDP 端口"  prop="rtc.port">
								<el-input v-model="serverConfig['rtc.port']" placeholder="WebRTC 使用的 UDP 端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="WebRTC TCP 媒体端口"  prop="rtc.tcpPort">
								<el-input v-model="serverConfig['rtc.tcpPort']" placeholder="WebRTC TCP 媒体端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="WebRTC 动态 RTP 端口范围"  prop="rtc.portRange">
								<el-input v-model="serverConfig['rtc.portRange']" placeholder="WebRTC 动态 RTP 端口范围" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="WebRTC 连接超时时间"  prop="rtc.timeoutSec">
								<el-input v-model="serverConfig['rtc.timeoutSec']" placeholder="WebRTC 连接超时时间" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="优先使用的音频编码"  prop="rtc.preferredCodecA">
								<el-input v-model="serverConfig['rtc.preferredCodecA']" placeholder="优先使用的音频编码（Opus/PCMA/PCMU 等）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="优先使用的视频编码"  prop="rtc.preferredCodecV">
								<el-input v-model="serverConfig['rtc.preferredCodecV']" placeholder="优先使用的视频编码（H264,H265,VP8,VP9 等）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="REMB 限制码率"  prop="rtc.rembBitRate">
								<el-input v-model="serverConfig['rtc.rembBitRate']" placeholder="REMB 限制码率" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="WebRTC 信令端口"  prop="rtc.signalingPort">
								<el-input v-model="serverConfig['rtc.signalingPort']" placeholder="WebRTC 信令端口（HTTP/WebSocket）" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="WebRTC 信令 SSL 端口"  prop="rtc.signalingSslPort">
								<el-input v-model="serverConfig['rtc.signalingSslPort']" placeholder="WebRTC 信令 SSL 端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="起始码率"  prop="rtc.start_bitrate">
								<el-input v-model="serverConfig['rtc.start_bitrate']" placeholder="起始码率" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
			</el-tab-pane>

			<!--	SRT		-->
			<el-tab-pane  label="SHELL/SRT" class="custom-padding-left">
				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="SHELL设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="端口"  prop="shell.port">
								<el-input v-model="serverConfig['shell.port']" placeholder="端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="最大请求大小"  prop="shell.maxReqSize">
								<el-input v-model="serverConfig['shell.maxReqSize']" placeholder="最大请求大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>

				<el-form :inline="true" class="custom-margin-top">
					<el-form-item label="SRT设置：" >
					</el-form-item>
				</el-form>
				<el-form :model="serverConfig" :rules="rules"  label-width="150px" class="custom-form">
					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="延迟估算参数"  prop="srt.latencyMul">
								<el-input v-model="serverConfig['srt.latencyMul']" placeholder="延迟估算参数" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="包缓存大小"  prop="srt.pktBufSize">
								<el-input v-model="serverConfig['srt.pktBufSize']" placeholder="包缓存大小" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

					<el-row :gutter="24">
						<el-col :span="12">
							<el-form-item label="端口"  prop="srt.port">
								<el-input v-model="serverConfig['srt.port']" placeholder="端口" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="12">
							<el-form-item label="会话超时(s)"  prop="srt.timeoutSec">
								<el-input v-model="serverConfig['srt.timeoutSec']" placeholder="会话超时(s)" :props="groupsProps" :show-all-levels="false" class="custom-width" clearable ></el-input>
							</el-form-item>
						</el-col>
					</el-row>

				</el-form>
			</el-tab-pane>

		</el-tabs>
		<template #footer>
			<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
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
				edit: 'ZLMediaKit服务器配置',
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
					],
					iceTransportPolicy:[
						{
							label: '任意',
							value: '0'
						},
						{
							label: '仅 TURN',
							value: '1'
						},
					],
					noOff:[
						{
							label: '关',
							value: "0"
						},
						{
							label: '开',
							value: "1"
						},
					]
				},
			},

			serverConfig:{
			},
			serverConfigOld:{
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
				this.serverConfigOld = JSON.parse(JSON.stringify(this.serverConfig));
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})

			}
		},

		//表单提交方法
		async submit() {
			// 对比旧数据,将修改的字段与修改的值，封装进一个新的对象中，传递给后端
			const changedData = {};

			// 确保 serverConfigOld 存在且不为 undefined
			if (!this.serverConfigOld) {
				this.serverConfigOld = {};
			}

			// 获取所有需要比较的键
			const currentKeys = Object.keys(this.serverConfig);
			const oldKeys = Object.keys(this.serverConfigOld);
			const allKeys = new Set([...currentKeys, ...oldKeys]);

			// 遍历所有键，比较值的变化
			for (const key of allKeys) {
				const oldValue = this.serverConfigOld[key];
				const newValue = this.serverConfig[key];

				// 处理 undefined 和 null 的情况，统一转换为字符串比较
				const oldStr = oldValue === undefined || oldValue === null ? '' : String(oldValue);
				const newStr = newValue === undefined || newValue === null ? '' : String(newValue);

				// 比较字符串形式的值
				if (oldStr !== newStr) {
					changedData[key] = newValue;
				}
			}
			//添加标识符
			changedData.zlmNodeHosts = this.cameraZLMNode.host;
			changedData.zlmNodeServerId = this.cameraZLMNode.serverId;
			// 如果有变化的数据则提交
			if (Object.keys(changedData).length > 0) {
				console.log('变化的配置项:', changedData);
				var res = await this.$API.auth.settings.updateZLMServerConfig.post({"serverConfig":JSON.stringify(changedData)});
				if (res.code == 200) {
					this.$message.success("操作成功");
					// 更新旧配置数据
					this.getZLMServerConfig(this.cameraZLMNode);
					//this.serverConfigOld = JSON.parse(JSON.stringify(this.serverConfig));
				} else {
					this.$alert(res.msg, "提示", {type: 'error'});
				}
			} else {
				this.$message.info("没有修改任何配置");
				console.log('没有检测到配置变化');
			}
		}
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
