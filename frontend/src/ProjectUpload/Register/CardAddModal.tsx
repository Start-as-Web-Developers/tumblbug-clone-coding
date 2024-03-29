import React from "react";
import { throwCustomError } from "../../utils/commonErrorFunction";
import {
  $,
  changeCSS,
  createMultiElements,
  formatKoreanCurrency,
} from "../../utils/commonFunction";
import { CARD_ADD_MODAL, KOREA_MONEY_MAX_LENGTH } from "../../utils/commonVariable";
import { updatePreviewCardBox } from "../Preview/PreviewDetail";
import "./cardAddModal.scss";

// global variables
const currentFilePath = "/frontend/src/ProjectUpload/CardAddModal.tsx";

/**
 * 후원 정보를 담은 카드 객체를 생성합니다.
 * @param sponsorMoney 후원 금액
 * @param sponsorExplain 후원 설명
 * @returns 카드 객체 (HTMLElement)
 */
const createCardElement = (sponsorMoney:string, sponsorExplain:string) => {
  const [$cardContainer, $moneyTag, $explainTag] = createMultiElements([
    "div",
    "h2",
    "h3",
  ]);

  $cardContainer.classList.add("sponsorCardArea__card");

  if (Number.isNaN(parseInt(sponsorMoney, 10))) {
    throwCustomError(
      currentFilePath,
      "createCardElement()",
      `weird input - can't parse ${sponsorMoney} into number`
    );
  }

  $moneyTag.innerHTML = `${formatKoreanCurrency(sponsorMoney)}원`;
  $explainTag.innerHTML = sponsorExplain;

  $cardContainer.appendChild($moneyTag);
  $cardContainer.appendChild($explainTag);

  return $cardContainer;
}

/**
 * $input value를 초기화합니다.
 * @param $input HTMLInputElement
 */
// eslint-disable-next-line no-param-reassign
const initializeInputValue = ($input: HTMLInputElement) => { $input.value = ""; };

/**
 * shortcut for closest()
 * @param $self 탐색 시작 Element
 * @param cssDeclaration css 선택자
 * @returns 조건에 부합하는 조상 Element
 */
const getAncestorElement = (
  $self: Element,
  cssDeclaration: string
): HTMLElement => {
  const $ancestor = $self.closest(cssDeclaration);
  if ($ancestor == null) {
    throwCustomError(
      currentFilePath,
      "getAncestorElement",
      `can"t find node whose cssDeclaration is ${cssDeclaration}`
    );
  } 
  return $ancestor as HTMLElement;
};

/**
 * 후원 정보를 담은 카드를 생성하여 카드 영역에 추가합니다.
 * @param moneyValue 후원 금액
 * @param explainValue 후원 설명
 */
const insertCardIntoCardArea = (
  $cardArea:HTMLElement,
  moneyValue: string,
  explainValue: string
) => {
  if (Number.isNaN(parseInt(moneyValue, 10))) {
    throwCustomError(
      currentFilePath,
      `insertCardIntoCardArea()`,
      `werid input, ${moneyValue} can"t be parsed as Number`
    );
  }
  const $card = createCardElement(moneyValue, explainValue);
  $cardArea.appendChild($card);
};

/**
 * 카드 추가와 관련된 modal을 닫습니다.
 * @param event click 이벤트 객체
 */
const closeCardAddModal = (
  event: React.MouseEvent<HTMLElement, MouseEvent> | Event
): void => {
  const $eventTarget = event.target as Element;
  const $cardAddModal = getAncestorElement($eventTarget, ".cardAddModal");
  const $cardArea = $(".sponsorCardArea") as HTMLElement;
  const $moneyInput = $(".moneyInput", $cardAddModal) as HTMLInputElement;
  const $explainInput = $(
    ".descriptionInput",
    $cardAddModal
  ) as HTMLInputElement;

  const sponsorMoney = $moneyInput.value;
  const sponsorExplain = $explainInput.value;

  insertCardIntoCardArea($cardArea, sponsorMoney, sponsorExplain);
  initializeInputValue($moneyInput);
  initializeInputValue($explainInput);
  changeCSS($cardAddModal, "top", CARD_ADD_MODAL.ORIGINAL);
  updatePreviewCardBox(
    `${formatKoreanCurrency(sponsorMoney)}원`,
    sponsorExplain
  );
}

const updateMoneyInfoElement = (inputValue:string):void => {
  if (Number.isNaN(parseInt(inputValue, 10))) {
    return;
  }
  
  const $moneyInfoElement = $(".cardAddForm__moneyForm > span") as HTMLElement;
  $moneyInfoElement.innerHTML =
    inputValue.length >= KOREA_MONEY_MAX_LENGTH
      ? "숫자가 너무 큽니다"
      : `${formatKoreanCurrency(inputValue)}원`;
}

function CardAddModal() {
  return (
    <section className="cardAddModal">
      <section className="cardAddFormContainer">
        <section className="cardAddForm">
          <h1>선물 카드 등록</h1>
          <div className="cardAddForm__moneyForm">
            <h2>후원 금액</h2>
            <span>1,000원</span>
            <input
              className="moneyInput"
              type="number"
              placeholder="후원 금액 입력"
              onChange={(event) => updateMoneyInfoElement(event.target.value)}
            />
          </div>
          <div className="cardAddForm__descriptionForm">
            <h2>후원 설명</h2>
            <input
              className="descriptionInput"
              type="text"
              placeholder="후원 설명 입력"
            />
          </div>
          <button
            type="button"
            className="cardAddForm__addBtn"
            onClick={(event) => closeCardAddModal(event)}
          >
            등록
          </button>
        </section>
      </section>
    </section>
  );
};

export default CardAddModal;
export {
  createCardElement,
  initializeInputValue,
  getAncestorElement,
  insertCardIntoCardArea,
  closeCardAddModal,
  updateMoneyInfoElement,
};