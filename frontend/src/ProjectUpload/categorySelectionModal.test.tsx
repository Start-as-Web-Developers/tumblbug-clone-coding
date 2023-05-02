import { CATEGORY_SELECT_MODAL } from "../utils/commonVariable";
import { closeModal } from "./CategorySelectModal";

describe("closeModal()", () => {
  test("<normal>", () => {
    // 노드 생성
    const $categorySelectModal = document.createElement("section");
    const $modalCategoryName = document.createElement("h3");
    const $modalCloseBtn = document.createElement("button");
    const $infoCategoryText = document.createElement("span");

    // 속성 정의
    $categorySelectModal.classList.add("categorySelectModal");
    $modalCategoryName.innerHTML = "선택된 카테고리";
    $infoCategoryText.classList.add("categoryRow__categoryName");

    // 돔 트리 구조화
    $modalCloseBtn.appendChild($modalCategoryName);
    $categorySelectModal.appendChild($modalCloseBtn);
    document.body.appendChild($categorySelectModal);
    document.body.appendChild($infoCategoryText);

    // 이벤트 발생 및 closeModal() 실행
    $modalCloseBtn.addEventListener("click", (event:Event) => {
      closeModal(
        event as unknown as React.MouseEvent<HTMLButtonElement, MouseEvent>
      );
    });
    $modalCloseBtn.dispatchEvent(new MouseEvent("click"));

    expect($infoCategoryText.innerHTML).toBe($modalCategoryName.innerHTML);
    expect($categorySelectModal.style.getPropertyValue("top")).toBe(
      CATEGORY_SELECT_MODAL.original
    );
  });
});