package org.classsix.ofms.domin;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by clxy on 2017/4/9.
 */
@Entity
@Table(name = "MOVIE_ITEM")
public class MovieItem implements Serializable {
    private static final long serialVersionUID = -5796252919139756166L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_MOVIE_ID")
    private long id;

    @Column(name = "URL",length = 150,nullable = true)
    private String url;

    @Column(name = "NAME",length = 100,nullable = true)
    private String name;

    @Column(name = "YEAR",length = 40,nullable = true)
    private String year;

    @Column(name = "IMAGE",length = 150,nullable = true)
    private String image;

    @Column(name = "DIRECTOR",length = 80,nullable = true)
    private String director;

    //编剧
    @Column(name = "WRITER",length = 100,nullable = true)
    private String writer;

    @Column(name = "ACTOR",length = 200,nullable = true)
    private String actor;

    @Column(name = "KIND",length = 80,nullable = true)
    private String kind;

    @Column(name = "COUNTRY",length = 80,nullable = true)
    private String country;

    @Column(name = "LANGUAGE",length = 80,nullable = true)
    private String language;

    @Column(name = "DATE",length = 100,nullable = true)
    private String date;

    @Column(name = "LENGTH",length = 100,nullable = true)
    private String length;

    @Column(name = "OTHER_NAME",length = 100,nullable = true)
    private String otherName;

    @Column(name = "IMBD",length = 30,nullable = true)
    private String imbd;

    @Column(name = "SCORE",nullable = true)
    private float score;

    @Column(name = "VOTE_COUNT",nullable = true)
    private long voteCount;

    @Column(name = "STAR",length = 100,nullable = true)
    private String star;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getImbd() {
        return imbd;
    }

    public void setImbd(String imbd) {
        this.imbd = imbd;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
