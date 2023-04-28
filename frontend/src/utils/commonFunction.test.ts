import { changeCSS, changeMultiCSS, eventTo, $ } from "./commonFunction";
import type { cssObj } from "./commonFunction";

describe("changeCSS()", () => {
  test("<normal>", () => {
    const $target = document.createElement("div");
    const property = "color";
    const value = "rgb(255, 255, 255)";

    changeCSS($target, property, value);

    expect($target.style[property]).toBe(value);
  });

  test("<abnormal> css key or Value is weird", () => {
    const $target = document.createElement("div");
    const property = "improper property";
    const value = "improper value";

    changeCSS($target, property, value);

    expect($target.style.getPropertyValue(property)).toBeFalsy();
  });
});

describe("changeMultiCSS()", () => {
  test("<normal> input: cssObjArray", () => {
    const $target = document.createElement("div");
    const cssObjArray: cssObj[] = [
      {
        key: "color",
        cssValue: "rgb(0, 0, 0)",
      },
      {
        key: "font-size",
        cssValue: "1rem",
      },
      {
        key: "background-color",
        cssValue: "rgb(255, 255, 255)",
      },
    ];

    changeMultiCSS($target, cssObjArray);

    // test part
    cssObjArray.forEach(({ key, cssValue }) => {
      expect($target.style.getPropertyValue(String(key))).toBe(cssValue);
    });
  });

  test("<abnormal> input: cssObjArray, value of inputs are weird", () => {
    const $target = document.createElement("div");
    const cssObjArray = [
      // improper key
      {
        key: "color-x",
        cssValue: "rgb(255, 255, 255)",
      },
      // improper cssValue
      {
        key: "fontsize",
        cssValue: "크게",
      },
      // improper key & cssValue
      {
        key: "moon",
        cssValue: "GD",
      },
    ];

    changeMultiCSS($target, cssObjArray);

    // test
    cssObjArray.forEach(({ key }) => {
      expect($target.style.getPropertyPriority(String(key))).toBeFalsy();
    });
  });
});

describe("eventTo()", () => {
  test("<normal> click event", () => {
    const $target = document.createElement("div");
    const eventType = "click";
    const eventHandler = jest.fn();

    eventTo($target, eventHandler, eventType);

    const manualEvent = new MouseEvent(eventType);
    $target.dispatchEvent(manualEvent);
    expect(eventHandler).toHaveBeenCalledTimes(1);
  });

  test("<normal> multi click events", () => {
    const $target = document.createElement("div");
    const eventType = "click";
    const eventHandler = jest.fn();
    const eventCallTimes = Math.floor(Math.random() * 10) + 1;

    eventTo($target, eventHandler, eventType);

    // test
    const manualEvent = new MouseEvent(eventType);

    for (let i = 0; i < eventCallTimes; i += 1) {
      $target.dispatchEvent(manualEvent);
    }
    expect(eventHandler).toHaveBeenCalledTimes(eventCallTimes);
  });

  test("<abnormal> wrong eventType", () => {
    const $target = document.createElement("div");
    const eventType = "clickClick"; // typo of dbclick
    const eventHandler = jest.fn();

    expect(() => {eventTo($target, eventHandler, eventType)}).toThrow();
  });
});

describe("$()", () => {
  test("<normal> get HTMLElement", () => {
    const $body = document.createElement("body");
    const $div = document.createElement("div");
    $body.appendChild($div);

    expect($("div", $body)).toBe($div);
  });

  test("<normal> get null", () => {
    const $body = document.createElement("body");
    expect($("h1", $body)).toBeNull();
  });
});
