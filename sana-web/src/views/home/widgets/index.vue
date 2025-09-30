<template>
	<el-main>
<!--		<el-alert title="以下数据均为暂时写死的数据，后续会不断地调整和对接正式的数据进行展示。目前仅仅是为了首页的数据展示" type="success" style="margin-bottom:20px;"></el-alert>-->
		<el-row :gutter="15">
			<el-col :lg="8">
				<el-card shadow="hover" header="本服务连接消息状态" v-loading="loading">
					<scEcharts ref="c1" height="260px" :option="option"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option2"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option3"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="24">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option4"></scEcharts>
				</el-card>
			</el-col>
<!--			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option5"></scEcharts>
				</el-card>
			</el-col>
			<el-col :lg="8">
				<el-card shadow="never">
					<scEcharts height="300px" :option="option6"></scEcharts>
				</el-card>
			</el-col>-->
		</el-row>
	</el-main>
</template>

<script>
import scEcharts from '@/components/scEcharts';

/**
 * 引入组件 @/components/scEcharts
 * 组件内部会自动加载主题 @/components/scEcharts/echarts-theme-T.js
 * 支持props包括 height，width，option
 * 组件export百度Echarts所有方法，使用方式: new scEcharts[fun]
 */

export default {
	name: 'chart',
	components: {
		scEcharts
	},
	data() {
		return {
			option: {
				tooltip: {
					trigger: 'axis'
				},
				xAxis: {
					boundaryGap: false,
					type: 'category',
					data: (function (){
						var now = new Date();
						var res = [];
						var len = 30;
						while (len--) {
							res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
							now = new Date(now - 2000);
						}
						return res;
					})()
				},
				yAxis: [{
					type: 'value',
					name: '消息数量',
					"splitLine": {
						"show": false
					}
				}],
				series: [
					{
						name: '收入',
						type: 'line',
						symbol: 'none',
						lineStyle: {
							width: 1,
							color: '#409EFF'
						},
						areaStyle: {
							opacity: 0.1,
							color: '#79bbff'
						},
						data: (function (){
							var res = [];
							var len = 30;
							while (len--) {
								res.push(Math.round(Math.random() * 0));
							}
							return res;
						})()
					},
				],
			},
			option2: {
				title: {
					text: '日志数据累计统计',
					subtext: '数据累计雷达图',
				},
				tooltip: {
					trigger: 'item'
				},
				radar: {
					radius: 100,
					center: ['50%', '55%'],
					indicator: [
						{ name: '系统操作(crud)'},
						{ name: '设备操作'},
						{ name: '设备告警'},
						{ name: '解除告警'},
						{ name: '情景模式'}
					]
				},
				series: [{
					name: "平台日志统计",
					type: 'radar',
					areaStyle: {},
					data: [
						{
							value: [4629, 2512, 2186, 79, 2262],
						}
					]
				}]
			},

			option3: {
				title: {
					text: '设备数量统计',
					subtext: '设备在线、离线饼图',
				},
				tooltip: {
					trigger: 'item'
				},
				series: [
					{
						name: '设备数量统计',
						type: 'pie',
						label: false,
						data: [

						]
					}
				]
			},
			option4: {
				title: {
					text: '情景模式统计',
					subtext: '数据、规则、命中、处理折线图',
				},
				grid: {
					top: '80px'
				},
				tooltip: {
					trigger: 'axis'
				},
				xAxis: {
					type: 'category',
					data: ['2025-6-1', '2025-6-2', '2025-6-3', '2025-6-4', '2025-6-5', '2025-6-6', '2025-6-7','2025-6-8','2025-6-9','2025-6-10','2025-6-11','2025-6-12','2025-6-13','2025-6-14','2025-6-15',]
				},
				yAxis: {
					type: 'value'
				},
				series: [
					{
						name: '条件命中',
						data: [120, 200, 150, 80, 70, 110, 130,120, 200, 150, 80, 70, 110, 130,140],
						type: 'line',
					},
					{
						name: '设备控制',
						data: [110, 180, 120, 120, 120, 200, 150, 80, 70, 110, 130,110, 180, 120, 120],
						type: 'line',
					}
					,
					{
						name: '数据转发',
						data: [130, 150, 90, 170, 120, 50, 150,120, 110, 190, 120, 150, 90, 150,110],
						type: 'line',
					}
					,
					{
						name: '总接受数据条数',
						data: [300, 320, 200, 330, 280, 250, 330,300, 320, 200, 330, 280, 250, 330,400],
						type: 'line',
					}
				]
			}
		}
	},
	created() {
		var _this = this;
		setTimeout(function () {
			_this.loading = false
		}, 500);
	},
	mounted() {
		this.devicesPin(),
		this.getLogData(),
		this.getServeData()
	},
	methods: {
		async devicesPin(){
			var res = await this.$API.device.deviceItem.devicesPin.get();
			if(res.code == 200){
				//这里选择刷新整个表格 OR 插入/编辑现有表格数据
				const formattedData = res.data.map(item => ({
					value: item.value,
					name: item.name
				}));
				this.option3.series[0].data = formattedData;
				//this.$message.success("删除成功")

			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		},
		async getLogData(){
			var res = await this.$API.device.deviceItem.getLogData.get();
			if(res.code == 200){
				console.log(res.data)
				const newDataArray = [
					{ value: [res.data.systemLog, res.data.deviceActionLog, res.data.deviceAlarmsLog, res.data.deviceAlarm, res.data.scenarioModeLog] }        // 包装成数组
				];
				console.log(newDataArray)
				this.option2.series[0].data = newDataArray;

			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		},
		async getServeData(){
			var _this = this;
			setInterval(function (){
				var o = _this.option;

				o.series[0].data.shift()
				o.series[0].data.push(Math.round(Math.random() * 100));

				o.xAxis.data.shift();
				o.xAxis.data.push((new Date()).toLocaleTimeString().replace(/^\D*/, ''));


				//_this.$refs.c1.myChart.setOption(o)
			},2100)

		}

	}
}
</script>

<style>
</style>
