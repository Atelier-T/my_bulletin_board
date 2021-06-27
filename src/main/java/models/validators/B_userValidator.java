package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.B_user;
import utils.DBUtil;

public class B_userValidator {
    // ユーザ名とパスワードに不備があればerrorsリストにメッセージが入り、返される
    public static List<String> validate(B_user b_user, Boolean nameDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        // ユーザ名に不備があるか確認し、あればerrorsリストへ
        String name_error = validateName(b_user.getU_name(), nameDuplicateCheckFlag);
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        // パスワードに不備があるか確認し、あればerrorsリストへ
        String password_error = validatePassword(b_user.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // それぞれの不備をチェックするクラス

    // ユーザ名の必須入力チェック
    private static String validateName(String u_name ,Boolean nameDuplicateCheckFlag) {
        if(u_name == null || u_name.equals("")) {
            return "ユーザ名を入力してください。";
        }
        // すでに登録されているユーザ名との重複チェック
        if(nameDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredName", Long.class).setParameter("u_name", u_name).getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたユーザ名の情報はすでに存在しています。";
            }
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}
