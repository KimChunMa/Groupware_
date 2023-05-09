import react , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
import styles from './css/main/header.css';

export default function Header( props ) {


    const [ login , setLogin ] = useState(null);
    const [ loginInfo , setLoginInfo ] = useState({}) ;

    // 로그인
    useEffect( () => {
        console.log( )
        axios.get("/login").then( r => {
            console.log( r.data );
            if( r.data != '' ){ // 로그인하였거나 로그인이 되어있는 경우
                sessionStorage.setItem("login_token" , JSON.stringify( r.data ) );
                setLogin( JSON.parse( sessionStorage.getItem("login_token") ) );
            }
        })
    } , [] )


    // 로그아웃
    const logOut = () => {
        // JS 세션 스토리지 초기화
        sessionStorage.setItem("login_token" , null );

        // 백엔드의 인증세션 지우기
        axios.get("/logout")
            .then( r => {
                console.log( r );
            })
        setLogin( null ); // 렌더링
        window.location.href = "/" ;
    }


    return (<>

        <a href="/"> HOME </a>
        <a href="/member/addmember"> 직원등록 </a>
        {/*이경석 링크용*/}
        <a href="/messenger"> 메신저 들어가기</a>
        {/*김동혁 임시 링크용*/}
        <a href="/partBoard">   부서등록 들가기 </a>
        <a href="/list">   게시판 들가기 </a>
        <a href="/approval">  서류작성 </a>
        {/*백한결*/}
        <a href="/reportconfirm">  서류상태확인페이지 </a>

        <a href="/"> LOGIN </a>
        <button onClick={ logOut }> LOGOUT </button>

    </>);
}