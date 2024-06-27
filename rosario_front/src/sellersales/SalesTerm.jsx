// 이 모든걸 가져옴
// SalesTerm.jsx
import React, { useState, useEffect } from "react";
import axios from "axios";
import moment from "moment";
import { Card, Typography, Select } from "antd";

import DailySalesChart from "./DailyChartTerm"; // Adjust the path according to your file structure
// import QuarterlySalesChart from "./QuarterlySalesChart"; // Adjust the path according to your file structure
import MonthlySalesChart from "./MonthlyChartTerm"; // Adjust the path according to your file structure
import DailySalesTable from "./DailyTableTerm"; // Adjust the path according to your file structure
import styles from "./SalesTerm.module.css";

const API_BASE_URL = "http://localhost:8081/rosario/admin/sales";

const { Title } = Typography;
const { Option } = Select;

const SalesTerm = () => {
  const [selectedPeriod, setSelectedPeriod] = useState("Daily");

  useEffect(() => {
    // Fetch initial data or perform any necessary actions on component mount
  }, []);

  return (
    <div className={styles.salesTerm}>
      <Card>
        <Title level={3}>매출현황</Title>
        <Select
          defaultValue="Daily"
          onChange={(value) => setSelectedPeriod(value)}
          style={{ width: 200 }}
        >
          <Option value="Daily">일별</Option>

          <Option value="Monthly">월별</Option>
        </Select>
        {selectedPeriod === "Daily" && <DailySalesChart />}

        {selectedPeriod === "Monthly" && <MonthlySalesChart />}
      </Card>

      <Card>
        <DailySalesTable />
      </Card>
    </div>
  );
};

export default SalesTerm;
