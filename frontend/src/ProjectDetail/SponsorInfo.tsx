import React from 'react';
import "./sponsorInfo.scss";
import { AiOutlineHeart, AiOutlineShareAlt } from "react-icons/ai";
import { formatKoreanCurrency } from '../utils/commonFunction';

interface sponsorInterface {
    goalMoney: number,
    sponsorMoney: number,
    sposnorMember: number,
    sponsorStartDate: Date,
    sponsorEndDate: Date,
    projectLike: number,
    projectShare: number,
}

function SponsorInfo() {
    const sponsorInfoObj: sponsorInterface = {
        goalMoney: 3_000_000,
        sponsorMoney: 51_176_000,
        sposnorMember: 1_752,
        sponsorStartDate: new Date(2023, 5, 8),
        sponsorEndDate: new Date(2023, 6, 8),
        projectLike: 3_140,
        projectShare: 828,
    }

    return (
        <section className="infoArea">
            <section className="mainBox">
                <div className="sponsorMoneyRow">
                    <h4>모인 금액</h4>
                    <h3><strong>{formatKoreanCurrency(sponsorInfoObj.sponsorMoney)}</strong>원</h3>
                    <h2>{Math.round(sponsorInfoObj.sponsorMoney / sponsorInfoObj.goalMoney) * 100}%</h2>
                </div>
                <div className="sponsorTimeRow">
                    <h4>남은 시간</h4>
                    <h3><strong>21</strong>일</h3>
                </div>
                <div className="sponsorMemberRow">
                    <h4>후원자</h4>
                    <h3><strong>{formatKoreanCurrency(sponsorInfoObj.sposnorMember)}</strong>명</h3>
                </div>
            </section>
            <section className="subBox">
                <span>목표금액</span>
                <span>${formatKoreanCurrency(sponsorInfoObj.goalMoney)}원</span>
                <span>펀딩 기간</span>
                <span>{sponsorInfoObj.sponsorStartDate.toDateString()} ~ {sponsorInfoObj.sponsorEndDate.toDateString()}<strong>20일 남음</strong></span>
                <span>결제</span>
                <span>목표 금액 달성시 2023.06.09에 결제 진행</span>
            </section>
            <section className="buttonBox">
                <div className="buttonBox__heart">
                    <AiOutlineHeart/>
                    <span>{sponsorInfoObj.projectLike}</span>
                </div>
                <div className="buttonBox__share">
                    <AiOutlineShareAlt/>
                    <span>{sponsorInfoObj.projectShare}</span>
                </div>
                <button type="button">이 프로젝트 후원하기</button>
            </section>
        </section>
    )
}

export default SponsorInfo;