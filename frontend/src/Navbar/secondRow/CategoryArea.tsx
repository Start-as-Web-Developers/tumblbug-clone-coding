import React, {useEffect, useState} from 'react';
import { FiMenu } from "react-icons/fi";
import { $, changeCSS, eventTo } from '../../utils/commonFunction';
import { CATEGORY_BOX_POSITION, NAVBAR_BOX_SHADOW } from '../../utils/commonVariable';
import './categoryArea.scss';

function CategoryArea() {
  const [categoryOpen, setCategoryOpen] = useState(false);

  function eventToCategoryBtn() {
    const $categoryBtn = $(".categoryBtn") as HTMLElement;
    const $navbar = $(".navbar") as HTMLElement;
    const $categoryBox = $(".categoryBox") as HTMLElement;

    eventTo($categoryBtn, () => {
      if (categoryOpen) {
        changeCSS($navbar, "box-shadow", NAVBAR_BOX_SHADOW.ORIGINAL);
        changeCSS($categoryBox, "top", CATEGORY_BOX_POSITION.ORIGINAL);
      } else {
        changeCSS($categoryBox, "top", CATEGORY_BOX_POSITION.CLICKED);
        changeCSS($navbar, "box-shadow", NAVBAR_BOX_SHADOW.CATEGORY_CLICKED);
      }

      setCategoryOpen(!categoryOpen);
    });
  }

  useEffect(() => {
    eventToCategoryBtn();
  }, []);

  useEffect(() => {
    eventToCategoryBtn();
  }, [categoryOpen]);

  return (
    <ul className="categoryArea">
      <li className="categoryBtn">
        <FiMenu />
        <span>카테고리</span>
      </li>
      <li><span>인기</span></li>
      <li><span>신규</span></li>
      <li><span>마감임박</span></li>
      <li><span>공개예정</span></li>
    </ul>
  );
}

export default CategoryArea;
