import React from "react";
import { FaPlus } from "react-icons/fa";
import { formatKoreanCurrency } from "../../utils/commonFunction";
import "./ProjectPrice.scss";

function ProjectPrice() {
  return (
    <section className="ProjectPrice">
      <h4 className="ProjectPriceTitle">선물 선택</h4>
      <div className="ProjectPriceCard">
        <h5>
          {`${formatKoreanCurrency(1000)}원 `}
          <FaPlus />
        </h5>
        <h6>선물 없이 후원하기</h6>
      </div>
      {}
    </section>
  );
}

export default ProjectPrice;
