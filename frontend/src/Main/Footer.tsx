import React from 'react';
import './footer.scss';
import { AiOutlineApple, AiOutlineCopyrightCircle } from "react-icons/ai";
import { BsGooglePlay } from "react-icons/bs";

function Footer() {
  return (
    <section className="footer">
      <div className="footer__firstLine"> </div>
      <section className="footerServiceRow">
        <div className="footerServiceRow__column">
          <h2>텀블벅</h2>
          <h4>공지사항</h4>
          <h4>서비스 소개</h4>
          <h4>채용</h4>
          <h4>2022 텀블벅 결산</h4>
          <h4>텀블벅 광고센터</h4>
        </div>
        <div className="footerServiceRow__column">
          <h2>이용안내</h2>
          <h4>헬프 센터</h4>
          <h4>첫 후원 가이드</h4>
          <h4>창작자 가이드</h4>
          <h4>수수료 안내</h4>
          <h4>제휴·협력</h4>
        </div>
        <div className="footerServiceRow__column02">
          <h2>정책</h2>
          <h4>이용약관</h4>
          <h4>개인정보 처리방침</h4>
          <h4>프로젝트 심사 기준</h4>
        </div>
        <div className="footerServiceRow__appColumn">
          <h2>App</h2>
          <div>
            <BsGooglePlay />
            <h3>안드로이드</h3>
          </div>
          <div>
            <AiOutlineApple />
            <h3>iOS</h3>
          </div>
        </div>
        <div className="footerServiceRow__emptyColumn"> </div>
        <div className="footerServiceRow__emptyColumn"> </div>
        <div className="footerServiceRow__emptyColumn"> </div>
        <div className="footerServiceRow__serviceColumn">
          <h2>고객지원</h2>
          <h4>평일 09:00~17:00 (12:00~14:00 제외)</h4>
          <div>
            <h5>텀블벅에 문의</h5>
          </div>
        </div>
      </section>
      <div className="footer__secondLine"> </div>
      <section className="footerCompanyExplainRow">
        <div className="footerCompanyExplainRow__companyInfo">
          <h3>회사명</h3>
          <h4>텀블벅(주)</h4>
          <h3>주소</h3>
          <h4>서울 서초구 서초대로 398, 19층 (서초동, BNK 디지털)</h4>
          <h3>대표</h3>
          <h4>김동환</h4>
          <h3>사업자등록번호</h3>
          <h4>105-87-52823</h4>
        </div>
        <div className="footerCompanyExplainRow__phoneInfo">
          <h3>통신판매업 신고번호</h3>
          <h4>2022-서울서초-0352</h4>
          <h3>대표번호</h3>
          <h4>02-6080-0760</h4>
        </div>
        <div className="footerCompanyExplainRow__Inc">
          <AiOutlineCopyrightCircle />
          <span>2023 Tumblbug Inc.</span>
        </div>
      </section>
      <section className="footerLawRow">
        <h5>
          텀블벅은 플랫폼 제공자로서 프로젝트의 당사자가 아니며, 직접적인
          통신판매를 진행하지 않습니다. 프로젝트의 완수 및 선물제공의 책임은
          해당 프로젝트의 창작자에게 있으며, 프로젝트와 관련하여 후원자와
          발생하는 법적 분쟁에 대한 책임은 해당 창작자가 부담합니다.
        </h5>
      </section>
    </section>
  );
}

export default Footer;