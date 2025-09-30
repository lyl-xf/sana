<template>

	<!--实时数据-->
	<el-table :data="deviceData.deviceForm" stripe  v-loading="loading" >
		<el-table-column label="#" type="index" width="150"></el-table-column>
		<el-table-column label="属性名称"  width="250">
			<template #default="scope">
				<span>{{scope.row.modeName}}</span>
			</template>
		</el-table-column>

		<el-table-column label="属性标志"  width="250">
			<template #default="scope">
				<span>{{ scope.row.modeSigns }}</span>
			</template>
		</el-table-column>

		<el-table-column label="实时数据" width="250">
			<template #default="scope">
				<span style="color: #0b94ef">{{ scope.row.currentValue }}</span>
			</template>
		</el-table-column>

		<el-table-column label="属性单位"  width="250">
			<template #default="scope">
				<span>{{ scope.row.modeRemark }}</span>
			</template>
		</el-table-column>
		<el-table-column min-width="1"></el-table-column>
	</el-table>
	<!--历史数据-->
</template>

<script>
import mqtt from 'mqtt';

export default {
	name: 'deviceDataTab',
	props: {
		deviceItemId: Number,
	},
	data() {
		return {
			loading: false,
			testDatas: 123,
			deviceData: {
				deviceForm:[],
				websocketPort:  '',
				username:  '',
				password: '',
				mqttIP: ''
			}
		}
	},

	mounted() {
		this.getDeviceMode(this.deviceItemId);
		this.getMqttBroker();
		setTimeout(() => {
			this.connectMQTT();
		}, 2000)
	},


	// todo mqtt监听数据，实时显示在实时数据中

	methods: {
		async getDeviceMode(deviceItemId){
			this.loading = true;
			var res = await this.$API.device.deviceItem.getDeviceMode.get({"id":deviceItemId});
			if (res.code === 200) {
				// 初始化 currentValue
				this.deviceData.deviceForm = res.data.map(item => ({
					...item,
					currentValue: ''
				}));
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},

		async getMqttBroker(){
			this.loading = true;
			var res = await this.$API.abutment.mqttClient.getMqttBroker.get();
			if (res.code === 200) {
				this.username = res.data.username;
				this.password = res.data.password;
				this.websocketPort = res.data.websocketPort;
			} else {
				this.$alert(res.msg, "提示", { type: 'error' });
			}
			this.loading = false;
		},

		//创建mqtt连接
		connectMQTT() {
			console.log('appAPI:', process.env.VUE_APP_API_BASEURL);
			const url = new URL(process.env.VUE_APP_API_BASEURL);
			console.log('url:', url);
			const hostWithPort = url.hostname;
			console.log('hostWithPort:', hostWithPort);
			const topic = `/SB${this.deviceItemId}`; // 订阅主题
			const brokerUrl = `ws://${hostWithPort}:${this.websocketPort}`; // 订阅主题
			console.log('brokerUrl:', brokerUrl);
			// 连接 MQTT 服务器（请替换为你的地址）
			const client = mqtt.connect(brokerUrl, {
				username: this.username,
				password: this.password,
				clean: true,        // 清除会话
				connectTimeout: 4000,
				clientId: 'web-client-' + Math.random().toString(16).substring(2, 8)
			});

			client.on('connect', () => {
				console.log('MQTT 已连接');
				console.log('topic：'+topic);
				client.subscribe(topic, err => {
					if (!err) {
						this.$message.success("已订阅主题")
						console.log(`已订阅主题: ${topic}`);
					}
				});
			});

			client.on('message', (receivedTopic, message) => {
				if (receivedTopic === topic) {
					try {
						const payload = JSON.parse(message.toString());
						this.updateCurrentValues(payload); // 更新表格数据
					} catch (e) {
						console.error('解析 MQTT 消息失败:', e);
					}
				}
			});

			this.mqttClient = client;
		},


		//测试展示
		updateCurrentValues(data) {
			console.log('Updating with data:', data);
			this.deviceData.deviceForm.forEach(item => {
				if (Object.prototype.hasOwnProperty.call(data, item.modeSigns)) {
					item.currentValue = data[item.modeSigns];
				}
			});
		},

	}
}
</script>

<style scoped>
.custom-margin-top {
	margin-top: 20px;
}
.align-right {
	display: flex;
	justify-content: flex-end;
}
</style>
