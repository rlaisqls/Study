import React, { useState, useRef, useEffect } from "react";
import styled, { css } from "styled-components";
import { FaCaretLeft, FaCaretRight } from "react-icons/fa";
import { dateFormat } from "../common/dateFormat";
import axios from 'axios';

const getMoonAge = (day) => {

    const response = axios.get(`http://localhost:8080/LunPhInfoService/getLunPhInfo?solYear=${day.getFullYear()}&solMonth=${day.getMonth()+1}&solDay=${day.getDate()}`)
        .then(function (res) {
            const dataSet = res.data;
            return dataSet.result;
        });

    return response;
}

function Calendar({ today, selectDate, setSelectDate }) {
    const modalRef = useRef();
    const [isOpen, setOpen] = useState(false);
    const [yearMonth, setYearMonth] = useState([
        today.getFullYear(),
        today.getMonth() + 1,
    ]);
    const [moonAge, setMoonAge] = useState();
    
    useEffect(() => {
        getMoonAge(selectDate).then(m => {setMoonAge(m);});
    }, [selectDate]);

    useEffect(() => {
        window.addEventListener("click", clickModalOutside);
    }, []);

    const clickModalOutside = event => {
        if (modalRef.current != null && modalRef.current.contains(event.target)) {
            setOpen(true);
        } else {
            setOpen(false);
        }
    };

    const getFullMonth = (yearMonth) => {
        const curMonthStart = new Date(yearMonth[0], yearMonth[1] - 1);
        const preMonthEnd = new Date(yearMonth[0], yearMonth[1] - 1, 0).getDate();
        const curStartDate = curMonthStart.getDay();
        const curEndDate = new Date(yearMonth[0], yearMonth[1], 0).getDate();
        let list = [[]];

        for (let i = curStartDate - 1; i >= 0; i--) {
            const preDate = new Date(yearMonth[0], yearMonth[1] - 2, preMonthEnd - i);
            list[0].push({
                year: preDate.getFullYear(),
                month: preDate.getMonth() + 1,
                date: preDate.getDate(),
                dateObj: preDate,
                state: "pre",
            });
        }

        for (let i = 1, j = 0; i <= curEndDate; i++) {
            if (list[j].length === 7) {
                j++;
                list.push([]);
            }
            list[j].push({
                year: yearMonth[0],
                month: yearMonth[1],
                date: i,

                dateObj: new Date(yearMonth[0], yearMonth[1] - 1, i),
                state: "cur",
            });
        }
        let i = 0;
        while (list[list.length - 1].length < 7) {
            i++;
            const nextDate = new Date(yearMonth[0], yearMonth[1], i);
            list[list.length - 1].push({
                year: nextDate.getFullYear(),
                month: nextDate.getMonth() + 1,
                date: nextDate.getDate(),
                dateObj: nextDate,
                state: "next",
            });
        }
        return list;
    };

    const calendarHandle = (arrow) => {
        let year = yearMonth[0];
        let month = yearMonth[1];
        switch (arrow) {
            case "pre":
                if (month === 1) {
                    year--;
                    month = 12;
                } else month--;
                break;
            case "next":
                if (month === 12) {
                    year++;
                    month = 1;
                } else month++;
                break;
        }
        setYearMonth([year, month]);
    };

    const selectDateHandle = (day) => {
        setSelectDate(day.dateObj);
    };

    return (
        <Wrapper ref={modalRef}>
            <RowBox>
                <OpenButton>
                    달력
                </OpenButton>
                <ValueViewer>{dateFormat(selectDate) + " " + moonAge}</ValueViewer>
            </RowBox>
            {isOpen && (
                <>
                    <CalendarContainer>
                        <Header>
                            <HeaderArrow onClick={() => calendarHandle("pre")}>
                                <FaCaretLeft/>
                            </HeaderArrow>
                            <HeaderText>
                                {yearMonth[0]}.
                                {yearMonth[1] < 10 ? `0${yearMonth[1]}` : yearMonth[1]}
                            </HeaderText>
                            <HeaderArrow onClick={() => calendarHandle("next")}>
                                <FaCaretRight />
                            </HeaderArrow>
                        </Header>
                        <WeekGrid>
                            {["일", "월", "화", "수", "목", "금", "토"].map((el, index) => (
                                <WeekBox key={index}>{el}</WeekBox>
                            ))}
                        </WeekGrid>
                        <DateGrid>
                            {getFullMonth(yearMonth).map((week) =>
                                week.map((day, idx) => (
                                    <DateBox
                                        key={idx}
                                        current={day.state === "cur"}
                                        selcted={String(selectDate) === String(day.dateObj)}
                                        onClick={() => day.state === "cur" && selectDateHandle(day)}
                                    >
                                        {day.date}
                                    </DateBox>
                                ))
                            )}
                        </DateGrid>
                    </CalendarContainer>
                </>
            )}
        </Wrapper>
    );
}

const Wrapper = styled.div`
    padding-top : 70px;
    padding-left : 10px;
`;

const OpenButton = styled.div`
    width: 70px;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #AAAAAA;
    border-radius: 10px;
    font-size: 17px;
    color: gray;
    cursor: pointer;
    position: relative;
    ${({ isOpen, setOpen }) =>
        isOpen &&
        css`
        color: white;
        background-color: darkgray;
    `}
`;

const RowBox = styled.div`
    display: flex;
    align-items: center;
    margin-bottom: 10px;
`;

const ValueViewer = styled.div`
    font-size: 17px;
    margin-left: 20px;
`;

const CalendarContainer = styled.div`
    margin-top: 0px;
    background-color: white;
    position: absolute;
    z-index: 10;
    border: 1px solid #AAAAAA;
    border-radius: 10px;
    padding: 10px;
    width: 350px;
    display: flex;
    flex-direction: column;
`;

const Header = styled.div`
    width: 100%;
    height: 20px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin: 10px 0;
    font-size: 20px;
`;

const HeaderArrow = styled.div`
    cursor: pointer;
    :hover {
        color: darkgray;
    }
`;

const HeaderText = styled.div``;

const DateGrid = styled.div`
    width: 100%;
    height: 350px;
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    grid-auto-rows: 45px;
    column-gap: 5px;
    row-gap: 5px;
`;

const DateBox = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #264167;
    border-radius: 50%;
    border: 1px solid white;
    cursor: pointer;

    ${({ current }) =>
        !current
            ? css`
                color: #DDDDDD;
            `
            : css`
                :hover {
                margin:15%;
                width: 70%;
                height: 70%;
                border: 1px solid lightgray;
            }
        `}
    ${({ selcted }) =>
        selcted &&
        css`
        margin:15%;
        width: 70%;
        height: 70%;
        background-color: #0078D7;
        color: white;
    `}
`;

const WeekGrid = styled.div`
    width: 350px;
    height: 18px;
    margin-bottom: 3px;
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    column-gap: 0px;
`;

const WeekBox = styled.div`
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 12px;
    background-color: #0078D7;
    color: white;
`;

export default Calendar;