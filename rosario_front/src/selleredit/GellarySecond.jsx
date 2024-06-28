import React from "react";

const GallerySecond = ({ photos }) => {

  return (
    <div className="max-w-4xl mx-auto grid grid-flow-col grid-rows-2 grid-cols-3 gap-4">
      <div className="position-a border-dashed border-red-500 border-2 relative">
        {photos[0] ? (
          <>
            <img
              src={photos[0]}
              alt="uploaded-1"
              loading="lazy"
              className="w-full h-60 object-cover rounded-lg"
            />
          </>
        ) : (
          <p className="text-center w-full">Empty</p>
        )}
      </div>
      <div className="col-start-3 position-b border-dashed border-red-500 border-2 relative">
        {photos[1] ? (
          <>
            <img
              src={photos[1]}
              alt="uploaded-2"
              loading="lazy"
              className="w-full h-60 object-cover rounded-lg"
            />
          </>
        ) : (
          <p className="text-center w-full">Empty</p>
        )}
      </div>
      <div className="position-c border-dashed border-red-500 border-2 relative">
        {photos[2] ? (
          <>
            <img
              src={photos[2]}
              alt="uploaded-3"
              loading="lazy"
              className="w-full h-60 object-cover rounded-lg"
            />
          </>
        ) : (
          <p className="text-center w-full">Empty</p>
        )}
      </div>
      <div className="position-d border-dashed border-red-500 border-2 relative">
        {photos[3] ? (
          <>
            <img
              src={photos[3]}
              alt="uploaded-4"
              loading="lazy"
              className="w-full h-60 object-cover rounded-lg"
            />
          </>
        ) : (
          <p className="text-center w-full">Empty</p>
        )}
      </div>
      <div className="row-start-1 col-start-2 col-span-2 position-e border-dashed border-red-500 border-2 relative">
        {photos[4] ? (
          <>
            <img
              src={photos[4]}
              alt="uploaded-5"
              loading="lazy"
              className="w-full h-60 object-cover rounded-lg"
            />
          </>
        ) : (
          <p className="text-center w-full">Empty</p>
        )}
      </div>
    </div>
  );
};

export default GallerySecond;