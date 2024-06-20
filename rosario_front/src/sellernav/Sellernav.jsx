import styles from "./SellerNav.module.css";
import classNames from "classnames";
import { ConfigProvider, Layout, Menu, Switch, Button } from "antd";
import { useState } from "react";
import Sellerlogin from "./Sellerlogin";
import {
  HomeOutlined,
  EditOutlined,
  LineChartOutlined,
  CalendarOutlined,
  TeamOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
} from "@ant-design/icons";
import { useNavigate } from "react-router-dom";

//사이드메뉴바

const { Header, Sider } = Layout;

const SellerNav = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [theme, setTheme] = useState("dark");
  const navigate = useNavigate();

  const changeTheme = (value) => {
    setTheme(value ? "dark" : "light");
  };
  const handleMenuClick = (e) => {
    console.log("click ", e);
    navigate(e.key);
  };

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
      <Layout>
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
              ROSARIO
            </div>
          </div>

          <Menu
            theme={theme}
            mode="inline"
            className={styles.sellernavbar}
            onClick={handleMenuClick}
          >
            <Menu.Item key="/admin/dashboard" icon={<HomeOutlined />}>
              DashBoard
            </Menu.Item>
            <Menu.SubMenu key="edit" icon={<EditOutlined />} title="Edit">
              <Menu.Item key="/admin/edit/catalog">Catalog</Menu.Item>
              <Menu.Item key="/admin/edit/profile">Profile</Menu.Item>
              <Menu.Item key="/admin/edit/product">Product</Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu
              key="sales"
              icon={<LineChartOutlined />}
              title="Sales"
            >
              <Menu.Item key="/admin/sales/term">Term</Menu.Item>
              <Menu.Item key="/admin/sales/type">Type</Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu
              key="orders"
              icon={<CalendarOutlined />}
              title="Orders"
            >
              <Menu.Item key="/admin/orders/suborders">Sub Orders</Menu.Item>
              <Menu.Item key="/admin/orders/genorders">Gen Orders</Menu.Item>
            </Menu.SubMenu>
            <Menu.Item key="/admin/subscription" icon={<TeamOutlined />}>
              Subscription
            </Menu.Item>
          </Menu>

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
        </Layout>
      </Layout>
    </ConfigProvider>
  );
};

export default SellerNav;
