package com.smilejk930.springboot.restfulapi.sample.user;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserLogin {

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String name, @RequestParam(required = false) String password,
            HttpSession session) {
        // name과 password가 모두 null이 아니고, name이 "admin"이고 password가 "password"인 경우
        if (Objects.nonNull(name) && Objects.nonNull(password) && name.equals("admin") && password.equals("password")) {
            session.setAttribute("user", name); // 세션에 사용자 정보 저장
            return "User Logged In"; // 사용자가 로그인 성공
        } else {
            return "Login Failed"; // 로그인 실패
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Check if the user is already logged in
        if (session.getAttribute("user") != null) {
            // If the user is logged in, clear their session attributes and redirect to the
            // login page
            session.invalidate();
            return "redirect:/login";
        } else {
            // If the user is not logged in, show an error message
            return "redirect:/login-error";
        }
    }
}
