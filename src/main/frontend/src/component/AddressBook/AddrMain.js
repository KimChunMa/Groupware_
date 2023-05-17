import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';

import styles from '../css/addressbook/AddrMain.css';
import AddAddressBook from './AddAddressBook';
import AddGroup from './AddGroup';

import { Container } from "@mui/material"

export default function AddrMain( props ) {


    return (<>
        <Container>
            <div className="addressbook-wrap">
                <h3 className="addr-title"> 주소록 관리 </h3>
                <div className="addr-box">
                    <div className="addr-group">
                        <AddGroup />
                    </div>
                    <div className="addr-addressbook">
                        <AddAddressBook />
                    </div>
                </div>
            </div>
        </Container>
    </>)
}