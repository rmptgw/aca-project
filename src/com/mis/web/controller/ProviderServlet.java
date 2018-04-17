package com.mis.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mis.web.bean.ProvBean;

/**
 * Servlet implementation class ProviderServlet
 */
@WebServlet("/prov.do")
public class ProviderServlet extends HttpServlet {
public ProviderServlet() {
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

		if (action.equals("PROVL")) {// 납품업체관리 리스트
			// 납품업체관리 데이터 초기화
			ProvBean.getInstance().init(request);
			String srch_keyword = request.getParameter("srch_keyword") == null ? ""
					: new String(request.getParameter("srch_keyword").getBytes("8859_1"), "EUC-KR");

			// View로 보낼 데이터 Attribute 설정
			request.setAttribute("srch_type", request.getParameter("srch_type"));
			request.setAttribute("srch_keyword", srch_keyword);
			request.setAttribute("provList", ProvBean.getInstance().getProvList());
			request.setAttribute("startPage", ProvBean.getInstance().getStartPage());
			request.setAttribute("endPage", ProvBean.getInstance().getEndPage());
			request.setAttribute("pageCnt", ProvBean.getInstance().getPageCnt());
			request.setAttribute("cpage", ProvBean.getInstance().getPage());
			request.setAttribute("totalArticle", ProvBean.getInstance().getTotalArticle());
			request.setAttribute("virtual_no", ProvBean.getInstance().getVitualNum());
			response.setCharacterEncoding("EUC-KR");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/prov/provlist.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals("PROVCR")) {// 납품업체등록페이지이동
		
			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/prov/provwrite.jsp");
			dispatcher.forward(request, response);
		
		} else if (action.equals("PROVC")) {
		
			
			int res = ProvBean.getInstance().createProvider(request);
			
			if (res > 0) {
				response.setCharacterEncoding("EUC-KR");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/prov.do?act=PROVL");
				dispatcher.forward(request, response);
			
			} else {
				response.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('납품업체정보 등록에 실패하였습니다. 정보확인 부탁드립니다.')history.go(-1);</script>");

			}
		} else if (action.equals("PROVR")) {
			int cpage = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
			request.setAttribute("cpage", cpage);
			request.setAttribute("srch_type", request.getParameter("srch_type"));
			request.setAttribute("srch_keyword", request.getParameter("srch_keyword"));
			request.setAttribute("provInfo", ProvBean.getInstance().getProvInfo(request.getParameter("NO")));

			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/prov/provview.jsp");
			dispatcher.forward(request, response);
			
			
		} else if (action.equals("PROVUR")) {// 수정할 납품업체 정보 + 납품업체 수정 페이지 이동
			request.setAttribute("provInfo", ProvBean.getInstance().getProvInfo(request.getParameter("NO")));

			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/prov/provmodify.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("PROVU")) {// 납품업체 수정
			// 정보수정 데이터 처리
			int res = ProvBean.getInstance().modifyProvider(request);
			if (res > 0) {// 성공일 경우 1
				request.setAttribute("provInfo", ProvBean.getInstance().getProvInfo(request.getParameter("NO")));

				response.setCharacterEncoding("EUC-KR");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/prov/provview.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('납품업체정보 수정에 실패하였습니다. 정보확인 부탁드립니다.')history.go(-1);</script>");
			}
		} else if (action.equals("PROVD")) {// 납품업체삭제
			// 정보수정 데이터 처리
			int res = ProvBean.getInstance().deleteProvider(request.getParameter("NO"));

			if (res > 0) {// 성공일 경우 1
				response.setCharacterEncoding("EUC-KR");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/prov.do?act=PROVL");
				dispatcher.forward(request, response);
			} else {
				request.setCharacterEncoding("EUC-KR");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('납품업체정보 삭제에 실패하였습니다. 정보확인 부탁드립니다.')history.go(-1);</script>");
			}
		}
	}}
