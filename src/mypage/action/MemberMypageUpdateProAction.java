package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberUpdateDeleteService;
import vo.ActionForward;
import vo.Member;

public class MemberMypageUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		
		MemberUpdateDeleteService memberUpdate = new MemberUpdateDeleteService();
		
		member.setPw(request.getParameter("pw"));
		member.setAddress_number(request.getParameter("address_number"));
		member.setAddress(request.getParameter("address"));
		member.setAddress_contents(request.getParameter("address_contents"));
		member.setEmail(request.getParameter("email"));
		member.setBirth(request.getParameter("birth"));
		member.setGender(request.getParameter("gender"));
		
		boolean isUpdateMember = memberUpdate.getMemberDetail(member);
		
		ActionForward forward = null;
		
		if(!isUpdateMember) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('수정실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			session.setAttribute("member", member);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("memberMypageUpdateSuccess.bg");
		}
		
		return forward;
	}

}
