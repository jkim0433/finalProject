import React, { useState, useEffect } from "react";
import "./Header.css";
import { headerMenus, loginMenus } from "../data/menu";
import { Link } from "react-router-dom";
import { Bars3Icon } from "@heroicons/react/24/outline";
import MobileMenuButton from "./MobileMenuButton"; // MobileMenuButton 컴포넌트를 가져옵니다

const Header = () => {
  const isAuthenticated = !!localStorage.getItem("token"); // 토큰이 있는지 확인하여 인증 상태를 판별합니다
  const [roles, setRoles] = useState([]); // 역할 상태를 관리하기 위한 상태
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false); // 모바일 메뉴의 가시성을 관리하는 상태

  useEffect(() => {
    // localStorage에서 roles 가져오기
    const storedRoles = localStorage.getItem("roles");
    if (storedRoles) {
      try {
        const parsedRoles = JSON.parse(storedRoles);
        setRoles(parsedRoles); // roles 상태 업데이트
      } catch (error) {
        console.error("localStorage에서 roles 파싱 오류:", error);
      }
    }
  }, []);

  const handleLogout = () => {
    // localStorage에서 토큰과 역할을 삭제하여 로그아웃 처리합니다
    localStorage.removeItem("token");
    localStorage.removeItem("roles");
    // 선택적으로 UI 상태 업데이트 또는 리디렉션을 처리할 수 있습니다
    // 예를 들어, 로그아웃 후 로그인 페이지로 리디렉션할 수 있습니다
    window.location.replace("/"); // 로그인 페이지로 리디렉션
  };

  const toggleMobileMenu = () => {
    setMobileMenuOpen(!mobileMenuOpen); // 모바일 메뉴 상태를 토글합니다
  };

  const closeMobileMenu = () => {
    setMobileMenuOpen(false);
  };
  
  return (
    <header id="header" className="absolute inset-x-0 top-0 z-50">
      <nav className="header_menu flex items-center justify-between p-6 lg:px-8">
        {/* 메뉴아이콘 */}
        <div className="flex">
          <button
            type="button"
            className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-red-600 m-2"
            onClick={toggleMobileMenu}
          >
            <Bars3Icon className="h-6 w-6 inline-flex items-center gap-x-1 leading-6" />
          </button>
        </div>

        <div id="menu" className="lg:justify-center">
          <ul className="flex items-center justify-center lg:justify-between hidden lg:flex lg:gap-x-12">
            {headerMenus.map((menu, key) => {
              // ROLE_SELLER 역할을 가진 경우 "mypage" 링크를 조건부로 렌더링합니다
              if (menu.title === "mypage" && roles.includes("ROLE_SELLER")) {
                return null; // ROLE_SELLER에게는 mypage 링크를 렌더링하지 않습니다
              }
              return (
                <li key={key} className="text-red-600 font-semibold border">
                  <Link to={menu.link}>{menu.title}</Link>
                </li>
              );
            })}
            {roles.includes("ROLE_SELLER") && (
              <li className="text-red-600 font-semibold border">
                <Link to="/admin/dashboard">dashboard</Link>
              </li>
            )}
          </ul>
        </div>

        <ul className="menu_login flex lg:justify-end">
          {/* 인증 상태에 따라 조건부 렌더링 처리 */}
          {isAuthenticated ? (
            // 인증되었을 때 로그아웃 버튼을 보여줍니다
            <>
              <button id="btn_rounded" className="m-1 overflow-wrap-normal">
                <Link to="/cart">Cart</Link>
              </button>
              <button
                id="btn_rounded"
                className="m-1 overflow-wrap-normal"
                onClick={handleLogout}
              >
                Logout
              </button>
            </>
          ) : (
            // 미인증 상태일 때 로그인 버튼을 보여줍니다
            loginMenus.map((menu, key) => (
              <button
                key={key}
                id="btn_rounded"
                className="m-1 overflow-wrap-normal"
              >
                <Link to={menu.link}>{menu.title}</Link>
              </button>
            ))
          )}
        </ul>

        {/* 모바일 메뉴 버튼 클릭 시 나타나는 MobileMenuButton 컴포넌트 */}
        {mobileMenuOpen && <MobileMenuButton isOpen={mobileMenuOpen} onClose={closeMobileMenu} />}
      </nav>
    </header>
  );
};

export default Header;
