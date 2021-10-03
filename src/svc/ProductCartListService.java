package svc;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Basket_detail;

public class ProductCartListService {

	public ArrayList<Basket_detail> getCartList(HttpServletRequest request){

		//굳이 서비스가 필요없다고 했자나 ㅡㅡ
	HttpSession session = request.getSession();
	ArrayList<Basket_detail> cartList = (ArrayList<Basket_detail>) session.getAttribute("cartList");
	
	return cartList;
	}
}
