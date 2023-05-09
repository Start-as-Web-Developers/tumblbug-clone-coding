import React from 'react';
import BodyFilterBtnArea from './BodyFilterBtnArea';
import BodyTitle from './BodyTitle';
import CardArea from './CardArea';
import "./body.scss";

function Body() {
  return (
    <section className="body">
      <BodyFilterBtnArea />
      <BodyTitle />
      <CardArea />
    </section>
  );
}

export default Body;