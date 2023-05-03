import React , { useState , useEffect } from 'react';
import axios from 'axios';
import '../css/messenger/messenger.css';


/* ------------------------폰트 어썸--------------------------
npm i --save @fortawesome/fontawesome-svg-core
npm install --save @fortawesome/free-solid-svg-icons
npm install --save @fortawesome/react-fontawesome
npm i @fortawesome/free-solid-svg-icons @fortawesome/free-regular-svg-icons @fortawesome/free-brands-svg-icons
npm i @fortawesome/react-fontawesome
*/
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
//     {사용할 아이콘(카라멜)} from "@fortawesome/free-(regular, solid)-svg-icons";
import { faComments } from "@fortawesome/free-regular-svg-icons";


export default function Messenger(props){
    return(
    <div className="container">
        <div className="wrap">
            <div className="left">
                <div className="left_header">
                    <div className="order_chat">채팅 ▼</div>
                    <div className="create_chat">
                        <FontAwesomeIcon icon={faComments} size="xm" />
                    </div> {/*방만들기 아이콘*/}
                </div>  {/* header e */}

                <div className="left_content">
                    <div className="left_content_img"> 채팅방 이미지 </div>

                    <div className="msg">
                        <div className="chat_name"> 채팅이름 </div>
                        <div className="msg_text"> 최근 메세지 </div>
                    </div>

                    <div className="msg_date"> 2023.03.03 </div>
                </div>  {/* left_content e */}

            </div> {/* left e */}

            <div className="center">
                <div className="header">
                    중앙 헤더
                 </div>
                 <div className="chat_room">
                 채팅방
                </div>
            </div> {/* center e */}

            <div className="right">
                오른쪽
            </div> {/* right e */}


        </div>
    </div>
    )
}