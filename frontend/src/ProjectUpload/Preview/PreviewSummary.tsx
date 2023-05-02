import React from 'react';
import './previewSummary.scss';
import { BsHeart, BsShare } from "react-icons/bs";
import { $, changeCSS } from '../../utils/commonFunction';

const updatePreviewThumbNail = (imgLink:string) => {
  const $thumbNail = $(".previewSummary__thumbNail") as HTMLElement;
  $thumbNail.setAttribute("src", imgLink);
  changeCSS($thumbNail, "opacity", "1");
}

const updatePreviewBox = () => {
  const $btnBox = $(".previewSummary__buttonBox") as HTMLElement;
  const $sponsorBox = $(".previewSummary__sponsorNumber") as HTMLElement;
  changeCSS($btnBox, "opacity", "1");
  changeCSS($sponsorBox, "opacity", "1");
}

const updatePreviewDueDate = (dueDate:string) => {
  const $dueDateRow = $(".previewSummary__dueDateRow") as HTMLElement;
  const $dueDateText = $("h2", $dueDateRow) as HTMLElement;
  $dueDateText.innerHTML = dueDate;
  changeCSS($dueDateRow, "opacity", "1");
  updatePreviewBox();
}

const updatePreviewDueMoney = (dueMoney:string) => {
  const $dueMoneyRow = $(".previewSummary__dueMoneyRow") as HTMLElement;
  const $dueMoneyText = $("h2", $dueMoneyRow) as HTMLElement;
  $dueMoneyText.innerHTML = dueMoney;
  changeCSS($dueMoneyRow, "opacity", "1");
  updatePreviewBox();
}

function PreviewSummary() {
  return (
    <section className="previewSummary">
      <img
        src="./"
        alt="존재하지 않는 이미지 주소"
        className="previewSummary__thumbNail"
      />
      <div className="previewSummary__textBox">
        <div className="previewSummary__dueDateRow">
          <h5>목표 기한</h5>
          <h2>언제까지</h2>
        </div>
        <div className="previewSummary__dueMoneyRow">
          <h5>목표 금액</h5>
          <h2>얼마</h2>
        </div>
        <div className="previewSummary__sponsorNumber">
          <h5>후원자 수</h5>
          <h2>9,999명</h2>
        </div>
        <div className="previewSummary__buttonBox">
          <button type="button" className="previewSummary__commonBtn">
            <BsHeart />
          </button>
          <button type="button" className="previewSummary__commonBtn">
            <BsShare />
          </button>
          <button type="submit" className="previewSummary__submitBtn">
            이 프로젝트 후원하기
          </button>
        </div>
      </div>
    </section>
  );
}

export default PreviewSummary;
export { updatePreviewThumbNail, updatePreviewDueDate, updatePreviewDueMoney };