import React,{useState,useEffect} from 'react'
import axios from 'axios'
import {useParams} from 'react-router-dom'; // HTTP 경로 상의 매개변수 호출 해주는 함수
// mui Container
import Container from '@mui/material/Container';

import ReplyList from './ReplyList'
export default function View(props) {

    const params = useParams();
    const [board , setBoard] = useState({});

    const [login , setLogin] = useState(JSON.parse(sessionStorage.getItem('login_token') ) )

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

    // 2. 게시글 삭제
    const onDelete = () =>{
        axios.delete("/board" , {params : {boardNo : params.boardNo}})
            .then(r=>{
                console.log(r.data);
                if(r.data==true){
                    alert('삭제 완료');
                    window.location.href="/list";
                }else{alert("삭제 실패")}
            })
    }

    // 3. 수정 페이지 이동
    const onUpdate =()=>{ window.location.href="/update?boardNo="+board.boardNo }

    // 4. 댓글 작성
    const onReplyWrite=(replyContent) =>{
        let info = {replyContent : replyContent , boardNo : board.boardNo}; console.log(info);
        axios.post("/board/reply" , info)
            .then((r)=>{
                if(r.data==true){
                    alert('댓글작성 완료'); getBoard();
                }else{
                    alert('댓글작성 실패')
                }
            })
    }

    // 게시물 수정 삭제 버튼박스
       const btnBox =
                    login != null && login.memberNo == board.memberNo
                    ? <div> <button onClick={ onDelete }>삭제</button>
                            <button onClick={onUpdate} >수정</button> </div>
                    : <div> </div>

return (
    <Container>
        <div>
            작성자: {board.memberName}
            조회수: {board.boardView}
        </div>
        <div>
            <h3>{board.boardTitle}</h3>
        </div>
        <div>
            <p>{board.boardContent}</p>
            {btnBox}
        </div>
        <ReplyList onReplyWrite={onReplyWrite} />
    </Container>
);
}