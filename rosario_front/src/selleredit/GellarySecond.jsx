import React from "react";

const GallerySecond = ({ photos }) => {
  return (
    <div className="max-w-4xl mx-auto grid grid-flow-col grid-rows-2 grid-cols-3 gap-4">
      {photos.map((photo, index) => (
        <div key={index} className={`position-${String.fromCharCode(97 + index)} border-dashed border-red-500 border-2 relative`}>
          {photo ? (
            <img
              src={photo}
              alt={`uploaded-${index + 1}`}
              loading="lazy"
              className="w-full h-60 object-cover rounded-lg"
            />
          ) : (
            <p className="text-center w-full">Empty</p>
          )}
        </div>
      ))}
    </div>
  );
};

export default GallerySecond;
