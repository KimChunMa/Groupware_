import React,{useState,useEffect} from 'react'
import axios from 'axios'
import {useParams} from 'react-router-dom'; // HTTP 경로 상의 매개변수 호출 해주는 함수
// mui Container
import Container from '@mui/material/Container';

export default function View(props) {

    const params = useParams();
    const [board , setBoard] = useState({});

    // 1. 개별 게시물 가져오는 axios 함수
    const getBoard = ()=>{
        axios.get("/board/getboard" , {params:{boardNo : params.boardNo}})
            .then((r)=>{
                console.log(r.data);
                setBoard(r.data);
            })
    }

    // 1-1 처음 열렸을때 렌더링
    useEffect(()=>{ getBoard();},[])

return (
    <Container>
        <div>
            작성자: {board.memberName}
        </div>
        <div>
            <h3>{board.boardTitle}</h3>
        </div>
        <div>
            <p>{board.boardContent}</p>
        </div>
    </Container>
);
}