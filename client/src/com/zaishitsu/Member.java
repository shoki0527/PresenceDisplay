package com.zaishitsu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	public static List<Member> list = new ArrayList<Member>();
	private String name;
	private String host_name;
	private String mac_addr;

	public Member(String name,String host_name,String mac_addr) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;this.host_name=host_name;this.mac_addr=mac_addr;

	}

	public String getName() {
		return name;
	}

	public String getHost_name() {
		return host_name;
	}

	public String getMac_addr() {
		return mac_addr;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}

	public static void fileinput(){
		int count = 0;
		File file = new File("/var/lib/tomcat7/data/count.txt");
			try {
				@SuppressWarnings("resource")
				FileReader filereader = new FileReader(file);
				count = filereader.read();
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			list.clear();
		try {
			FileInputStream inFile = new FileInputStream("/var/lib/tomcat7/data/member.obj");
			//(8)ObjectInputStreamオブジェクトの生成
			ObjectInputStream inObject = new ObjectInputStream(inFile);
			//(9)オブジェクトの読み込み
			for(int i=0;i<count;i++){
				Member mem = (Member)inObject.readObject();
				list.add(mem);
			}

			inObject.close();
			inFile.close();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}