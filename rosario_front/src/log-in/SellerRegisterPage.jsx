import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function SellerRegisterPage() {
  const [seller, setSeller] = useState({
    sellerNm: "",
    sellerBirthDt: "",
    sellerCno: "",
    sellerAdr: "",
    sellerRgtDt: "",
    sellerEmailAdr: "",
    sellerPassword: "",
  });

  const [emailCheckMessage, setEmailCheckMessage] = useState("");
  const navigate = useNavigate();
  const handleSellerChange = (e) => {
    const { name, value } = e.target;
    setSeller((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleEmailCheck = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8081/api/sellers/check-email?email=${seller.sellerEmailAdr}`
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

  const handleSellerRegister = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.get(
        `http://localhost:8081/api/sellers/check-email?email=${seller.sellerEmailAdr}`
      );
      if (response.data === true) {
        setEmailCheckMessage(
          "이미 사용 중인 이메일입니다. 다른 이메일을 입력해주세요."
        );
      } else {
        await axios.post("http://localhost:8081/api/sellers/register", seller);
        console.log("회원 가입 성공:", response.data);
        // 회원 가입 성공 후 로그인 페이지로 이동
        navigate("/loginpage");
      }
    } catch (error) {
      if (error.response) {
        // 서버가 500 상태 코드를 반환했을 때 처리
        console.error("서버 오류로 인한 회원 가입 실패:", error.response.data);
        // 사용자에게 오류 메시지 표시 등 추가적인 처리 가능
      } else if (error.request) {
        // 요청이 만들어졌으나 응답을 받지 못했을 때 처리
        console.error("서버로부터 응답을 받지 못했습니다:", error.request);
      } else {
        // 요청을 설정하는 과정에서 오류가 발생했을 때 처리
        console.error("요청 설정 중 오류 발생:", error.message);
      }
    }
  };

  return (
    <div className="h-screen items-center flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-lg">
        <form
          onSubmit={handleSellerRegister}
          className="space-y-6 shadow-sm w-50 p-10 box-content rounded-lg bg-orange-50"
        >
          <div>
            <label
              htmlFor="sellerNm"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              상호명:
            </label>
            <input
              type="text"
              id="sellerNm"
              name="sellerNm"
              value={seller.sellerNm}
              onChange={handleSellerChange}
              autoComplete="current-password"
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 -indigo-600 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="sellerEmailAdr"
              className="block text-sm font-medium leading-6 text-neutral-900"
            >
              이메일:
            </label>
            <input
              type="email"
              id="sellerEmailAdr"
              name="sellerEmailAdr"
              value={seller.sellerEmailAdr}
              onChange={handleSellerChange}
              autoComplete="current-password"
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
              htmlFor="sellerRgtDt"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              사업자등록번호:
            </label>
            <input
              type="text"
              id="sellerRgtDt"
              name="sellerRgtDt"
              value={seller.sellerRgtDt}
              onChange={handleSellerChange}
              autoComplete="current-password"
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <div className="items-center justify-between">
            <label
              htmlFor="sellerBirthDt"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              생년월일:
            </label>
            <input
              type="date"
              id="sellerBirthDt"
              name="sellerBirthDt"
              value={seller.sellerBirthDt}
              onChange={handleSellerChange}
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="sellerCno"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              연락처:
            </label>
            <input
              type="text"
              id="sellerCno"
              name="sellerCno"
              value={seller.sellerCno}
              onChange={handleSellerChange}
              autoComplete="current-password"
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="sellerAdr"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              사업장주소:
            </label>
            <input
              type="text"
              id="sellerAdr"
              name="sellerAdr"
              value={seller.sellerAdr}
              onChange={handleSellerChange}
              autoComplete="current-password"
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <div>
            <label
              htmlFor="sellerPassword"
              className="mt-2 block text-sm font-medium leading-6 text-neutral-900"
            >
              비밀번호:
            </label>
            <input
              type="password"
              id="sellerPassword"
              name="sellerPassword"
              value={seller.sellerPassword}
              onChange={handleSellerChange}
              autoComplete="current-password"
              className="border block w-full rounded-full border-0 py-1.5 px-4 text-neutral-900 placeholder:text-gray-400 sm:text-sm sm:leading-6"
            />
          </div>
          <button
            type="submit"
            className="py-2 bg-red-600 flex w-full justify-center rounded-full px-3 py-1.5 text-bold text-orange-100 font-semibold"
          >
            업체 회원가입
          </button>
        </form>
      </div>
    </div>
  );
}

export default SellerRegisterPage;
