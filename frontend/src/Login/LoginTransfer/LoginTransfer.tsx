import React from "react";
import "./LoginTransfer.scss";

interface LoginTransferProps {
  transferLogin: (loginauth: string) => void;
}

function LoginTransfer(props: LoginTransferProps) {
  const clickLoginTransfer = (auth: string) => {
    const { transferLogin } = props;
    transferLogin(auth);
  };
  return (
    <section className="LoginTransferIcons">
      <button type="button" onClick={() => clickLoginTransfer("kakao")}>
        <svg width="34" height="34" viewBox="0 0 34 34" fill="none">
          <circle cx="17" cy="17" r="14.875" fill="#FEE500" />
          <path
            fillRule="evenodd"
            clipRule="evenodd"
            d="M17.0002 9.03125C12.0901 9.03125 8.146 12.0938 8.146 15.8415C8.30698 18.4205 9.91683 20.6369 12.2511 21.604L11.4059 24.6666C11.3657 24.7472 11.4059 24.8681 11.4864 24.908C11.5669 24.989 11.7279 24.989 11.8084 24.908L15.3903 22.5309C15.9135 22.6115 16.4367 22.6518 17.0002 22.6518C21.87 22.6518 25.8543 19.5892 25.8543 15.8415C25.8543 12.0938 21.9102 9.03125 17.0002 9.03125Z"
            fill="black"
            fillOpacity="0.9"
          />
        </svg>
      </button>

      <button type="button" onClick={() => clickLoginTransfer("email")}>
        <svg width="34" height="34" viewBox="0 0 34 34" fill="none">
          <circle cx="17" cy="17" r="14.875" fill="#D0D0D0" />
          <path
            fillRule="evenodd"
            clipRule="evenodd"
            d="M24.5557 10.8608H9.44458V12.0886H24.5557V10.8608ZM24.5557 12.0887L17.0001 17.8183L9.44458 12.0887H24.5557Z"
            fill="white"
          />
          <path
            d="M20.1483 16.9997L24.5557 20.2737L24.5557 13.7256L20.1483 16.9997Z"
            fill="white"
          />
          <path
            d="M13.852 16.9997L9.44458 20.2737L9.44458 13.7256L13.852 16.9997Z"
            fill="white"
          />
          <path
            fillRule="evenodd"
            clipRule="evenodd"
            d="M17.0001 19.4554L19.1589 17.8184L24.5557 21.9109L24.5557 23.1387H9.44458V21.9109L14.8414 17.8184L17.0001 19.4554Z"
            fill="white"
          />
        </svg>
      </button>
    </section>
  );
}

export default LoginTransfer;
