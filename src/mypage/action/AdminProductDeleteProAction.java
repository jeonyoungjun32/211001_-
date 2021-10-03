package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AllListService;
import vo.ActionForward;
import vo.AllList;

public class AdminProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String productCheckCode[] = request.getParameterValues("productCheck");
		
		if(productCheckCode == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('상품이 체크되지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		AllListService productListService = new AllListService();
		
		ArrayList<AllList> allList = productListService.getArrAll(productCheckCode);
		
		if(allList.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('삭제할 리스트가 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		ActionForward forward = null;
		for(AllList alls: allList) {
			boolean arrProductDeleteSuccess = false;
			
			String strCode = Integer.toString(alls.getSerial_code());
			
			if(strCode.substring(0, 1).equalsIgnoreCase("2") || strCode.substring(0, 1).equalsIgnoreCase("1")) {
				arrProductDeleteSuccess = productListService.getArrAllDelete(alls);
			}
			
			System.out.println("test");
			
			if(!arrProductDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('삭제가 실패 되었습니다');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				forward = new ActionForward("adminProductListDeleteSuccess.bg", false);
			}
		}
		
		return forward;
	}

}
