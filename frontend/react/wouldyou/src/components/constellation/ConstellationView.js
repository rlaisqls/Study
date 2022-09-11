import React, { useEffect } from "react";
import styled from "styled-components";
import axios from 'axios';

const getConstellationByDate = (day) => {

    const response = axios.get(`http://localhost:8080/ConstellationService/getConstellation/one?solMonth=${day.getMonth()}&solDay=${day.getDate()}`)
        .then(function (res) {
            return res.data;
        });

    return response;
}

function ConstellationView({ selectDate, selectConstellation, setSelectConstellation}) {

    useEffect(() => {
        getConstellationByDate(selectDate).then(r => setSelectConstellation(r));
    }, []);

    return (
        <>
            {selectConstellation != null ? (
                <>
                    <Wrapper>
                        <Container>
                            <ConstellationImage src={selectConstellation.image} />
                            <ConstellationTitle>{selectConstellation.name}</ConstellationTitle>
                            <ConstellationEgTitle>({selectConstellation.englishName}, {selectConstellation.scientificName})</ConstellationEgTitle>
                            <ConstellationInfo>
                                자오선통과 - {selectConstellation.culmination}<br></br>
                                남중시각 - {selectConstellation.midnightTime}<br></br>
                                위치 - {selectConstellation.location}
                            </ConstellationInfo>
                            <ConstellationDescription dangerouslySetInnerHTML={{ __html: '&nbsp' + selectConstellation.description.replace(/<br>/g, '<br>&nbsp')}}>
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
    line-height: 170%;
`;

const ConstellationEgTitle = styled.span`
    font-size: 23px;
`;

const ConstellationInfo = styled.div`
    font-size: 19px;
`;

const ConstellationDescription = styled.div`
    text-align: justify;
    margin-top: 15px;
    line-height: 180%;
    font-size: 17px;
`;

export default ConstellationView;