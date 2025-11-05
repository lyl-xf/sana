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
		}
	}


}
