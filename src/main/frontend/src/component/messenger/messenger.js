import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
import '../css/messenger/messenger.css';
import ChatRoom from './ChatRoom';
/* ------------------------ mui ------------------------------*/
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';

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
    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ로그인ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //로그인 정보 객체
    let member = JSON.parse( sessionStorage.getItem("login_token") );

    //로그인 정보 가져오는 함수
    useEffect(()=>{
        axios.get("/login")
            .then(r=>{
            if(r.data.memberNo==0){
                alert('오류!');  window.location.href ="/";
            }else{member = r.data}
            })
    },[])
    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 로그인 끝 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 채팅방 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    // 0.방 만들기 모달 용
    const [modal, setModal] = useState(false);

    // 1-1.채팅방만들시 사용할 제목
    const [title, setTitle] = useState("");

    // 1-2.채팅방 배열
    const [chatRooms , SetChatRooms] = useState([]);

    //1-3. 클릭한 채팅방 번호
    const [roomId , setRoomId] = useState(0);


    // 1-1. 채팅방 만들기 (멤버 id는 어디에 얻어야하는지? MessengerService + messenger)
        //1) 방만드는 아이콘 클릭시 모달나오게하기
    const create_chat = () => { setModal(true);}
        //2) TextField 값 가져오기
    const titleChange = (event) => { setTitle(event.target.value); }
        //3) 전달하기
    const create = () => {
        let ChatRoomsDto = {name:title , memberNo: member.memberNo}
        axios.post("/chat" ,  ChatRoomsDto )
            .then(r => {
                if(r.data == true) {
                alert("방 생성 완료!");setModal(false);
                document.querySelector('#title_input').value ="";
                printChat();
                }else{alert("오류가 발생하였습니다.");}
            })
    }
    //3) 모달 나가기
    const closeModal = () => {setModal(false);}

    //2. 채팅방 출력하기
    const printChat = () => {
          axios.get("/chat")
                .then(r=> {console.log(r);
                SetChatRooms([...r.data]);
                })
    }

    // 1-2. 채팅방 출력하기 (처음 한번)
    useEffect(()=>{ printChat();},[])

    //1-3. 채팅방 클릭시 채팅방번호 수정후 ChatRoom에게 전달
    const clickRooms = (chatRoomId)=> { setRoomId(chatRoomId); }

    return(<>
    <div className="container">
        <div className="wrap">
        {/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ left ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */}
            <div className="left">
                {/* 왼쪽 상단 부분*/}
                <div className="header">
                    <div className="order_chat">채팅 ▼</div>
                    <div className="create_chat" onClick={create_chat}>
                        <FontAwesomeIcon icon={faComments} size="2x" />
                    </div> {/*방만들기 아이콘*/}
                </div>  {/* header e */}

                {/*왼쪽 중단 부분*/}
                <div className="left_content">

                    {chatRooms.map((o)=>(
                        <div className="chat_room" onClick={(e) => clickRooms(o.chatRoomId)}>
                            <div className="chat_room_left">
                                <div className="left_content_img"> 채팅방 이미지 </div>

                                <div className="msg">
                                    <div className="chat_name"> {o.name} </div>
                                    <div className="msg_text"> 최근 메세지 </div>
                                </div>
                            </div>

                            <div className="chat_room_right">
                                <div className="msg_date"> {o.cdate} </div>
                            </div>
                        </div>
                    ))}


                </div>  {/* left_content e */}
            </div> {/* left e */}
            {/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 중앙 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/}

            {/* 클릭한 채팅방 Id, 멤버 정보, 채팅방 배열[클릭한 채팅-1] 배열은0부터 아이디는 1부터, 초기는 0*/}
            <ChatRoom roomId={roomId} member={member} chatRooms={chatRooms[roomId-1]}  />

            {/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 오른쪽 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/}
            <div className="right">
                오른쪽
            </div> {/* right e */}


        </div>
    </div>

    {/* 모달 창*/}
	<div className="modal_wrap" style={modal ? {display:'block'} : {display:'none'}} >
		<div className="modal_box">
			<h3 className="modal_title">
                방만들기
			</h3>
			<div className="modal_content">
			     <TextField onChange={titleChange} id="title_input" label="채팅방 제목" variant="standard"  />
			</div>

			<div className="modal_btns">
				<button  onClick={create}  className="modal_check" type="button">방 생성하기</button>
				<button onClick={closeModal} className="modal_cencel" type="button">닫기</button>
			</div>
		</div>
	</div>

    </>)
}