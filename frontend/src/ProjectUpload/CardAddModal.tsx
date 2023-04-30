import React from "react";
import { $, changeCSS, createMultiElements, formatKoreanCurrency } from "../utils/commonFunction";
import { CARD_ADD_MODAL, KOREA_MONEY_MAX_LENGTH } from "../utils/commonVariable";
import "./cardAddModal.scss";

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
  $moneyTag.innerHTML = `${sponsorMoney}원`;
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
): HTMLElement => $self.closest(cssDeclaration) as HTMLElement;

/**
 * 후원 정보를 담은 카드를 생성하여 카드 영역에 추가합니다.
 * @param moneyValue 후원 금액
 * @param explainValue 후원 설명
 */
const insertCardIntoCardArea = (moneyValue:string, explainValue:string) => {
  const $card = createCardElement(moneyValue, explainValue);
  const $cardArea = $(".sponsorCardArea");
  $cardArea?.appendChild($card);
}

/**
 * 카드 추가와 관련된 modal을 닫습니다.
 * @param event click 이벤트 객체
 */
const closeCardAddModal = (
  event: React.MouseEvent<HTMLElement, MouseEvent>
): void => {
  const $eventTarget = event.target as Element;
  const $cardAddModal = getAncestorElement($eventTarget, ".cardAddModal");
  const $moneyInput = $(".moneyInput", $cardAddModal) as HTMLInputElement;
  const $explainInput = $(
    ".descriptionInput",
    $cardAddModal
  ) as HTMLInputElement;

  insertCardIntoCardArea($moneyInput.value, $explainInput.value);
  initializeInputValue($moneyInput);
  initializeInputValue($explainInput);

  changeCSS($cardAddModal, "top", CARD_ADD_MODAL.ORIGINAL);
}

const updateMoneyInfoElement = (inputValue:string):void => {
  if (Number.isNaN(parseInt(inputValue, 10))) {
    return;
  }
  
  const $moneyInfoElement = $(".cardAddForm__moneyForm > span") as HTMLElement;
  if (inputValue.length >= KOREA_MONEY_MAX_LENGTH) {
    $moneyInfoElement.innerHTML = "숫자가 너무 큽니다";
  } else {
    const koreaMoneyString = formatKoreanCurrency(inputValue);
    $moneyInfoElement.innerHTML = `${koreaMoneyString}원`;
  }
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
export { getAncestorElement };