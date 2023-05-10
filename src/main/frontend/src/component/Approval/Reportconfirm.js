import React , { useState , useEffect } from 'react';
import axios from 'axios';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import Approval from './Approval'
/*--------------------table mui----------------*/
/*import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';*/
//----------------------------------------------//


/*서류결제상태확인페이지 */
export default function Reportconfirm( props ){
    console.log("props확인")
    console.log(props);
    //요청한 게시물 정보를 가지고 있는 리스트 변수[상태관리변수]
    const [ rows, setRows ]=useState( [] );

    const getUserRank = () => {
        axios.get('/approval/getUserRank').then(r => {
            console.log(r);

        })
    }


    useEffect(()=>{
        axios.get('/approval/alist')
                .then(r => { console.log(r)


                 });
    });


    //서류상태확인
    return(<>
        <h3>서류</h3>
        <button  onClick={getUserRank} type="button">랭크</button>
    </>)
}