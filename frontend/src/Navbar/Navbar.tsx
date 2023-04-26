import React from 'react';
import './navbar.scss';
import NavbarFirstRow from './firstRow/NavbarFirstRow';
import NavbarSecondRow from './secondRow/NavbarSecondRow';
import CategoryBox from './categoryBox/CategoryBox';

function Navbar() {
  return (
    <section className="navbar">
      <section className="navbarArea">
        <NavbarFirstRow />
        <NavbarSecondRow />
      </section>
      <CategoryBox />
    </section>
  );
}

export default Navbar;