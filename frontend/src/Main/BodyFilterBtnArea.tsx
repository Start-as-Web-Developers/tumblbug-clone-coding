import React from 'react';
import { FiChevronDown } from "react-icons/fi";
import './bodyFilterBtnArea.scss';

function BodyFilterBtnArea() {
  return (
    <section className="filterBtnArea">
      <div className="filterBtnArea__percentBtn">
        <span>달성률</span>
        <FiChevronDown />
      </div>
      <div className="filterBtnArea__editorRecommendBtn">에디터 추천</div>
    </section>
  );
}

export default BodyFilterBtnArea;
