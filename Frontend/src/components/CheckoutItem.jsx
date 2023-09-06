import React from 'react';


const CheckoutItem = ({productId, productName, quantity, totalPrice, onDelete }) => {

    const handleDelete = (productId) => {
        // call on delete function from cart
        onDelete(productId);
    };

    return (
        <div className='cartItem' id='round'>
            <ul>
                <li>Name of product: {productName}</li>
                <li>Quantity: {quantity}</li>
                <li>Total price: {totalPrice}</li>
            </ul>
        </div>
    );
};

export default CheckoutItem;
