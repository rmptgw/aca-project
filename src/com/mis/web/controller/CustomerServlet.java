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

		if (action.equals("CUSL")) {// 고객관리 리스트
			// 고객관리 데이터 초기화
			CusBean.getInstance().init(request);
			String srch_keyword = request.getParameter("srch_keyword") == null ? ""	: new String(request.getParameter("srch_keyword").getBytes("8859_1"), "EUC-KR");

			// View로 보낼 데이터 Attribute 설정
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

		} else if (action.equals("CUSCR")) {// 고객등록페이지이동
		
			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cuswrite.jsp");
			dispatcher.forward(request, response);
		
		} else if (action.equals("CUSC")) {// 고객 등록
		
			int res = CusBean.getInstance().createCustomer(request);
			
			if (res > 0) {
				response.setCharacterEncoding("EUC-KR");
 			    RequestDispatcher dispatcher = request.getRequestDispatcher("/cus.do?act=CUSL");
				dispatcher.forward(request, response);
			
			} else {
				response.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('고객정보 등록에 실패하였습니다. 정보확인 부탁드립니다.')history.go(-1);</script>");

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
			
			
		} else if (action.equals("CUSUR")) {// 수정할 고객 정보 + 고객 수정 페이지 이동
			request.setAttribute("cusInfo", CusBean.getInstance().getCusInfo(request.getParameter("NO")));

			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cusmodify.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("CUSU")) {// 고객 수정
			// 정보수정 데이터 처리
			int res = CusBean.getInstance().modifyCustomer(request);
			if (res > 0) {// 성공일 경우 1
				request.setAttribute("cusInfo", CusBean.getInstance().getCusInfo(request.getParameter("NO")));

				response.setCharacterEncoding("EUC-KR");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cus/cusview.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('고객정보 수정에 실패하였습니다. 정보확인 부탁드립니다.')history.go(-1);</script>");
			}
		} else if (action.equals("CUSD")) {// 고객삭제
			// 정보수정 데이터 처리
			int res = CusBean.getInstance().deleteCustomer(request.getParameter("NO"));

			if (res > 0) {// 성공일 경우 1
				response.setCharacterEncoding("EUC-KR");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/cus.do?act=CUSL");
				dispatcher.forward(request, response);
			} else {
				request.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('고객정보 삭제에 실패하였습니다. 정보확인 부탁드립니다.')history.go(-1);</script>");
			}
		}
	}
}
