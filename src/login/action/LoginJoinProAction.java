package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinService;
import vo.ActionForward;
import vo.Member;

public class LoginJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MemberJoinService memberJoinService = new MemberJoinService();
		
		Member member = new Member();
		
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setAddress_number(request.getParameter("address_number"));
		member.setAddress(request.getParameter("address"));
		member.setAddress_contents(request.getParameter("address_contents"));
		member.setEmail(request.getParameter("email"));
		member.setBirth(request.getParameter("birth"));
		member.setGender(request.getParameter("gender"));
		
		boolean isInsertmember = memberJoinService.JoinMember(member);

		ActionForward forward = null;
		
		if(!isInsertmember) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("joinSuccess.bk");
		}
		
		return forward;
	}
}
