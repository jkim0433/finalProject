import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, } from "react-router-dom";
import LoginPage from "./log-in/LoginPage";
import RegisterPage from "./log-in/RegisterPage";
import Header from "./components/Header";
import Home from "./pages/Home";
import Sellernav from "./sellernav/Sellernav";
import SellerRegisterPage from "./log-in/SellerRegisterPage";
import ImgLinkConverter from "./shop/ImgLinkConverter";

function App() {
  const [roles, setRoles] = useState([]);

  useEffect(() => {
    // localStorage에서 roles 가져오기
    const storedRoles = localStorage.getItem("roles");
    if (storedRoles) {
      try {
        const parsedRoles = JSON.parse(storedRoles);
        setRoles(parsedRoles); // roles 상태 업데이트
        console.log("roles in App", parsedRoles);
      } catch (error) {
        console.error("localStorage에서 roles 파싱 오류:", error);
      }
    }
  }, []);

  return (
    <Router>
      <div>
        <Routes>
          {/* Route without Header */}
          <Route path="/loginpage" element={<LoginPage />} />
          <Route path="/api/customers/register" element={<RegisterPage />} />
          <Route path="/api/sellers/register" element={<SellerRegisterPage />} />
          <Route path="/api/uploadProdImg" element={<ImgLinkConverter />} />

          {/* Route with Header */}
          <Route
            path="/*"
            element={
              <>
                <header>
                  <Header />
                </header>
                <main id="main">
                  <Routes>
                    <Route path="/" element={<Home />} />
                  </Routes>
                </main>
                <footer id="footer">{/* Footer content */}</footer>
              </>
            }
          />

          {/* Route with admin */}
          <Route
            path="/admin/*"
            element={
              roles.includes("ROLE_SELLER") ? (
                <Sellernav />
              ) : (
                <Navigate to="/loginpage" replace />
              )
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
