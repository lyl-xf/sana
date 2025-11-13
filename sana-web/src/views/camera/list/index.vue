<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<el-button type="primary" icon="el-icon-plus" v-auth="'camera:settings:addStreamProxy'"  @click="add">新增设备</el-button>
			</div>
			<div class="right-panel">
				<div class="right-panel-search">
					<el-input v-model="search.name" placeholder="规则名称" clearable></el-input>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
		</el-header>
		<el-main class="nopadding">
			<scTable ref="table" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="#" type="index" width="40"></el-table-column>
				<el-table-column label="名称" prop="name" width="180"></el-table-column>
				<el-table-column label="app" prop="app" width="150"></el-table-column>
				<el-table-column label="stream" prop="stream" width="150"></el-table-column>
				<el-table-column label="虚拟主机" prop="vhost" width="180"></el-table-column>
				<el-table-column label="所属节点" prop="nodeNameId" width="150"></el-table-column>
				<el-table-column label="创建人员" prop="creatorName" width="180"></el-table-column>
<!--				<el-table-column label="创建时间" prop="createTime" width="150"></el-table-column>-->
				<el-table-column label="操作" fixed="right" align="center" width="250">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" v-auth="'camera:settings:preview'"  @click="showCamera(scope.row)">预览</el-button>
							<el-button text type="primary" size="small" v-auth="'camera:settings:updateStreamProxy'"  @click="table_edit(scope.row, scope.$index)">编辑</el-button>
							<el-popconfirm title="确定删除吗？"  @confirm="table_del(scope.row, scope.$index)">
								<template #reference>
									<el-button text type="primary" v-auth="'camera:settings:delStreamProxy'" size="small">删除</el-button>
								</template>
							</el-popconfirm>
						</el-button-group>
					</template>
				</el-table-column>
			</scTable>
		</el-main>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialog" @success="handleSaveSuccess" @closed="dialog.save=false"></save-dialog>
	<player-dialog v-if="dialog.player" ref="playerDialog" @success="handleSaveSuccess" @closed="dialog.player=false"></player-dialog>

</template>

<script>
	import saveDialog from './save'
	import playerDialog from './player.vue'
	export default {
		name: 'role',
		components: {
			saveDialog,
			playerDialog
		},
		data() {
			return {
				rulesId: Number,
				dialog: {
					save: false,
					player: false,
					permission: false
				},
				apiObj: this.$API.auth.settings.getPage,
				selection: [],
				search: {
					keyword: null
				}
			}
		},
		methods: {
			//添加
			add(){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('add')
				})
			},
			//编辑
			table_edit(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('edit').setData(row)
				})
			},

			//

			showCamera(row){
				//播放视频
				this.dialog.player = true
				this.$nextTick(() => {
					this.$refs.playerDialog.open('show').setData(row)
				})
			},
			//删除
			async table_del(row){
				var reqData = {id: row.id}
				var res = await await this.$API.auth.settings.delStreamProxy.get(reqData);
				if(res.code == 200){
					this.$refs.table.refresh()
					this.$message.success("删除成功")
				}else{
					this.$alert(res.msg, "提示", {type: 'error'})
				}
			},

			//表格选择后回调事件
			selectionChange(selection){
				this.selection = selection;
			},
			//表格内开关
			changeSwitch(val, row){
				row.status = row.status == '1'?'0':'1'
				row.$switch_status = true;
				setTimeout(()=>{
					delete row.$switch_status;
					row.status = val;
					this.$message.success("操作成功")
				}, 500)
			},
			//搜索
			upsearch(){
				this.$refs.table.upData(this.search)
			},

			//本地更新数据
			handleSaveSuccess(data, mode){
				if(mode=='add'){
					this.$refs.table.refresh()
				}else if(mode=='edit'){
					this.$refs.table.refresh()
				}
			}
		}
	}
</script>

<style>
</style>
