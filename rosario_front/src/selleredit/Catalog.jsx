import React, { useState, useEffect } from "react";
import "./Catalog.css";
import Gallery from "./Gallery";
import GallerySecond from "./GellarySecond";
import Modal from "./Modal";

const Catalog = () => {
  const [selectedCard, setSelectedCard] = useState(null);
  const [photos, setPhotos] = useState([]);
  const [isAccordionOpen, setIsAccordionOpen] = useState(false);
  const [error, setError] = useState("");
  const [imageUris, setImageUris] = useState([]);
  const [sellerId, setSellerId] = useState("");
  const [loadedImages, setLoadedImages] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {
    const storedSellerId = localStorage.getItem("sellerId");
    console.log("First SellerId", storedSellerId);

    if (!storedSellerId) {
      setError("Seller ID not found in localStorage. Please log in.");
      return;
    }
    setSellerId(storedSellerId);
    fetchSellerImages(storedSellerId);
  }, []);

  const fetchSellerImages = async (sellerId) => {
    try {
      const response = await fetch(
        `http://localhost:8081/api/sellers/${sellerId}/images`
      );
      if (!response.ok) {
        throw new Error("Failed to fetch images.");
      }
      const data = await response.json();
      setLoadedImages(data || []);
      console.log("기존 등록된 카탈로그", data);
    } catch (error) {
      console.error("Error fetching images:", error);
      setError("Failed to fetch images.");
    }
  };

  const handleCardSelect = (cardType) => {
    setSelectedCard(cardType);
    setPhotos([]);
    setImageUris([]);
    setIsAccordionOpen(true);
  };

  const handleFileChange = (e) => {
    const files = Array.from(e.target.files)
      .slice(0, 5 - photos.length)
      .map((file) => URL.createObjectURL(file));
    setPhotos((prevPhotos) => [...prevPhotos, ...files]);
    Array.from(e.target.files)
      .slice(0, 5 - imageUris.length)
      .forEach((file) => {
        uploadFile(file);
      });
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = async (e) => {
    e.preventDefault();
    if (photos.length >= 5) {
      setError("You can only upload up to 5 images.");
      return;
    }
    const file = e.dataTransfer.files[0];
    const fileUrl = URL.createObjectURL(file);
    setPhotos((prevPhotos) => [...prevPhotos, fileUrl]);
    uploadFile(file);
  };

  const uploadFile = async (file) => {
    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await fetch(
        "http://localhost:8081/api/uploadSellerImg/test",
        {
          method: "POST",
          body: formData,
        }
      );

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Something went wrong");
      }

      const data = await response.json();
      setImageUris((prevUris) => [...prevUris, data.fileUri]);
      setError("");
      console.log("Image uploaded. URL:", data.fileUri);
    } catch (error) {
      console.error("Error:", error);
      setError(error.message);
    }
  };

  const handleStickTogether = async () => {
    const sellerId = localStorage.getItem("sellerId");

    if (!sellerId) {
      setError("Seller ID not found. Please log in.");
      return;
    }

    if (imageUris.length < 5) {
      setError("Please upload 5 images.");
      return;
    }

    try {
      let method = "POST"; // 기본적으로 POST 요청으로 설정

      if (loadedImages.length > 0) {
        method = "PATCH"; // loadedImages가 있으면 PATCH 요청으로 설정
      }

      const responses = await Promise.all(
        imageUris.slice(0, 5).map((imageUri, index) => {
          // 최대 5개의 이미지만 처리
          const filename = `sellerImg${index + 1}.jpg`; // sellerImg1부터 시작하도록 설정

          const endpoint = `http://localhost:8081/api/uploadSellerImg/s/${sellerId}`;

          return fetch(endpoint, {
            method,
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              filename,
              fileUri: imageUri,
            }),
          });
        })
      );

      for (const response of responses) {
        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.message || "Something went wrong");
        }

        const data = await response.json();
        console.log("Response data:", data);
      }

      setError("");
      setSuccessMessage(
        method === "PATCH"
          ? "Images updated successfully!"
          : "Images saved successfully!"
      );
    } catch (error) {
      console.error("Error saving file URIs:", error);
      setError(error.message);
    }
  };

  const handleDeletePhoto = (index) => {
    setPhotos((prevPhotos) => prevPhotos.filter((_, i) => i !== index));
    setImageUris((prevUris) => prevUris.filter((_, i) => i !== index));
  };

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const confirmModal = () => {
    setIsModalOpen(false);
    handleStickTogether();
  };

  return (
    <div className="Catalog">
      <h1>
        registered Catalog - {loadedImages.length > 0 ? sellerId : "Empty"}
      </h1>
      {loadedImages.length > 0 && (
        <div className="card-display">
          <h2>Loaded Images</h2>
          <GallerySecond photos={loadedImages} />
        </div>
      )}
      <h1>Photo Card App</h1>

      <div className="card-selector">
        <h2>Select Card Type</h2>
        <div className="card-options">
          <div
            className={`card-outline ${
              selectedCard === "type1" ? "selected" : ""
            }`}
            onClick={() => handleCardSelect("type1")}
          >
            Type 1
          </div>
          <div
            className={`card-outline ${
              selectedCard === "type2" ? "selected" : ""
            }`}
            onClick={() => handleCardSelect("type2")}
          >
            Type 2
          </div>
          <div
            className={`card-outline ${
              selectedCard === "type3" ? "selected" : ""
            }`}
            onClick={() => handleCardSelect("type3")}
          >
            Type 3
          </div>
        </div>
      </div>

      {selectedCard && (
        <div className="accordion">
          <button
            className="accordion-button"
            onClick={() => setIsAccordionOpen(!isAccordionOpen)}
          >
            {isAccordionOpen ? "Hide Upload Section" : "Show Upload Section"}
          </button>
          {isAccordionOpen && (
            <div
              className="upload-area"
              onDragOver={handleDragOver}
              onDrop={handleDrop}
            >
              <p>
                Drag & Drop an image file here or click the button to upload.
              </p>
              <input
                type="file"
                accept="image/*"
                onChange={handleFileChange}
                hidden
                id="file-upload"
              />
              <label htmlFor="file-upload" className="upload-button">
                Upload
              </label>
              {photos.length > 0 && (
                <div className="preview">
                  <h3>Preview:</h3>
                  <Gallery photos={photos} onDelete={handleDeletePhoto} />
                </div>
              )}
            </div>
          )}
        </div>
      )}

      {photos.length > 0 && (
        <div className={`card-display ${selectedCard}`}>
          <h2>Card Display - {selectedCard}</h2>
          <Gallery photos={photos} onDelete={handleDeletePhoto} />
        </div>
      )}

      <button onClick={openModal}>Save Images</button>

      <Modal isOpen={isModalOpen} onClose={closeModal} onConfirm={confirmModal}>
        <h2>Card Display - {selectedCard}</h2>
        <Gallery photos={photos} onDelete={handleDeletePhoto} />
      </Modal>

      {error && <p className="error">{error}</p>}
      {successMessage && <p className="success">{successMessage}</p>}
    </div>
  );
};

export default Catalog;
