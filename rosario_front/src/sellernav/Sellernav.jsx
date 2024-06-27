import React from "react";
import styles from "./Sellernav.module.css";
import classNames from "classnames";
import { ConfigProvider, Layout, Menu, Switch, Button } from "antd";
import { useState } from "react";
import {
  HomeOutlined,
  EditOutlined,
  LineChartOutlined,
  CalendarOutlined,
  TeamOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
} from "@ant-design/icons";
import { useNavigate, Routes, Route } from "react-router-dom";

import Sellerlogin from "./Sellerlogin";
import Dashboard from "./Dashboard";
import Subscription from "../sellersubscription/Subscription";
import SellerSubOrders from "../sellerorders/SellerSubOrders";
import SellerGenOrders from "../sellerorders/SellerGenOrders";
import SalesTerm from "../sellersales/SalesTerm";
import Catalog from "../selleredit/Catalog";
import Profile from "../selleredit/Profile";
import Product from "../selleredit/Product";

import sellermenu from "../data/sellermenu";

//사이드메뉴바

const { Header, Sider, Content } = Layout;

const Sellernav = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [theme, setTheme] = useState("dark");
  const navigate = useNavigate();

  const changeTheme = (value) => {
    setTheme(value ? "dark" : "light");
  };
  const handleMenuClick = ({ key }) => {
    navigate(key);
  };

  // 아이콘 매핑 함수
  const iconMap = {
    HomeOutlined: <HomeOutlined />,
    EditOutlined: <EditOutlined />,
    LineChartOutlined: <LineChartOutlined />,
    CalendarOutlined: <CalendarOutlined />,
    TeamOutlined: <TeamOutlined />,
  };

  const sellermenuitem = sellermenu.map((item) => ({
    ...item,
    icon: iconMap[item.icon],
    children: item.children?.map((child) => ({
      ...child,
      icon: iconMap[child.icon],
    })),
  }));

  return (
    <ConfigProvider
      theme={{
        components: {
          Layout: {
            bodyBg: "#D9D2D0",
            headerBg: "#D9D2D0",
            siderBg: "#A61717",
          },
          Menu: {
            darkItemBg: "#A61717",
            darkSubMenuItemBg: "#F23030",
            darkItemSelectedBg: "#F29494",
            darkPopupBg: "#260101",

            itemSelectedColor: "#A61717",
            itemSelectedBg: "#F29494",
            itemHoverBg: "#D9D2D0",
          },
        },
        token: {
          colorPrimary: "#260101",
          colorBgSpotlight: "#260101",
        },
      }}
    >
      <Layout style={{ minHeight: "100vh" }}>
        <Sider
          collapsed={collapsed}
          collapsible
          trigger={null}
          theme={theme}
          className={styles.sidebar}
        >
          <div className={styles.logo}>
            <img
              src="/src/img/logo.jpg"
              alt="ROSARIO Logo"
              className={classNames(styles.logoimage, {
                [styles.logoimageCollapsed]: collapsed,
                [styles.logoimageExpanded]: !collapsed,
              })}
            />
            <div
              className={classNames(styles.logoname, {
                [styles.logonameCollapsed]: collapsed,
                [styles.logonameExpanded]: !collapsed,
                [styles.logonameDark]: theme === "dark",
                [styles.logonameLight]: theme === "light",
              })}
            >
              <a href="">ROSARIO</a>
            </div>
          </div>

          <Menu
            theme={theme}
            mode="inline"
            className={styles.sellernavbar}
            onClick={handleMenuClick}
            items={sellermenuitem}
          />

          <div
            className={classNames(styles.switch, {
              [styles.switchCollapsed]: collapsed,
              [styles.switchExpanded]: !collapsed,
            })}
          >
            <Switch
              checked={theme === "dark"}
              onChange={changeTheme}
              checkedChildren="Dark"
              unCheckedChildren="Light"
              className={styles.switchbtn}
            />
          </div>

          <Sellerlogin collapsed={collapsed} theme={theme} />
        </Sider>

        <Layout>
          <Header style={{ padding: 0 }}>
            <Button
              type="text"
              className="collapsebtn"
              onClick={() => setCollapsed(!collapsed)}
              icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            />
          </Header>
          <Content
            style={{
              margin: "0px 40px",
            }}
          >
            <Routes>
              <Route path="dashboard" element={<Dashboard />} />
              <Route path="edit/catalog" element={<Catalog />} />
              <Route path="edit/profile" element={<Profile />} />
              <Route path="edit/product" element={<Product />} />
              <Route path="sales/term" element={<SalesTerm />} />
              {/* <Route path="sales/type" element={<Type />} /> */}
              <Route path="orders/suborders" element={<SellerSubOrders />} />
              <Route path="orders/genorders" element={<SellerGenOrders />} />
              <Route path="subscription" element={<Subscription />} />
            </Routes>
          </Content>
        </Layout>
      </Layout>
    </ConfigProvider>
  );
};

export default Sellernav;
