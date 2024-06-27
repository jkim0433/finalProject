import React, { useState, useEffect } from 'react';
import axios from 'axios';
import GallerySecond from '../selleredit/GellarySecond';

const Gallery = ({ productId }) => {
  const [photos, setPhotos] = useState([]);

  useEffect(() => {
    const fetchProductImages = async () => {
      try {
        const response = await axios.get(`http://localhost:8081/api/uploadProdImg/${productId}`);
        if (Array.isArray(response.data)) {
          setPhotos(response.data);
        } else {
          console.error('Invalid data format from API');
        }
      } catch (error) {
        console.error('Error fetching product images:', error);
      }
    };

    fetchProductImages();
  }, [productId]);

import React from "react";
import image5 from "../img/image3.png";
import image6 from "../img/image4.png";
import image7 from "../img/image8.png";
import image8 from "../img/image6.png";
import image9 from "../img/image2.png";

const Gallery = () => {
  return (
    <div className="mb-5 max-w-4xl mx-auto grid grid-flow-col grid-rows-2 grid-cols-3 gap-6">
      <div>
        <img src={image5} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg hover:scale-110 transition-transform duration-300 ease-in-out" />
      </div>
      <div className="col-start-3">
        <img src={image6} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg hover:scale-110 transition-transform duration-300 ease-in-out"/>
      </div>
      <div>
        <img src={image7} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg hover:scale-110 transition-transform duration-300 ease-in-out" />
      </div>
      <div>
        <img src={image8} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg hover:scale-110 transition-transform duration-300 ease-in-out" />
      </div>
      <div className="row-start-1 col-start-2 col-span-2">
        <img src={image9} alt="" loading="lazy" className="w-full h-60 object-cover rounded-lg hover:scale-110 transition-transform duration-300 ease-in-out" />
      </div>
    </div>
  );
};

export default Gallery;
