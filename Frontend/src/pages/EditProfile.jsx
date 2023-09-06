import React from 'react';

const EditProfile = () => {
    return (
        <div>
            <h1 >Edit your email/password</h1>
            <br></br>
            <ul>
                <li><input type='text' id='round' class='textfield' name='email' placeholder='New email (ex. xyz@email.com)'></input>
                <br></br>
                <br></br></li>
                <li><input type='password' id='round' class='textfield' name='newPassword' placeholder='New password'></input>
                <br></br>
                <br></br></li>
                <li><input type='password' id='round' class='textfield' name='confirmPassword' placeholder='Confirm password'></input>
                <br></br>
                <br></br></li>
            </ul>
            <br></br>
            <br></br>
            <button class='coolbutton' id='round' name='saveProfile'>Confirm</button>
        </div>
    );
};

export default EditProfile;