import { formatKoreanCurrency } from "../utils/commonFunction";
import { CARD_ADD_MODAL, CATEGORY_MODAL_OPEN_BTN_VISITED_TEXT, CATEGORY_SELECT_MODAL, KOREA_MONEY_MAX_LENGTH, TOO_BIG_NUMBER_TEXT } from "../utils/commonVariable";
import {
  markBtnAsVisited,
  openModal,
  openCardAddModal,
  setGoalMoneyInfoToKoreaMoney,
} from "./ProjectRegisterColumn";

describe("markBtnAsVisited()", () => {
  const $modalOpenBtn = document.createElement("button");
  $modalOpenBtn.classList.add("categoryRow__openBtn");
  document.body.appendChild($modalOpenBtn);

  test("<normal>", () => {
    markBtnAsVisited();
    expect($modalOpenBtn.innerHTML).toBe(CATEGORY_MODAL_OPEN_BTN_VISITED_TEXT);
  });
});

describe("openModal()", () => {
  const $categoryModal = document.createElement("section");
  $categoryModal.classList.add("categorySelectModal");
  document.body.appendChild($categoryModal);

  test("<normal>", () => {
    openModal();

    expect($categoryModal.style.getPropertyValue("top")).toBe(
      CATEGORY_SELECT_MODAL.POPUP
    );

    // 이 부분 작성 못했음
    // openModal() 내부에서 실행되는 markBtnAsVisited()가 1번 호출되는지 판단하는 부분!
    // expect(markBtnAsVisited).toHaveBeenCalledTimes(1);
  });
});

describe("openCardAddModal()", () => {
  const $cardAddModal = document.createElement("section");
  $cardAddModal.classList.add("cardAddModal");
  document.body.appendChild($cardAddModal);

  test("<normal>", () => {
    openCardAddModal();
    expect($cardAddModal.style.getPropertyValue("top")).toBe(
      CARD_ADD_MODAL.POPUP
    );
  });
});

describe("setGoalMoneyInfoToKoreaMoney()", () => {
  const $goalMoneyInput = document.createElement("input");
  const $moneyInfo = document.createElement("span");

  $goalMoneyInput.classList.add("dueRow__goalMoneyInput");
  $moneyInfo.classList.add("dueRow__moneyInfo");

  document.body.appendChild($goalMoneyInput);
  document.body.appendChild($moneyInfo);

  afterEach(() => {
    $goalMoneyInput.value = "";
    $moneyInfo.innerHTML = "";
  })

  test("<normal> proper input value string", () => {
    const inputValue = "1000";
    $goalMoneyInput.value = inputValue;

    setGoalMoneyInfoToKoreaMoney();
    expect($moneyInfo.innerHTML).toBe(`${formatKoreanCurrency(inputValue)}원`);
  });

  test("<normal> proper long input value string", () => {
    const inputValue = "1".repeat(KOREA_MONEY_MAX_LENGTH);
    $goalMoneyInput.value = inputValue;

    setGoalMoneyInfoToKoreaMoney();
    expect($moneyInfo.innerHTML).toBe(TOO_BIG_NUMBER_TEXT);
  });

  test("<normal> proper empty input value string", () => {
    const inputValue = "";
    $goalMoneyInput.value = inputValue;

    setGoalMoneyInfoToKoreaMoney();
    expect($moneyInfo.innerHTML).toBe("");
  });

  test("<abnormal> weird input value string", () => {
    const inputValue = "weird input value";
    $goalMoneyInput.value = inputValue;

    setGoalMoneyInfoToKoreaMoney();
    expect($moneyInfo.innerHTML).toBe("");
  })
});