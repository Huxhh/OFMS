package org.classsix.ofms.domin;

import javax.persistence.*;

/**
 * Created by huxh on 2017/5/3.
 */
@Entity
@Table(name = "SCORE_FILM")
public class FilmScore {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_FS_ID")
    private Integer id;

    @Column(name = "USER_ID")
    private Integer uid;

    @Column(name = "FILM_ID")
    private Long fid;

    @Column(name = "SCORE")
    private float score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
