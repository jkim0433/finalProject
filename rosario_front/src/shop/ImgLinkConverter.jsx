import React, { useState } from "react";

const ImgLinkConverter = ({ onUpload }) => {
  const [imageUrl, setImageUrl] = useState("");
  const [error, setError] = useState("");
  const [productId, setProductId] = useState("");
  const [product, setProduct] = useState(null);
  const [images, setImages] = useState([]);

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = async (e) => {
    e.preventDefault();
    const file = e.dataTransfer.files[0];
    uploadFile(file);
  };

  const uploadFile = async (file) => {
    const formData = new FormData();
    formData.append("file", file);
    try {
      const response = await fetch("http://localhost:8081/api/uploadProdImg/test", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Something went wrong");
      }
      const data = await response.json();
      setImageUrl(data.fileUri);
      if (onUpload) onUpload(data.fileUri);
      setError("");
      console.log("Image uploaded. URL:", data.fileUri);
    } catch (error) {
      console.error("Error:", error);
      setError(error.message);
    }
  };

  const handleStickTogether = async () => {
    if (!productId) {
      setError("상품 ID를 입력해주세요.");
      return;
    }
    if (!imageUrl) {
      setError("먼저 이미지를 업로드해주세요.");
      return;
    }
    try {
      const response = await fetch(`http://localhost:8081/api/uploadProdImg/p/${productId}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          productId: productId,
          filename: imageUrl.substring(imageUrl.lastIndexOf("/") + 1),
          fileUri: imageUrl,
        }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Something went wrong");
      }
      const data = await response.json();
      console.log("Response data:", data);
      setImages([data]);
      setError("");
    } catch (error) {
      console.error("Error saving file URI:", error);
      setError(error.message);
    }
  };

  const fetchProductDetails = async () => {
    if (!productId) {
      setError("상품 ID를 입력해주세요.");
      return;
    }
    try {
      const response = await fetch(`http://localhost:8081/api/products/${productId}`);
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Something went wrong");
      }
      const data = await response.json();
      setProduct(data);
      setImages(data.productImages || []);
      setError("");
    } catch (error) {
      console.error("Error fetching images:", error);
      setError(error.message);
    }
  };

  const handleProductIdChange = (e) => {
    setProductId(e.target.value);
  };

  return (
    <div className="p-4 text-center">
      <div
        onDragOver={handleDragOver}
        onDrop={handleDrop}
        className="border-2 border-dashed border-gray-300 rounded p-4 text-center mb-7"
      >
        <p className="mb-2">
          이미지 파일을 드래그하여 여기에 놓거나 업로드 버튼을 클릭하세요.
        </p>
        <label className="inline-block px-4 py-2 bg-blue-500 text-white rounded mr-2 cursor-pointer">
          업로드
          <input
            type="file"
            accept="image/*"
            onChange={(e) => uploadFile(e.target.files[0])}
            className="hidden"
          />
        </label>
        <button
          className={`inline-block px-4 py-2 bg-blue-500 text-white rounded ${!imageUrl ? 'opacity-50 cursor-not-allowed' : ''}`}
          onClick={handleStickTogether}
          disabled={!imageUrl}
        >
          함께 붙이기
        </button>
        {error && (
          <p className="text-red-500 mt-2">{error}</p>
        )}
        {imageUrl && (
          <div className="mt-2">
            <p className="text-sm">업로드된 이미지 URL:</p>
            <p className="text-sm">{imageUrl}</p>
          </div>
        )}
        <div className="mt-2">
          <p className="text-sm">상품 ID:</p>
          <input
            type="text"
            value={productId}
            onChange={handleProductIdChange}
            className="px-4 py-2 text-base border border-gray-300 rounded"
            style={{ width: "200px" }}
          />
        </div>
      </div>
      <div className="mt-4">
        <h2 className="text-xl mb-2">상품 ID로 이미지 가져오기</h2>
        <input
          type="text"
          value={productId}
          onChange={handleProductIdChange}
          className="border border-gray-300 rounded px-4 py-2 mb-2"
        />
        <button
          className="bg-blue-500 text-white px-4 py-2 rounded"
          onClick={fetchProductDetails}
        >
          상품 상세 정보 가져오기
        </button>
        {error && (
          <p className="text-red-500 mt-2">{error}</p>
        )}
        {product && (
          <div className="mt-2">
            <h2 className="text-xl">상품 상세 정보:</h2>
            <p className="text-sm text-neutral-900">이름: {product.productNm}</p>
            <p className="text-sm">가격: {product.productPrice}</p>
            <p className="text-sm">재고: {product.productStock}</p>
            <p className="text-sm">사이즈: {product.productSize}</p>
          </div>
        )}
        {images.length > 0 && (
          <div className="mt-2">
            <h2 className="text-xl">이미지 목록:</h2>
            <ul>
              {images.map((img) => (
                <li key={img.productImgId}>
                  <img
                    src={img.prodFilePath}
                    alt={img.prodFilename}
                    className="max-w-full mt-2"
                    style={{ maxHeight: "200px" }}
                  />
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
    </div>
  );
};

export default ImgLinkConverter;
