import React from 'react';
import { Link } from 'react-router-dom';

const Order = () => {
    return (
        <div>
            <h1 >Thank you for shopping with us!</h1>
            <Link to='/productList'><h2>Continue shopping</h2></Link>
        </div>
    );
};

export default Order;