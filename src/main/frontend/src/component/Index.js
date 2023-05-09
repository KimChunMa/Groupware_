import React from 'react';
import { BrowserRouter , Routes , Route , Outlet } from 'react-router-dom';

import Main from './Main';

import Header from './Header';

// ---------------------- 김동혁 ---------------------------------//
import List from './board/List';
import Write from './board/Write';
import PartBoard from './board/PartBoard';

// -------------- 멤버 - 김성봉 --------------- //
import Home from './Home'
import Login from './member/Login'
import AddMember from './member/AddMember';


//----------------------- 이경석 ---------------------------------//
import Messenger from './messenger/messenger';

//-----------------------백한결----------------------------------//
import Approval from './approval/approval'; //휴가계작성  [2023-05-04]
import Reportconfirm from './approval/Reportconfirm'; //레포트상태확인  [2023-05-09]
//-----------------------백한결 [2023-05-04]----------------------------------//

/*
    react-router-dom 다양한 라우터 컴포넌트 제공
    1. <BrowserRouter>  : 가상 URL 관리 [ 브라우저 URL 동기화 ]
    2. <Routes>         : 가장 적합한 <Route> 컴포넌트를 검토하고 찾는다.
            요청된 path 에 적합한 <Route> 찾아서 <Routes> 범위내 렌더링
    3. <Route>          : 실제 URL 경로를 지정해주는 컴포넌트
        <Route path="login" element={ <Login /> } />
        http://localhost:3000/login     get 요청시 Login 컴포넌트 반환
*/


export default function Index( props ){
    return (<>
         <BrowserRouter>

            <Header />

            <Routes>
                { /* 멤버 */ }
                <Route path="/member/addmember" element={ <AddMember /> } />
                <Route path="/" element={ <Main /> } />
                <Route path="/messenger" element={ <Messenger /> } /> {/*이경석 */}
                <Route path="/approval" element={ <Approval /> } /> {/*백한결 */}
                <Route path="/list" element={ <List /> } /> {/*김동혁 */}
                <Route path="/write" element={ <Write /> } /> {/*김동혁 */}
                <Route path="/partboard" element={ <PartBoard /> } /> {/*김동혁 */}
            </Routes>

         </BrowserRouter>
    </>);
}

