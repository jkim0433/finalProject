import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./log-in/LoginPage";
import RegisterPage from "./log-in/RegisterPage";
import Header from "./components/Header";
import Home from "./pages/Home";
import SellerNav from "./sellernav/SellerNav";
import Dashboard from "./sellernav/Dashboard";
import SubOrders from "./sellerorders/SellerSubOrders";
import GenOrders from "./sellerorders/SellerGenOrders";
import Term from "./sellersales/Term";
import Type from "./sellersales/Type";
import Catalog from "./selleredit/Catalog";
import Profile from "./selleredit/Profile";
import Product from "./selleredit/Product";
import Subscription from "./sellersubscription/Subscription";

function App() {
  return (
    <Router>
      <div>
        <Routes>
          {/* Route without Header */}
          <Route path="/loginpage" element={<LoginPage />} />
          <Route path="/api/customers/register" element={<RegisterPage />} />
          {/* <Route path="/admin/sellernav" element={<Sellernav />} /> */}

          {/* Route with Header */}
          <Route
            path="/"
            element={
              <>
                <header>
                  <Header />
                </header>
                <main id="main">
                  <Routes>
                    <Route path="/" element={<Home />} />
                  </Routes>
                </main>
                <footer id="footer">{/* Footer content */}</footer>
              </>
            }
          />

          {/* Route with admin */}
          <Route
            path="/admin/*"
            element={
              <>
                <SellerNav />
                <Routes>
                  <Route path="/dashboard" element={<Dashboard />} />
                  <Route path="/edit/catalog" element={<Catalog />} />
                  <Route path="/edit/profile" element={<Profile />} />
                  <Route path="/edit/product" element={<Product />} />
                  <Route path="/sales/term" element={<Term />} />
                  <Route path="/sales/type" element={<Type />} />
                  <Route path="/orders/suborders" element={<SubOrders />} />
                  <Route path="/orders/genorders" element={<GenOrders />} />
                  <Route path="/subscription" element={<Subscription />} />
                </Routes>
              </>
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
