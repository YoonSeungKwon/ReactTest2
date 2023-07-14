import React from "react";
import { Link } from "react-router-dom";

const Main = () =>{

    return(
        <>
            <h4>
                Main
            </h4>
            <Link to="/login">to Login Page</Link>
            <br/>
            <Link to="/register">to Register Page</Link>
            <br/>
            <Link to="/test">Test Page</Link>
        </>
    )

}
export default Main;