import react , { useState , useEffect } from 'react';
import styles from '../css/member/AddMember.css'

// MUI ----------------------------
import {
    Container , TextField , Select , SelectChangeEvent , InputLabel ,
    MenuItem , FormControl , Button
} from '@mui/material';


export default function AddMember( props ) {


    const [ rank , setRank ] = useState('');


    const handleChange = (event: SelectChangeEvent) => {
        setRank(event.target.value);
    };

    return (<>
        <Container className="addMember-wrap">
            <div className="addMember-mainBox">
                <div className="addMember-itemBox">
                    <TextField label="사원명" type="text" className="input_name" />
                    <FormControl className="input_rank">
                        <InputLabel> 직급 </InputLabel>
                        <Select
                          value={ rank }
                          label="직급"
                          onChange={ handleChange }
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
                    <TextField label="아이디" type="text" className="input_id" />
                    <TextField label="패스워드" type="password" className="input_pwd" />
                </div>
                <div className="addMember-itemBox">
                    <TextField label="연락처" type="text" className="input_phone" />
                </div>
                <div className="addMember-itemBox">
                    <TextField label="이메일" type="text" className="input_email" />
                </div>
                <div className="addMember-itemBox imgBox">
                    <div className="input_btn_box">
                        <TextField type="file" className="input_profile" />
                        <div className="btnBox">
                            <Button variant="contained"> 등록 </Button>
                            <Button variant="outlined"> 취소 </Button>
                        </div>
                    </div>
                    <div className="img-view"> 이미지 미리보기 </div>
                </div>
            </div>
        </Container>
    </>);
}