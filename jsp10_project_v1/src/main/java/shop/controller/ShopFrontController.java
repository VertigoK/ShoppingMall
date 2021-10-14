package shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.action.Action;
import shop.action.CancelOrderAction;
import shop.action.DeleteAccountAction;
import shop.action.LogInAction;
import shop.action.LogOutAction;
import shop.action.MemberDetailAction;
import shop.action.MemberModifyAction;
import shop.action.OrderListAction;
import shop.action.ProductDetailAction;
import shop.action.ProductListAction;
import shop.action.ProductOrderAction;
import shop.action.ProductOrderFormAction;
import shop.action.SignUpAction;
import shop.vo.ActionForward;

@WebServlet("*.sh")
public class ShopFrontController extends HttpServlet {

	private static final long serialVersionUID = 1405052343288250106L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doProcess(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doProcess(req, res);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String command = req.getServletPath();
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/logInForm.sh")) {
			forward = new ActionForward();`
			forward.setRedirect(true);
			forward.setPath("/shop/login.jsp");
		} else if(command.equals("/logIn.sh")) {
			action = new LogInAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/logOut.sh")) {
			action = new LogOutAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/signUpForm.sh")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/shop/signup.jsp");
		} else if(command.equals("/signUp.sh")) {
			action = new SignUpAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/deleteAccountForm.sh")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/shop/delete_account.jsp");
		} else if(command.equals("/deleteAccount.sh")) {
			action = new DeleteAccountAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		} else if(command.equals("/memberDetail.sh")) {
			action = new MemberDetailAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/memberModifyForm.sh")) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/shop/member_modify.jsp");
		} else if(command.equals("/memberModify.sh")) {
			action = new MemberModifyAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/productList.sh")) {
			action = new ProductListAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/productDetail.sh")) {
			action = new ProductDetailAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/productOrderForm.sh")) {
			action = new ProductOrderFormAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/productOrder.sh")) {
			action = new ProductOrderAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.equals("/orderList.sh")) {
			action = new OrderListAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else if(command.equals("/cancelOrder.sh")) {
			action = new CancelOrderAction();
			try {
				forward = action.execute(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// sendRedirect() or forward() 선택
		if(forward != null) {
			String path = forward.getPath();
			if(forward.isRedirect()) {
				res.sendRedirect(path);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher(path);
				rd.forward(req, res);
			}
		}
		
		/* 
		 > sendRedirect() 
		   1. URL을 변경 (브라우저가 해당 URL을 얻기 위해 request를 새로하게 됨)
		   2. browser가 새로운 request를 생성하므로 기존 request parameter를 전달하지 않음
	       3. 기존 request parameter를 전달하고 싶다면 아래 예제처럼 session 객체에 저장해서 전달해야 함
		   (예제) HttpSession session = req.getSession(); session.setAttribute("login_info", member);
		
		 > forward()
		   1. URL을 변경하지 않음
		   2. browser가 관여하지 않으므로 기존 request parameter를 그대로 전달함
		
		 > 적용 대상
		   1. 시스템(session, DB)에 변화가 생기는 요청(로그인, 회원가입, 글쓰기, 주문 등)의 경우 sendRedirect 방식으로 응답
		   2. 시스템에 변화가 생기지 않는 단순 요청(리스트 보기, 검색 등)의 경우 forward 방식으로 응답
		
		 > 상세 설명
		   https://smoothprogramming.com/java/differences-of-sendredirect-and-forward-method-execution-flow/
		*/

	}

}
