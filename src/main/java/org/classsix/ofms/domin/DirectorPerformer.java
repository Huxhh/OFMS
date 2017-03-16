package org.classsix.ofms.domin;

import org.classsix.ofms.domin.common.BaseEntity;

import javax.persistence.*;

/**
 * Created by Jiang on 2017/3/16.
 *
 * @author Jiang
 */
@Entity
@Table(name = "FILM_PERFORMER")
public class DirectorPerformer extends BaseEntity {
    private static final long serialVersionUID = -1307074322826515682L;

    /** 导演演员表主键*/
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "PK_FILM_PERFORMER_REF_ID")
    private Integer id;

    /**演员外键 */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_PERFORMER_ID")
    private Performer performer;

    /** 角色外键*/
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_FILM_ID")
    private Film film;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
