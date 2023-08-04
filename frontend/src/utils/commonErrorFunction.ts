const makeCustomErrorMessage = (
  errorLocation: string,
  errorMethodName: string,
  errorMessage: string
): string => `
    [Custom Error]
      location : ${errorLocation}
      error method : ${errorMethodName}()
      error message : ${errorMessage}
  `;

const throwCustomError = (
  errorLocation: string,
  errorMethodName: string,
  errorMessage: string
) => {
  const customErrorMessage = makeCustomErrorMessage(
    errorLocation, errorMethodName, errorMessage
  );
  throw new Error(customErrorMessage);
};

export { throwCustomError };