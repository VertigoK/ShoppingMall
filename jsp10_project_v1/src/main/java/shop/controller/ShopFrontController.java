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
		
		// sendRedirect() or forward() ??????
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
		   1. URL??? ?????? (??????????????? ?????? URL??? ?????? ?????? request??? ???????????? ???)
		   2. browser??? ????????? request??? ??????????????? ?????? request parameter??? ???????????? ??????
	       3. ?????? request parameter??? ???????????? ????????? ?????? ???????????? session ????????? ???????????? ???????????? ???
		   (??????) HttpSession session = req.getSession(); session.setAttribute("login_info", member);
		
		 > forward()
		   1. URL??? ???????????? ??????
		   2. browser??? ???????????? ???????????? ?????? request parameter??? ????????? ?????????
		
		 > ?????? ??????
		   1. ?????????(session, DB)??? ????????? ????????? ??????(?????????, ????????????, ?????????, ?????? ???)??? ?????? sendRedirect ???????????? ??????
		   2. ???????????? ????????? ????????? ?????? ?????? ??????(????????? ??????, ?????? ???)??? ?????? forward ???????????? ??????
		
		 > ?????? ??????
		   https://smoothprogramming.com/java/differences-of-sendredirect-and-forward-method-execution-flow/
		*/

	}

}
