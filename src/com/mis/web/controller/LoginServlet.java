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

	
		if(action.equals("MAIN")){//����ȭ�� ����
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
			//1. ���̵�, ��й�ȣ �Ķ���� ����
			String user = request.getParameter("user");//LOGIN ȭ�鿡�� �Է��� ID
			String pass = request.getParameter("pass");//LOGIN ȭ�鿡�� �Է��� password

			System.out.println(user);//Console â�� USER ID ���
			System.out.println(pass);//Console â�� USER PASSWORD ���

			//2.��û������ ���� : �ʼ��Է��׸�
			if((user != null && user.trim().length() >0) && pass.trim().length()>0){
				//3. Model���� ��ûó�� ����
				LoginBean login = LoginBean.getInstance();
				boolean grade = false;
				
				grade = login.loginCheck(user, pass);

				// 5. ��ûó������� ���� ���� ü���� �̵�
				// 5-1) �������� ���� �Ӽ����� : setAtrribute(kep, Object)
				// 5-2) ������������ �̵�
				if(grade){ //�α��� ����
					Employee emp = LoginBean.getInstance().getEmpInfo(user);
					HttpSession session = request.getSession(true);
					session.setAttribute("userNo", emp.getEmpNo());
					session.setAttribute("userName", emp.getEmpName());
					session.setAttribute("userDept", emp.getEmpDept());
					session.setAttribute("userAuth", emp.getEmpAuth());

					response.setCharacterEncoding("EUC-KR");
					RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
					dispatcher.forward(request, response);

				}else{//�α��� ����
					response.setCharacterEncoding("EUC-KR");
					PrintWriter out = response.getWriter();
					out.print("<script>alert('���̵� �� ��ȣ�� Ȯ���Ͻñ� �ٶ��ϴ�.'); history.go(-1);</stript>");
				}
			}

		}
	}

}
