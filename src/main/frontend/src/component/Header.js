import react from 'react';
import styles from './css/main/header.css';

export default function Header( props ) {
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

    </>);
}