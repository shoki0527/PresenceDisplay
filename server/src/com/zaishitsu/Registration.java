package com.zaishitsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String IPaddr = request.getRemoteAddr();
		String host_name = null;
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("nbtscan -s , "+ IPaddr);
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String str = buffReader.readLine();

		while(str != null){
			host_name = str;
			str = buffReader.readLine();
		}
		String[] host_name_sp = host_name.split(",");
		PrintWriter out = response.getWriter();
		out.println("<html lang=ja><head><title>在室管理システム--コンピュータ登録--</title><meta http-equiv=Content-Type Content=text/html;charset=UTF-8></head><body>");
		//out.println("IP=" + IPaddr+"<br/>");
		out.println("このPCの情報<br/>コンピュータ名 : "+ host_name_sp[1]+"<br/>");
		out.println("MAC_ADDR : " +host_name_sp[4] +"<br/><br/>");
		out.println("上記のコンピュータ名とMACアドレスを登録します。<br/>PC使用者の名前を<font color=red>英数字で</font>入力してください。<br/>");
		out.println("<form action=/zaishitsu/Registered method=post Accept-charset=UTF-8>");
		out.println("名前 : <input type=text name=name size=40>");
		out.println("<input type=hidden name=host_name value="+host_name_sp[1]+">");
		out.println("<input type=hidden name=mac_addr value="+host_name_sp[4]+">");
		out.println("<input type=submit value=登録></form><br/><a href=/zaishitsu/>戻る</a></body><br/><br/>");
		out.println("----接続元PCのNetBIOSからの応答----<br/>");
		for(int i=0;i<host_name_sp.length;i++){
			out.println("input_st["+i+"]="+host_name_sp[i]+"<br/>");
		}

		out.println("</body></html>");
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
