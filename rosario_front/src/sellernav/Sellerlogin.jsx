import styles from "./SellerNav.module.css";
import { IoLogOutOutline } from "react-icons/io5";
import classNames from "classnames";

const Sellerlogin = ({ collapsed, theme }) => {
  return (
    <div className={styles.profiletotal}>
      <div
        className={classNames(styles.profilebigimg, {
          [styles.profilebigimgCollapsed]: collapsed,
          [styles.profilebigimgExpanded]: !collapsed,
        })}
      >
        <img
          src="/src/img/doguricoffee.jpg"
          className={classNames(styles.profileimg, {
            [styles.profileimgCollapsed]: collapsed,
            [styles.profileimgExpanded]: !collapsed,
          })}
        />
      </div>

      <div
        className={classNames(styles.profilebigcontent, {
          [styles.profilebigcontentCollapsed]: collapsed,
          [styles.profilebigcontentExpanded]: !collapsed,
        })}
      >
        <span
          className={classNames(styles.profilecontent, {
            [styles.profilecontentDark]: theme === "dark",
            [styles.profilecontentLight]: theme === "light",
          })}
        >
          도혜꽃집
        </span>
        <span
          className={classNames(styles.profilemail, {
            [styles.profilemailDark]: theme === "dark",
            [styles.profilemailLight]: theme === "light",
          })}
        >
          doguri@naver.com
        </span>
      </div>

      <div className={styles.profileicon}>
        <IoLogOutOutline
          className={classNames(styles.profilelogout, {
            [styles.profilelogoutCollapsed]: collapsed,
            [styles.profilelogoutExpanded]: !collapsed,
            [styles.profilelogoutDark]: theme === "dark",
            [styles.profilelogoutLight]: theme === "light",
          })}
        />
      </div>
    </div>
  );
};

export default Sellerlogin;
