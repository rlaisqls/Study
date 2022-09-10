import React, { useState } from "react";
import Header from "./components/Header"
import Calendar from "./components/Calendar";
import Constellation from "./components/constellation/Constellation"
import ConstellationView from "./components/constellation/ConstellationView";
import "./styles/font.css";

function App() {
    const today = new Date();
    const [selectDate, setSelectDate] = useState(
        new Date(today.getFullYear(), today.getMonth(), today.getDate())
    );
    const [selectConstellation, setSelectConstellation] = useState();
    return (
        <>
            <Header />
            <Calendar
                today={today}
                selectDate={selectDate}
                setSelectDate={setSelectDate}
            />
            <ConstellationView
                selectDate={selectDate}
                selectConstellation={selectConstellation}
                setSelectConstellation={setSelectConstellation}
            />
            <Constellation
                selectDate={selectDate}
                setSelectConstellation={setSelectConstellation}
            />
        </>
    );
}

export default App;