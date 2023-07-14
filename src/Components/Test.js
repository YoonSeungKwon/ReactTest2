import React, {useState, useEffect} from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Test = () =>{

    const [data, setData] = useState();

    const navigate = useNavigate();

    useEffect(()=>{
        axios.get("/api/v1/index"
        ).then((res)=>{
            setData(res.data)
        }).catch()
    })

    const handleClick = e =>{
        navigate("/")
    }

    return(
        <>
            <h4>{data}</h4>
            <button onClick={handleClick}>메인 페이지</button>
        </>
    )

}

export default Test;