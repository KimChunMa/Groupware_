import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import { Paper , Button , Box , TextField } from '@mui/material'
import { DataGrid } from '@mui/x-data-grid';

const columns = [
  { field: 'id', headerName: 'ID', width: 90 },
  { field: 'firstName', headerName: 'First name', width: 150, editable: true },
  { field: 'lastName', headerName: 'Last name', width: 150, editable: true },
  { field: 'age', headerName: 'Age', type: 'number', width: 110, editable: true },
  { field: 'fullName', headerName: 'Full name', width: 160 }
];

const rows = [
    { id: 1, lastName: 'Snow', firstName: 'Jon', age: 35 },
    { id: 2, lastName: 'Lannister', firstName: 'Cersei', age: 42 },
    { id: 3, lastName: 'Lannister', firstName: 'Jaime', age: 45 },
    { id: 4, lastName: 'Stark', firstName: 'Arya', age: 16 },
    { id: 5, lastName: 'Targaryen', firstName: 'Daenerys', age: null },
    { id: 6, lastName: 'Melisandre', firstName: null, age: 150 },
    { id: 7, lastName: 'Clifford', firstName: 'Ferrara', age: 44 },
    { id: 8, lastName: 'Frances', firstName: 'Rossini', age: 36 },
    { id: 9, lastName: 'Roxie', firstName: 'Harvey', age: 65 }
];

export default function AddAddressBook( props ) {

    const [ rowSelectionModel, setRowSelectionModel ] = React.useState([]);
    console.log( rowSelectionModel );

    console.log( props.selectedId + " 그룹으로부터 받은 선택된 그룹아이디" )

    return (<>
        <Paper elevation={3} style={{ height: "100%" , marginLeft: '9px'}}>
            <h4 className="addr-add-title"> 주소록 목록 </h4>
            <div>
                <Box sx={{ height: 400, width: '95%' , margin: '0px auto'}}>
                    <Button> 선택삭제 </Button>
                    <DataGrid
                        rows={rows}
                        columns={columns}
                        initialState={{
                        pagination: {
                        paginationModel: {
                        pageSize: 5,
                        },
                        },
                        }}
                        pageSizeOptions={[5]}
                        checkboxSelection
                        disableRowSelectionOnClick
                        onRowSelectionModelChange={ (newRowSelectionModel) => {
                            setRowSelectionModel(newRowSelectionModel);
                        }}
                    />
                    <h4 className="add-title"> 주소록 등록 </h4>
                    <div className="add-input-box">
                        <TextField sx={{ width: 120 }} label="이름" size="small" type="text" className="addr_name" name="addr_name" />
                        <TextField sx={{ width: 200 }} label="전화번호" size="small" type="text" className="addr_phone" name="addr_phone" />
                        <TextField sx={{ width: 250 }} label="이메일" size="small" type="text" className="addr_email" name="addr_email" />
                        <Button className="addr-add-btn"> + </Button>
                    </div>
                </Box>

            </div>
        </Paper>
    </>)
}