package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberLoginService;
import vo.ActionForward;

public class LoginFindIDProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String member_name = request.getParameter("name");
		String member_email = request.getParameter("email");
		
		MemberLoginService memberLoginService = new MemberLoginService();
		
		String getId = memberLoginService.getMemberId(member_name, member_email);
		
		ActionForward forward = new ActionForward();
		if(getId == null || getId == "") {
			forward.setPath("/login/loginFindIDResultForm.jsp");
		} else {
			request.setAttribute("id", getId);
			forward.setPath("/login/loginFindIDResultForm.jsp");
		}
		return forward;
	}
}
