import React from 'react';

const EditAddress = () => {
    return (
        <div>
            <h1 >Edit your address info</h1>
            <ul>
                <li><input type='text' id='round' class='textfield' name='address' placeholder='Street Address'></input>
                <br></br>
                <br></br></li>
                <li><input type='text' id='round' class='textfield' name='city' placeholder='City'></input>
                <br></br>
                <br></br></li>
                <li><input type='text' id='round' class='textfield' name='province' placeholder='Province'></input>
                <br></br>
                <br></br></li>
                <li><input type='text' id='round' class='textfield' name='postalCode' placeholder='Postal/Zip code'></input>
                <br></br>
                <br></br></li>
                <li><input type='text' id='round' class='textfield' name='phoneNumber' placeholder='Phone#'></input>
                <br></br>
                <br></br></li>
            </ul>

            <br></br>
            <br></br>
            <button class='coolbutton' id='round' name='saveAddress'>Confirm</button>
        </div>
    );
};

export default EditAddress;