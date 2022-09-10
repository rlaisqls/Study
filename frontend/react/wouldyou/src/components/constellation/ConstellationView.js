import React, { useEffect } from "react";
import styled from "styled-components";
import axios from 'axios';

const getConstellation = (day) => {

    const response = axios.get(`http://localhost:8080/ConstellationService/getConstellation/one?solMonth=${day.getMonth()}`)
        .then(function (res) {
            return res.data;
        });

    return response;
}

function ConstellationView({ selectDate, selectConstellation, setSelectConstellation}) {

    useEffect(() => {
        getConstellation(selectDate).then(r => setSelectConstellation(r));
    }, []);

    return (
        <>
            {selectConstellation != null ? (
                <>
                    <Wrapper>
                        <Container>
                            <ConstellationImage src={selectConstellation.image} />
                            <ConstellationTitle>{selectConstellation.name}</ConstellationTitle>
                            <ConstellationInfo>
                                ({selectConstellation.englishName}, {selectConstellation.scientificName})<br></br>
                                자오선통과 {selectConstellation.culmination}<br></br>
                                위치 {selectConstellation.location}
                            </ConstellationInfo>
                            <ConstellationDescription dangerouslySetInnerHTML={{ __html: selectConstellation.description }}>
                            </ConstellationDescription>
                        </Container>
                    </Wrapper>
                </>
            ) : <div></div>}
        </>
    );
}

const Container = styled.div`
    padding: 3% 4%;
`;

const Wrapper = styled.div`
    margin-right: 1%;
    float: right;
    height: 625px;
    width: 63%;
    border: 2px solid #DDDDDD;
    border-radius: 5px;

    overflow: scroll;
    &::-webkit-scrollbar {
      width: 8px;
      height: 0px;
      border-radius: 6px;
      background: rgba(255, 255, 255, 0.4);
    }
    &::-webkit-scrollbar-thumb {
      background-color: #AAAAAA;
      border-radius: 6px;
    }
`;

const ConstellationImage = styled.img`
    float: right;
    border-radius: 5px;
    margin: 20px 0px 20px 20px;
    height: 300px;
`;

const ConstellationTitle = styled.span`
    font-size: 35px;
`;

const ConstellationInfo = styled.div`
    font-size: 20px;
`;

const ConstellationDescription = styled.div`
    margin-top: 15px;
    line-height: 180%;
    font-size: 17px;
`;

export default ConstellationView;