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


// import { useState, useEffect } from "react";
// import "./ShopList.css";
// import { shops } from "../data/shop";
// import axios from "axios";

// export default function ShopList() {
//   const [seller, setSeller] = useState(null);
//   const [animate, setAnimate] = useState(true);
//   const onStop = () => setAnimate(false);
//   const onRun = () => setAnimate(true);

//   useEffect(() => {
//     const fetchSellerInfo = async () => {
//       try {
//         const response = await axios.get("/api/sellers"); // API 엔드포인트
//         if (response.data && response.data.length > 0) {
//           setSeller(response.data[0]); // 첫 번째 Seller 정보를 가져옴
//         }
//       } catch (error) {
//         console.error("Error fetching seller info:", error);
//       }
//     };

//     fetchSellerInfo();
//   }, []);

//   return (
//     <div className="shop_list">
//       <div className="wrapper">
//         <div className="slide_container">
//           <ul
//             className="slide_wrapper"
//             onMouseEnter={onStop}
//             onMouseLeave={onRun}
//           >
//             <div className={"slide original".concat(animate ? "" : " stop")}>
//               {seller && (
//                 <li className="big">
//                   <div className="text">
//                     <p>{seller.sellerNm}</p>
//                   </div>
//                 </li>
//               )}
//             </div>
//             <div className={"slide clone".concat(animate ? "" : " stop")}>
//               {seller && (
//                 <li className="big">
//                   <div className="text">
//                     <p>{seller.sellerNm}</p>
//                   </div>
//                 </li>
//               )}
//             </div>
//           </ul>
//         </div>
//       </div>
//     </div>
//   );
// }
