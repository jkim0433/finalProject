import { useState } from "react";
import "./ShopList.css";

const texts = [
    { text: "블래스가든", target: "#" },
    { text: "라마라마", target: "#" },
    { text: "플라워베리", target: "#" },
    { text: "보떼봉떼", target: "#" },
    { text: "격물 공부", target: "#" },
    { text: "니콜라이 버그만", target: "#" },
    { text: "지스팀", target: "#" },
];

export default function ShopList() {
    const [animate, setAnimate] = useState(true);
    const onStop = () => setAnimate(false);
    const onRun = () => setAnimate(true);

    return (
        <div className="wrapper">
            <div className="slide_container">
                <ul
                    className="slide_wrapper"
                    onMouseEnter={onStop}
                    onMouseLeave={onRun}
                >
                    <div
                        className={"slide original".concat(
                            animate ? "" : " stop"
                        )}
                    >
                        {texts.map((text, i) => (
                            <li
                                key={i}
                                className={i % 2 === 0 ? "big" : "small"}
                            >
                                <div className="text">
                                    <p>{text.text}</p>
                                </div>
                            </li>
                        ))}
                    </div>
                    <div
                        className={"slide clone".concat(animate ? "" : " stop")}
                    >
                        {texts.map((text, i) => (
                            <li
                                key={i}
                                className={i % 2 === 0 ? "big" : "small"}
                            >
                                <div className="text">
                                    <p>{text.text}</p>
                                </div>
                            </li>
                        ))}
                    </div>
                </ul>
            </div>
        </div>
    );
}
