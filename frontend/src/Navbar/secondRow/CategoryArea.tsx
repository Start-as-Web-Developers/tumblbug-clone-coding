import React, {useEffect, useState} from 'react';
import { FiMenu } from "react-icons/fi";
import { $, changeCSS, eventTo } from '../../utils/commonFunction';
import { CATEGORY_BOX, CATEGORY_BOX_POSITION, NAVBAR_BOX_SHADOW } from '../../utils/commonVariable';
import './categoryArea.scss';

const changeNavbarToClickedShadow = () => {
  const $navBar = $(".navbar") as HTMLElement;
  changeCSS($navBar, "box-shadow", NAVBAR_BOX_SHADOW.CATEGORY_CLICKED);
};

const changeNavbarToOriginalShadow = () => {
  const $navBar = $(".navbar") as HTMLElement;
  changeCSS($navBar, "box-shadow", NAVBAR_BOX_SHADOW.ORIGINAL);
}

const showCategoryBox = () => {
  const $categoryBox = $(".categoryBox") as HTMLElement;
  changeCSS($categoryBox, "top", CATEGORY_BOX_POSITION.CLICKED);
}

const hideCategoryBox = () => {
  const $categoryBox = $(".categoryBox") as HTMLElement;
  changeCSS($categoryBox, "top", CATEGORY_BOX_POSITION.ORIGINAL);
}

const openCategoryBox = (
  clickStateHandler: React.Dispatch<React.SetStateAction<boolean>>
) => {
  showCategoryBox();
  changeNavbarToClickedShadow();
  clickStateHandler(CATEGORY_BOX.OPEN);
};

const closeCategoryBox = (
  clickStateHandler: React.Dispatch<React.SetStateAction<boolean>>
) => {
  changeNavbarToOriginalShadow();
  hideCategoryBox();
  clickStateHandler(CATEGORY_BOX.CLOSE);
};

function CategoryArea() {
  const [categoryBoxIsOpend, setCategoryBoxIsOpend] = useState(false);

  useEffect(() => {
    const $categoryBtn = $(".categoryBtn") as HTMLElement;
    eventTo($categoryBtn, () => {
      if (categoryBoxIsOpend) {
        closeCategoryBox(setCategoryBoxIsOpend);
      } else {
        openCategoryBox(setCategoryBoxIsOpend);
      }
    });
  }, [categoryBoxIsOpend]);

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
