import React from "react";
import { AiFillGithub, AiFillLinkedin, AiOutlineMail } from "react-icons/ai";

const Footer = () => {
  return (
    <footer className="mt-2 bg-white text-gray-900 py-4">
      <div className="container mx-auto px-4 flex items-center justify-between">
        <div className="text-sm ">
          CopyRight &copy; 2024 Rosario, All rights reserved.
        </div>
        <div className="flex space-x-4">
          <a
            href="#"
            className="text-neutral-700 hover:text-gray-200 transition duration-300"
          >
            <AiFillGithub className="w-6 h-6" />
          </a>
          <a
            href="#"
            className="text-neutral-700 hover:text-gray-200 transition duration-300"
          >
            <AiFillLinkedin className="w-6 h-6" />
          </a>
          <button
            type="button"
            className="text-neutral-700 hover:text-gray-200 transition duration-300"
          >
            <AiOutlineMail className="w-6 h-6" />
          </button>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
