import React from 'react';

const Profile = () => {
    return (
        <div>
            <h1>My profile</h1>
            <br></br>
            <div class='accountInfo'>
            <h2>Account info</h2>
                <ul>
                    <li>Username:</li>
                    <li>Email:</li>
                    <li></li>
                </ul>
            </div>
            <form action='/editProfile'>
                <button class='coolbutton' id='round' name='editAccount'>Change email or password</button>
            </form>
            <br></br>
            <br></br>

            <div class='contactInfo'>
            <h2>Contact Info</h2>
                <ul>
                    <li>Street address:</li>
                    <li>City:</li>
                    <li>Postal/zip code:</li>
                    <li>Province/state:</li>
                    <li>Phone number:</li>
                </ul>
            </div>
            <form action='/editAddress'>
                <button class='coolbutton' id='round' name='editAddress'>Change address info</button>
            </form>
            <br></br>
            <br></br>
        </div>
    );
};

export default Profile;