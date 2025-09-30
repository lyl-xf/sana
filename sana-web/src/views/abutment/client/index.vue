<template>
	<el-container>
		<el-container>
			<el-main class="nopadding">
				<el-container>
					<el-header>
						<div class="left-panel">

							<el-select  placeholder="请选择" v-model="search.operateType" @change="handleNodeClick">
								<el-option label="系统协议连接客户端" value="0"></el-option>
								<el-option label="代理协议连接客户端" value="1"></el-option>
							</el-select>

						</div>

						<div class="right-panel">

						</div>
					</el-header>
					<el-main class="nopadding">
						<scTable ref="table" :apiObj="apiObj" stripe highlightCurrentRow >
							<el-table-column  width="30"></el-table-column>
							<el-table-column label="序号" type="index" width="80"></el-table-column>
							<el-table-column label="客户端Id" prop="clientId" width="150"></el-table-column>
							<el-table-column label="客户端名称" prop="username" width="150"></el-table-column>
							<el-table-column label="保活时间" prop="keepAlive" width="80"></el-table-column>
							<el-table-column label="是否连接" prop="connected" width="80">
								<template #default="scope">
									<el-tag v-if="scope.row.connected==true" type="success">是</el-tag>
									<el-tag v-if="scope.row.connected==false" type="danger">否</el-tag>
								</template>
							</el-table-column>
							<el-table-column label="协议信息" prop="protoFullName" width="150"></el-table-column>
							<el-table-column label="客户端ip" prop="ipAddress" width="150"></el-table-column>
							<el-table-column label="客户端端口" prop="port" width="100"></el-table-column>
							<el-table-column label="操作" fixed="right" align="center" width="200">
								<template #default="scope">
									<el-button-group>
										<!--v-auth="'abutment:mqttClient:getSubscriptions'"-->
										<el-button text type="primary" size="small"  @click="getSubscriptions(scope.row)">订阅信息</el-button>
										<el-popconfirm title="确定关闭吗？如果有重连机制会自动重连"  @confirm ="clientClose(scope.row.clientId)">
											<template #reference>
												<!--v-auth="'abutment:mqttClient:close'"-->
												<el-button text type="primary"  size="small">关闭连接</el-button>
											</template>
										</el-popconfirm>
									</el-button-group>
								</template>
							</el-table-column>
						</scTable>
					</el-main>
				</el-container>
			</el-main>
		</el-container>
	</el-container>

	<el-drawer v-model="infoDrawer" title="订阅信息" :size="600" destroy-on-close>
		<info ref="info"></info>
	</el-drawer>
</template>

<script>
import info from './info.vue'
import scEcharts from '@/components/scEcharts/index.vue'

export default {
	name: 'log',
	components: {
		info,
		scEcharts
	},
	data() {
		return {
			infoDrawer: false,

			apiObj: this.$API.abutment.mqttClient.getClients,
			search: {
				operateType: "0",
			}
		}
	},
	methods: {

		handleNodeClick() {
			var params = {
				operateType: this.search.operateType
			}
			this.$refs.table.reload(params);
		},

		getSubscriptions(row) {
			this.infoDrawer = true;
			this.$nextTick(() => {
				this.$refs.info.setData(row);
			});
		},

		async clientClose(clientId){
			const params = {
				clientId: clientId
			};
			var res = await this.$API.abutment.mqttClient.clientClose.get(params);
			if(res.code == 200){
				this.$refs.table.refresh()
				this.$message.success("操作成功")
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		},

	}
}
</script>

<style>
</style>
