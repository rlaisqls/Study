import React, { useState } from 'react';
import { Icon } from '@iconify/react';
import axios from 'axios';

import { format, addMonths, subMonths } from 'date-fns';
import { startOfMonth, endOfMonth, startOfWeek, endOfWeek } from 'date-fns';
import { isSameMonth, isSameDay, addDays } from 'date-fns';


async function moon(year, month, date) {

    const res = await axios.get(
        `http://localhost:8080/LunPhInfoService/getLunPhInfo?solYear=${year}&solMonth=${month}&solDay=${date}&ServiceKey=EEXySxuzBB5HXz%2BW5BAximCisyOLiQpbJOwldraXuVQ2sY2sboz5%2BUJ8U2girScqMprcbbPF3xpf4s67SROQKA%3D%3D`
    ).then(function (response) {
        const dataSet = response.data;
        return dataSet.result;
    });

    return res;
}

const RenderHeader = ({ currentMonth, prevMonth, nextMonth }) => {
    return (
        <div className="header row">
            <div className="col col-start">
                <span className="text">
                    <span className="text month">
                        {format(currentMonth, 'M')}월
                    </span>
                    {format(currentMonth, 'yyyy')}
                </span>
            </div>
            <div className="col col-end">
                <Icon icon="bi:arrow-left-circle-fill" onClick={prevMonth} />
                <Icon icon="bi:arrow-right-circle-fill" onClick={nextMonth} />
            </div>
        </div>
    );
};

const RenderDays = () => {
    const days = [];
    const date = ['Sun', 'Mon', 'Thu', 'Wed', 'Thrs', 'Fri', 'Sat'];

    for (let i = 0; i < 7; i++) {
        days.push(
            <div className="col" key={i}>
                {date[i]}
            </div>,
        );
    }

    return <div className="days row">{days}</div>;
};

const RenderCells = ({ currentMonth, selectedDate, onDateClick }) => {
    const monthStart = startOfMonth(currentMonth);
    const monthEnd = endOfMonth(monthStart);
    const startDate = startOfWeek(monthStart);
    const endDate = endOfWeek(monthEnd);

    const rows = [];
    let days = [];
    let day = startDate;
    let formattedDate = '';

    while (day <= endDate) {
        for (let i = 0; i < 7; i++) {
            formattedDate = format(day, 'd');
            const cloneDay = day;

            const year = day.getFullYear();
            const month = day.getMonth() + 1;
            const date = day.getDate();

            moon(year, month, date).then(m => {
                days.push(
                    <div
                        className={`col cell ${!isSameMonth(day, monthStart)
                            ? 'disabled'
                            : isSameDay(day, selectedDate)
                                ? 'selected'
                                : format(currentMonth, 'M') !== format(day, 'M')
                                    ? 'not-valid'
                                    : 'valid'
                            }`}
                        key={day}
                        onClick={() => onDateClick(cloneDay)}
                    >
                        <span
                            className={
                                format(currentMonth, 'M') !== format(day, 'M')
                                    ? 'text not-valid'
                                    : ''
                            }
                        >
                            {m}
                        </span>
                    </div>,
                );
                day = addDays(day, 1);
                console.log(m);
            });

            
        }


        rows.push(
            <div className="row" key={day}>
                {days}

            </div>,
        );
        days = [];
    }
    return <div className="body">{rows}</div>;
};

function Calendar() {
    const [currentMonth, setCurrentMonth] = useState(new Date());
    const [selectedDate, setSelectedDate] = useState(new Date());

    const prevMonth = () => {
        setCurrentMonth(subMonths(currentMonth, 1));
    };
    const nextMonth = () => {
        setCurrentMonth(addMonths(currentMonth, 1));
    };
    const onDateClick = (day) => {
        setSelectedDate(day);
    };
    return (
        <div className="calendar_wrap">
            <div className="calendar">
                <RenderHeader
                    currentMonth={currentMonth}
                    prevMonth={prevMonth}
                    nextMonth={nextMonth}
                />
                <RenderDays />
                <RenderCells
                    currentMonth={currentMonth}
                    selectedDate={selectedDate}
                    onDateClick={onDateClick}
                />
            </div>
        </div>
    );
}

export default Calendar;