import React from 'react';
import './navbar.scss';
import NavbarFirstRow from './firstRow/NavbarFirstRow';
import NavbarSecondRow from './secondRow/NavbarSecondRow';

function Navbar() {
  return (
    <section className="navbar">
      <section className="navbarArea">
        <NavbarFirstRow/>
        <NavbarSecondRow/>
      </section>
    </section>
  );
}

export default Navbar;