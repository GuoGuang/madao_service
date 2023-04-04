package com.madao.model.dto;

import lombok.Data;

import java.util.HashMap;

/**
 * 接收前端弹幕消息
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-26 21:01
 */
@Data
public class BarrageMessage {

	private String text;
	private String date;
	private HashMap<String, String> style;
}
