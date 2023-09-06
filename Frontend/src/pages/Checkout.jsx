import React, { useEffect, useState } from 'react';
import CheckoutItem from '../components/CheckoutItem';

const Checkout = () => {
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

    return (
        <div>
            <h1 >Checkout</h1>

            <div>
                 <h2>Your items:</h2>
                 {cartItems.map(item => (
                <CheckoutItem
                    key={item.productId}
                    productName={item.productName}
                    quantity={item.quantity}
                    totalPrice={item.totalPrice}
                />
            ))}
            </div>

            <form action='/order'>
                <button id='round' className='coolbutton'>Place order</button>
            </form>
        </div>
    );
};

export default Checkout;