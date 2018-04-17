package com.mis.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mis.web.bean.LoginBean;
import com.mis.web.model.Employee;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//TODO Auto-generated method stub


		String action = request.getParameter("act") == null? "" : request.getParameter("act");

	
		if(action.equals("MAIN")){//메인화면 셋팅
			response.setCharacterEncoding("EUC-KR");
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		} else if(action.equals("LOGOUT")){

			HttpSession session = request.getSession();
			session.removeAttribute((String) session.getAttribute("userNo"));
			session.removeAttribute((String) session.getAttribute("userName"));
			session.removeAttribute((String) session.getAttribute("userAuth"));

			session.invalidate();
			System.out.println("-----------------------------LOGOUT");

			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else{
			//1. 아이디, 비밀번호 파라메터 저장
			String user = request.getParameter("user");//LOGIN 화면에서 입력한 ID
			String pass = request.getParameter("pass");//LOGIN 화면에서 입력한 password

			System.out.println(user);//Console 창에 USER ID 출력
			System.out.println(pass);//Console 창에 USER PASSWORD 출력

			//2.요청데이터 검증 : 필수입력항목
			if((user != null && user.trim().length() >0) && pass.trim().length()>0){
				//3. Model에게 요청처리 위임
				LoginBean login = LoginBean.getInstance();
				boolean grade = false;
				
				grade = login.loginCheck(user, pass);

				// 5. 요청처리결과에 따라 응답 체이지 이동
				// 5-1) 응답결과에 대한 속성설정 : setAtrribute(kep, Object)
				// 5-2) 응답결과페이지 이동
				if(grade){ //로그인 성공
					Employee emp = LoginBean.getInstance().getEmpInfo(user);
					HttpSession session = request.getSession(true);
					session.setAttribute("userNo", emp.getEmpNo());
					session.setAttribute("userName", emp.getEmpName());
					session.setAttribute("userDept", emp.getEmpDept());
					session.setAttribute("userAuth", emp.getEmpAuth());

					response.setCharacterEncoding("EUC-KR");
					RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
					dispatcher.forward(request, response);

				}else{//로그인 실패
					response.setCharacterEncoding("EUC-KR");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('아이디 및 암호를 확인하시기 바랍니다.'); history.go(-1);</stript>");
				}
			}

		}
	}

}
