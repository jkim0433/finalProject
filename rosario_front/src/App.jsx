import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./log-in/LoginPage";
import RegisterPage from "./log-in/RegisterPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/loginpage" element={<LoginPage />} />
        <Route path="/api/customers/register" element={<RegisterPage />} />
      </Routes>
    </Router>
  );
}

export default App;
