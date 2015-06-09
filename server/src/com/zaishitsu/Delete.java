package com.zaishitsu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		// TODO Auto-generated method stub
		Member.fileinput();
		int number;
		response.setContentType("text/html;charset=UTF-8");
		number = Integer.parseInt(request.getParameter("delete_number"));
		Member.list.remove(number);
		Member.fileoutput();
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>在室管理システム--削除完了--</title><meta http-equiv=Content-Type Content=text/html;charset=UTF-8></head><body>");
		out.println("削除しました。<br/>");
		out.println("<a href=/zaishitsu/MemberTable>戻る</a><br/>");
		out.println("</body></html>");
	}

}
