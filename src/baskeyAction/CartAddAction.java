package baskeyAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.action.Action;
import svc.ProductCartAddService;
import vo.ActionForward;
import vo.Product;

public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductCartAddService productCartAddService = new ProductCartAddService();	 
		
		//int serial_code_p = Integer.parseInt(request.getParameter("serial_code_p"));
		String id = request.getParameter("id");
		Product cartProduct = productCartAddService.geteCartIce(id);
		
		System.out.println(id);
		
		//System.out.println(serial_code_p);//22값 받음
		//System.out.println(id); null값줌 

		//System.out.println(cartProduct); //이건 null
		
		
		productCartAddService.addCart(request, cartProduct);
		
		return new ActionForward("memberCartList.bk", true);

	}

}
