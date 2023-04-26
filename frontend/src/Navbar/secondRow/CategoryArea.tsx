import React, {useEffect, useState} from 'react';
import { FiMenu } from "react-icons/fi";
import './categoryArea.scss';

function CategoryArea() {
  const [categoryOpen, setCategoryOpen] = useState(false);

  function eventToCategoryBtn() {
    const $categoryBtn = document.querySelector(".categoryBtn") as HTMLElement;
    $categoryBtn.addEventListener("click", () => {
      const $navbar = document.querySelector(".navbar") as HTMLElement;
      const $categoryBox = document.querySelector(".categoryBox") as HTMLElement;

      if (categoryOpen) {
        $navbar.style.boxShadow = '0px 1px 6px rgba(0, 0, 0, 0.08)';
        $categoryBox.style.top = "-36vh";
      } else {
        $categoryBox.style.top = "14vh";
        $navbar.style.boxShadow = "0px 0px 0px #fff";
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
  );
}

export default CategoryArea;
