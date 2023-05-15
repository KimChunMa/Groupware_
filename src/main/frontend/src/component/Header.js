import react , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
import FileSaver from "file-saver";
import styles from './css/main/header.css';

import SideBar from './SideBar';

export default function Header( props ) {

    const [ login , setLogin ] = useState(null);
    const [ loginInfo , setLoginInfo ] = useState({}) ;
    const [ imageUrl , setImageUrl ] = useState("");

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

    const loginToken = JSON.parse(sessionStorage.getItem("login_token"));


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

    // 로그인한사람(세션)의 프로필 이미지 가져오기
    const getProfileImg = () => {

        const uuidFilename = login.uuidFilename ; // 로그인된 사람의 실제 이미지파일이름
        console.log( uuidFilename );

        if( uuidFilename == null ){ // 만약에 이미지파일을 등록하지 않은 사람인 경우
            alert("등록된 이미지 파일이 없습니다");
            return ;
        }

        // 서버로부터 이미지를 찾아 Blob 으로 가져오기
        axios({

                url: `/image/${uuidFilename}`,  // 이미지 파일 이름을 포함한 API 엔드포인트
                method: 'GET',
                responseType: 'arraybuffer',   // 바이너리 데이터로 받기 위해 responseType을 설정

            }).then(response => {

                console.log( response.data );
                const contentType = response.headers['content-type']; // 응답받은값의 헤더에 컨텐츠타입을 호출 // 예) : 'image/png'
                console.log(contentType);

                const imageBlob = new Blob([response.data], {type: contentType });  // 바이너리 데이터를 Blob 객체로 변환
                const imageUrl = URL.createObjectURL(imageBlob);  // Blob URL을 생성하여 이미지를 렌더링할 수 있는 URL을 만듦
                console.log( imageUrl );

                setImageUrl( imageUrl ); // 상태변수에 Blob 경로 대입

        }).catch(error => {
            console.error(error);
        });
    }



    return (<>
        <SideBar />
        <a href="/home"> HOME </a>
        <a href="/member/addmember"> 직원등록 </a>
        <a href="/addressbook"> 주소록 </a>
        {/*이경석 링크용*/}
        <a href="/messenger"> 메신저 들어가기</a>
        {/*김동혁 임시 링크용*/}
        <a href="/partBoard">   부서등록 들가기 </a>
        <a href="/list">   게시판들가기 </a>
        <a href="/approval">  서류작성 </a>
        {/*백한결*/}
        <a href="/reportconfirm">  서류상태확인페이지 </a>
        <a href="/approval/myapproval">  내 서류상태확인 </a>

        <a href="/"> LOGIN </a>
        <button onClick={ logOut }> LOGOUT </button>
        <button onClick={ getProfileImg }> 이미지 테스트 </button>

        <img src={ imageUrl } />




    </>);
}