
<template>
	<el-main>
		<el-row :gutter="15">
			<el-col :xl="6" :lg="6" :md="8" :sm="12" :xs="24" v-for="item in list" :key="item.id">
				<el-card class="task task-item" shadow="hover" @click="edit(item)">
					<h2>{{item.title}}</h2>
					<ul>
						<li>
							<h3>节点名称</h3>
							<p>{{item.serverId}}</p>
						</li>
						<li>
							<h3>IP地址</h3>
							<p >{{item.host}}</p>
						</li>
					</ul>
					<div class="bottom" @click.stop>
						<div class="state">
							<el-tag v-if="item.enabled==true" size="small">运行中</el-tag>
							<el-tag v-if="item.enabled!=true" size="small" type="info">未启用</el-tag>
						</div>
						<div class="handler">
							<!--							<el-popconfirm title="确定立即执行吗？" @confirm="run(item)">
															<template #reference>
																<el-button type="primary" icon="el-icon-caret-right" circle></el-button>
															</template>
														</el-popconfirm>-->
							<el-dropdown trigger="click" >
								<el-button type="primary" icon="el-icon-more" circle plain></el-button>
								<template #dropdown>
									<el-dropdown-menu>
										<el-dropdown-item  v-if="item.mqttType !=1" @click="rebootzlm(item)">重启</el-dropdown-item>

<!--
										<el-dropdown-item  v-if="item.mqttType !=1" @click="edit(item)">Rtp服务</el-dropdown-item>

										<el-dropdown-item  v-if="item.mqttType !=1" @click="edit(item)">ffmpeg推拉流</el-dropdown-item>

										<el-dropdown-item  v-if="item.mqttType !=1" @click="edit(item)">推流代理</el-dropdown-item>

										<el-dropdown-item  v-if="item.mqttType !=1" @click="edit(item)">拉流代理</el-dropdown-item>
-->

									</el-dropdown-menu>
								</template>
							</el-dropdown>
						</div>
					</div>
				</el-card>
			</el-col>
		</el-row>
	</el-main>


	<edit-dialog v-if="dialog.edit" ref="editDialog" @success="handleSuccess" @closed="dialog.edit=false"></edit-dialog>


</template>

<script>


import editDialog from "./edit";

export default {
	name: 'task',
	components: {
		editDialog
	},

	data() {
		return {
			dialog: {
				info: false
			},
			list: []
		}
	},
	mounted() {
		this.getZLMCamera()
	},
	methods: {
		//获取协议列表
		async getZLMCamera(){
			var res = await this.$API.auth.settings.list.get();
			if(res.code == 200){
				this.list = res.data.nodes;
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})

			}

		},
		show(item){
			if(item.enabled ==  true) {
				this.dialog.info = true
				this.$nextTick(() => {
					this.$refs.infoDialog.open('show').setData(item)
				})
			}else {
				this.$message.error("该节点未启动,请在配置文件中开启之后开始查看")
			}
		},
		edit(item){
			if(item.enabled ==  true) {
				this.dialog.edit = true
				this.$nextTick(() => {
					this.$refs.editDialog.open('edit').setData(item)
				})
			}else {
				this.$message.error("该节点未启动,请在配置文件中开启之后开始编辑")
			}
		},

		rebootzlm(item){
			if(item.enabled ==  true) {
				this.$confirm('这只有Daemon方式才能重启，否则是直接关闭！确定重启吗？？','提示', {
					type: 'warning',
					confirmButtonText: '确定',
					confirmButtonClass: 'el-button--danger'
				}).then(async () => {
					//调用退出接口
					var res = await this.$API.auth.settings.restartServer.post(item);
					if(res.code == 200){
						this.$message.info("服务器将在一秒后自动重启")
					}
				}).catch(() => {
					//取消退出
				})
			}else {
				this.$message.error("该节点未启动,请在配置文件中开启之后开始编辑")
			}
		},
		//本地更新数据
		handleSuccess(){
			this.getZLMCamera()
		}
	}
}
</script>


<style scoped>
.task {height: 210px;}
.task-item h2 {font-size: 15px;color: #3c4a54;padding-bottom:15px;}
.task-item li {list-style-type:none;margin-bottom: 10px;}
.task-item li h4 {font-size: 12px;font-weight: normal;color: #999;}
.task-item li p {margin-top: 5px;}
.task-item .bottom {border-top: 1px solid #EBEEF5;text-align: right;padding-top:10px;display: flex;justify-content: space-between;align-items: center;}

.task-add {display: flex;flex-direction: column;align-items: center;justify-content: center;text-align: center;cursor: pointer;color: #999;}
.task-add:hover {color: #409EFF;}
.task-add i {font-size: 30px;}
.task-add p {font-size: 12px;margin-top: 20px;}

.dark .task-item .bottom {border-color: var(--el-border-color-light);}
</style>
