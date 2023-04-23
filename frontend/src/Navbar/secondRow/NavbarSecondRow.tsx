import React from 'react';
import './navbarSecondRow.scss';
import CategoryArea from './CategoryArea';
import SearchArea from './SearchArea';

function NavbarSecondRow() {
  return (
    <section className="navbarSecondRow">
      <CategoryArea />
      <SearchArea/>
    </section>
  );
}

export default NavbarSecondRow;