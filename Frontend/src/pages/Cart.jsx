import React, { useEffect, useState } from 'react';
import CartItem from '../components/CartItem.jsx';

import {

    FaCartPlus,
} from "react-icons/fa";

const Cart = () => {
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
        fetchCartItems();
    }, []);

    const fetchCartItems = async () => {
        try {
            const accessToken = localStorage.getItem('accessToken');
            const userId = localStorage.getItem('userId');
            const response = await fetch(`http://localhost:8080/cart/${userId}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            });

            if (response.ok) {
                const data = await response.json();
                setCartItems(data.cartItems);
            } else {
                console.error('Error fetching cart items');
            }
        } catch (error) {
            console.error('Error fetching cart items:', error);
        }
    };

    const handleDelete = async () => {
        try {
            const accessToken = localStorage.getItem('accessToken');
            const userId = localStorage.getItem('userId');
            const response = await fetch(`http://localhost:8080/cart/${userId}`, {
                method: 'DELETE',
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            });

            if (response.ok) {
                // Cart successfully deleted, update the cart items
                setCartItems([]);
            } else {
                console.error('Error deleting cart');
            }
        } catch (error) {
            console.error('Error deleting cart:', error);
        }
    };

    const handleCheckout = async () => {
        try {
            const accessToken = localStorage.getItem('accessToken');
            const userId = localStorage.getItem('userId');
            const response = await fetch(`http://localhost:8080/cart/${userId}/checkout`, {
                method: 'POST',
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            });

            if (response.ok) {
                // Handle successful checkout or update UI
            } else {
                console.error('Error checking out cart');
            }
        } catch (error) {
            console.error('Error checking out cart:', error);
            alert("Session expired after 30 minutes. Please log in again.");
        }
    };

    return (
        <div>
            <h1>Shopping cart</h1>
            {cartItems.map(item => (
                <CartItem
                    key={item.productId}
                    productName={item.productName}
                    quantity={item.quantity}
                    totalPrice={item.totalPrice}
                />
            ))}
            <form action='/Checkout'>
                <button id='round' className='coolbutton'><h2>Check out</h2><FaCartPlus /></button>
            </form>
        </div>
    );
};

export default Cart;
