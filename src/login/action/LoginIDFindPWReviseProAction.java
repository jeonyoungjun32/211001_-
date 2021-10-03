package login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberLoginService;
import vo.ActionForward;

public class LoginIDFindPWReviseProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberLoginService memberLoginService = new MemberLoginService();
		
		boolean memberIDReviseSuccess = memberLoginService.getMemberIDRevise(id, pw);
		
		ActionForward forward = null;
		
		if(!memberIDReviseSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호 변경실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("login.bk");
		}
		
		return forward;
	}

}
