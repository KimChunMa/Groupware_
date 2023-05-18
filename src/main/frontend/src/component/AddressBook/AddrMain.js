import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import styles from '../css/addressbook/AddrMain.css';
import AddAddressBook from './AddAddressBook';
import AddGroup from './AddGroup';

import { Container , Alert } from "@mui/material"

export default function AddrMain( props ) {

    // Alert 상태변수
    const [showAlert, setShowAlert] = useState(false);
    const [showfailAlert, setFailAlert] = useState(false);

    const [ selectedId, setSelectedId ] = useState(null);

    const getId = ( selectedId ) => {
        setSelectedId( selectedId );
    }


    // 하위 컴포넌트에서 Alert 코드 실행 시, 해당 컴포넌트에서 렌더링 되므로 상위 컴포넌트에서 렌더링하기 위해 props 전달
    const alertSet = () => {
        // 상태변수를 true 로 변경 >> 아래의 Alert 실행
        setShowAlert( true );

        // 이후 해당 알럿이 사라지게하기 위해 3초뒤 false 로 다시 변경
        setTimeout( () => {
            setShowAlert(false);
        }, 3000);
    }

    const failAlert = () =>{
        setFailAlert( true );
        setTimeout( () => {
            setFailAlert(false);
        }, 3000);
    }


    return (<>
        { /* showAlert 상태변수값이 true 인 경우에만 해당 코드 렌더링 */ }
        { showAlert && (
            <Alert variant="filled" severity="success" className="alert-position">요청하신 작업이 완료되었습니다.</Alert>
        )}
        { showfailAlert && (
            <Alert variant="filled" severity="error" className="alert-position">작업에 실패하였습니다.</Alert>
        )}
        <Container>
            <div className="addressbook-wrap">
                <h3 className="addr-title"> 주소록 관리 </h3>
                <div className="addr-box">
                    <div className="addr-group">
                        <AddGroup
                            getId={ getId }
                            alertSet={ alertSet }
                            failAlert={ failAlert }
                        />
                    </div>
                    <div className="addr-addressbook">
                        <AddAddressBook selectedId={ selectedId } />
                    </div>
                </div>
            </div>
        </Container>
    </>)
}