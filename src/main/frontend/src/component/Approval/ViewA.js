import React , { useState , useEffect } from 'react';
import axios from 'axios';
import {useParams}from 'react-router-dom';
import Button from '@mui/material/Button';

import Container from '@mui/material/Container';

export default function ViewA(props){
    /*결제전  서류  내용 페이지*/
    const params = useParams();
    console.log(params);
    console.log(params.approvalNo);

    //해당게시물의 서류내용출력 [2023-05-15]
    const [approval , setApproval] = useState({});

    //1.해당게시물의 서류내용출력 [2023-05-15]
    const getPrint = ()=>{
        axios.get('/approval/getPrint',{ params :{approvalNo:params.approvalNo} })
            .then( (r) =>{
                console.log(r);
                console.log(r.data);
                setApproval(r.data);
            })

    }
    //1.처음 열렸을때 렌더링
    useEffect(()=>{getPrint();},[])


     //수락버튼 클릭시[2023-05-12]
    const setOkay = () => {
        console.log('수락버튼 클릭');
        console.log(params.approvalNo);
        axios.get('/approval/getAccept', { params :{approvalNo:params.approvalNo} } ).then( r => {
            console.log(r);
            console.log(r.data);
            if(r.data==1){
                    alert('수락되었습니다');
            }else{
                    alert('수락실패하였습니다')
            }
        })
    }

    //반려버튼 클릭시[2023-05-15]
    const setNo = () => {
        console.log('반려버튼 클릭')
        console.log(params.approvalNo);
        axios.get( '/approval/getRefuse', { params :{approvalNo:params.approvalNo} } ).then( r =>{
                console.log(r);
                console.log(r.data);
                if(r.data==1){
                    alert('반려되였습니다')
                }
        })
    }


    return(<>
        <Container>
                 <h3>서류상세내용확인</h3>
            <div>
                 작성일: {approval.approvalData}
            </div>

            <div>
                작성자: {approval.approvalWriter}
            </div>

            <div>
                 작성제목: {approval.approvalTitle}
            </div>

            <div>
                 작성내용: {approval.approvalContent}
            </div>

            <div>
                 직위: {approval.memberRank} /*수정필요*/
            </div>

            <div>
                 부서명: {approval.partName} /*수정필요*/
            </div>
        </Container>

            <Button onClick={ setOkay }> 수락 </Button>
            <Button onClick={ setNo }> 반려 </Button>

    </>)

}