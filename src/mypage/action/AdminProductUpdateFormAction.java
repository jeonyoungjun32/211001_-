package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AllListService;
import vo.ActionForward;
import vo.AllList;

public class AdminProductUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int serial_code = Integer.parseInt(request.getParameter("serial_code"));
		
		AllListService productListService = new AllListService();
		
		AllList asllList = productListService.getAll(serial_code);
		
		request.setAttribute("asllList", asllList);
		
		ActionForward forward = new ActionForward("mypage/adminProductUpdateForm.jsp", false);
		
		return forward;
	}

}
