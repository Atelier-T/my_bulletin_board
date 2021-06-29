package controllers.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.B_user;
import utils.DBUtil;

/**
 * Servlet implementation class B_userEditServlet
 */
@WebServlet("/users/edit")
public class B_userEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public B_userEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        B_user u = em.find(B_user.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("user", u);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("u_id", u.getU_id());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
        rd.forward(request, response);
    }

}
