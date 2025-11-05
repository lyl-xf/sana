<template>
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1300" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
			<!--	展示内容	-->
		<el-alert v-if="mode =='show'" title="配置均来自于 application-*.yml 中的 zlm 中的配置信息，根据不同节点的配置信息访问ZLMediaKit。针对于以下配置信息均来自于ZLMediaKit，修改完记得重启。" type="warning" show-icon :closable="false"/>
		<el-main>
			<el-row :gutter="15">
				<el-col :lg="24">
					<el-card shadow="never" header="配置信息">
						<sc-code-editor v-model="serverConfig" mode="javascript" theme="darcula"></sc-code-editor>
					</el-card>
				</el-col>
			</el-row>


		</el-main>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</sc-dialog>
</template>

<script>
import scFileImport from "@/components/scFileImport/index.vue";
import {defineAsyncComponent} from "vue";
const scCodeEditor = defineAsyncComponent(() => import('@/components/scCodeEditor'));
export default {
	components: {scFileImport,scCodeEditor},
	emits: ['success', 'closed'],
	data() {
		return {
			serverConfig: ``,
			mode: "show",
			titleMap: {
				show: 'ZLMediaKit节点配置信息'
			},
			visible: false,
			isSaveing: false,
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
				this.serverConfig = JSON.stringify(res.data.data[0], null, 2)
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})

			}
		},
		async submit(){
			var res = await this.$API.auth.settings.updateZLMServerConfig.post({"serverConfig":this.serverConfig});
			if(res.code == 200){
				this.$alert(res.msg, "提示", {type: 'info'})
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})

			}
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
