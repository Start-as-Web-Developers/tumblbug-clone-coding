import React from 'react';
import './bodyTitle.scss';
import { cards } from './CardArea';

function BodyTitle() {
  return (
    <h1 className="bodyTitle">
      <span className="bodyTitle__projectNumber">{cards.length}</span>
      <span className="bodyTitle__projectExplain">
        개의 프로젝트가 있습니다.
      </span>
    </h1>
  );
}

export default BodyTitle;
