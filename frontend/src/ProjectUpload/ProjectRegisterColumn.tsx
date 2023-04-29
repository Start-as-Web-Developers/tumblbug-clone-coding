import React from 'react';
import "./ProjectRegisterColumn.scss";

function ProjectRegisterColumn() {
  return (
    <section className="projectRegisterColumn">
      <h1 className="projectRegisterColumn__title">Register Project</h1>
      <section className="projectMetaInfoArea">
        <div className="titleRow">
          <h2>프로젝트 제목</h2>
          <input type="text" placeholder="프로젝트 제목 입력" />
        </div>
        <div className="commentRow">
          <h2>프로젝트 설명</h2>
          <input type="text" placeholder="프로젝트 설명 입력" />
        </div>
        <div className="imageLinkRow">
          <h2>썸네일 주소</h2>
          <input type="text" placeholder="썸네일 주소 입력" />
        </div>
        <div className="dueRow">
          <h2>마감 기한</h2>
          <input type="date" />
          <h2>목표 금액</h2>
          <span>1,000원</span>
          <input type="number" placeholder="목표 금액 입력" value="1000"/>
        </div>
        <div className="categoryRow">
          <h2>카테고리</h2>
          <button type="button">카테고리 탐색</button>
          <span>선택된 카테고리가 없습니다</span>
        </div>
      </section>
      <section className="projectDescriptionArea">describe</section>
      <section className="projectSponsorArea">sponsor</section>
    </section>
  );
}

export default ProjectRegisterColumn;