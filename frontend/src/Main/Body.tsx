import React from 'react';
import './body.scss';
import BodyFilterBtnArea from './BodyFilterBtnArea';
import BodyTitle from './BodyTitle';
import CardArea from './CardArea';

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