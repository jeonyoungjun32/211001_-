package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.AllList;
import vo.IO;
import vo.Icecream;
import vo.Product;

public class AllListService {
	
	//AdminProductInserProAction : 물건 등록
	public boolean insertProduct(Product product, String id) throws Exception {
		
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		int insertCount = productDAO.insertProduct(product, id);
		
		boolean insertSuccess = false;
		if(insertCount > 0) {
			commit(con);
			insertSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return insertSuccess;
	}
	
	//AdminIcecreamInserProAction : 아이스크림 등록
	public boolean insertIcecream(Icecream icecream, String id) throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		int insertCount = productDAO.insertIceceream(icecream, id);
		
		boolean insertSuccess = false;
		if(insertCount > 0) {
			commit(con);
			insertSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		
		return insertSuccess;
	}
	
	//AdminProductFormAction
	//AdminProductUpdateFormAction
	public AllList getAll(int serial_code) throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		AllList allList = productDAO.getAllFindCode(serial_code);
		
		close(con);
		
		return allList;
	}
	
	//AdminProductUpdateProAction
	public boolean getAllUpdate(int serial_code, String name, String allergy, int kcal, int price) throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		int allUpdateCount = productDAO.AllUpdate(serial_code, name, allergy, kcal, price);
		
		boolean allUpdateSuccess = false;
		if (allUpdateCount > 0) {
			allUpdateSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return allUpdateSuccess;
	}
	
	//AdminProductListFormAction - 물건 리스트
	public ArrayList<AllList> getListAll() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<AllList> allList = productDAO.getListProduct();
		
		close(con);
		
		return allList;
	}

	//AdminProductDeleteProAction
	//AdminIOFormAction
	//체크된 물건 찾기
	public ArrayList<AllList> getArrAll(String[] productCheckCode) throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<AllList> allList = productDAO.adminGetAll(productCheckCode);
		
		close(con);
		
		return allList;
	}

	//AdminProductDeleteProAction - 상품 삭체 요청
	public boolean getArrAllDelete(AllList allList) throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		int arrProductDeleteCount = productDAO.arrAllDelete(allList);
		
		boolean arrProductDeleteSuccess = false;
		if(arrProductDeleteCount > 0) {
			commit(con);
			arrProductDeleteSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return arrProductDeleteSuccess;
	}

	//AdminIOProAction
	public boolean adminIOUpdate(int[] allSerial_code, int[] product_count, String[] strSerial_code) throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		int IOValue = productDAO.arrAdminIOupdate(allSerial_code,product_count,strSerial_code);
		
		boolean IOSuccess = false;
		if(IOValue >0) {
			commit(con);
			IOSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return IOSuccess;
		
	}
	
	//AdminIOListFormAction
	public ArrayList<IO> getIOList() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<IO> ioList = productDAO.selectIOlist();
		
		close(con);
		
		return ioList;
	}
	
}
