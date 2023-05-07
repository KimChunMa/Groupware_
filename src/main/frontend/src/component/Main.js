import React , { useState , useEffect } from 'react';
import { BrowserRouter , Routes , Route , Outlet } from 'react-router-dom';
import styles from './css/main/main.css';
// --------------------------- //

import Header from './Header';

export default function Main( props ){

    return (<>
        <Header />
        <Outlet />
    </>);
}