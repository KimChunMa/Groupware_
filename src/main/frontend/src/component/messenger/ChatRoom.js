import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
//   implementation 'org.springframework.boot:spring-boot-starter-websocket' //web 소켓 builder

export default function Messenger(props){
    //0. 로그인 객체 정보
   	const member = props.member;
    //1.메세지 보내기 DOM
    let msgInput = useRef(null);
    //2-1. 클릭한 채팅방 번호 (messenger 1-3 동일)
    const [roomId2 , setRoomId2] = useState(0);
    //2-2. 현재 채팅방 전체 메세지
   	const [msgContent, setMsgContent ] = useState([]);


    //1. 메세지 보내기
    const sendMessages = () =>{
        let chatMessagesDto = {
            content: msgInput.current.value,
            memberNo: props.member.memberNo,
            chatRoomId: props.roomId,
            msgType: "msg"
        }
        axios.post('/chat/message', chatMessagesDto)
            .then(r=>{})
    }

    //2-1. 채팅방클릭시 채팅방 번호 수정
    useEffect(()=>{ setRoomId2(props.roomId)},[props.roomId])

    //2-2. 채팅방 클릭시 메세지 출력  (roomId2를 props.roomId로 수정 가능 )
    useEffect(()=>{
        if(roomId2 > 0){
        axios.get("/chat/message", {params:{"chatRoomId":roomId2}})
            .then(r=>{setMsgContent(r.data);})
        }
    },[roomId2])

    //2-3. 렌더링 할때마다 스크롤 가장 하단으로 내리기
    useEffect ( () => {
    document.querySelector('.in_chat_room').scrollTop=
        document.querySelector('.in_chat_room').scrollHeight;
    },[msgContent])

    console.log(msgContent)

    return (<>
                {/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 중앙 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/}
                <div className="center">
                    <div className="header">
                        <span className="chat_name center_chat_name">
                            {props.chatRooms != undefined || props.chatRooms != null ?
                             props.chatRooms.name : '' }
                        </span>
                    </div>

                    <div className="in_chat_room">
                    {
                         msgContent.map((o)=>{
                            <div className="chat_date">2023.05.04 </div>
                            {
                                if(o.memberNo != member.memberNo ){
                                    return(<>
                                        <div className="your_message messagebox">
                                            <div className="your_profile_img"> img </div>
                                            <div className="message"> {o.content} </div>
                                        </div>
                                    </>);
                               }else if( o.memberNo == member.memberNo ){
                                    return(<>
                                         <div className="me_message messagebox">
                                             <div className="message"> {o.content}</div>
                                             <div className="your_profile_img"> img </div>
                                         </div>
                                     </>)
                               }
                            }
                        })
                    }
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
                </div>  {/* center e */}
    </>)
}