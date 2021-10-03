package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberLoginService;
import vo.ActionForward;

public class LoginFindPWProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		MemberLoginService memberLoginService = new MemberLoginService();
		
		String pw = memberLoginService.getMemberFind(id,email);
		
		ActionForward forward = new ActionForward();
		
		if(pw == null || pw == "") {
			forward.setPath("/login/loginFindPWResultForm.jsp");
		} else {
			request.setAttribute("pw", pw);
			forward.setPath("/login/loginFindPWResultForm.jsp");
		}
		
		return forward;
	}

}
