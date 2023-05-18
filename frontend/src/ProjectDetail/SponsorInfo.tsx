import React from 'react';
import "./sponsorInfo.scss";

function SponsorInfo() {
    return (
        <section className="infoArea">
            <section className="mainBox">
                <div className="sponsorMoneyRow">
                    <h4>모인 금액</h4>
                    <h3><strong>51,176,000원</strong></h3>
                    <h2>1705%</h2>
                </div>
                <div className="sponsorTimeRow">
                    <h4>남은 시간</h4>
                    <h3>21일</h3>
                </div>
                <div className="sponsorMemberRow">
                    <h4>후원자</h4>
                    <h3><strong>1,752명</strong></h3>
                </div>
            </section>
            <section className="subBox">
                sub
            </section>
            <section className="buttonBox">
                button
            </section>
        </section>
    )
}

export default SponsorInfo;