import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Login.css";

function LoginPage() {
  const [credentials, setCredentials] = useState({
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredentials((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8081/do_login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(credentials),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      console.log("123");
      // 서버로부터 JSON 형식의 응답을 받음
      const data = await response.json();
      console.log("456");
      // 받은 데이터의 유효성 검사 및 처리
      if (data && data.token) {
        // JWT 토큰과 사용자 정보를 로컬 스토리지에 저장
        console.log("로그인 성공 사용자 정보:", data.user);
        console.log("JWT 토큰:", data.token);
        const user = data.user;
        // 사용자 정보에서 권한을 추출
        const roles = user.authorities.map((auth) => auth.authority);
        const emailAdr = user.username;
        const sellerId = user.sellerId;
        const customerId = user.customerId;
        console.log("로그인 성공 사용자 이메일:", emailAdr);
        console.log("로그인 성공 사용자 ID,S/C:", sellerId, "/", customerId);
        // JWT 토큰과 사용자 정보를 로컬 스토리지에 저장
        localStorage.setItem("token", data.token);
        localStorage.setItem("emlAdr", emailAdr);
        localStorage.setItem("sellerId", sellerId);
        localStorage.setItem("customerId", customerId);
        localStorage.setItem("user", JSON.stringify(user));
        localStorage.setItem("roles", JSON.stringify(roles)); // 권한을 로컬 스토리지에 저장

        // 로그인 성공 후 홈 페이지로 이동
        navigate("/");
      } else {
        throw new Error("로그인 오류: 서버에서 올바른 응답을 받지 못했습니다.");
      }
    } catch (error) {
      console.error("로그인 오류:", error);
    }
  };

  return (
    <div className="h-screen items-center flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-sm"></div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form
          onSubmit={handleLogin}
          className="space-y-6 shadow-sm w-50 p-10 box-content rounded-lg bg-orange-50"
        >
          <div>
            <label
              htmlFor="email"
              className="block text-sm font-medium leading-6 text-neutral-900"
            >
              Email address
            </label>
            <div className="mt-2">
              <input
                id="email"
                name="email" // username을 email로 변경
                type="email"
                autoComplete="email"
                required
                className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 -indigo-600 sm:text-sm sm:leading-6"
                value={credentials.email}
                onChange={handleChange}
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between">
              <label
                htmlFor="password"
                className="block text-sm font-medium leading-6 text-neutral-900"
              >
                Password
              </label>
            </div>
            <div className="mt-2">
              <input
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                required
                className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
                value={credentials.password}
                onChange={handleChange}
              />
            </div>
          </div>

          <div>
            <button
              type="submit"
              className="py-2 bg-red-600 flex w-full justify-center rounded-full px-3 py-1.5 text-bold text-orange-100"
            >
              Sign in
            </button>
          </div>

          <div className="flex justify-center text-xs text-red-600 font-bold space-x-1">
            <button>아이디찾기</button>
            <button>비밀번호찾기</button>
            <Link to="/api/customers/register">개인회원가입</Link>
            <Link to="/api/sellers/register">업체회원가입</Link>
          </div>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
