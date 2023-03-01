package org.changsol.api.securities.users;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.changsol.api.apps.users.dto.UserDto;
import org.changsol.api.apps.users.entity.Users;
import org.changsol.api.apps.users.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUser extends User {

    @Getter
    private final UserDto.Response user;

    public SecurityUser(Users user) {
        super(user.getLoginId(), user.getPassword(), getAuthorities(user));
        this.user = UserMapper.INSTANCE.response(user);
    }

    /**
     * 사용자에 대한 ROLE 지정
     */
    private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        Set<String> authorities = Sets.newHashSet();
        authorities.add("ROLE_USER");
        authorities.add("ROLE_" + user.getType().name());
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
