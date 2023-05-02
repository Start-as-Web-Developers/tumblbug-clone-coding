import React from 'react';
import {
  Logo02, Logo03, Logo04, Logo05, Logo06,
  Logo07, Logo08, Logo09, Logo10, Logo11,
  Logo12, Logo13, Logo14, Logo15, Logo16,
  Logo17, Logo18, Logo19, Logo20, Logo21,
  Logo22 } from '../../Navbar/categoryBox/Logo';
import { $, changeCSS } from '../../utils/commonFunction';
import { CATEGORY_SELECT_MODAL } from '../../utils/commonVariable';
import { getAncestorElement } from './CardAddModal';
import "./categorySelectModal.scss";

type logoObj = {
  component: typeof Logo02,
  logoName: string;
};

const closeModal = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
  const $self = event.target as Element;
  const $categorySelectModal = getAncestorElement(
    $self,
    ".categorySelectModal"
  );
  const $modalCategoryName = $("h3", $self as HTMLElement) as HTMLElement;
  const $infoCategoryText = $(".categoryRow__categoryName") as HTMLElement;
  $infoCategoryText.innerHTML = $modalCategoryName.innerHTML;
  changeCSS($categorySelectModal, "top", CATEGORY_SELECT_MODAL.original);
}

function CategorySelectModal() {
  const logoObjArray: logoObj[] = [
    {
      component: Logo02,
      logoName: "디자인 문구",
    },
    {
      component: Logo03,
      logoName: "푸드",
    },
    {
      component: Logo04,
      logoName: "출판",
    },
    {
      component: Logo05,
      logoName: "영화 비디오",
    },
    {
      component: Logo06,
      logoName: "보드게임 RPG",
    },
    {
      component: Logo07,
      logoName: "캐릭터 굿즈",
    },
    {
      component: Logo08,
      logoName: "향수 뷰티",
    },
    {
      component: Logo09,
      logoName: "디자인",
    },
    {
      component: Logo10,
      logoName: "공연",
    },
    {
      component: Logo11,
      logoName: "디지털 게임",
    },
    {
      component: Logo12,
      logoName: "홈 리빙",
    },
    {
      component: Logo13,
      logoName: "의류",
    },
    {
      component: Logo14,
      logoName: "예술",
    },
    {
      component: Logo15,
      logoName: "웹툰 만화",
    },
    {
      component: Logo16,
      logoName: "테크 가전",
    },
    {
      component: Logo17,
      logoName: "잡화",
    },
    {
      component: Logo18,
      logoName: "사진",
    },
    {
      component: Logo19,
      logoName: "웹툰 리소스",
    },
    {
      component: Logo20,
      logoName: "반려동물",
    },
    {
      component: Logo21,
      logoName: "쥬얼리",
    },
    {
      component: Logo22,
      logoName: "음악",
    },
  ];

  return (
    <section className="categorySelectModal">
      <section className="modalContainer">
        <h1>Click Category</h1>
        <section className="modalContainer__selectBox">
          {logoObjArray.map(({ component, logoName }) => (
            <button
              className="logoContainer"
              key={component.name}
              type="button"
              onClick={(
                event: React.MouseEvent<HTMLButtonElement, MouseEvent>
              ) => closeModal(event)}
            >
              {component()}
              <h3>{logoName}</h3>
            </button>
          ))}
        </section>
      </section>
    </section>
  );
}

export default CategorySelectModal;
export { closeModal };