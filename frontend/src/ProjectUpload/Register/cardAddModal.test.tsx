import { $, formatKoreanCurrency } from "../../utils/commonFunction";
import {
  CARD_ADD_MODAL,
  KOREA_MONEY_MAX_LENGTH,
} from "../../utils/commonVariable";
import {
  createCardElement,
  initializeInputValue,
  getAncestorElement,
  insertCardIntoCardArea,
  closeCardAddModal,
  updateMoneyInfoElement,
} from "./CardAddModal";

describe("createCardElement()", () => {
  test("<normal>", () => {
    const moneyString = "1000";
    const explainString = "후원 설명";

    const $cardContainer = createCardElement(moneyString, explainString);
    const $moneyTag = $("h2", $cardContainer);
    const $explainTag = $("h3", $cardContainer);

    expect($cardContainer.tagName).toBe("DIV");
    expect($cardContainer.classList).toContain("sponsorCardArea__card");
    expect($moneyTag).toBeTruthy();
    expect($explainTag).toBeTruthy();
    expect($moneyTag?.innerHTML).toBe(`${moneyString}원`);
    expect($explainTag?.innerHTML).toBe(explainString);
  });

  test("<abnormal> can't parse moneyString", () => {
    const moneyString = "후원 금액"; // 부적절한 input
    const explainString = "후원 설명";
    expect(() => createCardElement(moneyString, explainString)).toThrow();
  });
});

describe("initializeInputValue()", () => {
  test("<normal>", () => {
    const $input = document.createElement("input") as HTMLInputElement;
    initializeInputValue($input);
    expect($input.value).toBe("");
  });
});

describe("getAncestorElement()", () => {
  const $parent = document.createElement("section");
  const $child = document.createElement("div");
  $parent.appendChild($child);
  $parent.classList.add("parent");

  test("<normal>", () => {
    const cssInput = ".parent";
    const $ancestor = getAncestorElement($child, cssInput);
    expect($ancestor).toBe($parent);
  });

  test("<abnormal> weird cssDeclaration", () => {
    const weirdCssInput = ".child";
    expect(() => getAncestorElement($child, weirdCssInput)).toThrow();
  });
});

describe("insertCardIntoCardArea()", () => {
  const $cardArea = document.createElement("section");
  $cardArea.classList.add("sponsorCardArea");

  // for testing insertCardIntoCardArea(), $cardArea should has no child
  afterEach(() => {
    if ($cardArea.firstChild) {
      const $child = $cardArea.firstChild;
      $cardArea.removeChild($child);
    }
  })

  test("<normal>", () => {
    const moneyString = "1000";
    const explainString = "후원 설명";

    insertCardIntoCardArea($cardArea, moneyString, explainString);
    expect($cardArea.childElementCount).toBe(1);
  });

  test("<abnormal> weird input : moneyString can't be parsed", () => {
    const moneyString = "weird input";
    const explainString = "후원 설명";
    expect(() => insertCardIntoCardArea($cardArea, moneyString, explainString)).toThrow();
  });
});

describe("closeCardAddModal()", () => {
  // 어떻게 해야 할 지 모르겠음...
});

describe("updateMoneyInfoElement()", () => {
  const $moneyInfoContainer = document.createElement("div");
  const $moneyInfo = document.createElement("span") as HTMLElement;
  $moneyInfoContainer.classList.add("cardAddForm__moneyForm");
  $moneyInfoContainer.appendChild($moneyInfo);
  document.body.appendChild($moneyInfoContainer);

  afterEach(() => {
    $moneyInfo.innerHTML = "";
  });

  test(`<normal> moenyInput.length < ${KOREA_MONEY_MAX_LENGTH}`, () => {
    const moneyInput = "1000";

    // 실행
    updateMoneyInfoElement(moneyInput);

    // 검증
    expect($moneyInfo.innerHTML).toBe(`${formatKoreanCurrency(moneyInput)}원`);
  });

  test(`<normal> moenyInput.length >= ${KOREA_MONEY_MAX_LENGTH}`, () => {
    const moneyInput = "1".repeat(KOREA_MONEY_MAX_LENGTH);

    // 실행
    updateMoneyInfoElement(moneyInput);

    // 검증
    expect($moneyInfo.innerHTML).toBe("숫자가 너무 큽니다");
  });

  test('<abnormal> weird moneyInput', () => {
    const moneyInput = "부적절한 입력 값";
    const originalInnerHTML = $moneyInfo.innerHTML;

    // 실행
    updateMoneyInfoElement(moneyInput);

    // 검증
    expect($moneyInfo.innerHTML).toBe(originalInnerHTML);
  })
});