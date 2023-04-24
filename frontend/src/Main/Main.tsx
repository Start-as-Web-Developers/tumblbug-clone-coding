import React from 'react';
import Navbar from '../Navbar/Navbar';
import Body from './Body';
import './main.scss';

function Main() {
  return (
    <section className="main">
      <Navbar />
      <Body />
    </section>
  );
}

export default Main;
