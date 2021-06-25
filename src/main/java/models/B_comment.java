package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "b_comments")

public class B_comment {
    // コメントID
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer c_id;

    // ユーザID
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    private B_user b_users;

    // コメント本文
    @Column(name = "content", nullable = false)
    private String content;

    // 削除フラグ
    // 0.削除していない　1.削除済み
    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;

    // 作成日時
    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    // 更新日時
    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public B_user getB_users() {
        return b_users;
    }

    public void setB_users(B_user b_users) {
        this.b_users = b_users;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
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
}
