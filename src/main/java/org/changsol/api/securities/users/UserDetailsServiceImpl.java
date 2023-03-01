package org.changsol.api.securities.users;

import lombok.RequiredArgsConstructor;
import org.changsol.api.apps.users.entity.Users;
import org.changsol.api.apps.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        Users user = userRepository.findByLoginId(arg0).orElseThrow(() -> new UsernameNotFoundException("Not Entity"));
        if (user.getStatus() == Users.Status.WITHDRAWAL){
            throw new UsernameNotFoundException("탈퇴된 계정입니다.");
        }
        
        return new SecurityUser(user);
    }

}
