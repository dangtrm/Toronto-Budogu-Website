import React from 'react';

import { FaTrash } from 'react-icons/fa';

const CartItem = ({productId, productName, quantity, totalPrice, onDelete }) => {

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
                <button id='round' className="coolbutton" onClick={() => handleDelete(productId)}><FaTrash size={30} /></button>
            </ul>
        </div>
    );
};

export default CartItem;
