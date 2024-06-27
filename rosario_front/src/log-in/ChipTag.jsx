import React, { useState, useEffect } from "react";
import axios from "axios";
import "./ChipTag.css"; // 스타일을 위한 CSS 파일을 import합니다.
import { useNavigate } from "react-router-dom";

const ChipTag = () => {
  const [chipData, setChipData] = useState([]); // Chip 데이터 상태
  const [inputValue, setInputValue] = useState(""); // 입력값 상태
  const [chipCartData, setChipCartData] = useState([]); // 선택된 Chip 데이터 상태
  const navigate = useNavigate(); // useNavigate 훅을 올바르게 호출
  // 컴포넌트가 마운트될 때 태그 데이터를 불러옵니다.
  useEffect(() => {
    const fetchTags = async () => {
      try {
        const response = await fetch("http://localhost:8081/api/chipTags");
        const data = await response.json();
        setChipData(data); // 불러온 태그 데이터를 상태에 설정합니다.
      } catch (error) {
        console.error("태그 데이터를 불러오는 중 오류 발생:", error);
      }
    };
    fetchTags(); // fetchTags 함수를 호출하여 데이터를 가져옵니다.
  }, []);
  // Chip 삭제 처리 함수
  const handleDelete = (chipToDelete) => async () => {
    try {
      // 선택된 Chip의 tagId를 이용해 API에서 해당 Chip을 삭제합니다.
      await fetch(
        `http://localhost:8081/api/chipTags/${chipToDelete.chipTagId}`,
        {
          method: "DELETE",
        }
      );
      // Chip 데이터 상태를 업데이트하여 삭제된 Chip을 제외하고 새로 설정합니다.
      setChipData((chips) =>
        chips.filter((chip) => chip.chipTagId !== chipToDelete.chipTagId)
      );
    } catch (error) {
      console.error("Chip 삭제 중 오류 발생:", error);
    }
  };
  // Chip 추가 처리 함수
  const handleAddChip = async () => {
    // 입력값이 공백이거나 이미 존재하는 Chip인 경우 추가하지 않습니다.
    if (
      inputValue.trim() === "" ||
      chipData.some((chip) => chip.label === inputValue.trim())
    ) {
      return;
    }
    try {
      // API를 통해 입력값을 새로운 Chip으로 추가합니다.
      const response = await fetch("http://localhost:8081/api/chipTags", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        // 입력값을 trim() 함수로 공백 제거 후 그대로 전송합니다.
        body: inputValue.trim(),
      });
      // 추가된 Chip 데이터를 응답에서 받아와 Chip 데이터 상태에 추가합니다.
      const newChip = await response.json();
      setChipData([...chipData, newChip]); // 새로운 Chip을 Chip 데이터 상태에 추가합니다.
      setInputValue(""); // 입력값을 초기화합니다.
    } catch (error) {
      console.error("새로운 Chip 추가 중 오류 발생:", error);
    }
  };
  // 입력값 변경 이벤트 처리 함수
  const handleInputChange = (event) => {
    setInputValue(event.target.value); // 입력값 상태를 변경합니다.
  };
  // Enter 키 입력 시 Chip 추가 처리 함수
  const handleKeyDown = (event) => {
    if (event.key === "Enter") {
      handleAddChip(); // handleAddChip 함수를 호출하여 Chip을 추가합니다.
      event.preventDefault(); // 기본 동작을 중지합니다.
    }
  };
  // Chip 클릭 처리 함수
  const handleClick = (chip) => {
    console.log(`You clicked ${chip.label}, ${chip.chipTagId} Chip.`, chip); // Chip 정보를 콘솔에 출력
    // 이미 선택된 Chip인 경우 추가하지 않습니다.
    if (chipCartData.some((cartChip) => cartChip.chipTagId === chip.chipTagId))
      return;
    setChipCartData([...chipCartData, chip]); // 선택된 Chip을 Chip Cart 데이터 상태에 추가합니다.
  };
  // Chip Cart에서 Chip 삭제 처리 함수
  const handleDeleteCart = (chipToDelete) => () => {
    // Chip Cart 데이터 상태를 업데이트하여 삭제된 Chip을 제외하고 설정합니다.
    setChipCartData((chips) =>
      chips.filter((chip) => chip.chipTagId !== chipToDelete.chipTagId)
    );
  };
  // Chip Cart의 Chip을 확정하고 고객 개인취향을 추가시킴
  const handleSendChip = async () => {
    try {
      const customerId = localStorage.getItem("customerId"); // localStorage에서 customerId 가져오기
      // chipCartData에서 chipTagId 값만 추출하여 서버가 기대하는 형식으로 변환합니다.
      const chipTagIds = chipCartData.map((chip) => chip.chipTagId);
      const response = await axios.post(
        `http://localhost:8081/api/customers/${customerId}/chipTags`,
        chipTagIds, // 변환된 데이터를 전송
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log("Chip Cart 데이터 전송 성공:", response.data);
      setChipCartData([]); // Chip Cart 데이터를 초기화합니다.
      navigate("/"); // 데이터를 전송한 후 이동할 경로
    } catch (error) {
      console.error("Chip Cart 데이터 전송 중 오류 발생:", error);
    }
  };
  return (
    <>
      <div className="form-control">
        <input
          className="text-field"
          type="text"
          placeholder="메모태그"
          value={inputValue}
          onChange={handleInputChange}
          onKeyDown={handleKeyDown}
        />
      </div>
      <div className="chip-list">
        {chipData.map((data) => {
          return (
            <div key={data.chipTagId} className="list-item">
              <div className="chip" onClick={() => handleClick(data)}>
                {data.label}
              </div>
              {/* "Add_the_Chip" 태그인 경우 삭제 아이콘을 표시하지 않습니다. */}
              {data.label !== "Add_the_Chip" && (
                <div className="delete-icon" onClick={handleDelete(data)}>
                  &#x2715;
                </div>
              )}
            </div>
          );
        })}
      </div>
      <div className="chip-cart">
        <h1>Tag Cart</h1>
        {/* Chip Cart 데이터가 존재할 경우 해당 Chip을 표시합니다. */}
        {chipCartData.length > 0 &&
          chipCartData.map((cartData) => {
            return (
              <div key={cartData.chipTagId} className="list-item">
                <div className="chip cart-chip">{cartData.label}</div>
                {/* Chip 삭제 아이콘을 클릭하면 해당 Chip을 Chip Cart에서 삭제합니다. */}
                <div
                  className="delete-icon"
                  onClick={handleDeleteCart(cartData)}
                >
                  &#x2715;
                </div>
              </div>
            );
          })}
        {/* Chip Cart를 확정하는 버튼 */}
        <button className="confirm-button" onClick={handleSendChip}>
          확정
        </button>
      </div>
    </>
  );
};
export default ChipTag;