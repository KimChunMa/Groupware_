import React,{useState,useEffect} from 'react'
import axios from 'axios'

export default function ReplyList(props) {

    const [ login , setLogin ] = useState( JSON.parse( sessionStorage.getItem('login_token') ) )
    const [ replies , setReplies ] = useState([])

    // [김동혁]댓글 작성 핸들러
    const onWriteHandler = ()=>{
        console.log(document.querySelector('.replyContent').value );
        props.onReplyWrite(document.querySelector('.replyContent').value);
    }

    // [김동혁]댓글 수정 핸들러
    const onUpdateHandler = (e , replyNo)=>{
        console.log('수정할 댓글 번호'+replyNo);
        props.onReplyUpdate(document.querySelector('.replyUpdateContent'+replyNo).value,replyNo);
        document.querySelector('.replyUpdateContent'+replyNo).value = '';
    }
    console.log( props );

    // [김동혁] 댓글 삭제 핸들러
    const onDeleteHandler = (e, replyNo)=>{
        console.log('삭제할 댓글 번호 : ' +replyNo);
        props.onReplyDelete(replyNo);
    }

    return (<>
        <input className="replyContent" type="text"/> <button onClick={onWriteHandler}> 댓글작성 </button>
        <h3> 댓글 목록 </h3>
                {   props.replies.map( (r)=>{
                        return (<div>
                                    <div>
                                        <span>{r.memberName}</span>
                                        <span>{r.replyDate}</span>
                                    </div>
                                        <span>{r.replyContent}</span>
                                    <span>{ (login != null && login.memberNo == r.memberNo) ?
                                        <>
                                            <input className={ 'replyUpdateContent'+(r.replyNo) } type="text" />
                                            <button onClick={(e)=>onDeleteHandler(e,r.replyNo)}>삭제</button>
                                            <button onClick={(e)=>onUpdateHandler(e,r.replyNo)}>수정</button>
                                        </> :
                                        <></> }
                                    </span>
                                </div>)
                    })
                }
    </>)
}