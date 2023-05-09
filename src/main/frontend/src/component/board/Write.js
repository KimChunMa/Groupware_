import React,{useState,useEffect} from 'react'
import axios from 'axios'
import Container from '@mui/material/Container';

import Header from '../Header';

export default function Write(props) {

    // 1. 게시물 쓰기
    const setBoard = () =>{
        let info = {
            boardTitle : document.querySelector("#boardTitle").value,
            boardContent : document.querySelector("#boardTitle").value,
            partNo : partNo
        }
        console.log(info)
    }

    axios.post()

    return (<h3>글쓰기 페이지입니다.</h3>)

}