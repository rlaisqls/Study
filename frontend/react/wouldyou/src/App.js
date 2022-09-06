import React, { useState } from "react";
import Header from "./components/Header"
import Calendar from "./components/Calendar";
import AstroEvent from "./components/AstroEvent";
import Constellation from "./components/Constellation"
import ConstellationView from "./components/ConstellationView";
import "./styles/font.css";

function App() {
    const today = new Date();
    const [selectDate, setSelectDate] = useState(
        new Date(today.getFullYear(), today.getMonth(), today.getDate())
    );
    return (
        <>
            <Header/>
            <Calendar
                today={today}
                selectDate={selectDate}
                setSelectDate={setSelectDate}
            />
            <AstroEvent/>
            <ConstellationView/>
            <Constellation
                selectDate={selectDate}
            />
        </>
    );
}

export default App;