import React from 'react';
import { FiSearch } from "react-icons/fi";
import './searchArea.scss';

function SearchArea() {
  return (
    <div className="searchArea">
      <input type="text" placeholder="검색어를 입력해주세요." />
      <FiSearch />
    </div>
  );
}

export default SearchArea;