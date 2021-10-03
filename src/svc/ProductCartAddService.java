package svc;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;

import static db.Jdbcutil.*;

import vo.Basket_detail;
import vo.Product;

public class ProductCartAddService {

	public Product geteCartIce(String id) {

		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);

		// 상품코드
		Product product = productDAO.selectProduct(id);
		
		System.out.println(id);
		
		//System.out.println("22?");
		//System.out.println(id); null?왜!?!
		//System.out.println(product); //여기서도 null이다 
		//System.out.println(serial_code_p);22값을 줌
		close(con);
		return product;
	}

	public ArrayList<Basket_detail> addCart(HttpServletRequest request, Product cartProduct) {
		HttpSession session = request.getSession();

		ArrayList<Basket_detail> cartList = (ArrayList<Basket_detail>) session.getAttribute("cartList");

		// 장바구니에 물건이 없으면
		if (cartList == null) {
			cartList = new ArrayList<Basket_detail>();
			session.setAttribute("cartList", cartList);
		}

		boolean isNewCart = true;

		
		// 아이스크림 상품 중 이름이 같으면!!!
		for (int i = 0; i < cartList.size(); i++) {
			if (cartProduct.getName().equals(cartList.get(i).getBasket_detail_name())) {
				isNewCart = false;
				cartList.get(i).setBasket_detail_count(cartList.get(i).getBasket_detail_count()+1);
				break;
			}

		}
		//System.out.println(cartProduct); // cartProduct가 null이다 
		
		// 똑같은걸 클릭하면 수량 자동 1증가
		if(isNewCart == true) {
			Basket_detail basket_detail = new Basket_detail();
			
			basket_detail.setBasket_detail_name(cartProduct.getName());			//상품 이름
			basket_detail.setBasket_detail_choice(cartProduct.getChoice());		//상품 선택메뉴
			basket_detail.setBasket_detail_count(cartProduct.getCount());			//상품 수량
			basket_detail.setBasket_detail_date(cartProduct.getPI_date());			//현재 날짜
			basket_detail.setBasket_basket_num(cartProduct.getSerial_code_p());	//상품 번호
			basket_detail.setBasket_price(cartProduct.getPrice());					//상품 가격
			basket_detail.setMember_id(cartProduct.getId());						//상품id???   회원id???
			
			cartList.add(basket_detail);
			
		}
		return cartList;
	}
}
