import React, { useEffect, useState } from "react";
import styled from "styled-components";
import axios from 'axios';
import { constellationList } from "../../common/constellationList";

const getConstellationByName = (day, name) => {

    const response = axios.get(`http://52.5.10.3:8080/ConstellationService/getConstellation/name?name=${name}&solMonth=${day.getMonth()}&solDay=${day.getDate()}`)
        .then(function (res) {
            return res.data;
        });

    return response;
}

function ConstellationSearch({ selectDate, setSelectConstellation }) {

    const [inputValue, setInputValue] = useState('');
    const [isHaveInputValue, setIsHaveInputValue] = useState(false);
    const [dropDownList, setDropDownList] = useState(constellationList());
    const [dropDownItemIndex, setDropDownItemIndex] = useState(-1);

    const changeInputValue = event => {
        setInputValue(event.target.value);
        setIsHaveInputValue(true);
    };

    const clickDropDownItem = clickedItem => {
        setInputValue(clickedItem);
        setIsHaveInputValue(false);
    };

    const showDropDownList = () => {
        if (inputValue === '') {
            setIsHaveInputValue(false);
            setDropDownList([]);
        } else {
            const choosenTextList = constellationList().filter(textItem => textItem.includes(inputValue))
            setDropDownList(choosenTextList);
        }
    };

    useEffect(showDropDownList, [inputValue]);

    const handleDropDownKey = event => {
        if (isHaveInputValue) {
            if (event.key === 'ArrowDown' &&
                dropDownList.length - 1 > dropDownItemIndex) {
                setDropDownItemIndex(dropDownItemIndex + 1);
            }

            if (event.key === 'ArrowUp' && dropDownItemIndex >= 0) {
                setDropDownItemIndex(dropDownItemIndex - 1);
            }

            if (event.key === 'Enter' && dropDownItemIndex >= 0) {
                clickDropDownItem(dropDownList[dropDownItemIndex]);
                setDropDownItemIndex(-1);
            }
        }
    }

    const clickSearckButtonHandle = () => {
        constellationList().map(textItem => {
            if (textItem == inputValue) {
                getConstellationByName(selectDate, inputValue).then(res => setSelectConstellation(res));
            }
        })
    }

    const handleOnKeyPress = e => {
        if (e.key === 'Enter') {
            clickSearckButtonHandle(); 
        }
    };

    return (
        <>
            <SearchInput type="text" placeholder="별자리 검색"
                value={inputValue}
                onChange={changeInputValue}
                onKeyUp={handleDropDownKey}
                onKeyPress={handleOnKeyPress}/>
            {isHaveInputValue && (
                <DropDownBox>
                    {dropDownList.length === 0 && (
                        <DropDownItem>해당하는 단어가 없습니다</DropDownItem>
                    )}
                    {dropDownList.map((dropDownItem, dropDownIndex) => {
                        return (
                            <DropDownItem
                                key={dropDownIndex}
                                onClick={() => clickDropDownItem(dropDownItem)}
                                onMouseOver={() => setDropDownItemIndex(dropDownIndex)}
                                className={
                                    dropDownItemIndex === dropDownIndex ? 'selected' : ''
                                }
                            >
                                {dropDownItem}
                            </DropDownItem>
                        )
                    })}
                </DropDownBox>
            )}

            <SearchButton onClick={() => clickSearckButtonHandle()}>🔍</SearchButton>

        </>
    );
}

const SearchInput = styled.input`
    display: inline-block;
    font-size: 18px;
    height: 40px;
    margin: 5px 1%;
    padding: 0 1%;
    width: 26%;
    border: 1px solid #999999;
    border-radius: 5px;
`;

const DropDownBox = styled.ul`
    position: absolute;
    font-size: 18px;
    width: 27.7%;
    margin: -5px 1%;
    padding: 8px 0;
    background-color: white;
    border: 1px solid rgba(0, 0, 0, 0.3);
    border-radius: 5px;
    box-shadow: 0px 3px 2px rgb(0, 0, 0, 0.3);
    list-style-type: none;
    z-index: 3;
`;

const DropDownItem = styled.li`
    padding: 0 16px;

    &.selected {
        background-color: #DDDDDD;
    }
`;

const SearchButton = styled.button`
    display: inline-block;
    font-size: 18px;
    height: 40px;
    margin: 5px 0;
    width: 4%;
    border: 1px solid #999999;
    border-radius: 5px;
    :hover {
        background-color: #DDDDDD;
    }
`;

export default ConstellationSearch;