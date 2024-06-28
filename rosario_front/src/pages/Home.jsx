import React from "react";
import "./Home.css";
import { Carousel } from "react-responsive-carousel";
import "react-responsive-carousel/lib/styles/carousel.min.css"; // 스타일 파일 임포트
import image1 from "../img/image1.png";
import image2 from "../img/image1.png";
import image3 from "../img/image1.png";
import image4 from "../img/image5.png";

import ShopList from "../shop/ShopList";
import Footer from "../components/Footer";
import Gallery from "../shop/Gallery";


const Home = () => {
  return (
    <div>
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

      <main className="py-6 px-4 sm:p-6 md:py-10 md:px-8">
        <div className="max-w-4xl mx-auto grid grid-cols-1 lg:max-w-4xl lg:gap-x-20 ">
          <div className="relative p-3 col-start-1 row-start-1 flex flex-col-reverse rounded-lg bg-gradient-to-t from-black/75 via-black/0 sm:bg-none sm:row-start-2 sm:p-0 lg:row-start-1">
            <h1 className="italic mt-1 text-lg font-semibold text-white sm:text-red-600 sm:hover:text-amber-200 md:text-2xl dark:sm:text-white hover:text-red-600">
              Welcome to our services
            </h1>
          </div>
          <div className="grid col-start-1 col-end-3 row-start-1 sm:mb-6 lg:gap-6 lg:col-start-2 lg:row-end-6 lg:row-span-6 lg:mb-0">
            <img
              src={image4}
              alt=""
              className="w-full h-60 object-cover rounded-lg sm:h-72 sm:col-span-2 lg:col-span-full hover:shadow-xl"
              loading="lazy"
            />
          </div>
          <div className="mt-4 w-100 align-end col-start-1 row-start-3 self-center sm:mt-0 sm:col-start-2 sm:row-start-2 sm:row-span-2 lg:col-start-1 lg:row-start-3 lg:row-end-4">
            <button
              type="button"
              className="hover:bg-amber-100 hover:text-red-600 mt-4 py-1 px-5 bg-red-600 flex w-md justify-center rounded-full px-3 py-1.5 text-extrabold text-amber-100"
            >
              Entrance
            </button>
          </div>
          <p className="mt-4 text-sm text-neutral-600 leading-6 col-start-1 sm:col-span-2 lg:mt-6 lg:row-start-4 lg:col-span-1 dark:text-slate-400">
            We are dedicated to delivering the beauty and emotion of flowers
            toyour doorstep. One-Time Orders: Easily order for special
            occasionsor to express your sentiments. Subscription Orders: Want to
            receive a new bouquet every month? Subscribe to our service and keep
            the special gifts coming. <br />
            <br />
            <span className="font-semibold">
              Experience the Extraordinary
            </span>{" "}
            <br />
            We strive to make your special moments even more exceptional.
            Experience beautiful bouquets and unforgettable moments with our
            service!{" "}
          </p>
        </div>

        <div className="gallery mt-20">
          <Gallery />
        </div>
      </main>
      <div className="shop_list">
        <ShopList />
      </div>
      <div className="footer">
        <Footer />
      </div>
    </div>
  );
};

export default Home;
