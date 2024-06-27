import React, { useState, useEffect } from "react";
import GallerySecond from "../selleredit/GellarySecond";

const Gallery = () => {
  const [photos, setPhotos] = useState([]);

  useEffect(() => {
    const fetchPhotos = async () => {
      try {
        const sellerId = 1; // 실제 사용할 sellerId로 변경
        const response = await fetch(`http://localhost:8081/api/sellers/${sellerId}/images`);
        if (!response.ok) {
          throw new Error("Failed to fetch photos");
        }
        const data = await response.json();
        console.log("API Response:", data); // 응답 데이터 확인
        setPhotos(data.map(img => `http://localhost:8081/api/sellers/${sellerId}/images${img.imageUrl}`));
      } catch (error) {
        console.error("Error fetching photos:", error);
      }
    };
  
    fetchPhotos();
  }, []);

import React from "react";
import image5 from "../img/image3.png";
import image6 from "../img/image4.png";
import image7 from "../img/image8.png";
import image8 from "../img/image6.png";
import image9 from "../img/image2.png";

const Gallery = () => {
  return (
    <div>
      <h2>Product Gallery</h2>
      <GallerySecond photos={photos.map(img => img.prodFilePath)} />
    </div>
  );
};

export default Gallery;
