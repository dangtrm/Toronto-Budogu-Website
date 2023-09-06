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
    FaPeopleArrows
}from "react-icons/fa";
import { NavLink } from 'react-router-dom';
import { Link } from 'react-router-dom';

import './Sidebar.css';


const Sidebar = ({children}) => {
    const[isOpen ,setIsOpen] = useState(false);
    const toggle = () => setIsOpen (!isOpen);
    const menuItem=[
        {
            path:"/profile",
            name:"Profile",
            icon:<FaAddressBook/>
        },
        {
            path:"/productList",
            name:"Browse shopping directory",
            icon:<FaShoppingBag/>
        },
        {
            path:"/cart",
            name:"Shopping cart",
            icon:<FaCartPlus/>
        },
        {
            path:"/about",
            name:"About",
            icon:<FaInfo/>
        },
        {
            path:"/team",
            name:"Our team",
            icon:<FaPeopleArrows/>
        }
    ]
    return (
        <div className="container">
           <div style={{width: isOpen ? "300px" : "50px"}} className="sidebar">
               <div className="top_section">
                   <h1 style={{display: isOpen ? "block" : "none"}} className="logo"><Link className="homelink" to="/">Toronto Budogu</Link></h1>
                   <div style={{marginLeft: isOpen ? "50px" : "0px"}} className="bars">
                       <FaBars onClick={toggle}/>
                   </div>
               </div>
               {
                   menuItem.map((item, index)=>(
                       <NavLink to={item.path} key={index} className="link" activeclassName="active">
                           <div className="icon">{item.icon}</div>
                           <div style={{display: isOpen ? "block" : "none"}} className="link_text">{item.name}</div>
                       </NavLink>
                   ))
               }
           </div>
           <main>{children}</main>
        </div>
    );
};

export default Sidebar;