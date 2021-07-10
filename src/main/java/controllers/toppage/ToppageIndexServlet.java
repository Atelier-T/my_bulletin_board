package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.B_comment;
import utils.DBUtil;

/**
 * Servlet implementation class ToppageIndexServlet
 */
@WebServlet("/index.html")
public class ToppageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToppageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // データベースから情報を取得
        // コメント情報
        List<B_comment> comments = em.createNamedQuery("getAllComments", B_comment.class)
                .getResultList();

        em.close();


        // 取得した情報をJSPに渡す
        request.setAttribute("comments", comments);

        // フラッシュメッセージがあれば表示し、データを削除
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        // ログイン状態ならコメントを書き込めるようにトークンを設定
        if(request.getSession().getAttribute("login_user") != null) {
            request.setAttribute("_token", request.getSession().getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}
