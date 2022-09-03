import React, { useState } from 'react';
import Calendar from './js/Calendar.js'
import Header from './js/Header.js';

import './css/header.css'
import './css/common.scss';
import './css/calendar.scss';
import './css/theme.scss';


class App extends React.Component {
    render() {
        return (
            <div className="App">
                <Header></Header>
                <Calendar></Calendar>
            </div>
        );
    }
}

export default App;
