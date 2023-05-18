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

    //해당게시물의 서류내용출력 [2023-05-18]
    const [approval , setApproval] = useState({});

    //1.해당게시물의 서류내용출력 [2023-05-18]
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


    /*직위*/
    const abc = (value) => {
            if( value == 1 ){
                    return '사원';
                }else if(value == 2) {
                    return '주임';
                }else if(value == 3) {
                    return '대리';
                }else if(value == 4 ){
                    return '과장';
                }else if(value == 5 ){
                    return '차장';
                }else if(value == 6) {
                    return '팀장';
                }else if(value == 7){
                    return '부장';
                }else if( value == 9 ){
                    return '사장';
                }else{
                    return '슈퍼관리자'
                }

            }

    return(<>
        <Container>
                 <h3> MY서류상세내용확인</h3>
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
                 직위:  { abc(approval.memberRank) }
            </div>

            <div>
                 부서명: {approval.partName}
            </div>
        </Container>


    </>)

}