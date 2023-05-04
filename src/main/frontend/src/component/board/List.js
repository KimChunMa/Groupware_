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

export default function List(props) {
    return(
    <Container>
        <div style={{display : 'flex' , justifyContent:'space-between' , alignItems:'center'}}>
            <select>
                <option>임시 카테고리1</option>
                <option>임시 카테고리2</option>
            </select>
            <div>
                <select>
                    <option>공지사항</option>
                    <option>프로젝트1팀</option>
                    <option>프로젝트2팀</option>
                </select>
                <input type="text" />
                <button variant="outlined"> 검색 </button>
            </div>
        </div>
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableCell align="center" style={{width:"10%"}}> 글 번호 </TableCell>
                    <TableCell align="center" style={{width:"10%"}}> 작성일 </TableCell>
                    <TableCell align="center" style={{width:"10%"}}> 카테고리 </TableCell>
                    <TableCell align="center" style={{width:"50%"}}> 제목 </TableCell>
                    <TableCell align="center" style={{width:"10%"}}> 작성자 </TableCell>
                    <TableCell align="center" style={{width:"10%"}}> 조회수 </TableCell>
                </TableHead>
                <TableBody>
                    <TableRow sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                        <TableCell component="th" scope="row"> 1 </TableCell>
                        <TableCell align="center"> 23-05-24</TableCell>
                        <TableCell align="center"> 공지사항 </TableCell>
                        <TableCell align="left"> 예시용 </TableCell>
                        <TableCell align="center"> 도라에몽 </TableCell>
                        <TableCell align="center"> 1 </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
        <div style={{margin:'30px 0 0 0 auto' , display:'flex'}}>
            <div>← 1 2 3 4 5 →</div>
            <div>
                <a href="/write"><button variant="outlined">게시물 작성</button></a>
            </div>
        </div>
    </Container>
    )
}