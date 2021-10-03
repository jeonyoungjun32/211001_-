package mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberInfoService;
import vo.ActionForward;
import vo.Member;

public class AdminMemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String memberCheckId[] = request.getParameterValues("memberCheck");
		
		String memberUpdateAthor = request.getParameter("MemberUpdateaAthor");
		
		MemberInfoService memberInfoService = new MemberInfoService();

		ArrayList<Member> memberList = memberInfoService.getArrMember(memberCheckId);
		
		if (memberList.isEmpty()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('id가 체크되지 않았습니다.');");
			out.print("location='adminMemberListPro.bg';");
			out.print("</script>");
		}
		
		ActionForward forward = null;
		if(memberUpdateAthor=="") {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('권한이 설정되지 않았습니다.');");
			out.print("location='adminMemberListPro.bg';");
			out.print("</script>");
		} else {
			for(Member members : memberList) {
				boolean updateMemberAuthorSuccess = memberInfoService.getArrmemberUpdate(members,memberUpdateAthor);
				
				if(!updateMemberAuthorSuccess) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.print("<script>");
					out.print("alert('권한 수정이 실패되었습니다');");
					out.print("history.back();");
					out.print("</script>");
				} else {
					request.setAttribute("memberUpdateAthor", memberUpdateAthor);
					forward = new ActionForward("adminMemberListUpdateSuccess.bg", false);
				}
			}
		}
		return forward;
	}

}
