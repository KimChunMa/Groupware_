import React , { useState , useEffect , useRef } from 'react';

import styles from './css/main/Home.css';

import { Container , Paper } from '@mui/material'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';


const bull = (
  <Box
    component="span"
    sx={{ display: 'inline-block', mx: '2px', transform: 'scale(0.8)' }}
  >
    •
  </Box>
);

export default function Home( props ) {

    // 현재시간
    const [time, setTime] = useState( new Date() );

    useEffect(() => {

        const id = setInterval(() => {
            setTime(new Date());
        }, 1000);

        return (() => clearInterval(id))

    }, []);


    return (<>
        <Container>
            <div className="main-top-box">
                <Paper elevation={1} style={{ width:"45%" , margin:"1%" }} >
                    <h4 className="main-box-title">근태정보</h4>
                    <div>{time.toLocaleTimeString()}</div>
                </Paper>
                <Paper elevation={1} style={{ width:"45%", margin:"1%" }}>
                    <h4 className="main-box-title">전자결재</h4>
                    <div className="approval-state-box">
                        <div className="state-box-item">
                            <div className="approval-text">결재할 문서</div>
                            <div>0</div>
                        </div>
                        <div className="state-box-item">
                            <div className="approval-text">상신한 문서</div>
                            <div>0</div>
                        </div>
                        <div className="state-box-item">
                            <div className="approval-text">진행된 문서</div>
                            <div>0</div>
                        </div>
                        <div>
                            <div className="approval-text">대기중 문서</div>
                            <div>0</div>
                        </div>
                    </div>
                </Paper>
            </div>
            <div className="main-middle-box">
                <div className="calander-box">
                    <LocalizationProvider
                      dateAdapter={ AdapterDayjs }
                      localeText={{
                        calendarWeekNumberHeaderText: '#',
                        calendarWeekNumberText: (weekNumber) => `${weekNumber}.`,
                      }}
                    >
                      <DateCalendar displayWeekNumber />
                    </LocalizationProvider>
                </div>
                <div className="schedule-box">
                    <Card sx={{ minWidth: 275 }}>
                      <CardContent>
                        <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                          Word of the Day
                        </Typography>
                        <Typography variant="h5" component="div">
                          be{bull}nev{bull}o{bull}lent
                        </Typography>
                        <Typography sx={{ mb: 1.5 }} color="text.secondary">
                          adjective
                        </Typography>
                        <Typography variant="body2">
                          well meaning and kindly.
                          <br />
                          {'"a benevolent smile"'}
                        </Typography>
                      </CardContent>
                      <CardActions>
                        <Button size="small">Learn More</Button>
                      </CardActions>
                    </Card>
                </div>
            </div>
        </Container>
    </>);
}