import React from "react";
import ReactDOM from "react-dom";
import { $, changeCSS } from "../utils/commonFunction";
import "./cardAddModal.scss";

function getCardView(sponsorMoney:string, sponsorExplain:string) {
  const $cardContainer = document.createElement("div");
  const $moneyTag = document.createElement("h2");
  const $explainTag = document.createElement("h3");

  $cardContainer.classList.add("sponsorCardArea__card");
  $moneyTag.innerHTML = `${sponsorMoney}원`;
  $explainTag.innerHTML = sponsorExplain;

  $cardContainer.appendChild($moneyTag);
  $cardContainer.appendChild($explainTag);

  return $cardContainer;
}

function CardAddModal() {
  function closeCardAddModal(event: React.MouseEvent<HTMLElement, MouseEvent>) {
    const $self = event.target as Element;
    const $cardAddModal = $self.closest(".cardAddModal") as HTMLElement;
    const $moneyInput = $cardAddModal.querySelector(
      ".cardAddForm__moneyForm > input"
    ) as HTMLInputElement;
    const $explainInput = $cardAddModal.querySelector(
      ".cardAddForm__descriptionForm > input"
    ) as HTMLInputElement;

    let inputMoneyValue = "";
    let inputExplainValue = "";

    if ($moneyInput && $explainInput) {
      inputMoneyValue = $moneyInput.value;
      inputExplainValue = $explainInput.value;

      $moneyInput.value = "";
      $explainInput.value = "";

      const $card = getCardView(inputMoneyValue, inputExplainValue);
      const $cardArea = $(".sponsorCardArea");
      $cardArea?.appendChild($card);
    }

    changeCSS($cardAddModal, "top", "-100vh");
  }

  return (
    <section className="cardAddModal">
      <section className="cardAddFormContainer">
        <section className="cardAddForm">
          <h1>선물 카드 등록</h1>
          <div className="cardAddForm__moneyForm">
            <h2>후원 금액</h2>
            <span>1,000원</span>
            <input type="number" placeholder="후원 금액 입력" />
          </div>
          <div className="cardAddForm__descriptionForm">
            <h2>후원 설명</h2>
            <input type="text" placeholder="후원 설명 입력" />
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
}

export default CardAddModal;