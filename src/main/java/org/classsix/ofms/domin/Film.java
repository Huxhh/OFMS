package org.classsix.ofms.domin;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */

import org.classsix.ofms.domin.common.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 电影类
 */
@Entity
@Table(name = "FILM")
public class Film extends BaseEntity {
    private static final long serialVersionUID = -5740919825288439368L;

    /** 电影表主键*/
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_FILM_ID")
    private Integer id;

    /** 导演外键 */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_DIRECTOR_ID")
    private Director director;


    /** 评分*/
    @Column(name = "RANK", length = 2,nullable = false)
    private Double rank;


    /** 类型*/
    @Column(name = "TYPE", length = 2,nullable = false)
    private Integer type;

    /** 地区*/
    @Column(name = "AREA", length = 2,nullable = false)
    private Integer area;

    /**上映日期*/
    @Column(name = "OUT_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }
}
