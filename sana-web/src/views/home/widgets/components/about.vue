<template>
	<el-card shadow="hover" header="关于项目" class="item-background">
		<p> 基于 SpringBoot3.3 + sa-token1.44 + Mybatis-Plus3.5.5 + java17 + mysql8 + minio8.5.1 + redis6.2 + swagger3.0 + vue3.2 + axios + element-plus</p>
		<p>
			欢迎给出宝贵的意见，欢迎提issue： <a href="https://github.com/lyl-xf/sana/issues" target="_blank">sana</a>
		</p>
	</el-card>
</template>

<script>
	export default {
		title: "关于项目",
		icon: "el-icon-setting",
		description: "点个星星支持一下",
		data() {
			return {

			}
		}
	}
</script>

<style scoped>
	.item-background p {color: #999;margin-top:10px;line-height: 1.8;}
</style>
