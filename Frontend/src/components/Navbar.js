import React, { useState } from 'react';
import {
    FaTh,
    FaBars,
    FaUserAlt,
    FaRegChartBar,
    FaCommentAlt,
    FaShoppingBag,
    FaThList,
    FaAddressBook,
    FaCartPlus,
    FaInfo,
    FaKey,
    FaHome,
    FaPersonBooth,
}from "react-icons/fa";
import { Link } from 'react-router-dom';
import SearchBar from './SearchBar'

import './Sidebar.css';

export default function Navbar() {
    return (
        <nav className="nav">
            <Link to="/" className="site-title">Toronto Budogu</Link>
            <ul>
                    <li>
                    <Link to="/about"><FaInfo></FaInfo>About</Link>
                </li>
                <li>
                    <Link to='/productList'><FaShoppingBag></FaShoppingBag>Shop</Link>
                </li>
                <li>
                    <Link to='/login'><FaKey></FaKey>Login</Link>
                </li>
            </ul>
        </nav>
    );
};