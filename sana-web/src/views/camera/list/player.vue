<template>
	<el-dialog
		:title="titleMap[mode]"
		v-model="visible"
		:width="1000"
		:close-on-click-modal="false"
		destroy-on-close
		@close="stopPlay"
		@closed="$emit('closed')"
	>
	<div class="webrtc-player">
		<video ref="videoRef" autoplay playsinline muted controls></video>
		<div style="margin-top:10px;">
			<el-button type="success" v-if="form.nodeIp!=''" @click="startPlay">播放视频</el-button>
			<el-button type="warning" v-if="form.nodeIp!=''" @click="stopPlay">停止播放</el-button>
		</div>
	</div>

	<template #footer>
		<el-button @click="onDialogClose ">关 闭</el-button>
	</template>
	</el-dialog>
</template>

<script>
export default {
	emits: ['success', 'closed'],
	data() {
		return {
			mode: "add",
			titleMap: {
				add: '视频播放',
				edit: '视频播放',
				show: '视频播放'
			},
			visible: false,
			isSaveing: false,
			form: {},
			videoRef: null,
			pc: null, // RTCPeerConnection 实例
			zlmServer: "", // ← 改成你的 ZLM 地址
			app: "", // 应用名
			stream: "" // 流名
		}
	},
	mounted() {
		this.videoRef = this.$refs.videoRef;
		console.log("✅ videoRef 已初始化", this.videoRef);
	},
	methods: {
		open(mode = 'add') {
			this.mode = mode;
			this.visible = true;
			this.$nextTick(() => {
				this.videoRef = this.$refs.videoRef;
			});
			return this;
		},

		async startPlay() {
			try {
				if (this.pc) {
					this.pc.close();
					this.pc = null;
				}
				this.zlmServer = this.form.nodeIp;
				this.app = this.form.app;
				this.stream = this.form.stream;

				this.pc = new RTCPeerConnection({
					iceServers: [{urls: 'stun:stun.l.google.com:19302'}]
				});

				this.pc.addTransceiver("video", {direction: "recvonly"});
				this.pc.addTransceiver("audio", {direction: "recvonly"});

				this.pc.ontrack = (event) => {
					console.log('Received track:', event.track.kind);
					let stream = event.streams[0];
					if (!stream) {
						stream = new MediaStream();
						stream.addTrack(event.track);
					}
					this.videoRef.srcObject = stream;
					this.videoRef.muted = true;
					this.videoRef.play().catch(e => console.error("play() failed:", e));
				};

				this.pc.oniceconnectionstatechange = () => {
					console.log("ICE connection state:", this.pc.iceConnectionState);
				};

				const offer = await this.pc.createOffer();
				await this.pc.setLocalDescription(offer);

				await new Promise(resolve => {
					if (this.pc.iceGatheringState === 'complete') resolve();
					else {
						const check = () => {
							if (this.pc.iceGatheringState === 'complete') {
								this.pc.removeEventListener('icegatheringstatechange', check);
								resolve();
							}
						};
						this.pc.addEventListener('icegatheringstatechange', check);
					}
				});

				const apiUrl = `http://${this.zlmServer}/index/api/webrtc?app=${this.app}&stream=${this.stream}&type=play`;
				console.log("🚀 发送 SDP 到 ZLM:", apiUrl);
				const res = await fetch(apiUrl, {
					method: "POST",
					headers: {"Content-Type": "text/plain;charset=utf-8"},
					body: this.pc.localDescription.sdp
				});

				const response = await res.json();
				if (response.code !== 0) throw new Error("ZLM返回错误码: " + response.code);

				await this.pc.setRemoteDescription({
					type: "answer",
					sdp: response.sdp
				});

				console.log("✅ WebRTC 播放成功");
			} catch (e) {
				console.error("❌ WebRTC 播放失败:", e);
				this.reSetCamera();
				this.$message.error("播放失败：正在重新设置视频代理信息，请重新预览");
			}
		},
		/** ✅ 新增：关闭弹窗时自动停止播放 **/
		stopPlay(){
			console.log("🧹 弹窗关闭，停止播放...");
			if (this.pc) {
				this.pc.close();
				this.pc = null;
			}
			if (this.videoRef) {
				this.videoRef.srcObject = null;
				this.videoRef.pause();
			}
		},
		//表单提交方法
		async reSetCamera(){
			var res= await this.$API.auth.settings.updateStreamProxy.post(this.form);
			this.isSaveing = false;
			if(res.code == 200){
				this.$emit('success', this.form, this.mode)
				this.visible = false;
				this.$message.success("添加代理成功，请重新观看")
			}else{
				this.$alert(res.msg, "提示", {type: 'error'})
			}
		},
		onDialogClose() {
			this.stopPlay();
			this.visible = false
		},

		setData(data) {
			Object.assign(this.form, data);
		},

		submit() {
			this.$emit("success");
			this.visible = false;
		}
	}
};
</script>

<style scoped>
.webrtc-player {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 20px;
}

video {
	width: 100%;
	max-width: 900px;
	height: 480px;
	background: black;
	border-radius: 10px;
	display: block;
}
</style>
