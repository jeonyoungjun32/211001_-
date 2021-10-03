package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.AllListService;
import vo.ActionForward;

public class AdminIOProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strAllSerial_code[] = request.getParameterValues("code");
		
		if(strAllSerial_code == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('입고할 상품이 없습니다');");
			out.print("history.back();");
			out.print("</script>");
		}
		int i;
		int j=0;
		for(i=0;i<strAllSerial_code.length;i++) {
			j++;
		}
		String strSerial_code[] = new String[j];
		int allSerial_code[] = new int[j];
		for(i=0; i<strAllSerial_code.length; i++) {
			strSerial_code[i] = strAllSerial_code[i];
			strSerial_code[i] = strSerial_code[i].substring(0, 1);
		}
		
		String strProduct_count[] = request.getParameterValues("IO_count");
		
		int product_count[] = new int[j];
		
		for(i =0; i<strAllSerial_code.length; i++) {
			product_count[i] = Integer.parseInt(strProduct_count[i]);
			allSerial_code[i] = Integer.parseInt(strAllSerial_code[i]);
		}
		
		
		AllListService productListService = new AllListService();
		
		boolean IOSuccess = productListService.adminIOUpdate(allSerial_code,product_count,strSerial_code);
		
		ActionForward forward = null;
		
		if (!IOSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('입고가 실패되었습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			forward = new ActionForward("adminIOProSuccess.bg", false);
		}
		
		return forward;
	}

}
