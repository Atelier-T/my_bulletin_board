package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.B_user;
import models.validators.B_userValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class B_userUpdateServlet
 */
@WebServlet("/users/update")
public class B_userUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public B_userUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            B_user u = em.find(B_user.class, (Integer)(request.getSession().getAttribute("u_id")));

            // ユーザ名が変更されていれば重複チェック
            Boolean nameDuplicateCheckFlag = true;
            if(u.getU_name().equals(request.getParameter("u_name"))) {
                nameDuplicateCheckFlag = false;
            } else {
                u.setU_name(request.getParameter("u_name"));
            }

            // パスワード欄に入力があったら入力値チェック
            Boolean passwordCheckFlag = true;
            String password = request.getParameter("password");
            if(password == null || password.equals("")) {
                passwordCheckFlag = false;
            } else {
                u.setPassword(
                        EncryptUtil.getPasswordEncrypt(
                                password,
                                (String)this.getServletContext().getAttribute("pepper")
                                )
                        );
            }

            u.setUser_flag(Integer.parseInt(request.getParameter("user_flag")));
            u.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = B_userValidator.validate(u, nameDuplicateCheckFlag, passwordCheckFlag);
            // ヴァリデートで問題があれば前のページに戻る、問題がなければ登録
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", u);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("user_id");

                response.sendRedirect(request.getContextPath() + "/users/index");
            }
        }
    }

}
