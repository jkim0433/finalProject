import { headerMenus, loginMenus } from "../data/menu";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const MobileMenuButton = ({ isOpen, onClose }) => {
  const [roles, setRoles] = useState([]); // 역할 상태를 관리하기 위한 상태
  const isAuthenticated = !!localStorage.getItem("token"); // 토큰이 있는지 확인하여 인증 상태를 판별합니다

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

  const handleCloseMenu = () => {
    // 모바일 메뉴를 닫는 로직
    console.log("Closing mobile menu...");
    onClose(); // 부모 컴포넌트에서 전달된 onClose 함수를 호출하여 메뉴를 닫습니다
  };

  return (
    <div
      className={`fixed inset-0 z-50 bg-red-600 w-72 transition-opacity duration-300 ease-in-out ${
        isOpen ? "opacity-100" : "opacity-0 pointer-events-none"
      }`}
    >
      <div className="flex justify-end p-4">
        <button
          type="button"
          className="text-white"
          // 모바일 메뉴를 닫는 로직을 추가할 수 있습니다
          onClick={handleCloseMenu}
        >
          X
        </button>
      </div>
      <div className="flex flex-col ml-10 mt-10 justify-left f-full">
        {/* 여기에 실제 모바일 메뉴의 내용을 추가하세요 */}
        <ul className="space-y-4">
          {headerMenus.map((menu, key) => {
            // ROLE_SELLER 역할을 가진 경우 "mypage" 링크를 조건부로 렌더링합니다
            if (menu.title === "mypage" && roles.includes("ROLE_SELLER")) {
              return null; // ROLE_SELLER에게는 mypage 링크를 렌더링하지 않습니다
            }
            return (
              <li key={key} className="text-orange-50 font-bold">
                <Link to={menu.link}>{menu.title}</Link>
              </li>
            );
          })}
          {roles.includes("ROLE_SELLER") && (
            <li className="text-orange-50 font-bold">
              <Link to="/admin/dashboard">dashboard</Link>
            </li>
          )}
        </ul>
        <div className="mt-10">
          <ul className="space-y-4">
            {/* 인증 상태에 따라 조건부 렌더링 처리 */}
            {isAuthenticated ? (
              // 인증되었을 때 로그아웃 버튼을 보여줍니다
              <>
                <li className="text-orange-50 font-bold">
                  <Link to="/cart">Cart</Link>
                </li>
                <button className="text-orange-50 font-bold" onClick={handleLogout}>
                  Logout
                </button>
              </>
            ) : (
              // 미인증 상태일 때 로그인 버튼을 보여줍니다
              loginMenus.map((menu, key) => (
                <li key={key} className="text-orange-50 font-bold">
                  <Link to={menu.link}>{menu.title}</Link>
                </li>
              ))
            )}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default MobileMenuButton;
