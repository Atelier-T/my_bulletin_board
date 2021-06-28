package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "b_users")

@NamedQueries({
    // ユニット名が既にあるか確認
    @NamedQuery(
        name = "checkRegisteredName",
        query = "SELECT COUNT(u) FROM B_user AS u WHERE u.u_name = :u_name"
    ),
    // 全てのユーザ情報を取得
    @NamedQuery(
        name = "getAllUsers",
        query = "SELECT u FROM B_user AS u"
    ),
    // ユーザ情報の数を取得
    @NamedQuery(
        name = "getUsersCount",
        query = "SELECT COUNT(u) FROM B_user AS u"
    ),
})

public class B_user {
    // ユーザID
    @Id
    @Column(name = "u_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer u_id;

    // ユーザ名
    @Column(name = "u_name", length = 255, nullable = false, unique = true)
    private String u_name;

    // パスワード
    @Column(name = "password", length = 64, nullable = false)
    private String password;

    // ユーザ分類
    // 0.管理者　1.ユーザ　2.凍結中ユーザ
    @Column(name = "user_flag", nullable = false)
    private Integer user_flag;

    // 作成日時
    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    // 更新日時
    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    // その他の情報
    @Column(name = "u_info", nullable = true)
    private String u_info;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_flag() {
        return user_flag;
    }

    public void setUser_flag(Integer user_flag) {
        this.user_flag = user_flag;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getU_info() {
        return u_info;
    }

    public void setU_info(String u_info) {
        this.u_info = u_info;
    }
}
