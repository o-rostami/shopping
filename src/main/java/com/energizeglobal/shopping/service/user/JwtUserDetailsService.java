package com.energizeglobal.shopping.service.user;

import com.energizeglobal.shopping.config.JwtTokenUtil;
import com.energizeglobal.shopping.exception.BusinessException;
import com.energizeglobal.shopping.exception.NotFoundException;
import com.energizeglobal.shopping.model.dto.JwtRequest;
import com.energizeglobal.shopping.model.dto.JwtResponse;
import com.energizeglobal.shopping.model.dto.UserDto;
import com.energizeglobal.shopping.model.entity.MyUserDetails;
import com.energizeglobal.shopping.model.entity.UserEntity;
import com.energizeglobal.shopping.repository.UserRepository;
import com.energizeglobal.shopping.service.role.RoleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@NoArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${role.user}")
    private String roleUser;

    @Value("${admin.username}")
    private String userName;

    @Value("${admin.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserNameAndBlockedTrue(username);
        if (Objects.nonNull(user) && Objects.nonNull(user.getUserName())) {
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("INVALID.CREDENTIALS");
        }
    }

    public JwtResponse login(JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID.CREDENTIALS");
        }
        UserDetails userDetails = loadUserByUsername(request.getUserName());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(jwtToken);
    }

    public Long register(UserDto request) {
        UserEntity user = userRepository.findByUserName(request.getUserName());
        if (Objects.isNull(user)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(request.getUserName());
            userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
            userEntity.setBlocked(Boolean.TRUE);
            userEntity.getRoles().add(roleService.getRoleByName(roleUser));
            return userRepository.save(userEntity).getId();
        } else {
            throw new BusinessException("DUPLICATE.USERNAME");
        }
    }


    public void blockUser(Long userId) {
        UserEntity userEntity = getUserById(userId);
        if(userEntity.getUserName().equals(userName)){
            throw new BusinessException("ADMIN.BLOCKED.NOT.ALLOWED");
        }
        userEntity.setBlocked(Boolean.FALSE);
        userRepository.save(userEntity);
    }

    private UserEntity getUserById(Long playerId) {
        return userRepository
                .findById(playerId)
                .orElseThrow(() -> new NotFoundException("USER.NOT.EXIST"));
    }

    public void unBlockUser(Long userId) {
        UserEntity userEntity = getUserById(userId);
        userEntity.setBlocked(Boolean.TRUE);
        userRepository.save(userEntity);
    }
}