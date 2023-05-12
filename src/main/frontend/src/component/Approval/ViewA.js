import React , { useState , useEffect } from 'react';
import axios from 'axios';
import {useParams}from 'react-router-dom';
import Button from '@mui/material/Button';

export default function ViewA(props){
    /*결제전  서류  내용 페이지*/
    const params = useParams();
    console.log(params);
    console.log(params.approvalNo);

/*    //해당게시물의서 류내용출력
    useEffect(()=>{
        axios.get('/board',{params:pageInfo}) //0은 전체보기
            .then(res=>{console.log(res);
            setRows(res.data.boardDtoList)
            setTotalPage(res.data.totalPage)
            setTotalCount(res.data.totalCount)

            })
            .catch(err=>{console.log(err);})
    }, [pageInfo]) //pageInfo(cno,page)  변경될때마다 해당 useEffect 실행된다    */


     //수락버튼 클릭시
    const setokay = () => {
        console.log('수락버튼 클릭');
        console.log(params.approvalNo);
        axios.get('/approval/getAccept', { params :{approvalNo:params.approvalNo} } ).then( r => {
            console.log(r);
            console.log(r.data);
            alert('수락되었습니다');
        })
    }

    const setNo = () => {
        console.log('반려버튼 클릭')
    }



    return(<>
        <h3>서류상세내용확인</h3>


        <Button onClick={ setokay }> 수락 </Button>
        <Button onClick={ setNo }> 반려 </Button>

    </>)

}