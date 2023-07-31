package com.JWT_S3_Security.JWT_S3_Security_Study.JWT;

import com.JWT_S3_Security.JWT_S3_Security_Study.model.User;
import com.JWT_S3_Security.JWT_S3_Security_Study.model.UserDetailsImpl;
import com.JWT_S3_Security.JWT_S3_Security_Study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 아이디입니다. 아이디 : " + username));
        return UserDetailsImpl.build(user);
    }
}

