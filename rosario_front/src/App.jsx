import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./log-in/LoginPage";
import RegisterPage from "./log-in/RegisterPage";
// import Dashboard from "./sellernav/Dashboard";
import Sellernav from "./sellernav/Sellernav";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/loginpage" element={<LoginPage />} />
        <Route path="/api/customers/register" element={<RegisterPage />} />
        <Route path="/admin/sellernav" element={<Sellernav />} />
      </Routes>
    </Router>
  );
}

export default App;
