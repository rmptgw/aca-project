package com.mis.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mis.web.bean.CusBean;

/** * Servlet implementation class CustomerServlet*/
@WebServlet("/cus.do")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("act");

		if (action.equals("CUSL")) {// ������ ����Ʈ
			// ������ ������ �ʱ�ȭ
			CusBean.getInstance().init(request);
			String srch_keyword = request.getParameter("srch_keyword") == null ? ""	: new String(request.getParameter("srch_keyword").getBytes("8859_1"), "EUC-KR");

			// View�� ���� ������ Attribute ����
			request.setAttribute("srch_type", request.getParameter("srch_type"));
			request.setAttribute("srch_keyword", srch_keyword);
			request.setAttribute("cusList", CusBean.getInstance().getCusList());
			request.setAttribute("startPage", CusBean.getInstance().getStartPage());
			request.setAttribute("endPage", CusBean.getInstance().getEndPage());
			request.setAttribute("pageCnt", CusBean.getInstance().getPageCnt());
			request.setAttribute("cpage", CusBean.getInstance().getPage());
			request.setAttribute("totalArticle", CusBean.getInstance().getTotalArticle());
			request.setAttribute("virtual_no", CusBean.getInstance().getVitualNum());
			response.setCharacterEncoding("EUC-KR");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cuslist.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals("CUSCR")) {// ������������̵�
		
			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cuswrite.jsp");
			dispatcher.forward(request, response);
		
		} else if (action.equals("CUSC")) {// �� ���
		
			int res = CusBean.getInstance().createCustomer(request);
			
			if (res > 0) {
				response.setCharacterEncoding("EUC-KR");
 			    RequestDispatcher dispatcher = request.getRequestDispatcher("/cus.do?act=CUSL");
				dispatcher.forward(request, response);
			
			} else {
				response.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('������ ��Ͽ� �����Ͽ����ϴ�. ����Ȯ�� ��Ź�帳�ϴ�.')history.go(-1);</script>");

			}
		} else if (action.equals("CUSR")) {
			int cpage = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
			request.setAttribute("cpage", cpage);
			request.setAttribute("srch_type", request.getParameter("srch_type"));
			request.setAttribute("srch_keyword", request.getParameter("srch_keyword"));
			request.setAttribute("cusInfo", CusBean.getInstance().getCusInfo(request.getParameter("NO")));

			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cusview.jsp");
			dispatcher.forward(request, response);
			
			
		} else if (action.equals("CUSUR")) {// ������ �� ���� + �� ���� ������ �̵�
			request.setAttribute("cusInfo", CusBean.getInstance().getCusInfo(request.getParameter("NO")));

			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cusmodify.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("CUSU")) {// �� ����
			// �������� ������ ó��
			int res = CusBean.getInstance().modifyCustomer(request);
			if (res > 0) {// ������ ��� 1
				request.setAttribute("cusInfo", CusBean.getInstance().getCusInfo(request.getParameter("NO")));

				response.setCharacterEncoding("EUC-KR");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cusview.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('������ ������ �����Ͽ����ϴ�. ����Ȯ�� ��Ź�帳�ϴ�.')history.go(-1);</script>");
			}
		} else if (action.equals("CUSD")) {// ������
			// �������� ������ ó��
			int res = CusBean.getInstance().deleteCustomer(request.getParameter("NO"));

			if (res > 0) {// ������ ��� 1
				response.setCharacterEncoding("EUC-KR");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cus.do?act=CUSL");
				dispatcher.forward(request, response);
			} else {
				request.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('������ ������ �����Ͽ����ϴ�. ����Ȯ�� ��Ź�帳�ϴ�.')history.go(-1);</script>");
			}
		}
	}
}
