<template>
	<el-container>
		<el-main class="nopadding">
			<scTable ref="table" :data="apiData" stripe paginationLayout="">
				<el-table-column  width="50"></el-table-column>
				<el-table-column label="序号" type="index" width="80"></el-table-column>
				<el-table-column label="QOS" prop="mqttQoS" width="120"></el-table-column>
				<el-table-column label="订阅主题" prop="topicFilter" width="200"></el-table-column>
				<el-table-column label="客户端ID" prop="clientId" width="150"></el-table-column>
			</scTable>
		</el-main>
	</el-container>
</template>

<script>
	export default {
		data() {
			return {
				apiData: [],
				clientId: null
			}
		},
		methods: {
			setData(data){
				this.clientId = data.clientId
				this.getSubscriptions()
			},
			async getSubscriptions(){
				const params = {
					clientId: this.clientId
				};
				var res = await this.$API.abutment.mqttClient.getSubscriptions.get(params);
				if(res.code == 200){
					this.apiData = res.data
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},
		}
	}
</script>

<style scoped>
	.code {background: #848484;padding:15px;color: #fff;font-size: 12px;border-radius: 4px;}
</style>
