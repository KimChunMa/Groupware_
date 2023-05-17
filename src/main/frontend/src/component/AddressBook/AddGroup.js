import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import PropTypes from 'prop-types';
import TreeView from '@mui/lab/TreeView';
import TreeItem, { treeItemClasses } from '@mui/lab/TreeItem';
import { useSpring, animated } from '@react-spring/web';
import {
    Paper , Stack , Alert , Collapse , styled ,
    alpha , SvgIcon , Button , Box , Typography ,
    Modal , TextField
} from '@mui/material'

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};
export default function AddGroup( props ) {

    // 모달 제어 변수 및 메소드
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);


    const groupName = useRef(null);

    useEffect( () => {
        groupGet();
    } , [] )


    const groupGet = () => {
        axios.get("/addressgroup").then( r => {
            console.log( r );
        })
    }

    const groupAdd = () => {
        console.log( "groupAdd 클릭" );
        console.log( groupName.current.value );

        axios.post("/addressgroup" , { groupName : groupName.current.value } ).then( r =>{
            console.log( r );

        })
    }

    // <Alert severity="success"> 성공적으로 주소록이 등록되었습니다! </Alert>
    return (<>
        <Paper elevation={3} style={{ height: "100%" }}>
            <div className="group-add-btnbox">
                <Button variant="contained" size="small" className="group-addBtn" onClick={ handleOpen }> 그룹추가 </Button>
                <Button variant="outlined" size="small"> 그룹삭제 </Button>
            </div>
        </Paper>

        <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Box sx={style}>
                <Typography id="modal-modal-title" variant="h6" component="h2">
                    그룹 등록하기
                </Typography>
                <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    <TextField inputRef={ groupName } name="groupName" label="그룹명" variant="standard" style={{ margin:"20px 0px" }}/>
                    <div>
                        <Button variant="contained" size="small" className="group-addBtn" onClick={ groupAdd }> 등록 </Button>
                        <Button variant="outlined" size="small" onClick={ handleClose }> 취소 </Button>
                    </div>
                </Typography>
            </Box>
        </Modal>

    </>)
}