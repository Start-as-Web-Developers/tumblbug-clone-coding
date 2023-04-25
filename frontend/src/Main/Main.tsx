import React from "react";
import Navbar from "../Navbar/Navbar";
import Body from "./Body";
import Footer from './Footer';
import "./main.scss";

function Main() {
  return (
    <section className="main">
      <Navbar />
      <Body />
      <Footer/>
    </section>
  );
}

export default Main;
