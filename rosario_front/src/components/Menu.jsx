import React from "react";
import "./Header.css";
import MenuIcon from "../svg/MenuIcon";
import { Link } from "react-router-dom";
import { headerMenus } from "../data/menu";

const Menu = () => {

  //********************테스트용****************/
  return (
    <header id='header'>
      <nav className='header_menu'>
        <ul>
          <li>
              <MenuIcon />
              {/* {menu.icon} */}
          </li>
            {headerMenus.map((menu, key) => (
              <li key={key}>
              {/* <li key={key} className={location.pathname === menu.src ? 'active' : ''}> */}
                <Link to={menu.src}>
                  {menu.title}
                </Link>
              </li>
            ))}
          </ul>
      </nav>
    </header>
  );
};

export default Menu;
