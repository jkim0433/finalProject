//금일매출표

import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table, Card, Typography } from "antd";
import moment from "moment";

const API_BASE_URL = "http://localhost:8081/rosario/admin/sales";

const { Title } = Typography;

const DailyTableTerm = () => {
  const [dailySales, setDailySales] = useState([]);

  useEffect(() => {
    fetchDailySalesDetails();
  }, []);

  const fetchDailySalesDetails = async () => {
    //const today = moment();
    const date = moment("2024-04-02");
    try {
      const response = await axios.get(
        `${API_BASE_URL}/term/daily/details/${date.year()}/${
          date.month() + 1
        }/${date.date()}` //date->today로 변경하면 됨
      );
      const data = Array.isArray(response.data) ? response.data : [];
      setDailySales(data);
    } catch (error) {
      console.error("Failed to fetch daily sales details:", error);
      setDailySales([]);
    }
  };

  const columns = [
    { title: "NO", dataIndex: "ordersId", key: "ordersId" },
    { title: "고객 ", dataIndex: "customerNm", key: "customerNm" },
    { title: "주문날짜", dataIndex: "ordersDate", key: "ordersDate" },
    { title: "주문금액", dataIndex: "totalAmount", key: "totalAmount" },
    { title: "구독", dataIndex: "subscriptionType", key: "subscriptionType" },
  ];

  return (
    <div className="daily-sales-table">
      <Card>
        <Title level={2}>금일 매출표</Title>
        <Table dataSource={dailySales} columns={columns} rowKey="ordersId" />
      </Card>
    </div>
  );
};

export default DailyTableTerm;
