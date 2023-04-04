package com.madao.base.controller.blog;

import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "音乐")
@RestController
@RequestMapping(value = "/api/ba/music")
public class ApiMusicController {

	@GetMapping
	@Operation(summary = "查询所有音乐", description = "ApiMusic")
	public JsonData<Map<String, Object>> findMusicByCondition() {
//		List<Music> musics = apiMusicService.findMusicByCondition();

		String hashMap = "{\"tracks\":[{\"name\":\"Unsayable\",\"id\":31152622,\"ar\":[{\"id\":1054179,\"name\":\"Brambles\",\"tns\":[],\"alias\":[]}],\"al\":{\"id\":3112512,\"name\":\"Charcoal\",\"picUrl\":\"http://p2.music.126.net/axYIK-N-ljMKv8ELHX2fyA==/7727367720543371.jpg\",\"tns\":[],\"pic\":7727367720543371},\"dt\":172000}]}\n";
		Map<String, Object> s = JsonUtil.jsonToMap(hashMap);
		return JsonData.success(s);
	}

	@GetMapping(value = "/{musicId}/url")
	@Operation(summary = "音乐Url", description = "ApiMusic")
	public JsonData<List<Map<String, Object>>> findMusicURL(@PathVariable String musicId) {
//		Music music = apiMusicService.findMusicDetail(musicId);
		String tempUrl = "[{\n" +
				"    \"url\": \"https://vue-admin-guoguang.oss-cn-shanghai.aliyuncs.com/%EF%BC%B4%EF%BC%B2%EF%BC%A1%EF%BC%84%EF%BC%A8%20-%20WAITING.mp3\"\n" +
				"  }]";
		List<Map<String, Object>> hashMap = JsonUtil.jsonToListMap(tempUrl);
		return JsonData.success(hashMap);
	}

	@GetMapping(value = "/lyric/{musicId}")
	@Operation(summary = "音乐歌词", description = "ApiMusic")
	public JsonData<Map<String, Object>> findMusicLyric(@PathVariable String musicId) {
		String objMap = "{\"status\":\"success\",\"message\":\"获取音乐歌词成功\",\"result\":{\"uncollected\":true,\"sgc\":true,\"sfy\":true,\"qfy\":true,\"needDesc\":true,\"code\":200,\"briefDesc\":null}}\n";
		Map<String, Object> objectMap = JsonUtil.jsonToMap(objMap);
		return JsonData.success(objectMap);
	}


}
