package com.JWT_S3_Security.JWT_S3_Security_Study.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    // Spring Security의 UserDetails 인터페이스를 구현한 사용자 세부 정보 클래스

    private static final long serialVersionUID= 1L;
    // 직렬화를 위한 버전 UID

    private long id;
    // 인덱스

    private String userId;
    // 아이디

    private String password;
    // 비밀번호

    private Collection<? extends GrantedAuthority> authorities;
    // 사용자의 권한 목록을 나타내는 변수이다.

    public UserDetailsImpl(Long id , String userId , String password,
                           Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleType().toString()));
        return new UserDetailsImpl(
                user.getIdx(),
                user.getUserId(),
                user.getPassword(),
                authorities);
    }
    // User 기반으로 UesrDetailsImpl 객체를 생성하는 정적 메소드
    // user : 사용자 정보를 담고 있다.
    // authorities : 사용자의 권한을 나타내는 GrantedAuthority 객체를 생성하고 , HashSet에 추가한다.
    // RoleType을 사용자의 역할을 문자열로 변환하여 권한 객체를 생성한다.



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
        // 사용자의 권한 목록을 반환한단.
    }

    public long getId(){return id;}
    // 사용자의 인덱스를 반환한다.

    @Override
    public String getPassword() {
        return password;
        // 사용자의 비밀번호를 반환한다.
    }

    @Override
    public String getUsername() {
        return userId;
        // 사용자의 아이디를 반환한다.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // 사용자의 계정이 만료되지 않았는지 여부를 반환하는 메소드
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // 사용자 계정이 잠겨있지 않은지 여부를 반환하는 메소드
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // 사용자의 인증 정보가 만료되지 않았는지 여부를 반환하는 메소드
    }

    @Override
    public boolean isEnabled() {
        return true;
        //사용자 계정이 활성화되어 있는지 여부를 반환하는 메소드
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(userId , user.userId);
    }

}
