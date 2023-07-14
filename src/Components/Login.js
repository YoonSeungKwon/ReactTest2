import React, {useState} from "react";
import axios from "axios";
import { ACCESS_TOKEN } from "../Constant/apiConstant";

const Login = (props) =>{

    const [state, setState] = useState(
        {
            email:"",
            password:"",
        }
    )    


    const handleChange = e=>{
        const {id , value} = e.target   
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }

    const handleSubmit = () =>{
        axios.post("/api/member/login", {
            headers: {
                'Content-Type': 'application/json'
            },
            body:{
                'email':state.email,
                'password':state.password
            }
        }).then(function(response){
            console.log(response)
            if(response.status === 200){
                localStorage.setItem(ACCESS_TOKEN,response.headers.get("Authorization"));
            }
            else{
                console.log("Error");
            }
        }).catch(function(error){
            console.log(error);
        });
    }


    return(
        <>
            Email:
            <input type="text"
                    id="email"
                    name="email"
                    value={state.email}
                    onChange={handleChange}
            />
            <br/>
            Password:
            <input type="text"
                    id="password"
                    name="password"
                    value={state.password}
                    onChange={handleChange}
            />
            <br/>
            <button type="submit" onClick={handleSubmit}>Login</button>
        </>
    )

}

export default Login;