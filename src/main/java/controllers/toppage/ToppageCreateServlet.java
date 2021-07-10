package controllers.toppage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.B_comment;
import models.B_res;
import models.B_user;
import utils.DBUtil;

/**
 * Servlet implementation class ToppageCreateServlet
 */
@WebServlet("/create")
public class ToppageCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToppageCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            // コメント情報格納
            B_comment c = new B_comment();
            // コメント情報格納の為にログインユーザの情報を取得
            B_user u = (B_user)request.getSession().getAttribute("login_user");

            // 各データをコメント情報として格納
            c.setB_users(u);
            c.setContent(request.getParameter("content"));

            c.setDelete_flag(0);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            c.setCreated_at(currentTime);
            c.setUpdated_at(currentTime);

            // 正規表現が上手くいかないので没
//            // 返信のパターンを登録
//            Pattern p = Pattern.compile(".*>{2}[0-9]{*}.*");
//            // 上記のパターンの文字列があるか確認
//            Matcher m = p.matcher(c.getContent());
//            // パターンに合った文字列を入れる
//            List<String> res = new ArrayList<String>();
//            while(m.find()) {
//                res.add(m.group());
//            }
//
//            // 「>>」を除いて最初のレス番を登録
//            if(res != null) {
//                c.setRes(Integer.parseInt(res.get(0).substring(2)));
//            }


//            // コメント本文をチェックし、返信コメントであればその返信先のレス番を記録する
//            // 書き込みのソートを目的とする為、抽出するレス番は最初の1つだけ
//
//            // 返信を示す記号を「>>」として設定
//            // res_checkで「>>」を抽出
//            // res_checkが2の時にnumber_flagを立てて、数字を抽出
//            // pは抽出した一文字が数字がどうかをチェックするパターン
//            // 抽出した番号をr_numberに入れる
//            int res_check = 0;
//            Boolean number_flag = false;
//            Pattern p = Pattern.compile("[0-9]");
//            String r_number = "";
//
//            for(int i = 0; i < c.getContent().length(); i++) {
//                String checked = c.getContent().substring(i, i + 1);
//                if(checked.equals(">")) {
//                    res_check += 1;
//                } else {
//                    res_check = 0;
//                }
//                if(number_flag) {
//                    Matcher m = p.matcher(checked);
//                    if(m.matches()) {
//                        r_number += checked;
//                    } else {
//                        number_flag = false;
//                        if(!r_number.equals("")) {
//                            break;
//                        }
//                    }
//                }
//                if(res_check == 2) {
//                    number_flag = true;
//                }
//            }
//
//            // レス番を抽出した場合のみ登録
//            if(!r_number.equals("")) {
//                c.setRes(Integer.parseInt(r_number));
//            }

            // コメントをデータベースに登録
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "コメントを投稿しました。");

            // 正規表現が上手く通らないので没
//            // 返信データを登録
//            // コメント本文からレス番を抽出
//
//            // 返信のパターンを登録
//            Pattern p = Pattern.compile(".*>{2}[0-9]{*}.*");
//            // 上記のパターンの文字列があるか確認
//            Matcher m = p.matcher(c.getContent());
//            // パターンに合った文字列を入れる
//            List<String> res = new ArrayList<String>();
//            while(m.find()) {
//                res.add(m.group());
//            }
//
//            // 「>>」を除いてレス番を登録
//            if(res != null) {
//                for(int i = 0; i < res.size(); i++) {
//                    B_res r = new B_res();
//                    r.setB_comments(c);
//                    r.setR_number(Integer.parseInt(res.get(i).substring(2)));
//                    em.getTransaction().begin();
//                    em.persist(r);
//                    em.getTransaction().commit();
//                    r = null;
//                }
//            }


            // 登録されないので没
            // コメント本文をチェックし、返信コメントであればその返信先のレス番を記録する
            // 書き込みのソートを目的とする為、抽出するレス番は最初の1つだけ

            // 返信を示す記号を「>>」として設定
            // res_checkで「>>」を抽出
            // res_checkが2の時にnumber_flagを立てて、数字を抽出
            // pは抽出した一文字が数字がどうかをチェックするパターン
            // 抽出した番号をr_numberに入れる
            // 一区切りずつr_numbersに格納していく
            int res_check = 0;
            Boolean number_flag = false;
            Pattern p = Pattern.compile("[0-9]");
            List<String> r_numbers = new ArrayList<String>();
            StringBuilder r_number = new StringBuilder();

            for(int i = 0; i < c.getContent().length(); i++) {
                String checked = c.getContent().substring(i, i + 1);
                if(checked.equals(">")) {
                    res_check += 1;
                } else {
                    res_check = 0;
                }
                if(number_flag) {
                    Matcher m = p.matcher(checked);
                    if(m.matches()) {
                        r_number.append(checked);
                    } else {
                        number_flag = false;
                        r_numbers.add(r_number.toString());
                        r_number = new StringBuilder();
                    }
                }
                if(res_check == 2) {
                    number_flag = true;
                }
            }

            // レス番を抽出した場合のみ登録
            if(r_numbers.size() > 0) {
                for(int i = 0; i < r_numbers.size(); i++) {
                    B_res r = new B_res();
                    r.setB_comments(c);
                    r.setR_number(Integer.parseInt(r_numbers.get(i)));

                    em.getTransaction().begin();
                    em.persist(r);
                    em.getTransaction().commit();
                    r = null;
                }
            }

            em.close();

            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
