import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import { DataGrid } from '@mui/x-data-grid';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import SendIcon from '@mui/icons-material/Send';
import Stack from '@mui/material/Stack';

const columns = [
    { field: 'id', headerName: '번호', width: 100 },
    { field: 'memberId', headerName: '아이디', width: 100 },
    { field: 'partNo', headerName: '부서', width: 100 },
    { field: 'memberName', headerName: '사원명', width: 100 },
    { field: 'memberPhone', headerName: '휴대폰', width: 100 },
    { field: 'memberEmail', headerName: '이메일', width: 100 }
];

export default function MemberList( props ) {

    const [ rows , setRows ] = useState([]);
    const [ rowSelectionModel, setRowSelectionModel ] = React.useState([]);
    console.log( rowSelectionModel );

    const getMemberList = () => {
        console.log('getMemberList 실행')
        axios.get("/member").then( r => {
            // mui 사용시 반드시 id 값이 필요
            r.data.forEach( ( m ) => {
                m.id = m.memberNo; // axios 로 받은 데이터를 반복문을 돌려 id 필드 추가
            })

            setRows( r.data );
        })
    }

    const onDeleteHandler = () => {

        const msg = window.confirm("삭제 시 복구가 불가능합니다. 삭제하시겠습니까?");

        if( msg ) {
            rowSelectionModel.forEach( o => {
                axios.delete("/member" , { params : { memberNo : o } } ).then( r => {
                    if( r.data ) {
                        getMemberList();
                    }else{
                        alert("[ FAIL ] 삭제 실패")
                    }
                })
            })
        }
    }


    useEffect( () => {
        getMemberList();
    } , [] )

    return(<>
        <div style={{ height: 400, width: '100%' }}>
            <DataGrid
                rows={rows}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: { page: 0, pageSize: 5 },
                    },
                }}
                pageSizeOptions={[5, 10]}
                checkboxSelection
                onRowSelectionModelChange={ (newRowSelectionModel) => {
                    setRowSelectionModel(newRowSelectionModel);
                }}
            />
        </div>

        <Stack direction="row" spacing={2}>
          <Button
            variant="outlined"
            startIcon={<DeleteIcon />}
            onClick={ onDeleteHandler }
          >
            선택삭제
          </Button>
        </Stack>
    </>);

}