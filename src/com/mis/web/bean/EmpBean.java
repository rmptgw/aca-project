package com.mis.web.bean;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mis.web.dao.EmployeeDao;
import com.mis.web.model.Employee;

import oracle.net.aso.b;

public class EmpBean {

	private static EmpBean instance;

	public static EmpBean getInstance() {
		if (instance == null) {
			instance = new EmpBean();
		}
		return instance;
	}

	int virtual_no;// ���� �۹�ȣ
	int start_page;// ����¡ ù��° ������
	int end_page;// ����¡ ������������
	int page_cnt;// ������ ��ü��
	int page;// ���� ������
	int totalArticle;// ��ü �Խù���

	String srch_type;
	String srch_keyword;

	ArrayList<Employee> empList = new ArrayList<Employee>();

	public void init(HttpServletRequest req) throws UnsupportedEncodingException {

		int cpp = 10;// ���������� ǥ�� �Խù���
		page = req.getParameter("page") == null ? 1 : Integer.valueOf(req.getParameter("page"));

		srch_type = req.getParameter("srch_type") == null ? "" : req.getParameter("srch_type");
		srch_keyword = req.getParameter("srch_keyword") == null ? ""
				: new String(req.getParameter("srch_keyword").getBytes("8859_1"), "EUC-KR");
		empList = EmployeeDao.getInstance().getEmpList(cpp, page, srch_type, srch_keyword);
		// totalArticle Size �������� Query ����
		totalArticle = EmployeeDao.getInstance().getEmpCnt();
		page_cnt = (int) Math.ceil((float) totalArticle / cpp);
		if (page > page_cnt) {
			page = page_cnt;
		}
		virtual_no = totalArticle - (page - 1) * cpp + 1;
		start_page = (int) ((page - 1) / 10) * 10 + 1;
		end_page = start_page + 9;
		if (end_page > page_cnt) {
			end_page = page_cnt;
		}
		System.out.println("totalArticle: " + totalArticle);
		System.out.println("page_cnt" + page_cnt);
		System.out.println(empList.size());
	}

	public Employee getEmpInfo(String no) {
		Employee emp = EmployeeDao.getInstance().getEmpDetailInfo(no);
		return emp;
	}

	public int modifyEmployee(HttpServletRequest req) {
		int res = 0;
		// ���޹��� parameter ������ Employee ��ü ����
		Employee emp = new Employee();
		emp.setEmpNo(req.getParameter("NO"));
		emp.setEmpName(req.getParameter("empName"));
		emp.setEmpPosi(req.getParameter("empPosi"));
		emp.setEmpDept(req.getParameter("empDept"));
		emp.setEmpHp(req.getParameter("empHp"));
		emp.setEmpEmail(req.getParameter("empEmail"));
		emp.setEmpPw(req.getParameter("empPw"));
		emp.setEmpAuth(req.getParameter("empAuth"));
		// ������ ��ü�� DAO ����
		res = EmployeeDao.getInstance().updateEmployee(emp);
		// ���� ��� �� ����
		return res;
	}

	public int createEmployee(HttpServletRequest req) {
		int res = 0;
		// ���޹��� parameter ������ Employee ��ü ����
		Employee emp = new Employee();
		emp.setEmpName(req.getParameter("empName"));
		emp.setEmpPosi(req.getParameter("empPosi"));
		emp.setEmpDept(req.getParameter("empDept"));
		emp.setEmpHp(req.getParameter("empHp"));
		emp.setEmpEmail(req.getParameter("empEmail"));
		emp.setEmpPw(req.getParameter("empPw"));
		emp.setEmpAuth(req.getParameter("empAuth"));

		// ������ ��ü�� DAO ����
		res = EmployeeDao.getInstance().createEmployee(emp);
		
		// ���� ��� �� ����
		return res;
	}

	public int deleteEmployee(String no) {
		int res = 0;
		res = EmployeeDao.getInstance().deleteEmployee(no);
		return res;
	}

	public int getTotalArticle() {
		return totalArticle;
	}

	public boolean isFirstPage() {
		return page == 1 ? true : false;
	}

	public boolean isEndPage() {
		return page == page_cnt ? true : false;
	}

	public boolean isNowPage(int p) {
		return page == p ? true : false;
	}

	public boolean isPrevPage() {
		return end_page < 11 ? true : false;
	}

	public boolean isNextPage() {
		return end_page == page_cnt ? true : false;
	}

	public int getStartPage() {
		return start_page;
	}

	public int getEndPage() {
		return end_page;
	}

	public int getPageCnt() {
		return page_cnt;
	}

	public int getPage() {
		return page;
	}

	public boolean isList() {
		return totalArticle != 0 ? true : false;
	}

	public int getVitualNum() {
		return --virtual_no;
	}

	public ArrayList<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList<Employee> emList) {
		this.empList = empList;
	}
}