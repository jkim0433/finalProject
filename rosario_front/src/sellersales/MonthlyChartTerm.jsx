// 월별 매출액
import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import moment from "moment";

const API_BASE_URL = "http://localhost:8081/rosario/admin/sales";

const MonthlyChartTerm = () => {
  const [salesData, setSalesData] = useState([]);

  useEffect(() => {
    const today = moment(); //moment라이브러리를 사용해 현재 날짜와 시간 가져옴
    const year = today.year();
    // const month = today.month() + 1;
    fetchMonthlySalesChart(year);
  }, []);
  const fetchMonthlySalesChart = async (year) => {
    try {
      const requests = [];

      for (let month = 1; month <= 12; month++) {
        // 각 월별 데이터에 대한 GET 요청을 생성하고 배열에 추가함
        requests.push(
          axios.get(`${API_BASE_URL}/term/monthly/${year}/${month}`)
        );
      }

      // 모든 요청을 동시에 보내고 응답을 기다립니다.
      const responses = await Promise.all(requests);
      // 각 월별 응답 데이터에서 판매 데이터만 추출하여 배열로 변환합니다.
      const monthlySales = responses.map((response) => response.data);

      // 추출된 판매 데이터를 상태에 저장합니다
      setSalesData(monthlySales);
    } catch (error) {
      console.error("연간 매출 데이터를 가져오는 중 오류 발생:", error);
      setSalesData([]);
    }
  };

  // salesData를 차트에 표시할 수 있는 형식으로 변환.
  // 날짜와 판매 데이터를 포함하는 객체 배열로 변환.

  const formatChartData = () => {
    return salesData.map((monthlySales, index) => ({
      month: moment().month(index).format("M월"),
      sales: monthlySales || 0,
    }));
  };

  return (
    <div className="monthly-sales-chart">
      {salesData ? (
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={formatChartData()}>
            <XAxis dataKey="month" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Line type="monotone" dataKey="sales" stroke="#8884d8" />
          </LineChart>
        </ResponsiveContainer>
      ) : (
        <p>로딩 중...</p>
      )}
    </div>
  );
};

export default MonthlyChartTerm;
