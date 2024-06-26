// 일별매출액
import React, { useState, useEffect } from "react";
import axios from "axios";
import moment from "moment";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

const API_BASE_URL = "http://localhost:8081/rosario/admin/sales";

// 일별로 뽑아서 보이기
const DailyChartTerm = () => {
  // salesData: 일별 판매 데이터를 저장하기 위한 상태 변수.
  const [salesData, setSalesData] = useState([]);

  //컴포넌트가 마운트될 때 현재 연도와 월을 가져와 fetchDailySalesData 함수를 호출
  useEffect(() => {
    const today = moment();
    const year = today.year();
    const month = today.month() + 1; // moment.month()는 0부터 시작하므로 +1을 해줍니다.
    fetchDailySalesData(year, month);
  }, []);

  // 주어진 연도와 월에 해당하는
  // 모든 날짜에 대해 API 호출을 병렬로 수행.
  // 응답 데이터를 salesData 상태 변수에 저장
  const fetchDailySalesData = async (year, month) => {
    try {
      // 해당 월의 총 일 수를 계산합니다.
      const daysInMonth = moment(`${year}-${month}`, "YYYY-MM").daysInMonth();
      // 요청을 보낼 배열을 초기화합니다.
      const requests = [];

      // 월의 첫 날부터 마지막 날까지의 모든 일에 대해 요청을 생성합니다.
      for (let day = 1; day <= daysInMonth; day++) {
        // 각 일별 데이터에 대한 GET 요청을 생성하고 배열에 추가함
        requests.push(
          axios.get(`${API_BASE_URL}/term/daily/${year}/${month}/${day}`)
        );
      }

      // 모든 요청을 동시에 보내고 응답을 기다립니다.
      const responses = await Promise.all(requests);
      // 각 일별 응답 데이터에서 판매 데이터만 추출하여 배열로 변환합니다.
      const dailySales = responses.map((response) => response.data);

      // 추출된 판매 데이터를 상태에 저장합니다.
      setSalesData(dailySales);
    } catch (error) {
      // 오류가 발생하면 콘솔에 오류메시지 기록, 빈 배열로 상태 설정
      console.error("Error fetching monthly sales data:", error);
      setSalesData([]);
    }
  };

  // salesData를 차트에 표시할 수 있는 형식으로 변환.
  // 날짜와 판매 데이터를 포함하는 객체 배열로 변환.
  const formatChartData = () => {
    const startDate = moment().startOf("month");
    return salesData.map((dailySales, index) => ({
      date: startDate.clone().add(index, "days").format("D일"),
      sales: dailySales || 0,
    }));
  };

  return (
    <div className="daily-sales-chart">
      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={formatChartData()}>
          <XAxis dataKey="date" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="sales" stroke="#8884d8" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default DailyChartTerm;
