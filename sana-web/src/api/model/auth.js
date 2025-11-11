import config from "@/config"
import http from "@/utils/request"

export default {

	/**
	 * 菜单
	 */
	settings: {
		list: {
			url: `${config.API_URL}/camera/settings/getZLMSetting`,
			name: "获取流媒体节点列表",
			get: async function(){
				return await http.get(this.url);
			}
		},
		getZLMServerConfig: {
			url: `${config.API_URL}/camera/settings/getZLMServerConfig`,
			name: "获取流媒体节点列表",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},

		updateZLMServerConfig: {
			url: `${config.API_URL}/camera/settings/updateZLMServerConfig`,
			name: "获取流媒体节点列表",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		restartServer: {
			url: `${config.API_URL}/camera/settings/restartServer`,
			name: "重启zlm服务",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		addStreamProxy: {
			url: `${config.API_URL}/camera/settings/addStreamProxy`,
			name: "增加zlm服务",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},

		updateStreamProxy: {
			url: `${config.API_URL}/camera/settings/updateStreamProxy`,
			name: "修改zlm服务",
			post: async function(data){
				return await http.post(this.url, data, {});
			}
		},
		getPage: {
			url: `${config.API_URL}/camera/settings/page`,
			name: "查询代理设备列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		},
		delStreamProxy: {
			url: `${config.API_URL}/camera/settings/delStreamProxy`,
			name: "删除代理设备列表",
			get: async function(params){
				return await http.get(this.url, params);
			}
		}
	}


}
