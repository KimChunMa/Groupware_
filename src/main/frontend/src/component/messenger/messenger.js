import React , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
import '../css/messenger/messenger.css';

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
    // 0.방 만들기 모달 용
    const [modal, setModal] = useState(false);

    // 1-1.채팅방만들시 사용할 제목
    const [title, setTitle] = useState("");

    // 1-2.채팅방 배열
    const [chatRooms , SetChatRooms] = useState([]);


    //메세지 출력창
    const [msgContent, setMsgContent ] = useState([]);



    //채팅 입력창 DOM 객체 제어 변수
    let msgInput = useRef(null);

    // 1-1. 방만들기 (멤버 id는 어디에 얻어야하는지? MessengerService + messenger)
        //1) 방만드는 아이콘 클릭시 모달나오게하기
    const create_chat = () => { setModal(true);}
        //2) TextField 값 가져오기
    const titleChange = (event) => { setTitle(event.target.value); }
        //3) 전달하기
    const create = () => {
        let chat_room = {  name:title }
        axios.post("/chat" , {params : {name:title}})
            .then(r => {
                if(r.data == true) {
                alert("방 생성 완료!");setModal(false);
                document.querySelector('#title_input').value ="";
                }else{alert("오류가 발생하였습니다.");}
            })
    }
        //3) 모달 나가기
    const closeModal = () => {setModal(false);}

    // 1-2. 채팅방 출력하기
    useEffect(()=>{
        axios.get("/chat")
            .then(r=> {console.log(r);
            SetChatRooms([...r.data]);
            })
    },[])

    console.log(chatRooms);


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
                        <div className="chat_room">
                            <div className="chat_room_left">
                                <div className="left_content_img"> 채팅방 이미지 </div>

                                <div className="msg">
                                    <div className="chat_name"> {o.name} </div>
                                    <div className="msg_text"> 최근 메세지 </div>
                                </div>
                            </div>

                            <div className="chat_room_right">
                                <div className="msg_date"> 2023.03.03 </div>
                            </div>
                        </div>
                    ))}
                })

                </div>  {/* left_content e */}
            </div> {/* left e */}

            {/*ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 중앙 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/}
            <div className="center">
                <div className="header">
                    <span className="chat_name center_chat_name"> 중앙 헤더 </span>
                 </div>

                <div className="in_chat_room">
                    <div className="chat_date">2023.05.04 </div>
                </div>

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

                {/* --------------------- 메세지 입력 창 ------------------------ */}
                <div className="message_send_box">
                    <div className="file">
                      <div className="btn-upload">파일 </div>
                    </div>
                    <form>
                        <input type="file" name="file" id="file"/>
                    </form>
                    <input type="text" className="message_input" ref={msgInput} />
                    <button type="button" className="message_btn"> 전송 </button>
                </div>
            </div> {/* center e */}


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