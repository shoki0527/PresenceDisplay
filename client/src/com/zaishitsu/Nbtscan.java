package com.zaishitsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Nbtscan {
	public static void main(String args[]){
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("./nbtscan.sh");
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			List<String> mac_list = new ArrayList<String>();
			String str = buffReader.readLine();
			while(str != null){
				String[] arraystr = str.split(",");
				//System.out.println(arraystr[4]);
				mac_list.add(arraystr[4]);
				str = buffReader.readLine();
			}
			for(int i=0;i<mac_list.size();i++){
				System.out.println(mac_list.get(i));
			}
			System.out.println("---------以下いるひと---------");
			Member.fileinput();
			String registrated_member="";

			List<String> now_member = new ArrayList<String>();
			for(int i=0;i<mac_list.size();i++){
				for(int j=0;j<Member.list.size();j++){
					if(mac_list.get(i).equals(Member.list.get(j).getMac_addr())){
						now_member.add(Member.list.get(j).getName());
					}
				}
			}
			for(int i=0;i<Member.list.size();i++){
				registrated_member = registrated_member + "," + Member.list.get(i).getName();
			}

			for(int i=0;i<now_member.size();i++){
				System.out.println(now_member.get(i));
			}
			//String post_data = now_member.get(0)+",";
			String post_data = "";
			for(int i=0;i<now_member.size();i++){
				post_data = post_data+","+now_member.get(i);
			}
			post_data = registrated_member + "-" +post_data;
			if(post_data!=null){
				post(post_data);
			}
			else{
				post("誰もいません");
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	public static void post(String post_data){
        String urlString = "http://zaishitsu-puluei.rhcloud.com/fujiilab/View";
        try{
        	URL url = new URL(urlString);

            URLConnection uc = url.openConnection();
            uc.setDoOutput(true);//POST可能にする

            uc.setRequestProperty("User-Agent", "fujiiken");// ヘッダを設定
            uc.setRequestProperty("Accept-Language", "ja");// ヘッダを設定
            OutputStream os = uc.getOutputStream();//POST用のOutputStreamを取得

            //String postStr = "foo1=bar1&foo2=bar2";//POSTするデータ
            String postStr = post_data;//POSTするデータ
            PrintStream ps = new PrintStream(os);
            ps.print(postStr);//データをPOSTする
            ps.close();

            InputStream is = uc.getInputStream();//POSTした結果を取得
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            reader.close();
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL format: " + urlString);
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Can't connect to " + urlString);
            System.exit(-1);
        }
    }
}
