import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import styles from '../css/member/memberList.css';

import { DataGrid } from '@mui/x-data-grid';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import SendIcon from '@mui/icons-material/Send';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import TextField from '@mui/material/TextField';




const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 500,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

export default function MemberList( props ) {


    const [ selectMember , setSelectedMember ] = useState('');

    // 정보수정 렌더링 상태변수
    const[ editInfo , setEditInfo ] = useState({});


    // 모달 제어 변수
    const [open, setOpen] = React.useState(false);

    const handleOpen = ( event , params ) => {

        // renderCell 함수는 params 로 값을 받고 해당 값을 살펴보면 row 라는 데이터가 들어있다.
        console.log( event );
        console.log( params );
        console.log( params.row.memberId );
        setSelectedMember( params.row.memberId );
        setEditInfo( params.row );
        setOpen(true);
    }
    const handleClose = () => setOpen(false);



    // 멤버리스트 테이블 제어 변수
    const [ rows , setRows ] = useState([]);
    const [ rowSelectionModel, setRowSelectionModel ] = React.useState([]);
    console.log( rowSelectionModel );

    const updateMember = useRef(null);

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


    // 수정 텍스트필드 렌더링을 위한 객체 생성 함수
    const infoEdit = () =>{ // form 내부 텍스트 필드가 체인지 됐을 경우

        console.log('테스트입니다.')
        console.log( updateMember.current );
        console.log( updateMember.current.memberName );
        console.log( updateMember.current.memberName.value );
        // useRef 활용 텍스트필드의 value 값을 가져온다

        let newInfo = { // 새로운 객체에 각 텍스트필드 입력값을 체인지 마다 받아와 저장하고
            memberNo : editInfo.memberNo,
            memberName : updateMember.current.memberName.value,
            memberPwd : updateMember.current.memberPwd.value,
            memberPwdConfirm : updateMember.current.memberPwdConfirm.value,
            memberPhone : updateMember.current.memberPhone.value,
            memberEmail : updateMember.current.memberEmail.value
        }

        // useState 상태변수에 대입하여 렌더링
        setEditInfo( newInfo );

    }

    const memberUpdate = () => {
        console.log('업데이트')
        console.log( editInfo );

        console.log( updateMember.current );
        console.log( updateMember.current.memberName.value );

        let formData = new FormData( updateMember.current );

        axios.post("/member/update" , { memberNo : 1 } ).then( r => {
            console.log( r );
        })
    }

    const columns = [
            { field: 'id', headerName: '번호', width: 100 },
            { field: 'memberId', headerName: '아이디', width: 100 },
            { field: 'partName', headerName: '부서', width: 100 },
            { field: 'memberName', headerName: '사원명', width: 100 },
            { field: 'memberPhone', headerName: '휴대폰', width: 100 },
            { field: 'memberEmail', headerName: '이메일', width: 100 },
            { field:
                'data' ,
                headerName : '수정' ,
                width: 100 ,
                // 수정버튼 [ 모달 ]
                renderCell : (params) => (
                    <div>
                      <Button onClick={ (event) => handleOpen( event , params ) }> 수정 </Button>
                      <Modal
                        open={open}
                        onClose={handleClose}
                        aria-labelledby="modal-modal-title"
                        aria-describedby="modal-modal-description"
                      >
                        <Box sx={style}>
                          <Typography id="modal-modal-title" variant="h6" component="h2">
                            회원 정보 수정
                          </Typography>
                          <div>
                            <TextField
                                id="memberId"
                                defaultValue={ selectMember }
                                variant="standard"
                                disabled
                            />
                            <form ref={ updateMember } onChange={ infoEdit }>
                                <TextField id="memberName" value={ editInfo.memberName } label="이름" variant="standard" />
                                <TextField id="memberPwd" value={ editInfo.memberPwd } label="비밀번호" variant="standard" />
                                <TextField id="memberPwdConfirm" value={ editInfo.memberPwdConfirm } label="비밀번호 확인" variant="standard" />
                                <TextField id="memberPhone" value={ editInfo.memberPhone } label="연락처" variant="standard" />
                                <TextField id="memberEmail" value={ editInfo.memberEmail } label="이메일" variant="standard" />
                                <TextField type="file" className="input_profile" name="memberProfile" />
                            </form>
                          </div>
                          <div className="modiBtnBox">
                            <Button onClick={ memberUpdate }> 수정 </Button>
                            <Button onClick={ handleClose }> 취소 </Button>
                          </div>
                        </Box>
                      </Modal>
                    </div>
                )
            }
        ];

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