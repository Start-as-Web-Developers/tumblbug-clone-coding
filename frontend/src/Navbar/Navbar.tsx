import React from 'react';
import './navbar.scss';
import NavbarFirstRow from './NavbarFirstRow';
import NavbarSecondRow from './NavbarSecondRow';

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