package com.example.rosario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage() {
        log.info("로그인 페이지");
        return "loginPage"; // loginPage.html 파일을 반환
    }//p

    @PostMapping("/perform_login")
    public String performLogin(@RequestParam String username, @RequestParam String password) {
        log.info("로그인 시도: 사용자명 = " + username);
        // Spring Security가 인증 과정을 처리하므로 이 메서드는 실제로 사용되지 않습니다.
        // 그러나 로깅 등의 목적으로 사용할 수 있습니다.
        return "redirect:/"; // 로그인 성공 후 리다이렉트
    }
}
//import React, { useState } from 'react';
//        import axios from 'axios';
//        import { useHistory } from 'react-router-dom';
//
//        function LoginPage() {
//        const [credentials, setCredentials] = useState({
//        username: '',
//        password: '',
//        });
//        const history = useHistory();
//
//        const handleChange = (e) => {
//        const { name, value } = e.target;
//        setCredentials((prevState) => ({
//        ...prevState,
//        [name]: value,
//        }));
//        };
//
//        const handleLogin = async (event) => {
//        event.preventDefault();
//        try {
//        await axios.post('/perform_login', credentials);
//        history.push('/');
//        } catch (error) {
//        console.error('로그인 오류:', error);
//        }
//        };
//
//        return (
//<div>
//<h2>로그인</h2>
//<form onSubmit={handleLogin}>
//<div>
//<label htmlFor="username">사용자명:</label>
//<input
//            type="text"
//                    id="username"
//                    name="username"
//                    value={credentials.username}
//                    onChange={handleChange}
//                    />
//</div>
//<div>
//<label htmlFor="password">비밀번호:</label>
//<input
//            type="password"
//                    id="password"
//                    name="password"
//                    value={credentials.password}
//                    onChange={handleChange}
//                    />
//</div>
//<button type="submit">로그인</button>
//</form>
//</div>
//        );
//        }
//
//        export default LoginPage;