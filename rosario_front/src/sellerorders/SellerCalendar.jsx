import React, { useState, useEffect } from "react";
import { Calendar, ConfigProvider, Badge } from "antd";
import { MdArrowBackIos, MdArrowForwardIos } from "react-icons/md";
import moment from "moment";
import "moment/locale/ko";
import styles from "./SellerCalendar.module.css";
import axios from "axios";

// 일정 데이터 가져오기
const getDefaultListData = (value) => {
  let listData = [];
  switch (value.date()) {
    case 6:
      listData = [
        {
          type: "warning",
          content: "This is warning event.",
        },
        {
          type: "success",
          content: "This is usual event.",
        },
      ];
      break;
    case 10:
      listData = [
        {
          type: "warning",
          content: "This is warning event.",
        },
        {
          type: "success",
          content: "This is usual event.",
        },
        {
          type: "error",
          content: "This is error event.",
        },
      ];
      break;
    case 15:
      listData = [
        {
          type: "warning",
          content: "This is warning event",
        },
        {
          type: "success",
          content: "This is very long usual event......",
        },
        {
          type: "error",
          content: "This is error event 1.",
        },
        {
          type: "error",
          content: "This is error event 2.",
        },
        {
          type: "error",
          content: "This is error event 3.",
        },
        {
          type: "error",
          content: "This is error event 4.",
        },
      ];
      break;
    default:
  }
  return listData || [];
};

// 캘린더 헤더 렌더링
const headerRender = (props) => {
  const { value, onChange } = props;

  const localeValue = value.clone();
  const month = localeValue.month() + 1;
  const year = localeValue.year();

  const handlePrevMonth = () => {
    const newDate = value.clone().subtract(1, "month");
    onChange(newDate);
  };

  const handleNextMonth = () => {
    const newDate = value.clone().add(1, "month");
    onChange(newDate);
  };

  return (
    <div
      style={{
        padding: 10,
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        color: "#A61717",
      }}
    >
      <MdArrowBackIos
        onClick={handlePrevMonth}
        size={20}
        style={{ cursor: "pointer", marginRight: 10 }}
      />
      <span style={{ fontSize: 25, fontWeight: "bold" }}>
        {month}월 {year}년
      </span>
      <MdArrowForwardIos
        onClick={handleNextMonth}
        size={20}
        style={{ cursor: "pointer", marginLeft: 10 }}
      />
    </div>
  );
};

// 캘린더 컴포넌트
const SellerCalendar = () => {
  const today = moment(); // 오늘 날짜를 가져옵니다.
  const [selectedDate, setSelectedDate] = useState(today); // 선택된 날짜 상태를 관리합니다.
  const [holidays, setHolidays] = useState([]); //공휴일 관리

  //공휴일 데이터 가져오기
  useEffect(() => {
    fetchHolidays();
  }, []);

  // 공휴일 데이터를 가져오는 함수
  const fetchHolidays = async () => {
    try {
      //공휴일 API호출
      const response = await fetch(
        `https://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo?solYear=2024&_type=json&numOfRows=20&ServiceKey=n4waYA%2FSK%2BYe%2FFeJPCcFifN4DUeJb2TC9nUmL0xyd8jJJxPzh5qHz985Qd80572y8Zufeuyih3keeHmEaFFPEQ%3D%3D`
      );
      const data = await response.json();

      //API에서 받은 공휴일데이터 state에 저장
      if (data.response.header.resultCode === "00") {
        setHolidays(data.response.body.items.item);
      } else {
        console.error("Failed to fetch holidays");
      }
    } catch (error) {
      console.error("Failed fetching holidays:", error);
    }
  };

  const getListData = (value) => {
    const dateString = value.format("YYYYMMDD");
    const filteredHolidays = holidays.filter(
      (holiday) => holiday.locdate === parseInt(dateString)
    );

    const holidayEvents = filteredHolidays.map((holiday) => ({
      type: "holiday",
      content: holiday.dateName,
    }));
    const defaultEvents = getDefaultListData(value);

    return [...defaultEvents, ...holidayEvents];

    // return filteredHolidays.map((holiday) => ({
    //   type: "holiday",
    //   content: holiday.dateName,
    // }));
  };

  // 날짜 변경 이벤트 핸들러
  const onDateChange = (newDate) => {
    setSelectedDate(newDate);
  };

  // 날짜 칸 렌더링
  const fullCellRender = (value) => {
    const listData = getListData(value);
    const isToday = value.isSame(today, "day");
    const isSelected = value.isSame(selectedDate, "day");
    const isCurrentMonth = value.month() === selectedDate.month();

    let cellClass = `${styles.customDateCell}`;
    if (isToday) cellClass += ` ${styles.todayCell}`;
    if (isSelected) cellClass += ` ${styles.selectedCell}`;

    //공휴일필터링
    const holidayEvents = listData.filter((item) => item.type === "holiday");
    const isHoliday = holidayEvents.length > 0;

    //토일 글자색 지정
    const textColor = isHoliday
      ? "red"
      : value.day() === 0
      ? "red"
      : value.day() === 6
      ? "blue"
      : "#333333";

    const handleCellClick = () => {
      if (!isSelected) {
        setSelectedDate(value);
        console.log("New date:", value.format("YYYY-MM-DD"));
      }
    };

    return isCurrentMonth ? (
      <div
        className={cellClass}
        onClick={handleCellClick}
        styles={{ position: "relative" }}
      >
        <div
          className={styles.customDateNumber}
          style={{ color: textColor, cursor: "pointer" }}
        >
          {value.date()}
        </div>
        <ul className={styles.events}>
          {listData.map((item, index) => (
            <li key={`${item.content}-${index}`}>
              <Badge status={item.type} text={item.content} />
            </li>
          ))}
        </ul>
        {isHoliday && (
          <span className={styles.holidayLabel}>
            {holidayEvents.map((item) => item.content).join(", ")}
          </span>
        )}
      </div>
    ) : (
      <div style={{ display: "none" }} />
    );
  };

  // 요일변경
  const customHeaderRender = ({ value, onChange }) => {
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];

    return (
      <div className={styles.customWeekHeader}>
        {daysOfWeek.map((day, index) => {
          let dayClassName = ` ${styles.customWeekDay}`;
          if (index === 0) dayClassName += ` ${styles.sun}`;
          if (index === 6) dayClassName += ` ${styles.sat}`;

          return (
            <span key={index} className={dayClassName}>
              {day}
            </span>
          );
        })}
      </div>
    );
  };

  return (
    <ConfigProvider
      theme={{
        components: {
          Calendar: {
            itemActiveBg: "rgb(242,148,148, 0.5)",
          },
        },
        token: {
          colorPrimary: "#A61717",
          controlItemBgHover: "rgb(217,210,208, 0.5)",
          colorTextDisabled: "#ffffff",
          colorSplit: "rgba(5, 5, 5, 0.06)",
        },
      }}
    >
      <Calendar
        mode="month"
        headerRender={(props) => (
          <>
            {headerRender(props)}
            {customHeaderRender(props)}
          </>
        )}
        fullCellRender={fullCellRender} // `fullCellRender`를 사용합니다.
        onSelect={onDateChange}
      />
    </ConfigProvider>
  );
};

export default SellerCalendar;
