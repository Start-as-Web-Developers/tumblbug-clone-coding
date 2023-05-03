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

export { makeCustomErrorMessage };