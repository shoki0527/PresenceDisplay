package com.viewer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class View
 */
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Now now;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		String data = now.getNow()+",damy";
		String[] dataSP = data.split("-");

		String registrated_member = dataSP[0];
		String[] registrated_memberSP = registrated_member.split(",");

		String now_member = dataSP[1];
		String[] now_memberSP =now_member.split(",");

		PrintWriter out = response.getWriter();
		out.println("<html lang=ja><head><title>在室管理システム--view--</title><meta http-equiv=Content-Type Content=text/html;charset=UTF-8><link rel=stylesheet href=table.css /></head><body><h1 align=center>藤井研究室 在室管理システム</h1><br/>");
		out.println("<h2 align=center>最終更新日時 ： "+now.getTime()+"</h2><br/>");
		out.println("<table border=1 align=center><tbody><tr align=center><td width=25%><b>登録者</b></td>"
				+ "<td width=18%><b>在室</b></td><td width=18%><b>不在</b></td><td width=57%><b>最終在室日時</b></td></tr>");
		//if(now_memberSP.length==1){
		//	out.println("<tr><td>誰もいません</td></tr>");
		//}
		/*
		for(int i=1;i<now_memberSP.length;i++){
			out.println("<tr><td aligen=center bgcolor=#66bbff><b>"+now_memberSP[i]+"</b></td></tr>");
		}
		*/
		for(int i=1;i<registrated_memberSP.length;i++){
			out.println("<tr align=center><td><b><h2>"+registrated_memberSP[i]+"</h2></b></td><td>.");
			boolean iru = false;
			for(int j=1;j<now_memberSP.length;j++){
				if(registrated_memberSP[i].equals(now_memberSP[j])){
					//out.println("○");
					out.println("<img src=/zaishitsu/img/"+((int)(Math.random() * 20) + 1)+".png height=64 width=64>");
					iru = true;
					boolean aru=false;
					int num=0;
					for(int b=0;b<TimeStamp.list.size();b++){
						if(TimeStamp.list.get(b).getMember().equals(now_memberSP[j])){
							aru = true;
							num = b;
						}
					}
					if(aru){
						TimeStamp.list.get(num).setTime(now.getTime());
					}
					else{
					TimeStamp.list.add(new TimeStamp(now_memberSP[j],now.getTime()));
					}
					//out.println("<td>"+now.getTime()+"</td>");
				}
				else{
					out.println("");
				}
			}
			if(iru){
				out.println(".</td><td>.</td>");
			}
			else{
				out.println("</td><td><img src=/zaishitsu/img/"+((int)(Math.random() * 20) + 1)+".png height=64 width=64></td>");
			}
			for(int z=0;z<TimeStamp.list.size();z++){
				if(registrated_memberSP[i].equals(TimeStamp.list.get(z).getMember())){
					out.println("<td>"+TimeStamp.list.get(z).getTime()+"</td>");
				}
			}
			out.println("</tr>");
		}
		out.println("</tbody></table><br/><br/><center><a href=http://192.168.11.2:8080/zaishitsu/>管理画面（研究室内からのアクセスのみ）</a></center><br/>");

		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Date date1 = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
		String time =sdf1.format(date1);
		String data="";
		InputStream in = request.getInputStream();
		BufferedReader r = new BufferedReader(new InputStreamReader(in, "utf-8"));
		String line = null;
		while( (line = r.readLine()) != null) {
			data = data + line;
		}
		now = new Now(data,time);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html lang=ja><head><title>在室管理システム--view--</title><meta http-equiv=Content-Type Content=text/html;charset=UTF-8></head><body>");
		out.println("現在の在室者: "+ now.getNow() +"<br/>");
		out.println("時間: "+ now.getTime() +"<br/>");
		out.println("</body></html>");
		out.close();
	}

}
