import React from "react";
import Navbar from "../Navbar/Navbar";
import Body from "./Body";
import Footer from "./Footer";
import LoginBox from "../Login/LoginBox";
import "./main.scss";

function Main() {
  return (
    <section className="main">
      <Navbar />
      <Body />
      <Footer />
    </section>
  );
}

// function Login() {
//   return (
//     <section className="main">
//       <LoginBox />
//     </section>
//   );
// }

export default Main;
