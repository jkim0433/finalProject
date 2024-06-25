import React from 'react';
import image5 from "../img/image3.png";
import image6 from "../img/image4.png";
import image7 from "../img/image8.png";
import image8 from "../img/image6.png";
import image9 from "../img/image2.png";

const Gallery = () => {
  return (
    <div className="max-w-4xl mx-auto grid grid-flow-col grid-rows-2 grid-cols-3 gap-4">
      <div>
        <img src={image5} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg" />
      </div>
      <div className="col-start-3">
        <img src={image6} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg"/>
      </div>
      <div>
        <img src={image7} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg" />
      </div>
      <div>
        <img src={image8} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg" />
      </div>
      <div className="row-start-1 col-start-2 col-span-2">
        <img src={image9} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg" />
      </div>
    </div>
  );
};

export default Gallery;
