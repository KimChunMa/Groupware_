import React , { useState , useEffect } from 'react';
import axios from 'axios';

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import Container from '@mui/material/Container';
import Button from '@mui/material/Button';

import Header from '../Header';

export default function List(props) {
    return(
    <Container>
        <Header/>
        <div style={{display : 'flex' , justifyContent:'space-between' , alignItems:'center'}}>
            <select>
                <option>임시 카테고리1</option>
                <option>임시 카테고리2</option>
            </select>
            <div>
                <input type="text" />
                <a href="#"><button variant="outlined"> 게시글 작성</button></a>
            </div>
        </div>
        <TableContainer component={Paper}>
            <TableHead>
                <TableCell align="center" style={{width:"10%"}}> 글 번호 </TableCell>
                <TableCell align="center" style={{width:"10%"}}> 작성일 </TableCell>
                <TableCell align="center" style={{width:"10%"}}> 카테고리 </TableCell>
                <TableCell align="center" style={{width:"50%"}}> 제목 </TableCell>
                <TableCell align="center" style={{width:"10%"}}> 작성자 </TableCell>
                <TableCell align="center" style={{width:"10%"}}> 조회수 </TableCell>
            </TableHead>
        </TableContainer>
    </Container>
    )
}