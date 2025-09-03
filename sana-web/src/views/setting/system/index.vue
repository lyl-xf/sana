<template>
	<el-main>
		<el-card shadow="never">
			<el-tabs tab-position="top">
<!--				<div class="el-form-item-msg" data-v-b33b3cf8="">关闭后普通用户无法登录，仅允许管理员角色登录</div>-->
				<el-tab-pane label="系统日志定时入库">
					<el-alert title="每次点击增删改查，都会优先存在redis中，通过定时存储任务进行间隔性获取并批量存入TDengine数据库中。下方为增删改查相关日志的入库时间间隔调整" type="warning" style="margin-bottom: 15px;"></el-alert>
					<el-form ref="form" :model="syslog" label-width="100px" style="margin-top: 20px;">

						<el-form-item label="任务名称">
							<el-input v-model="syslog.jobName" disabled></el-input>
							<div class="el-form-item-msg" >任务名称，这里默认为系统日志定时任务:logsDataSave</div>
						</el-form-item>
						<el-form-item label="cron表达式">
							<el-input v-model="syslog.cron"></el-input>
							<div class="el-form-item-msg" >cron表达式，默认为20s执行一次，一次存储20条（每次存储条数可通过修改配置文件中的'log-save-speed'调整）</div>
						</el-form-item>
						<el-form-item label="任务组名">
							<el-input v-model="syslog.jobGroup" disabled></el-input>
							<div class="el-form-item-msg" >根据当前的时间戳生成</div>
						</el-form-item>
						<el-form-item label="触发器名称">
							<el-input v-model="syslog.triggerName" disabled></el-input>
							<div class="el-form-item-msg" >触发器名称，这里默认为'Trigger_' + 任务名称</div>
						</el-form-item>
						<el-form-item label="触发器组">
							<el-input v-model="syslog.triggerGroup" disabled></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" style="margin-top: 50px" @click="SaveConfig()">保存</el-button>
						</el-form-item>
					</el-form>
				</el-tab-pane>

				<el-tab-pane label="设备数据定时入库">
					<el-alert title="设备操作日志、情景模式日志，以及告警、解除告警等日志，都实时存在redis中，通过定时存储任务进行间隔性获取并批量存入TDengine数据库中。下方为设备相关日志的入库时间间隔调整" type="warning" style="margin-bottom: 15px;"></el-alert>
					<el-form ref="form" :model="devicelog" label-width="100px" style="margin-top: 20px;">
						<el-form-item label="任务名称">
							<el-input v-model="devicelog.jobName" disabled></el-input>
							<div class="el-form-item-msg" >任务名称，这里默认为系统日志定时任务:deviceDataSave</div>
						</el-form-item>
						<el-form-item label="cron表达式">
							<el-input v-model="devicelog.cron"></el-input>
							<div class="el-form-item-msg" >cron表达式，默认为20s执行一次，一次存储20条（每次存储条数可通过修改配置文件中的'device-data-speed'调整）</div>
						</el-form-item>
						<el-form-item label="任务组名">
							<el-input v-model="devicelog.jobGroup" disabled></el-input>
							<div class="el-form-item-msg" >根据当前的时间戳生成</div>
						</el-form-item>
						<el-form-item label="触发器名称">
							<el-input v-model="devicelog.triggerName" disabled></el-input>
							<div class="el-form-item-msg" >触发器名称，这里默认为'Trigger_' + 任务名称</div>
						</el-form-item>
						<el-form-item label="触发器组">
							<el-input v-model="devicelog.triggerGroup" disabled></el-input>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" style="margin-top: 50px" @click="SaveConfig()">保存</el-button>
						</el-form-item>
					</el-form>
				</el-tab-pane>


			</el-tabs>
		</el-card>
	</el-main>
</template>

<script>
	export default {
		name: 'system',
		data() {
			return {
				syslog: {},
				devicelog: {}
			}
		},
		mounted() {

			this.getJobData();
		},
		methods: {
			async getJobData(){
				var res = await await this.$API.system.logJob.list.get();
				if (res.code == 200) {
					if(res.data[0].jobType == 1){
						this.syslog = res.data[0];
						this.devicelog = res.data[1];
					}else if(res.data[1].jobType == 1){
						this.syslog = res.data[1];
						this.devicelog = res.data[0];
					}
			}},
			async SaveConfig(){
				this.$message.warning("功能开发中.....");
			}
		}
	}
</script>

<style>
</style>
