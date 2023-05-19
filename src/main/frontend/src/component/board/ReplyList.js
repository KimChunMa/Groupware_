import React,{useState,useEffect} from 'react'
import axios from 'axios'

export default function ReplyList(props) {
    const onWriteHandler = ()=>{
        console.log(document.querySelector('.replyContent').value );
        props.onReplyWrite(document.querySelector('.replyContent').value);
    }

    console.log( props );

    return (<>
        <input className="replyContent" type="text"/> <button onClick={onWriteHandler}> 댓글작성 </button>
        <h3> 댓글 목록 </h3>
                {   props.replies.map( (r)=>{
                        return (<div>
                                    <span>{r.replyContent}</span>
                                    <span>{r.replyDate}</span>
                                </div>)
                    })
                }
    </>)
}