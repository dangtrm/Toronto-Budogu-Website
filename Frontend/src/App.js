import React from 'react';
import {useState} from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home.jsx';
import Sidebar from './components/Sidebar';
import Navbar from './components/Navbar';
import Profile from './pages/Profile.jsx';
import Cart from './pages/Cart.jsx';
import ProductList from './pages/ProductList.jsx';
import About from './pages/About.jsx';
import Login from './pages/Login.jsx';
import Team from './pages/Team.jsx';
import Register from './pages/Register.jsx';
import EditProfile from './pages/EditProfile.jsx';
import EditAddress from './pages/EditAddress.jsx';
import Order from './pages/Order.jsx';
import Checkout from './pages/Checkout';
import ProductListEdit from './pages/ProrductListEdit';
import './styles.css';
//import axios from 'axios';

const App = () => {

  return (
    <BrowserRouter>
    <Navbar/>
    <Sidebar>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/productList" element={<ProductList />} />
      <Route path="/cart" element={<Cart />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/checkout" element={<Checkout />} />
      <Route path="/order" element={<Order/>} />
      <Route path="/editProfile" element={<EditProfile/>} />
      <Route path="/editAddress" element={<EditAddress/>} />
      <Route path="/editProducts" element={<ProductListEdit/>} />
      <Route path="/about" element={<About />} />
      <Route path="/team" element={<Team/>} />
    </Routes>
    </Sidebar>
    <footer>Copyright © 2023 Toronto Budogu team</footer>
    </BrowserRouter>
  );
};

export default App;