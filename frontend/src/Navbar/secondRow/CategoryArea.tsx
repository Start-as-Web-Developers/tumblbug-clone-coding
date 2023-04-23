import React from 'react';
import { FiMenu } from "react-icons/fi";
import './categoryArea.scss';

function CategoryArea() {
  return(
    <ul className="categoryArea">
        <li>
          <FiMenu />
          <span>카테고리</span>
        </li>
        <li>
          <span>인기</span>
        </li>
        <li>
          <span>신규</span>
        </li>
        <li>
          <span>마감임박</span>
        </li>
        <li>
          <span>공개예정</span>
        </li>
      </ul>
  )
}

export default CategoryArea;
