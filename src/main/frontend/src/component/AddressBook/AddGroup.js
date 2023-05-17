import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import PropTypes from 'prop-types';
import TreeView from '@mui/lab/TreeView';
import TreeItem, { treeItemClasses } from '@mui/lab/TreeItem';
import { useSpring, animated } from '@react-spring/web';
import { Paper , Stack , Alert , Collapse , styled , alpha , SvgIcon , Button } from '@mui/material'


export default function AddGroup( props ) {


    // <Alert severity="success"> 성공적으로 주소록이 등록되었습니다! </Alert>
    return (<>
        <Paper elevation={3} style={{ height: "100%" }}>
            <div className="group-add-btnbox">
                <Button variant="contained" size="small" className="group-addBtn"> 그룹추가 </Button>
                <Button variant="outlined" size="small"> 그룹삭제 </Button>
            </div>

        </Paper>
    </>)
}