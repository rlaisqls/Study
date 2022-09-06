import React, { useState, useRef, useEffect } from "react";
import styled, { css } from "styled-components";

function ConstellationView() {
    return (
        <>
            <Wrapper>
                <Container>
                    <Title>안드로메다자리</Title>
                </Container>
                
            </Wrapper>
        </>
    );
}

const Title = styled.span`
    font-size: 35px;
`;

const Container = styled.div`
    padding: 3% 4%;
`;

const Wrapper = styled.div`
    margin-right: 1%;
    float: right;
    height: 625px;
    width: 63%;
    border: 2px solid gray;
    border-radius: 5px;
`;

export default ConstellationView;