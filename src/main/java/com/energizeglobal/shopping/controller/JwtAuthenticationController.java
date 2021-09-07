package com.energizeglobal.shopping.controller;

import com.energizeglobal.shopping.model.dto.JwtRequest;
import com.energizeglobal.shopping.model.dto.JwtResponse;
import com.energizeglobal.shopping.service.user.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class JwtAuthenticationController {

    private JwtUserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationController(JwtUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest request) {
        return userDetailsService.getUserByUsername(request);
    }
}
