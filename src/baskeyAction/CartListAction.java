package baskeyAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.action.Action;
import svc.ProductCartListService;
import vo.ActionForward;
import vo.Basket_detail;

public class CartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductCartListService productCartListService = new ProductCartListService();	 
		
		ArrayList<Basket_detail> cartList = productCartListService.getCartList(request);
		
		int totalMoney = 0;//지불할 총 금액
		int money = 0;//장바구니 항목 하나에 대한 지불 금액
		
		for (int i = 0; i < cartList.size(); i++) {
			money = cartList.get(i).getBasket_price() * cartList.get(i).getBasket_detail_count();
			totalMoney += money;
		}
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("cartList", cartList);
		
		
		
		return new ActionForward("memberCartList.jsp", false);

	}

}
