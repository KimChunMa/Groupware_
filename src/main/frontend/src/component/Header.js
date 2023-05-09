import react , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
import styles from './css/main/header.css';

export default function Header( props ) {


    const [ login , setLogin ] = useState({});
    const [ loginInfo , setLoginInfo ] = useState({}) ;

    // 로그인
    useEffect( () => {
        console.log( )
        axios.get("/login").then( r => {
            console.log( r.data );
            setLoginInfo( r.data );
        })
    } , [] )

    // 로그아웃


    return (<>
        <a href="/home"> HOME </a>
        <a href="/member/addmember"> 직원등록 </a>
        {/*이경석 링크용*/}
        <a href="/messenger"> 메신저 들어가기</a>
        {/*김동혁 임시 링크용*/}
        <a href="/partBoard">   부서등록 들가기 </a>
        <a href="/list">   게시판 들가기 </a>
        <a href="/"> LOGIN </a>
    </>);
}