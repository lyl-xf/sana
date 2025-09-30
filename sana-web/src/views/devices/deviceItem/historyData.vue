<template >
	<sc-dialog :title="titleMap[mode]" v-model="visible" :width="1400" :close-on-click-modal="false" destroy-on-close @closed="$emit('closed')">
		<el-container>
<!--			<el-alert title="最大只能查看三个月的数据" type="warning" show-icon :closable="false"/>-->
			<el-header>
				<div class="left-panel">
					<el-date-picker
						v-model="search.date"
						@change="upsearch"
						type="datetimerange"
						range-separator="至"
						start-placeholder="开始日期"
						end-placeholder="结束日期"
						:picker-options="datePickerOptions"

					/>
				</div>
			</el-header>

			<el-main class="nopadding">
				<scTable ref="table" :apiObj="listApi"  :params="listApiParams" row-key="id"  stripe height="500"   >
					<el-table-column
						v-for="column in columns"
						:key="column.prop"
						:label="column.label"
						:prop="column.prop"
					>
						<template #default="{ row }" v-if="column.prop === 'ts'" >
							{{ formatDate(row.ts) }}
						</template>
					</el-table-column>
				</scTable>
			</el-main>

		</el-container>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
		</template>
	</sc-dialog>
</template>
<script>

export default {
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "show",
			titleMap: {
				show: '设备历史数据'
			},
			visible: false,
			//表单数据
			dialog: {
				save: false
			},
			listApi: null,
			listApiParams: {},
			columns: [],
			search: {
				date: [
					new Date(new Date().setMonth(new Date().getMonth() - 3)), // 最近三个月
					new Date() // 当前时间
				]
			}
		}
	},

	methods: {
		setData(data){
			this.columns=[]
			this.listApiParams = {
				deviceId: data.id,
				startTime: this.search.date[0],
				endTime: this.search.date[1]
			}

			this.gteDeviceMode(data.id)

		},
		//显示
		open(mode='show'){
			this.mode = mode;
			this.visible = true;
			return this
		},
		upsearch() {
			const [startDate, endDate] = this.search.date;
			const maxTimeRange = 120 * 24 * 60 * 60 * 1000; // 90天

			if (endDate.getTime() - startDate.getTime() > maxTimeRange) {
				this.$message.warning('时间范围不能超过三个月，已自动调整');
				this.search.date[1] = new Date(startDate.getTime() + maxTimeRange); // 自动调整结束时间
			}

			var params = {
				startTime: this.search.date[0],
				endTime: this.search.date[1]
			};
			this.$refs.table.upData(params);
		},
		formatDate(dateString) {
			const date = new Date(dateString);
			const year = date.getFullYear();
			const month = String(date.getMonth() + 1).padStart(2, '0');
			const day = String(date.getDate()).padStart(2, '0');
			const hours = String(date.getHours()).padStart(2, '0');
			const minutes = String(date.getMinutes()).padStart(2, '0');
			const seconds = String(date.getSeconds()).padStart(2, '0');

			return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
		},
		datePickerOptions: {
			onPick: ({ maxDate, minDate }) => {
				if (minDate && maxDate) {
					const timeDiff = Math.abs(maxDate.getTime() - minDate.getTime());
					const threeMonthsInMs = 120 * 24 * 60 * 60 * 1000; // 90天的毫秒数
					if (timeDiff > threeMonthsInMs) {
						this.$message.warning('最多只能选择三个月的时间范围');
						this.search.date = [minDate, new Date(minDate.getTime() + threeMonthsInMs)]; // 自动调整结束时间为最大范围
					}
				}
			},
			disabledDate: (time) => {
				const currentDate = new Date();
				const threeMonthsAgo = new Date(currentDate.setMonth(currentDate.getMonth() - 3));
				return time.getTime() < threeMonthsAgo.getTime() || time.getTime() > new Date().getTime();
			}
		},
		//查询历史数据
		async gteDeviceMode(deviceId){
			var res = await this.$API.device.deviceItem.getDeviceModeMap.get({'deviceId':deviceId});
			if(res.code == 200) {
				this.columns = res.data;
				this.$nextTick(()=>{
				this.listApi = this.$API.device.historyData.page;
				})
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		}
	}
}


</script>

<style>

</style>
