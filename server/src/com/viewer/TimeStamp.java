package com.viewer;

import java.util.ArrayList;
import java.util.List;

public class TimeStamp {
	public static List<TimeStamp> list = new ArrayList<TimeStamp>();
	private String member;
	private String time = "在室記録なし";

	public TimeStamp(String member ,String time) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.member =member ; this.time = time;
	}
	public TimeStamp(String member) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.member = member;
	}

	public String getMember() {
		return member;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
