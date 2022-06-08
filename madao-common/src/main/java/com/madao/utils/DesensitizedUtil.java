package com.madao.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据脱敏工具类
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class DesensitizedUtil {

	// 通用验证
	//-----------------------------------------------------------------------

	/**
	 * 【中文姓名】只显示第一个汉字，其他隐藏为2个星号
	 * <p>
	 * DesensitizedUtil.password("李六子"); = "李**"
	 * </p>
	 */
	public static String chineseName(String fullName) {
		if (StringUtils.isBlank(fullName)) {
			return "";
		}
		String name = StringUtils.left(fullName, 1);
		return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
	}

	/**
	 * 【身份证号】显示最后四位，其他隐藏。共计18位或者15位
	 * <p>
	 * DesensitizedUtil.password("140311199701017639"); = "*************7639"
	 * </p>
	 */
	public static String idCardNum(String id) {
		if (StringUtils.isBlank(id)) {
			return "";
		}
		String num = StringUtils.right(id, 4);
		return StringUtils.leftPad(num, StringUtils.length(id), "*");
	}

	/**
	 * 【固定电话】 显示后四位，其他隐藏，比如：
	 * <p>
	 * DesensitizedUtil.password("01086551122"); = "*******1122"
	 * </p>
	 */
	public static String fixedPhone(String num) {
		if (StringUtils.isBlank(num)) {
			return "";
		}
		return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
	}

	/**
	 * 【手机号码】前三位，后四位，其他隐藏，比如：
	 * <p>
	 * DesensitizedUtil.password("17667198751"); = "176****8751"
	 * </p>
	 */
	public static String mobilePhone(String num) {
		if (StringUtils.isBlank(num)) {
			return "";
		}
		return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));
	}

	/**
	 * 【地址】只显示到地区，不显示详细地址
	 * <p>
	 * DesensitizedUtil.password("上海徐汇区漕河泾开发区青门路308弄"); = "上海徐汇区漕河泾开发区***"
	 * </p>
	 *
	 * @param sensitiveSize 敏感信息长度
	 */
	public static String address(String address, int sensitiveSize) {
		if (StringUtils.isBlank(address)) {
			return "";
		}
		int length = StringUtils.length(address);
		return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
	}

	/**
	 * 【电子邮箱】 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示
	 * <p>
	 * DesensitizedUtil.password("demo@126.com"); = "d**@126.com"
	 * </p>
	 */
	public static String email(String email) {
		if (StringUtils.isBlank(email)) {
			return "";
		}
		int index = StringUtils.indexOf(email, "@");
		if (index <= 1) {
			return email;
		} else {
			return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(StringUtils.mid(email, index, StringUtils.length(email)));
		}

	}

	/**
	 * 【银行卡号】前六位，后四位，其他用星号隐藏每位1个星号
	 * <p>
	 * DesensitizedUtil.password("6222081812002934027"); = "6222081**********4027"
	 * </p>
	 */
	public static String bankCard(String cardNum) {
		if (StringUtils.isBlank(cardNum)) {
			return "";
		}
		return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
	}

	/**
	 * 【密码】密码的全部字符都用*代替
	 * <p>
	 * DesensitizedUtil.password("123456"); = "******"
	 * </p>
	 */
	public static String password(String password) {
		if (StringUtils.isBlank(password)) {
			return "";
		}
		String pwd = StringUtils.left(password, 0);
		return StringUtils.rightPad(pwd, StringUtils.length(password), "*");
	}


	// 自定义验证
	//-----------------------------------------------------------------------

	/**
	 * 保留前面几位
	 * <p>
	 * DesensitizedUtil.left("张虎子",1); = "张**"
	 * DesensitizedUtil.left("17667198751",3); = "176********"
	 * </p>
	 *
	 * @param str   fullName
	 * @param index index
	 * @return String
	 */
	public static String left(String str, int index) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		String name = StringUtils.left(str, index);
		return StringUtils.rightPad(name, StringUtils.length(str), "*");
	}

	/**
	 * 前面保留 index 位明文，后面保留 end 位明文,如：[身份证号] 110****58，前面保留3位明文，后面保留2位明文
	 * <p>
	 * DesensitizedUtil.around("张虎子",1,1); = "张*子"
	 * DesensitizedUtil.around("140311199701017639",3,3); = "140************639"
	 * </p>
	 *
	 * @param str   name
	 * @param index index
	 * @param end   end
	 * @return String
	 */
	public static String around(String str, int index, int end) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return StringUtils.left(str, index).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(str, end), StringUtils.length(str), "*"), "***"));
	}

	/**
	 * 保留后面几位
	 * <p>
	 * DesensitizedUtil.around("张虎子",1); = "**子"
	 * DesensitizedUtil.around("17667198751",3); = "********751"
	 * </p>
	 *
	 * @param str name
	 * @param end end
	 * @return String
	 */
	public static String right(String str, int end) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return StringUtils.leftPad(StringUtils.right(str, end), StringUtils.length(str), "*");
	}
}
