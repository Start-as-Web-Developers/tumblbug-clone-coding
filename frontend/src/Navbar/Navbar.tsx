import React from "react";
import "./navbar.scss";
import NavbarFirstRow from "./firstRow/NavbarFirstRow";
import NavbarSecondRow from "./secondRow/NavbarSecondRow";
import CategoryBox from "./categoryBox/CategoryBox";
import MyDropdown from "../MyDropdown/MyDropdown";

function Navbar() {
  return (
    <section className="navbar">
      <section className="navbarArea">
        <NavbarFirstRow />
        <div className="dropdownContainer">
          <MyDropdown />
        </div>
        <NavbarSecondRow />
      </section>
      <CategoryBox />
    </section>
  );
}

export default Navbar;
