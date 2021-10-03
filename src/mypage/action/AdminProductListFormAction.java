package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AllListService;
import svc.ProductService;
import vo.ActionForward;
import vo.AllList;
import vo.Member;
import vo.ProductListPageInfo;

public class AdminProductListFormAction implements Action {
	
	/*수정 중*/
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<AllList> allList = new ArrayList<AllList>();
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		
		
		String viewCount = request.getParameter("view_Count");
		int IntviewCount =0;
		if(viewCount == null || viewCount.equals("5")) {
			viewCount="5";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("10")) {
			viewCount="10";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("25")) {
			viewCount="25";
			IntviewCount = Integer.parseInt(viewCount);
		} else if (viewCount.equals("50")) {
			viewCount="50";
			IntviewCount = Integer.parseInt(viewCount);
		}
		
		ProductService productService = new ProductService();
		
		String viewSelect = request.getParameter("Product_Select");
		int IntViewSelect =0;
		String choiceNum ="";
		if(viewSelect == null || viewSelect.equals("ice_cream")) {
			IntViewSelect =5;
			choiceNum = "1";
		} else if (viewSelect.equals("ice_cake")) {
			IntViewSelect=1;
			choiceNum = "2";
		} else if (viewSelect.equals("beverage")) {
			IntViewSelect=2;
			choiceNum = "2";
		} else if (viewSelect.equals("coffee")) {
			IntViewSelect=3;
			choiceNum = "2";
		} else if (viewSelect.equals("dessert")) {
			IntViewSelect=4;
			choiceNum = "2";
		}
		
		//상품 총 갯수
		int AllListCount = productService.getAllListCount(IntViewSelect,choiceNum);
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int maxPage = (int)((double)AllListCount/IntviewCount+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		ProductListPageInfo productListPageInfo = new ProductListPageInfo();
		
		productListPageInfo.setPage(page);
		productListPageInfo.setMemberListCount(AllListCount);
		productListPageInfo.setMaxPage(maxPage);
		productListPageInfo.setStartPage(startPage);
		productListPageInfo.setEndPage(endPage);
		
		productService.getAllListSelect(IntViewSelect,choiceNum);
		
		AllListService allListService =new AllListService();
		
		//수정중
		allList = allListService.getListAll();
		
		ActionForward forward = null;
		
		if(!member.getAuthor().equalsIgnoreCase("2")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('관리자가 아닙니다');");
			out.print("history.back();");
			out.print("</script>");
		} else if (member.getAuthor().equalsIgnoreCase("2")) {
			request.setAttribute("allList", allList);
			forward = new ActionForward();
			forward.setPath("mypage/adminProductList.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
