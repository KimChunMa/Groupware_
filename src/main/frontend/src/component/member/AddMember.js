import react , { useState , useEffect , useRef } from 'react';
import axios from 'axios';
import styles from '../css/member/AddMember.css'

// MUI ----------------------------
import {
    Container , TextField , Select , SelectChangeEvent , InputLabel ,
    MenuItem , FormControl , Button
} from '@mui/material';


export default function AddMember( props ) {


    const [ rank , setRank ] = useState('');
    const [ part , setPart ] = useState('');

    let memberInfo = useRef(null);



    const addMember = () => {
        console.log('addMember');
        console.log( memberInfo.current );
        console.log( memberInfo.current.member_id.value );

        let formData = new FormData( memberInfo.current );

        axios.post("/member/add", formData ).then( (r) => {
            console.log( r );
        })

    }


    const handleChange = (event: SelectChangeEvent) => {
        setRank(event.target.value);
    };
    const handleChangePart = (event: SelectChangeEvent) => {
        setPart(event.target.value);
    };

    return (<>
        <Container className="addMember-wrap">
            <form ref={ memberInfo }>
                <div className="addMember-mainBox">
                    <div className="addMember-itemBox">
                        <FormControl className="input_rank">
                            <InputLabel> 부서선택 </InputLabel>
                            <Select
                              value={ part }
                              label="부서"
                              onChange={ handleChangePart }
                              name="member_rank"
                            >
                              <MenuItem value={1}> 기술운영 </MenuItem>
                              <MenuItem value={2}> 영업 </MenuItem>
                              <MenuItem value={3}> 경영지원 </MenuItem>
                              <MenuItem value={4}> 사업 </MenuItem>
                            </Select>
                        </FormControl>
                    </div>
                    <div className="addMember-itemBox">
                        <TextField label="사원명" type="text" className="input_name" name="member_name"/>
                        <FormControl className="input_rank">
                            <InputLabel> 직급 </InputLabel>
                            <Select
                              value={ rank }
                              label="직급"
                              onChange={ handleChange }
                              name="member_rank"
                            >
                              <MenuItem value={1}> 사원 </MenuItem>
                              <MenuItem value={2}> 주임 </MenuItem>
                              <MenuItem value={3}> 대리 </MenuItem>
                              <MenuItem value={4}> 과장 </MenuItem>
                              <MenuItem value={5}> 차장 </MenuItem>
                              <MenuItem value={6}> 팀장 </MenuItem>
                              <MenuItem value={7}> 부장 </MenuItem>
                              <MenuItem value={9}> 대표이사 </MenuItem>
                            </Select>
                        </FormControl>
                    </div>
                    <div className="addMember-itemBox">
                        <TextField label="아이디" type="text" className="input_id" name="member_id" />
                        <TextField label="패스워드" type="password" className="input_pwd" name="member_pwd" />
                    </div>
                    <div className="addMember-itemBox">
                        <TextField label="연락처" type="text" className="input_phone" name="member_phone" />
                    </div>
                    <div className="addMember-itemBox">
                        <TextField label="이메일" type="text" className="input_email" name="member_email" />
                    </div>
                    <div className="addMember-itemBox imgBox">
                        <div className="input_btn_box">
                            <TextField type="file" className="input_profile" name="member_profile" />
                            <div className="btnBox">
                                <Button variant="contained" onClick={ addMember }> 등록 </Button>
                                <Button variant="outlined"> 취소 </Button>
                            </div>
                        </div>
                        <div className="img-view"> 이미지 미리보기 </div>
                    </div>
                </div>
            </form>
        </Container>
    </>);
}