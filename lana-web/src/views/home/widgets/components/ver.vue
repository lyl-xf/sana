<template>
	<el-card shadow="hover" header="版本信息">
		<div style="height: 210px;text-align: center;">
			<img src="img/ver.svg" style="height:140px"/>
<!--			<h2 style="margin-top: 15px;">sana {{$CONFIG.CORE_VER}}</h2>-->
			<h2 style="margin-top: 15px;">sana {{ver}}</h2>
			<p style="margin-top: 5px;">最新版本 {{ver}}</p>
		</div>
		<div style="margin-top: 20px;">
			<el-button type="primary" plain round @click="golog">更新日志</el-button>
			<el-button type="primary" plain round @click="gogit">github</el-button>
		</div>
	</el-card>
</template>

<script>
	export default {
		title: "版本信息",
		icon: "el-icon-monitor",
		description: "当前项目版本信息",
		data() {
			return {
				ver: 'loading...'
			}
		},
		mounted() {
			this.getVer()
		},
		methods: {
			async getVer(){
				const ver = await this.$API.common.ver.get()
				this.ver = ver.data
			},
			golog(){
				window.open("https://github.com/lyl-xf/sana/releases")
			},
			gogit(){
				window.open("https://github.com/lyl-xf/sana")
			}
		}
	}
</script>
