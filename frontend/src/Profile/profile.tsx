import React from "react";
import Navbar from "../Navbar/Navbar";
import ProfileContent from "./profileContent";
import Footer from "../Main/Footer";
import "../Main/main.scss";

function Profile() {
  return (
    <section className="main">
      <Navbar />
      <ProfileContent />
      <Footer />
    </section>
  );
}

export default Profile;
