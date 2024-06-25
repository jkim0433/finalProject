import React, { useState, useEffect } from "react";
import "./Header.css";
import { headerMenus, loginMenus } from "../data/menu";
// import MenuIcon from '../svg/MenuIcon';
import { Link } from "react-router-dom";
import { Bars3Icon } from "@heroicons/react/24/outline";

const Header = () => {
  const isAuthenticated = !!localStorage.getItem("token"); // Check if token exists

  const [roles, setRoles] = useState([]);

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
    // Clear token from localStorage
    localStorage.removeItem("token");
    localStorage.removeItem("roles");
    // Optionally, update UI state or redirect
    // For example, redirect to login page after logout
    window.location.replace("/"); // Redirect to login page
  };

  return (
    <header id="header" className="absolute inset-x-0 top-0 z-50">
      <nav className="header_menu flex items-center justify-between p-6 lg:px-8">
        {/* 메뉴아이콘 */}
        <div className="flex">
          <button
            type="button"
            className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-red-600 m-2"
            // onClick={() => setMobileMenuOpen(true)}
            // onClick={() => setMobileMenuOpen(!mobileMenuOpen)} 
          >
            {/* <span className="sr-only">Open main menu</span> */}
            <Bars3Icon className="h-6 w-6 inline-flex items-center gap-x-1 leading-6" />
            
          </button>

          
        </div>

        <div id="menu" className="lg:justify-center">
          <ul className="flex items-center justify-center lg:justify-between hidden lg:flex lg:gap-x-12">
            {headerMenus.map((menu, key) => {
              // Check if the role is ROLE_SELLER to conditionally render "mypage"
              if (menu.title === "mypage" && roles.includes("ROLE_SELLER")) {
                return null; // Don't render mypage link for ROLE_SELLER
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
          {/* Conditional rendering based on authentication state */}
          {isAuthenticated ? (
            // Show Logout Button when authenticated
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
            // Show Login Button when not authenticated
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
      </nav>
    </header>
  );
};

export default Header;
