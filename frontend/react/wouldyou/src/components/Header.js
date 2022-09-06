import React, { useState, useRef } from "react";
import styled, { css } from "styled-components";

function Header() {
    return (
        <Topbar>
            🌌 우주라이크
        </Topbar>
    );
}

const Topbar = styled.header`
    position: fixed;
    top: 0;
    left: 0;
    right: 0;

    height: 50px;
    padding: 5px 5px 5px 2%;

    background-color: #000;;

    font-size: 18pt;
    color: #FFFFFF;

    display: flex;
    justify-content: space-between;
    align-items: center;

    z-index: 2;
`;

export default Header;