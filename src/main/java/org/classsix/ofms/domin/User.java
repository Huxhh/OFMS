package org.classsix.ofms.domin;

import org.classsix.ofms.domin.common.BasePerson;
import org.classsix.ofms.domin.validgroup.UserGroup;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @Column(name = "USER_NAME", length = 50, nullable = false,unique = true)
    @NotNull(groups = {UserGroup.login.class})
    private String userName;

    /** 邮箱地址 */
    @Column(name = "MAIl", length = 50, nullable = false,unique = true)
    private String mail;

    /** 密码 */
    @Column(name = "PASSWORD", length = 20, nullable = false)
    @NotNull(groups = {UserGroup.login.class})
    private String password;

    /** 余额 */
    @Column(name = "BALANCE", length = 20, nullable = false)
    private Integer balance;

    /**角色权限**/
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Role> roles;


    @Transient
    private String verNum;

    public String getVerNum() {
        return verNum;
    }

    public void setVerNum(String verNum) {
        this.verNum = verNum;
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> auths = new ArrayList<>();
//        List<Role> roles = this.getRoles();
//        for (Role role : roles) {
//            auths.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return auths;
//    }
//
//
//    @Override
//    public String getUsername() {
//        return this.getUserName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }


}
