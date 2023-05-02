import React from 'react';
import "./previewDetail.scss";

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
      <section className="cardBox">
        <div className="card">
          <h1>1,000원</h1>
          <h2>설명</h2>
        </div>
      </section>
    </section>
  );
}

export default PreviewDetail;