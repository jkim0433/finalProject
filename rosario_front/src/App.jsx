import React from "react";
import { BrowserRqouter as Router, Routes, Route } from "react-router-dom";
// import Home from "./pages/Home";
// import About from "./pages/About";
// import Shop from "./pages/Shop";
// import MyPage from "./pages/MyPage";
import LoginPage from "./log-in/LoginPage";

function App() {
  return (
    <Router>
      <Routes>
        {/* <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/shop" element={<Shop />} />
        <Route path="/mypage" element={<MyPage />} /> */}
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </Router>
  );
}

export default App;
