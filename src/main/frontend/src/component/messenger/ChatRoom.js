import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
//   implementation 'org.springframework.boot:spring-boot-starter-websocket' //web 소켓 builder

export default function Messenger(props){
    //1.메세지 보내기 DOM
    let msgInput = useRef(null);
    console.log(props.roomId) //채팅방 번호

    //1. 메세지 보내기
    const sendMessages = () =>{
        console.log(msgInput.current.value);
        let chatMessagesDto = {
            content: msgInput.current.value,
            memberNo: props.member.memberNo,
            chatRoomId: props.roomId,
            msgType: "msg"
        }
        console.log(chatMessagesDto);
        axios.post('/chat/message', chatMessagesDto)
            .then(r=>{console.log(r)})
    }







    let [msgContent, setMsgContent ] = useState([]);

    //5. 렌더링 할때마다 스크롤 가장 하단으로 내리기
    useEffect ( () => {
    document.querySelector('.in_chat_room').scrollTop=
        document.querySelector('.in_chat_room').scrollHeight;
    },[msgContent])



    return (<>
                {/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 중앙 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/}
                <div className="center">
                    <div className="header">
                        <span className="chat_name center_chat_name"> 중앙 헤더 </span>
                    </div>

                    <div className="in_chat_room">
                        <div className="chat_date">2023.05.04 </div>

                        <div className="your_message messagebox">
                            <div className="your_profile_img"> img </div>
                            <div className="message"> asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</div>
                        </div>

                        <div className="me_message messagebox">
                            <div className="message"> asdf </div>
                            <div className="your_profile_img"> img </div>
                        </div>

                         <div className="your_message messagebox">
                            <div className="your_profile_img"> img </div>
                            <div className="message"> asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdf</div>
                        </div>

                         <div className="me_message messagebox">
                            <div className="message"> asdf </div>
                            <div className="your_profile_img"> img </div>
                         </div>
                     </div>


                    {/* --------------------- 메세지 입력 창 ------------------------ */}
                    <div className="message_send_box">
                        <div className="file">
                          <div className="btn-upload">파일 </div>
                        </div>
                        <form>
                            <input type="file" name="file" id="file"/>
                        </form>
                        <input type="text" className="message_input" ref={msgInput} />
                        <button type="button" className="message_btn" onClick={sendMessages}> 전송 </button>
                    </div>
                </div> {/* center e */}
    </>)
}