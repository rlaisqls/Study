import React, { useState, useRef, useEffect } from "react";
import styled, { css } from "styled-components";

function AstroEvent() {
    return (
        <Wrapper>
            <Title>오늘의 천문현상</Title>
            <Container>
            
            </Container>
        </Wrapper>
    );
}

const Title = styled.span`

    line-height: 40px;
    font-size: 25px;
`;

const Container = styled.div`
    height: 200px;
    width: 100%;
    background-color: blue;
`;

const Wrapper = styled.div`

    display: inline-block;
    height: 240px;
    margin: 5px 1%;
    width: 33%;
`;

export default AstroEvent;