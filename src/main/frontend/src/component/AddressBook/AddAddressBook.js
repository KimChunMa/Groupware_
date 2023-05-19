import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import { Paper , Button , Box , TextField } from '@mui/material'
import { DataGrid } from '@mui/x-data-grid';



export default function AddAddressBook( props ) {

    // 테이블 구성 요소
    const columns = [
      { field: 'id', headerName: '번호', width: 90 },
      { field: 'addrName', headerName: '이름', width: 100 },
      { field: 'addrPhone', headerName: '전화번호', width: 150 },
      { field: 'addrEmail', headerName: '이메일', width: 150 }
    ];


    const [ rowSelectionModel, setRowSelectionModel ] = React.useState([]);
    const [ addrBookList , setAddressBookList ] = useState([]);

    console.log( rowSelectionModel );
    console.log( props.addrGroupList );

    console.log( props.selectedId + " 그룹으로부터 받은 선택된 그룹아이디" );

    const addressAdd = useRef(null);

    // 주소록 등록하기 메소드 ( axios )
    const addressbookAdd = () => {
        console.log( props.selectedId + "등록 버튼 실행");
        console.log( addressAdd.current );

        if( props.selectedId == undefined || props.selectedId == 0 ) {
            alert("그룹을 선택해주세요, 그룹선택 후 주소록 등록이 가능합니다.")
            return;
        }

        let formData = new FormData( addressAdd.current );
        formData.append('groupNo' , props.selectedId );

        axios.post("/addressbook" , formData ).then( r => {
            console.log( r.data );
            if( r.data == 0 ){
                props.groupGet();
                props.alertSet();
                // 사용자 입력값 초기화
                addressAdd.current.reset();
            }
        })
    }

    useEffect( () => {
        getAddressBook();
    } , [props.addrGroupList])

    const getAddressBook = () => {

        let listArray = [];
        setAddressBookList( listArray );

        props.addrGroupList.forEach( (group) => {
            if( group.groupNo == props.selectedId ){
                group.addressBookDtoList.forEach( (list) => {
                    list.id = list.addrNo ;
                    listArray.push( list );
                    setAddressBookList( [...listArray] );
                })
            }
        })
    }

    useEffect( () => {
        console.log( "addressbook useEffect 실행" )
        getAddressBook();
    } , [ props.selectedId ] )


    // 선택한 주소록 삭제하기 [] 배열을 반복문 돌려 체크박스에 체크된 row 값
    const addrDelete = () => {
        rowSelectionModel.forEach( (row) => {
            axios.delete("/addressbook" , { params: { addrNo : row } } ).then( r => {
                console.log( r.data );
                if( r.data ){
                    props.groupGet();
                    props.alertSet();
                }
            })
        })
    }

    return (<>
        <Paper elevation={3} style={{ height: "100%" , marginLeft: '9px'}}>
            <h4 className="addr-add-title"> 주소록 목록 </h4>
            <div>
                <Box sx={{ height: 400, width: '95%' , margin: '0px auto'}}>
                    <Button> 수정 </Button>
                    <Button onClick={ addrDelete }> 선택삭제 </Button>
                    <DataGrid
                        rows={ addrBookList }
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
                        <form ref={ addressAdd } className="addressAdd-form">
                            <TextField sx={{ width: 150 }} label="이름" size="small" type="text" className="addr_name" name="addrName" />
                            <TextField sx={{ width: 200 }} label="전화번호" size="small" type="text" className="addr_phone" name="addrPhone" />
                            <TextField sx={{ width: 250 }} label="이메일" size="small" type="text" className="addr_email" name="addrEmail" />
                            <Button className="addr-add-btn" onClick={ addressbookAdd }> + </Button>
                        </form>
                    </div>
                </Box>
            </div>
        </Paper>
    </>)
}