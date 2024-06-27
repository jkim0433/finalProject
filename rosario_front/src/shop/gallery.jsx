import React, { useState, useEffect } from "react";
import axios from "axios";
import GallerySecond from "../selleredit/GellarySecond";

const Gallery = ({ productId }) => {
  const [photos, setPhotos] = useState([]);

  useEffect(() => {
    const fetchProductImages = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8081/api/uploadProdImg/${productId}`
        );
        if (Array.isArray(response.data)) {
          setPhotos(response.data);
        } else {
          console.error("Invalid data format from API");
        }
      } catch (error) {
        console.error("Error fetching product images:", error);
      }
    };

    fetchProductImages();
  }, [productId]);

  return (
    <div>
      <h2>Product Gallery</h2>
      <GallerySecond photos={photos.map((img) => img.prodFilePath)} />
    </div>
  );
};

export default Gallery;

// 수정확인
