package util;

public class StrUtil {
	public static String sec2time[]= { null,
			"08:00-08:45","08:55-09:40","10:10-10:55","11:05-11:45",
			"02:00-02:45","02:55-03:40","04:00-04:45","04:55-5:40",
			"07:00-07:45","07:55-08:40"
	};
	public static String sec2name[]= { null,
			"第一节","第二节","第三节","第四节",
			"第五节","第六节","第七节","第八节",
			"第九节","第十节"
	};
	public static String secs2name[]= { null,
			"一二节","三四节","五六节","七八节","九十节"
	};
	public static int weekname2id(String week) {
		int id=0;
		switch(week) {
		case "星期一": id=1;break;
		case "星期二": id=2;break;
		case "星期三": id=3;break;
		case "星期四": id=4;break;
		case "星期五": id=5;break;
		}
		return id;
	}
	public static int secname2id(String secname) {
		int id=0;
		switch(secname) {
		case "第一节": id=1;break;
		case "第二节": id=2;break;
		case "第三节": id=3;break;
		case "第四节": id=4;break;
		case "第五节": id=5;break;
		case "第六节": id=6;break;
		case "第七节": id=7;break;
		case "第八节": id=8;break;
		case "第九节": id=9;break;
		case "第十节": id=10;break;
		}
		return id;
	}
	public static int secsname2id(String secsname) {
		int id=0;
		switch(secsname) {
		case "一二节": id=1;break;
		case "三四节": id=3;break;
		case "五六节": id=5;break;
		case "七八节": id=7;break;
		case "九十节": id=9;break;
		}
		return id;
	}
	public static int typename2id(String typename) {
		int id=0;
		switch(typename) {
		case "单周": id=1;break;
		case "双周": id=2;break;
		case "连续周": id=3;break;
		}
		return id;
	}
}
