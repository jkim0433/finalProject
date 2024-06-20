import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import './Login.css'

function LoginPage() {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });
  const navigate = useNavigate(); // useHistory 대신 useNavigate 사용

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
      const response = await fetch("http://localhost:8081/perform_login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      // 로그인 성공 후 홈 페이지로 이동
      navigate("/");
    } catch (error) {
      console.error("로그인 오류:", error);
    }
  };

  return (
    <div className="h-screen items-center flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-sm">
      </div>

      <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
        <form onSubmit={handleLogin} className="space-y-6">
          <div>
            <label htmlFor="email" className="block text-sm font-medium leading-6 text-neutral-900">Email address</label>
            <div className="mt-2">
              <input
                id="email"
                name="username" // username을 email로 변경
                type="email"
                autoComplete="email"
                required
                className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 -indigo-600 sm:text-sm sm:leading-6"
                value={credentials.username}
                onChange={handleChange}
              />
            </div>
          </div>

          <div>
            <div className="flex items-center justify-between">
              <label htmlFor="password" className="block text-sm font-medium leading-6 text-neutral-900">Password</label>
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
            <button type="submit" className="py-2 bg-red-600 flex w-full justify-center rounded-full px-3 py-1.5 text-bold text-orange-100">Sign in</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
