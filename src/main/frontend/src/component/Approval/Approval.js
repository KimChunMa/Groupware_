import React , { useState , useEffect } from 'react';
import axios from 'axios';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';

/*휴가계작성 페이지*/
export default function ApprovalWrite( props ){

    //휴가게 쓰기
    const setApproval = ()=>{
        let info = {
            approvalWriter: document.querySelector('#approvalWriter').value,
            approvalTitle: document.querySelector('#approvalTitle').value,
            approvalContent: document.querySelector('#approvalContent').value,
            approvalData: document.querySelector('#approvalData').value,

        }

        console.log( info );

               axios.post('/approval/awrite', info )
                   .then(r=>{
                       console.log(r);
                       if(r.data=true){
                            alert('게시물작성성공');
                            window.location.href="/";
                       }

/*                       if(r.data==1){
                           alert('카테고리 선택후 쓰기 가능합니다')
                       }else if(r.data==2){
                           alert('로그인하고와')
                       }else if(r.data==3){
                           alert('게시물작성실패')
                       }else if(r.data==4){
                           alert('게시물작성성공');
                           window.location.href="/board/list";
                       }*/
                   } )


    }



    return(<>
        <Container>
            <TextField fullWidth className="approvalWriter" id="approvalWriter" label="작성자" variant="standard" />
            <TextField fullWidth className="approvalTitle"   id="approvalTitle" label="제목" variant="standard" />

            <TextField fullWidth className="approvalContent" id="approvalContent"
                      label="내용"
                      multiline
                      rows={5}
                      variant="standard"
            />
            <TextField fullWidth className="approvalData" id="approvalData" label="날짜" variant="standard" />
            <Button variant="outlined" onClick={ setApproval }> 작성 </Button>
            <Button variant="outlined"> 취소 </Button>
            <div>

            </div>
        </Container>
    </>)


}