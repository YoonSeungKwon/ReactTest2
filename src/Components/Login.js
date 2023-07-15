import React, {useState} from "react";
import axios from "axios";
import { ACCESS_TOKEN } from "../Constant/apiConstant";
import { useNavigate } from "react-router-dom";

const Login = (props) =>{

    const navigate = useNavigate();

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
        axios.post("/api/member/login", state,{
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function(response){
            console.log(response)
            if(response.status === 200){
                localStorage.setItem(ACCESS_TOKEN,response.headers.get("Authorization"));
                navigate("/")
            }
            else{
                console.log("Error");
                alert(response.data.message)
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