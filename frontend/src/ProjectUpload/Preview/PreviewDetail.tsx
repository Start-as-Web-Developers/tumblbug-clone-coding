import React from 'react';
import { $, changeCSS } from '../../utils/commonFunction';
import { SPONSOR_CARD_MAXIMUM } from '../../utils/commonVariable';
import "./previewDetail.scss";

const updatePreviewIntroduce = (introduce:string) => {
  const $introduceRow = $(".textBox__introduceRow") as HTMLElement;
  const $introduceText = $("h3", $introduceRow) as HTMLElement;
  $introduceText.innerHTML = introduce;
  changeCSS($introduceRow, "opacity", "1");
}

const updatePreviewBudget = (budget:string) => {
  const $budgetRow = $(".textBox__budgeRow") as HTMLElement;
  const $budgetText = $("h3", $budgetRow) as HTMLElement;
  $budgetText.innerHTML = budget;
  changeCSS($budgetRow, "opacity", "1");
}

const updatePreviewSchedule = (schedule: string) => {
  const $scheduleRow = $(".textBox__scheduleRow") as HTMLElement;
  const $scheduleText = $("h3", $scheduleRow) as HTMLElement;
  $scheduleText.innerHTML = schedule;
  changeCSS($scheduleRow, "opacity", "1");
}

const updatePreviewTeamExplain = (teamExplain: string) => {
  const $teamExplainRow = $(".textBox__teamExplainRow") as HTMLElement;
  const $teamExplainText = $("h3", $teamExplainRow) as HTMLElement;
  $teamExplainText.innerHTML = teamExplain;
  changeCSS($teamExplainRow, "opacity", "1");
}
const updatePreviewSponsor = (sponsor: string) => {
  const $sponsorRow = $(".textBox__sponsorExplainRow") as HTMLElement;
  const $sponsorText = $("h3", $sponsorRow) as HTMLElement;
  $sponsorText.innerHTML = sponsor;
  changeCSS($sponsorRow, "opacity", "1");
}

const getCardNode = (sponsorMoney:string, sponsorExplain:string) => {
  const $card = document.createElement("div");
  const $sponsorMoney = document.createElement("h1");
  const $sponsorExplain = document.createElement("h2");
  
  $card.classList.add("card");
  $sponsorMoney.innerHTML = sponsorMoney;
  $sponsorExplain.innerHTML = sponsorExplain;

  $card.appendChild($sponsorMoney);
  $card.appendChild($sponsorExplain);

  return $card;
}

const updatePreviewCardBox = (sponsorMoney: string, sponsorExplain:string) => {
  const $cardBox = $(".cardBox") as HTMLElement;

  if ($cardBox.children.length >= SPONSOR_CARD_MAXIMUM) {
    return;
  } 
  
  const $card = getCardNode(sponsorMoney, sponsorExplain);
  $cardBox.appendChild($card);
}

function PreviewDetail() {
  return (
    <section className="previewDetail">
      <section className="textBox">
        <div className="textBox__introduceRow">
          <h2>상세 소개</h2>
          <h3>상세 소개가 입력됩니다.</h3>
        </div>
        <div className="textBox__budgeRow">
          <h2>예산 계획</h2>
          <h3>예산 계획이 입력됩니다.</h3>
        </div>
        <div className="textBox__scheduleRow">
          <h2>일정 계획</h2>
          <h3>일정 계획이 입력됩니다.</h3>
        </div>
        <div className="textBox__teamExplainRow">
          <h2>팀 소개</h2>
          <h3>팀 소개가 입력됩니다.</h3>
        </div>
        <div className="textBox__sponsorExplainRow">
          <h2>신뢰와 안전</h2>
          <h3>신뢰와 안전이 입력됩니다.</h3>
        </div>
      </section>
      <section className="cardBox"> </section>
    </section>
  );
}

export default PreviewDetail; 
export {
  updatePreviewIntroduce,
  updatePreviewBudget,
  updatePreviewSchedule,
  updatePreviewTeamExplain,
  updatePreviewSponsor,
  updatePreviewCardBox,
};