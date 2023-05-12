import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import { Drawer , Box , Typography , Button , IconButton } from "@mui/material"
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import MenuIcon from '@mui/icons-material/Menu'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import TreeView from '@mui/lab/TreeView';
import TreeItem from '@mui/lab/TreeItem';


export default function SideBar( props ) {

    // 사이드바 open , close 상태관리 변수
    const [ isDrawerOpen , setIsDrawerOpen ] = useState( false );

    const [expanded, setExpanded] = React.useState([]);

    const handleToggle = (event, nodeIds) => {
        setExpanded(nodeIds);
    };

    const handleExpandClick = () => {
        setExpanded((oldExpanded) =>
            oldExpanded.length === 0 ? ['1', '5', '6', '7'] : [],
        );
    };

    const handleClick = ( event , nodeIds ) => {

        switch( nodeIds ) {
            case '2' : window.location.href = "/member/memberlist" ;
            break ;
            case '3' : window.location.href = "/member/addpart" ;
            break ;
            case '4' : window.location.href = "/member/addmember" ;
            break ;
        }

    }

    return( <>
        <IconButton
            size='large'
            edge='start'
            color='inherit'
            aria-label='logo'
            onClick={ () => setIsDrawerOpen( true ) }
        >
            <MenuIcon />
        </IconButton>
        <Drawer
            anchor="left"
            open={ isDrawerOpen }
            onClose={ () => setIsDrawerOpen(false) }
        >
            <Box p={2} width='400px' textAlign='center' role='presentation'>

                <Typography variant='h6' component='div'>
                    Connect, @
                </Typography>

                <div>
                    직원 프로필이 표시될 자리
                </div>

                <Box sx={{ height: '100%', flexGrow: 1, maxWidth: 400, overflowY: 'auto' }}>
                    <Box sx={{ mb: 1 }}>
                        <Button onClick={handleExpandClick}>
                            {expanded.length === 0 ? '전체보기' : '간략히 보기'}
                        </Button>
                    </Box>
                    <TreeView
                        aria-label="controlled"
                        defaultCollapseIcon={<ExpandMoreIcon />}
                        defaultExpandIcon={<ChevronRightIcon />}
                        expanded={expanded}
                        onNodeToggle={handleToggle}
                        multiSelect
                    >
                        <TreeItem nodeId="1" label="직원관리">
                            <TreeItem nodeId="2" label="직원정보" onClick={ (event) => handleClick( event , "2") }/>
                            <TreeItem nodeId="3" label="부서관리" onClick={ (event) => handleClick( event , "3") }/>
                            <TreeItem nodeId="4" label="직원등록" onClick={ (event) => handleClick( event , "4") }/>
                        </TreeItem>
                        <TreeItem nodeId="5" label="Documents">
                            <TreeItem nodeId="6" label="MUI">
                                <TreeItem nodeId="7" label="src">
                                    <TreeItem nodeId="8" label="index.js" />
                                    <TreeItem nodeId="9" label="tree-view.js" />
                                </TreeItem>
                            </TreeItem>
                        </TreeItem>
                    </TreeView>
                </Box>
            </Box>
        </Drawer>
    </>)

}