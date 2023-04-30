import React from 'react';
import './cardArea.scss';
import { AiOutlineHeart } from "react-icons/ai";
import { formatKoreanCurrency } from '../utils/commonFunction';

function CardArea() {
  type cardContent = {
    projectImg: string;
    category: string;
    author: string;
    title: string;
    comment: string;
    startDate: string;
    endDate: string;
    goalMoney: number;
    totalMoney: number;
    sponsor: number;
  };

  const cards: cardContent[] = [
    {
      projectImg:
        "https://tumblbug-pci.imgix.net/11ea5b920c65d5f3d65d29a1b1583cd2e03f16e6/7e8526e5354e0c0bcc41f19ea3532d3953153f46/b54dc2f6adc3d2efe36e579b1105000924deb9ca/3cb39544-e40c-427d-859c-257455ee4ea8.jpeg?ixlib=rb-1.1.0&w=1240&h=930&auto=format%2Ccompress&lossless=true&fit=crop&s=c341eed1c8b070c6695df0e833628bdd",
      category: "보드게임",
      author: "Nandy & Woody",
      title: "직장에서도 즐기는 엑셀 방탈출, 두 번째 에피소드 후회",
      comment:
        "엑셀의 다양한 기능을 이용하여 제작한 누구나 손쉽게 즐길 수 있는 온라인 방탈출입니다.",
      startDate: "2023-03-01",
      endDate: "2023-04-24",
      goalMoney: 10_000_000,
      totalMoney: 12_455_500,
      sponsor: 100,
    },
    {
      projectImg:
        "https://tumblbug-pci.imgix.net/9f0e816d04522c2050c5dcc27b1f45f22ad0ec70/2a9ad8f9f06641f434e179686bd0485ddc8f4d47/b92bbd7ed714729a3433a21837ca61386914894f/ab7cd04a-0d92-4282-8dfe-6818d0285fd0.jpeg?ixlib=rb-1.1.0&w=1240&h=930&auto=format%2Ccompress&lossless=true&fit=crop&s=6de2fd180a83a78c6cec71933f312568",
      category: "만화·웹툰 리소스",
      author: "데코툰",
      title: "비밀보석상: 로판/현대 귀걸이, 목걸이, 브로치, 패턴 등",
      comment:
        "비밀보석상: 로판/현대 귀걸이, 목걸이 + 브로치, 벨트 + 패턴, 자수",
      startDate: "2023-03-01",
      endDate: "2023-04-24",
      goalMoney: 4_000_000,
      totalMoney: 4_754_700,
      sponsor: 100,
    },
    {
      projectImg:
        "https://tumblbug-pci.imgix.net/4a3a63f9bec6ed9abcd82f0d961329be82ad795c/2d6d2b7cbc7fd6ac399e9c2b7ba69079222ba26e/24587809b5e89e78e8f8091e7373095f52fb15e3/2d417a40-975d-4e9e-9e4d-d398bfb668ca.jpeg?ixlib=rb-1.1.0&w=1240&h=930&auto=format%2Ccompress&lossless=true&fit=crop&s=471cc3708c376c28a8a2329e43e024ca",
      category: "동인·게임",
      author: "튜나사운드",
      title: "정여진의 2000년대 애니송 풀버전 프로젝트!",
      comment:
        "대한민국 애니송 대표가수 '정여진'의 2000년대 애니메이션 엔딩곡 풀버전 프로젝트!",
      startDate: "2023-03-01",
      endDate: "2023-04-24",
      goalMoney: 100_000_000,
      totalMoney: 112_127_000,
      sponsor: 100,
    },
    {
      projectImg:
        "https://tumblbug-pci.imgix.net/39ea294562d7dc097b43c832f73a77fbee55ab92/8398c934327b992a3f986f7e82469dc324151ec4/b027bb85a66b93f8e3d497a0b0b360123ebb604a/e8e21ed8-19f3-47d4-8adb-53f19a68c04c.png?ixlib=rb-1.1.0&w=1240&h=930&auto=format%2Ccompress&lossless=true&fit=crop&s=d7220bc2dcf708e8f12f159847b7a7dc",
      category: "카드 게임",
      author: "데코툰",
      title: "[관상]카드로 캐릭터의 인생과 심리를 잠금해제하시겠습니까?",
      comment:
        "[FAKE or REAL] 스타*스를 모티브로 만들었으며, 관상학을 카드로 담아내었습니다.",
      startDate: "2023-03-01",
      endDate: "2023-04-24",
      goalMoney: 5_000_000,
      totalMoney: 4_835_000,
      sponsor: 100,
    },
    {
      projectImg:
        "https://tumblbug-pci.imgix.net/23132d1b1603bf67d41cae29a8bdeccf17b07d13/3aa263fde713e44b51c464e201e811f78715b5c6/e3aba6344e034dbec8982f8eea7f30cf66e8911e/7b968e7a-6fb0-4750-b6dd-c0965b36fd4f.jpeg?ixlib=rb-1.1.0&w=1240&h=930&auto=format%2Ccompress&lossless=true&fit=crop&s=23367c6239e52a47411e3eaccf2faf8b",
      category: "가방",
      author: "오히르",
      title: "비즈니스에 스타일을 담다, 일상에도 들 수 있는 비즈니스백",
      comment: "깔끔하면서도 트렌디한 오히르의 비즈니스백을 소개합니다.",
      startDate: "2023-03-01",
      endDate: "2023-04-24",
      goalMoney: 8_000_000,
      totalMoney: 1_620_000,
      sponsor: 100,
    },
    {
      projectImg:
        "https://tumblbug-pci.imgix.net/39ea294562d7dc097b43c832f73a77fbee55ab92/55290cefe9ec980f1e487d4fefc87d848f777b9b/407d9e090de0808aaa389878cde4d239eb858e66/9b5c44f7-c87b-43df-9083-fe9aa3264d83.jpeg?ixlib=rb-1.1.0&w=1240&h=930&auto=format%2Ccompress&lossless=true&fit=crop&s=719153ec9d53eb78bef775c63e838c81",
      category: "디자인 문구",
      author: "세상에 모든 디자인 <하나>",
      title: "알고 계신가요? 맛있는 컬러들이 심리를 조정한다는 것을",
      comment:
        "오감이 100%라고 가정했을 때, 시각이 차지하는 비중은 무려 87%. '색감의 중요성'",
      startDate: "2023-03-01",
      endDate: "2023-04-24",
      goalMoney: 500_000,
      totalMoney: 2_085_000,
      sponsor: 100,
    },
  ];

  function getPercent(totalMoney: number, goalMoney: number): string {
    const percent = totalMoney / goalMoney;

    if (percent >= 1) {
      return '100%';
    }
    return `${percent * 100}%`;
  }

  return (
    <section className="cardArea">
      {cards.map(
        ({
          projectImg,
          category,
          author,
          title,
          comment,
          startDate,
          endDate,
          goalMoney,
          totalMoney,
          sponsor,
        }) => (
          <div className="cardContainer" key={title}>
            <div className="cardContainer__thumbNail">
              <img src={projectImg} alt="thumb nail" />
              <AiOutlineHeart />
            </div>
            <div className="cardContainer__categoryArea">
              <span>{category}</span>
              <span>|</span>
              <span>{author}</span>
            </div>
            <div className="cardContainer__cardTitle">{title}</div>
            <div className="cardContainer__cardExplain">{comment}</div>
            <div className="cardContainer__percentBarArea">
              <div className="cardContainer__percentTextArea">
                <h2>{Math.round((totalMoney / goalMoney) * 100)}%</h2>
                <h4>{formatKoreanCurrency(totalMoney)}원</h4>
                <h3>6일 남음</h3>
              </div>
              <div className="cardContainer__percentBar">
                <div
                  className="cardContainer__innerPercentBar"
                  style={{ width: getPercent(totalMoney, goalMoney)}}
                >
                  {" "}
                </div>
              </div>
            </div>
          </div>
        )
      )}
    </section>
  );
}

export default CardArea;