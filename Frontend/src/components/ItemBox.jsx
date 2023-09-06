// ItemBox.jsx (updated)
import React, { useState } from 'react';
import './ItemBox.css';
import '../styles.css';

const ItemBox = (props) => {
    const [quantity, setQuantity] = useState(1); // Initialize quantity state with 1

    const handleQuantityChange = (event) => {
        setQuantity(parseInt(event.target.value));
    };

    const addToCart = () => {
        // Call the addToCart function from ProductList with the relevant parameters
        props.onAddToCart(props.productId, quantity, props.price);
    };

    return (
        <div className='itemBox' id='round'>
            <h1>{props.name}</h1>
            <img style={{ width: 100, height: 100 }} className='itemImage' src={props.imageURL} alt="Item" />
            <p>Brand: {props.brand}</p>
            <p>Category: {props.category}</p>
            <p>Price: {props.price}</p>
            <p># in stock: {props.stockQuantity}</p>
            <p>Description: {props.description}</p>
            <input
                type='number'
                id='roundSmall'
                placeholder='Enter amount'
                value={quantity}
                onChange={handleQuantityChange}
                min={1} // Make sure it's not negative or zero
            />
            <br />
            <button className='coolButton' id='round' onClick={addToCart}>
                Add to cart
            </button>
        </div>
    );
};

export default ItemBox;
