package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "b_res")

public class B_res {
    // レスID
    @Id
    @Column(name = "r_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer r_id;

    // コメントID
    @ManyToOne
    @JoinColumn(name = "c_id", nullable = false)
    private B_comment b_comments;

    // レス番
    @Column(name = "r_number", nullable = false)
    private Integer r_number;

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public B_comment getB_comments() {
        return b_comments;
    }

    public void setB_comments(B_comment b_comments) {
        this.b_comments = b_comments;
    }

    public Integer getR_number() {
        return r_number;
    }

    public void setR_number(Integer r_number) {
        this.r_number = r_number;
    }
}
