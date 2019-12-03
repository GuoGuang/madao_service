package com.ibole.base.controller.blog;

import com.ibole.base.service.blog.ApiMusicService;
import com.ibole.utils.JsonData;
import com.ibole.utils.JsonUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "音乐")
@RestController
@RequestMapping(value = "/api/base/music")
public class ApiMusicController {

	private final ApiMusicService apiMusicService;

	@Autowired
	public ApiMusicController(ApiMusicService apiMusicService) {
		this.apiMusicService = apiMusicService;
	}

	/**
	 * 查询所有音乐
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findMusicByCondition() {
//		List<Music> musics = apiMusicService.findMusicByCondition();

		String hashMap = "{\"tracks\":[{\"name\":\"Unsayable\",\"id\":31152622,\"ar\":[{\"id\":1054179,\"name\":\"Brambles\",\"tns\":[],\"alias\":[]}],\"al\":{\"id\":3112512,\"name\":\"Charcoal\",\"picUrl\":\"http://p2.music.126.net/axYIK-N-ljMKv8ELHX2fyA==/7727367720543371.jpg\",\"tns\":[],\"pic\":7727367720543371},\"dt\":172000}]}\n";
		Map<String, Object> s = JsonUtil.jsonToMap(hashMap);
		return JsonData.success(s);
	}

	/**
	 * 音乐Url
	 * @param musicId:音乐id
	 * @return  JsonData
	 */
	@GetMapping(value = "/{musicId}/url")
	public JsonData findMusicURL(@PathVariable String musicId) {
//		Music music = apiMusicService.findMusicDetail(musicId);
		String tempUrl = "[{\n" +
				"    \"url\": \"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/%EF%BC%B4%EF%BC%B2%EF%BC%A1%EF%BC%84%EF%BC%A8%20-%20WAITING.mp3\"\n" +
				"  }]";
		List<Map<String, Object>> hashMap = JsonUtil.jsonToListMap(tempUrl);
		return JsonData.success(hashMap);
	}

	/**
	 * 音乐歌词
	 *
	 * @param musicId:音乐id
	 * @return JsonData
	 */
	@GetMapping(value = "/lyric/{musicId}")
	public JsonData findMusicLyric(@PathVariable String musicId) {
		String objMap = "{\"status\":\"success\",\"message\":\"获取音乐歌词成功\",\"result\":{\"uncollected\":true,\"sgc\":true,\"sfy\":true,\"qfy\":true,\"needDesc\":true,\"code\":200,\"briefDesc\":null}}\n";
		Map<String, Object> objectMap = JsonUtil.jsonToMap(objMap);
		return JsonData.success(objectMap);
	}


}
