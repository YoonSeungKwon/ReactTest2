import React, {useState} from "react";
import axios from "axios";

const Register = (props) =>{

    const [state, setState] = useState({
        email:"",
        password:""
    });


    const handleChange = (e) =>{
        const {id , value} = e.target   
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }

    const handleSubmit = () =>{
        axios.post("/api/member/join",  state,{
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(function(response){
                console.log(response.data)
        })
            .catch(function(error){
                console.log(error)
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
            <button type="submit" onClick={handleSubmit}>Register</button>
        </>
    )
}
export default Register;