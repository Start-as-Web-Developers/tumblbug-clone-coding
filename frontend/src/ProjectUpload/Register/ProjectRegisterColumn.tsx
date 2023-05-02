import React from 'react';
import { $, changeCSS, formatKoreanCurrency } from '../../utils/commonFunction';
import {
  CARD_ADD_MODAL,
  CATEGORY_MODAL_OPEN_BTN_VISITED_TEXT,
  CATEGORY_SELECT_MODAL,
  KOREA_MONEY_MAX_LENGTH,
  TOO_BIG_NUMBER_TEXT,
} from "../../utils/commonVariable";
import { updatePreviewBudget, updatePreviewIntroduce, updatePreviewSchedule, updatePreviewSponsor, updatePreviewTeamExplain } from '../Preview/PreviewDetail';
import { updatePreviewDueDate, updatePreviewDueMoney, updatePreviewThumbNail } from '../Preview/PreviewSummary';
import { updatePreviewExplain, updatePreviewTitle } from '../Preview/PreviewTitle';
import "./ProjectRegisterColumn.scss";

interface secondRowInfo {
  className: string,
  headerName: string,
  eventHandler?: (text: string) => void
};

const secondRowInfos: secondRowInfo[] = [
  {
    className: "introduceRow",
    headerName: "상세 소개",
    eventHandler: updatePreviewIntroduce,
  },
  {
    className: "budgeRow",
    headerName: "예산 계획",
    eventHandler: updatePreviewBudget,
  },
  {
    className: "scheduleRow",
    headerName: "일정 계획",
    eventHandler: updatePreviewSchedule,
  },
  {
    className: "teamExplainRow",
    headerName: "팀 소개",
    eventHandler: updatePreviewTeamExplain,
  },
  {
    className: "sponsorExplainRow",
    headerName: "선물 설명",
  },
  {
    className: "notifyRow",
    headerName: "신뢰와 안전",
    eventHandler: updatePreviewSponsor,
  },
];

/**
 * 카테고리 탐색 버튼의 텍스트를 변경하여
 * 사용자에게 카테고리 탐색 이력이 있음을 알려줍니다.
 */
const markBtnAsVisited = (): void => {
  const $modalOpenBtn = $(".categoryRow__openBtn") as HTMLElement;
  $modalOpenBtn.innerHTML = CATEGORY_MODAL_OPEN_BTN_VISITED_TEXT;
};

/**
 * 카테고리 선택 modal을 팝업합니다.
 */
const openModal = (): void => {
  const $modal = $(".categorySelectModal") as HTMLElement;
  changeCSS($modal, "top", CATEGORY_SELECT_MODAL.POPUP);
  markBtnAsVisited();
};

/**
 * 후원 카드 생성 modal을 팝업합니다.
 */
const openCardAddModal = (): void => {
  const $cardOpenModal = $(".cardAddModal") as HTMLElement;
  changeCSS($cardOpenModal, "top", CARD_ADD_MODAL.POPUP);
};

const setGoalMoneyInfoToKoreaMoney = (
  event: React.ChangeEvent<HTMLInputElement>
) => {
  const $goalMoneyInput = $(".dueRow__goalMoneyInput") as HTMLInputElement;
  const inputValue: string = $goalMoneyInput.value;

  if (Number.isNaN(parseInt(inputValue, 10))) {
    return;
  }

  const $moneyInfo = $(".dueRow__moneyInfo") as HTMLElement;

  if (inputValue.length >= KOREA_MONEY_MAX_LENGTH) {
    $moneyInfo.innerHTML = TOO_BIG_NUMBER_TEXT;
    updatePreviewDueMoney(TOO_BIG_NUMBER_TEXT);
  } else {
    $moneyInfo.innerHTML = `${formatKoreanCurrency(inputValue)}원`;
    updatePreviewDueMoney(`${formatKoreanCurrency(inputValue)}원`);
  }
};

const onTitleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
  updatePreviewTitle(event.target.value);
};

const onProjectExplainInputChange = (
  event: React.ChangeEvent<HTMLInputElement>
) => updatePreviewExplain(event.target.value);

const onThumbNailLinkChange = (event: React.ChangeEvent<HTMLInputElement>) =>
  updatePreviewThumbNail(event.target.value);

const onDueDateChange = (event: React.ChangeEvent<HTMLInputElement>) =>
  updatePreviewDueDate(event.target.value);

function ProjectRegisterColumn() {
  return (
    <section className="projectRegisterColumn">
      <h1 className="projectRegisterColumn__title">Register Project</h1>
      <section className="projectMetaInfoArea">
        <div className="titleRow">
          <h2>프로젝트 제목</h2>
          <input
            type="text"
            placeholder="프로젝트 제목 입력"
            onChange={(event) => onTitleInputChange(event)}
          />
        </div>
        <div className="commentRow">
          <h2>프로젝트 설명</h2>
          <input
            type="text"
            placeholder="프로젝트 설명 입력"
            onChange={(event) => onProjectExplainInputChange(event)}
          />
        </div>
        <div className="imageLinkRow">
          <h2>썸네일 주소</h2>
          <input
            type="text"
            placeholder="썸네일 주소 입력"
            onChange={(event) => onThumbNailLinkChange(event)}
          />
        </div>
        <div className="dueRow">
          <h2>마감 기한</h2>
          <input type="date" onChange={(event) => onDueDateChange(event)} />
          <h2>목표 금액</h2>
          <span className="dueRow__moneyInfo">1,000원</span>
          <input
            type="number"
            placeholder="목표 금액 입력"
            className="dueRow__goalMoneyInput"
            onChange={(event) => setGoalMoneyInfoToKoreaMoney(event)}
          />
        </div>
        <div className="categoryRow">
          <h2>카테고리</h2>
          <button
            className="categoryRow__openBtn"
            type="button"
            onClick={openModal}
          >
            카테고리 탐색
          </button>
          <span className="categoryRow__categoryName">
            선택된 카테고리가 없습니다
          </span>
        </div>
      </section>
      <section className="projectDescriptionArea">
        {secondRowInfos.map(({ className, headerName, eventHandler }) => (
          <div className={className} key={className}>
            <h2>{headerName}</h2>
            {eventHandler ? (
              <input
                type="text"
                placeholder={`${headerName} 입력`}
                onChange={(event) => eventHandler(event.target.value)}
              />
            ) : (
              <input
                type="text"
                placeholder={`${headerName} 입력`}
              />
            )}
          </div>
        ))}
      </section>
      <section className="projectSponsorArea">
        <h1>
          <span>선물 방식 등록</span>
          <button
            type="button"
            className="projectSponsorArea__cardAddBtn"
            onClick={openCardAddModal}
          >
            +
          </button>
        </h1>
        <div className="sponsorCardArea"> </div>
      </section>
    </section>
  );
}

export default ProjectRegisterColumn;
export {
  markBtnAsVisited,
  openModal,
  openCardAddModal,
  setGoalMoneyInfoToKoreaMoney,
};