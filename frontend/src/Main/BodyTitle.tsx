import React from 'react';
import './bodyTitle.scss';

interface props{
  cardNumber: number;
}

function BodyTitle(prop: props) {
  return (
    <h1 className="bodyTitle">
      <span className="bodyTitle__projectNumber">{prop.cardNumber}</span>
      <span className="bodyTitle__projectExplain">
        개의 프로젝트가 있습니다.
      </span>
    </h1>
  );
}

export default BodyTitle;
