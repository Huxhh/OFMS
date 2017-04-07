package org.classsix.ofms.domin;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by huxh on 2017/4/7.
 */
public class BuyFilm {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_BF_ID")
    private Integer id;

    @Column(name = "USER_ID")
    private Integer uid;

    @Column(name = "FILM_ID")
    private Integer fid;

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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public BuyFilm() {
    }

    public BuyFilm(Integer uid, Integer fid) {
        this.uid = uid;
        this.fid = fid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuyFilm buyFilm = (BuyFilm) o;

        if (id != null ? !id.equals(buyFilm.id) : buyFilm.id != null) return false;
        if (uid != null ? !uid.equals(buyFilm.uid) : buyFilm.uid != null) return false;
        return fid != null ? fid.equals(buyFilm.fid) : buyFilm.fid == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        return result;
    }
}
