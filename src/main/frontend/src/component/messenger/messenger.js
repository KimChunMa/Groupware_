import React , { useState , useEffect } from 'react';
import axios from 'axios';
import '../css/messenger/messenger.css';
import { comments } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/regular-fontawesome"

/* 폰트 어썸
npm i --save @fortawesome/fontawesome-svg-core
npm install --save @fortawesome/free-solid-svg-icons
npm install --save @fortawesome/react-fontawesome
*/

export default function Messenger(props){
    return(
    <div className="container">
        <div className="wrap">
            <div className="left">
                <div className="left_header">
                    <div className="order_chat">채팅 ▼</div>
                    <div className="create_chat">
                    <FontAwesomeIcon icon="fa-regular fa-comments" />
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
            </div> {/* right e */}


        </div>
    </div>
    )
}