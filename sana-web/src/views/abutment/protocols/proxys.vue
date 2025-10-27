<template>
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1300" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-form  v-show="mode != 'show'" :model="protocol" :rules="rules"  ref="dialogForm" label-width="100px" label-position="left">
			<el-row :gutter="16" >
				<el-col :span="12">
					<el-form-item label="名称" prop="name">
						<el-input v-model="protocol.name" placeholder="请输入mqtt连接名称" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item label="IP地址" prop="ip">
						<el-input v-model="protocol.ip" placeholder="请输入mqtt连接地址"  clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item label="mqtt端口" prop="tcpPort">
						<el-input v-model="protocol.tcpPort" placeholder="请输入mqtt端口" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item label="http/WS端口" prop="websocketPort">
						<el-input v-model="protocol.websocketPort" placeholder="http、websocket端口"  clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item label="是否启用" prop="mqttEnabled">
						<el-select  v-model="protocol.mqttEnabled" placeholder="请选择该mqtt是否启用"  clearable style="width: 100%;">
							<el-option
								v-for="item in options"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select >
					</el-form-item>
					<el-form-item label="mqtt类型" prop="mqttType">
						<el-select v-model="protocol.mqttType" placeholder="请选择mqtt类型" clearable style="width: 100%;">
							<el-option
								v-for="item in mqttTypeOptions"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select>
					</el-form-item>
					<el-form-item label="是否开启认证" prop="authEnable">
						<el-select v-model="protocol.authEnable" placeholder="请选择是否开启认证" clearable style="width: 100%;">
							<el-option
								v-for="item in options"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select>
					</el-form-item>
					<el-form-item v-show="protocol.authEnable==1" label="认证用户名" prop="username">
						<el-input v-model="protocol.username" placeholder="请输入认证用户名" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item v-show="protocol.authEnable==1" label="认证密码" prop="password">
						<el-input v-model="protocol.password"  placeholder="请输入认证密码" clearable style="width: 100%;"></el-input>
					</el-form-item>

				</el-col>
				<el-col :span="12">
					<el-form-item label="http-api" prop="httpEnable">
						<el-select v-model="protocol.httpEnable" placeholder="请选择是否开启http-api"  clearable style="width: 100%;">
							<el-option
								v-for="item in options"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select>
					</el-form-item>
					<el-form-item  v-show="protocol.httpEnable==1" label="http认证" prop="httpBasicAuth">
						<el-select v-model="protocol.httpBasicAuth" placeholder="请选择是否开启http-api认证"  clearable style="width: 100%;">
							<el-option
								v-for="item in options"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select>
					</el-form-item>
					<el-form-item v-show="protocol.httpBasicAuth==1" label="http认证用户名" prop="httpBasicUsername">
						<el-input v-model="protocol.httpBasicUsername" placeholder="请输入http认证用户名" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item v-show="protocol.httpBasicAuth==1" label="http认证密码" prop="httpBasicPassword">
						<el-input v-model="protocol.httpBasicPassword" placeholder="请输入http认证密码" clearable style="width: 100%;"></el-input>
					</el-form-item>

					<el-form-item label="是否开启 ssl" prop="sslEnabled">
						<el-select v-model="protocol.sslEnabled" placeholder="请选择是否开启 ssl" clearable style="width: 100%;">
							<el-option
								v-for="item in options"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select>
					</el-form-item>
					<el-form-item v-show="protocol.sslEnabled==1" label="ssl 密钥目录" prop="keystorePath">
						<el-input v-model="protocol.keystorePath" placeholder="请输入ssl keystore目录" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item v-show="protocol.sslEnabled==1" label="ssl 密钥密码" prop="keystorePass">
						<el-input v-model="protocol.keystorePass" placeholder="请输入ssl keystore密码" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item  label="双向认证" prop="clientAuth">
						<el-select v-model="protocol.clientAuth" placeholder="请选择是否开启双向认证" clearable style="width: 100%;">
							<el-option
								v-for="item in clientAuthOptions"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select>
					</el-form-item>
					<el-form-item v-show="protocol.clientAuth==1" label="双向认证目录" prop="truststorePath">
						<el-input v-model="protocol.truststorePath" placeholder="请输入ssl 双向认证 truststore 目录" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item v-show="protocol.clientAuth==1" label="双向认证密码" prop="truststorePass">
						<el-input v-model="protocol.truststorePass" placeholder="请输入ssl 双向认证 truststore 密码" clearable style="width: 100%;"></el-input>
					</el-form-item>
					<el-form-item label="连接方式" prop="linkType">
						<el-select v-model="protocol.linkType" placeholder="请选择连接方式" clearable style="width: 100%;">
							<el-option
								v-for="item in linkTypeOptions"
								:key="item.value"
								:label="item.label"
								:value="item.value"
								:disabled="item.disabled"
							/>
						</el-select>
					</el-form-item>
<!--					<el-form-item label="http接口" prop="productTypeShow">
						<el-cascader v-model="protocol.productTypeShow" clearable style="width: 100%;"></el-cascader>
					</el-form-item>
					<el-form-item label="mqtt转发" prop="productTypeShow">
						<el-cascader v-model="protocol.productTypeShow" clearable style="width: 100%;"></el-cascader>
					</el-form-item>
					<el-form-item label="mq转发" prop="productTypeShow">
						<el-cascader v-model="protocol.productTypeShow" clearable style="width: 100%;"></el-cascader>
					</el-form-item>-->
				</el-col>
			</el-row>
		</el-form>
			<!--	展示内容	-->
		<el-alert v-if="mode =='show'" title="系统 mqtt 配置均来自于 application-*.yml 中的 mqtt 的配置信息，如果要修改请修改yml配置文件，页面上做修改不会生效。 这里仅做展示使用，方便快速接入" type="warning" show-icon :closable="false"/>
		<el-card shadow="never"  v-show="mode == 'show'">
			<el-descriptions :column="2" border size="small" style="margin-top: 15px;">
				<el-descriptions-item label="mqtt名称" :width="200">{{this.protocol.name}}</el-descriptions-item>
				<el-descriptions-item label="mqtt订阅地址" :width="200">{{this.protocol.ip}}</el-descriptions-item>
				<el-descriptions-item label="mqtt订阅是否启用" :width="200">
					<el-tag v-if="this.protocol.enabled==true" size="small" >启用</el-tag>
					<el-tag v-if="this.protocol.enabled==false" size="small" type="info">未启用</el-tag>
				</el-descriptions-item>

				<el-descriptions-item label="mqtt类型">{{ this.protocol.mqttType == 2 ? '系统mqtt' : '其他代理mqtt' }}</el-descriptions-item>
				<el-descriptions-item label="订阅mqtt认证用户名">{{this.protocol.userName}}</el-descriptions-item>
				<el-descriptions-item label="订阅mqtt认证密码">{{this.protocol.password}}</el-descriptions-item>
				<el-descriptions-item label="订阅mqtt端口">{{this.protocol.port}}</el-descriptions-item>

				<el-descriptions-item label="订阅前缀">{{this.protocol.proxyPrefix}}</el-descriptions-item>
				<el-descriptions-item label="订阅设备上下线监听主题">{{this.protocol.proxyStatusPrefix}}</el-descriptions-item>
				<el-descriptions-item label="全局订阅的 topic">{{this.protocol.globalSubscribe}}</el-descriptions-item>
				<el-descriptions-item label="订阅mqtt客户端id">{{this.protocol.clientId}}</el-descriptions-item>
				<el-descriptions-item label="是否重连">{{this.protocol.reconnect}}</el-descriptions-item>
				<el-descriptions-item label="重连时间">{{this.protocol.reInterval}}</el-descriptions-item>
				<el-descriptions-item label="订阅mqtt客户端版本">{{this.protocol.version}}</el-descriptions-item>
				<el-descriptions-item label="接受buffer size大小">{{this.protocol.readBufferSize}}</el-descriptions-item>
				<el-descriptions-item label="消息解析最大 bytes 长度">{{this.protocol.maxBytesInMessage}}</el-descriptions-item>

				<el-descriptions-item label="心跳模式">{{this.protocol.heartbeatMode}}</el-descriptions-item>
				<el-descriptions-item label="工作线程数">{{this.protocol.bizThreadPoolSize}}</el-descriptions-item>
				<el-descriptions-item label="心跳超时策略">{{this.protocol.heartbeatTimeoutStrategy}}</el-descriptions-item>

			</el-descriptions>
		</el-card>
				<template #footer>
					<el-button v-if="mode!='show'" @click="visible=false" >取 消</el-button>
					<el-button v-if="mode!='show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
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

			mode: "add",
			titleMap: {
				add: '新增mqtt',
				edit: '修改mqtt',
				show: 'mqtt-client-proxy信息'
			},
			visible: false,
			isSaveing: false,
			protocol:{
				id: "",
				ip: "",
				mqttName: "",
				mqttType: "",
				tcpPort: "",
				websocketPort: "",
				authEnable: "",
				username: "",
				password: "",
				httpEnable: "",
				httpBasicAuth: "",
				httpBasicUsername: "",
				httpBasicPassword: "",
				sslEnabled: "",
				keystorePath: "",
				keystorePass: "",
				clientAuth: "",
				truststorePath: "",
				truststorePass: "",
				mqttEnabled: "",
				createTime: "",
				updateTime: "",
				creatorName: "",
				updaterName: "",
				linkType:""
			},


			clientAuthOptions: [
				{
					value: 'none',
					label: '不启用',
				},
				{
					value: 'REQUIRE',
					label: '启用',
					/*disabled: true,*/
				},
			],

			options: [
				{
					value: 1,
					label: '启用',
				},
				{
					value: 0,
					label: '不启用',
					/*disabled: true,*/
				},
			],
			linkTypeOptions: [
				{
					value: 0,
					label: 'mqtt监听',
				},
				{
					value: 1,
					label: 'http接口连接',
				},

				{
					value: 2,
					label: 'mq队列转发',
				},
			],

			mqttTypeOptions: [
				{
					value: 1,
					label: '本地mqtt',
				},
				{
					value: 2,
					label: '代理mqtt',
					/*disabled: true,*/
				},
			],
			//验证规则
			rules: {
				mqttName: [
					{required: true, message: '请输入mqtt连接名称'}
				],
				ip: [
					{required: true, message: '请输入mqtt连接地址'}
				],
				tcpPort: [
					{required: true, message: '请输入mqtt端口'}
				],
				mqttEnabled: [
					{required: true, message: '请选择该mqtt是否启用'}
				],
				mqttType: [
					{required: true, message: '请选择mqtt类型'}
				],
				productTypeShow: [
					{required: true, message: '请选择连接方式'}
				],
			},
		}
	},

	mounted() {
		this.getMqttClientProxy();

	},
	methods: {

		submit(){
			this.$refs.dialogForm.validate(async (valid) => {
				if (valid) {
					this.isSaveing = true;
					var res = {};
					if(this.mode === 'add'){
						//新增用户
						res = await this.$API.abutment.protocols.save.post(this.protocol);
					}else {
						//修改用户
						res = await this.$API.protocol.abutment.update.post(this.protocol);
					}
					this.isSaveing = false;
					if(res.code == 200){
						this.$emit('success', this.form, this.mode)
						this.visible = false;
						this.$message.success("操作成功")
					}else{
						this.$alert(res.msg, "提示", {type: 'error'})
					}
				}else{
					return false;
				}
			})
		},
		open(mode='add'){
			this.mode = mode;
			this.visible = true;
			return this
		},
		async getMqttClientProxy(){
			var res = await this.$API.abutment.protocols.getMqttProxyClientMessage.get();
			if(res.code == 200) {
				this.protocol = res.data;
			}
		},

		setData(data){
			Object.assign(this.protocol, data)
		}
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
