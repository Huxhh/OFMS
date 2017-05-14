package org.classsix.ofms.domin;

import org.classsix.ofms.domin.common.BasePerson;
import org.classsix.ofms.domin.validgroup.UserGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */
@Entity
@Table(name = "USER")
public class User extends BasePerson{
    private static final long serialVersionUID = 5783613723738241740L;
    /** 用户主键 */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_USER_ID")
    private Integer id;

    /** 账号 */
    @Column(name = "USER_NAME", length = 50, nullable = false)
    @NotNull(groups = {UserGroup.login.class})
    private String userName;

    /** 手机号码 */
    @Column(name = "MAIl", length = 50, nullable = false)
    private String mail;

    /** 密码 */
    @Column(name = "PASSWORD", length = 20, nullable = false)
    @NotNull(groups = {UserGroup.login.class})
    private String password;

    /** 余额 */
    @Column(name = "BALANCE", length = 20, nullable = false)
    private Integer balance;

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
