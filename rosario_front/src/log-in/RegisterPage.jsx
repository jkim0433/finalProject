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
        setEmailCheckMessage(
          "이미 사용 중인 이메일입니다. 다른 이메일을 입력해주세요."
        );
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
    // <div className="h-screen items-center flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
    //   <div className="sm:mx-auto sm:w-full sm:max-w-lg">
    //     <form
    //       onSubmit={handleRegister}
    //       className="space-y-6 shadow-sm w-50 p-10 box-content rounded-lg bg-orange-50"
    //     >
    //       <div>
    //         <label
    //           htmlFor="customerNm"
    //           className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
    //         >
    //           이름:
    //         </label>
    //         <input
    //           type="text"
    //           id="customerNm"
    //           name="customerNm"
    //           value={customer.customerNm}
    //           onChange={handleChange}
    //           className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 -indigo-600 sm:text-sm sm:leading-6"
    //         />
    //       </div>
    //       <div>
    //         <label
    //           htmlFor="customerEmlAdr"
    //           className="block text-sm font-medium leading-6 text-neutral-900"
    //         >
    //           이메일:
    //         </label>
    //         <input
    //           type="email"
    //           id="customerEmlAdr"
    //           name="customerEmlAdr"
    //           value={customer.customerEmlAdr}
    //           onChange={handleChange}
    //           className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
    //         />
    //         <span className="text-xs font-semibold">
    //           <button
    //             type="button"
    //             onClick={handleEmailCheck}
    //             className="mt-2 w-full flex rounded-lg text-orange-600 justify-end"
    //           >
    //             중복확인
    //           </button>
    //           <p className="mt-1 text-sm text-gray-500">{emailCheckMessage}</p>
    //         </span>
    //       </div>
    //       <div className="items-center justify-between">
    //         <label
    //           htmlFor="customerBirthDt"
    //           className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
    //         >
    //           생년월일:
    //         </label>
    //         <input
    //           type="date"
    //           id="customerBirthDt"
    //           name="customerBirthDt"
    //           value={customer.customerBirthDt}
    //           onChange={handleChange}
    //           className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
    //         />
    //       </div>
    //       <div>
    //         <label
    //           htmlFor="customerCno"
    //           className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
    //         >
    //           연락처:
    //         </label>
    //         <input
    //           type="text"
    //           id="customerCno"
    //           name="customerCno"
    //           value={customer.customerCno}
    //           onChange={handleChange}
    //           className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
    //         />
    //       </div>
    //       <div>
    //         <label
    //           htmlFor="customerAdr"
    //           className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
    //         >
    //           주소:
    //         </label>
    //         <input
    //           type="text"
    //           id="customerAdr"
    //           name="customerAdr"
    //           value={customer.customerAdr}
    //           onChange={handleChange}
    //           className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
    //         />
    //       </div>
    //       <div>
    //         <label
    //           htmlFor="customerPassword"
    //           className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
    //         >
    //           비밀번호:
    //         </label>
    //         <input
    //           type="password"
    //           id="customerPassword"
    //           name="customerPassword"
    //           value={customer.customerPassword}
    //           onChange={handleChange}
    //           className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
    //         />
    //       </div>
    //       <button
    //         type="submit"
    //         className="py-2 bg-red-600 flex w-full justify-center rounded-full px-3 py-1.5 text-bold text-orange-100 font-semibold"
    //       >
    //         회원가입
    //       </button>
    //     </form>
    //   </div>
    // </div>

      <form className="mt-20 mx-10" onSubmit={handleRegister}>
        <div className="border-b border-gray-900/10 pb-12 mx-auto max-w-2xl">
          <h2 className="text-base font-semibold leading-7 text-gray-900">
            회원가입
          </h2>
          {/* <p className="mt-1 text-sm leading-6 text-gray-600">
            Use a permanent address where you can receive mail.
          </p> */}

          <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
            
          <div className="sm:col-span-3">
              <label
                htmlFor="customerEmlAdr"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                이메일
              </label>
              <div className="mt-2">
                <input
                  type="email"
                  name="customerEmlAdr"
                  id="customerEmlAdr"
                  value={customer.customerEmlAdr}
                  onChange={handleChange}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
             <span className="text-xs font-semibold">
                <button
                  type="button"
                  onClick={handleEmailCheck}
                  className="mt-2 w-full flex rounded-lg text-orange-600 justify-end"
                >
                  중복확인
                </button>
                <p className="mt-1 text-sm text-gray-500">
                  {emailCheckMessage}
                </p>
              </span>
            </div>

            <div className="sm:col-span-3">
              <label
                htmlFor="customerNm"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                이름
              </label>
              <div className="mt-2">
                <input
                  type="text"
                  name="customerNm"
                  id="customerNm"
                  autoComplete="given-name"
                  value={customer.customerNm}
                  onChange={handleChange}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            


            <div className="sm:col-span-3">
              <label
                htmlFor="customerPassword"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                비밀번호
              </label>
              <div className="mt-2">
                <input
                  type="password"
                  name="customerPassword"
                  id="customerPassword"
                  autoComplete="given-name"
                  value={customer.customerPassword}
                  onChange={handleChange}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div className="sm:col-span-3">
              <label
                htmlFor="customerBirthDt"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                생년월일
              </label>
              <div className="mt-2">
                <input
                  type="date"
                  name="customerBirthDt"
                  id="customerBirthDt"
                  autoComplete="birthday"
                  value={customer.customerBirthDt}
                  onChange={handleChange}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div className="sm:col-span-3">
              <label
                htmlFor="customerCno"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                연락처
              </label>
              <div className="mt-2">
                <input
                  id="text"
                  name="customerCno"
                  type="customerCno"
                  autoComplete="telephone"
                  value={customer.customerCno}
                  onChange={handleChange}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
             
            </div>

            {/* <div className="sm:col-span-3">
        <label
          htmlFor="country"
          className="block text-sm font-medium leading-6 text-gray-900"
        >
          Country
        </label>
        <div className="mt-2">
          <select
            id="country"
            name="country"
            autoComplete="country-name"
            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6"
          >
            <option>서울시</option>
            <option>경기도</option>
            <option>세종시</option>
          </select>
        </div>
      </div> */}

            <div className="col-span-full">
              <label
                htmlFor="customerAdr"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                주소
              </label>
              <div className="mt-2">
                <input
                  type="text"
                  name="customerAdr"
                  id="customerAdr"
                  autoComplete="address"
                  value={customer.customerAdr}
                  onChange={handleChange}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <button
              type="submit"
              className="py-2 bg-red-600 flex w-full justify-center rounded-full px-3 py-1.5 text-bold text-orange-100 font-semibold"
            >
              OK
            </button>

            {/* <div className="sm:col-span-2 sm:col-start-1">
        <label
          htmlFor="city"
          className="block text-sm font-medium leading-6 text-gray-900"
        >
          City
        </label>
        <div className="mt-2">
          <input
            type="text"
            name="city"
            id="city"
            autoComplete="address-level2"
            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>

      <div className="sm:col-span-2">
        <label
          htmlFor="region"
          className="block text-sm font-medium leading-6 text-gray-900"
        >
          State / Province
        </label>
        <div className="mt-2">
          <input
            type="text"
            name="region"
            id="region"
            autoComplete="address-level1"
            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div>

      <div className="sm:col-span-2">
        <label
          htmlFor="postal-code"
          className="block text-sm font-medium leading-6 text-gray-900"
        >
          ZIP / Postal code
        </label>
        <div className="mt-2">
          <input
            type="text"
            name="postal-code"
            id="postal-code"
            autoComplete="postal-code"
            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
          />
        </div>
      </div> */}
          </div>
        </div>
      </form>

  );
}

export default RegisterPage;
