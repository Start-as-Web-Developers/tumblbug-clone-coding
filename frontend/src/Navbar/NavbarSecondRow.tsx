import React from 'react';
import './navbarSecondRow.scss';
import { FiMenu, FiSearch } from "react-icons/fi";

function NavbarSecondRow() {
  return (
    <section className="navbarSecondRow">
      <ul className="categoryArea">
        <li>
          <FiMenu />
          카테고리
        </li>
        <li>인기</li>
        <li>신규</li>
        <li>마감임박</li>
        <li>공개예정</li>
      </ul>
      <div className="searchArea">
        <input type="text" placeholder="검색어를 입력해주세요." />
        <FiSearch/>
      </div>
    </section>
  );
}

export default NavbarSecondRow;