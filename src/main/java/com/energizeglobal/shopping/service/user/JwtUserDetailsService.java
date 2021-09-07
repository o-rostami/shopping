package com.energizeglobal.shopping.service.user;

import com.energizeglobal.shopping.config.JwtTokenUtil;
import com.energizeglobal.shopping.exception.BusinessException;
import com.energizeglobal.shopping.exception.InvalidPasswordException;
import com.energizeglobal.shopping.model.dto.JwtRequest;
import com.energizeglobal.shopping.model.dto.JwtResponse;
import com.energizeglobal.shopping.model.entity.MyUserDetails;
import com.energizeglobal.shopping.model.entity.UserEntity;
import com.energizeglobal.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String userName;

    @Value("${admin.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserNameAndEnabledTrue(username);
        if (Objects.nonNull(user) && Objects.nonNull(user.getUserName())) {
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("INVALID_CREDENTIALS");
        }
    }

    public JwtResponse getUserByUsername(JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            if (request.getUsername().equals(userName)) {
                throw new BadCredentialsException("INVALID.PASSWORD");
            } else {
                throw new InvalidPasswordException("INVALID.USERNAME");
            }
        }
        UserDetails userDetails = loadUserByUsername(request.getUsername());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(jwtToken);
    }
    public UserDetails loadByUserNameAndPassword(String userName, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            throw new BusinessException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new BusinessException("INVALID_CREDENTIALS");
        }
        return loadUserByUsername(userName);
    }

    public UserEntity createApiUser(JwtRequest request) {
        UserEntity user = userRepository.findByUserNameAndEnabledTrue(request.getUsername());
        if (Objects.isNull(user)) {

//            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
//            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
//            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5PADDING");

//            cipher.init(Cipher.ENCRYPT_MODE,keySpec,iv);
//            byte[] encrypted=cipher.doFinal(request.getPassword().getBytes());
//            Base64.encodeBase64String(encrypted);

            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(request.getUsername());
            userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
            userEntity.setEnabled(Boolean.TRUE);
            return userRepository.save(userEntity);
        } else {
            throw new BusinessException("DUPLICATE.USERNAME");
        }
    }


}