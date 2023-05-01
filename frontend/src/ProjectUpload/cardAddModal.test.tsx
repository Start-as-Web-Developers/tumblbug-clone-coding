import { $ } from "../utils/commonFunction";
import {
  createCardElement,
  initializeInputValue,
  getAncestorElement,
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
    const moneyString = "후원 금액";  // 부적절한 input
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
