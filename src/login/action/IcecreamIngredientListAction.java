package login.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ProductService;
import vo.ActionForward;
import vo.Icecream;
import vo.Product;

public class IcecreamIngredientListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Icecream> icecreamList = new ArrayList<Icecream>();
		
		ProductService productService =new ProductService();
				
		icecreamList = productService.getListicecreamList();
		
		ActionForward forward = null;
		
		request.setAttribute("icecreamList", icecreamList);
		forward = new ActionForward();
		forward.setPath("icecreamIngredientList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
