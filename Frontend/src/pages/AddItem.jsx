import React from 'react';

const AddItem = () => {
    return (
        <div>
            <h1>Add a new item</h1>
            <form>
                <ul>
                    <li><input type='text' id='round' name='itemID' placeholder='Item ID#'></input></li>
                    <br></br>
                    <br></br>
                    <li><input type='text' id='round' name='itemName' placeholder='Name of item'></input></li>
                    <br></br>
                    <br></br>
                    <li><input type='text' id='round' name='itemCategory' placeholder='Category'></input></li>
                    <br></br>
                    <br></br>
                </ul>

                <ul>
                    <li><input type='text' id='round' name='itemDesc' placeholder='Description'></input></li>
                    <br></br>
                    <br></br>
                    <li><input type='text' id='round' name='itemPrice' placeholder='Price'></input></li>
                    <br></br>
                    <br></br>
                    <li><input type='text' id='round' name='restockAmount' placeholder='Restock'></input></li>
                    <br></br>
                    <br></br>
                </ul>

                <ul>
                    <button type='submit' id='round'>Add item</button>
                </ul>
                <ul>
                    <button type='reset' id='round'>Reset</button>
                </ul>
                
            </form>
            
        </div>
    );
};

export default AddItem;