package com.mis.web.bean;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mis.web.dao.EmployeeDao;
import com.mis.web.dao.ProviderDao;
import com.mis.web.model.Employee;
import com.mis.web.model.Provider;

import oracle.net.aso.b;

public class ProvBean {

	private static ProvBean instance;

	public static ProvBean getInstance() {
		if (instance == null) {
			instance = new ProvBean();
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

	ArrayList<Provider> provList = new ArrayList<Provider>();

	public void init(HttpServletRequest req) throws UnsupportedEncodingException {

		int cpp = 10;// ���������� ǥ�� �Խù���
		page = req.getParameter("page") == null ? 1 : Integer.valueOf(req.getParameter("page"));

		srch_type = req.getParameter("srch_type") == null ? "" : req.getParameter("srch_type");
		srch_keyword = req.getParameter("srch_keyword") == null ? ""
				: new String(req.getParameter("srch_keyword").getBytes("8859_1"), "EUC-KR");
		provList = ProviderDao.getInstance().getProvList(cpp, page, srch_type, srch_keyword);
		// totalArticle Size �������� Query ����
		totalArticle = ProviderDao.getInstance().getProvCnt();
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
		System.out.println(provList.size());
	}

	public Provider getProvInfo(String no) {
		Provider prov = ProviderDao.getInstance().getProvDetailInfo(no);
		return prov;
	}

	public int modifyProvider(HttpServletRequest req) {
		int res = 0;
		// ���޹��� parameter ������ Provider ��ü ����
		Provider prov = new Provider();
		prov.setProvNo(req.getParameter("NO"));
		prov.setProvName(req.getParameter("provName"));
		prov.setProvCEO(req.getParameter("provCEO"));
		prov.setProvHP(req.getParameter("provHp"));
		prov.setProvEmail(req.getParameter("provEmail"));
		prov.setProvAdd(req.getParameter("provAdd"));
		// ������ ��ü�� DAO ����
		res = ProviderDao.getInstance().updateProvider(prov);
		// ���� ��� �� ����
		return res;
	}

	public int createProvider(HttpServletRequest req) {
		int res = 0;
		// ���޹��� parameter ������ Provider ��ü ����
		Provider prov = new Provider();
		prov.setProvName(req.getParameter("provName"));
		prov.setProvCEO(req.getParameter("provCEO"));
		prov.setProvHP(req.getParameter("provHp"));
		prov.setProvEmail(req.getParameter("provEmail"));
		prov.setProvAdd(req.getParameter("provAdd"));

		// ������ ��ü�� DAO ����
		res = ProviderDao.getInstance().createProvider(prov);
		
		// ���� ��� �� ����
		return res;
	}

	public int deleteProvider(String no) {
		int res = 0;
		res = ProviderDao.getInstance().deleteProvider(no);
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

	public ArrayList<Provider> getProvList() {
		return provList;
	}

	public void setProvList(ArrayList<Provider> provList) {
		this.provList = provList;
	}
}