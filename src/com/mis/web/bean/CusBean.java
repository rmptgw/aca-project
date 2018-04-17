package com.mis.web.bean;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mis.web.dao.CustomerDao;
import com.mis.web.model.Customer;

public class CusBean {

	private static CusBean instance;

	public static CusBean getInstance() {
		if (instance == null) {
			instance = new CusBean();
		}
		return instance;
	}

	int virtual_no;// 가상 글번호
	int start_page;// 페이징 첫번째 페이지
	int end_page;// 페이징 마지막페이지
	int page_cnt;// 페이지 전체수
	int page;// 현재 페이지
	int totalArticle;// 전체 게시물수

	String srch_type;
	String srch_keyword;

	ArrayList<Customer> cusList = new ArrayList<Customer>();

	public void init(HttpServletRequest req) throws UnsupportedEncodingException {

		int cpp = 10;// 한페이지당 표시 게시물수
		page = req.getParameter("page") == null ? 1 : Integer.valueOf(req.getParameter("page"));

		srch_type = req.getParameter("srch_type") == null ? "" : req.getParameter("srch_type");
		srch_keyword = req.getParameter("srch_keyword") == null ? ""
				: new String(req.getParameter("srch_keyword").getBytes("8859_1"), "EUC-KR");
		cusList = CustomerDao.getInstance().getCusList(cpp, page, srch_type, srch_keyword);
		// totalArticle Size 가져오는 Query 실행
		totalArticle = CustomerDao.getInstance().getCusCnt();
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
		System.out.println(cusList.size());
	}

	public Customer getCusInfo(String no) {
		Customer cus = CustomerDao.getInstance().getCusDetailInfo(no);
		return cus;
	}

	public int modifyCustomer(HttpServletRequest req) {
		int res = 0;
		// 전달받은 parameter 값으로 Customer 객체 생성
		Customer cus = new Customer();
		cus.setCusNo(req.getParameter("NO"));
		cus.setCusName(req.getParameter("cusName"));
		cus.setCusHP(req.getParameter("cusHP"));
		cus.setCusEmail(req.getParameter("cusEmail"));
		cus.setCusAdd(req.getParameter("cusAdd"));
		// 생성된 객체를 DAO 전달
		res = CustomerDao.getInstance().updateCustomer(cus);
		// 수정 결과 값 리턴
		return res;
	}

	public int createCustomer(HttpServletRequest req) {
		int res = 0;
		// 전달받은 parameter 값으로 Customer 객체 생성
		Customer cus = new Customer();
		cus.setCusName(req.getParameter("cusName"));
		cus.setCusHP(req.getParameter("cusHP"));
		cus.setCusEmail(req.getParameter("cusEmail"));
		cus.setCusAdd(req.getParameter("cusAdd"));

		// 생성된 객체를 DAO 전달
		res = CustomerDao.getInstance().createCustomer(cus);
		
		// 수정 결과 값 리턴
		return res;
	}

	public int deleteCustomer(String no) {
		int res = 0;
		res = CustomerDao.getInstance().deleteCustomer(no);
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

	public ArrayList<Customer> getCusList() {
		return cusList;
	}

	public void setCusList(ArrayList<Customer> cusList) {
		this.cusList = cusList;
	}
}