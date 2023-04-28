import { changeCSS } from "./commonFunction";

describe("changeCSS()", () => {
  test("set css value for proper inputs", () => {
    const $target = document.createElement("div");
    const property = "color";
    const value = "rgb(255, 255, 255)";

    changeCSS($target, property, value);

    expect($target.style[property]).toBe(value);
  });

  test("don't set css value for improper inputs", () => {
    const $target = document.createElement("div");
    const property = "improper property";
    const value = "improper value";

    changeCSS($target, property, value);

    expect($target.style.getPropertyValue(property)).toBeFalsy();
  });
});
