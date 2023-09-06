// ProductList.jsx (updated)
import React, { useState, useEffect } from 'react';
import ItemBox from '../components/ItemBox.jsx';

const ProductList = () => {
    const [products, setProducts] = useState([]);
    const [filteredProducts, setFilteredProducts] = useState([]);
    const [searchInput, setSearchInput] = useState('');

    console.log(localStorage.getItem('userId'));

    useEffect(() => {
        fetch('http://localhost:8080/product')
            .then(response => response.json())
            .then(data => {
                setProducts(data);
                setFilteredProducts(data);
            })
            .catch(error => console.error('Error fetching products:', error));
    }, []);

    useEffect(() => {
        if (searchInput === '') {
            setFilteredProducts(products);
        } else {
            handleSearch(searchInput);
        }
    }, [searchInput]);

    const handleSearch = (keyword) => {
        fetch(`http://localhost:8080/product/byKeyword/${keyword}`)
            .then(response => response.json())
            .then(data => setFilteredProducts(data))
            .catch(error => console.error('Error fetching products by keyword:', error));
    };

    const handleCategoryFilter = (category) => {
        fetch(`http://localhost:8080/product/byCategory/${category}`)
            .then(response => response.json())
            .then(data => setFilteredProducts(data))
            .catch(error => console.error('Error fetching products by category:', error));
    };

    const handleBrandFilter = (brand) => {
        fetch(`http://localhost:8080/product/byBrand/${brand}`)
            .then(response => response.json())
            .then(data => setFilteredProducts(data))
            .catch(error => console.error('Error fetching products by brand:', error));
    };

    const addToCart = async (productId, quantity, price) => {
        const userId = localStorage.getItem('userId'); // Replace 'userId' with the actual key used in localStorage
        try {
            const response = await fetch(`http://localhost:8080/cart/${userId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
                },
                body: JSON.stringify({ productId: productId, quantity: quantity, price: price })
            });

            if (response.ok) {
                // Handle success or update UI accordingly
                alert('Added to cart!');
                // refresh the page
                window.location.reload();
            } else {
                console.error('Failed to add to cart.');
                alert('Not currently logged in. Please log in.');
            }
        } catch (error) {
            console.error('Error adding to cart:', error);
            alert('Not currently logged in. Please log in.');
        }
    };

    return (
        <>
            <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', padding: '0 30px' }}>
                <h1>In stock now</h1>
                <div>
                    <input
                        type='search'
                        className='searchbar'
                        id='round'
                        placeholder='What are you looking for?'
                        onChange={(e) => setSearchInput(e.target.value)}
                        value={searchInput}
                    />
                    <button className='coolbutton' id='round' onClick={() => handleCategoryFilter(searchInput)}>
                        Search by Category
                    </button>
                    <button className='coolbutton' id='round' onClick={() => handleBrandFilter(searchInput)}>
                        Search by Brand
                    </button>
                </div>
            </div>
            <br />
            <div className='productList'>
                {filteredProducts.map(product => (
                    <ItemBox
                        key={product.id}
                        name={product.name}
                        category={product.category}
                        imageURL={product.imageURL}
                        color={product.color}
                        price={product.price}
                        description={product.info}
                        stockQuantity={product.stockQuantity}
                        brand={product.brand}
                        productId={product.id} // Pass productId as a prop
                        onAddToCart={addToCart}
                    />
                ))}
            </div>
        </>
    );
};

export default ProductList;
