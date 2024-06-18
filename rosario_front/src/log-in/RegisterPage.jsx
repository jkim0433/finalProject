import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function RegisterPage() {
  const [customer, setCustomer] = useState({
    customerNm: "",
    customerBirthDt: "",
    customerCno: "",
    customerAdr: "",
    customerEmlAdr: "",
    customerPassword: "",
  });
  
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCustomer((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleRegister = async (event) => {
    event.preventDefault();
    try {
      await axios.post("/api/customers/register", customer);
      // 회원 가입 성공 후 로그인 페이지로 이동
      navigate("/login");
    } catch (error) {
      console.error("회원 가입 오류:", error);
    }
  };

  return (
    <div>
      <h2>회원 가입</h2>
      <form onSubmit={handleRegister}>
        <div>
          <label htmlFor="customerNm">이름:</label>
          <input
            type="text"
            id="customerNm"
            name="customerNm"
            value={customer.customerNm}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="customerBirthDt">생년월일:</label>
          <input
            type="date"
            id="customerBirthDt"
            name="customerBirthDt"
            value={customer.customerBirthDt}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="customerCno">연락처:</label>
          <input
            type="text"
            id="customerCno"
            name="customerCno"
            value={customer.customerCno}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="customerAdr">주소:</label>
          <input
            type="text"
            id="customerAdr"
            name="customerAdr"
            value={customer.customerAdr}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="customerEmlAdr">이메일:</label>
          <input
            type="email"
            id="customerEmlAdr"
            name="customerEmlAdr"
            value={customer.customerEmlAdr}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="customerPassword">비밀번호:</label>
          <input
            type="password"
            id="customerPassword"
            name="customerPassword"
            value={customer.customerPassword}
            onChange={handleChange}
          />
        </div>
        <button type="submit">회원 가입</button>
      </form>
    </div>
  );
}

export default RegisterPage;
