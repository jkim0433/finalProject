import { useState } from "react";
import "./ShopList.css";
import { shops } from "../data/shop";

export default function ShopList() {
  const [animate, setAnimate] = useState(true);
  const onStop = () => setAnimate(false);
  const onRun = () => setAnimate(true);

  return (
    <div className="shop_list">
      <div className="wrapper">
        <div className="slide_container">
          <ul
            className="slide_wrapper"
            onMouseEnter={onStop}
            onMouseLeave={onRun}
          >
            <div className={"slide original".concat(animate ? "" : " stop")}>
              {shops.map((text, i) => (
                <li key={i} className={i % 2 === 0 ? "big" : "small"}>
                  <div className="text">
                    <p>{text.text}</p>
                  </div>
                </li>
              ))}
            </div>
            <div className={"slide clone".concat(animate ? "" : " stop")}>
              {shops.map((text, i) => (
                <li key={i} className={i % 2 === 0 ? "big" : "small"}>
                  <div className="text">
                    <p>{text.text}</p>
                  </div>
                </li>
              ))}
            </div>
          </ul>
        </div>
      </div>
    </div>
  );
}
