package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

public class OrderPageSelectDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String iceCodes[] = request.getParameterValues("order_check_ice");
		String productCodes[] = request.getParameterValues("order_check_product");
		
		String strIceCount[] = request.getParameterValues("iceCount");
		String strProductCount[] = request.getParameterValues("productCount");
		
		int IntproductCode[] = null;
		int IntIceCount[] =null;
		int IntProductCount[] =null;
		
		int i=0;
		int j=0;
		if(iceCodes == null && productCodes == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('선택되지 않았습니다');");
			out.print("history.back();");
			out.print("</script>");
		} else if (iceCodes != null || productCodes != null) {
			
			if(iceCodes != null) {
				i=0;
				for(i=0; i<iceCodes.length; i++) {
					i++;
				}
//				IntIceCount[] = new int [i];
				for(i =0; i<iceCodes.length; i++) {
					i++;
					IntIceCount[i] =Integer.parseInt(strIceCount[i]);
				}
				
				String InticeCode[] = new String[i];
			} else if (productCodes != null) {
				j=0;
				for(j=0; j<productCodes.length; j++) {
					j++;
				}
				String IntProudctCode[] = new String[i];
				
			}
			
			
		
		}
		
		
		ActionForward forward = null;
		
		return forward;
	}

}
