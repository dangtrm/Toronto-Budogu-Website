import React from 'react';

const SearchBar = ({ searchInput, setSearchInput, handleCategoryFilter, handleBrandFilter }) => {
    const handleChange = (e) => {
        setSearchInput(e.target.value);
    };

    const handleSearch = (e) => {
        e.preventDefault();
        setSearchInput('');
    };

    return (
        <form onSubmit={handleSearch}>
            <div>
                <input
                    type='search'
                    className='searchbar'
                    id='round'
                    placeholder='What are you looking for?'
                    onChange={handleChange}
                    value={searchInput}
                />
                <button type='submit' className='coolbutton' id='round'>
                    Search
                </button>
            </div>
            <div>
                <button
                    className='coolbutton'
                    id='round'
                    onClick={() => handleCategoryFilter('categoryValue')}
                >
                    Filter by Category
                </button>
                <button
                    className='coolbutton'
                    id='round'
                    onClick={() => handleBrandFilter('brandValue')}
                >
                    Filter by Brand
                </button>
            </div>
        </form>
    );
};

export default SearchBar;
