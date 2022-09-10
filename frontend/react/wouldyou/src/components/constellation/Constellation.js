import React, { useEffect,  useState } from "react";
import styled from "styled-components";
import axios from 'axios';

const getConstellations = (day) => {

    const response = axios.get(`http://localhost:8080/ConstellationService/getConstellation?solMonth=${day.getMonth()}`)
        .then(function (res) {
            const dataSet = res.data;
            return dataSet.result;
        });

    return response;
}

function Constellation({ selectDate, setSelectConstellation }) {

    const [res, setRes] = useState([]);

    useEffect(() => {
        getConstellations(selectDate).then(r => setRes(r));
    }, [selectDate]);

    return (
        <>
            <Wrapper>
                <Title>오늘의 별자리</Title>
                <Container>
                    <ConstellationTable>
                        <tbody>
                            {res.map((data) => (
                                <tr key={data.name}>
                                    <td>
                                        <ConstellationTableCell onClick={() =>{setSelectConstellation(data);}}>
                                            <ConstellationImage src={data.image}/>
                                            <ConstellationTitle>{data.name}</ConstellationTitle>
                                            <ConstellationEgTitle>({data.englishName}, {data.scientificName})</ConstellationEgTitle>
                                            <ConstellationCulmination>자오선통과 {data.culmination}</ConstellationCulmination>
                                        </ConstellationTableCell>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </ConstellationTable>
                </Container>
            </Wrapper>
        </>
    );
}

const Wrapper = styled.div`
    display: inline-block;
    height: 370px;
    margin: 5px 1%;
    width: 33%;
`;

const Title = styled.span`
    line-height: 40px;
    font-size: 25px;
`;

const Container = styled.div`
    height: 330px;
    background-color: white;
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

const ConstellationTable = styled.table`
    width: 100%;
`;

const ConstellationTableCell = styled.div`
    height: 100px;
    width: 92%;
    background-color: white;
    padding: 2%;
    margin: 1% 2%;
    border: 2px solid #DDDDDD;
    border-radius: 5px;

    :hover {
        background-color: #EEEEEE;
    }
`

const ConstellationImage = styled.img`
    float: right;
    border-radius: 5px;
    height: 100%;
`;

const ConstellationTitle = styled.div`
    font-size: 20px;
`;

const ConstellationEgTitle = styled.div`
    font-size: 18px;
`;

const ConstellationCulmination = styled.div`
    font-size: 16px;
`;

export default Constellation;