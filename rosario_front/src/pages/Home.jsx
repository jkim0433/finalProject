import React from "react";
import "./Home.css";
import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css"; // 스타일 파일 임포트
import image1 from "../img/image1.png";
import image2 from "../img/image1.png";
import image3 from "../img/image1.png";

const Home = () => {
  return (
    <div className="carousel-container">
      <Carousel>
        <div className="relative">
          <img src={image1} alt="Image 1" />
          {/* <div className="absolute inset-0 flex justify-center items-center">
            <p className="rosario text-4xl lg:text-3xl xl:text-5xl sm:mt-10 md:mt-20 lg:mt-28 xl:mt-36">
              ROSARIO
            </p>
          </div> */}
        </div>
        <div className="relative">
          <img src={image2} alt="Image 2" />
        </div>
        <div className="relative">
          <img src={image3} alt="Image 3" />
        </div>
      </Carousel>
    </div>
  );
};

export default Home;
