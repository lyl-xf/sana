<template>
  <el-empty image="img/404.png" :image-size="280" description="请检查自身权限，如果没有问题，点击下方按钮让我们进入大屏页面开始新的旅程吧！如果没有权限，则进不去哦。当然，本页面不建议给普通用户使用">
	  <div class="button-container">
		  <button class="fancy-button" @click="navigateToDashboard">
			  <span class="button-text">点击进入大屏页面</span>
			  <el-icon class="button-icon"><ArrowRight /></el-icon>
		  </button>
	  </div>
  </el-empty>

</template>

<script setup>
import { ArrowRight } from '@element-plus/icons-vue'
import tool from '@/utils/tool';

const navigateToDashboard = () => {
	/*const url = new URL(process.env.GOVIEW_API_BASEURL);*/
	const goviewUrl = process.env.VUE_APP_GOVIEW_BASEURL + '/#/project/items?token='+tool.cookie.get("TOKEN")
	window.open(goviewUrl, '_blank')
}
</script>

<style scoped>
.button-container {
  margin-top: 20px;
}

.fancy-button {
  position: relative;
  padding: 15px 30px;
  font-size: 16px;
  font-weight: bold;
  color: white;
  background: linear-gradient(45deg, #ff6b6b, #4ecdc4);
  border: none;
  border-radius: 50px;
  cursor: pointer;
  overflow: hidden;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.fancy-button:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.3);
  background: linear-gradient(45deg, #4ecdc4, #ff6b6b);
}

.fancy-button:active {
  transform: translateY(1px);
}

.fancy-button::before {
  content: '';
  position: absolute;
  top: 0;
  left: -100%;
  width: 100%;
  height: 100%;
  background: linear-gradient(
    90deg,
    transparent,
    rgba(255, 255, 255, 0.2),
    transparent
  );
  transition: 0.5s;
}

.fancy-button:hover::before {
  left: 100%;
}

.button-text {
  position: relative;
  z-index: 1;
}

.button-icon {
  position: relative;
  z-index: 1;
  margin-left: 8px;
  transition: transform 0.3s ease;
  width: 1em;
  height: 1em;
}

.fancy-button:hover .button-icon {
  transform: translateX(3px);
}
</style>
