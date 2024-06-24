import React from 'react';
import './Header.css'; 
import { headerMenus, loginMenus } from '../data/menu';
// import MenuIcon from '../svg/MenuIcon';
import { Link } from 'react-router-dom';
import { Bars3Icon } from '@heroicons/react/24/outline';

const Header = () => {
    return (
        <header id='header' className='absolute inset-x-0 top-0 z-50'>
        <nav className='header_menu flex items-center justify-between p-6 lg:px-8'>

          {/* 메뉴아이콘 */}
          <div className="flex">
            <button
              type="button"
              className="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-red-600 m-2"
              // onClick={() => setMobileMenuOpen(true)}
            >
              {/* <span className="sr-only">Open main menu</span> */}
              <Bars3Icon className="h-6 w-6" aria-hidden="true"/>
            </button>
          </div>
          
          <div id='menu' className='lg:justify-center'>
            <ul className='flex items-center justify-center lg:justify-between hidden lg:flex lg:gap-x-12'>
                {headerMenus.map((menu, key) => ( 
                  <li key={key} className='text-red-600 font-semibold border'>
                    <Link to={menu.link}> 
                      {menu.title}
                    </Link>
                  </li>
                ))}
              </ul>
          </div>
            
          <ul className='menu_login flex lg:justify-end'>
              {loginMenus.map((menu, key) => (
                <button key={key} id='btn_rounded' className='m-1 overflow-wrap-normal'>
                  <Link to={menu.link}>
                    {menu.title}
                  </Link>
                </button>
              ))}
            </ul>
        </nav>
      </header>
    )
}

export default Header;
