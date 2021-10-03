package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.AllListService;
import vo.ActionForward;
import vo.Icecream;
import vo.Member;

public class AdminIcecreamInserProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String icecream_choice = request.getParameter("choice");
		
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("member");
		
		String id = member.getId();
		
		if(icecream_choice == "" || icecream_choice == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('물건 등록 종류를 선택하지 않았습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		Icecream icecream = new Icecream();
		
		icecream.setName(request.getParameter("name"));
		icecream.setKcal(Integer.parseInt(request.getParameter("kcal")));
		icecream.setAllergy(request.getParameter("allergy"));
		icecream.setPrice(Integer.parseInt(request.getParameter("price")));
		icecream.setChoice(icecream_choice);
		
		AllListService productListService = new AllListService();
		
		boolean insertSuccess = productListService.insertIcecream(icecream,id);
		
		ActionForward forward = null;
		
		if(!insertSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("adminProductInsertSuccessForm.bg");
		}
		
		return forward;
	}

}
