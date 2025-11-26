<template>
	<el-container>
		<el-main class="nopadding">
			<scTable ref="table" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="#" type="index" width="100"></el-table-column>
				<el-table-column label="图片" width="120" >
					<template #default="scope">
						<el-avatar :src="scope.row.indexImage" size="small"></el-avatar>
					</template>
				</el-table-column>
				<el-table-column label="大屏名称" prop="projectName" width="250"></el-table-column>
				<el-table-column label="大屏备注信息" prop="remarks" width="250"></el-table-column>
				<el-table-column label="创建时间" prop="createTime" width="200">
					<template #default="scope">
						{{ scope.row.createTime ? new Date(scope.row.createTime).toLocaleString('zh-CN') : '' }}
					</template>
				</el-table-column>
				<el-table-column label="创建人员" prop="creatorName" width="180"></el-table-column>
				<el-table-column label="操作"  align="center" width="220">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" @click="table_show(scope.row, scope.$index)">查看</el-button>
						</el-button-group>
					</template>
				</el-table-column>
			</scTable>
		</el-main>
	</el-container>

</template>

<script>

import tool from "@/utils/tool";

export default {
	name: 'role',
	data() {
		return {
			dialog: {
				save: false,
				permission: false
			},
			apiObj: this.$API.device.combination.page,
			selection: [],
			search: {
				keyword: null
			}
		}
	},
	methods: {

		//查看
		table_show(row){
			//http://localhost:8082/#/chart/preview/5
			const goviewUrl = process.env.VUE_APP_GOVIEW_BASEURL + '/#/chart/preview/'+row.id+'?token='+tool.cookie.get("TOKEN")
			window.open(goviewUrl, '_blank')
		},

	}
}
</script>

<style>
</style>
