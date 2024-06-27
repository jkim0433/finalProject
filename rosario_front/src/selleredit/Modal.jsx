import React from "react";
import "./Modal.css";

const Modal = ({ isOpen, onClose, onConfirm, children }) => {
  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        {children}
        <div className="modal-actions">
          <button onClick={onClose}>취소</button>
          <button onClick={onConfirm}>확인</button>
        </div>
      </div>
    </div>
  );
};

export default Modal;
