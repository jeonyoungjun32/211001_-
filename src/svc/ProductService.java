package svc;

import static db.Jdbcutil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDAO;
import vo.Icecream;
import vo.Product;

public class ProductService {

	//IcecreamIngredientListAction
	//IcecreamListAction - 아이스크림
	public ArrayList<Icecream> getListicecreamList() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Icecream> icecreamList = productDAO.getListIcecream();
		
		close(con);
		
		return icecreamList;
	}
		
	//IceCakeIngredientListAction
	//IceCakeListAction - 아이스케이크
	public ArrayList<Product> getListIceCake() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> iceCakeList = productDAO.getListIceCake();
		
		close(con);
		
		return iceCakeList;
	}

	//DessertIngredientListAction
	//DessertListAction - 디저트
	public ArrayList<Product> getListDessert() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> dessertList = productDAO.getListDessert();
		
		close(con);
		
		return dessertList;
	}

	//BeverageIngredientListAction
	//BeverageListAction - 음료
	public ArrayList<Product> getListBeverage() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> beverageList = productDAO.getListBeverage();
		
		close(con);
		
		return beverageList;
	}
	
	//CoffeeIngredientListAction
	//CoffeeListAction - 커피
	public ArrayList<Product> getListCoffee() throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		ArrayList<Product> coffeeList = productDAO.getListCoffee();
		
		close(con);
		
		return coffeeList;
	}

	// AdminProductListFormAction
	public int getAllListCount(int intViewSelect, String choiceNum) throws Exception {
		Connection con = getConnection();

		ProductDAO productDAO = ProductDAO.getInstance();

		productDAO.setConnection(con);

		int AllListCount = productDAO.getAllListCount(intViewSelect, choiceNum);

		close(con);

		return AllListCount;
	}
		
	//AdminProductListFormAction
	public void getAllListSelect(int intViewSelect, String choiceNum) throws Exception {
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		productDAO.getAllListSelect(intViewSelect, choiceNum);
		
		close(con);
		
	}

	
	
	
}
