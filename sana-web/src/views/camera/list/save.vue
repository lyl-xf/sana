<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="1200" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode=='show'" ref="dialogForm" label-width="200px" class="custom-form">

			<el-row :gutter="24">
				<el-col :span="12" >
					<el-form-item label="摄像头名称：" prop="name" class="custom-width">
						<el-input v-model="form.name" clearable></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="12" >
					<el-form-item label="所属节点：" prop="nodeNameId" class="custom-width">
						<el-select  v-model="form.nodeNameId" placeholder="请选择" class="custom-width">
							<el-option v-for="item in zlmNodeHosts" :key="item.serverId" :label="item.serverId" :value="item.serverId"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="12" >
					<el-form-item label="虚拟主机：" prop="vhost" class="custom-width">
						<el-input v-model="form.vhost" placeholder="虚拟主机" clearable></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="12">
					<el-form-item label="应用名：" prop="app">
						<el-input v-model="form.app" placeholder="应用名" clearable></el-input>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="12" >
					<el-form-item label="流id：" prop="stream" class="custom-width">
						<el-input v-model="form.stream" placeholder="流id" clearable></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="12" >
					<el-form-item label="拉流方式：" prop="rtpType" class="custom-width">
						<el-select  v-model="form.rtpType" placeholder="请选择" class="custom-width">
							<el-option v-for="item in rtpTypeOpt" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>
					</el-form-item>
				</el-col>

			</el-row>

			<el-row :gutter="24">

				<el-col :span="12">
					<el-form-item label="超时时间：" prop="timeoutSec">
						<el-input v-model="form.timeoutSec" clearable></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="12" >
					<el-form-item label="重试次数：" prop="retryCount" class="custom-width">
						<el-input v-model="form.retryCount" clearable></el-input>
					</el-form-item>
				</el-col>

			</el-row>

			<el-row :gutter="24">

				<el-col :span="12">
					<el-form-item label="时间戳覆盖：" prop="modifyStamp">
						<el-select  v-model="form.modifyStamp" placeholder="请选择" class="custom-width">
							<el-option v-for="item in modifyStampOpt" :key="item.value" :label="item.label" :value="item.value"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="12" >
					<el-form-item label="mp4录像保存目录：" prop="mp4SavePath" class="custom-width">
						<el-input v-model="form.mp4SavePath" clearable></el-input>
					</el-form-item>
				</el-col>

			</el-row>

			<el-row :gutter="24">

				<el-col :span="12">
					<el-form-item label="mp4切片大小(s)：" prop="mp4MaxSecond" class="custom-width">
						<el-input v-model="form.mp4MaxSecond" clearable></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="12" >
					<el-form-item label="hls文件保存目录：" prop="hlsSavePath" class="custom-width">
						<el-input v-model="form.hlsSavePath" clearable></el-input>
					</el-form-item>
				</el-col>

			</el-row>
			<el-row :gutter="24">
				<el-col :span="24">
					<el-form-item label="拉流地址：" prop="url">
						<el-input v-model="form.url" placeholder="例如：rtsp://admin:admin@127.0.0.1:554/h264/ch1/main" clearable></el-input>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="8" >
					<el-form-item label="转hls-mpeg：" prop="enableHls">
						<el-switch
							v-model="this.form.enableHls"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="转hls-fmp4：" prop="enableHlsFmp4">
						<el-switch
							v-model="this.form.enableHlsFmp4"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="mp4录制：" prop="enableMp4">
						<el-switch
							v-model="this.form.enableMp4"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="8" >
					<el-form-item label="转RTSP：" prop="enableRtsp">
						<el-switch
							v-model="this.form.enableRtsp"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="转RTMP/FLV：" prop="enableRtmp">
						<el-switch
							v-model="this.form.enableRtmp"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="转http-ts/ws-ts：" prop="enableTs">
						<el-switch
							v-model="this.form.enableTs"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="8" >
					<el-form-item label="转http-fmp4/ws-fmp4：" prop="enableFmp4">
						<el-switch
							v-model="this.form.enableFmp4"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="按需生成hls：" prop="hlsDemand">
						<el-switch
							v-model="this.form.hlsDemand"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="按需生成rtsp：" prop="rtspDemand">
						<el-switch
							v-model="this.form.rtspDemand"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="8" >
					<el-form-item label="按需生成rtmp：" prop="rtmpDemand">
						<el-switch
							v-model="this.form.rtmpDemand"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="按需生成ts：" prop="tsDemand">
						<el-switch
							v-model="this.form.tsDemand"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="按需生成fmp4：" prop="fmp4Demand">
						<el-switch
							v-model="this.form.fmp4Demand"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="8" >
					<el-form-item label="允许音频：" prop="enableAudio">
						<el-switch
							v-model="this.form.enableAudio"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

				<el-col :span="8" >
					<el-form-item label="添加静音音轨：" prop="addMuteAudio">
						<el-switch
							v-model="this.form.addMuteAudio"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>

			</el-row>

			<el-row :gutter="24">
				<el-col :span="24" >
					<el-form-item label-width="300px" label="MP4录制当做观看者参与播放人数计数：" prop="mp4AsPlayer">
						<el-switch
							v-model="this.form.mp4AsPlayer"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>
			</el-row>

			<el-row :gutter="24">
				<el-col :span="24" >
					<el-form-item label-width="300px" label="无人观看自动关闭流(不触发无人观看hook)：" prop="autoClose">
						<el-switch
							v-model="this.form.autoClose"
							class="mt-2"
							style="margin-left: 24px"
							inline-prompt
						/>
					</el-form-item>
				</el-col>
			</el-row>


		</el-form>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script>

	export default {
		emits: ['success', 'closed'],
		data() {
			return {
				mode: "add",
				titleMap: {
					add: '新增拉流代理',
					edit: '编辑拉流代理',
					show: '查看拉流代理'
				},
				visible: false,
				isSaveing: false,
				//表单数据
				form: {
					nodeNameId:"zlm-node-1",
					name: "",
					app: "live",
					nodeIp: "",
					params: "",
					stream: "test",
					vhost: "__defaultVhost__",
					url: "",
					rtpType: 0,
					retryCount: -1,
					timeoutSec:	5,
					modifyStamp: 0,
					mp4SavePath: "",
					mp4MaxSecond: 10,
					hlsSavePath: "",
					enableHls: false,
					enableHlsFmp4: false,
					enableMp4: false,
					enableRtsp: true,
					enableRtmp: true,
					enableTs: true,
					enableFmp4: true,
					hlsDemand: false,
					rtspDemand: false,
					rtmpDemand: false,
					tsDemand: false,
					fmp4Demand: false,
					enableAudio: true,
					addMuteAudio: true,
					mp4AsPlayer: false,
					autoClose: true

				},
				value2: true,
				rtpTypeOpt:[
					{
						label: 'TCP',
						value: 0
					},
					{
						label: 'UDP',
						value: 1
					},
					{
						label: '组播',
						value: 2
					}
				],
				modifyStampOpt:[
					{
						label: '不开启',
						value: 3
					},
					{
						label: '绝对时间戳',
						value: 0
					},
					{
						label: '系统时间戳',
						value: 1
					},
					{
						label: '相对时间戳',
						value: 2
					}
				],
				zlmNodeHosts: [],
				//验证规则
				rules: {
					name: [
						{required: true, message: '请输入设备名称'}
					],
					nodeNameId: [
						{required: true, message: '请选择节点信息'}
					]
				}
			}
		},
		mounted() {
			this.getZLMCamera()
		},
		methods: {
			//显示
			open(mode='add'){
				this.mode = mode;
				this.visible = true;
				return this
			},
			//表单提交方法
			async submit(){
				this.isSaveing = true;
				var res = {};
				if(this.mode === 'add'){
					res = await this.$API.auth.settings.addStreamProxy.post(this.form);
				}else {
					res = await this.$API.auth.settings.updateStreamProxy.post(this.form);
				}
				this.isSaveing = false;
				if(res.code == 200){
					this.$emit('success', this.form, this.mode)
					this.visible = false;
					this.$message.success("操作成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},
			//获取协议列表
			async getZLMCamera(){
				var res = await this.$API.auth.settings.list.get();
				if(res.code == 200){
					this.zlmNodeHosts = res.data.nodes;
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})

				}

			},
			//表单注入数据
			setData(data){
				Object.assign(this.form, data)
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

.custom-width {
	width: 100%;
}
</style>
