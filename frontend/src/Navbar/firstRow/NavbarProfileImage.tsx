import React from "react";
import './navbarProfileImage.scss'

function NavbarProfileImage() {
  return (
    <svg
      className="navbarProfileImage"
      width="20"
      height="20"
      viewBox="0 0 48 48"
      fill="#d9d9d9"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        d="M41.8081 40.2C40.6938 32.1539 34.8597 26 27.852 26H20.1498C13.1405 26 7.30625 32.1539 6.19186 40.2C6.06567 41.1111 6 42.0465 6 43H42C42 42.0465 41.9343 41.1111 41.8081 40.2Z"
        fill="#fff"
      />
      <path
        d="M24 23C28.9639 23 33 18.9626 33 14C33 9.0374 28.9639 5 24 5C19.0379 5 15 9.0374 15 14C15 18.9626 19.0379 23 24 23Z"
        fill="#fff"
      />
    </svg>
  );
}

export default NavbarProfileImage;
