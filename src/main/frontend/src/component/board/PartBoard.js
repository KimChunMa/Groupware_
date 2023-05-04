/* 김동혁 임시 파일임 */
import React,{useState,useEffect,useRef} from 'react'
import axios from 'axios'
export default function PartBoard(props){
    const setPart = () =>{ console.log('setPart')
        let part_name = document.querySelector('.part_name');
        axios.post('/board/part/write' , {"part_name" : part_name.value})
            .then((r)=>{
                if(r.data==true){alert('등록 성공'); part_name.value = ''}
            })
    }
    return(<>
        <h3>임시 부서등록 페이지</h3>
        <input type="text" className="part_name" />
        <button onClick={setPart} type="button"> 부서 등록 </button>
    </>)
}