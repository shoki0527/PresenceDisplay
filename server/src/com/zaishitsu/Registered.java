package com.zaishitsu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registered
 */
public class Registered extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registered() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member.fileinput();
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String name;
		String host_name;
		String mac_addr;
		//List<Member> list = new ArrayList<Member>();
		name = request.getParameter("name");
		host_name = request.getParameter("host_name");
		mac_addr = request.getParameter("mac_addr");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		boolean regist = true;
		for(int i=0;i<Member.list.size();i++){
			if(Member.list.get(i).getName().equals(name)){
				regist = false;
			}
		}
		if(regist){
			Member.list.add(new Member(name,host_name,mac_addr));
			int list_size = Member.list.size();
			out.println("<html><head><title>在室管理システム--登録完了--</title><meta http-equiv=Content-Type Content=text/html;charset=UTF-8></head><body>");
			out.println("使用者名 : "+Member.list.get(list_size-1).getName()+"<br/>");
			out.println("コンピュータ名 : "+Member.list.get(list_size-1).getHost_name()+"<br/>");
			out.println("mac_addr : "+Member.list.get(list_size-1).getMac_addr()+"<br/>");
			out.println("上記のように登録しました<br/>");
			out.println("リストのサイズ"+list_size+"<br/>");
			out.println("<center><a href=/zaishitsu/Registration>戻る</a></center></body></body></html>");
		}
		else{
			out.println("<html><head><title>在室管理システム--登録完了--</title><meta http-equiv=Content-Type Content=text/html;charset=UTF-8></head><body>");
			out.println("あなたは既に登録されています。");
			//out.println(System.getProperty("user.dir"));
			out.println("<center><a href=/zaishitsu/Registration>戻る</a></center></body></body></html>");
		}
		out.close();
		Member.fileoutput();
	}

}
