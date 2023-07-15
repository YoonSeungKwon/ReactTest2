import React, {useState, useEffect} from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { ACCESS_TOKEN } from "../Constant/apiConstant";

const Test = (props) =>{

    const [data, setData] = useState();

    const navigate = useNavigate();

    useEffect(()=>{
        axios.get("/api/v1/index"
        ,
        {
            headers:{
                Authorization:"Bearer "+localStorage.getItem(ACCESS_TOKEN)
            }
        }).then((res)=>{
            setData(res.data)
        }).catch((error)=>{
            console.log(error)
            alert("User Only")
            navigate("/")
        })
    })

    const handleClick = e =>{
        navigate("/")
    }

    const handleLogOut = () =>{
        localStorage.clear()
        alert("LogOut")
        navigate("/login")
    }

    return(
        <>
            <h4>{data}</h4>
            <button onClick={handleClick}>메인 페이지</button>
            <button onClick={handleLogOut}>로그아웃</button>
        </>
    )

}

export default Test;