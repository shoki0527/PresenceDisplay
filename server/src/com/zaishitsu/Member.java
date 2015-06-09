package com.zaishitsu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	public static void fileoutput(){
		try {
	      //(2)FileOutputStreamオブジェクトの生成
	      FileOutputStream outFile = new FileOutputStream("data/member.obj");
	      //(3)ObjectOutputStreamオブジェクトの生成
	      ObjectOutputStream outObject = new ObjectOutputStream(outFile);
	      //(4)クラスHelloのオブジェクトの書き込み
	      for(int i=0;i<list.size();i++){
				outObject.writeObject(list.get(i));
	      }
	      outObject.close();  //(5)オブジェクト出力ストリームのクローズ
	      outFile.close();  //(6)ファイル出力ストリームのクローズ
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		File file = new File("data/count.txt");
		try {
			FileWriter filewriter = new FileWriter(file, false);
			filewriter.write(Integer.toString(list.size()));
			filewriter.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public static void fileinput(){
		try {
			list.clear();
			FileInputStream inFile = new FileInputStream("data/member.obj");

			//(8)ObjectInputStreamオブジェクトの生成
			ObjectInputStream inObject = new ObjectInputStream(inFile);
			//(9)オブジェクトの読み込み
			Member mem = (Member)inObject.readObject();
			while(mem!=null){
				list.add(mem);
				mem = (Member)inObject.readObject();
			}
			inObject.close();
			inFile.close();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
