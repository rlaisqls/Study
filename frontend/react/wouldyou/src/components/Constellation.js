import React, { useState, useRef, useEffect } from "react";
import styled, { css } from "styled-components";
import axios from 'axios';

const getConstellations = (day) => {

    console.log(day);

    const response = axios.get(`http://localhost:8080/ConstellationService/getConstellation?solMonth=${day.getMonth()+1}`)
        .then(function (res) {
            const dataSet = res.data;
            //dataSet.result.map(r=>console.log(r.scientificName));
            return dataSet.result;
        });
    /*
    response.then(r=>{
        r.map(rr=>
            console.log(rr.name)
            );
    })*/
    //response.map(r=>console.log("ㅠㅠㅠㅠㅠ"+r.scientificName));

    return response;
}

function Constellation({selectDate}) {
    
    return (
        <>
            <Wrapper>
                <Title>오늘의 별자리</Title>
                <Container>
                    {getConstellations(selectDate).then(r=>{
                            console.log("gdgdgdgsggdddgdgd");
                            r.map((response) => {
                                console.log(response.location);
                                <ConstellationBox>
                                    {response.name}
                                </ConstellationBox>
                            })
                        }
                    )}
                </Container>
            </Wrapper>
        </>
    );
}

const Title = styled.span`

    line-height: 40px;
    font-size: 25px;
`;

const Container = styled.div`
    height: 330px;
    width: 100%;
    background-color: blue;
`;

const Wrapper = styled.div`

    display: inline-block;
    height: 370px;
    margin: 5px 1%;
    width: 33%;
`;

const ConstellationBox = styled.span``;

const ConstellationImage = styled.img``;

export default Constellation;