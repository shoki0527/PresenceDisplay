package com.zaishitsu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberTable
 */
public class MemberTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member.fileinput();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html lang=ja><head><title>在室管理システム--メンバ一覧--</title><meta http-equiv=Content-Type Content=text/html;charset=UTF-8></head><body>");
		out.println("<h2 align=center>登録者一覧</h2>");
		out.println("<table align=center border=1><tr><td>名前</td><td>ＰＣ名</td><td>MACアドレス</td><td>コマンド</td></tr>");
		for(int i=0;i<Member.list.size();i++){
			out.println("<tr><td>"+Member.list.get(i).getName()+"</td><td>"+Member.list.get(i).getHost_name()+"</td><td>"+Member.list.get(i).getMac_addr()+"</td>");
			out.println("<td><form action=/zaishitsu/Delete method=post><input type=hidden name=delete_number value="+i+"><input type=submit value=削除></form></td></tr>");
		}
		out.println("</table><center><a href=/zaishitsu/>戻る</a></center></body></html>");
		out.close();
	}
}
