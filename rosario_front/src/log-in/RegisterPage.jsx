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

  const [emailCheckMessage, setEmailCheckMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCustomer((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleEmailCheck = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8081/api/customers/check-email?email=${customer.customerEmlAdr}`
      );
      if (response.data === true) {
        setEmailCheckMessage("이미 사용 중인 이메일입니다.");
      } else {
        setEmailCheckMessage("사용 가능한 이메일입니다.");
      }
    } catch (error) {
      console.error("이메일 중복 확인 오류:", error);
    }
  };

  const handleRegister = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8081/api/customers/check-email?email=${customer.customerEmlAdr}`
      );
      if (response.data === true) {
        setEmailCheckMessage("이미 사용 중인 이메일입니다. 다른 이메일을 입력해주세요.");
      } else {
        await axios.post(
          "http://localhost:8081/api/customers/register",
          customer
        );
        // 회원 가입 성공 후 로그인 페이지로 이동
        navigate("/loginpage");
      }
    } catch (error) {
      console.error("회원 가입 오류:", error);
    }
  };

  return (
    <div className="h-screen items-center flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-lg">
        <form
          onSubmit={handleRegister}
          className="space-y-6 shadow-sm w-50 p-10 box-content rounded-lg bg-orange-50"
        >
          <div>
            <label
              htmlFor="customerNm"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              이름:
            </label>
            <input
              type="text"
              id="customerNm"
              name="customerNm"
              value={customer.customerNm}
              onChange={handleChange}
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 -indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="customerEmlAdr"
              className="block text-sm font-medium leading-6 text-neutral-900"
            >
              이메일:
            </label>
            <input
              type="email"
              id="customerEmlAdr"
              name="customerEmlAdr"
              value={customer.customerEmlAdr}
              onChange={handleChange}
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
            <span className="text-xs font-semibold">
              <button
                type="button"
                onClick={handleEmailCheck}
                className="mt-2 w-full flex rounded-lg text-orange-600 justify-end"
              >
                중복확인
              </button>
              <p className="mt-1 text-sm text-gray-500">{emailCheckMessage}</p>
            </span>
          </div>
          <div className="items-center justify-between">
            <label
              htmlFor="customerBirthDt"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              생년월일:
            </label>
            <input
              type="date"
              id="customerBirthDt"
              name="customerBirthDt"
              value={customer.customerBirthDt}
              onChange={handleChange}
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="customerCno"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              연락처:
            </label>
            <input
              type="text"
              id="customerCno"
              name="customerCno"
              value={customer.customerCno}
              onChange={handleChange}
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="customerAdr"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              주소:
            </label>
            <input
              type="text"
              id="customerAdr"
              name="customerAdr"
              value={customer.customerAdr}
              onChange={handleChange}
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="customerPassword"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              비밀번호:
            </label>
            <input
              type="password"
              id="customerPassword"
              name="customerPassword"
              value={customer.customerPassword}
              onChange={handleChange}
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <button
            type="submit"
            className="py-2 bg-red-600 flex w-full justify-center rounded-full px-3 py-1.5 text-bold text-orange-100 font-semibold"
          >
            회원가입
          </button>
        </form>
      </div>
    </div>
  );
}

export default RegisterPage;
