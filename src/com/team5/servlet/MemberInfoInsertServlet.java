package com.team5.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team5.dao.MemberDao;
import com.team5.dto.Member;
import com.team5.dto.MemberInfo;
import com.team5.dto.Member;

@WebServlet(value = "/memberinfoinsert.action")
public class MemberInfoInsertServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		RequestDispatcher dispatcher = 
			req.getRequestDispatcher("/WEB-INF/views/member/index.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {

		//1. get data
		String location = req.getParameter("location");
		String highschool = req.getParameter("highschool");
		String university = req.getParameter("university");
		String phone = req.getParameter("phone");
		int memberid = Integer.parseInt(req.getParameter("memberid"));
		

		//2. set data (request & DB에 데이터 저장)
		MemberInfo memberinfo = new MemberInfo();
				   memberinfo.setLocation(location);
				   memberinfo.setHighschool(highschool);
				   memberinfo.setUniversity(university);
				   memberinfo.setPhone(phone);
				   memberinfo.setMemberId(memberid);
		req.setAttribute("memberinfo", memberinfo);

		MemberDao dao = new MemberDao();
		Date created_date = dao.modifyUser(memberinfo);
		memberinfo.setCreateDate(created_date);
		
		//3. forward to jsp
		RequestDispatcher dispatcher =
					req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}
}
