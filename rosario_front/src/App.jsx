import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./log-in/LoginPage";
import RegisterPage from "./log-in/RegisterPage";
import Header from "./components/Header";
import Home from "./pages/Home";

function App() {
  return (
    <Router>
      <div>
        <Routes>
          {/* Route without Header */}
          <Route path="/loginpage" element={<LoginPage />} />
          <Route path="/api/customers/register" element={<RegisterPage />} />
          
          {/* Route with Header */}
          <Route path="/" element={
            <>
              <header>
                <Header />
              </header>
              <main id='main'>
                <Routes>
                  <Route path="/" element={<Home />} />
                </Routes>
              </main>
              <footer id='footer'>
                {/* Footer content */}
              </footer>
            </>
          } />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
